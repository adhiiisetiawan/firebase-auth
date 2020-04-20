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

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextInputLayout textEmail;
    private TextInputLayout textPassword;
    private MaterialButton btnSignIn;
    private TextView textSignUp;

    private ProgressDialog progressDialog;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finish();
        }

        textEmail =  findViewById(R.id.text_input_email);
        textPassword = findViewById(R.id.text_input_password);
        btnSignIn = findViewById(R.id.button_sign_in);
        textSignUp = findViewById(R.id.text_dont_have_account);

        progressDialog = new ProgressDialog(this);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = textEmail.getEditText().getText().toString();
                password = textPassword.getEditText().getText().toString();

                if(TextUtils.isEmpty(email)){

                    Toast.makeText(MainActivity.this,"Please enter email", Toast.LENGTH_LONG).show();

                }
                else if(TextUtils.isEmpty(password)){
                    Toast.makeText(MainActivity.this,"Please enter password",Toast.LENGTH_LONG).show();

                }
                else if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                }
                else {


                    userLogin(email,password);
                }
            }
        });

        textSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
                finish();
            }
        });
    }

    private void userLogin(String email, String password){
        progressDialog.setMessage("Loging in Please Wait...");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                //if the task is successfull
                if (task.isSuccessful()) {
                    //start the profile activity
                    finish();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                }
            }
        });
    }
}
