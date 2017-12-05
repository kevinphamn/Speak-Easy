package com.example.kevin.myapplication;
        import android.util.Log;

        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.net.URLConnection;
/**
 * Created by Hao on 9/2/2015.
 */
public class trash {

        public static void main(String[] args){
            try{
                String apiKey = "MDIwMzIzMDkyMDE0NDA4MDM2MzJjMTYwZA001";
                String numResult ="&numResults=1";
                String format ="&format=JSON";
                String nprID ="1003";
                String webPage = "http://api.npr.org/query?id="+nprID+"&apiKey="+apiKey+numResult+format;

                System.out.print(webPage);
                URL url = new URL(webPage);


                URLConnection urlConnection = url.openConnection();
                InputStream is = urlConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);

                int numCharsRead;
                char[] charArray = new char[1024];
                StringBuffer sb = new StringBuffer();

                while ((numCharsRead = isr.read(charArray)) > 0) {
                    sb.append(charArray,0,numCharsRead);

                }

                String result = sb.toString();

                Log.d("hao",result);

//                System.out.println(result.getClass().getName());

                //String indexedResult = result["list"]["title"]["$text"]

//			System.out.println("***Begin ***");
//			System.out.println(result);
//			System.out.println("***End***");

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }

        }
    }

