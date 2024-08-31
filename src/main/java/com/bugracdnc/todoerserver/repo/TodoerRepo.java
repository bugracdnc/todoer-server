package com.bugracdnc.todoerserver.repo;

import com.bugracdnc.todoerserver.entities.Todoer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TodoerRepo extends JpaRepository<Todoer, UUID> {
}
