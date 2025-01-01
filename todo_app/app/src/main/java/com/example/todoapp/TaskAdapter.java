package com.example.todoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> taskList;
    private OnTaskClickListener onTaskClickListener;

    public interface OnTaskClickListener {
        void onTaskClick(int position);
    }

    public TaskAdapter(List<Task> taskList, OnTaskClickListener onTaskClickListener) {
        this.taskList = taskList;
        this.onTaskClickListener = onTaskClickListener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view, onTaskClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task currentTask = taskList.get(position);
        holder.titleTextView.setText(currentTask.getTitle());
        holder.descriptionTextView.setText(currentTask.getDescription());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleTextView, descriptionTextView;
        OnTaskClickListener onTaskClickListener;

        TaskViewHolder(@NonNull View itemView, OnTaskClickListener onTaskClickListener) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.textTitle);
            descriptionTextView = itemView.findViewById(R.id.textDescription);
            this.onTaskClickListener = onTaskClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onTaskClickListener.onTaskClick(getAdapterPosition());
        }
    }
}