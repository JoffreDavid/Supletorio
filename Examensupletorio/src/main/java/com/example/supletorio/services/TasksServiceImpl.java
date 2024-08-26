package com.example.supletorio.services;

import com.example.supletorio.models.Tasks;
import com.example.supletorio.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TasksServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Tasks> findAll() {
        return taskRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Tasks> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    @Transactional
    public Tasks save(Tasks task) {
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
