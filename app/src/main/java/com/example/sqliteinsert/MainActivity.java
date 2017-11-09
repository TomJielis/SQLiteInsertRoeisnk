package com.example.sqliteinsert;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText Name, Pass;
    myDbAdapter helper;
    public static Context context;
    EditText edit;
    EditText editnew;
    EditText editDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        Name= (EditText) findViewById(R.id.editName);
        Pass= (EditText) findViewById(R.id.editPass);
        helper = new myDbAdapter(this);
        Button showdata = (Button)findViewById(R.id.button2);
        edit = (EditText)findViewById(R.id.Oldname);
        editnew = (EditText)findViewById(R.id.NewName);
        editDel = (EditText)findViewById(R.id.DelUser);
        Button update = (Button)findViewById(R.id.button3);
        Button Del = (Button)findViewById(R.id.BtnDeleteUser);

        Del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name = editDel.getText().toString();
                    helper.deleteUser(name);
                    Toast.makeText(MainActivity.this, "User is deleted", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Kon gebruiker niet verwijderen", Toast.LENGTH_SHORT).show();
                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String oldname = edit.getText().toString();
                    String NewName = editnew.getText().toString();
                    helper.updateUser(oldname, NewName);
                    Toast.makeText(MainActivity.this, "user is updated", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "user update failed.", Toast.LENGTH_SHORT).show();
            }

            }
        });
        showdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResultAct.class);
                startActivity(intent);
            }
        });
    }

    public void addUser(View view)
    {
       // Toast.makeText(this,"Running", Toast.LENGTH_LONG).show();
        String t1 = Name.getText().toString();
        String t2 = Pass.getText().toString();
        long identity = helper.insertData(t1,t2);
        if(identity<0)
        {
            Message.message(context,"Unsuccessful");
        } else
        {
            Message.message(context,"Successful");
        }
    }
}
