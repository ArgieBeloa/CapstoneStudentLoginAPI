package com.example.demo.studentPackage;

import com.example.demo.eventPerformancedb.StudentEventAttended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/students")
@CrossOrigin
public class StudentController {


    @Autowired
    private StudentService studentService;

//    get all student Data
    @GetMapping("/allStudentData")
    public ResponseEntity<List <StudentModel> >getAllStudentData(){
        List<StudentModel> studentModels = studentService.getAllStudentsData();

        return new ResponseEntity<>(studentModels, HttpStatus.OK);

    }

//    get specific student by student number
    @GetMapping("/{studentNumber}")
    public StudentModel getStudentById(@PathVariable String studentNumber){

        return studentService.getByStudentNumber(studentNumber);
    }

    @PostMapping("/login")
    public String studentLogin(@RequestBody StudentModel studentModel){

        return studentService.verify(studentModel);
    }

//  add student data
    @PostMapping("/register")
    public StudentModel createStudentData(@RequestBody StudentModel studentModel){

        return studentService.createStudentData(studentModel);
    }

//    update student event attended

@PostMapping("/{id}/events")
public ResponseEntity<?> addStudentEvents(@PathVariable String id,
                                          @RequestBody List<StudentEventAttended> events) {
    studentService.addEventToStudent(id, events);
    return ResponseEntity.ok("Events added successfully");
}

    @PostMapping("/{id}/evaluation")
    public ResponseEntity<?> addStudentEvaluation(@PathVariable String id,
                                              @RequestBody List<StudentRecentEvaluation> evaluations) {
        studentService.addEvaluationToStudent(id, evaluations);
        return ResponseEntity.ok("Evaluation added successfully");
    }

//    delete by id
    @DeleteMapping("/{id}")
    public  void deleteById(@PathVariable String id){

        studentService.deleteStudent(id);

    }

















}
