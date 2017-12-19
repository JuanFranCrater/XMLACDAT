package com.example.usuario.xml;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1,btn2,btn3,btn4,btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2=findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        btn3=findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
        btn4=findViewById(R.id.btn4);
        btn4.setOnClickListener(this);
        btn5=findViewById(R.id.btn5);
        btn5.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view==btn1)
        {
        startActivity(new Intent(MainActivity.this,PartesActivity.class));
        }
        if(view==btn2)
        {
            startActivity(new Intent(MainActivity.this,NotasActivity.class));
        }
        if(view==btn3)
        {
            startActivity(new Intent(MainActivity.this,TitularesActivity.class));
        }
        if(view==btn4)
        {

        }
        if(view==btn5)
        {

        }
    }
}
