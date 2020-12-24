package com.gsorry.quiz.controller;

import com.gsorry.quiz.domain.Answer;
import com.gsorry.quiz.domain.Question;
import com.gsorry.quiz.service.AnswerService;
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
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerService answerService;

    @GetMapping("/admin/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.finAll());
        return "admin/users/list";
    }

    @GetMapping("/admin/users/all_answered")
    public String getUsersAllAnswered(Model model) {
        model.addAttribute("users", userService.finAllAnswered());
        return "admin/users/list";
    }

    @GetMapping("/admin/users/all_correct")
    public String getUsersAllCorrect(Model model) {
        model.addAttribute("users", userService.finAllCorrect());
        return "admin/users/list";
    }

    @GetMapping("/admin/questions")
    public String getQuestions(Model model) {
        model.addAttribute("questions", questionService.findAll());
        return "admin/questions/list";
    }

    @GetMapping("/admin/questions/top_correct")
    public String getQuestionsTopCorrect(Model model) {
        model.addAttribute("questions", questionService.findTopCorrect());
        return "admin/questions/list_correct";
    }

    @GetMapping("/admin/questions/top_incorrect")
    public String getQuestionsTopIncorect(Model model) {
        model.addAttribute("questions", questionService.findTopIncorrect());
        return "admin/questions/list_incorrect";
    }

    @GetMapping("/admin/questions/new")
    public String newQuestion(Model model) {
        Question question = new Question();
        model.addAttribute("question", question);
        return "admin/questions/new";
    }

    @PostMapping("/admin/questions/create")
    public String createQuestion(@ModelAttribute("question") Question question) {
        questionService.save(question);
        return "redirect:/admin/questions";
    }

    @GetMapping("/admin/questions/edit/{id}")
    public String editQuestion(@PathVariable("id") Long id, Model model) {
        Question question = questionService.findById(id);
        model.addAttribute("question", question);
        return "admin/questions/edit";
    }

    @PostMapping("/admin/questions/update/{id}")
    public String updateQuestion(@PathVariable("id") Long id, @ModelAttribute("question") Question question) {
        questionService.save(question);
        return "redirect:/admin/questions";
    }

    @GetMapping("/admin/questions/delete/{id}")
    public String deleteQuestion(@PathVariable("id") Long id) {
        Question question = questionService.findById(id);
        questionService.delete(question);
        return "redirect:/admin/questions";
    }

    @GetMapping("/admin/answers/new/{id}")
    public String newAnswer(@PathVariable("id") Long id, Model model) {
        Question question = questionService.findById(id);
        Answer answer = new Answer();
        answer.setQuestion(question);
        model.addAttribute("answer", answer);
        return "admin/answers/new";
    }

    @PostMapping("/admin/answers/create/{id}")
    public String createAnswer(@PathVariable("id") Long id, @ModelAttribute("answer") Answer answer) {
        Question question = questionService.findById(id);
        answer.setQuestion(question);
        answerService.save(answer);
        return "redirect:/admin/questions/edit/"+id+'/';
    }

    @GetMapping("/admin/answers/edit/{id}")
    public String editAnswer(@PathVariable("id") Long id, Model model) {
        Answer answer = answerService.findById(id);
        model.addAttribute("answer", answer);
        return "admin/answers/edit";
    }

    @PostMapping("/admin/answers/update/{id}")
    public String updateAnswer(@PathVariable("id") Long id, @ModelAttribute("answer") Answer answer) {
        Answer current = answerService.findById(id);
        current.setContent(answer.getContent());
        current.setCorrect(answer.isCorrect());
        answerService.save(current);
        return "redirect:/admin/questions/edit/"+current.getQuestion().getId();
    }

    @GetMapping("/admin/answers/delete/{id}")
    public String deleteAnswer(@PathVariable("id") Long id) {
        Answer answer = answerService.findById(id);
        Question question = answer.getQuestion();
        answerService.delete(answer);
        return "redirect:/admin/questions/edit/"+question.getId();
    }
}
