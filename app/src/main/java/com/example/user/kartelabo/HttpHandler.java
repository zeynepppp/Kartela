package com.example.user.kartelabo;


//https://raw.githubusercontent.com/zeynepppp/deneme/master/renkler.json

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpHandler {

    public HttpHandler(){}

    public String makeServicesCall(String requestUrl){

        String responce=null;

        try {
            URL url= new URL(requestUrl);

            HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            InputStream in=new BufferedInputStream(httpURLConnection.getInputStream());
            responce=convertString(in);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responce;
    }


    public String convertString(InputStream is){
        BufferedReader reader= new BufferedReader(new InputStreamReader(is));
        StringBuilder sb=new StringBuilder();
        String satir="";

        try
        {
            while((satir=reader.readLine())!=null){
                sb.append(satir).append("\n");


        }
        }catch (IOException e){


        }finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        return sb.toString();
    }
}
