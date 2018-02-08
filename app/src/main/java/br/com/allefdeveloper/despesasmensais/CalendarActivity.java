package br.com.allefdeveloper.despesasmensais;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import butterknife.BindColor;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;

public class CalendarActivity extends AppCompatActivity {
    @BindColor(R.color.colorAccent)
    int arrow;

    private MaterialCalendarView cal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        ButterKnife.bind(this);
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
                widget.setArrowColor(arrow);
                widget.setSelectionColor(arrow);

            }
        });
    }
}
