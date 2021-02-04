package com.example.sparkbankingapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Ref;

public class ExampleDialog extends AppCompatDialogFragment {

    EditText name;
    String data4,datadata,datadata1,datadata2;
    String id;
    Integer data1,data2,sum;

    public ExampleDialog(String data4) {
        this.data4=data4;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.layout_dialog,null);
        name=view.findViewById(R.id.name);
        builder.setView(view).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                final String name1=name.getText().toString();
                String Name=data4;
                String Amount=name1;
                model obj = new model(Name,Amount);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = database.getReference("PERSON");
                FirebaseDatabase database2 = FirebaseDatabase.getInstance();
                final DatabaseReference myRef2 = database2.getReference("Users");
                id = myRef.push().getKey();
                myRef.child("Sai Suvam").child(id).setValue(obj);
                myRef.child("Sai Suvam").child(id).child("amount").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        datadata=dataSnapshot.getValue(String.class);
                        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
                        final DatabaseReference myRef1 = database1.getReference("Changes");
                        myRef1.child("change").setValue(String.valueOf(datadata));
                        myRef1.child("change").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                datadata1=dataSnapshot.getValue(String.class);
                                data1=Integer.parseInt(datadata1);

                                myRef2.child(data4).child("Balance").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        datadata2=dataSnapshot.getValue(String.class);
                                        data2=Integer.parseInt(datadata2);
                                        sum=data1+data2;
                                        myRef2.child(data4).child("Balance").setValue(String.valueOf(sum));
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });










                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });











            }
        });

        return builder.create();
    }



}
