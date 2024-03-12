package com.example.drivingtest;

public class Question {
    private int id;
    private String question;
    private String ideaA;
    private String ideaB;
    private String ideaC;
    private String ideaD;
    private int answer;

    public Question(int id, String question, String ideaA, String ideaB, String ideaC, String ideaD, int answer) {
        this.id = id;
        this.question = question;
        this.ideaA = ideaA;
        this.ideaB = ideaB;
        this.ideaC = ideaC;
        this.ideaD = ideaD;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getIdeaA() {
        return ideaA;
    }

    public void setIdeaA(String ideaA) {
        this.ideaA = ideaA;
    }

    public String getIdeaB() {
        return ideaB;
    }

    public void setIdeaB(String ideaB) {
        this.ideaB = ideaB;
    }

    public String getIdeaC() {
        return ideaC;
    }

    public void setIdeaC(String ideaC) {
        this.ideaC = ideaC;
    }

    public String getIdeaD() {
        return ideaD;
    }

    public void setIdeaD(String ideaD) {
        this.ideaD = ideaD;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
