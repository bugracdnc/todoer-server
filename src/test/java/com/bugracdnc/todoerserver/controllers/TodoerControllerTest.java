package com.bugracdnc.todoerserver.controllers;

import com.bugracdnc.todoerserver.entities.Todoer;
import com.bugracdnc.todoerserver.mappers.TodoerMapper;
import com.bugracdnc.todoerserver.models.TodoerDTO;
import com.bugracdnc.todoerserver.repo.TodoerRepo;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class TodoerControllerTest {

    @Autowired
    TodoerController todoerController;

    @Autowired
    TodoerRepo todoerRepo;

    @Autowired
    TodoerMapper todoerMapper;

    @Rollback
    @Transactional
    @Test
    void deleteByNotFound() {
        assertThrows(NotFoundException.class, () -> todoerController.deleteById(UUID.randomUUID()));
    }

    @Rollback
    @Transactional
    @Test
    void deleteById() {
        Todoer toDelete = todoerRepo.findAll().getFirst();

        ResponseEntity<HttpStatus> res = todoerController.deleteById(toDelete.getId());

        assert res.getStatusCode() == HttpStatusCode.valueOf(204);
        assert !todoerRepo.existsById(toDelete.getId());
    }

    @Rollback
    @Transactional
    @Test
    void updateByNotFound() {
        assertThrows(NotFoundException.class, () -> todoerController.updateById(UUID.randomUUID(), TodoerDTO.builder().build()));
    }

    @Rollback
    @Transactional
    @Test
    void updateById() {
        Todoer todo = todoerRepo.findAll().getFirst();
        TodoerDTO dto = todoerMapper.objToDto(todo);
        dto.setTodo("Integration testing");

        ResponseEntity<HttpStatus> res = todoerController.updateById(todo.getId(), dto);

        assert res.getStatusCode() == HttpStatusCode.valueOf(204);
        assert Objects.equals(todoerRepo.findById(todo.getId()).get().getTodo(), dto.getTodo());
    }

    @Rollback
    @Transactional
    @Test
    void addByPost() {
        TodoerDTO dto = TodoerDTO.builder()
                                 .todo("Integration Testing")
                                 .build();

        ResponseEntity<HttpStatus> res = todoerController.addByPost(dto);

        assert res.getStatusCode() == HttpStatusCode.valueOf(201);
        assert res.getHeaders().getLocation() != null;

        String[] locationUUID = res.getHeaders().getLocation().getPath().split("/");
        UUID savedUUID = UUID.fromString(locationUUID[4]);

        assert todoerRepo.findById(savedUUID).isPresent();
    }

    @Test
    void getByIdNotFound() {
        assertThrows(NotFoundException.class, () -> todoerController.getById(UUID.randomUUID()));
    }

    @Test
    void getById() {
        Todoer todo = todoerRepo.findAll().getFirst();
        TodoerDTO dto = todoerController.getById(todo.getId());

        assert dto.getId() == todo.getId();
    }

    @Rollback
    @Transactional
    @Test
    void emptyList() {
        todoerRepo.deleteAll();
        List<TodoerDTO> todos = todoerController.list();
        assert todos.isEmpty();
    }

    @Test
    void list() {
        List<TodoerDTO> todos = todoerController.list();

        assert !todos.isEmpty();
    }


}