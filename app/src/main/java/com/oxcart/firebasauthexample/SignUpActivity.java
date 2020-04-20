package com.oxcart.firebasauthexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    private TextInputLayout textEmailRegister;
    private TextInputLayout textPasswordRegister;
    private MaterialButton btnSignUp;
    private TextView textSignIn;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        textEmailRegister = findViewById(R.id.text_input_email_register);
        textPasswordRegister = findViewById(R.id.text_input_password_register);
        btnSignUp = findViewById(R.id.button_sign_up);

        textSignIn = findViewById(R.id.text_already_have_account);

        progressDialog = new ProgressDialog(SignUpActivity.this);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finish();
        }

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = textEmailRegister.getEditText().getText().toString();
                password = textPasswordRegister.getEditText().getText().toString();
                if(TextUtils.isEmpty(email)){

                    Toast.makeText(SignUpActivity.this,"Please enter email", Toast.LENGTH_LONG).show();

                }
                else if(TextUtils.isEmpty(password)){
                    Toast.makeText(SignUpActivity.this,"Please enter password",Toast.LENGTH_LONG).show();

                }
                else if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                }
                else {
                    registerUser(email,password);
                }
            }
        });


        textSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }

    private void registerUser(String email, String password){
        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //display some message here
                    Toast.makeText(SignUpActivity.this,"Successfully registered",Toast.LENGTH_LONG).show();
                }else{
                    //display some message here
                    Toast.makeText(SignUpActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });
    }
}
