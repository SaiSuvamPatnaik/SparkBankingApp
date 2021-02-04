package com.example.sparkbankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class users extends AppCompatActivity {

    CardView user1,user2,user3,user4,user5,user6,user7,user8;
    TextView name1,name2,name3,name4,name5,name6,name7,name8;
    String name,data4;
    ImageView next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        user1=findViewById(R.id.user1);
        name1=findViewById(R.id.name1);
        user2=findViewById(R.id.user2);
        name2=findViewById(R.id.name2);
        user3=findViewById(R.id.user3);
        name3=findViewById(R.id.name3);
        user4=findViewById(R.id.user4);
        name4=findViewById(R.id.name4);
        user5=findViewById(R.id.user5);
        name5=findViewById(R.id.name5);
        user6=findViewById(R.id.user6);
        name6=findViewById(R.id.name6);
        user7=findViewById(R.id.user7);
        name7=findViewById(R.id.name7);
        user8=findViewById(R.id.user8);
        name8=findViewById(R.id.name8);
        next = findViewById(R.id.next);

        String dataa = (getIntent().getStringExtra("name"));


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(users.this,history.class);
                intent.putExtra("data4",data4);
                startActivity(intent);
            }
        });



        user1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name= (String) name1.getText();
                Intent intent = new Intent(users.this,details.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
        user2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name= (String) name2.getText();
                Intent intent = new Intent(users.this,details.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
        user3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name= (String) name3.getText();
                Intent intent = new Intent(users.this,details.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
        user4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name= (String) name4.getText();
                Intent intent = new Intent(users.this,details.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
        user5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name= (String) name5.getText();
                Intent intent = new Intent(users.this,details.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
        user6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name= (String) name6.getText();
                Intent intent = new Intent(users.this,details.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
        user7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name= (String) name7.getText();
                Intent intent = new Intent(users.this,details.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
        user8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name= (String) name8.getText();
                Intent intent = new Intent(users.this,details.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
    }
}