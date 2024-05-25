package com.digitalartists.minesweeper.view;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.digitalartists.minesweeper.R;
import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.digitalartists.minesweeper.R;
import com.digitalartists.minesweeper.view.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class Login extends AppCompatActivity {


    EditText lemail, lpassoword;
    Button log;
    FirebaseAuth luth;
    ProgressBar lpar;

    @Override
    public void onStart() {

        super.onStart();

        // Initialize FirebaseAuth instance
        luth = FirebaseAuth.getInstance();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = luth.getCurrentUser();
        if (currentUser != null) {
            Intent login_intent = new Intent(Login.this, MainActivity.class);
            startActivity(login_intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        luth = FirebaseAuth.getInstance(); // Initialize FirebaseAuth instance

        lemail = findViewById(R.id.logemail);
        lpassoword = findViewById(R.id.logpass);
        lpar = findViewById(R.id.parlog);
        log = findViewById(R.id.logbutton);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lpar.setVisibility(View.VISIBLE);
                String email, pass;
                email = String.valueOf(lemail.getText());
                pass = String.valueOf(lpassoword.getText());
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Login.this, "Enter email", Toast.LENGTH_SHORT).show();
                    lpar.setVisibility(View.GONE);
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(Login.this, "Enter password", Toast.LENGTH_SHORT).show();
                    lpar.setVisibility(View.GONE);
                    return;
                }

                luth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                lpar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(Login.this, "Authentication successful", Toast.LENGTH_SHORT).show();
                                    Intent login_intent = new Intent(Login.this, MainActivity.class);
                                    startActivity(login_intent);
                                } else {
                                    Toast.makeText(Login.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void reg(View view) {
        Intent login_intent = new Intent(Login.this, Registrationn.class);
        startActivity(login_intent);
    }
}


