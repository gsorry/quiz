package com.gsorry.quiz.controller;

import com.gsorry.quiz.domain.Decision;
import com.gsorry.quiz.service.DecisionService;
import com.gsorry.quiz.service.QuestionService;
import com.gsorry.quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

    @Autowired
    DecisionService decisionService;

    @GetMapping("/user/questionnaire/{page}")
    public String getQuestionnaire(@PathVariable("page") String page, Model model) {
        model.addAttribute("decision", decisionService.findOrNew(page));
        model.addAttribute("questions", questionService.findNext(page));
        return "user/questionnaire";
    }

    @PostMapping("/user/questionnaire/{page}")
    public String postQuestionnaire(@PathVariable("page") String page, @ModelAttribute("decision") Decision decision) {
        decisionService.save(decision);
        if (questionService.isLast(page)) {
            return "redirect:/user/list";
        } else {
            return "redirect:/user/questionnaire/"+(Integer.parseInt(page) + 1);
        }
    }

    @GetMapping("/user/list")
    public String getList(Model model) {
        model.addAttribute("users", userService.findTopFive());
        return "user/list";
    }
}
