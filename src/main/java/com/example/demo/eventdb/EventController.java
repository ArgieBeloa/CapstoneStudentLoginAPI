package com.example.demo.eventdb;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
@CrossOrigin
public class EventController {

    @Autowired
    private EventService eventService;


//  get all
    @GetMapping("/getAll")
    public ResponseEntity<List<EventModel>> getAllEvents(){

          List<EventModel> events = eventService.getAllEvents();

          return new ResponseEntity<>(events,HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<EventModel>> getEventByCategory(@PathVariable String category){

          List<EventModel> event = eventService.getEventByCategory(category);

        return new ResponseEntity<>(event,  HttpStatus.OK);
    }

//    add data
    @PostMapping("/add")
    public ResponseEntity<EventModel> createEvent(@RequestBody EventModel newEventModel){

        EventModel newEvent;
        newEvent = eventService.createEvent(newEventModel);

        return new ResponseEntity<> (newEvent, HttpStatus.CREATED);
    }


//    add event details
@PostMapping("/{id}/eventEvaluationDetails")
public ResponseEntity<?> addEvents(@PathVariable String id,
                                          @RequestBody List<EventEvaluationDetails> newEvaluationDetails) {
    eventService.addEventEvaluationDetails(id,newEvaluationDetails);
    return ResponseEntity.ok("Event Evaluation details added successfully");
}

//    add evaluation details

    //    add event details
    @PostMapping("/{id}/eventPerformanceDetails")
    public ResponseEntity<?> addEventPerformance(@PathVariable String id,
                                       @RequestBody List<EventPerformanceDetails> newPerformanceDetails) {
        eventService.addPerformanceDetails(id,newPerformanceDetails);
        return ResponseEntity.ok("Event Performance details added successfully");
    }





//    delete by id
@DeleteMapping("/{id}")
public  void deleteById(@PathVariable String id){

    eventService.deleteEvent(id);

}
}
