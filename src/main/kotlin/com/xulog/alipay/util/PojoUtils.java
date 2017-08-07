package com.xulog.alipay.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Sets;
import com.xulog.alipay.annotation.ExtraFields;
import kotlin.Pair;
import org.reflections.ReflectionUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
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
     *
     * @param bean 对象咯
     */
    public static <T> Stream<Pair<String, Object>> transToKeyValueList(T bean) {
        Set<Field> allFields = ReflectionUtils.getAllFields(bean.getClass());
        Stream<Pair<String, Object>> extraStream = allFields
                .stream()
                .filter(Objects::nonNull) //null
                .filter(it -> !it.isSynthetic()) //外部类引用
                .filter(it -> it.getAnnotation(ExtraFields.class) != null) //包含extraFields
                .flatMap(it -> {
                    it.setAccessible(true);
                    try {
                        if (Map.class.isAssignableFrom(it.getType())) {
                            return ((Map<Object, Object>) it.get(bean)).entrySet()
                                    .stream()
                                    .map(kv -> new Pair<>(kv.getKey().toString(), kv.getValue()));
                        }
                        else {
                            Object o = it.get(bean);
                            return transToKeyValueList(o);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        return null;
                    }
                });//处理

        Stream<Pair<String, Object>> fieldStream = allFields //获取所有field
                .stream()
                .filter(Objects::nonNull) //null
                .filter(it -> !it.isSynthetic()) //外部类引用
                .filter(it -> it.getAnnotation(ExtraFields.class) == null) //包含extraFields
                .filter(it -> !"log".equals(it.getName()))
                .map(it -> {
                    it.setAccessible(true);
                    String fieldName = it.getName();
                    Object value;
                    try {
                        value = it.get(bean);
                        if (value != null && Date.class.isAssignableFrom(it.getType())) {
                            JsonFormat annotation = it.getAnnotation(JsonFormat.class);
                            if (annotation != null) {
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(annotation.pattern());
                                simpleDateFormat.setTimeZone(TimeZone.getTimeZone(ZoneId.of("GMT+8")));
                                return new Pair<>(fieldName, simpleDateFormat.format(value));
                            }
                        }
                        return new Pair<>(fieldName, value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        return null;
                    }
                });//处理

        return Stream.concat(fieldStream, extraStream).filter(Objects::nonNull);
    }

    /**
     * 把一个对象的所有field取出来，含有@ExtraFields注解的作为额外部分
     * 拼合在一起（排除value为空或者null）
     *
     * @param bean 对象咯
     */
    public static <T> Map<String, Object> toMap(T bean) {
        return transToKeyValueList(bean)
                .filter(it -> it.getFirst() != null && !"".equals(it.getSecond()))
                .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
    }

    /**
     * 把一个对象的所有field取出来，含有@ExtraFields注解的作为额外部分
     * 拼合在一起（排除value为空或者null）
     * 相对于toMap，区别在与value为String，全部调用toString()
     *
     * @param bean 对象咯
     */
    public static <T> Map<String, String> toForm(T bean) {
        return transToKeyValueList(bean)
                .filter(it -> it.getFirst() != null && !"".equals(it.getSecond()))
                .collect(Collectors.toMap(Pair::getFirst, it -> it.getSecond().toString()));
    }

    /**
     * 把一个对象的所有field取出来，含有@ExtraFields注解的作为额外部分
     * 拼合在一起（排除value为空或者null）
     *
     * @param bean 对象咯
     */
    public static <T> String toXML(T bean) {
        return transToKeyValueList(bean)
                .filter(it -> it.getSecond() != null && !"".equals(it.getFirst()))
                .map(it -> "<" + it.getFirst() + ">" + it.getSecond() + "</" + it.getFirst() + ">")
                .collect(Collectors.joining("", "<xml>", "</xml>"));
    }


    public static <T> String transToSortedQueryStr(T bean, String... excludeFields) {
        HashSet<String> excludes = Sets.newHashSet(excludeFields);
        return transToKeyValueList(bean)
                .filter(it -> !excludes.contains(it.getFirst()))//过滤掉指定不参与的字符串
                .filter(it -> it.getSecond() != null && !"".equals(it.getSecond())) //过滤掉空的
                .sorted(Comparator.comparing(Pair::getFirst)) //排序
                .map(it -> it.getFirst() + "=" + it.getSecond())//拼接成a=b
                .collect(Collectors.joining("&"));//合并成a=b&c=d
    }


    public static Map<String, String> queryStrToMap(String queryStr) {
        return Arrays.stream(queryStr.split("&"))
                .map(it -> {
                    String[] split = it.split("=");
                    return new Pair<>(split[0], split[1]);
                })
                .map(it -> {
                    try {
                        return new Pair<>(URLDecoder.decode(it.getFirst(), "UTF-8"), URLDecoder.decode(it.getSecond(), "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return null;
                    }

                })
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
    }


}
