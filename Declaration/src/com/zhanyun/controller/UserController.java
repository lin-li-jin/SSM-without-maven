package com.zhanyun.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zhanyun.bean.User;


@Controller  
@RequestMapping("/user")
public class UserController {
private static Logger log=LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value="/test",method=RequestMethod.GET)  
    public String test(HttpServletRequest request,Model model){  
        int userId = Integer.parseInt(request.getParameter("id"));  
        System.out.println("userId:"+userId);
        User user=null;
        if (userId==1) {
             user = new User();  
             user.setId("1");
             user.setName("lin");
             user.setPassword("123");
        }
       
        log.debug(user.toString());
        model.addAttribute("user", user);  
        return "index";  
    } 
    
    
    
    /**
     * 
     * @description 这是用来测试是否能正常返回json数据的
     * @author linlj
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/json",method = RequestMethod.GET,produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public String hello(HttpServletResponse response) throws IOException{
        User u1=new User();
        u1.setId("1");
        u1.setName("林丽君");
        u1.setPassword("123");
        
        User u2=new User();
        u2.setId("2");
        u2.setName("林小宅");
        u2.setPassword("345");
        
       
        Map<String,User> map=new HashMap<String, User>();
        map.put("001", u1);
        map.put("002", u2);
        String jsonString = JSON.toJSONString(map);
        return jsonString;
    }
}
