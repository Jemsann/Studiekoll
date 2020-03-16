package com.example.studiekoll;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddCourse extends AppCompatActivity {
    private Button saveButton;
    private Button regretButton;
    private EditText courseTitle;
    private EditText courseHours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Lägg till kurs");
        saveButton=findViewById(R.id.button_Save);
        regretButton=findViewById(R.id.button_Regret);
        courseTitle=findViewById(R.id.editText_Title);
        courseHours=findViewById(R.id.editText_Hours);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title;
                Integer hours;
                if(courseTitle.getText().toString().equals("")){
                    Toast.makeText(AddCourse.this,"Ange kursnamn",Toast.LENGTH_SHORT).show();
                }else{
                    if(courseHours.getText().toString().equals("")){
                        hours=0;
                    }else{
                        hours=Integer.parseInt(courseHours.getText().toString());
                    }
                    title=courseTitle.getText().toString();
                    Bundle bundle = new Bundle();

                    CourseClass newCourse = new CourseClass(R.drawable.ic_android_black_24dp,title,hours,"");
                    Intent resultIntent = new Intent();
                    bundle.putSerializable("NEW_COURSE",newCourse);

                    resultIntent.putExtras(bundle);
                    
                    setResult(RESULT_OK,resultIntent);
                    finish();
                }
            }
        });

        regretButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                setResult(RESULT_CANCELED,resultIntent);
                finish();
            }
        });

    }

}
