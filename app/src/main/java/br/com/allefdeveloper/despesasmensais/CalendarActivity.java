package br.com.allefdeveloper.despesasmensais;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

public class CalendarActivity extends AppCompatActivity {

    private MaterialCalendarView cal;
    Boolean sim = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        cal = (MaterialCalendarView) findViewById(R.id.calendarView);
        cal.setTopbarVisible(true);
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(CalendarActivity.this, "Data Atual" +cal.getCurrentDate(), Toast.LENGTH_LONG).show();
            }
        });
        cal.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Toast.makeText(CalendarActivity.this, "Data Atual" +date, Toast.LENGTH_LONG).show();

            }
        });
    }
}
