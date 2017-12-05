package com.example.kevin.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.sababado.circularview.CircularView;
import com.sababado.circularview.Marker;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends ActionBarActivity {

    private ArrayList<String> ideas = new ArrayList<String>();
    String title=null;
    String topicNum=null;
    String generalTopic=null;
    String URL=null;
    boolean canPressButton=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyCircularViewAdapter mAdapter = new MyCircularViewAdapter();
        CircularView circularView = (CircularView) findViewById(R.id.circular_view);
        circularView.setAdapter(mAdapter);


        //Random change

        circularView.setOnCircularViewObjectClickListener(new CircularView.OnClickListener(){
            public void onClick(final CircularView view){
            }
            public void onMarkerClick(CircularView view, Marker marker, int position){
                if(canPressButton) {
                    canPressButton=false;
                    switch (marker.getId()) {
                        case 1:
                            topicNum = "1003";
                            generalTopic = "US";
                            break;
                        case 2:
                            topicNum = "1004";
                            generalTopic = "World";
                            break;
                        case 3:
                            topicNum = "1014";
                            generalTopic = "Politics";
                            break;
                        case 4:
                            topicNum = "1006";
                            generalTopic = "Business";
                            break;
                        case 5:
                            topicNum = "1019";
                            generalTopic = "Technology";
                            break;

                        case 6:
                            topicNum = "1007";
                            generalTopic = "Science";
                            break;
                        case 7:
                            topicNum = "1128";
                            generalTopic = "Health";
                            break;
                        case 8:
                            topicNum = "1015";
                            generalTopic = "Race/Culture";
                            break;
                        case 9:
                            topicNum = "1013";
                            generalTopic = "Education";
                            break;
                        default:
                            break;
                    }

                    myAsyncTask mTask = new myAsyncTask();
                    mTask.execute(topicNum);
                }
            }
        });
    }


     /**   Button topicUS = (Button)findViewById(R.id.topicUS);
        Button topicWorld = (Button)findViewById(R.id.topicWorld);
        Button topicPolitics = (Button)findViewById(R.id.topicPolitics);
        Button topicBusiness = (Button)findViewById(R.id.topicBusiness);
        Button topicTechnology = (Button)findViewById(R.id.topicTechnology);
        Button topicScience = (Button)findViewById(R.id.topicScience);
        Button topicHealth = (Button)findViewById(R.id.topicHealth);
        Button topicRace = (Button)findViewById(R.id.topicRace);
        Button topicEducation = (Button)findViewById(R.id.topicEducation);

        topicUS.setOnClickListener(this);
        topicWorld.setOnClickListener(this);
        topicPolitics.setOnClickListener(this);
        topicBusiness.setOnClickListener(this);
        topicTechnology.setOnClickListener(this);
        topicScience.setOnClickListener(this);
        topicHealth.setOnClickListener(this);
        topicRace.setOnClickListener(this);
        topicEducation.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.topicUS:
                topicNum="1003";
                generalTopic="US";
                break;
            case R.id.topicWorld:
                topicNum="1004";
                generalTopic="World";
                break;
            case R.id.topicPolitics:
                topicNum="1014";
                generalTopic="Politics";
                break;
            case R.id.topicBusiness:
                topicNum="1006";
                generalTopic="Business";
                break;
            case R.id.topicTechnology:
                topicNum="1019";
                generalTopic="Technology";
                break;

            case R.id.topicScience:
                topicNum="1007";
                generalTopic="Science";
                break;
            case R.id.topicHealth:
                topicNum="1128";
                generalTopic="Health";
                break;
            case R.id.topicRace:
                topicNum="1015";
                generalTopic="Race/Culture";
                break;
            case R.id.topicEducation:
                topicNum="1013";
                generalTopic="Education";
                break;
            default:
                break;
        }
        myAsyncTask mTask = new myAsyncTask();
        mTask.execute(topicNum, title);

    }**/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

            ideas.clear();

        }

        protected String doInBackground(String ... args) {

            String x = args[0];

            String webPage = createURL("MDIwMzIzMDkyMDE0NDA4MDM2MzJjMTYwZA001", "text", "story", "&numResults=5", "&format=JSON", x);
            String result = returnJson(webPage);
            jsonToJavaClass toRead = readJson(result);
            int arraySize = Array.getLength(toRead.getList().getStory());

            int z = choseID(arraySize);
            String ID = toRead.getList().getStory()[z].getId();
            String newURL = createURL("MDIwMzIzMDkyMDE0NDA4MDM2MzJjMTYwZA001", null, null, null, "&format=JSON", ID);
            String chosen = returnJson(newURL);
            jsonToJavaClass read = readJson(chosen);
            //System.out.println(read.getList().getStory()[0].getTitle().get$text());
            title = read.getList().getStory()[0].getTitle().get$text();
            URL = read.getList().getStory()[0].getLink()[0].get$text();
            if(read.getList().getStory()[0].getText().getParagraph().length < 4) {
                for(int j=0; j<read.getList().getStory()[0].getText().getParagraph().length; j++) {
                    ideas.add(read.getList().getStory()[0].getText().getParagraph()[j].get$text());
                }
            }
            else {
                for (int i = 0; i < 4; i++) {
                    //System.out.println(read.getList().getStory()[0].getText().getParagraph()[i].get$text());
                    ideas.add(read.getList().getStory()[0].getText().getParagraph()[i].get$text());
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
            canPressButton = true;
            Intent i= new Intent(MainActivity.this,SecondActivity.class);
            i.putExtra("title", title);
            i.putStringArrayListExtra("strings", ideas);
            i.putExtra("topicNum", topicNum);
            i.putExtra("generalTopic",generalTopic);
            i.putExtra("URL",URL);
            startActivity(i);

        }
    }


}
