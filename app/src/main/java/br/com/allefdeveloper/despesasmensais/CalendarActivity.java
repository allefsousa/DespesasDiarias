package br.com.allefdeveloper.despesasmensais;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import br.com.allefdeveloper.despesasmensais.Model.GastosDiarios;
import br.com.allefdeveloper.despesasmensais.Utils.DataeHora;
import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;

public class CalendarActivity extends AppCompatActivity {
    @BindColor(R.color.colorAccent)
    int arrow;
    @BindView(R.id.texteditdata)
    TextInputEditText txtdata;
    @BindView(R.id.textedittitulo)
    TextInputEditText txtTitulo;
    @BindView(R.id.texteditdescricao)
    TextInputEditText txtDescricao;
    @BindView(R.id.texteditvalor)
    TextInputEditText txtvalor;
    @BindView(R.id.texteditformadepagamento)
    TextInputEditText txtformadepagamento;
    @BindView(R.id.btnsalvar)
    Button addDispesa;



    private GastosDiarios gastos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataeHora.retornadaData();
        Fabric.with(this, new Crashlytics());
        ButterKnife.bind(this);
        setContentView(R.layout.activity_add_dispesa);
        gastos = new GastosDiarios();




    }
}
