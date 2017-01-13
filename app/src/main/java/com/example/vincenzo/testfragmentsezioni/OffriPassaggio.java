package com.example.vincenzo.testfragmentsezioni;

/**
 * Created by Vincenzo on 17/11/2016.
 */

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AnalogClock;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.Date;

import static java.security.AccessController.getContext;

/**
 * Created by Vincenzo on 12/11/2016.
 */

public class OffriPassaggio extends AppCompatActivity {

    static int i = 0;
    static int num_offerte = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle datipassati = getIntent().getExtras();
        final String mailUtente = datipassati.getString("MailUtente");
        num_offerte  = datipassati.getInt("NumOfferte");
        if(num_offerte >= 8){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.pagenotfound);
        }
        else{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.app_bar_offri_passaggio);
            i=0;
            num_offerte = 0;
            //titolo toolbar
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_offripassaggio);
            setSupportActionBar(toolbar);




        /*final EditText partenze = (EditText) findViewById(R.id.partenze);
        final EditText via = (EditText) findViewById(R.id.via);
        final EditText civico = (EditText) findViewById(R.id.civico);
        final TextView orario = (TextView) findViewById(R.id.TextViewOraio2);*/
            final EditText auto = (EditText) findViewById(R.id.auto);
            final TextView data = (TextView) findViewById(R.id.dataview);


            final Spinner colore = (Spinner) findViewById(R.id.colori);
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array._color, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            colore.setAdapter(adapter2);


            final Spinner posti = (Spinner) findViewById(R.id.num_posti);
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array._posti_disponibili, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            posti.setAdapter(adapter3);


            ImageButton sButton = (ImageButton) findViewById(R.id.button_calendar);

            Button sSubmit = (Button) findViewById(R.id.submit_offri_passaggio);

            final LinearLayout LL2 = (LinearLayout) findViewById(R.id.LayoutDinamico);
        /*final LinearLayout LLV1= (LinearLayout) findViewById(R.id.LayoutDinamicoVertical1);
        final LinearLayout LLV2= (LinearLayout) findViewById(R.id.LayoutDinamicoVertical2);*/

            final LinearLayout LLDO1 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale1);
            final LinearLayout LLDO2 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale2);
            final LinearLayout LLDO3 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale3);
            final LinearLayout LLDO4 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale4);
            final LinearLayout LLDO5 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale5);
            final LinearLayout LLDO6 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale6);
            final LinearLayout LLDO7 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale7);
            final LinearLayout LLDO8 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale8);
            final LinearLayout LLDO9 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale9);
            final LinearLayout LLDO10 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale10);
            final LinearLayout LLDO11 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale11);
            final LinearLayout LLDO12 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale12);
            final LinearLayout LLDO13 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale13);
            final LinearLayout LLDO14 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale14);
            final LinearLayout LLDO15 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale15);
            final LinearLayout LLDO16 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale16);
            final LinearLayout LLDO17 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale17);
            final LinearLayout LLDO18 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale18);
            final LinearLayout LLDO19 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale19);
            final LinearLayout LLDO20 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale20);
            final LinearLayout LLDO21 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale21);
            final LinearLayout LLDO22 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale22);
            final LinearLayout LLDO23 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale23);
            final LinearLayout LLDO24 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale24);
            final LinearLayout LLDO25 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale25);
            final LinearLayout LLDO26 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale26);
            final LinearLayout LLDO27 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale27);
            final LinearLayout LLDO28 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale28);
            final LinearLayout LLDO29 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale29);
            final LinearLayout LLDO30 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale30);
            final LinearLayout LLDO31 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale31);
            final LinearLayout LLDO32 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale32);
            final LinearLayout LLDO33 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale33);
            final LinearLayout LLDO34 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale34);
            final LinearLayout LLDO35 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale35);
            final LinearLayout LLDO36 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale36);
            final LinearLayout LLDO37 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale37);
            final LinearLayout LLDO38 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale38);
            final LinearLayout LLDO39 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale39);
            final LinearLayout LLDO40 = (LinearLayout) findViewById(R.id.LayoutDinamicoOrizontale40);


            final LinearLayout separatore1 = (LinearLayout) findViewById(R.id.separatore1);
            final LinearLayout separatore2 = (LinearLayout) findViewById(R.id.separatore2);
            final LinearLayout separatore3 = (LinearLayout) findViewById(R.id.separatore3);
            final LinearLayout separatore4 = (LinearLayout) findViewById(R.id.separatore4);
            final LinearLayout separatore5 = (LinearLayout) findViewById(R.id.separatore5);
            final LinearLayout separatore6 = (LinearLayout) findViewById(R.id.separatore6);
            final LinearLayout separatore7 = (LinearLayout) findViewById(R.id.separatore7);

            final LinearLayout Tappa1 = (LinearLayout) findViewById(R.id.tappa1);
            final LinearLayout Tappa2 = (LinearLayout) findViewById(R.id.tappa2);
            final LinearLayout Tappa3 = (LinearLayout) findViewById(R.id.tappa3);
            final LinearLayout Tappa4 = (LinearLayout) findViewById(R.id.tappa4);
            final LinearLayout Tappa5 = (LinearLayout) findViewById(R.id.tappa5);
            final LinearLayout Tappa6 = (LinearLayout) findViewById(R.id.tappa6);
            final LinearLayout Tappa7 = (LinearLayout) findViewById(R.id.tappa7);
            final LinearLayout Tappa8 = (LinearLayout) findViewById(R.id.tappa8);


            final String[] selectedItemColor = new String[1];  //Lo converte in array di stringhe ANDROID STUDIO ..perchè basterebbe un sola variabile
            final String[] selectedItemPosti = new String[1];  //Lo converte in array di stringhe ANDROID STUDIO ..perchè basterebbe un sola variabile


            final EditText citta[] = new EditText[8];
            final EditText via[] = new EditText[8];
            final EditText civico[] = new EditText[8];
            final EditText ora[] = new EditText[8];
            final EditText prezzo[] = new EditText[8];
            final TextView[] arrayTextCitta = new TextView[8];
            final TextView[] arrayTextVia = new TextView[8];
            final TextView[] arrayTextCivico = new TextView[8];
            final TextView[] arrayTextPrezzo = new TextView[8];
            final TextView[] arrayTextOrario = new TextView[8];
            final TextView[] arraySpaziatore = new TextView[8];
            final TextView[] arrayTestoTappe = new TextView[8];


            colore.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {

                    //se array
                    //selectedItem=apparecchi[position];

                    //se Lista
                    //selectedItem=apparecchi.get(position);
                    selectedItemColor[0] = (String) colore.getSelectedItem();
                    if (selectedItemColor[0].equals("Rosso"))
                        colore.setBackgroundColor(Color.RED);
                    if (selectedItemColor[0].equals("Magenta"))
                        colore.setBackgroundColor(Color.MAGENTA);
                    if (selectedItemColor[0].equals("Verde"))
                        colore.setBackgroundColor(Color.GREEN);
                    if (selectedItemColor[0].equals("Blu"))
                        colore.setBackgroundColor(Color.BLUE);
                    if (selectedItemColor[0].equals("Giallo"))
                        colore.setBackgroundColor(Color.YELLOW);
                    if (selectedItemColor[0].equals("Grigio"))
                        colore.setBackgroundColor(Color.GRAY);
                    if (selectedItemColor[0].equals("Nero"))
                        colore.setBackgroundColor(Color.BLACK);
                    if (selectedItemColor[0].equals("Bianco"))
                        colore.setBackgroundColor(Color.WHITE);

                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                }
            });

            posti.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                    selectedItemPosti[0] = (String) posti.getSelectedItem();
                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                }
            });


            sButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    DialogFragment dialogfragment = new DatePickerDialogTheme1();
                    dialogfragment.show(getFragmentManager(), "Theme 1");
                }

            });

       /* sButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(),"TimePicker");
            }

        });*/


            sSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (i == 0) {
                        Toast.makeText(OffriPassaggio.this, "Devi inserire almeno una fermata!\nPuoi aggiungere/eliminare una fermata con i tasti posti in basso!", Toast.LENGTH_SHORT).show();
                    } else {
                        int k = i - 1;
                        if (auto.getText().toString().equals("") || data.getText().toString().equals("") || selectedItemColor[0].equals("") || selectedItemPosti[0].equals(""))
                            Toast.makeText(OffriPassaggio.this, "Devi riempire tutti i campi per procedere!", Toast.LENGTH_SHORT).show();
                        else {

                            //String ora = orario.getText().toString();

                            boolean controllo_spazio = true;
                            boolean controllo_campo_vuoto = true;
                            for (int j = 0; j < auto.getText().toString().length(); j++) {
                                if (auto.getText().toString().substring(j, j + 1).equals(" "))
                                    controllo_spazio = false;
                            }


                            if (auto.getText().toString().equals(""))    // lo lascio fuori il for in quanto è un controllo singolo di un elemenento (AUTO) creato staticamente
                                controllo_campo_vuoto = false;


                            //Questi sono i controlli degli elementi dinamici
                            for (int a = 0; a < i; a++) {

                                //questi for controllano che nelle varie stringhe non vi sia alcuno spazio
                                for (int j = 0; j < citta[a].getText().toString().length(); j++) {
                                    if (citta[a].getText().toString().substring(j, j + 1).equals(" "))
                                        controllo_spazio = false;
                                }
                                for (int j = 0; j < via[a].getText().toString().length(); j++) {
                                    if (via[a].getText().toString().substring(j, j + 1).equals(" "))
                                        controllo_spazio = false;
                                }

                                //Questi if controllano se i vari campi dinamici sono vuoti
                                if (citta[a].getText().toString().equals(""))
                                    controllo_campo_vuoto = false;

                                if (via[a].getText().toString().equals(""))
                                    controllo_campo_vuoto = false;

                                if (civico[a].getText().toString().equals(""))
                                    controllo_campo_vuoto = false;

                                if (prezzo[a].getText().toString().equals(""))
                                    controllo_campo_vuoto = false;

                                if (ora[a].getText().toString().equals(""))
                                    controllo_campo_vuoto = false;
                            }

                            // se non vi sono ne spazi ne campi vuoti possiamo proseguire
                            if (controllo_spazio && controllo_campo_vuoto) {

                                starttask("http://technidos.altervista.org/PhpProject/offriPassaggio.php?mail=" + mailUtente + "&data=" + data.getText().toString() + "&auto=" + auto.getText().toString() + "&colore=" + selectedItemColor[0] + "&posti=" + selectedItemPosti[0]);

                                flag = 0;
                                tempo_attesa = 0;
                                Boolean bool = true;
                                while (bool) {
                                    if (tempo_attesa == 1)
                                        break;
                                }

                                for (int z = 0; z < i; z++) {
                                    starttaskTappa("http://technidos.altervista.org/PhpProject/tappa.php?cod_passaggio=" + codice_passaggio + "&citta=" + citta[z].getText().toString() + "&prezzo=" + prezzo[z].getText().toString() + "&via=" + via[z].getText().toString() + "&civico=" + civico[z].getText().toString() + "&ora_andata=" + ora[z].getText().toString(),z);
                                    bool = true;
                                    while (bool) {
                                        if (controlloTappe > z) {
                                            break;
                                        } else if (controlloErroriTappe.equals("Error")) {
                                            break;
                                        }
                                    }
                                    if (controlloErroriTappe.equals("Error")) {
                                        break;
                                    }
                                }
                                if (controlloErroriTappe.equals("OK")) {
                                    Toast.makeText(OffriPassaggio.this, "Inserimento avvenuto con successo!", Toast.LENGTH_SHORT).show();
                                    AreaUtente.num_offerte_calcolato++; ///aggiungo il numero delle offerte fatte per gestire meglio gli aggiornamenti
                                    switch (i){

                                        case 1:
                                            starttask("http://technidos.altervista.org/PhpProject/inviaMailDati.php?mail=" + mailUtente + "&cod_passaggio="+ codice_passaggio+ "&data=" + data.getText().toString() + "&auto=" + auto.getText().toString() + "&colore=" + selectedItemColor[0] + "&posti=" + selectedItemPosti[0]+"&tappa1="+citta[0].getText().toString()+"&tappa2=nessuna"+"&tappa3=nessuna"+"&tappa4=nessuna"+"&tappa5=nessuna"+"&tappa6=nessuna"+"&tappa7=nessuna"+"&tappa8=nessuna");
                                            break;//Invio i dati alla nuova intentbreak;
                                        case 2:
                                            starttask("http://technidos.altervista.org/PhpProject/inviaMailDati.php?mail=" + mailUtente + "&cod_passaggio="+ codice_passaggio+ "&data=" + data.getText().toString() + "&auto=" + auto.getText().toString() + "&colore=" + selectedItemColor[0] + "&posti=" + selectedItemPosti[0]+"&tappa1="+citta[0].getText().toString()+"&tappa2="+citta[1].getText().toString()+"&tappa3=nessuna"+"&tappa4=nessuna"+"&tappa5=nessuna"+"&tappa6=nessuna"+"&tappa7=nessuna"+"&tappa8=nessuna");
                                            break;//Invio i dati alla nuova intentbreak;
                                        case 3:
                                            starttask("http://technidos.altervista.org/PhpProject/inviaMailDati.php?mail=" + mailUtente + "&cod_passaggio="+ codice_passaggio+ "&data=" + data.getText().toString() + "&auto=" + auto.getText().toString() + "&colore=" + selectedItemColor[0] + "&posti=" + selectedItemPosti[0]+"&tappa1="+citta[0].getText().toString()+"&tappa2="+citta[1].getText().toString()+"&tappa3="+citta[2].getText().toString()+"&tappa4=nessuna"+"&tappa5=nessuna"+"&tappa6=nessuna"+"&tappa7=nessuna"+"&tappa8=nessuna");
                                            break;//Invio i dati alla nuova intentbreak;
                                        case 4:
                                            starttask("http://technidos.altervista.org/PhpProject/inviaMailDati.php?mail=" + mailUtente + "&cod_passaggio="+ codice_passaggio+ "&data=" + data.getText().toString() + "&auto=" + auto.getText().toString() + "&colore=" + selectedItemColor[0] + "&posti=" + selectedItemPosti[0]+"&tappa1="+citta[0].getText().toString()+"&tappa2="+citta[1].getText().toString()+"&tappa3="+citta[2].getText().toString()+"&tappa4="+citta[3].getText().toString()+"&tappa5=nessuna"+"&tappa6=nessuna"+"&tappa7=nessuna"+"&tappa8=nessuna");

                                            break;//Invio i dati alla nuova intentbreak;
                                        case 5:
                                            starttask("http://technidos.altervista.org/PhpProject/inviaMailDati.php?mail=" + mailUtente + "&cod_passaggio="+ codice_passaggio+ "&data=" + data.getText().toString() + "&auto=" + auto.getText().toString() + "&colore=" + selectedItemColor[0] + "&posti=" + selectedItemPosti[0]+"&tappa1="+citta[0].getText().toString()+"&tappa2="+citta[1].getText().toString()+"&tappa3="+citta[2].getText().toString()+"&tappa4="+citta[3].getText().toString()+"&tappa5="+citta[4].getText().toString()+"&tappa6=nessuna"+"&tappa7=nessuna"+"&tappa8=nessuna");

                                            break;//Invio i dati alla nuova intentbreak;
                                        case 6:
                                            starttask("http://technidos.altervista.org/PhpProject/inviaMailDati.php?mail=" + mailUtente + "&cod_passaggio="+ codice_passaggio+ "&data=" + data.getText().toString() + "&auto=" + auto.getText().toString() + "&colore=" + selectedItemColor[0] + "&posti=" + selectedItemPosti[0]+"&tappa1="+citta[0].getText().toString()+"&tappa2="+citta[1].getText().toString()+"&tappa3="+citta[2].getText().toString()+"&tappa4="+citta[3].getText().toString()+"&tappa5="+citta[4].getText().toString()+"&tappa6="+citta[5].getText().toString()+"&tappa7=nessuna"+"&tappa8=nessuna");

                                            break;//Invio i dati alla nuova intentbreak;
                                        case 7:
                                            starttask("http://technidos.altervista.org/PhpProject/inviaMailDati.php?mail=" + mailUtente + "&cod_passaggio="+ codice_passaggio+ "&data=" + data.getText().toString() + "&auto=" + auto.getText().toString() + "&colore=" + selectedItemColor[0] + "&posti=" + selectedItemPosti[0]+"&tappa1="+citta[0].getText().toString()+"&tappa2="+citta[1].getText().toString()+"&tappa3="+citta[2].getText().toString()+"&tappa4="+citta[3].getText().toString()+"&tappa5="+citta[4].getText().toString()+"&tappa6="+citta[5].getText().toString()+"&tappa7="+citta[6].getText().toString()+"&tappa8=nessuna");
                                            break;//Invio i dati alla nuova intentbreak;
                                        case 8:
                                            starttask("http://technidos.altervista.org/PhpProject/inviaMailDati.php?mail=" + mailUtente + "&cod_passaggio="+ codice_passaggio+ "&data=" + data.getText().toString() + "&auto=" + auto.getText().toString() + "&colore=" + selectedItemColor[0] + "&posti=" + selectedItemPosti[0]+"&tappa1="+citta[0].getText().toString()+"&tappa2="+citta[1].getText().toString()+"&tappa3="+citta[2].getText().toString()+"&tappa4="+citta[3].getText().toString()+"&tappa5="+citta[4].getText().toString()+"&tappa6="+citta[5].getText().toString()+"&tappa7="+citta[6].getText().toString()+"&tappa8="+citta[7].getText().toString());
                                            break;//Invio i dati alla nuova intentbreak;

                                    }

                                    Intent intent = new Intent(
                                            OffriPassaggio.this,
                                            PassaggioRitorno.class
                                    );

                                    intent.putExtra("Cod_passaggio", codice_passaggio); //Invio i dati alla nuova intent
                                    intent.putExtra("numero_tappe", i); //Invio i dati alla nuova intent

                                    for(int z = 0; z < i ; z++) {
                                        switch(z){
                                            case 0: intent.putExtra("id_tappa1", id_tappa[z]);
                                                intent.putExtra("citta1", citta[z].getText().toString());
                                                break;
                                            case 1: intent.putExtra("id_tappa2", id_tappa[z]);
                                                intent.putExtra("citta2", citta[z].getText().toString());
                                                break;//Invio i dati alla nuova intentbreak;
                                            case 2: intent.putExtra("id_tappa3", id_tappa[z]);
                                                intent.putExtra("citta3", citta[z].getText().toString());
                                                break;//Invio i dati alla nuova intentbreak;
                                            case 3: intent.putExtra("id_tappa4", id_tappa[z]);
                                                intent.putExtra("citta4", citta[z].getText().toString());
                                                break;//Invio i dati alla nuova intentbreak;
                                            case 4: intent.putExtra("id_tappa5", id_tappa[z]);
                                                intent.putExtra("citta5", citta[z].getText().toString());
                                                break;//Invio i dati alla nuova intentbreak;
                                            case 5: intent.putExtra("id_tappa6", id_tappa[z]);
                                                intent.putExtra("citta6", citta[z].getText().toString());
                                                break;//Invio i dati alla nuova intentbreak;
                                            case 6: intent.putExtra("id_tappa7", id_tappa[z]);
                                                intent.putExtra("citta7", citta[z].getText().toString());
                                                break;//Invio i dati alla nuova intentbreak;
                                            case 7: intent.putExtra("id_tappa8", id_tappa[z]);
                                                intent.putExtra("citta8", citta[z].getText().toString());
                                                break;//Invio i dati alla nuova intentbreak;
                                        }
                                    }


                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Toast.makeText(OffriPassaggio.this, "Errore nell'inserimento delle fermate!", Toast.LENGTH_SHORT).show();
                                    //Inserire un controllo nel caso non andasse a buon fine
                                }
                            } else
                                Toast.makeText(OffriPassaggio.this, "Non puoi inserire ne spazi ne lasciare campi vuoti!", Toast.LENGTH_SHORT).show();

                        }
                    }

                }
            });


//////////////////////////////////////////////////////////////
            /////////////////////////////////////////////////////
            /////
            //  Inizio a gestire le varie opzioni dinamiche ..

            ///Dichiaro 3 vettori di tipo..BUTTON, EDIT TEXT e TEXTVIEW

            //Inizializzo per scelta obligata adesso tutti gli oggetti.....li visuallizzerò in seguito
            for (int i = 0; i < 8; i++) {
                int k = i + 1;
                citta[i] = new EditText(OffriPassaggio.this);
                citta[i].setWidth(2000);
                citta[i].setPadding(5, 0, 0, 20);
                citta[i].setTextSize(15);

                via[i] = new EditText(OffriPassaggio.this);
                via[i].setWidth(2000);
                via[i].setPadding(5, 0, 0, 20);
                via[i].setTextSize(15);

                civico[i] = new EditText(OffriPassaggio.this);
                civico[i].setWidth(2000);
                civico[i].setPadding(5, 0, 0, 20);
                civico[i].setTextSize(15);

                prezzo[i] = new EditText(OffriPassaggio.this);
                prezzo[i].setWidth(2000);
                prezzo[i].setPadding(5, 0, 0, 20);
                prezzo[i].setTextSize(15);

                ora[i] = new EditText(OffriPassaggio.this);
                ora[i].setWidth(2000);
                ora[i].setPadding(5, 0, 0, 20);
                ora[i].setTextSize(15);
                ora[i].setFocusable(false);

                arrayTextCitta[i] = new TextView(OffriPassaggio.this);
                arrayTextCitta[i].setText("Città   ");
                arrayTextVia[i] = new TextView(OffriPassaggio.this);
                arrayTextVia[i].setText("Viale   ");
                arrayTextCivico[i] = new TextView(OffriPassaggio.this);
                arrayTextCivico[i].setText("Civico ");
                arrayTextPrezzo[i] = new TextView(OffriPassaggio.this);
                arrayTextPrezzo[i].setText("Prezzo");
                arrayTextOrario[i] = new TextView(OffriPassaggio.this);
                arrayTextOrario[i].setText("Orario ");
                arraySpaziatore[i] = new TextView(OffriPassaggio.this);
                arraySpaziatore[i].setHeight(60);
                arrayTestoTappe[i] = new TextView(OffriPassaggio.this);
                switch (i) {
                    case 0:
                        arrayTestoTappe[i].setText("Prima Tappa");
                        break;
                    case 1:
                        arrayTestoTappe[i].setText("Seconda Tappa");
                        break;
                    case 2:
                        arrayTestoTappe[i].setText("Terza Tappa");
                        break;
                    case 3:
                        arrayTestoTappe[i].setText("Quarta Tappa");
                        break;
                    case 4:
                        arrayTestoTappe[i].setText("Quinta Tappa");
                        break;
                    case 5:
                        arrayTestoTappe[i].setText("Sesta Tappa");
                        break;
                    case 6:
                        arrayTestoTappe[i].setText("Settima Tappa");
                        break;
                    case 7:
                        arrayTestoTappe[i].setText("Ottava Tappa");
                        break;
                }
                arrayTestoTappe[i].setTextSize(24);

            }


            //Bottoncino rotondo
            FloatingActionButton fabPiu = (FloatingActionButton) findViewById(R.id.fab_offriPassaggioPiu);
            fabPiu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (i == 8) {
                        Snackbar.make(view, "Non puoi inserire più di 8 fermate per passaggio!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else {
                        String k = String.valueOf(i + 1);        //Semplicemente converto in stringa la i
                        citta[i].setId(i);//Aggiungo un id alla editText
                        via[i].setId(i);
                        civico[i].setId(i);
                        civico[i].setInputType(InputType.TYPE_CLASS_NUMBER);    // setto il civico come un number , pertanto mi uscirà la tastiera adatta a gestire SOLO numeri.
                        prezzo[i].setId(i);
                        prezzo[i].setInputType(InputType.TYPE_CLASS_NUMBER);    // setto il prezzo come un number , pertanto mi uscirà la tastiera adatta a gestire SOLO numeri.
                        ora[i].setId(i);                               //Setto L'ID del textVIEW


                        if (i == 0) {

                            LLDO1.addView(arrayTextCitta[i]);
                            LLDO1.addView(citta[i]);
                            LLDO2.addView(arrayTextVia[i]);
                            LLDO2.addView(via[i]);
                            LLDO3.addView(arrayTextCivico[i]);
                            LLDO3.addView(civico[i]);
                            LLDO4.addView(arrayTextOrario[i]);
                            LLDO4.addView(ora[i]);
                            LLDO5.addView(arrayTextPrezzo[i]);
                            LLDO5.addView(prezzo[i]);
                            separatore1.addView(arraySpaziatore[i]);
                            Tappa1.addView(arrayTestoTappe[i]);
                        }
                        if (i == 1) {

                            LLDO6.addView(arrayTextCitta[i]);
                            LLDO6.addView(citta[i]);
                            LLDO7.addView(arrayTextVia[i]);
                            LLDO7.addView(via[i]);
                            LLDO8.addView(arrayTextCivico[i]);
                            LLDO8.addView(civico[i]);
                            LLDO9.addView(arrayTextOrario[i]);
                            LLDO9.addView(ora[i]);
                            LLDO10.addView(arrayTextPrezzo[i]);
                            LLDO10.addView(prezzo[i]);
                            separatore2.addView(arraySpaziatore[i]);
                            Tappa2.addView(arrayTestoTappe[i]);


                        }
                        if (i == 2) {

                            LLDO11.addView(arrayTextCitta[i]);
                            LLDO11.addView(citta[i]);
                            LLDO12.addView(arrayTextVia[i]);
                            LLDO12.addView(via[i]);
                            LLDO13.addView(arrayTextCivico[i]);
                            LLDO13.addView(civico[i]);
                            LLDO14.addView(arrayTextOrario[i]);
                            LLDO14.addView(ora[i]);
                            LLDO15.addView(arrayTextPrezzo[i]);
                            LLDO15.addView(prezzo[i]);
                            separatore3.addView(arraySpaziatore[i]);
                            Tappa3.addView(arrayTestoTappe[i]);

                        }
                        if (i == 3) {

                            LLDO16.addView(arrayTextCitta[i]);
                            LLDO16.addView(citta[i]);
                            LLDO17.addView(arrayTextVia[i]);
                            LLDO17.addView(via[i]);
                            LLDO18.addView(arrayTextCivico[i]);
                            LLDO18.addView(civico[i]);
                            LLDO19.addView(arrayTextOrario[i]);
                            LLDO19.addView(ora[i]);
                            LLDO20.addView(arrayTextPrezzo[i]);
                            LLDO20.addView(prezzo[i]);
                            separatore4.addView(arraySpaziatore[i]);
                            Tappa4.addView(arrayTestoTappe[i]);

                        }
                        if (i == 4) {

                            LLDO21.addView(arrayTextCitta[i]);
                            LLDO21.addView(citta[i]);
                            LLDO22.addView(arrayTextVia[i]);
                            LLDO22.addView(via[i]);
                            LLDO23.addView(arrayTextCivico[i]);
                            LLDO23.addView(civico[i]);
                            LLDO24.addView(arrayTextOrario[i]);
                            LLDO24.addView(ora[i]);
                            LLDO25.addView(arrayTextPrezzo[i]);
                            LLDO25.addView(prezzo[i]);
                            separatore5.addView(arraySpaziatore[i]);
                            Tappa5.addView(arrayTestoTappe[i]);

                        }
                        if (i == 5) {

                            LLDO26.addView(arrayTextCitta[i]);
                            LLDO26.addView(citta[i]);
                            LLDO27.addView(arrayTextVia[i]);
                            LLDO27.addView(via[i]);
                            LLDO28.addView(arrayTextCivico[i]);
                            LLDO28.addView(civico[i]);
                            LLDO29.addView(arrayTextOrario[i]);
                            LLDO29.addView(ora[i]);
                            LLDO30.addView(arrayTextPrezzo[i]);
                            LLDO30.addView(prezzo[i]);
                            separatore6.addView(arraySpaziatore[i]);
                            Tappa6.addView(arrayTestoTappe[i]);

                        }
                        if (i == 6) {

                            LLDO31.addView(arrayTextCitta[i]);
                            LLDO31.addView(citta[i]);
                            LLDO32.addView(arrayTextVia[i]);
                            LLDO32.addView(via[i]);
                            LLDO33.addView(arrayTextCivico[i]);
                            LLDO33.addView(civico[i]);
                            LLDO34.addView(arrayTextOrario[i]);
                            LLDO34.addView(ora[i]);
                            LLDO35.addView(arrayTextPrezzo[i]);
                            LLDO35.addView(prezzo[i]);
                            separatore7.addView(arraySpaziatore[i]);
                            Tappa7.addView(arrayTestoTappe[i]);

                        }
                        if (i == 7) {

                            LLDO36.addView(arrayTextCitta[i]);
                            LLDO36.addView(citta[i]);
                            LLDO37.addView(arrayTextVia[i]);
                            LLDO37.addView(via[i]);
                            LLDO38.addView(arrayTextCivico[i]);
                            LLDO38.addView(civico[i]);
                            LLDO39.addView(arrayTextOrario[i]);
                            LLDO39.addView(ora[i]);
                            LLDO40.addView(arrayTextPrezzo[i]);
                            LLDO40.addView(prezzo[i]);
                            Tappa8.addView(arrayTestoTappe[i]);

                        }

                        i++;                    //Incremento la i
                    }

                }
            });


            //Bottoncino rotondo
            FloatingActionButton fabMeno = (FloatingActionButton) findViewById(R.id.fab_offriPassaggioMeno);
            fabMeno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (i == 0) {
                        Snackbar.make(view, "Non puoi diminuire ulteriormente il numero di fermate!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else {
                        i--;        ///decremento la i    /// PS.. forse questa i non serve più
                        ////Imposto i valori iniziali dei bottoni

                        ////Rimuovo gli oggetti precedentemente creati dinamicamente
                        if (i == 0) {

                            /////Attenzione il "removeView rimuove solo l'oggeto ma non lo elimina (quindi i dati inseriti bisogna rimuoverli successivamente)"
                            LLDO1.removeView(arrayTextCitta[i]);
                            LLDO1.removeView(citta[i]);
                            LLDO2.removeView(arrayTextVia[i]);
                            LLDO2.removeView(via[i]);
                            LLDO3.removeView(arrayTextCivico[i]);
                            LLDO3.removeView(civico[i]);
                            LLDO4.removeView(arrayTextOrario[i]);
                            LLDO4.removeView(ora[i]);
                            LLDO5.removeView(arrayTextPrezzo[i]);
                            LLDO5.removeView(prezzo[i]);
                            separatore1.removeView(arraySpaziatore[i]);
                            Tappa1.removeView(arrayTestoTappe[i]);


                            ///Rimuovo e ripristino i dati iniziali (ovvero vuoti)
                            citta[i].setText("");
                            via[i].setText("");
                            civico[i].setText("");
                            ora[i].setText("");
                            prezzo[i].setText("");

                        }

                        if (i == 1) {

                            LLDO6.removeView(arrayTextCitta[i]);
                            LLDO6.removeView(citta[i]);
                            LLDO7.removeView(arrayTextVia[i]);
                            LLDO7.removeView(via[i]);
                            LLDO8.removeView(arrayTextCivico[i]);
                            LLDO8.removeView(civico[i]);
                            LLDO9.removeView(arrayTextOrario[i]);
                            LLDO9.removeView(ora[i]);
                            LLDO10.removeView(arrayTextPrezzo[i]);
                            LLDO10.removeView(prezzo[i]);
                            separatore2.removeView(arraySpaziatore[i]);
                            Tappa2.removeView(arrayTestoTappe[i]);

                            ///Rimuovo e ripristino i dati iniziali (ovvero vuoti)
                            citta[i].setText("");
                            via[i].setText("");
                            civico[i].setText("");
                            ora[i].setText("");
                            prezzo[i].setText("");
                        }
                        if (i == 2) {
                            LLDO11.removeView(arrayTextCitta[i]);
                            LLDO11.removeView(citta[i]);
                            LLDO12.removeView(arrayTextVia[i]);
                            LLDO12.removeView(via[i]);
                            LLDO13.removeView(arrayTextCivico[i]);
                            LLDO13.removeView(civico[i]);
                            LLDO14.removeView(arrayTextOrario[i]);
                            LLDO14.removeView(ora[i]);
                            LLDO15.removeView(arrayTextPrezzo[i]);
                            LLDO15.removeView(prezzo[i]);
                            separatore3.removeView(arraySpaziatore[i]);
                            Tappa3.removeView(arrayTestoTappe[i]);

                            ///Rimuovo e ripristino i dati iniziali (ovvero vuoti)
                            citta[i].setText("");
                            via[i].setText("");
                            civico[i].setText("");
                            ora[i].setText("");
                            prezzo[i].setText("");
                        }
                        if (i == 3) {

                            LLDO16.removeView(arrayTextCitta[i]);
                            LLDO16.removeView(citta[i]);
                            LLDO17.removeView(arrayTextVia[i]);
                            LLDO17.removeView(via[i]);
                            LLDO18.removeView(arrayTextCivico[i]);
                            LLDO18.removeView(civico[i]);
                            LLDO19.removeView(arrayTextOrario[i]);
                            LLDO19.removeView(ora[i]);
                            LLDO20.removeView(arrayTextPrezzo[i]);
                            LLDO20.removeView(prezzo[i]);
                            separatore4.removeView(arraySpaziatore[i]);
                            Tappa4.removeView(arrayTestoTappe[i]);

                            ///Rimuovo e ripristino i dati iniziali (ovvero vuoti)
                            citta[i].setText("");
                            via[i].setText("");
                            civico[i].setText("");
                            ora[i].setText("");
                            prezzo[i].setText("");

                        }
                        if (i == 4) {

                            LLDO21.removeView(arrayTextCitta[i]);
                            LLDO21.removeView(citta[i]);
                            LLDO22.removeView(arrayTextVia[i]);
                            LLDO22.removeView(via[i]);
                            LLDO23.removeView(arrayTextCivico[i]);
                            LLDO23.removeView(civico[i]);
                            LLDO24.removeView(arrayTextOrario[i]);
                            LLDO24.removeView(ora[i]);
                            LLDO25.removeView(arrayTextPrezzo[i]);
                            LLDO25.removeView(prezzo[i]);
                            separatore5.removeView(arraySpaziatore[i]);
                            Tappa5.removeView(arrayTestoTappe[i]);

                            ///Rimuovo e ripristino i dati iniziali (ovvero vuoti)
                            citta[i].setText("");
                            via[i].setText("");
                            civico[i].setText("");
                            ora[i].setText("");
                            prezzo[i].setText("");
                        }
                        if (i == 5) {

                            LLDO26.removeView(arrayTextCitta[i]);
                            LLDO26.removeView(citta[i]);
                            LLDO27.removeView(arrayTextVia[i]);
                            LLDO27.removeView(via[i]);
                            LLDO28.removeView(arrayTextCivico[i]);
                            LLDO28.removeView(civico[i]);
                            LLDO29.removeView(arrayTextOrario[i]);
                            LLDO29.removeView(ora[i]);
                            LLDO30.removeView(arrayTextPrezzo[i]);
                            LLDO30.removeView(prezzo[i]);
                            separatore6.removeView(arraySpaziatore[i]);
                            Tappa6.removeView(arrayTestoTappe[i]);

                            ///Rimuovo e ripristino i dati iniziali (ovvero vuoti)
                            citta[i].setText("");
                            via[i].setText("");
                            civico[i].setText("");
                            ora[i].setText("");
                            prezzo[i].setText("");
                        }
                        if (i == 6) {
                            LLDO31.removeView(arrayTextCitta[i]);
                            LLDO31.removeView(citta[i]);
                            LLDO32.removeView(arrayTextVia[i]);
                            LLDO32.removeView(via[i]);
                            LLDO33.removeView(arrayTextCivico[i]);
                            LLDO33.removeView(civico[i]);
                            LLDO34.removeView(arrayTextOrario[i]);
                            LLDO34.removeView(ora[i]);
                            LLDO35.removeView(arrayTextPrezzo[i]);
                            LLDO35.removeView(prezzo[i]);
                            separatore7.removeView(arraySpaziatore[i]);
                            Tappa7.removeView(arrayTestoTappe[i]);

                            ///Rimuovo e ripristino i dati iniziali (ovvero vuoti)
                            citta[i].setText("");
                            via[i].setText("");
                            civico[i].setText("");
                            ora[i].setText("");
                            prezzo[i].setText("");
                        }
                        if (i == 7) {
                            LLDO36.removeView(arrayTextCitta[i]);
                            LLDO36.removeView(citta[i]);
                            LLDO37.removeView(arrayTextVia[i]);
                            LLDO37.removeView(via[i]);
                            LLDO38.removeView(arrayTextCivico[i]);
                            LLDO38.removeView(civico[i]);
                            LLDO39.removeView(arrayTextOrario[i]);
                            LLDO39.removeView(ora[i]);
                            LLDO40.removeView(arrayTextPrezzo[i]);
                            LLDO40.removeView(prezzo[i]);
                            Tappa8.removeView(arrayTestoTappe[i]);

                            ///Rimuovo e ripristino i dati iniziali (ovvero vuoti)
                            citta[i].setText("");
                            via[i].setText("");
                            civico[i].setText("");
                            ora[i].setText("");
                            prezzo[i].setText("");
                        }

                    }
                }
            });


            //Quando premo il bottone +

///////////////////////////
            //////////
            //////
            // Attivo un listener per ogni bottone creato dinamicamente.......PS...Non penso sia il modo più consono di operare ma funziona
            ora[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    DialogFragment newFragment = new TimePickerFragmentFermate(ora[0]);
                    newFragment.show(getFragmentManager(), "TimePicker");
                }
            });

            ora[1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    DialogFragment newFragment = new TimePickerFragmentFermate(ora[1]);      ////Richiamo la classe interna all'activity mandandogli il text[] da aggiornare con l orario apposito
                    newFragment.show(getFragmentManager(), "TimePicker");
                }
            });
            ora[2].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    DialogFragment newFragment = new TimePickerFragmentFermate(ora[2]);       ////Richiamo la classe interna all'activity mandandogli il text[] da aggiornare con l orario apposito
                    newFragment.show(getFragmentManager(), "TimePicker");
                }
            });
            ora[3].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    DialogFragment newFragment = new TimePickerFragmentFermate(ora[3]);    ////Richiamo la classe interna all'activity mandandogli il text[] da aggiornare con l orario apposito
                    newFragment.show(getFragmentManager(), "TimePicker");
                }
            });
            ora[4].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    DialogFragment newFragment = new TimePickerFragmentFermate(ora[4]);    ////Richiamo la classe interna all'activity mandandogli il text[] da aggiornare con l orario apposito
                    newFragment.show(getFragmentManager(), "TimePicker");
                }
            });
            ora[5].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    DialogFragment newFragment = new TimePickerFragmentFermate(ora[5]);    ////Richiamo la classe interna all'activity mandandogli il text[] da aggiornare con l orario apposito
                    newFragment.show(getFragmentManager(), "TimePicker");
                }
            });
            ora[6].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    DialogFragment newFragment = new TimePickerFragmentFermate(ora[6]);    ////Richiamo la classe interna all'activity mandandogli il text[] da aggiornare con l orario apposito
                    newFragment.show(getFragmentManager(), "TimePicker");
                }
            });
            ora[7].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    DialogFragment newFragment = new TimePickerFragmentFermate(ora[7]);    ////Richiamo la classe interna all'activity mandandogli il text[] da aggiornare con l orario apposito
                    newFragment.show(getFragmentManager(), "TimePicker");
                }
            });
        }


//////////////////////////////////////////////////// Fine Eventi Listener
    }

    //////////////// Gestisco i dati per DatePicker
    public static class DatePickerDialogTheme1 extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(),
                    R.style.MyDialogTheme, this, year, month, day);

            return datepickerdialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {


            TextView textview = (TextView) getActivity().findViewById(R.id.dataview);
            TextView textview2 = (TextView) getActivity().findViewById(R.id.data);

            textview2.setText("Data : ");
            textview.setText(day + "/" + (month + 1) + "/" + year);


        }
    }
///////////Finisco di gestire i dati per il DatePicker

    ///////////////////////

/////////// Inizio a gestire i dati per il TimePicker
    /*
    public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

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
            //Do something with the user chosen time
            //Get reference of host activity (XML Layout File) TextView widget

            TextView textOrario = (TextView) findViewById(R.id.TextViewOraio1);
            TextView textOrario2 = (TextView) findViewById(R.id.TextViewOraio2);

            //Set a message for user
            //Display the user changed time on TextView
            textOrario.setText("Ora selezionata = ");
            textOrario2.setText( String.valueOf(hourOfDay) + "-" + String.valueOf(minute) + "\n");
        }
    }
*/
/////////////////////  Finisco di gestire i dati per il TimePicker

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



///////////////////// Gestisco i TimePicker


    private static void starttask(String link) {
        new OffriPassaggio.FileAsync(link).execute("start");
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
                System.out.println("sto quaOffripassaggio!");
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
            }
            else {
               flag = 1;
            }


            if (flag == 1){
                JSONArray jsa = null;
                try {
                    jsa = new JSONArray("[" + result + "]");
                    for (int i = 0; i < jsa.length(); i++) {
                        json_data = jsa.getJSONObject(i);
                    }
                    codice_passaggio = json_data.getInt("cod_passaggio");
                } catch (JSONException jsex) {
                    //Log.e("TAG_ERROR_TRANSMITTER", jsex.toString());
                }
             }

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

            // activity.finish();    funzionava!! l'ho semplicemente spostato per ragioni tecniche legate al TOAST
            //m_ProgressDialog.dismiss();
        }
    }

    private static int codice_passaggio;
    private static int flag = 0;
    private static int tempo_attesa = 0;
    private static JSONObject json_data = null;
    private static boolean controllo = false;


////////////////////////////////////////////////////////////////////////////////////////////////////
// Inserico un metodo (starttaskTappa) e fileasinkTappa perchè svolgono operazioni differenti

    private static void starttaskTappa(String link,int var) {
        new OffriPassaggio.FileAsyncTappa(link,var).execute("start");
        //   Toast toast = Toast.makeText(Registrazione.this," start:",1);
        // toast.show();

    }


    static class FileAsyncTappa extends AsyncTask<Object, Object, String> {

        private String link;
        private int var;
        // ProgressDialog m_ProgressDialog;
        public FileAsyncTappa(String link,int var) {

            this.link = link;
            this.var = var;
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
                System.out.println("sto quaOffripassaggio!");
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
                controlloErroriTappe= "Error";
            } else {
                controlloErroriTappe="OK";

                JSONArray jsa = null;
                try {
                    jsa = new JSONArray("[" + result + "]");
                    for (int i = 0; i < jsa.length(); i++) {
                        json_data = jsa.getJSONObject(i);
                    }
                    id_tappa[var]=json_data.getInt("id");
                    System.out.println(id_tappa[var]);
                } catch (JSONException jsex) {
                    //Log.e("TAG_ERROR_TRANSMITTER", jsex.toString());
                }

            }

            controlloTappe++;
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

    private static int id_tappa[] = new int[8];
    private static int controlloTappe = 0;
    private static String controlloErroriTappe = "OK";


}

