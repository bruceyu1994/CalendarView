package com.bruceyu.calendarview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bruceyu.library.CalendarDay;
import com.bruceyu.library.DatePickerController;
import com.bruceyu.library.DayPickerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DayPickerView dpvCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dpvCalendar = (DayPickerView) findViewById(R.id.dpvCalendar);

        dpvCalendar.setParameter(new DatePickerController() {
            @Override
            public void onDayOfMonthSelected(CalendarDay calendarDay) {
                Toast.makeText(MainActivity.this, calendarDay.year + "-" +
                        (calendarDay.month+1) + "-" + calendarDay.day, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDateRangeSelected(List<CalendarDay> selectedDays) {
                int len = selectedDays.size();
                Toast.makeText(MainActivity.this,selectedDays.get(0).year + "-" +
                        (selectedDays.get(0).month+1) + "-" + selectedDays.get(0).day + "→" +
                        selectedDays.get(len-1).year + "-" + (selectedDays.get(len-1).month+1) + "-" +
                        selectedDays.get(len-1).day, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void alertSelectedFail(FailEven even) {

            }
        });

    }
}
