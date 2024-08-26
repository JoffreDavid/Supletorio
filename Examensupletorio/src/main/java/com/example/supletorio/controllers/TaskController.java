package com.example.supletorio.controllers;

import com.example.supletorio.models.Tasks;
import com.example.supletorio.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Tasks> readTasks() {
        return taskService.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readOne(@PathVariable(value = "id") Long id) {
        Optional<Tasks> task = taskService.findById(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Tasks newTask) {
        Tasks savedTask = taskService.save(newTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Tasks taskDetails, @PathVariable(value = "id") Long id) {
        Optional<Tasks> task = taskService.findById(id);
        if (!task.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Tasks existingTask = task.get();
        existingTask.setTitulo(taskDetails.getTitulo());
        existingTask.setDescripcion(taskDetails.getDescripcion());
        existingTask.setEstado(taskDetails.getEstado());
        existingTask.setFecha(taskDetails.getFecha());

        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(existingTask));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        if (!taskService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        taskService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/status/{estado}")
    public List<Tasks> getTasksByStatus(@PathVariable(value = "estado") boolean estado) {
        return taskService.findAll()
                .stream()
                .filter(task -> task.getEstado() == estado)
                .collect(Collectors.toList());
    }
}
