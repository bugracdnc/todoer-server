package com.bugracdnc.todoerserver.controllers;

import com.bugracdnc.todoerserver.services.TodoerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TodoerController {
    public static final String TODOER_PATH = "/api/v1/todoer";
    public static final String ID_PATH = TODOER_PATH+ "/{todoId}";
    private final TodoerService todoerService;
}
