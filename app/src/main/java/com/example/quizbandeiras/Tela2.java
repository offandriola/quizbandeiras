package com.example.quizbandeiras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Tela2 extends AppCompatActivity {

    private TextView txtNomeUsuario;
    private TextView txtAcertos;
    private Button btnResponderNovamente;
    private Button btnTelaPrincipal;
    private String nomeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        txtNomeUsuario = findViewById(R.id.txtNomeUsuario);
        txtAcertos = findViewById(R.id.txtAcertos);
        btnResponderNovamente = findViewById(R.id.btnResponderNovamente);
        btnTelaPrincipal = findViewById(R.id.btnTelaPrincipal);

        // Recebe os dados da tela anterior (nome do usuário e pontuação)
        nomeUsuario = getIntent().getStringExtra("NOME_USUARIO");
        int pontuacao = getIntent().getIntExtra("PONTUACAO", 0);

        // Atualiza os TextViews com os dados recebidos
        txtNomeUsuario.setText("Nome do Usuário: " + nomeUsuario);
        txtAcertos.setText("Acertos: " + pontuacao+"/10");

        btnResponderNovamente.setOnClickListener(v -> {
            Intent responderIntent = new Intent(this, Tela1.class);
            responderIntent.putExtra("NOME_USUARIO", nomeUsuario);
            startActivity(responderIntent);
            finish();
        });

        btnTelaPrincipal.setOnClickListener(v -> {
            Intent inicioIntent = new Intent(Tela2.this, MainActivity.class);
            startActivity(inicioIntent);
            finish();
        });
    }
}
