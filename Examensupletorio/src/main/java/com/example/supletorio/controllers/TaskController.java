package com.example.supletorio.controllers;

import com.example.supletorio.models.Tasks;
import com.example.supletorio.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/tareas")
public class TaskController {

    @Autowired
    private TaskService tareasServicio;

    @GetMapping("/{Id}")
    public ResponseEntity<?> readOne(@PathVariable(value = "id") Long id) {
        Optional<Tasks> oTarea = tareasServicio.findById(id);

        if (!oTarea.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oTarea);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Tasks tareaDetails, @PathVariable(value = "id") Long id) {
        Optional<Tasks> tarea = tareasServicio.findById(id);
        if (!tarea.isPresent()) {
            return ResponseEntity.notFound().build();
        }


        tarea.get().setNombre(tareaDetails.getNombre());
        tarea.get().setDescripcion(tareaDetails.getDescripcion());
        tarea.get().setEstado(tareaDetails.getEstado());
        tarea.get().setFecha(tareaDetails.getFecha());
        return ResponseEntity.status(HttpStatus.CREATED).body(tareasServicio.save(tareaDetails.get()));
    }

    //Delete a tarea
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        if (!tareasServicio.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        tareasServicio.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
