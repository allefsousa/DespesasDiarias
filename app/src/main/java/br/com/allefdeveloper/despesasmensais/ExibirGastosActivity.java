package br.com.allefdeveloper.despesasmensais;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.allefdeveloper.despesasmensais.Model.GastosDiarios;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ExibirGastosActivity extends AppCompatActivity {
    int quantCategorias;
    Calendar myCalendar;
    @BindView(R.id.datapesquisa)
    Spinner datapes;
    List<GastosDiarios> arraygastos;
    private PieChart mChart;
    private String[] categorias, mes;
    private FirebaseDatabase database;
    private DatabaseReference myref;
    private GastosDiarios gastos;
    Double[] gastosPorCategoria ;
    List<Double> gs;
    Map<String,Double> vv = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_exibir_gastos);
        ButterKnife.bind(this);
        getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        categorias = getResources().getStringArray(R.array.categorias);
        mes = getResources().getStringArray(R.array.mes);
        gastos = new GastosDiarios();
        arraygastos = new ArrayList<>();
        gastosPorCategoria = new Double[4];
        myCalendar = Calendar.getInstance();
        inicializaFirebase();
        ArrayAdapter<String> mesadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mes);
        datapes.setAdapter(mesadapter);


        datapes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, final int i, long l) {
                arraygastos.clear();
                mChart.clear();
                myref.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {
                       if(dataSnapshot.hasChild(String.valueOf(i))){
                           for (DataSnapshot d : dataSnapshot.child(String.valueOf(i)).getChildren()) {
                               gastos = d.getValue(GastosDiarios.class);
                               arraygastos.add(gastos);
                           }
                           trataDados(arraygastos);

                       }else if(i != 0){
                           Snackbar.make(findViewById(android.R.id.content), "Não existem dados para serem Exibidos nesse periodo !!",
                                   Snackbar.LENGTH_LONG).show();
                       }

                   }



                   @Override
                   public void onCancelled(DatabaseError databaseError) {

                   }
               });
//
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        quantCategorias = categorias.length;
        mChart = findViewById(R.id.chart1);


    }

    private void trataDados(List<GastosDiarios> arraygastos) {

        for (int i=0;i<arraygastos.size();i++){
            for (int j =0;j<categorias.length;j++) {
               if(arraygastos.get(i).getCategoria().equals(categorias[j])){
                   if(vv.containsKey(arraygastos.get(i).getCategoria())) {
                       Double val = vv.get(arraygastos.get(i).getCategoria());
                       val = val + arraygastos.get(i).getValor();
                       vv.put(arraygastos.get(i).getCategoria(),val);
                   }else {
                       vv.put(arraygastos.get(i).getCategoria(),arraygastos.get(i).getValor());
                   }
               }
            }
        }

        personalizaGrafico(mChart,arraygastos,vv);
    }

    private void inicializaFirebase() {
        database = FirebaseDatabase.getInstance();
        myref = database.getReference().child("despesas");

    }

    private void personalizaGrafico(PieChart mChart,List<GastosDiarios> gg ,Map valores) {
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);
        mChart.setUsePercentValues(true);
        mChart.setDragDecelerationFrictionCoef(0.95f);
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);
        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);
        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);
        mChart.setDrawCenterText(true);
        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);

        setData( 100,valores);
        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        mChart.setEntryLabelColor(Color.BLACK);
        mChart.setEntryLabelTextSize(15f);


    }

    private void setData( float range,Map gg) {

        float mult = range;
        ArrayList<PieEntry> entries = new ArrayList<>();



        Set<String> chaves = gg.keySet();
        gg.entrySet();

        for(String chave : chaves){
            Double recebe = (Double) gg.get(chave);


                entries.add(new PieEntry((float) (recebe + mult / 5),
                        chave,
                        getResources().getDrawable(R.drawable.ic_launcher_background)));

        }

        PieDataSet dataSet = new PieDataSet(entries, "Gastos Mensais ");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }
}
