package com.example.demo.eventdb;

public class EventEvaluationDetails {

      private String evaluationQuestion;
      private double studentRate;
      private String studentSuggestion;



    public String getEvaluationQuestion() {
        return evaluationQuestion;
    }

    public void setEvaluationQuestion(String evaluationQuestion) {
        this.evaluationQuestion = evaluationQuestion;
    }

    public double getStudentRate() {
        return studentRate;
    }

    public void setStudentRate(double studentRate) {
        this.studentRate = studentRate;
    }

    public String getStudentSuggestion() {
        return studentSuggestion;
    }

    public void setStudentSuggestion(String studentSuggestion) {
        this.studentSuggestion = studentSuggestion;
    }
}
