package com.lucky.e_wallet.finale;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity{

    private EditText ml, pw;
    private TextView link;
    private Button login;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        ml =  findViewById(R.id.EMail);
        pw =  findViewById(R.id.LPassword);
        progressBar = findViewById(R.id.progressBar);

        login =  findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail = ml.getText().toString();
                String pass = pw.getText().toString();

                if (mail.isEmpty()) {
                    ml.setError("Email is required!");
                    ml.requestFocus();
                    return;
                }
                if (pass.isEmpty()) {
                    pw.setError("Password is required!");
                    pw.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(mail, pass)
                        .addOnCompleteListener(
                                new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(

                                            @NonNull Task<AuthResult> task) {
                                        progressBar.setVisibility(View.GONE);
                                        if (task.isSuccessful()) {
                                            Toast.makeText(LoginActivity.this, "Login Successful! ", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                            startActivity(intent);
                                            finish();


                                            //startActivity(new Intent(LoginActivity.this, MainActivity.class));

                                        } else {
                                            Toast.makeText(LoginActivity.this, "Failed to Login! ", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
            }
        });
        link =  findViewById(R.id.LinkSignin);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }

        });

        //String username;

        //mAuth = FirebaseAuth.getInstance();

    }
}


    /*@Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                progressBar.setVisibility(View.VISIBLE);
                userLogin();
                break;
            case R.id.LinkSignin:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }

    private void userLogin() {
        String mail = ml.getText().toString();
        String pass = pw.getText().toString();

        if (mail.isEmpty()) {
            ml.setError("Email is required!");
            ml.requestFocus();
            return;
        }
        if (pass.isEmpty()) {
            pw.setError("Password is required!");
            pw.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(mail,pass)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(
                                @NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Login Successful! ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();

                    FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    GlobalVar.currentUser = snapshot.getValue(User.class);



                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {

                                                }
                                            });
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));

                }else{
                    Toast.makeText(LoginActivity.this, "Failed to Login! ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    /*private void setupHyperlink() {
        TextView linkTextView = findViewById(R.id.LinkLogin);
        linkTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }*/
//}
