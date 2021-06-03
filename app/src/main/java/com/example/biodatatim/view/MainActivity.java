package com.example.biodatatim.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.biodatatim.R;
import com.example.biodatatim.entity.AppDatabase;
import com.example.biodatatim.entity.DataTeam;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContact.view{
    private AppDatabase appDatabase;
    private MainPresenter mainPresenter;
    private MainAdapter mainAdapter;

    private Button btnOk;
    private RecyclerView recyclerView;
    private EditText etName,etDivision,etTeam;
    private int id = 0;
    boolean edit =  false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOk = findViewById(R.id.btn_submit);
        etName = findViewById(R.id.et_name);
        etDivision= findViewById(R.id.et_division);
        etTeam = findViewById(R.id.et_team);
        recyclerView =  findViewById(R.id.rc_main);

        appDatabase = AppDatabase.inidb(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mainPresenter = new MainPresenter(this);
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void successAdd() {
        Toast.makeText(this,"Add Succes",Toast.LENGTH_LONG).show();
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void successDelete() {
        Toast.makeText(this,"Delete Succes",Toast.LENGTH_LONG).show();
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void resetForm() {
        etName.setText("");
        etDivision.setText("");
        etTeam.setText("");
        btnOk.setText("Insert");
    }

    @Override
    public void getData(List<DataTeam> list) {
        mainAdapter =  new MainAdapter(this,list,this);
        recyclerView.setAdapter(mainAdapter);
    }

    @Override
    public void editData(DataTeam item) {
        etName.setText(item.getName());
        etDivision.setText(item.getDivision());
        etTeam.setText(item.getTeam());
        id = item.getId();
        edit = true;
        etName.setText(item.getName());
        btnOk.setText("Edit Now");
    }

    @Override
    public void deleteData(DataTeam item) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT>-Build.VERSION_CODES.LOLLIPOP){
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        }else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Delete Data").setMessage("Are you sure to delete this data?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resetForm();
                        mainPresenter.deleteData(item,appDatabase);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface .cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_dialer).show();
    }

    @Override
    public void onClick(View view) {
        if (view==btnOk){
            if (etName.getText().toString().equals("")||etDivision.getText().toString().equals("")||
                    etTeam .getText().toString().equals("")){
                Toast.makeText(this,"Please fill all the data required",Toast.LENGTH_SHORT).show();
            } else{
                if (!edit){
                    mainPresenter.insertData(etName.getText().toString(),etDivision.getText().toString(),
                            etTeam .getText().toString().toString(),appDatabase);
                }else{
                    mainPresenter.editData(etName.getText().toString(),etDivision.getText().toString(),
                            etTeam .getText().toString().toString(),id,appDatabase);
                    edit = false;
                }
                resetForm();
            }
        }
    }
}