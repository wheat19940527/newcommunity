package com.mqm.community.controller;

import com.mqm.community.dto.PaginationDTO;
import com.mqm.community.dto.QuestionDTO;
import com.mqm.community.mapper.QuestionMapper;
import com.mqm.community.mapper.UserMapper;
import com.mqm.community.model.Question;
import com.mqm.community.model.User;
import com.mqm.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

//    @GetMapping("/")
//     public String hello(@RequestParam(name = "name") String name, Model model)
//    {
//        model.addAttribute("name",name);
//        return "index";
//    }
    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "size",defaultValue = "5")Integer size
                        )
    {
//        Cookie[] cookies = request.getCookies();
//        if(cookies != null && cookies.length!=0)
//        for (Cookie cookie: cookies){
//            if(cookie.getName().equals("token"))
//            {
//                String token = cookie.getValue();
//                User user = userMapper.findByToken(token);
//                if(user != null){
//                    request.getSession().setAttribute("user",user);
//                }
//                break;
//            }
//        }

        PaginationDTO pagination = questionService.list(page,size);
        model.addAttribute("pagination",pagination);
        return "index";
    }
}
