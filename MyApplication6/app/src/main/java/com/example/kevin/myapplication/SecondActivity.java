package com.example.kevin.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Random;


public class SecondActivity extends ActionBarActivity implements View.OnClickListener{

    //private ArrayList<String> ideas = new ArrayList<String>();
    private String[] reroll = new String[5];
    private String[] views = new String[4];
    private TextView view;
    private TextView view1;
    private TextView view2;
    private TextView view3;
    private TextView view4;
    private TextView view5;
    String title=null;
    String URL=null;
    boolean canPressButton=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String topicNum = intent.getStringExtra("topicNum");
        ArrayList<String> resultArray = getIntent().getStringArrayListExtra("strings");
        String generalTopic=intent.getStringExtra("generalTopic");
        String URL=intent.getStringExtra("URL");

        setTitle(generalTopic);
        view = (TextView) findViewById(R.id.Title);
        view1 = (TextView) findViewById(R.id.text1);
        view2 = (TextView) findViewById(R.id.text2);
        view3 = (TextView) findViewById(R.id.text3);
        view4 = (TextView) findViewById(R.id.text4);
        view5 = (TextView) findViewById(R.id.text5);

        view.setText(title);
        for(int i=0;i<resultArray.size();i++) {
            views[i] = resultArray.get(i);
        }
        for(int j=resultArray.size();j<4;j++){
            views[j]="";
        }
        view1.setText(views[0]);
        view2.setText(views[1]);
        view3.setText(views[2]);
        view4.setText(views[3]);
        view5.setText(URL);

        /** Setting the Article texts to Black for easier reading
         */
        view1.setTextColor(Color.BLACK);
        view2.setTextColor(Color.BLACK);
        view4.setTextColor(Color.BLACK);
        view4.setTextColor(Color.BLACK);

        Button random = (Button)findViewById(R.id.randomButton);
        random.setOnClickListener(this);
    }

    public void onClick(View v) {

        canPressButton=false;
        Intent intent = getIntent();
        String topicNum = intent.getStringExtra("topicNum");
        myAsyncTask mTask = new myAsyncTask();
        mTask.execute(topicNum, title);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class myAsyncTask extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {


        }

        protected String doInBackground(String ... args) {

            String x = args[0];
            String title = args[1];

            String webPage = createURL("MDIwMzIzMDkyMDE0NDA4MDM2MzJjMTYwZA001","text","story","&numResults=5","&format=JSON",x);
            String result = returnJson(webPage);
            jsonToJavaClass toRead = readJson(result);
            int arraySize = Array.getLength(toRead.getList().getStory());

            int z = choseID(arraySize);
            String ID = toRead.getList().getStory()[z].getId();
            String newURL = createURL("MDIwMzIzMDkyMDE0NDA4MDM2MzJjMTYwZA001", null,null,null,"&format=JSON", ID);
            String chosen = returnJson(newURL);
            jsonToJavaClass read = readJson(chosen);
            //System.out.println(read.getList().getStory()[0].getTitle().get$text());
            title = read.getList().getStory()[0].getTitle().get$text();
            URL = read.getList().getStory()[0].getLink()[0].get$text();
            if(read.getList().getStory()[0].getText().getParagraph().length < 4) {
                for(int j=0; j<read.getList().getStory()[0].getText().getParagraph().length; j++) {
                    reroll[j] = read.getList().getStory()[0].getText().getParagraph()[j].get$text();
                }
                for(int k=read.getList().getStory()[0].getText().getParagraph().length; k<4; k++){
                    reroll[k]="";
                }
            }
            else {
                for (int i = 0; i < 4; i++) {
                    //Log.d("fixer",Integer.toString(ideas.length));
                    //System.out.println(read.getList().getStory()[0].getText().getParagraph()[i].get$text());
                    //ideas.add(read.getList().getStory()[0].getText().getParagraph()[i].get$text());
                    reroll[i] = read.getList().getStory()[0].getText().getParagraph()[i].get$text();

                }
            }
            return title;
        }

        public int choseID(int number) {
            Random x = new Random();
            return x.nextInt(number);
        }

        public String createURL(String apiKey, String requiredAssets, String dateType,
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

        public String returnJson(String webPage) {
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

        public jsonToJavaClass readJson(String result) {
            Gson x = new Gson();
            jsonToJavaClass toRead = x.fromJson(result, jsonToJavaClass.class);
            return toRead;
        }

        protected void onPostExecute(String title) {
            view.setText(title);

            /**view1.setText(ideas.get(0));
            view2.setText(ideas.get(1));
            view3.setText(ideas.get(2));
            view4.setText(ideas.get(3));**/
            view1.setText(reroll[0]);
            view2.setText(reroll[1]);
            view3.setText(reroll[2]);
            view4.setText(reroll[3]);
            view5.setText(URL);
            /** Setting the Article texts to Black for easier reading
             */
            view1.setTextColor(Color.BLACK);
            view2.setTextColor(Color.BLACK);
            view4.setTextColor(Color.BLACK);
            view4.setTextColor(Color.BLACK);
            //ideas.clear();
            canPressButton=true;
        }
    }

}
