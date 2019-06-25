package com.example.workmanager.OneTimeRequest1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.workmanager.R;


public class OneTimeWorkRequestActivity extends AppCompatActivity {
    Button buttonEnqueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonEnqueue = findViewById(R.id.buttonEnqueue);

//        WorkRequest subclass is
//        i)PeriodicWorkRequest
//        ii)OneTimeWorkRequest

        final OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(MyWorker.class).build();
        buttonEnqueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkManager.getInstance().enqueue(oneTimeWorkRequest);
            }
        });


    }
}
