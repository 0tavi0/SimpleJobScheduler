package com.otavio.jobsheduler_1;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import me.tatarka.support.job.JobParameters;
import me.tatarka.support.job.JobScheduler;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class JobService extends me.tatarka.support.job.JobService{
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.e("Job successfully","job"+params.getJobId());

        Toast.makeText(this, "Job successfully", Toast.LENGTH_SHORT).show();

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
