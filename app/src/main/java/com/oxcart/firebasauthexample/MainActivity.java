package com.oxcart.firebasauthexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextInputLayout textEmail;
    private TextInputLayout textPassword;
    private MaterialButton btnSignIn;
    private TextView textSignUp;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null){

            //cek user
//            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finish();
        }

        textEmail =  findViewById(R.id.text_input_email);
        textPassword = findViewById(R.id.text_input_password);
        btnSignIn = findViewById(R.id.button_sign_in);
        textSignUp = findViewById(R.id.text_dont_have_account);

        progressBar = new ProgressBar(this);

        textSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
                finish();
            }
        });
    }
}
