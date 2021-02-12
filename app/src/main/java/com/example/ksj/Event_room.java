package com.example.ksj;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Event_room {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int image;
    private String event_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public Event_room(int image, String event_name) {
        this.image = image;
        this.event_name = event_name;
    }

    @NonNull
    @Override
    public String toString() {
        return "{room" + event_name+'\''+"}";
    }
}
