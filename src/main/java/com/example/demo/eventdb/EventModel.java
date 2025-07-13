package com.example.demo.eventdb;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "eventData")
public class EventModel {

    @Id
    private String id;
    private String eventTitle;
    private String eventShortDescription;
    private String eventBody;
    private int allStudentRegister;
    private int allStudentInteresting;
    private double eventAveragePerformance;
    private String eventDate;
    private String eventTime;
    private String eventLocation;
    private String eventCategory;

    private List<EventEvaluationDetails> eventEvaluationDetails;
    private List<EventPerformanceDetails> eventPerformanceDetails;

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    /*


    {
         "eventTitle": "test 2",
      "eventShortDescription": "event short description",
       "eventBody": "This is body",
         "eventAveragePerformance": 0,
         "eventDate": "2025-07-07",
        "eventLocation": "DABBA",
        "eventCategory":"Technology"
    }
     */
    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventShortDescription() {
        return eventShortDescription;
    }

    public void setEventShortDescription(String eventShortDescription) {
        this.eventShortDescription = eventShortDescription;
    }

    public String getEventBody() {
        return eventBody;
    }

    public void setEventBody(String eventBody) {
        this.eventBody = eventBody;
    }

    public int getAllStudentRegister() {
        return allStudentRegister;
    }

    public void setAllStudentRegister(int allStudentRegister) {
        this.allStudentRegister = allStudentRegister;
    }

    public int getAllStudentInteresting() {
        return allStudentInteresting;
    }

    public void setAllStudentInteresting(int allStudentInteresting) {
        this.allStudentInteresting = allStudentInteresting;
    }

    public double getEventAveragePerformance() {
        return eventAveragePerformance;
    }

    public void setEventAveragePerformance(double eventAveragePerformance) {
        this.eventAveragePerformance = eventAveragePerformance;
    }

    public List<EventEvaluationDetails> getEventEvaluationDetails() {
        return eventEvaluationDetails;
    }

    public void setEventEvaluationDetails(List<EventEvaluationDetails> eventEvaluationDetails) {
        this.eventEvaluationDetails = eventEvaluationDetails;
    }

    public List<EventPerformanceDetails> getEventPerformanceDetails() {
        return eventPerformanceDetails;
    }

    public void setEventPerformanceDetails(List<EventPerformanceDetails> eventPerformanceDetails) {
        this.eventPerformanceDetails = eventPerformanceDetails;
    }
}
