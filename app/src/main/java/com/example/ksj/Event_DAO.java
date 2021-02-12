package com.example.ksj;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface Event_DAO {
    @Query("SELECT * FROM Event_room")
    List<Event_room> getAll();

    @Insert
    void insert(Event_room er);

    @Delete
    void delete(Event_room er);

    @Update
    void update(Event_room er);

}
