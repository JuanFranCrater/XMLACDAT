package com.example.usuario.xml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.usuario.xml.utils.Analisis;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class NotasActivity extends AppCompatActivity {

    TextView informacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        informacion= (TextView) findViewById(R.id.txvInformacion);

        try {
            informacion.setText(Analisis.analizarNombres(this));
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            informacion.setText(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            informacion.setText(e.getMessage());
        }
    }
}
