package br.com.danieljunior.deolhonaescola.asynctasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.github.mikephil.charting.data.BarEntry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.danieljunior.deolhonaescola.interfaces.LoadSchoolCallback;
import br.com.danieljunior.deolhonaescola.interfaces.LoadStatisticCallback;
import br.com.danieljunior.deolhonaescola.models.CustomBarEntry;

/**
 * Created by danieljunior on 09/01/17.
 */

public class LoadStatisticsTask extends AsyncTask<Void, Void, Void> {

    Context context;
    LoadStatisticCallback callback;
    private ProgressDialog pDialog;

    public LoadStatisticsTask(Context context, LoadStatisticCallback activity) {
        this.context = context;
        this.callback = activity;
    }

    @Override
    protected Void doInBackground(Void... params) {
//        Object param = params[0];
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//
//        String url = getTypeUrl(param);
//
//        ArrayList<School> listSchool = null;
//        try {
//            String response = makeRequest(url);
//            if (response != null) {
//                listSchool = new ArrayList<>();
//                JSONArray jsonArr = new JSONArray(makeRequest(url));
//                for (int i = 0; i < jsonArr.length(); i++) {
//                    JSONObject jsonObject = jsonArr.getJSONObject(i);
//                    School s = JsonUtil.mapperToSchool(jsonObject);
//                    if (s != null) {
//                        listSchool.add(JsonUtil.mapperToSchool(jsonObject));
//                    }
//                }
//            }
//        } catch (JSONException e) {
//        } finally {
//            callback.setSchoolList(listSchool);
//        }
        ArrayList<CustomBarEntry> entries;
        entries = new ArrayList<>();
        entries.add(new CustomBarEntry(300, 1000));
        entries.add(new CustomBarEntry(200, 2000));
        entries.add(new CustomBarEntry(100, 3000));
        callback.setBarEntries(entries);
        return null;
    }


    @Override
    protected void onPreExecute() {
        String message = "Carregando dados...";
        pDialog = ProgressDialog.show(context, "Aguarde...", message, true);
        pDialog.setCancelable(false);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        callback.postStatisticResult();
        pDialog.dismiss();
    }

    private String makeRequest(String urlAddress) {
        HttpURLConnection con = null;
        URL url = null;
        String response = null;
        try {
            url = new URL(urlAddress);
            con = (HttpURLConnection) url.openConnection();
            response = readStream(con.getInputStream());

        } catch (Exception e) {

        } finally {
            con.disconnect();
        }
        return response;
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }
}
