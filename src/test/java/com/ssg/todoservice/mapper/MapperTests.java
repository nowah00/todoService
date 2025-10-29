package com.ssg.todoservice.mapper;

import com.ssg.todoservice.domain.TodoVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class MapperTests {

    @Autowired(required = false)
    private TodoMapper todoMapper;

    @Test
    public void testGetTime() {
        log.info("Time : " + todoMapper.getTime());
    }

    @Test
    public void testInsert() {
        TodoVO todoVO = TodoVO.builder()
                .title("Good Morning")
                .dueDate(LocalDate.parse("2000-12-06"))
                .writer("Hawon")
                .build();
        todoMapper.insert(todoVO);
    }

    @Test
    public void testSelectAll() {
        List<TodoVO> voList = todoMapper.selectAll();
        log.info(voList);
    }

    @Test
    public void testSelectOne() {
        Long tno = 1L;

        TodoVO todoVO = todoMapper.selectOne(tno);

        log.info(todoVO);
    }

    @Test
    public void testDelete() {
        Long tno = 2L;

        todoMapper.delete(tno);
    }

    @Test
    public void testUpdate() {
        TodoVO todoVO = TodoVO.builder()
                .tno(4L)
                .title("Spring 프로젝트 설정하기")
                .dueDate(LocalDate.parse("2000-12-06"))
                .finished(true).build();
        todoMapper.update(todoVO);
    }

}
