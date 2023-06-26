package ru.hogwarts.school.service;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import ru.hogwarts.school.dto.StudentDtoIn;
import ru.hogwarts.school.dto.StudentDtoOut;

import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.exception.StudentNotFoundException;

import ru.hogwarts.school.mapper.StudentMapper;

import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentDtoOut create(StudentDtoIn studentDtoIn) {
        return studentMapper.toDto(
                studentRepository.save(
                        studentMapper.toEntity(studentDtoIn)
                )
        );
    }

    public StudentDtoOut update(long id, StudentDtoIn studentDtoIn) {
        return studentRepository.findById(id)
                .map(oldStudent -> {
                    oldStudent.setAge(studentDtoIn.getAge());
                    oldStudent.setName(studentDtoIn.getName());
                    return studentMapper.toDto(studentRepository.save(oldStudent));
                })
                .orElseThrow(() -> new StudentNotFoundException(id));
    }


        public StudentDtoOut get (long id){
            return studentRepository.findById(id)
                    .map(studentMapper::toDto)
                    .orElseThrow(() -> new StudentNotFoundException(id));
        }

        public StudentDtoOut delete (long id) {
            Student student = studentRepository.findById(id)
                    .orElseThrow(() -> new StudentNotFoundException(id));
            studentRepository.delete(student);
            return studentMapper.toDto(student);
        }

        public List<StudentDtoOut> findAll (@Nullable Integer age){
            return Optional.ofNullable(age)
                    .map(studentRepository::findAllByAge)
                    .orElseGet(studentRepository::findAll).stream()
                    .map(studentMapper::toDto)
                    .collect(Collectors.toList());
        }
}
