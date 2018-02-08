package br.com.allefdeveloper.despesasmensais;

import android.content.Intent;
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

import br.com.allefdeveloper.despesasmensais.Model.GastosDiarios;
import butterknife.BindColor;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;

public class CalendarActivity extends AppCompatActivity {
    @BindColor(R.color.colorAccent)
    int arrow;
    private GastosDiarios gastos;

    private MaterialCalendarView cal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        ButterKnife.bind(this);
        setContentView(R.layout.activity_calendar);
        cal = (MaterialCalendarView) findViewById(R.id.calendarView);
        cal.setTopbarVisible(true);
        gastos = new GastosDiarios();

        cal.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Toast.makeText(CalendarActivity.this, "Data Atual mes" +date.getMonth()+1 +" dia"+ date.getDay() +"anoo"+ date.getYear(), Toast.LENGTH_LONG).show();
                widget.setArrowColor(arrow);
//                widget.setSelectionColor(arrow);
                int mescorreto = date.getMonth();
                mescorreto =mescorreto +1;
                int[] vetdata = new int[3];
                vetdata[0] = date.getDay();
                vetdata[1] = mescorreto;
                vetdata[2] = date.getYear();
                Intent i = new Intent(CalendarActivity.this,AdicionarGastosActivity.class);
                i.putExtra("data",vetdata);
                startActivity(i);

            }
        });

    }
}
