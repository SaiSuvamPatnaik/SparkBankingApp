package com.example.sparkbankingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class details extends AppCompatActivity {

    ImageView img;
    TextView name,phone,balance,ifsc;
    Uri imageuri,uri;
    private FirebaseDatabase Database;
    private DatabaseReference Ref;
    Long data1,data2,data3;
    Button pay,back;
    String data4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        balance=findViewById(R.id.balance);
        ifsc=findViewById(R.id.ifsc);
        img=findViewById(R.id.img);
        pay=findViewById(R.id.pay);
        back=findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(details.this,users.class);
                intent.putExtra("data4",data4);
                startActivity(intent);
            }
        });

        data4 = (getIntent().getStringExtra("name"));

        Database=FirebaseDatabase.getInstance();
        Ref=Database.getReference("Users").child(data4);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.startPickImageActivity(details.this);
            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog();
            }
        });


        Ref.child("Name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String data=dataSnapshot.getValue(String.class);
                name.setText(data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Ref.child("Balance").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data1= Long.valueOf(String.valueOf(dataSnapshot.getValue(Long.class)));
                balance.setText(String.valueOf(data1));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Ref.child("Phone").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data3= Long.valueOf(String.valueOf(dataSnapshot.getValue(Long.class)));
                phone.setText(String.valueOf(data3));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Ref.child("IFSC").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data2= Long.valueOf(String.valueOf(dataSnapshot.getValue(Long.class)));
                ifsc.setText(String.valueOf(data2));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri imageuri = CropImage.getPickImageResultUri(this, data);
            if (CropImage.isReadExternalStoragePermissionsRequired(this, imageuri)) {
                uri = imageuri;
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            } else {
                startcrop(imageuri);
            }
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                imageuri=result.getUri();
                img.setImageURI(imageuri);

            }
        }


    }

    private void startcrop(Uri imageuri) {
        CropImage.activity(imageuri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .start(this);
    }

    private void opendialog() {
        ExampleDialog exampleDialog = new ExampleDialog(data4);
        exampleDialog.show(getSupportFragmentManager(),"example dialog");
    }

}