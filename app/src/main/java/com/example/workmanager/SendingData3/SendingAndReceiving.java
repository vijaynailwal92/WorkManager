package com.example.workmanager.SendingData3;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.example.workmanager.R;


public class SendingAndReceiving extends AppCompatActivity {

    Button buttonEnqueue;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending_and_receiving);

        textView = findViewById(R.id.textViewStatus);
        buttonEnqueue = findViewById(R.id.buttonEnqueue);

        Data data = new Data.Builder()
                .putString(ReceiveWorker.TASK_DESC, "The task data passed from MainActivity")
                .build();


        final OneTimeWorkRequest workRequest =
                new OneTimeWorkRequest.Builder(ReceiveWorker.class)
                        .setInputData(data)
                        .build();


        buttonEnqueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkManager.getInstance().enqueue(workRequest);
            }
        });


        WorkManager.getInstance().getWorkInfoByIdLiveData(workRequest.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(@Nullable WorkInfo workInfo) {
                        textView.append(workInfo.getState().name() + "\n");
                    }
                });



    }
}
