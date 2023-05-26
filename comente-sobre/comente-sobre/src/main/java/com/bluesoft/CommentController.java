package com.bluesoft;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CommentController {
    private CommentService commentService;
    private Map<String, List<Comment>> commentsMap;

    public CommentController() {
        this.commentService = new CommentService();
        this.commentsMap = new HashMap<>();
    }

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @PostMapping("/comment")
    public String addComment(@RequestParam("topic") String topic, @RequestParam("email") String email,
                             @RequestParam("comment") String comment) {
        List<Comment> comments = commentsMap.getOrDefault(topic, new ArrayList<>());
        Comment newComment = new Comment(email, comment);
        comments.add(newComment);
        commentsMap.put(topic, comments);
        return "redirect:/comment/" + topic;
    }

    @GetMapping("/comment/{topic}")
    public String commentPage(@PathVariable("topic") String topic, Model model) {
        List<Comment> comments = commentsMap.getOrDefault(topic, new ArrayList<>());
        model.addAttribute("topic", topic);
        model.addAttribute("comments", comments);
        return "comment";
    }
}
