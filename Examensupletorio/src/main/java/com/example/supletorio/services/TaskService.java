package com.example.supletorio.services;

import com.example.supletorio.models.Tasks;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<Tasks> findAll();
    Optional<Tasks> findById(Long id);
    Tasks save(Tasks task);
    void deleteById(Long id);
}
