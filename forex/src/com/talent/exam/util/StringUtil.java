package com.talent.exam.util;

import org.apache.log4j.Logger;
import java.lang.reflect.Field;

/**
 * Created by ���� on www.haixiangzhene.xyz
 * 2017/9/14.
 */
public class StringUtil {

    private static Logger logger=Logger.getLogger(StringUtil.class);



    /**
     * ���ַ���תΪʵ����
     * @param score ��ʽ:20,0,0,10,30,40,100
     */
     public  <T> T  FromString(String score,T t) throws NoSuchFieldException, IllegalAccessException {
        String[] scoreStrs=score.split(",");
        Field[] field=getAccessibleField(t.getClass());
        if (scoreStrs!=null) {
            int size=scoreStrs.length;
            int fieldSize=field.length;
            if (size!=fieldSize)
                throw new IllegalAccessException("�ַ�����ʵ���಻ƥ��");
            for (int i=0;i<scoreStrs.length;i++) {
                try {
                    Integer scoreInt = Integer.valueOf(scoreStrs[i]);
                    field[i].setInt(t,scoreInt);
                } catch (NumberFormatException ex) {
                    logger.warn("�����ַ���ת���ֳ�������:" + ex.getMessage(), ex.getCause());
                    return null;
                }
            }
            return t;
        }
        return null;
    }

    /**
     * @param t ʵ����
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
     * ����˽�����Կɷ���
     * @param tClasses ��
     */
    public Field[] getAccessibleField(Class tClasses){
         Field[] fields=tClasses.getDeclaredFields();
         Field.setAccessible(fields,true);
         return fields;
    }

}