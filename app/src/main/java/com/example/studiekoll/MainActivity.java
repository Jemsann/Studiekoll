package com.example.studiekoll;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageButton saveButton;

    ArrayList<CourseClass> courseClassList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();
        buildRecyclerView();
        saveButton=findViewById(R.id.image_button_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

    }
    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(courseClassList);
        editor.putString("ARRAY_LIST",json);
        editor.apply();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("ARRAY_LIST",null);
        Type type = new TypeToken<ArrayList<CourseClass>>(){}.getType();
        courseClassList=gson.fromJson(json,type);
        if(courseClassList==null){
            courseClassList = new ArrayList<>();
        }
    }
    public void buildRecyclerView(){
        mRecyclerView=findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);
        mAdapter=new Adapter(courseClassList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new Adapter.OnItemClickListener() {

            @Override
            public void onAddClick(int position) {
                int prevHours = courseClassList.get(position).getHours();
                prevHours++;
                courseClassList.get(position).setHours(prevHours);
                mAdapter.notifyItemChanged(position);
            }

            @Override
            public void onRemoveClick(int position) {
                int prevHours = courseClassList.get(position).getHours();
                prevHours--;
                courseClassList.get(position).setHours(prevHours);
                mAdapter.notifyItemChanged(position);
            }
        });
    }
    public void changeTitleItem(int position,String text){
        courseClassList.get(position).changeTitle(text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.egen_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item1:
                Toast.makeText(this,"Lägg till kurs", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,AddCourse.class);
                startActivityForResult(intent,1);
                return true;
            case R.id.menu_item2:
                Toast.makeText(this,"Ta bort kurs", Toast.LENGTH_SHORT).show();
                return true;

            default:return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                Bundle bundle = data.getExtras();
                CourseClass newCourse = (CourseClass) bundle.getSerializable("NEW_COURSE");
                if(newCourse!=null){
                    courseClassList.add(newCourse);
                    mAdapter.notifyDataSetChanged();
                }
            }else{
                Toast.makeText(this,"Ingen kurs tillagd",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
