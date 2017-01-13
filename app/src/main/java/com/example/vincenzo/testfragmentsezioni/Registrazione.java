package com.example.vincenzo.testfragmentsezioni;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import static java.security.AccessController.getContext;

/**
 * Created by Vincenzo on 12/11/2016.
 */

public class Registrazione extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrazione);

        final AlertDialog.Builder builder=new AlertDialog.Builder(this);

        final Spinner sesso = (Spinner) findViewById(R.id.sesso);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array._sesso, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sesso.setAdapter(adapter);

        final EditText textName = (EditText) findViewById(R.id.name);
        final EditText textSurname = (EditText) findViewById(R.id.surname);
        final EditText textMail = (EditText) findViewById(R.id.email);
        final EditText dataNascita = (EditText) findViewById(R.id.data_nascita);
        final EditText cellulare = (EditText) findViewById(R.id.cellulare);
        final EditText textPass = (EditText) findViewById(R.id.pass_reg);
        final EditText textRepeatPass = (EditText) findViewById(R.id.rip_pass_reg);
        Button sButton = (Button) findViewById(R.id.submit_reg);


        sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Questi for servono per controllare se ci sono spazi nelle stringhe
                boolean controllo_spazio = true;
                for (int j = 0; j < textName.getText().toString().length(); j++) {
                    if (textName.getText().toString().substring(j, j + 1).equals(" "))
                        controllo_spazio = false;
                }
                for (int j = 0; j < textSurname.getText().toString().length(); j++) {
                    if (textSurname.getText().toString().substring(j, j + 1).equals(" "))
                        controllo_spazio = false;
                }
                for (int j = 0; j < textMail.getText().toString().length(); j++) {
                    if (textMail.getText().toString().substring(j, j + 1).equals(" "))
                        controllo_spazio = false;
                }
                for (int j = 0; j < textPass.getText().toString().length(); j++) {
                    if (textPass.getText().toString().substring(j, j + 1).equals(" "))
                        controllo_spazio = false;
                }

                if (controllo_spazio) {
                    if (textPass.getText().toString().equals("") || textSurname.getText().toString().equals("") || textMail.getText().toString().equals("") || textPass.getText().toString().equals("") || textRepeatPass.getText().toString().equals("") || dataNascita.getText().toString().equals("") || cellulare.getText().toString().equals(""))
                        Toast.makeText(Registrazione.this, "Devi riempire tutti i camp per procedere!", Toast.LENGTH_SHORT).show();
                    else {
                        if (textPass.getText().toString().equals(textRepeatPass.getText().toString())) {
                            starttask("http://technidos.altervista.org/PhpProject/invia_Mail_Conferma.php?name=" + textName.getText().toString() + "&surname=" + textSurname.getText().toString() + "&mail=" + textMail.getText().toString() + "&password=" + textPass.getText().toString() + "&data_nascita=" + data_nascita + "&cellulare=" + cellulare.getText().toString() + "&sesso=" + (String) sesso.getSelectedItem());
                            flag = 0;
                            tempo_attesa = 0;
                            Boolean bool = true;
                            while (bool) {
                                if (tempo_attesa == 1)
                                    break;
                            }

                            if (flag == 2) {
                                Toast.makeText(Registrazione.this, "Controlla la tua Mail!!", Toast.LENGTH_SHORT).show();
                                finish();
                            } else if (flag == 1) {
                                Toast.makeText(Registrazione.this, "Mail già esiste, inseriscine un'altra!", Toast.LENGTH_SHORT).show();
                                flag = 0;
                            } else
                                Toast.makeText(Registrazione.this, "Mail non inviata!Riprova!", Toast.LENGTH_SHORT).show();

                            tempo_attesa = 0;
                        } else
                            Toast.makeText(Registrazione.this, "Le password inserite non corrispondo!!", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    Toast.makeText(Registrazione.this, "Non puoi inserire spazi vuoti.Per prosiguire ti invitiamo a rimuoverli e ricompilare i form.", Toast.LENGTH_SHORT).show();
            }
        });

        //Per inserire la data richiamo una classe interna per attivare il datepicker
        dataNascita.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DialogFragment dialogfragment = new DatePickerDialogTheme1(dataNascita);
                dialogfragment.show(getFragmentManager(), "Theme 1");
            }

        });
    }


    //////////////// Gestisco i dati per DatePicker
    public static String data_nascita;
    public static class DatePickerDialogTheme1 extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        EditText editText;
        public DatePickerDialogTheme1(EditText dataNascita){
            editText = dataNascita;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(),
                    R.style.MyDialogTheme,this,year,month,day);

            return datepickerdialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day){
            editText.setText("Data:  "+day + "/" + (month+1) + "/" + year);
            data_nascita = day + "/" + (month+1) + "/" + year;
        }
    }
///////////Finisco di gestire i dati per il DatePicker




    private static void starttask(String link) {
        new Registrazione.FileAsync(link).execute("start");
        //   Toast toast = Toast.makeText(Registrazione.this," start:",1);
        // toast.show();

    }


    static class FileAsync extends AsyncTask<Object, Object, String> {

        private String link;
        // ProgressDialog m_ProgressDialog;
        public FileAsync(String link){

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
                System.out.println("sto qua!");
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

            if (result.equals("Esiste")){
                flag = 1;
            }
            else if(result.equals("errore nell’invio dell’e-mail!")){
                flag = 0;
            }
            else
                flag = 2;

            tempo_attesa = 1;
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

               // activity.finish();    funzionava!! l'ho semplicemente spostato per ragioni tecniche legate al TOAST  //NOn badate a ciò erano cose iniziali xD
            //m_ProgressDialog.dismiss();
        }
    }

    private static int flag = 0;
    private static int tempo_attesa = 0;
}
