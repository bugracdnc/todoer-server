package com.bugracdnc.todoerserver.services;

import com.bugracdnc.todoerserver.models.TodoerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TodoerService {
    List<TodoerDTO> list();

    TodoerDTO save(TodoerDTO dto);

    Optional<TodoerDTO> getById(UUID id);

    Optional<TodoerDTO> updateById(UUID todoId, TodoerDTO dto);

    boolean deleteById(UUID todoId);
}
