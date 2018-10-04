package com.morethink.syspermission.util;

import java.util.Date;
import java.util.Random;

/**
 * 生成password的工具类
 *
 * @author wangpf
 */
public class PasswordUtil {

    public final static String[] WORD = {
            "a", "b", "c", "d", "e", "f", "g",
            "h", "j", "k", "m", "n",
            "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G",
            "H", "J", "K", "M", "N",
            "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z",
    };

    public final static String[] NUM = {
            "2", "3", "4", "5", "6", "7", "8", "9"
    };

    public static String randomPassword(){
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random(System.currentTimeMillis());
        boolean flag = false;

        // 设置默认密码为8 - 10位
        int length = random.nextInt(3) + 8;

        for (int i = 0; i < length; i++){
            if(flag){
                stringBuffer.append(NUM[random.nextInt(NUM.length)]);
            }else {
                stringBuffer.append(WORD[random.nextInt(WORD.length)]);
            }
            flag = !flag;
        }

        return stringBuffer.toString();
    }
}
