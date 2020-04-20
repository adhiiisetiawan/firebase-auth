package com.oxcart.firebasauthexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextView textWelcome;
    private MaterialButton btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() == null){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        textWelcome = findViewById(R.id.textview_welcome);
        btnLogout = findViewById(R.id.button_logout);

        FirebaseUser firebaseUser = mAuth.getCurrentUser();

        textWelcome.setText("Welcome "+firebaseUser.getDisplayName()+" "+firebaseUser.getEmail());

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
            }
        });
    }
}
