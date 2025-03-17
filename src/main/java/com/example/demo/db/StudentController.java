package com.example.demo.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepository  studentRepository;


    // Create a new student login
    @PostMapping("/add")
    public StudentModel createStudent(@RequestBody StudentModel model) {
        return studentRepository.save(model);
    }

    @GetMapping("/getAll")
    public List<StudentModel> getAllStudent() {
        return studentRepository.findAll();
    }

//    get by username
@GetMapping("/get/{username}")
public Optional<StudentModel> getStudentByUsername(@PathVariable String username) {
    return studentRepository.getByUsername(username);
}

//    update by username

    // Update user by username
    @PutMapping("/update/{username}")
    public ResponseEntity<StudentModel> updateStudentByUsername(@PathVariable String username, @RequestBody StudentModel studentDetails) {
        Optional<StudentModel> studentOptional = studentRepository.getByUsername(username);

        if (studentOptional.isEmpty()) {
            return ResponseEntity.notFound().build(); // If user is not found
        }

      StudentModel existingStudent = studentOptional.get();

        // Update the existing user with new details

        existingStudent.setPassword(studentDetails.getPassword());
        // Save and return the updated user
        StudentModel updatedStudent = studentRepository.save(existingStudent);
        return ResponseEntity.ok(updatedStudent );
    }


    // Delete a student
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable String id) {
       studentRepository.deleteById(id);
    }

//    improve security add encryption and create cluster online


}
