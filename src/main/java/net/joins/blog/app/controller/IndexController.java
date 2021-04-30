package net.joins.blog.app.controller;

import lombok.RequiredArgsConstructor;
import net.joins.blog.domain.entity.Post;
import net.joins.blog.domain.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

    final PostService service;

    @GetMapping
    public String index(Model model) {
        List<Post> list = service.getPostList();

        model.addAttribute("list", list);
        return "index";
    }

    @GetMapping("/post")
    public String post(Model model, Long id) {
        Post post = service.getPost(id);

        model.addAttribute("post", post);
        return "post";
    }


}
