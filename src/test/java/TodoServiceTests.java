import com.ssg.todoservice.domain.TodoVO;
import com.ssg.todoservice.dto.TodoDTO;
import com.ssg.todoservice.service.TodoService;
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
public class TodoServiceTests {

    @Autowired
    private TodoService todoService;

    @Test
    public void testRegister() {
        TodoDTO todoDTO = TodoDTO.builder()
                .title("Good Morning")
                .dueDate(LocalDate.parse("2000-12-06"))
                .writer("Hawon")
                .build();
        todoService.register(todoDTO);
    }

    @Test
    public void testGetAll() {
        List<TodoDTO> dtoList = todoService.getAll();
        log.info(dtoList);
    }

    @Test
    public void testGetOne() {
        Long tno = 4L;
        TodoDTO todoDTO = todoService.getOne(tno);
        log.info(todoDTO);
    }

    @Test
    public void testDelete() {
        Long tno = 4L;
        todoService.remove(tno);
    }

    @Test
    public void testUpdate() {
        TodoDTO todoDTO = TodoDTO.builder()
                .tno(5L)
                .title("Spring 프로젝트 설정하기")
                .dueDate(LocalDate.parse("2000-12-06"))
                .finished(true).build();
        todoService.modify(todoDTO);
    }
}
