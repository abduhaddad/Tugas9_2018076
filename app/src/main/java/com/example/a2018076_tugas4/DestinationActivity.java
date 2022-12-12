package com.example.a2018076_tugas4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.view.View;

import com.example.a2018076_tugas4.databinding.ActivityDestinationBinding;

public class DestinationActivity extends AppCompatActivity {

    private ActivityDestinationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDestinationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final OneTimeWorkRequest request = new
                OneTimeWorkRequest.Builder(MyWorker.class).build();
        binding.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkManager.getInstance().enqueueUniqueWork(
                        "Notifikasi", ExistingWorkPolicy.REPLACE,
                        request);
            }
        });


    }
}