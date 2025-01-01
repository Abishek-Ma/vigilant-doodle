package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddTaskActivity extends AppCompatActivity {

    private EditText editTitle, editDescription;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        editTitle = findViewById(R.id.editTitle);
        editDescription = findViewById(R.id.editDescription);
        btnSave = findViewById(R.id.btnSaveTask);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editTitle.getText().toString();
                String description = editDescription.getText().toString();

                DatabaseHelper dbHelper = new DatabaseHelper(AddTaskActivity.this);
                dbHelper.addTask(title, description);

                setResult(RESULT_OK, new Intent());
                finish();
            }
        });
    }
}