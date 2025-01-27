package com.example.sparkbankingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

import java.util.concurrent.Executor;

public class Homepage extends AppCompatActivity {

    CircleMenu crcl;
    ConstraintLayout cl;
    TextView balance;
    String data1;
    TextView refresh;
    private FirebaseDatabase Database;
    private DatabaseReference Ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        crcl = findViewById(R.id.crcl);
        cl = findViewById(R.id.cl);
        balance = findViewById(R.id.balance);
        refresh = findViewById(R.id.refresh);

        Database= FirebaseDatabase.getInstance();
        Ref=Database.getReference("Users").child("Sai Suvam");
        Ref.child("Balance").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                data1=dataSnapshot.getValue(String.class);
                balance.setText(String.valueOf(data1));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ref.child("Balance").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        data1=dataSnapshot.getValue(String.class);
                        balance.setText(String.valueOf(data1));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate()){
            case BiometricManager.BIOMETRIC_SUCCESS:

                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:

                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:


                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:

                break;
        }

        Executor executor = ContextCompat.getMainExecutor(this);
        final BiometricPrompt biometricPrompt = new BiometricPrompt(Homepage.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Homepage.this,users.class);
                startActivity(intent);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });

        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("LOGIN")
                .setDescription("USE FINGERPRINT TO LOGIN")
                .setNegativeButtonText("Cancel")
                .build();

        crcl.setMainMenu(Color.parseColor("#000000"),R.drawable.menu,R.drawable.cancel)
                .addSubMenu(Color.parseColor("#ff4032"),R.drawable.person)
                .addSubMenu(Color.parseColor("#2f2f2f"),R.drawable.history)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int index) {
                        switch (index){
                            case 0:
                                biometricPrompt.authenticate(promptInfo);
                                break;
                            case 1:
                                Intent inte = new Intent(Homepage.this,history.class);
                                startActivity(inte);
                                break;

                        }
                    }
                });



    }
}