package com.bugracdnc.todoerserver.services;

import com.bugracdnc.todoerserver.mappers.TodoerMapper;
import com.bugracdnc.todoerserver.repo.TodoerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoerServiceJPA implements TodoerService {
    private final TodoerRepo todoerRepo;
    private final TodoerMapper todoerMapper;
}
