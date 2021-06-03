package com.example.biodatatim.view;

import android.view.View;

import com.example.biodatatim.entity.AppDatabase;
import com.example.biodatatim.entity.DataTeam;

import java.util.List;

public interface MainContact {
    //kodingan activity
    interface view extends View.OnClickListener{
        void successAdd();
        void successDelete();
        void resetForm();
        void getData(List<DataTeam> List);
        void editData(DataTeam item);
        void deleteData(DataTeam item);
    }
    //kodingan database
    interface presenter{
        void insertData(String name, String division, String team, AppDatabase database);
        void readData(AppDatabase database);
        void editData(String name, String division, String team, int id, AppDatabase database);
        void deleteData(DataTeam dataTeam, AppDatabase database);
    }
}
