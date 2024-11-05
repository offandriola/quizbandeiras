package com.example.quizbandeiras;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtRgm;
    private Button btnIniQuiz;
    private Button btnEncerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ligar o java ao xml
        edtNome = findViewById(R.id.edtNome);
        edtRgm = findViewById(R.id.edtRgm);
        btnIniQuiz = findViewById(R.id.btnIniQuiz);
        btnEncerrar = findViewById(R.id.btnEncerrar);

        // Adicionar TextWatcher para monitorar mudanças nos campos de texto
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                verificarCampos();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        edtNome.addTextChangedListener(textWatcher);
        edtRgm.addTextChangedListener(textWatcher);

        btnIniQuiz.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Tela1.class);
            intent.putExtra("NOME_USUARIO", edtNome.getText().toString());
            intent.putExtra("RGM_USUARIO", edtRgm.getText().toString());
            startActivity(intent);
            finish();
        });
    }

    // função chamada sempre que há uma mudança em qualquer campo de texto
    private void verificarCampos() {
        String nome = edtNome.getText().toString().trim();
        String rgm = edtRgm.getText().toString().trim();
        btnIniQuiz.setEnabled(!nome.isEmpty() && !rgm.isEmpty());
    }
    public void encerrar(View view){
        finish();
    }
}