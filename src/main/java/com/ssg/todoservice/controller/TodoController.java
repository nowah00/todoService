package com.ssg.todoservice.controller;

import com.ssg.todoservice.dto.TodoDTO;
import com.ssg.todoservice.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @RequestMapping("list")
    public void list(Model model) {
        log.info("list() 호출 성공");
        model.addAttribute("dtoList", todoService.getAll());
    }

    @GetMapping("/register")
    public void register() {
        log.info("register() 호출 성공");
    }

    @PostMapping("/register")
    public String registerPost(@Valid TodoDTO todoDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        log.info("registerPost() 호출 성공");
        if (bindingResult.hasErrors()) {
            log.info("에러 발생..");
            redirectAttributes.addFlashAttribute("errors", bindingResult);
                return "redirect:/todo/list";
        }
        log.info(todoDTO);
        todoService.register(todoDTO);
        return "redirect:/todo/list";
    }

    @GetMapping({"/read","/modify"})
    public void read(Long tno, Model model) {
        log.info("read() 호출 성공");

        TodoDTO todoDTO = todoService.getOne(tno);
        log.info(todoDTO);

        model.addAttribute("dto",todoDTO);
    }

    @PostMapping("/remove")
    public String remove(Long tno,RedirectAttributes redirectAttributes) {
        log.info("remove() 호출 성공");
        log.info(tno + "삭제");

        todoService.remove(tno);

        return "redirect:/todo/list";
    }

    @PostMapping("/modify")
    public String modify (@Valid TodoDTO todoDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){

        log.info("modifyPost() 호출 성공");
        if (bindingResult.hasErrors()) {
            log.info("에러 발생..");
            redirectAttributes.addFlashAttribute("errors", bindingResult);
            return "redirect:/todo/modify";
        }

        log.info(todoDTO);

        todoService.modify(todoDTO);

        return "redirect:/todo/list";
    }
}
