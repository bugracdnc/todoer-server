package com.bugracdnc.todoerserver.services;

import com.bugracdnc.todoerserver.entities.Todoer;
import com.bugracdnc.todoerserver.mappers.TodoerMapper;
import com.bugracdnc.todoerserver.models.TodoerDTO;
import com.bugracdnc.todoerserver.repo.TodoerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class TodoerServiceJPA implements TodoerService {
    private final TodoerRepo todoerRepo;
    private final TodoerMapper todoerMapper;

    @Override
    public boolean deleteById(UUID todoId) {
        if(todoerRepo.existsById(todoId)) {
            todoerRepo.deleteById(todoId);
            return true;
        }
        return false;
    }

    @Override
    public Optional<TodoerDTO> updateById(UUID todoId, TodoerDTO dto) {
        AtomicReference<Optional<TodoerDTO>> atomicReference = new AtomicReference<>();
        todoerRepo.findById(todoId).ifPresentOrElse(todoer -> {
            todoer.setTodo(dto.getTodo());
            atomicReference.set((Optional.of(todoerMapper.objToDto(todoerRepo.save(todoer)))));
        }, () -> {
            atomicReference.set(Optional.empty());
        });

        return atomicReference.get();
    }

    @Override
    public TodoerDTO save(TodoerDTO dto) {
        Todoer todoer = todoerMapper.dtoToObj(dto);
        return todoerMapper.objToDto(todoerRepo.save(todoer));
    }

    @Override
    public Optional<TodoerDTO> getById(UUID id) {
        return Optional.ofNullable(todoerMapper.objToDto(todoerRepo.findById(id).orElse(null)));
    }

    @Override
    public List<TodoerDTO> list() {
        return todoerRepo.findAll()
                         .stream()
                         .map(todoerMapper::objToDto)
                         .toList();
    }
}
