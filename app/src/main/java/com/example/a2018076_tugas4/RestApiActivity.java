package com.example.a2018076_tugas4;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import
        com.example.a2018076_tugas4.databinding.ActivityRestApiBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class RestApiActivity extends AppCompatActivity implements
        View.OnClickListener {
    //declaration variable
    private ActivityRestApiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setup view binding
        binding =
                ActivityRestApiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.fetchButton.setOnClickListener(this);
    }

    //onclik button fetch
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fetch_button) {
//            index = binding.inputId.getText().toString();
            try {
                getData();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    //get data using api link
    public void getData() throws MalformedURLException {
        Uri uri = Uri.parse("https://masak-apa-tomorisakura.vercel.app/api/recipes/1")
                .buildUpon().build();
        URL url = new URL(uri.toString());
        new DOTask().execute(url);
    }

    class DOTask extends AsyncTask<URL, Void, String> {
        //connection request
        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];
            String data = null;
            try {
                data = NetworkUtils.makeHTTPRequest(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                parseJson(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //get data json
        public void parseJson(String data) throws JSONException {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSONArray receipeArray = jsonObject.getJSONArray("results");
            JSONObject obj = receipeArray.getJSONObject(0);
            String title = obj.get("title").toString();
            binding.resultId.setText(title);
            String times = obj.get("times").toString();
            binding.resultCreated.setText(times);
            String serving = obj.get("serving").toString();
            binding.resultUpdated.setText(serving);
        }
    }
}
