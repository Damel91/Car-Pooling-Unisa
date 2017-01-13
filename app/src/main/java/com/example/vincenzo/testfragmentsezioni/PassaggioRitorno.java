package com.example.vincenzo.testfragmentsezioni;

/**
 * Created by Vincenzo on 14/11/2016.
 */

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
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

public class PassaggioRitorno extends AppCompatActivity {

    static String datopassato = "";
    static String risultato;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passaggio_ritorno);

        Bundle datipassati = getIntent().getExtras();
        final int cod_passaggio  = datipassati.getInt("Cod_passaggio");
        final int num_tappe  = datipassati.getInt("numero_tappe");


        final LinearLayout LLVO1 = (LinearLayout) findViewById(R.id.vertical1);
        final LinearLayout LLVO2 = (LinearLayout) findViewById(R.id.vertical2);
        final LinearLayout LLVO3 = (LinearLayout) findViewById(R.id.vertical3);
        final LinearLayout LLVO4 = (LinearLayout) findViewById(R.id.vertical4);
        final LinearLayout LLVO5 = (LinearLayout) findViewById(R.id.vertical5);
        final LinearLayout LLVO6 = (LinearLayout) findViewById(R.id.vertical6);
        final LinearLayout LLVO7 = (LinearLayout) findViewById(R.id.vertical7);
        final LinearLayout LLVO8 = (LinearLayout) findViewById(R.id.vertical8);

        final LinearLayout LLSpaziatore1 = (LinearLayout) findViewById(R.id.separa_layout1);
        final LinearLayout LLSpaziatore2 = (LinearLayout) findViewById(R.id.separa_layout2);
        final LinearLayout LLSpaziatore3 = (LinearLayout) findViewById(R.id.separa_layout3);
        final LinearLayout LLSpaziatore4 = (LinearLayout) findViewById(R.id.separa_layout4);
        final LinearLayout LLSpaziatore5 = (LinearLayout) findViewById(R.id.separa_layout5);
        final LinearLayout LLSpaziatore6 = (LinearLayout) findViewById(R.id.separa_layout6);
        final LinearLayout LLSpaziatore7 = (LinearLayout) findViewById(R.id.separa_layout7);


        final EditText orario[] = new EditText[8];
        final TextView citta[] = new TextView[8];
        final TextView[] arraySpaziatore = new TextView[8];


        final EditText ora_da_fisciano = (EditText) findViewById(R.id.orario_partenza);

        final Button sSubmit = (Button) findViewById(R.id.submit_offri_passaggio_ritorno);
        final Button sDelete = (Button) findViewById(R.id.delete_offri_passaggio_ritorno);


        final int arrayIdTappa[] = new int [num_tappe];
        final String arrayCitta[] = new String [num_tappe];

        for(int i = 0; i < num_tappe; i++){
            switch(i){
                case 0: arrayIdTappa[i] = datipassati.getInt("id_tappa1");break;
                case 1: arrayIdTappa[i] = datipassati.getInt("id_tappa2");break;
                case 2: arrayIdTappa[i] = datipassati.getInt("id_tappa3");break;
                case 3: arrayIdTappa[i] = datipassati.getInt("id_tappa4");break;
                case 4: arrayIdTappa[i] = datipassati.getInt("id_tappa5");break;
                case 5: arrayIdTappa[i] = datipassati.getInt("id_tappa6");break;
                case 6: arrayIdTappa[i] = datipassati.getInt("id_tappa7");break;
                case 7: arrayIdTappa[i] = datipassati.getInt("id_tappa8");break;
            }
            System.out.println("Tappa"+arrayIdTappa[i]);
        }

        for(int i = 0; i < num_tappe; i++){
            switch(i){
                case 0: arrayCitta[i] = datipassati.getString("citta1");break;
                case 1: arrayCitta[i] = datipassati.getString("citta2");break;
                case 2: arrayCitta[i] = datipassati.getString("citta3");break;
                case 3: arrayCitta[i] = datipassati.getString("citta4");break;
                case 4: arrayCitta[i] = datipassati.getString("citta5");break;
                case 5: arrayCitta[i] = datipassati.getString("citta6");break;
                case 6: arrayCitta[i] = datipassati.getString("citta7");break;
                case 7: arrayCitta[i] = datipassati.getString("citta8");break;
            }
            System.out.println("Citta"+arrayIdTappa[i]);
        }


        for (int i = 0; i < 8; i++) {
            citta[i] = new TextView(PassaggioRitorno.this);
            citta[i].setWidth(2000);
            citta[i].setPadding(5, 0, 0, 20);
            citta[i].setTextSize(15);
            orario[i] = new EditText(PassaggioRitorno.this);
            orario[i].setHint("Orario di arrivo..");
            orario[i].setWidth(2000);
            orario[i].setPadding(5, 0, 0, 20);
            orario[i].setTextSize(15);
            orario[i].setFocusable(false);
            arraySpaziatore[i] = new TextView(PassaggioRitorno.this);
            arraySpaziatore[i].setHeight(60);
        }


        for(int i = 0; i < num_tappe; i++){
            citta[i].setText( "Destinazione: "+arrayCitta[num_tappe-i-1]);
        }

        if (num_tappe == 1) {
            LLVO1.addView(citta[0]);
            LLVO1.addView(orario[0]);
            LLSpaziatore1.addView(arraySpaziatore[0]);
        }

        if (num_tappe == 2) {
            LLVO1.addView(citta[0]);
            LLVO1.addView(orario[0]);
            LLSpaziatore1.addView(arraySpaziatore[0]);
            LLVO2.addView(citta[1]);
            LLVO2.addView(orario[1]);
            LLSpaziatore2.addView(arraySpaziatore[1]);
        }

        if (num_tappe == 3) {
            LLVO1.addView(citta[0]);
            LLVO1.addView(orario[0]);
            LLSpaziatore1.addView(arraySpaziatore[0]);
            LLVO2.addView(citta[1]);
            LLVO2.addView(orario[1]);
            LLSpaziatore2.addView(arraySpaziatore[1]);
            LLVO3.addView(citta[2]);
            LLVO3.addView(orario[2]);
            LLSpaziatore3.addView(arraySpaziatore[2]);
        }

        if (num_tappe == 4) {
            LLVO1.addView(citta[0]);
            LLVO1.addView(orario[0]);
            LLSpaziatore1.addView(arraySpaziatore[0]);
            LLVO2.addView(citta[1]);
            LLVO2.addView(orario[1]);
            LLSpaziatore2.addView(arraySpaziatore[1]);
            LLVO3.addView(citta[2]);
            LLVO3.addView(orario[2]);
            LLSpaziatore3.addView(arraySpaziatore[2]);
            LLVO4.addView(citta[3]);
            LLVO4.addView(orario[3]);
            LLSpaziatore4.addView(arraySpaziatore[3]);
        }

        if (num_tappe == 5) {
            LLVO1.addView(citta[0]);
            LLVO1.addView(orario[0]);
            LLSpaziatore1.addView(arraySpaziatore[0]);
            LLVO2.addView(citta[1]);
            LLVO2.addView(orario[1]);
            LLSpaziatore2.addView(arraySpaziatore[1]);
            LLVO3.addView(citta[2]);
            LLVO3.addView(orario[2]);
            LLSpaziatore3.addView(arraySpaziatore[2]);
            LLVO4.addView(citta[3]);
            LLVO4.addView(orario[3]);
            LLSpaziatore4.addView(arraySpaziatore[3]);
            LLVO5.addView(citta[4]);
            LLVO5.addView(orario[4]);
            LLSpaziatore5.addView(arraySpaziatore[4]);
        }

        if (num_tappe == 6) {
            LLVO1.addView(citta[0]);
            LLVO1.addView(orario[0]);
            LLSpaziatore1.addView(arraySpaziatore[0]);
            LLVO2.addView(citta[1]);
            LLVO2.addView(orario[1]);
            LLSpaziatore2.addView(arraySpaziatore[1]);
            LLVO3.addView(citta[2]);
            LLVO3.addView(orario[2]);
            LLSpaziatore3.addView(arraySpaziatore[2]);
            LLVO4.addView(citta[3]);
            LLVO4.addView(orario[3]);
            LLSpaziatore4.addView(arraySpaziatore[3]);
            LLVO5.addView(citta[4]);
            LLVO5.addView(orario[4]);
            LLSpaziatore5.addView(arraySpaziatore[4]);
            LLVO4.addView(citta[5]);
            LLVO4.addView(orario[5]);
            LLSpaziatore6.addView(arraySpaziatore[5]);
        }

        if (num_tappe == 7) {
            LLVO1.addView(citta[0]);
            LLVO1.addView(orario[0]);
            LLSpaziatore1.addView(arraySpaziatore[0]);
            LLVO2.addView(citta[1]);
            LLVO2.addView(orario[1]);
            LLSpaziatore2.addView(arraySpaziatore[1]);
            LLVO3.addView(citta[2]);
            LLVO3.addView(orario[2]);
            LLSpaziatore3.addView(arraySpaziatore[2]);
            LLVO4.addView(citta[3]);
            LLVO4.addView(orario[3]);
            LLSpaziatore4.addView(arraySpaziatore[3]);
            LLVO5.addView(citta[4]);
            LLVO5.addView(orario[4]);
            LLSpaziatore5.addView(arraySpaziatore[4]);
            LLVO4.addView(citta[5]);
            LLVO4.addView(orario[5]);
            LLSpaziatore6.addView(arraySpaziatore[5]);
            LLVO7.addView(citta[6]);
            LLVO7.addView(orario[6]);
            LLSpaziatore7.addView(arraySpaziatore[6]);
        }

        if (num_tappe == 8) {
            LLVO1.addView(citta[0]);
            LLVO1.addView(orario[0]);
            LLSpaziatore1.addView(arraySpaziatore[0]);
            LLVO2.addView(citta[1]);
            LLVO2.addView(orario[1]);
            LLSpaziatore2.addView(arraySpaziatore[1]);
            LLVO3.addView(citta[2]);
            LLVO3.addView(orario[2]);
            LLSpaziatore3.addView(arraySpaziatore[2]);
            LLVO4.addView(citta[3]);
            LLVO4.addView(orario[3]);
            LLSpaziatore4.addView(arraySpaziatore[3]);
            LLVO5.addView(citta[4]);
            LLVO5.addView(orario[4]);
            LLSpaziatore5.addView(arraySpaziatore[4]);
            LLVO4.addView(citta[5]);
            LLVO4.addView(orario[5]);
            LLSpaziatore6.addView(arraySpaziatore[5]);
            LLVO7.addView(citta[6]);
            LLVO7.addView(orario[6]);
            LLSpaziatore7.addView(arraySpaziatore[6]);
            LLVO8.addView(citta[7]);
            LLVO8.addView(orario[7]);
        }


        ///////////////
        // Attivo un listener per ogni bottone creato dinamicamente.......PS...Non penso sia il modo più consono di operare ma funziona
        orario[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DialogFragment newFragment = new PassaggioRitorno.TimePickerFragmentFermate(orario[0]);
                newFragment.show(getFragmentManager(), "TimePicker");
            }
        });

        orario[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DialogFragment newFragment = new PassaggioRitorno.TimePickerFragmentFermate(orario[1]);      ////Richiamo la classe interna all'activity mandandogli il text[] da aggiornare con l orario apposito
                newFragment.show(getFragmentManager(), "TimePicker");
            }
        });
        orario[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DialogFragment newFragment = new PassaggioRitorno.TimePickerFragmentFermate(orario[2]);       ////Richiamo la classe interna all'activity mandandogli il text[] da aggiornare con l orario apposito
                newFragment.show(getFragmentManager(), "TimePicker");
            }
        });
        orario[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DialogFragment newFragment = new PassaggioRitorno.TimePickerFragmentFermate(orario[3]);    ////Richiamo la classe interna all'activity mandandogli il text[] da aggiornare con l orario apposito
                newFragment.show(getFragmentManager(), "TimePicker");
            }
        });
        orario[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DialogFragment newFragment = new PassaggioRitorno.TimePickerFragmentFermate(orario[4]);    ////Richiamo la classe interna all'activity mandandogli il text[] da aggiornare con l orario apposito
                newFragment.show(getFragmentManager(), "TimePicker");
            }
        });
        orario[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DialogFragment newFragment = new PassaggioRitorno.TimePickerFragmentFermate(orario[5]);    ////Richiamo la classe interna all'activity mandandogli il text[] da aggiornare con l orario apposito
                newFragment.show(getFragmentManager(), "TimePicker");
            }
        });
        orario[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DialogFragment newFragment = new PassaggioRitorno.TimePickerFragmentFermate(orario[6]);    ////Richiamo la classe interna all'activity mandandogli il text[] da aggiornare con l orario apposito
                newFragment.show(getFragmentManager(), "TimePicker");
            }
        });
        orario[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DialogFragment newFragment = new PassaggioRitorno.TimePickerFragmentFermate(orario[7]);    ////Richiamo la classe interna all'activity mandandogli il text[] da aggiornare con l orario apposito
                newFragment.show(getFragmentManager(), "TimePicker");
            }
        });
        ora_da_fisciano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DialogFragment newFragment = new PassaggioRitorno.TimePickerFragmentFermate(ora_da_fisciano);    ////Richiamo la classe interna all'activity mandandogli il text[] da aggiornare con l orario apposito
                newFragment.show(getFragmentManager(), "TimePicker");
            }
        });



        ///////////SUBMIT

        sSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean controllo_campo_vuoto = true;

                //Questi sono i controlli degli elementi dinamici

                //questi for controllano che nelle varie stringhe non vi sia alcuno spazio


                //Questi if controllano se i vari campi dinamici sono vuoti
                for(int i = 0; i < num_tappe;i++){
                    if(i == 0)
                        if (orario[0].getText().toString().equals(""))
                            controllo_campo_vuoto = false;
                    if(i == 1)
                        if (orario[1].getText().toString().equals(""))
                            controllo_campo_vuoto = false;
                    if(i == 2)
                        if (orario[2].getText().toString().equals(""))
                            controllo_campo_vuoto = false;
                    if(i == 3)
                        if (orario[3].getText().toString().equals(""))
                            controllo_campo_vuoto = false;
                    if(i == 4)
                        if (orario[4].getText().toString().equals(""))
                            controllo_campo_vuoto = false;
                    if(i == 5)
                        if (orario[5].getText().toString().equals(""))
                            controllo_campo_vuoto = false;
                    if(i == 6)
                        if (orario[6].getText().toString().equals(""))
                            controllo_campo_vuoto = false;
                    if(i == 7)
                        if (orario[7].getText().toString().equals(""))
                            controllo_campo_vuoto = false;
                }
                if(ora_da_fisciano.getText().toString().equals(""))
                    controllo_campo_vuoto = false;

                // se non vi sono ne spazi ne campi vuoti possiamo proseguire
                if (controllo_campo_vuoto) {

                    String ora = null;

                    for(int i = 0; i < num_tappe; i++){
                        switch(i){
                            case 0: ora = orario[0].getText().toString();break;
                            case 1: ora = orario[1].getText().toString();break;
                            case 2: ora = orario[2].getText().toString();break;
                            case 3: ora = orario[3].getText().toString();break;
                            case 4: ora = orario[4].getText().toString();break;
                            case 5: ora = orario[5].getText().toString();break;
                            case 6: ora = orario[6].getText().toString();break;
                            case 7: ora = orario[7].getText().toString();break;
                        }
                        starttask("http://technidos.altervista.org/PhpProject/passaggio_ritorno.php?id="+arrayIdTappa[num_tappe-i-1]+"&ora_ritorno="+ora);
                        flag = 0;
                        tempo_attesa = 0;
                        Boolean bool = true;
                        while (bool) {
                            if (tempo_attesa == 1)
                                break;
                        }
                    }
                    starttask("http://technidos.altervista.org/PhpProject/orario_ritorno_fisciano.php?cod_passaggio="+cod_passaggio+"&ora_partenza_fisciano="+ora_da_fisciano.getText().toString());
                    flag = 0;
                    tempo_attesa = 0;
                    Boolean bool = true;
                    while (bool) {
                        if (tempo_attesa == 1)
                            break;
                    }
                    finish();
                    Toast.makeText(PassaggioRitorno.this, "Passaggi di ritorno inseriti!", Toast.LENGTH_SHORT).show();

                } else
                    Toast.makeText(PassaggioRitorno.this, "Non puoi lasciare campi vuoti!", Toast.LENGTH_SHORT).show();
            }
        });

        sDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }


///////////////////// Gestisco i TimePicker dinamici

    public class TimePickerFragmentFermate extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
        TextView text;
        public TimePickerFragmentFermate(TextView text){
            this.text = text;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            //Use the current time as the default values for the time picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            //Create and return a new instance of TimePickerDialog
            return new TimePickerDialog(getActivity(),this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        //onTimeSet() callback method
        public void onTimeSet(TimePicker view, int hourOfDay, int minute){
            text.setText( String.valueOf(hourOfDay) + "-" + String.valueOf(minute) + "\n");
        }
    }



    private static void starttask(String link) {
        new PassaggioRitorno.FileAsync(link).execute("start");
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

            System.out.println(result);
            if(result.equals("Errore")){
                flag = 0;
            }
            else {
                flag = 1;
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

    private static int flag = 0;

    private static int tempo_attesa = 0;

}

