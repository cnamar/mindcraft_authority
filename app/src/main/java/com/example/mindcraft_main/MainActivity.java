package com.example.mindcraft_main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    Button login;
    EditText mail;
    EditText password;
    FirebaseUser mUser;
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.login);
        mail = findViewById(R.id.mail);
        password = findViewById(R.id.editTextTextPassword);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        if(mUser!=null) {
            Intent page = new Intent(MainActivity.this, user.class);
            page.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            page.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(page);
        }

        else
        {
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "PROCESSING....", Toast.LENGTH_LONG).show();
                    String email = mail.getText().toString().trim();
                    String pwd = password.getText().toString().trim();

                    if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pwd)) {

                        mAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    Intent page=new Intent(MainActivity.this,user.class);
                                    page.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    page.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(page);

                                } else {
                                    Toast.makeText(getApplicationContext(), "Couldn't login, User not found", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    } else {

                        Toast.makeText(getApplicationContext(), "Complete all fields", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}