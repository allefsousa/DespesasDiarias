package br.com.allefdeveloper.despesasmensais;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import br.com.allefdeveloper.despesasmensais.Model.GastosDiarios;

public class AdicionarGastosActivity extends AppCompatActivity {
    private FirebaseFirestore db ;
    private GastosDiarios gastos ;
    private FirebaseAuth auth;
    int[] recebedata = new int[2];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_gastos);
        getSupportActionBar();
        getSupportActionBar().setTitle("Gastos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recebendoData();
        inicializaFirebase();
        gastos = new GastosDiarios();



        Toast.makeText(this, "mes"+recebedata[1], Toast.LENGTH_LONG).show();


// TODO: 08/02/2018  organizar essa bagunça

        gastos.setTitulo("");
        gastos.setValor(10.50);
        gastos.setAno(recebedata[2]);
        gastos.setDia(recebedata[0]);
        gastos.setMes(recebedata[1]);

        db.collection(String.valueOf(gastos.getMes())).document(String.valueOf(gastos.getDia())).set(gastos).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(AdicionarGastosActivity.this, "User Registered",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void inicializaFirebase() {
        auth = FirebaseAuth.getInstance();
        auth.signInAnonymously();
        db = FirebaseFirestore.getInstance();
    }

    private void recebendoData() {
        Intent intent = getIntent();
        recebedata =(int[]) intent.getSerializableExtra("data");
    }

}
