package com.example.firebase.firebasecrud;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText name, roll;
    Button button;
    String stname, rollno;
    String key;
    ListView list;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference studentref = database.getReference("student");
    ArrayList<StudentDetail> studentArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.editText);
        roll = (EditText) findViewById(R.id.editText2);
        button = (Button) findViewById(R.id.button);
        list = (ListView) findViewById(R.id.list);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adddetail();
            }
        });
        StudentAdapter studentAdapter = new StudentAdapter(MainActivity.this, studentArrayList);
        //attaching adapter to the listview
        list.setAdapter(studentAdapter);
    }


    public void adddetail() {
        stname = name.getText().toString();
        rollno = roll.getText().toString();
        key = studentref.push().getKey();

        StudentDetail st = new StudentDetail(stname, rollno, key);

        studentref.child(key).setValue(st).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "data added", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "data not added", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        studentref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                studentArrayList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    StudentDetail student = postSnapshot.getValue(StudentDetail.class);
                    studentArrayList.add(student);
                }
                StudentAdapter studentsAdapter = new StudentAdapter(MainActivity.this, studentArrayList);
                list.setAdapter(studentsAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
