package com.mqm.community.controller;

import com.mqm.community.dto.PaginationDTO;
import com.mqm.community.model.User;
import com.mqm.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "action") String action,
                          Model model,
                          @RequestParam(name = "page",defaultValue = "1")Integer page,
                          @RequestParam(name = "size",defaultValue = "5")Integer size){
//        User user = null;
//        Cookie[] cookies = request.getCookies();
//        if(cookies != null && cookies.length!=0){
//            for (Cookie cookie: cookies){
//                if(cookie.getName().equals("token"))
//                {
//                    String token = cookie.getValue();
//                    user = userMapper.findByToken(token);
//                    if(user != null){
//                        request.getSession().setAttribute("user",user);
//                    }
//                    break;
//                }
//            }
//        }
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:/";
        }

        if("questions".equals(action)){
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "my questions");
        }else if ("replies".equals(action)){
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "my answers");
        }
        PaginationDTO paginationDTO = questionService.list(user.getId(), page, size);
        model.addAttribute("pagination",paginationDTO);
        return "profile";
    }
}
