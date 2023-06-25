package ru.hogwarts.school.controller;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
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
    public Student get(@PathVariable("id") long id) {
        return studentService.getStudent(id);
    }

    @GetMapping
    public List<Student> findAll(@RequestParam(required = false) Integer age) {
        return studentService.findAllStudent(age);
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable("id") long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public Student delete(@PathVariable("id") long id) {
        return studentService.deleteStudent(id);
    }
}
