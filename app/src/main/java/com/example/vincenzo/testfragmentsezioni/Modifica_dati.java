package com.example.vincenzo.testfragmentsezioni;

/**
 * Created by Vincenzo on 14/11/2016.
 */

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;


/**
 * Created by Vincenzo on 12/11/2016.
 */




//NOTA BENE. "QUESTA PAGINA HA UN PICCOLO BUG...OVVERO QUANDO INVII CON SUCCESSO TRAMITE MAIL LE NUOVE CREDENZIALI LA TUA APP CONTINUERà A RIPORTARE NOME PASSWORD E MAIL VECCHIE
    //Da risolvere!!

public class Modifica_dati extends AppCompatActivity {

    static String datopassato = "";
    static String risultato;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifica_dati);

        Bundle datipassati = getIntent().getExtras();
        datopassato  = datipassati.getString("MailUtente");
        starttask("http://technidos.altervista.org/PhpProject/preleva_dati.php?mail="+datopassato);

        final AlertDialog.Builder builder=new AlertDialog.Builder(this);

        final EditText textName = (EditText) findViewById(R.id.name);
        final EditText textSurname = (EditText) findViewById(R.id.surname);
        final EditText textMail = (EditText) findViewById(R.id.email);
        final EditText textCellulare = (EditText) findViewById(R.id.n_cellulare);
        final EditText textDataNascita = (EditText) findViewById(R.id.mod_dataNascita);
        final EditText textPass = (EditText) findViewById(R.id.pass_reg);
        final EditText textRepeatPass = (EditText) findViewById(R.id.rip_pass_reg);

        final TextView viewCellulare = (TextView) findViewById(R.id.cellulareView);
        final TextView viewDataNascita = (TextView) findViewById(R.id.modNascitaView);
        final TextView viewName = (TextView) findViewById(R.id.nameview);
        final TextView viewSurname = (TextView) findViewById(R.id.surnameview);
        final TextView viewMail = (TextView) findViewById(R.id.mailview);
        final TextView viewPassword = (TextView) findViewById(R.id.passwordview);
        final TextView viewNonLoUso = (TextView) findViewById(R.id.nonlouso);

        Button sButton = (Button) findViewById(R.id.submit_reg);
        TextView sButton2 = (TextView) findViewById(R.id.carica_dati);

        sButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewMail.getText().toString().equals("")){
                    viewMail.setText("Mail attuale: "+mail);
                    viewMail.setPadding(0,30,0,0);
                    viewName.setText("Nome attuale: "+name);
                    viewName.setPadding(0,30,0,0);

                    viewCellulare.setText("Numero attuale: "+ cellulare);
                    viewCellulare.setPadding(0,30,0,0);

                    viewDataNascita.setText("Data di nascita attuale: "+ dataNascitaVecchia);
                    viewDataNascita.setPadding(0,30,0,0);

                    viewPassword.setText("Password attuale: "+password);
                    viewPassword.setPadding(0,30,0,0);

                    viewNonLoUso.setPadding(0,30,0,0);

                    viewSurname.setText("Surname attuale: "+surname);
                    viewSurname.setPadding(0,30,0,0);
                }
                else{
                    viewMail.setText("");
                    viewMail.setPadding(0,0,0,0);

                    viewName.setText("");
                    viewName.setPadding(0,0,0,0);

                    viewCellulare.setText("");
                    viewCellulare.setPadding(0,0,0,0);

                    viewDataNascita.setText("");
                    viewDataNascita.setPadding(0,0,0,0);

                    viewPassword.setText("");
                    viewPassword.setPadding(0,0,0,0);

                    viewNonLoUso.setPadding(0,0,0,0);

                    viewSurname.setText("");
                    viewSurname.setPadding(0,0,0,0);

                }

            }
        });

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
                if(controllo_spazio)
                    if(textPass.getText().toString().equals("") || textSurname.getText().toString().equals("") || textMail.getText().toString().equals("")|| textPass.getText().toString().equals("")|| textRepeatPass.getText().toString().equals(""))
                        Toast.makeText(Modifica_dati.this, "Devi riempire tutti i campi per procedere!", Toast.LENGTH_SHORT).show();
                    else{
                        if(textPass.getText().toString().equals(textRepeatPass.getText().toString())) {
                            starttask("http://technidos.altervista.org/PhpProject/invia_mail_modifica_dati.php?name=" + textName.getText().toString() + "&surname=" + textSurname.getText().toString() + "&mail=" + textMail.getText().toString() + "&password=" + textPass.getText().toString()+ "&data_nascita=" + dataNascitaNuova + "&cellulare=" + textCellulare.getText().toString()+  "&mail_originale=" + datopassato);
                            Boolean bool = true;
                            flag = 0;
                            tempo_attesa = 0;
                            while(bool){
                                if(tempo_attesa == 1)
                                    break;
                            }
                            if(flag == 2){
                                Toast.makeText(Modifica_dati.this, "Controlla la tua Mail!!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else if(flag == 1)
                                Toast.makeText(Modifica_dati.this, "Mail già esistente!", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(Modifica_dati.this, "Errore! Mail non inviata!", Toast.LENGTH_SHORT).show();

                        }
                        else
                            Toast.makeText(Modifica_dati.this, "Le password inserite non corrispondo!!", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(Modifica_dati.this, "Non puoi inserire spazi vuoti.Per prosiguire ti invitiamo a rimuoverli e ricompilare i form.", Toast.LENGTH_SHORT).show();

            }
        });


        textDataNascita.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DialogFragment dialogfragment = new DatePickerDialogTheme1(textDataNascita);
                dialogfragment.show(getFragmentManager(), "Theme 1");
            }

        });
    }


    //////////////// Gestisco i dati per DatePicker
    public static String dataNascitaNuova;
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
            dataNascitaNuova = day + "/" + (month+1) + "/" + year;
        }
    }
///////////Finisco di gestire i dati per il DatePicker






    private static void starttask(String link) {
        new Modifica_dati.FileAsync(link).execute("start");
        //   Toast toast = Toast.makeText(Registrazione.this," start:",1);
        // toast.show();

    }


    static class FileAsync extends AsyncTask<Object, Object, JSONArray> {

        private String link;
        private String JSONData;

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
        protected JSONArray doInBackground(Object... params) {
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

            JSONArray jsa = null;

                try {
                    jsa = new JSONArray("[" + result + "]");
                    for (int i = 0; i < jsa.length(); i++) {
                        json_data = jsa.getJSONObject(i);
                    }
                    name = json_data.getString("name");
                    password = json_data.getString("password");
                    surname = json_data.getString("surname");
                    mail = json_data.getString("mail");
                    cellulare = json_data.getString("cellulare");
                    dataNascitaVecchia = json_data.getString("data_nascita");
                } catch (JSONException jsex) {
                    //Log.e("TAG_ERROR_TRANSMITTER", jsex.toString());
                    Log.e("TAG_ERROR_TRANSMITTER", jsex.toString() + "|" + JSONData + "|" + result); //riga per il debug
                }

            System.out.println(result);
            if(result.equals("Errore")){
                flag = 0;
            }
            else if(result.equals("Esiste")){
                flag = 1;
            }
            else {
                flag = 2;
            }

            tempo_attesa = 1;
            return jsa;
        }

        @Override
        protected void onProgressUpdate(Object... values) {
            super.onProgressUpdate(values);
            //updateProgressBar(values[0]);
        }

        @Override
        protected void onPostExecute(JSONArray result) {
            super.onPostExecute(result);

            // activity.finish();    funzionava!! l'ho semplicemente spostato per ragioni tecniche legate al TOAST
            //m_ProgressDialog.dismiss();
        }
    }

    private static JSONObject json_data = null;
    private static int flag = 0;
    private static String name;
    private static String surname;
    private static String mail;
    private static String password;
    private static String dataNascitaVecchia;
    private static String cellulare;
    private static int tempo_attesa = 0;

}
