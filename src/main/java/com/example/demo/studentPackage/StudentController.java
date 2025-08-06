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

    @PutMapping("/register")
    public ResponseEntity<StudentModel> registerStudent(@RequestBody StudentModel studentModel) {
        StudentModel savedStudent = studentService.createStudentData(studentModel);
        return ResponseEntity.ok(savedStudent);
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

    @PostMapping("/{id}/upcomingEvents")
    public ResponseEntity<?> addStudentUpcomingEvents(@PathVariable String id,
                                                  @RequestBody List<StudentUpcomingEvents> events) {
        studentService.addStudentUpcomingEvents(id, events);
        return ResponseEntity.ok("Upcoming Events added successfully");
    }

//    delete upcoming student event (specific)
    @DeleteMapping("/{studentNumber}/delete/upcomingEvents/{eventId}")
    public ResponseEntity<String> deleteSpecificUpcomingEvents(@PathVariable String studentNumber, @PathVariable String eventId){

        try {
            studentService.deleteSpecificUpcomingEvent(studentNumber, eventId);
            return  new ResponseEntity<>("Event remove successfully",HttpStatus.OK);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>("An Error occurred while removing the event", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

//    delete by id
    @DeleteMapping("/{id}")
    public  void deleteById(@PathVariable String id){

        studentService.deleteStudent(id);

    }

















}
