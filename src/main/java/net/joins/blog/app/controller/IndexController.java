package net.joins.blog.app.controller;

import net.joins.blog.common.util.DateTimeUtils;
import net.joins.blog.web.vo.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

    @GetMapping("/api")
    public ResponseEntity<Object> index() {
        ApiResponse response = ApiResponse.of()
                .add("message", "This is api module")
                .add("current", DateTimeUtils.toString(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
        return response.toResponseEntity();
    }

    @GetMapping("index.html")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("post.html")
    public String post(Model model) {
        return "post";
    }


}
