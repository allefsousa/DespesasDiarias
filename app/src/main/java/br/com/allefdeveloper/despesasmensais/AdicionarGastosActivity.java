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
    FirebaseFirestore db ;
    GastosDiarios gastos ;
    FirebaseAuth auth;


    private static final String NAME_KEY = "Name";
    private static final String EMAIL_KEY = "Email";
    private static final String PHONE_KEY = "Phone";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_gastos);
        getSupportActionBar();
        getSupportActionBar().setTitle("Gastos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        int[] recebedata = new int[2];
        recebedata =(int[]) intent.getSerializableExtra("data");

        Toast.makeText(this, "mes"+recebedata[1], Toast.LENGTH_LONG).show();
        auth = FirebaseAuth.getInstance();
        auth.signInAnonymously();
        db = FirebaseFirestore.getInstance();
//        addNewContact();
        gastos = new GastosDiarios();
        gastos.setTitulo("chupa eu");
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

//    private void addNewContact() {
//        Map<String, Object> newContact = new HashMap<>();
//        newContact.put(NAME_KEY, "John");
//        newContact.put(EMAIL_KEY, "john@gmail.com");
//        newContact.put(PHONE_KEY, "080-0808-009");
//        db.collection("PhoneBook").document("Contacts").set(newContact)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Toast.makeText(AdicionarGastosActivity.this, "User Registered",
//                                Toast.LENGTH_LONG).show();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(AdicionarGastosActivity.this, "ERROR" + e.toString(),
//                                Toast.LENGTH_LONG).show();
//                        Log.d("TAG", e.toString());
//                    }
//                });
//    }
}
