package net.joins.blog.app.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

    @Getter
    @Builder
    static class Post {
        String link;
        String title;
        String subtitle;
        String writter;
        String updated;
        //  for post page
        String content;
        String bgImage;
    }

    @GetMapping("index.html")
    public String index(Model model) {
        List<Post> list = IntStream.range(1, 10)
                .mapToObj(i -> Post.builder()
                        .link("post.html")
                        .title("제목 - " + i)
                        .subtitle("subtitle - " + i)
                        .writter("misolab")
                        .updated("2021-02-08")
                        .build())
                .collect(Collectors.toList());

        model.addAttribute("list", list);
        return "index";
    }

    @GetMapping("post.html")
    public String post(Model model) {
        Post post = Post.builder()
                .title("Man must explore, and")
                .subtitle("Problems look mighty small from 150 miles up")
                .writter("misolab")
                .updated("2021-04-30")
                .content("Wellcome!!<img class=\"img-fluid\" src=\"/images/post-sample-image.jpg\">")
                .bgImage("assets/img/post-bg.jpg")
                .build();

        model.addAttribute("post", post);
        return "post";
    }


}
