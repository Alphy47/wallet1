package com.lucky.e_wallet.finale;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText email, password, repassword;

    private TextView Llink;
    Button register;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.Email);
        password =  findViewById(R.id.Password);
        repassword = findViewById(R.id.Repassword);
        progressBar = findViewById(R.id.progressBar);

        Llink = findViewById(R.id.LinkLogin);
        Llink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });

        register = findViewById(R.id.btnSignup);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String mail = email.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();


                if (TextUtils.isEmpty(mail)) {
                    email.setError("Email is required!");
                    email.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                    email.setError("Please provide valid email!");
                    email.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    password.setError("Password is required!");
                    password.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(repass)) {
                    repassword.setError("Confirmation password is required!");
                    repassword.requestFocus();
                    return;
                }
                if (!pass.equals(repass)) {
                    Toast.makeText(RegisterActivity.this, "Passwords are not matching.", Toast.LENGTH_LONG).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(mail, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {

                                    Toast.makeText(RegisterActivity.this,"Registered Successfully",
                                            Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });
    }
}