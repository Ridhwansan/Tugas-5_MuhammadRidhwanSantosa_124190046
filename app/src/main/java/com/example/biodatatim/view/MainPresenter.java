package com.example.biodatatim.view;

import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Delete;

import com.example.biodatatim.entity.AppDatabase;
import com.example.biodatatim.entity.DataTeam;

import java.util.List;

public class MainPresenter implements MainContact.presenter {
    private MainContact.view view;

    public MainPresenter(MainContact.view view) {
        this.view = view;
    }

    class InsertData extends AsyncTask<Void,Void,Long>{
    private AppDatabase appDatabase;
    private DataTeam dataTeam;

        public InsertData(AppDatabase appDatabase, DataTeam dataTeam) {
            this.appDatabase = appDatabase;
            this.dataTeam = dataTeam;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return appDatabase.dao().insertData(dataTeam);
        }
        protected void onPostExecute(Long along){
            super.onPostExecute(along);
            view.successAdd();
        }
    }

    @Override
    public void insertData(String name, String division, String team, AppDatabase database) {
        final DataTeam dataTeam = new DataTeam();
        dataTeam.setName(name);
        dataTeam.setDivision(division);
        dataTeam.setTeam(team);
        new InsertData(database,dataTeam).execute();
    }

    @Override
    public void readData(AppDatabase database) {
        List<DataTeam> list;
        list = database.dao().getData();
        view.getData(list);

    }
    class EditData extends AsyncTask<Void, Void, Integer> {
        private AppDatabase appDatabase;
        private DataTeam dataTeam;

        public EditData(AppDatabase appDatabase, DataTeam dataTeam) {
            this.appDatabase = appDatabase;
            this.dataTeam = dataTeam;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return appDatabase.dao().updateData(dataTeam);
        }
        protected void onPostExecute(Integer integer){
            super.onPostExecute(integer);
            Log.d("integer db","onPostExecute : "+integer);
            view.successAdd();
        }
    }

    @Override
    public void editData(String name, String division, String team, int id, AppDatabase database) {
        final DataTeam dataTeam = new DataTeam();
        dataTeam.setName(name);
        dataTeam.setDivision(division);
        dataTeam.setTeam(team);
        dataTeam.setId(id);
        new EditData(database,dataTeam).execute();
    }


    class DeleteData extends AsyncTask<Void,Void,Long>{
        private AppDatabase appDatabase;
        private DataTeam dataTeam;

        public DeleteData(AppDatabase appDatabase, DataTeam dataTeam) {
            this.appDatabase = appDatabase;
            this.dataTeam = dataTeam;
        }


        @Override
        protected Long doInBackground(Void... voids) {
            appDatabase.dao().deleteData(dataTeam);
            return null;
        }
        protected void onPostExecute(Long along){
            super.onPostExecute(along);
            view.successDelete();
        }
    }

    @Override
    public void deleteData(DataTeam dataTeam, AppDatabase database) {
        new DeleteData(database,dataTeam).execute();
    }
}
