package br.com.allefdeveloper.despesasmensais;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import br.com.allefdeveloper.despesasmensais.Adapter.GastosAdapter;
import br.com.allefdeveloper.despesasmensais.Model.GastosDiarios;
import butterknife.BindView;
import butterknife.ButterKnife;

public class GastosDetalhadosActivity extends AppCompatActivity {
    DatabaseReference reference;
    FirebaseDatabase database;
    private GastosDiarios gastos ;
    List<GastosDiarios> listadeGastos;
    @BindView(R.id.mees)
    Spinner spmees;
    String nomemes[];
    GastosAdapter gastosAdapter;
    Context context;
    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastos_detalhados);
        getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        inicializaFirebase();
        ButterKnife.bind(this);
        recyclerView = findViewById(R.id.recycler);
        listadeGastos = new ArrayList<>();
        nomemes = getResources().getStringArray(R.array.mes);
        ArrayAdapter<String> mesadpter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nomemes);
        spmees.setAdapter(mesadpter);
        gastos = new GastosDiarios();
        context = GastosDetalhadosActivity.this;
        spmees.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, final int i, long l) {
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        listadeGastos.clear();
                        if(dataSnapshot.hasChild(String.valueOf(i)))
                        for (DataSnapshot d : dataSnapshot.child(String.valueOf(i)).getChildren()){
                            gastos = d.getValue(GastosDiarios.class);
                            listadeGastos.add(gastos);
                        }
                        configRecyclerView();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





    }

    private void configRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        gastosAdapter = new GastosAdapter(context,listadeGastos);
        gastosAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(gastosAdapter);
    }

    private void inicializaFirebase() {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("despesas");
    }



}
