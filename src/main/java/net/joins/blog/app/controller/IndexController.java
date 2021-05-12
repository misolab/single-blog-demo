package net.joins.blog.app.controller;

import lombok.RequiredArgsConstructor;
import net.joins.blog.common.util.CalendarUtil;
import net.joins.blog.domain.entity.Post;
import net.joins.blog.domain.service.PostService;
import net.joins.blog.web.vo.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

    final PostService service;

    @GetMapping
    public String index(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, value = 10) Pageable pageable) {
        Page<Post> list = service.getPostList(pageable);

        model.addAttribute("list", list);
        return "index";
    }

    @GetMapping("/post")
    public String post(Model model, Long id) {
        Post post = service.getPost(id);

        model.addAttribute("post", post);
        return "post";
    }


    final CalendarUtil calendarUtil;

    @PostConstruct
    void onCreate() {
        Map<LocalDate, String> holidays = new HashMap<>();
        holidays.put(LocalDate.of(2021, 5, 5), "어린이날");
        holidays.put(LocalDate.of(2021, 5, 19), "석가탄신일");
        calendarUtil.setHolidays(holidays);
    }

    @GetMapping("/date")
    public String date(Model model, String date, int count) {
        CalendarUtil.DateInfo[] dates = calendarUtil.getDateInfo(date, count);
        model.addAttribute("dates", dates);
        return "date";
    }

    @GetMapping("/api")
    public ResponseEntity date(String date, int count) {
        CalendarUtil.DateInfo[] dates = calendarUtil.getDateInfo(date, count);
        ApiResponse response = ApiResponse.of().add("dates", dates);
        return response.toResponseEntity();
    }


}
