package com.example.workmanager.PeriodicRequest2;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.example.workmanager.R;


public class PeriodicWorkRequestActivity extends AppCompatActivity {

    Button buttonEnqueue;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodic_work_request);

        buttonEnqueue = findViewById(R.id.buttonEnqueue);
        textView = findViewById(R.id.textViewStatus);

        Data data = new Data.Builder().putString("task_desc", "The task data passed from MainActivity").build();


        Log.e("data1", data.getString("task_desc"));

        final OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(PeriodicWork.class).setInputData(data).build();

        buttonEnqueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkManager.getInstance().enqueue(oneTimeWorkRequest);
            }
        });

        WorkManager.getInstance().getWorkInfoByIdLiveData(oneTimeWorkRequest.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(@Nullable WorkInfo workInfo) {
                        textView.append(workInfo.getState().name() + "\n");
                    }
                });



    }
}
