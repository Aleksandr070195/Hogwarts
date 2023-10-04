package com.example.hogwarts.controller;

import com.example.hogwarts.model.Faculty;
import com.example.hogwarts.service.FacultyService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService service;

    public FacultyController(FacultyService service) {
        this.service = service;
    }

    @PostMapping
    public Faculty create(@RequestBody Faculty faculty) {
        return service.create(faculty);
    }

    @PutMapping
    public Faculty update(@PathVariable("id") Long id, @RequestBody Faculty faculty) {
        return service.update(id, faculty);
    }

    @DeleteMapping("/{id}")
    public Faculty delete(@PathVariable("id") Long id) {
        return service.remove(id);
    }

    @GetMapping("/{id}")
    public Faculty getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @GetMapping
    public Collection<Faculty> getAll() {
        return service.getAll();
    }

    @GetMapping("/filterd")
    public Collection<Faculty> getAllByColor(@RequestParam("color") String color) {
        return service.getAllByColor(color);
    }

    @GetMapping("/by-color-or-name")
    public Collection<Faculty> filteredByColorOrName(@RequestParam String colorOrName) {
        return service.getAllByNameOrColor(colorOrName, colorOrName);
    }

    @GetMapping("/by-student")
    public Faculty getByStudent(Long studentId) {
        return service.getByStudentId(studentId);

    }
}
