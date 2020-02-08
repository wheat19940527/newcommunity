package com.mqm.community.controller;

import com.mqm.community.cache.TagCache;
import com.mqm.community.dto.QuestionDTO;
import com.mqm.community.mapper.QuestionMapper;
import com.mqm.community.model.Question;
import com.mqm.community.model.User;
import com.mqm.community.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id")Long id,
                       Model model){
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("title", question.getTitle());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("id",question.getId());
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @GetMapping("/publish")
    public String publish(Model model){
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @PostMapping("/publish")
    public String dopublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "id", required = false) Long id,
            HttpServletRequest request,
            Model model){

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        model.addAttribute("tags", TagCache.get());

        if(title == null || title == ""){
            model.addAttribute("error", "title can not be null");
            return "publish";
        }
        if(description == null||description==""){
            model.addAttribute("error", "description can not be null");
            return "publish";
        }
        if(tag == null||tag == ""){
            model.addAttribute("error", "tag can not be null");
            return "publish";
        }
        String invalid = TagCache.filterInvalid(tag);
        if(StringUtils.isNotBlank(invalid)){
            model.addAttribute("error","input invalid tag: " + invalid);
            return "publish";
        }

//        User user = null;
//        Cookie[] cookies = request.getCookies();
//        if(cookies != null && cookies.length!=0)
//        for (Cookie cookie: cookies){
//            if(cookie.getName().equals("token"))
//            {
//                String token = cookie.getValue();
//                user = userMapper.findByToken(token);
//                if(user != null){
//                    request.getSession().setAttribute("user",user);
//                }
//                break;
//            }
//        }
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            model.addAttribute("error","no user login!");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setId(id);
        questionService.createOrUpdate(question);
//        questionMapper.create(question);
        return "redirect:/";

    }
}
