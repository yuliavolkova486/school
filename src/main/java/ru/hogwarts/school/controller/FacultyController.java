package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.FacultyDtoIn;
import ru.hogwarts.school.dto.FacultyDtoOut;
import ru.hogwarts.school.service.FacultyService;
import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }
    @PostMapping
    public FacultyDtoOut create(@RequestBody FacultyDtoIn facultyDtoIn) {
        return facultyService.create(facultyDtoIn);
    }
    @PutMapping("/{id}")
    public FacultyDtoOut update(@PathVariable("id") long id, @RequestBody FacultyDtoIn facultyDtoIn ) {
        return facultyService.update(id, facultyDtoIn);
    }


    @GetMapping("/{id}")
    public FacultyDtoOut get(@PathVariable("id") long id) {return facultyService.get(id); }

    @DeleteMapping("/{id}")
    public FacultyDtoOut delete(@PathVariable("id") long id) {
        return facultyService.delete(id);
    }


    @GetMapping
    List<FacultyDtoOut> findAll (@RequestParam (required = false) String color) {
    return facultyService.findAll(color);
    }
}

