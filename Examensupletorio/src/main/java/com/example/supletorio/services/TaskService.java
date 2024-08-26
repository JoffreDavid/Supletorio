package com.example.supletorio.services;

import com.example.supletorio.models.Tasks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.config.Task;

import java.util.Optional;


public interface TaskService {
    public Iterable <Tasks> findAll();
    public Page<Tasks> findAll(Pageable pageable);
    public Optional<Tasks> findById(Long id);
    public Tasks save(Task usuarios);
    public void deleteById(Long id);
}
