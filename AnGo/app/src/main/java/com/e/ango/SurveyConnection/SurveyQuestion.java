package com.e.ango.SurveyConnection;

public class SurveyQuestion {
    public String questionWeatherType;
    public String questionWeatherText;

    public SurveyQuestion() {
    }

    public SurveyQuestion(String questionWeatherType, String questionWeatherText) {
        this.questionWeatherType = questionWeatherType;
        this.questionWeatherText = questionWeatherText;
    }

    public String getQuestionWeatherType() {
        return questionWeatherType;
    }

    public void setQuestionWeatherType(String questionWeatherType) {
        this.questionWeatherType = questionWeatherType;
    }

    public String getQuestionWeatherText() {
        return questionWeatherText;
    }

    public void setQuestionWeatherText(String questionWeatherText) {
        this.questionWeatherText = questionWeatherText;
    }
}
