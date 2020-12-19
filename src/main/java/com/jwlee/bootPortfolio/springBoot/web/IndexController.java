package com.jwlee.bootPortfolio.springBoot.web;

import com.jwlee.bootPortfolio.springBoot.config.auth.LoginUser;
import com.jwlee.bootPortfolio.springBoot.config.auth.dto.SessionUser;
import com.jwlee.bootPortfolio.springBoot.service.posts.PostsService;
import com.jwlee.bootPortfolio.springBoot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){

        model.addAttribute("posts",postsService.findAllDesc());

        if (user != null){
            System.out.println(user.getName());
            System.out.println(user.getEmail());
            model.addAttribute("userName",user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postSave(){
        return "posts-save";
    }


    @GetMapping("/posts/update/{id}")
    public String postUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);

        return "posts-update";
    }

}
