package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Student;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final HashMap<Long, Student> students = new HashMap<>();
    private long idGenerator = 1;

    public Student createStudent(Student student) {
        student.setId(idGenerator++);
        students.put(idGenerator, student);
        return student;
    }

    public Student updateStudent(long id, Student student) {
        if (students.containsKey(id)) {
            Student oldStudent = students.get(id);
            oldStudent.setName(student.getName());
            oldStudent.setAge(student.getAge());
            students.replace(id, oldStudent);
            return oldStudent;
        } else {
            throw new StudentNotFoundException(id);
        }
    }

    public Student getStudent(long id) {
        if (students.containsKey(id)) {
            return students.get(id);
        } else {
            throw new StudentNotFoundException(id);
        }
    }

    public Student deleteStudent(long id) {
        if (students.containsKey(id)) {
            return students.remove(id);
        } else {
            throw new StudentNotFoundException(id);
        }
    }

    public List<Student> findAllStudent(Integer age) {
        return Optional.ofNullable(age)
                .map(a ->
                        students.values().stream()
                                .filter(student -> student.getAge() == a)
                )
                .orElseGet(() -> students.values().stream())
                .collect(Collectors.toList());
    }
}
