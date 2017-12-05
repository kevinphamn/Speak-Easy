package com.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Random;


public class MyClass {

    public static void main(String[] args) {

            String webPage = createURL("MDIwMzIzMDkyMDE0NDA4MDM2MzJjMTYwZA001","text","story","&numResults=5","&format=JSON","1003");
            String result = returnJson(webPage);
            article toRead = readJson(result);
            int arraySize = Array.getLength(toRead.getList().getStory());

            int z = choseID(arraySize);
            String ID = toRead.getList().getStory()[z].getId();
            String newURL = createURL("MDIwMzIzMDkyMDE0NDA4MDM2MzJjMTYwZA001", null,null,null,"&format=JSON", ID);
            System.out.println(newURL);
            String chosen = returnJson(newURL);
            article read = readJson(chosen);
            System.out.println(read.getList().getStory()[0].getTitle().get$text());
            for(int i = 0; i < 4; i++) {
                System.out.println(read.getList().getStory()[0].getText().getParagraph()[i].get$text());
            }
    }

    public static int choseID(int number) {
        Random x = new Random();
        return x.nextInt(number);
    }

    public static String createURL(String apiKey, String requiredAssets, String dateType,
                                   String numResult, String format, String nprID) {

        if(numResult == null) {
            String webPage = "http://api.npr.org/query?id=" + nprID +"&apiKey=" + apiKey + format;
            return webPage;
        }
        else{
            String webPage = "http://api.npr.org/query?id=" + nprID + "&requiredAssets=" + requiredAssets + "&dateType=" + dateType + "&apiKey=" + apiKey + numResult + format;
            return webPage;
        }
    }

    public static String returnJson(String webPage) {
        String result = null;
        try {
            URL url = new URL(webPage);


            URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);

            int numCharsRead;
            char[] charArray = new char[1024];
            StringBuffer sb = new StringBuffer();

            while ((numCharsRead = isr.read(charArray)) > 0) {
                sb.append(charArray, 0, numCharsRead);

            }

            result = sb.toString();
            return result;

        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static article readJson(String result) {
        Gson x = new Gson();
        article toRead = x.fromJson(result, article.class);
        return toRead;
    }
}
