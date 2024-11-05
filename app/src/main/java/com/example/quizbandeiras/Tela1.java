package com.example.quizbandeiras;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Tela1 extends AppCompatActivity {

    private ImageView imageViewFlag;
    private RadioGroup radioGroupOptions;
    private Button btnResponder;

    // Controle do índice da pergunta
    private int currentQuestionIndex = 0;

    // imagens - bandeiras
    private String[] flagImages = {"brasil", "bulgaria", "canada", "china", "coreia_sul",
            "cuba", "guatemala", "hungria", "italia", "usa"}; // Nomes dos recursos das bandeiras

    // alternativas
    private String[][] options = {
            {"Brasil", "Argentina", "Uruguai", "Chile"},
            {"Nigéria", "Líbano", "Somália", "Bulgaria"},
            {"França", "Canadá", "Espanha", "Itália"},
            {"Colombia", "Suécia", "China", "Dinamarca"},
            {"Costa Rica", "Suiça", "Coreia do Norte", "Coreia do Sul"},
            {"França", "Cuba", "Jamaica", "Russia"},
            {"Irlanda do Norte", "Holanda", "Guatemala", "Chile"},
            {"Hungria", "Panamá", "Brasil", "Arábia Saudita"},
            {"Croácia", "Suécia", "Luxemburgo", "Itália"},
            {"Polônia", "Colombia", "México", "Estados Unidos"}
    };

    // Índices das respostas corretas
    private int[] correctAnswers = {0, 3, 1, 2, 3, 1, 2, 0, 3, 3};

    // Inicializa a pontuação
    private int pontuacao = 0;
    private String nomeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela1);

        imageViewFlag = findViewById(R.id.imageViewFlag);
        radioGroupOptions = findViewById(R.id.radioGroupOptions);
        btnResponder = findViewById(R.id.btnResponder);

        // Supondo que você já tem o nome do usuário de uma entrada anterior
        nomeUsuario = getIntent().getStringExtra("NOME_USUARIO");

        loadQuestion();

        radioGroupOptions.setOnCheckedChangeListener((group, checkedId) -> {
            btnResponder.setEnabled(true);
        });

        btnResponder.setOnClickListener(v -> {
            int selectedOption = radioGroupOptions.indexOfChild(findViewById(radioGroupOptions.getCheckedRadioButtonId()));
            if (selectedOption == correctAnswers[currentQuestionIndex]) {
                pontuacao++; // Aumenta a pontuação em caso de acerto
                Toast.makeText(Tela1.this, "Você acertou!", Toast.LENGTH_SHORT).show(); // Exibe mensagem de acerto
            } else {
                Toast.makeText(Tela1.this, "Você errou!", Toast.LENGTH_SHORT).show(); // Exibe mensagem de erro
            }

            currentQuestionIndex++;
            if (currentQuestionIndex < flagImages.length) {
                loadQuestion();
            } else {
                // Vá para a tela de ranking com os dados necessários
                Intent intent = new Intent(Tela1.this, Tela2.class);
                intent.putExtra("NOME_USUARIO", nomeUsuario);
                intent.putExtra("PONTUACAO", pontuacao);
                startActivity(intent);
                finish();
            }
            // Limpar seleção dos botões de rádio
            radioGroupOptions.clearCheck();
            // Desabilita o botão novamente até que o usuário selecione uma nova opção
            btnResponder.setEnabled(false);
        });
    }

    private void loadQuestion() {
        // Atualize a imagem e as opções baseadas no índice da pergunta atual
        int flagResId = getResources().getIdentifier(flagImages[currentQuestionIndex], "drawable", getPackageName());
        imageViewFlag.setImageResource(flagResId);
        ((RadioButton) findViewById(R.id.radioOpção1)).setText(options[currentQuestionIndex][0]);
        ((RadioButton) findViewById(R.id.radioOpção2)).setText(options[currentQuestionIndex][1]);
        ((RadioButton) findViewById(R.id.radioOpção3)).setText(options[currentQuestionIndex][2]);
        ((RadioButton) findViewById(R.id.radioOpção4)).setText(options[currentQuestionIndex][3]);
        btnResponder.setEnabled(false);
    }
}
