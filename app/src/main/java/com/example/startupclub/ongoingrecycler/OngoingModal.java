package com.example.startupclub.ongoingrecycler;

public class OngoingModal {

    String title,description,URL,date,venue,time;

    public OngoingModal(String title, String description, String URL,String date,String venue,String time) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.URL = URL;
        this.venue = venue;
        this.time = time;
    }

    public OngoingModal() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
