package com.digitalartists.minesweeper.view;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.digitalartists.minesweeper.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Registrationn extends AppCompatActivity {
    EditText eemail,ename,epassoword;
    Button reg ;

    FirebaseAuth uth;
    ProgressBar par;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrationn);
        uth =FirebaseAuth.getInstance();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            ename = findViewById(R.id.regname);
            eemail = findViewById(R.id.regemail);
            epassoword= findViewById(R.id.regpass);
            par = findViewById(R.id.parreg);
            reg = findViewById(R.id.regbutton);
            reg.setOnClickListener(new  View.OnClickListener() {
                @Override
                public void onClick(View view) { // Change onclick to onClick
                    par.setVisibility(View.VISIBLE); // Change view.VISIBLE to View.VISIBLE
                    String email, pass, name;
                    email = String.valueOf(eemail.getText());
                    name = String.valueOf(ename.getText());
                    pass = String.valueOf(epassoword.getText());
                    if (TextUtils.isEmpty(email)) {
                        Toast.makeText(Registrationn.this, "enter email", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(pass)) {
                        Toast.makeText(Registrationn.this, "enter password", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(name)) {
                        Toast.makeText(Registrationn.this, "enter name", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    uth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        par.setVisibility(View.GONE); // Change view.GONE to View.GONE
                                        Toast.makeText(Registrationn.this, "Done",
                                                Toast.LENGTH_SHORT).show();

                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        if (user != null) {
                                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                                    .setDisplayName(name) // Set the user's name
                                                    // You can also set other profile information here if needed
                                                    .build();

                                            user.updateProfile(profileUpdates)
                                                    .addOnCompleteListener(task1 -> {
                                                        if (task1.isSuccessful()) {
                                                            Log.d(TAG, "User profile updated.");
                                                        }
                                                    });
                                        }

                                        Intent login_intent = new Intent(Registrationn.this ,Login.class);
                                        startActivity(login_intent);
                                    } else {
                                        Toast.makeText(Registrationn.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            });
            return insets;

        });
    }
}