package net.joins.blog.app.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

    @ModelAttribute("common")
    public void common(Model model) {
        Map<String, String> header = new HashMap<>();
        header.put("name", "misolab.com");
        header.put("url", "https://www.misolab.com");
        header.put("title", "Misolab's Blog");
        header.put("subtitle", "developed by single-archetype");
        model.addAttribute("header", header);

        Map<String, String> footer = new HashMap<>();
        footer.put("github", "https://www.github.com");
        footer.put("twitter", "https://www.twitter.com");
        footer.put("facebook", "https://www.facebook.com");
        model.addAttribute("footer", footer);
    }
}
