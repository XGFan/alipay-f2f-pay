package com.xulog.alipay.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import kotlin.Pair;
import kotlin.collections.SetsKt;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("unchecked")
public class PojoUtils {
    /**
     * 把一个对象的所有field取出来，含有@ExtraFields注解的作为额外部分
     * 忠实简单的拼合在一起（不会排除任何field，除了ExtraFields部分）
     * todo 这个应该废弃掉，使用Jackson的会更稳妥一些，这个支持部分
     * todo jackson的注解，但是不完全支持等同于混乱
     *
     * @param bean 对象咯
     */
    public static <T> Stream<Pair<String, Object>> transToKeyValueList(T bean) {
        if (bean == null) {
            return Stream.empty();
        }
        if (Map.class.isAssignableFrom(bean.getClass())) {
            //如果是map，直接迭代
            return ((Map<Object, Object>) bean).entrySet()
                    .stream().map(it -> new Pair<>(it.getKey().toString(), it.getValue()))
                    .filter(Objects::nonNull);

        } else {
            //获取所有fields
            return getAllFields(bean.getClass()).stream()
                    .filter(Objects::nonNull) //先过滤掉空的
                    .filter(it -> !it.isSynthetic()) //非外部类引用
                    .flatMap(it -> {
                        //获取field
                        String fieldKey = it.getName();
                        //获取value
                        it.setAccessible(true);
                        Object fieldValue;
                        try {
                            fieldValue = it.get(bean);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                            return Stream.empty();
                        }
                        //这边借用一下jackson的注解
                        if (it.getAnnotation(JsonIgnore.class) != null) {
                            return null;
                        }
                        if (it.getAnnotation(JsonUnwrapped.class) != null) {
                            return transToKeyValueList(fieldValue);
                        }
                        //存在fieldConfig
                        JsonProperty jsonProperty = it.getAnnotation(JsonProperty.class);
                        if (jsonProperty != null) {
                            String alias = jsonProperty.value();
                            if (!alias.isEmpty()) {
                                fieldKey = alias;
                            }
                        } else {
                            if (fieldValue != null && Date.class.isAssignableFrom(it.getType())) {
                                JsonFormat jsonFormat = it.getAnnotation(JsonFormat.class);
                                if (jsonFormat != null) {
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(jsonFormat.pattern());
                                    simpleDateFormat.setTimeZone(TimeZone.getTimeZone(ZoneId.of("GMT+8")));
                                    return Stream.of(new Pair<String, Object>(fieldKey, simpleDateFormat.format(fieldValue)));
                                }
                            }
                        }
                        return Stream.of(new Pair<>(fieldKey, fieldValue));
                    })
                    .filter(Objects::nonNull);//再次过滤掉可能出错的null
        }
    }


    public static HashSet<Field> getAllFields(Class<?> aClass) {
        return getAllFields(aClass, new HashSet<>());
    }

    public static HashSet<Field> getAllFields(Class<?> aClass, HashSet<Field> fields) {
        fields.addAll(Arrays.asList(aClass.getDeclaredFields()));
        if (!aClass.equals(Object.class)) {
            Class<?> superclass = aClass.getSuperclass();
            return getAllFields(superclass, fields);
        } else {
            return fields;
        }
    }

    public static <T> String transToSortedQueryStr(T bean, String... excludeFields) {
        HashSet<String> excludes = SetsKt.hashSetOf(excludeFields);
        return transToKeyValueList(bean)
                .filter(it -> !excludes.contains(it.getFirst()))//过滤掉指定不参与的字符串
                .filter(it -> it.getSecond() != null && !"".equals(it.getSecond())) //过滤掉空的
                .sorted(Comparator.comparing(Pair::getFirst)) //排序
                .map(it -> it.getFirst() + "=" + it.getSecond())//拼接成a=b
                .collect(Collectors.joining("&"));//合并成a=b&c=d
    }

}
