package com.talent.exam.config;

import com.talent.exam.modules.stu_exam.model.ListenModelConfig;
import com.talent.tools.WebWorkUtil;

/**
 * Created by 吴樟 on www.haixiangzhene.xyz
 * 2017/10/11
 * 1.考试状态获取
 */
public class ExamConfig {

    //获取考试配置，如果查找不到则重新创建
    public static ListenModelConfig getExamConfig(){
        Object obj= WebWorkUtil.sessionGet("ListenExamConfig");
        if (obj!=null || !(obj instanceof ListenModelConfig)){
            ListenModelConfig config=new ListenModelConfig();
            config.setOnAnswer(false);
            config.setPaperId(-1);
            config.setSubjectId(-1);
            return config;
        }
        ListenModelConfig config= (ListenModelConfig) obj;
        return config;
    }


    public static  boolean  getExamState(){
        ListenModelConfig config=getExamConfig();
        return config.isOnAnswer();
    }


}
