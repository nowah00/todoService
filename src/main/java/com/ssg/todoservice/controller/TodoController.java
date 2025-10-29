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

    // 전체 목록 조회
    @RequestMapping("list")
    public void list(Model model) {
        log.info("list() start");
        model.addAttribute("dtoList", todoService.getAll());
    }

    // 목록 생성 페이지 이동
    @GetMapping("/register")
    public void register() {
        log.info("Get register() start");
    }

    // 목록 생성
    @PostMapping("/register")
    public String registerPost(@Valid TodoDTO todoDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        log.info("Post register() start");
        if (bindingResult.hasErrors()) {
            log.info("에러 발생..");
            redirectAttributes.addFlashAttribute("errors", bindingResult);
                return "redirect:/todo/list";
        }
        log.info(todoDTO);
        todoService.register(todoDTO);
        return "redirect:/todo/list";
    }

    // 상세 목록 조회
    @GetMapping({"/read","/modify"})
    public void read(Long tno, Model model) {
        log.info("Get read() start");
        TodoDTO todoDTO = todoService.getOne(tno);
        log.info(todoDTO);
        model.addAttribute("dto",todoDTO);
    }

    // 목록 삭제
    @PostMapping("/remove")
    public String remove(Long tno,RedirectAttributes redirectAttributes) {
        log.info("Post remove() start");
        log.info(tno + "삭제");
        todoService.remove(tno);
        return "redirect:/todo/list";
    }

    // 목록 수정
    @PostMapping("/modify")
    public String modify (@Valid TodoDTO todoDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){
        log.info("Post modifyPost() start");
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
