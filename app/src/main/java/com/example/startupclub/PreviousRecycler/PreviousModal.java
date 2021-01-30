package com.example.startupclub.PreviousRecycler;

public class PreviousModal {
    String title,description,URL,date,venue,time;

    public PreviousModal() {
    }

    public PreviousModal(String title, String description, String URL, String date, String venue, String time) {
        this.title = title;
        this.description = description;
        this.URL = URL;
        this.date = date;
        this.venue = venue;
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
}
