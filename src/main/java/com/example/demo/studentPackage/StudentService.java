package com.example.demo.studentPackage;

import com.example.demo.eventPerformancedb.StudentEventAttended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

//    get request
    public List<StudentModel> getAllStudentsData (){

       return studentRepository.findAll();
    }

//    get by student number
    public StudentModel getByStudentNumber(String studentNumber){

        return  studentRepository.getByStudentNumber(studentNumber);
    }
//    put request
    public StudentModel createStudentData(StudentModel studentModel){

        studentModel.setStudentPassword(encoder.encode(studentModel.getStudentPassword()));
        return  studentRepository.save(studentModel);

    }

//    add only student event attended
//    public void addStudentEventAttended(String id, StudentEventAttended studentEventAttended){
//
//        StudentModel studentModel = studentRepository.findById(id).orElse(null);
//
//
//            studentModel.getStudentEventAttendents().add(studentEventAttended);
//            studentRepository.save(studentModel);
//
//    }

    public void addEventToStudent(String studentId, List<StudentEventAttended> newEvents) {
        if (newEvents == null || newEvents.isEmpty()) {
            return;
        }

        Query query = new Query(Criteria.where("id").is(studentId));

        Update update = new Update()
                .push("studentEventAttendents")
                .each(newEvents.toArray());

        mongoTemplate.updateFirst(query, update, StudentModel.class);
    }

    public void addEvaluationToStudent(String studentId, List<StudentRecentEvaluation> newEvents) {
        if (newEvents == null || newEvents.isEmpty()) {
            return;
        }

        Query query = new Query(Criteria.where("id").is(studentId));

        Update update = new Update()
                .push("studentRecentEvaluations")
                .each(newEvents.toArray());

        mongoTemplate.updateFirst(query, update, StudentModel.class);
    }

//    delete by student id

    public void deleteStudent(String id){

      studentRepository.deleteById(id);
    }

    public String verify(StudentModel studentModel) {

        Authentication authentication = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(studentModel.getStudentNumber(), studentModel.getStudentPassword()));

        if (authentication.isAuthenticated())
            return jwtService.generateToken(studentModel.getStudentNumber()) ;


        return "fail";


    }
}
