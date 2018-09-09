package com.otavio.jobsheduler_1;

import android.annotation.TargetApi;

import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.TimeUnit;

import me.tatarka.support.job.JobInfo;
import me.tatarka.support.job.JobScheduler;

public class MainActivity extends AppCompatActivity {

    int count;

    EditText edtSegundos;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAgendar = (Button) findViewById(R.id.button);
        Button btnCancelar = (Button) findViewById(R.id.button2);
        edtSegundos = (EditText) findViewById(R.id.edtSegundos);




        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                JobScheduler scheduler = JobScheduler.getInstance(getBaseContext());

                //cancels all JobIds
                scheduler.cancelAll();
                Log.e("Job cancel","job"+scheduler);

            }
        });


        btnAgendar.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                //convert for milliseconds
                String segundos = (edtSegundos.getText().toString());
                long segConvertido = TimeUnit.SECONDS.toMillis(Integer.parseInt(segundos));
                Log.e("SegundosConvertido",""+segConvertido);

                count++;
                Log.e("Count",""+count);

                JobScheduler scheduler = JobScheduler.getInstance(getBaseContext());
                ComponentName componentName = new ComponentName(getBaseContext(), JobService.class);

                JobInfo.Builder builder = new JobInfo.Builder(count, componentName);

                builder.setPersisted(true)  //even restarting the mobile phone the job continues
                        .setBackoffCriteria(5000, JobInfo.BACKOFF_POLICY_LINEAR)
                        .setPeriodic(segConvertido);

                if (scheduler != null) {
                    int result = scheduler.schedule(builder.build());
                    if (result == JobScheduler.RESULT_SUCCESS) {
                        Log.e("Job successfully","job"+scheduler);
                    } else {
                        Log.e("Job not successfully","job"+scheduler);
                    }
                }
            }
        });
    }
}
