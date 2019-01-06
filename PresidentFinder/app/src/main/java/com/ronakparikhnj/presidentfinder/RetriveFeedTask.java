package com.ronakparikhnj.presidentfinder;

import android.os.AsyncTask;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.File;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.ClarifaiResponse;
import clarifai2.dto.input.ClarifaiInput;



class RetrieveFeedTask extends AsyncTask<String, Void, String> {

    static byte[] data2;
    private Exception exception;
    static String kp;

    protected String doInBackground(String... urls) {
        try {
            // TODO Auto-generated method stub
            ClarifaiClient b = new ClarifaiBuilder("e2ea135ffff146f6a25ed06b6f70b780").buildSync();
            ClarifaiResponse c = (b.getDefaultModels().demographicsModel().predict().withInputs(ClarifaiInput.forImage(data2)).executeSync());
            String d = c.rawBody();
            String[] v = d.split("concepts");
            String[]kz = new String[3];
            for(int i = 1;i<v.length;i++)
                kz[i-1]=(v[i].substring(31,v[i].indexOf("\"",31)));
            double ffff = Integer.parseInt(kz[0])/100.0;
                    if(kz[2].equals("white"))ffff*=2.0;  //this is all arbitrary
            if(kz[1].equals("feminine")||(Integer.parseInt(kz[0]) <= 34)){ //Not trying to be sexist, but no woman in the history of the US has been president, please prove this condition wrong! also kz[0] is the age and you have to be 35+ to be pres
                ffff=0;
            }
            String bbb = ("You look like a "+kz[0]+" year old "+ kz[2]+" "+(kz[1].equals("feminine")?"female":"male")+" so you have a "+((ffff>1)?"very high":(ffff>0.75)?"high":(ffff>0.5)?"medium":(ffff>0.25)?"low":"very low")+" chance of becoming president of the United States by today's standards.");
            kp = bbb;
           return bbb;
        } catch (Exception e) {
            this.exception = e;
            return null;
        }
    }


}