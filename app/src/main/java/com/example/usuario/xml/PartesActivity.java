package com.example.usuario.xml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.usuario.xml.utils.Analisis;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import static com.example.usuario.xml.utils.Analisis.TEXTO;

public class PartesActivity extends AppCompatActivity {

    TextView informacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partes);
        informacion= (TextView) findViewById(R.id.txvInformacion);

        try {
            informacion.setText(Analisis.analizar(TEXTO));
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            informacion.setText(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            informacion.setText(e.getMessage());
        }
    }
}
