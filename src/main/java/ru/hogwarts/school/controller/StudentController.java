package ru.hogwarts.school.controller;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.StudentDtoIn;
import ru.hogwarts.school.dto.StudentDtoOut;
import ru.hogwarts.school.service.StudentService;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/{id}")
    public StudentDtoOut get(@PathVariable("id") long id) {
        return studentService.get(id);
    }

    @GetMapping
    public List<StudentDtoOut> findAll(@RequestParam(required = false) Integer age) {
        return studentService.findAll(age);
    }

    @PostMapping
    public StudentDtoOut create(@RequestBody StudentDtoIn studentDtoIn) {
        return studentService.create(studentDtoIn);
    }

    @PutMapping("/{id}")
    public StudentDtoOut update(@PathVariable("id") long id, @RequestBody StudentDtoIn studentDtoIn) {
        return studentService.update(id, studentDtoIn);
    }

    @DeleteMapping("/{id}")
    public StudentDtoOut delete(@PathVariable("id") long id) {
        return studentService.delete(id);
    }
    @GetMapping("/filter")
    public List<StudentDtoOut> findByAgeBetween(@RequestParam int ageFrom, @RequestParam int ageTo) {
        return studentService.findByAgeBetween(ageFrom, ageTo);
    }
}
