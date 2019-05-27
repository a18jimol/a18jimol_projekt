package com.example.a18jimol_projekt;

import android.content.ClipData;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> listData;
    private ArrayAdapter<Info> adapter;
    private String[] extraMessage;
    public static final String EXTRA_MESSAGE = "hejhej";
    public static final String EXTRA_MESSAGE2 = "hejhej2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Retro Spel Listan");
        new FetchData().execute();

        adapter = new ArrayAdapter<Info>(this, R.layout.list_item, R.id.list_item_textview);

        ListView my_listview = (ListView) findViewById(R.id.my_listview);
        my_listview.setAdapter(adapter);

        my_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // skaper en variabel som hämtar data från arrayerna efter värdet på i
                String temp = adapter.getItem(i).info();

                Intent intent = new Intent(getApplicationContext(), InfoDetails.class);

                String allt = adapter.getItem(i).info();
                String allt2 = adapter.getItem(i).title();
                intent.putExtra(EXTRA_MESSAGE,allt );
                intent.putExtra(EXTRA_MESSAGE2,allt2 );
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();


        if (id == R.id.action_refresh){
            adapter.clear();
            new FetchData().execute();
            return true;
        }

        if (id == R.id.action_about){
            Intent intent = new Intent(getApplicationContext(), InfoDetails.class);
            String abouttext = "Den här appen är byggd för att bevara information om gamla spelkonsoler. Appen är för både personer med ett stort intresse för spelkonsoler och de som vill lära sig något nytt.";
            intent.putExtra(EXTRA_MESSAGE,abouttext );
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private class FetchData extends AsyncTask<Void,Void,String> {
    @Override
    protected String doInBackground(Void... params) {
        // These two variables need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a Java string.
        String jsonStr = null;

        try {
            // Construct the URL for the Internet service
            URL url = new URL("http://wwwlab.iit.his.se/a18jimol/android/data.php");

            // Create the request to the PHP-service, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            jsonStr = buffer.toString();
            return jsonStr;
        } catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in
            // attempting to parse it.
            return null;
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("Network error", "Error closing stream", e);
                }
            }
        }
    }

    @Override
    protected void onPostExecute(String o) {
        super.onPostExecute(o);
        Log.d("brom","DataFetched:"+o);
        // This code executes after we have received our data. The String object o holds
        // the un-parsed JSON string or is null if we had an IOException during the fetch.
        try {

            JSONArray jimmyarray = new JSONArray(o);
            for(int i=0; i < jimmyarray.length(); i++) {
                Log.d("brom", "element 0:" + jimmyarray.get(i).toString());
                JSONObject jimmy1 = jimmyarray.getJSONObject(i);
                Log.d("brom",  jimmy1.getString("name"));
                Log.d("brom",  jimmy1.getString("location"));
                Log.d("brom",  ""+jimmy1.getInt("size"));
                Log.d("brom",  jimmy1.getString("company"));
                Log.d("brom",  ""+jimmy1.getInt("cost"));
                Log.d("brom",  jimmy1.getString("category"));
                //Log.d("brom",  n.toString());

                Info m = new Info(jimmy1.getString("name"),jimmy1.getString("location"),jimmy1.getInt("size"),jimmy1.getString("company"),jimmy1.getInt("cost"),jimmy1.getString("category") );
                Log.d("brom",  m.toString());
                adapter.add(m);
            }
        } catch (JSONException e) {
            Log.e("brom","E:"+e.getMessage());
        }

        // Implement a parsing code that loops through the entire JSON and creates objects
        // of our newly created Mountain class.
    }
}
}
