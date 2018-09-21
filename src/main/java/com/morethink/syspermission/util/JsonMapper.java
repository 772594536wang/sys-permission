package com.morethink.syspermission.util;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类和json对象相互转化工具类
 *
 * @author wangpf
 */
public class JsonMapper {
    private final static Logger log = LoggerFactory.getLogger(JsonMapper.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 设置有未知属性不能映射成PO时，不报错
        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);

        // 设置一个null对象转化为json，不报错
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);

        objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));

        // 排除空值
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
    }

    /**
     * 对象转string
     *
     * @param object 被转化的对象
     * @param <T>    对象泛型
     * @return 转化后的字符串
     */
    public static <T> String object2String(T object) {
        if (object == null) {
            return null;
        }
        try {
            return object instanceof String ? (String) object : objectMapper.writeValueAsString(object);
        } catch (Exception ex) {
            log.warn("parse object to string exception ,error:{}", ex);
            return null;
        }
    }

    public static <T> T string2Object(String src, TypeReference<T> typeReference) {
        if (src == null || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? src : objectMapper.readValue(src, typeReference));
        } catch (Exception ex) {
            log.warn("parse string to object exceptionm, string:{} , typeReference<T>:{}, error:{}", src, typeReference.getType(), ex);
            return null;
        }
    }
}
