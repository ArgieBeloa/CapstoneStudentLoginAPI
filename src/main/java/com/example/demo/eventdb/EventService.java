package com.example.demo.eventdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    public List<EventModel> getAllEvents(){

         return eventRepository.findAll();
     }

     public EventModel createEvent(EventModel eventModel){
         return eventRepository.save(eventModel);

     }


     public void addEventEvaluationDetails(String id, List<EventEvaluationDetails> newEventEvaluationDetails){


//             if (newEventEvaluationDetails == null ||newEventEvaluationDetails.isEmpty()) {
//                 return;
//             }

             Query query = new Query(Criteria.where("id").is(id));

             Update update = new Update()
                     .push("EventEvaluationDetails")
                     .each(newEventEvaluationDetails.toArray());

             mongoTemplate.updateFirst(query, update, EventModel.class);
    }

    public void addPerformanceDetails(String id, List<EventPerformanceDetails> newEventPerformanceDetails){


//        if (newEventPerformanceDetails == null ||newEventPerformanceDetails.isEmpty()) {
//            return;
//        }

        Query query = new Query(Criteria.where("id").is(id));

        Update update = new Update()
                .push("eventPerformanceDetails")
                .each(newEventPerformanceDetails.toArray());

        mongoTemplate.updateFirst(query, update, EventModel.class);
    }
    //    get by category
    public List<EventModel> getEventByCategory(String category){

        return eventRepository.getByEventCategory(category);
    }
    public void deleteEvent(String id){

        eventRepository.deleteById(id);
    }





}
