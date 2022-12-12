package com.example.mapamoradia;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.ktx.Firebase;

public class MainActivity extends Activity {

    

    private TextView text_telacadastro;
    private EditText email_edit, senha_edit;
    private Button login_button;
    private ProgressBar progressBar;
    String[] mensagens = {"Preencha todos os campos",};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        IniciarComponentes();

        text_telacadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, FormCadastro.class);
                startActivity(intent);

            }
        });
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = email_edit.getText().toString();
                String senha = senha_edit.getText().toString();

                if (email.isEmpty() || senha.isEmpty()) {

                    Snackbar snackbar = Snackbar.make(view, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                } else {
                    AutenticarUsuario();

                }

            }
        });
    }
        private void AutenticarUsuario() {

            String email = email_edit.getText().toString();
            String senha = senha_edit.getText().toString();

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressBar.setVisibility(View.VISIBLE);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                TelaPrincipal();
                            }
                        }, 3000);
                    }
                }
            });
        }
            private void TelaPrincipal() {
                Intent intent = new Intent(MainActivity.this, FormTelaPrincipal.class);
                startActivity(intent);
                finish();


    }

    private void IniciarComponentes(){

        text_telacadastro = findViewById(R.id.text_telacadastro);
        email_edit = findViewById(R.id.email_edit);
        senha_edit = findViewById(R.id.senha_edit);
        login_button = findViewById(R.id.login_button);
        progressBar = findViewById(R.id.progressbar);

    }

}
