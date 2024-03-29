package com.example.workmanager.ReceivingData4;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;

import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.workmanager.R;


public class RecevingWoker extends Worker {

    public static final String TASK_DESC = "task_desc";

    public RecevingWoker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super( context,workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        String taskDesc = getInputData().getString(TASK_DESC);

        displayNotification("My Worker", taskDesc);
        //setting output data
        Data data = new Data.Builder()
                .putString(TASK_DESC, "The conclusion of the task")
                .build();
        setOutputData(data);
        return Result.SUCCESS;
    }

    private void displayNotification(String title, String task) {
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("simplifiedcoding", "simplifiedcoding", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), "simplifiedcoding")
                .setContentTitle(title)
                .setContentText(task)
                .setSmallIcon(R.mipmap.ic_launcher);

        notificationManager.notify(1, notification.build());
    }

}
