package com.example.usuario.xml.utils;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Xml;

import com.example.usuario.xml.R;
import com.example.usuario.xml.pojo.Noticia;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by usuario on 12/12/17.
 */

public class Analisis {
    public static final String TEXTO = "<texto><saludo>Hello World!</saludo><despedida>Goodbye</despedida></texto>";


    public static ArrayList<Noticia> analizarNoticias(File file) throws XmlPullParserException, IOException {
        int eventType;
        ArrayList<Noticia> noticias = new ArrayList<Noticia>();
        Noticia actual = null;

        boolean dentroItem = false;
        boolean dentroTitle = false;
        boolean dentroLink =false;
        boolean dentroDescription =false;
        boolean dentroPubDate = false;

        XmlPullParser xpp = Xml.newPullParser();
        xpp.setInput(new FileReader(file));
        eventType=xpp.getEventType();
        while (eventType!=XmlPullParser.END_DOCUMENT){
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if(xpp.getName().equals("item"))
                    {
                        dentroItem=true;
                        actual=new Noticia();
                    }
                    if(xpp.getName().equals("title"))
                    {
                        dentroTitle=true;
                    }
                    if(xpp.getName().equals("link"))
                    {
                        dentroLink=true;

                    }
                    if(xpp.getName().equals("description"))
                    {
                        dentroDescription=true;

                    }
                    if(xpp.getName().equals("pubDate"))
                    {
                        dentroPubDate=true;

                    }
                    break;

                case XmlPullParser.TEXT:
                    if(dentroItem)
                    {
                        if(dentroTitle)
                        {
                            actual.setTitle(xpp.getText());
                        }else if(dentroLink)
                        {
                            actual.setLink(xpp.getText());
                        }else if(dentroDescription)
                        {
                            actual.setDescription(xpp.getText());
                        }else if(dentroPubDate)
                        {
                            actual.setPubDate(xpp.getText());
                        }
                    }
                    break;

                case XmlPullParser.END_TAG:
                    if(xpp.getName().equals("item"))
                    {
                        dentroItem=false;
                        noticias.add(actual);
                    }
                    break;
            }
            eventType = xpp.next();
        }
        //devolver el array de noticias
        return noticias;
    }
    public static String analizarRSS(File file) throws NullPointerException, XmlPullParserException,
            IOException {
        boolean dentroItem = false;
        boolean dentroTitle = false;
        StringBuilder builder = new StringBuilder();
        XmlPullParser xpp = Xml.newPullParser();
        xpp.setInput(new FileReader(file));
        int eventType = xpp.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if(xpp.getName().equals("item"))
                    {
                        dentroItem=true;
                    }
                    if(dentroItem&&xpp.getName().equals("title"))
                    {
                        dentroTitle=true;
                    }
                    break;
                case XmlPullParser.TEXT:
                    if(dentroItem&&dentroTitle)
                    {
                        builder.append(xpp.getText()+"\n");
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if(xpp.getName().equals("item"))
                    {
                        dentroItem=false;
                    }
                    if(dentroItem&&xpp.getName().equals("title"))
                    {
                        dentroTitle=false;
                    }
                    break;
            }
            eventType = xpp.next();
        }
        return builder.toString();
    }
    public static String analizarNombres(Context c) throws XmlPullParserException,
            IOException {
        boolean esNombre = false;
        boolean esNota = false;
        StringBuilder cadena = new StringBuilder();
        Double suma = 0.0;
        int contador = 0;
        XmlResourceParser xrp = c.getResources().getXml(R.xml.alumnos);
        int eventType = xrp.getEventType();
        while (eventType != XmlPullParser. END_DOCUMENT ) {
            switch (eventType) {

                case XmlPullParser.START_TAG :

                            if(xrp.getName().equals("nombre")) {
                                cadena.append("Alumno: ");
                                esNombre=true;
                            }

                            if(xrp.getName().equals("nota"))
                            {
                                cadena.append("Asignatura: ");
                                cadena.append(xrp.getAttributeValue(null, "asignatura")+"\n");
                                cadena.append("Fecha: ");
                                cadena.append(xrp.getAttributeValue(null, "fecha")+"\n");
                                cadena.append("Nota: ");
                                esNota=true;
                            }

                    break;
                case XmlPullParser.TEXT :
                    if(esNombre) {
                        cadena.append(xrp.getText());

                    esNombre=false;
                    }else if(esNota){
                        contador++;
                        suma+=Double.parseDouble(xrp.getText());
                        cadena.append(xrp.getText());
                        esNota=false;
                    }
                    break;
                case XmlPullParser.END_TAG :
                        cadena.append("\n");
                    break;
            }
            eventType = xrp.next();
        }
        cadena.append("Media: "+String.format("%.2f",suma/contador));
        xrp.close();
        return cadena.toString();
    }

    public static String analizar(String texto) throws XmlPullParserException, IOException
    {
        StringBuilder cadena = new StringBuilder();
        XmlPullParser xpp = Xml.newPullParser();

        xpp.setInput( new StringReader(texto));
        int eventType = xpp.getEventType();
        cadena.append("Inicio . . . \n");
        while (eventType != XmlPullParser. END_DOCUMENT ) {
            if(eventType == XmlPullParser.START_DOCUMENT)
            {
                cadena.append("Inicio del documento\n");
            }else if(eventType == XmlPullParser.START_TAG)
            {
                cadena.append("Inicio del TAG: "+xpp.getName()+'\n');
            }else if(eventType== XmlPullParser.END_TAG)
            {
                cadena.append("Fin del TAG: "+xpp.getName()+'\n');
            }else if(eventType==XmlPullParser.TEXT)
            {
                cadena.append("TEXT: "+ xpp.getText()+'\n');
            }else if(eventType==XmlPullParser.COMMENT)
            {
                cadena.append("COMENTARIO: "+xpp.getText()+'\n');
            }
            eventType = xpp.next();
        }
        cadena.append("Fin del documento" + "\n" + "Fin");
        return cadena.toString();
    }
}
