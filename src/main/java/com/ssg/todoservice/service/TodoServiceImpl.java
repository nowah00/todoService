package com.ssg.todoservice.service;

import com.ssg.todoservice.domain.TodoVO;
import com.ssg.todoservice.dto.TodoDTO;
import com.ssg.todoservice.mapper.TodoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    private final TodoMapper todoMapper;

    private final ModelMapper modelMapper;

    @Override
    public void register(TodoDTO todoDTO) {
        log.info("register() 호출 성공");

        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        log.info(todoVO);

        todoMapper.insert(todoVO);
    }

    @Override
    public List<TodoDTO> getAll() {
        log.info("getAll() 호출 성공");

        List<TodoDTO> dtoList = todoMapper.selectAll().stream()
                .map(vo -> modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public TodoDTO getOne(Long tno) {
        log.info("getOne() 호출 성공");

        TodoVO todoVO = todoMapper.selectOne(tno);

        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);

        return todoDTO;
    }

    @Override
    public void remove(Long tno) {
        log.info("remove() 호출 성공");
        todoMapper.delete(tno);
    }

    @Override
    public void modify(TodoDTO todoDTO) {
        log.info("modify() 호출 성공");
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        todoMapper.update(todoVO);
    }
}
