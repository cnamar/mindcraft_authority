package com.example.mindcraft_main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class user extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    TextView name;
    TextView area;
    FirebaseFirestore db;
    Button sign,manage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        name=findViewById(R.id.name);
        area=findViewById(R.id.area);
        manage=findViewById(R.id.manage);
        mAuth= FirebaseAuth.getInstance();
        String uid=mAuth.getCurrentUser().getUid();
        db= FirebaseFirestore.getInstance();
        DocumentReference doc=db.collection("Authorities").document(uid);
        doc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful())
                {
                    DocumentSnapshot details=task.getResult();
                    name.setText(details.getString("Name"));

                    area.setText(details.getString("Local_body_name"));
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Something error occured",Toast.LENGTH_LONG).show();
                }
            }
        });
        sign=findViewById(R.id.signout);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent pagen=new Intent(user.this,MainActivity.class);
                pagen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                pagen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(pagen);

            }
        });
        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent page=new Intent(user.this,tabular.class);
                page.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                page.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(page);
            }
        });
    }
}