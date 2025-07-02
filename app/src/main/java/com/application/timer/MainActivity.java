package com.application.timer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView countdownText;
    Handler handler = new Handler();
    Runnable runnable;
    Date targetDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        countdownText = findViewById(R.id.countdownText);

        targetDate = getDefaultTrilogyDate();

        // Start countdown updater
        runnable = new Runnable() {
            @Override
            public void run() {
                updateCountdown();
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(runnable);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

            /*
                private void openDatePicker() {
                    final Calendar calendar = Calendar.getInstance();
                    DatePickerDialog datePickerDialog = new DatePickerDialog(
                            this,
                            (view, year, month, dayOfMonth) -> {
                                calendar.set(year, month, dayOfMonth, 9, 0, 0);
                                targetDate = calendar.getTime();
                            },
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                     datePickerDialog.show();
    }
*/

    @SuppressLint("SetTextI18n")
    private void updateCountdown() {
        long currentTime = System.currentTimeMillis();
        long targetTime = targetDate.getTime();
        long difference = targetTime - currentTime;

        if (difference > 0) {
            long days = difference / (1000 * 60 * 60 * 24);
            long hours = (difference / (1000 * 60 * 60)) % 24;
            long minutes = (difference / (1000 * 60)) % 60;
            long seconds = (difference / 1000) % 60;

            String countdown = String.format("%d Days\n%02d Hr : %02d Min : %02d Sec", days, hours, minutes, seconds);


            countdownText.setText(countdown);
        } else {
            countdownText.setText("Let's Go! 🚀");
        }
    }

    @NonNull
    private Date getDefaultTrilogyDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(2026, Calendar.MAY, 11, 9, 0, 0); // May 11, 2026
        return cal.getTime();


    }
}