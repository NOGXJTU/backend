package com.volunteer.commonweal.common;
//对象检验类
public class Objects {
    public static final boolean isNull(Object object){return object == null;}

    public static final boolean isNotNull(Object object){return object != null;}

    public static final <T> T requireNotNull(T obj){
        if(obj == null){
            throw new NullPointerException();//NullPointerException不能被捕获
        }
        return obj;
    }
    public static final void assertAllNotNull(Object... objects){
        for(Object object:objects){
            if(object == null){
                throw new NullPointerException();
            }
        }
    }
    public static final boolean isNotNull(Object... objects){
        for(Object object:objects){
            if(isNull(object)){
                return false;
            }
        }
        return true;
    }
}
