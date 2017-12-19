package com.example.usuario.xml;

import android.app.ProgressDialog;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.xml.utils.Analisis;
import com.example.usuario.xml.utils.RestClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;

import cz.msebera.android.httpclient.Header;

public class TitularesActivity extends AppCompatActivity implements View.OnClickListener {
   private static String URL="https://www.alejandrosuarez.es/feed";
    public static final String TEMPORAL = "alejandro.xml";
    Button boton;
    TextView informacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titulares);
        boton = (Button) findViewById(R.id.button);
        boton.setOnClickListener(this);
        informacion = (TextView) findViewById(R.id.txvInformacion);
    }

    @Override
    public void onClick(View v) {
        if (v == boton)
            descarga(URL, TEMPORAL);
    }

    private void descarga(String rss, String temporal) {
        final ProgressDialog progreso = new ProgressDialog(this);
        File miFichero = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), temporal);
        RestClient.get(rss, new FileAsyncHttpResponseHandler(miFichero) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                progreso.dismiss();
                Toast.makeText(TitularesActivity.this,throwable.getMessage(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                progreso.dismiss();
                Toast.makeText(TitularesActivity.this,"Fichero descargado correctamente",Toast.LENGTH_SHORT).show();
                try {
                    informacion.setText(Analisis.analizarRSS(file));
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                    Toast.makeText(TitularesActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(TitularesActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onStart() {
                super.onStart();
                progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progreso.setMessage("Conectando . . .");
                progreso.setCancelable(false);
                progreso.show();
            }
        });
    }
}
