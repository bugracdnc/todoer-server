package com.bugracdnc.todoerserver.mappers;

import com.bugracdnc.todoerserver.entities.Todoer;
import com.bugracdnc.todoerserver.models.TodoerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface TodoerMapper {
    Todoer objToDTO(TodoerDTO dto);

    TodoerDTO dtoToOBJ(Todoer obj);
}
