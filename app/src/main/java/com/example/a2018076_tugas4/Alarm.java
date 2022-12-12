package com.example.a2018076_tugas4;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a2018076_tugas4.databinding.FragmentAlarmBinding;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

public class Alarm extends Fragment {

    private FragmentAlarmBinding binding;
    private MaterialTimePicker picker;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void cancelAlarm() {
        //untuk menggagalkan alarm yang sudah disetel
        Intent intent = new Intent(getActivity(), AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getActivity(), 0,
                intent, 0);
        if (alarmManager == null) {
            alarmManager = (AlarmManager)
                    getActivity().getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(pendingIntent);
        Toast.makeText(getActivity(), "Alarm Cancelled",
                Toast.LENGTH_SHORT).show();
    }

    private void setAlarm() {
        //untuk menjalankan alarm yang sudah disetel
        alarmManager = (AlarmManager)
                getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getActivity(), AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getActivity(), 0,
                intent, 0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);
        Toast.makeText(getActivity(), "Alarm Set Successfully",
                Toast.LENGTH_SHORT).show();
    }

    private void showTimePicker() {
        //memunculkan dialog timepicker menggunakan library dariandroid
        picker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Alarm Time")
                .build();
        picker.show(getActivity().getSupportFragmentManager(), "AlarmManager");
        //mengeset waktu didalam view
        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (picker.getHour() > 12) {
                    binding.selectedTime.setText(
                            String.format("%02d : %02d",
                                    picker.getHour(), picker.getMinute())
                    );
                } else {
                    binding.selectedTime.setText(picker.getHour()
                            + " : " + picker.getMinute() + " ");
                }
                //menangkap inputan jam kalian lalu memulai alarm
                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,
                        picker.getHour());
                calendar.set(Calendar.MINUTE,
                        picker.getMinute());
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        //mendeskripsikan channel notifikasi yang akan dibangun
        CharSequence name = "INI ALARM MANAGER";
        String description = "PRAKTIKUM BAB6 TENTANG ALARM MANAGER ";
        //tingkat importance = high ( penting sekali )
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel channel = new NotificationChannel("AlarmManager", name, importance);
        channel.setDescription(description);
        //membuka izin pengaturan dari aplikasi untuk memulai service notifikasi
        NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding =
                FragmentAlarmBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        createNotificationChannel();
        binding.selectedTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker();
            }
        });
        binding.setAlarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAlarm();
            }
        });
        binding.cancelAlarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelAlarm();
            }
        });
        return v;
    }
}