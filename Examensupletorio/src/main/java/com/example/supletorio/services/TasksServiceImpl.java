package com.example.supletorio.services;

import com.example.supletorio.models.Tasks;
import com.example.supletorio.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class TasksServiceImpl implements TaskService{
    @Autowired
    private TaskRepository tasksRepository;

    @Override
    public Iterable<Tasks> findAll() {
        return tasksRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Tasks> findAll(Pageable pageable) {
        return tasksRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Tasks> findById(Long id) {
        return tasksRepository.findById(id);
    }

    @Override
    @Transactional
    public Tasks save(Tasks tareas) {
        return tasksRepository.save(tareas);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        tasksRepository.deleteById(id);
    }
}
