package br.com.allefdeveloper.despesasmensais;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;

    private DatabaseReference reference;
    private FirebaseFirestore firestore;
    private GastosDiarios gastos;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dispesa);
        ButterKnife.bind(this);
        Fabric.with(this, new Crashlytics());
        firebaseConfig();
        DataeHora.retornadaData();
        gastos = new GastosDiarios();
        myCalendar = Calendar.getInstance();



        /**
         * configurando o data timepicker
         */
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        txtdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                /**
                 * criando a estrutura para abrir o date timer picker ao
                 * clicar no edittext
                 */
                new DatePickerDialog(CalendarActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        addDispesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = txtdata.getText().toString();
                String[] mesat = data.split("/");
                if (data != null) {

                    gastos.setDataDispesa(txtdata.getText().toString());
                    gastos.setTitulo(txtTitulo.getText().toString());
                    gastos.setValor(Double.parseDouble(txtvalor.getText().toString()));
                    gastos.setDescricao(txtDescricao.getText().toString());
                    gastos.setFormaPagamento(txtformadepagamento.getText().toString());
                    reference.child(mesat[1]).setValue(gastos).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            limpasCampos();
                            Toast.makeText(CalendarActivity.this, "Produto Adicionado com Sucesso !!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });

    }

    private void limpasCampos() {
        txtdata.setText("");
        txtDescricao.setText("");
        txtformadepagamento.setText("");
        txtTitulo.setText("");
        txtvalor.setText("");
    }

    /**
     * metodo responsavel por inicializar o firebase;
     */
    private void firebaseConfig() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInAnonymously();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("despesas");
    }

    /**
     * metodo responsavel por formatar a data e atualizar o campo com a data
     * selecionada.
     */
    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        txtdata.setText(sdf.format(myCalendar.getTime()));
    }
}
