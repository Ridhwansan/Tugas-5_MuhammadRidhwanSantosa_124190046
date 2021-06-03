package com.example.biodatatim.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataTeamDAO {
    @Insert
    Long insertData(DataTeam dataTeam);

    @Query("Select * from team_db")
    List<DataTeam> getData();

    @Update
    int updateData(DataTeam item);

    @Delete
    void deleteData (DataTeam item);
}
