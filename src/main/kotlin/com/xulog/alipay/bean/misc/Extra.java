package com.xulog.alipay.bean.misc;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xulog.alipay.annotation.ExtraFields;

import java.util.HashMap;
import java.util.Map;

public class Extra {

    @ExtraFields
    @JsonIgnore
    private Map<String, Object> extraMap = new HashMap<>();

    public Object getExtraArg(String key) {
        return this.extraMap.get(key);
    }


    @JsonAnySetter
    public void setExtraArg(String name, Object value) {
        this.extraMap.put(name, value);
    }

}
