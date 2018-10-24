package com.volunteer.commonweal.common;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class TokenGenerateUtils {
    private static String getRandom(){
        String value = "";
        Random random = new Random();
        int gen = random.nextInt(2);
        String charOrNum = gen % 2 ==0 ? "char":"num";
        if ("char".equals(charOrNum)) {
            //字符
            int temp = random.nextInt(2)%2==0?65:97;
            int ascii = random.nextInt(26);
            value += (char)(ascii + temp);
        }else if ("num".equals(charOrNum)) {
            //是数字
            value += String.valueOf(random.nextInt(10));
        }
        return value;
    }

    private static Set<String> getStrAndNum(int length) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < length; i++) {
            String value = getRandom();
            set.add(value);
        }
        //若生成的字符串没达到指定长度 继续生成
        if (set.size() < length) {
            String value = getRandom();
            set.add(value);
        }
        return set;
    }

    private static  String printSet(Set set){ //打印set的方法
        Iterator iterator = set.iterator();
        String value = "";
        while (iterator.hasNext()) {
            //String ele = (String) iterator.next();
            value += (String)iterator.next();
        }
        return value;
    }


    public static String getRandonString(int length){
        String value= "";
        if (length > 0) {
            //如果返回的字符串小于指定长度 重新生成
            while (value.length() < length) {
                Set<String> store = getStrAndNum(length);
                value = printSet(store);
            }
            return value;
        }else{
            return value;
        }
    }

}
