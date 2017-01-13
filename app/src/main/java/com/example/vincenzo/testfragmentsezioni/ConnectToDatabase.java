package com.example.vincenzo.testfragmentsezioni;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Davide on 12/01/2017.
 */

public class ConnectToDatabase {

    public ConnectToDatabase(String link) {
        new FileAsync(link).execute("start");
    }

    static class FileAsync extends AsyncTask<Object, Object, String> {

        private String link;

        // ProgressDialog m_ProgressDialog;
        public FileAsync(String link) {
            this.link = link;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //displayProgressBar("Downloading...");
            // m_ProgressDialog = ProgressDialog.show(activity, "", "Loading ...", true);

        }

        @Override
        protected String doInBackground(Object... params) {
            String result = "";
            try {
                // CONNECT
                URL url = new URL(link);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //conn.setDoOutput(true);
                //conn.setRequestProperty("Accept-Charset", "UTF-8");
                conn.setRequestProperty("Content-Type", "text/html;charset=utf-8");
                //conn.setRequestProperty("User-Agent", USER_AGENT);
                conn.setRequestMethod("GET");
                conn.connect();
                // READ RESPONSE
                int r = conn.getResponseCode();
                Log.e("TAG_HEADER_URL_REQUEST", url.toString());
                Log.e("TAG_HEADER_KEY", conn.getHeaderFieldKey(2));
                Log.e("TAG_HEADER_DATA", conn.getHeaderField(2));
                if (r == 200) {
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    result += response.toString();
                } else {
                    result = "[{'ERROR':'Server " + r + "'}]";
                    Log.e("TAG_CONN", "x" + String.valueOf(r));
                }
                conn.disconnect();
            } catch (MalformedURLException malex) {
                result = "[{'ERROR':'Malformed URL'}]";
            } catch (IOException ioex) {
                result = "[{'ERROR':'IO Exception'}]";
            }
            System.out.println(result);

            if (result.equals("Error")) {
                flag = 0;//errore scrittura sul database
            } else {
                flag = 1;
            }

            if (flag == 1) {
                JSONArray jsa;
                try {
                    jsa = new JSONArray(result);
                    json_data = new JSONObject[jsa.length()];
                    for (int i = 0; i < jsa.length(); i++) {
                        json_data[i] = jsa.getJSONObject(i);
                    }
                } catch (JSONException jsex) {
                    Log.e("TAG_ERROR_TRANSMITTER", jsex.toString() + "|" + json_data + "|" + result); //riga per il debug
                }
            }
            tempo_attesa = true;
            return result;
        }

        @Override
        protected void onProgressUpdate(Object... values) {
            super.onProgressUpdate(values);
            //updateProgressBar(values[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            // activity.finish();    funzionava!! l'ho semplicemente spostato per ragioni tecniche legate al TOAST
            //m_ProgressDialog.dismiss();
        }
    }

    public int isConnected(){
        return flag;
    }

    public JSONObject[] getJsonData(){
        return json_data;
    }

    public boolean isReady(){
        return tempo_attesa;
    }

    private static int flag = 2;
    private static boolean tempo_attesa = false;
    private static JSONObject[]json_data = null;
}
