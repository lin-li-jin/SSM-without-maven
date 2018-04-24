package com.talent.exam.util;

import org.apache.log4j.Logger;
import java.lang.reflect.Field;

/**
 * Created by 吴樟 on www.haixiangzhene.xyz
 * 2017/9/14.
 */
public class StringUtil {

    private static Logger logger=Logger.getLogger(StringUtil.class);



    /**
     * 将字符串转为实体类
     * @param score 格式:20,0,0,10,30,40,100
     */
     public  <T> T  FromString(String score,T t) throws NoSuchFieldException, IllegalAccessException {
        String[] scoreStrs=score.split(",");
        Field[] field=getAccessibleField(t.getClass());
        if (scoreStrs!=null) {
            int size=scoreStrs.length;
            int fieldSize=field.length;
            if (size!=fieldSize)
                throw new IllegalAccessException("字符串与实体类不匹配");
            for (int i=0;i<scoreStrs.length;i++) {
                try {
                    Integer scoreInt = Integer.valueOf(scoreStrs[i]);
                    field[i].setInt(t,scoreInt);
                } catch (NumberFormatException ex) {
                    logger.warn("分数字符串转数字出现问题:" + ex.getMessage(), ex.getCause());
                    return null;
                }
            }
            return t;
        }
        return null;
    }

    /**
     * @param t 实体类
     */
    public <T> String FromBean(T t){
        Field[] fields=getAccessibleField(t.getClass());
        StringBuilder sb=new StringBuilder();
        for (Field field:fields){
            try {
                int intVal=field.getInt(t);
                sb.append(intVal+",");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        sb.delete(sb.length()-1,sb.length());
        return sb.toString();
    }


    /**
     * 设置私有属性可访问
     * @param tClasses 类
     */
    public Field[] getAccessibleField(Class tClasses){
         Field[] fields=tClasses.getDeclaredFields();
         Field.setAccessible(fields,true);
         return fields;
    }

}
