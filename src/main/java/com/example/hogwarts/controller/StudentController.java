package com.example.hogwarts.controller;

import com.example.hogwarts.model.Student;
import com.example.hogwarts.service.AvatarService;
import com.example.hogwarts.service.StudentService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService service;
    private final AvatarService avatarService;

    public StudentController(StudentService service, AvatarService avatarService) {
        this.service = service;
        this.avatarService = avatarService;
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return service.create(student);
    }

    @PutMapping
    public Student update(@PathVariable("id") Long id, @RequestBody Student student) {
        return service.update(id, student);
    }

    @DeleteMapping("/{id}")
    public Student delete(@PathVariable("id") Long id) {
        return service.remove(id);
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @GetMapping
    public Collection<Student> getAll() {
        return service.getAll();
    }

    @GetMapping("/filtered")
    public Collection<Student> getAllByAge(@RequestParam("age") int age) {
        return service.getAllByAge(age);
    }

    @GetMapping("/age-between")
    public Collection<Student> ageBetween(@RequestParam int min, @RequestParam int max) {
        return service.getByAge(min, max);
    }

    @PostMapping(value = "/{studentId}/avatart", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> save(@PathVariable Long studentId, @RequestBody MultipartFile multipartFile, byte[] data) {
        try {
            return ResponseEntity.ok(avatarService.save(studentId, multipartFile, data));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
