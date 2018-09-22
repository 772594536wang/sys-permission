package com.morethink.syspermission.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 处理目录结构的层级level工具类
 *
 * @author wangpf
 */
public class LevelUtil {
    /**
     * 定义各个层级的分隔符
     */
    private final static String SEPARATOR = ".";

    /**
     * 目录结构的根id是0
     */
    private final static String ROOT = "0";

    /**
     * 目录层级的计算规则,目录如下：
     * 0
     * 0.1
     * 0.1.1
     * 0.1.2
     * 0.4
     */
    public static String calculateLevel(String parentLevel, Integer parentId) {
        if (StringUtils.isBlank(parentLevel)) {
            // 当前是首层，返回root值
            return ROOT;
        } else {
            return StringUtils.join(parentLevel, SEPARATOR, parentId);
        }
    }
}
