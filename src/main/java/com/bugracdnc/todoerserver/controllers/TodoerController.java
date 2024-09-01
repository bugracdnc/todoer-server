package com.bugracdnc.todoerserver.controllers;

import com.bugracdnc.todoerserver.models.TodoerDTO;
import com.bugracdnc.todoerserver.services.TodoerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TodoerController {
    public static final String TODOER_PATH = "/api/v1/todoer";
    public static final String ID_PATH = TODOER_PATH + "/{todoId}";
    private final TodoerService todoerService;

    @DeleteMapping(ID_PATH)
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("todoId") UUID todoId) {
        if(todoerService.deleteById(todoId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        throw new NotFoundException();
    }

    @PutMapping(ID_PATH)
    public ResponseEntity<HttpStatus> updateById(@PathVariable("todoId") UUID todoId, @Validated @RequestBody TodoerDTO dto) {
        if(todoerService.updateById(todoId, dto).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        throw new NotFoundException();
    }

    @PostMapping(TODOER_PATH)
    public ResponseEntity<HttpStatus> addByPost(@Validated @RequestBody TodoerDTO todoerDTO) {
        TodoerDTO savedDTO = todoerService.save(todoerDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", TODOER_PATH + "/" + savedDTO.getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping(ID_PATH)
    public TodoerDTO getById(@PathVariable UUID todoId) {return todoerService.getById(todoId).orElseThrow(NotFoundException::new);}

    @GetMapping(TODOER_PATH)
    public List<TodoerDTO> list() {return todoerService.list();}
}
