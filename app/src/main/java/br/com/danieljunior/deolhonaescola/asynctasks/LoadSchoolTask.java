package br.com.danieljunior.deolhonaescola.asynctasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import br.com.danieljunior.deolhonaescola.interfaces.LoadCallback;
import br.com.danieljunior.deolhonaescola.models.School;
import br.com.danieljunior.deolhonaescola.utils.JsonUtil;
import br.com.danieljunior.deolhonaescola.utils.UrlConstants;

/**
 * Created by danieljunior on 09/01/17.
 */

public class LoadSchoolTask extends AsyncTask<Object, Void, Void> {

    public static final int BY_NAME = 1;
    public static final int BY_EXPENSE = 2;
    public static final int BY_INCOME = 3;
    public static final int ALL = 4;

    LoadCallback callback;
    Context context;
    private ProgressDialog pDialog;
    int type;

    public LoadSchoolTask(Context context, LoadCallback activity, int type) {
        this.context = context;
        this.callback = activity;
        this.type = type;
    }

    @Override
    protected Void doInBackground(Object... params) {
        Object param = params[0];
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String url = getTypeUrl(param);

        ArrayList<School> listSchool = null;
        try {
            String response = makeRequest(url);
            if (response != null) {
                listSchool = new ArrayList<>();
                JSONArray jsonArr = new JSONArray(makeRequest(url));
                for (int i = 0; i < jsonArr.length(); i++) {
                    JSONObject jsonObject = jsonArr.getJSONObject(i);
                    School s = JsonUtil.mapperToSchool(jsonObject);
                    if (s != null) {
                        listSchool.add(JsonUtil.mapperToSchool(jsonObject));
                    }
                }
            }
        } catch (JSONException e) {
        } finally {
            callback.setSchoolList(listSchool);
            return null;
        }
    }

    private String getTypeUrl(Object param) {
        try {
            param = URLEncoder.encode((String) param, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        switch (type) {
            case BY_NAME:
                return UrlConstants.API_BASE_URL_DEVELOPMENT + "/by_name?name=" + param;
            case BY_EXPENSE:
                return UrlConstants.API_BASE_URL_DEVELOPMENT + "/by_expense?expense=" + param;
            case BY_INCOME:
                return UrlConstants.API_BASE_URL_DEVELOPMENT + "/by_income?income=" + param;
            case ALL:
                return UrlConstants.API_BASE_URL_DEVELOPMENT;
            default:
                return UrlConstants.API_BASE_URL_DEVELOPMENT;
        }
    }


    @Override
    protected void onPreExecute() {
        String message = "Realizando busca...";
        if (type == ALL) {
            message = "Carregando o mapa. Isto pode demorar um pouco...";
        }
        pDialog = ProgressDialog.show(context, "Aguarde...", message, true);
        pDialog.setCancelable(false);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        pDialog.dismiss();
        callback.postResult();
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
