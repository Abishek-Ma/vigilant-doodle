package com.example.todoapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnTaskClickListener {

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private DatabaseHelper databaseHelper;
    private Button btnAdd;
    private static final int REQUEST_CODE_ADD_TASK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        recyclerView = findViewById(R.id.recyclerViewTasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnAdd = findViewById(R.id.btnAddTask);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_TASK);
            }
        });

        loadTasks();
    }

    private void loadTasks() {
        List<Task> tasks = databaseHelper.getAllTasks();
        taskAdapter = new TaskAdapter(tasks, this);
        recyclerView.setAdapter(taskAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_TASK && resultCode == RESULT_OK) {
            loadTasks();
        }
    }

    @Override
    public void onTaskClick(int position) {
        Task selectedTask = databaseHelper.getAllTasks().get(position);
        databaseHelper.deleteTask(selectedTask.getId());
        loadTasks();
    }
}