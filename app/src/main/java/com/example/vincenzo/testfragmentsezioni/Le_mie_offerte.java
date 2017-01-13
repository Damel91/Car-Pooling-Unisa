package com.example.vincenzo.testfragmentsezioni;

/**
 * Created by Vincenzo on 14/11/2016.
 */

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
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


public class Le_mie_offerte extends AppCompatActivity {

    static String mailUtente = "";
    static int num_offerte = 0;
    static int k = 0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.le_mie_offerte);
        Bundle datipassati = getIntent().getExtras();
        mailUtente  = datipassati.getString("MailUtente");
        num_offerte  = datipassati.getInt("NumOfferte");

        cod_provvisorio = 0;
        for(int z = 0; z < num_offerte; z++) {
            starttaskDati("http://technidos.altervista.org/PhpProject/prelevoDati_le_mie_offerte.php?mail=" + mailUtente +"&cod_provvisorio="+ cod_provvisorio,z);
            flag = 0;
            tempo_attesa = 0;
            Boolean bool = true;
            while (bool) {
                if (tempo_attesa == 1)
                    break;
            }
        }


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


        final TextView orario[] = new TextView[num_offerte];
        final TextView citta[] = new TextView[num_offerte];
        final ImageButton modifica[] = new ImageButton[8];
        final ImageButton elimina[] = new ImageButton[8];
        final TextView[] arraySpaziatore = new TextView[num_offerte];


        /// Ho lasciato i nomi città e orario ma in realta non sono città ed orari... mi scoccio di cambiare nome per nome

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams setSpaziatore = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        setSpaziatore.setMargins(0 , 0, 0,60);


        for (int i = 0; i < 8; i++){

            elimina[i] = new ImageButton(Le_mie_offerte.this);
            elimina[i].setImageResource(R.mipmap.cestino);
            elimina[i].setLayoutParams(params);
            elimina[i].setPadding(5, 0, 0, 20);
            modifica[i] = new ImageButton(Le_mie_offerte.this);
            modifica[i].setImageResource(R.mipmap.pencil);
            modifica[i].setLayoutParams(params);
            modifica[i].setPadding(5, 0, 0, 20);

        }
        for (int i = 0; i < num_offerte; i++) {

            citta[i] = new TextView(Le_mie_offerte.this);
            citta[i].setWidth(2000);
            citta[i].setPadding(5, 0, 0, 20);
            citta[i].setTextSize(15);
            citta[i].setText("Codice: "+array_codice_offerta[i]);
            citta[i].setLayoutParams(params);

            orario[i] = new TextView(Le_mie_offerte.this);
            orario[i].setText("Data: "+array_data_offerta[i]);
            orario[i].setWidth(2000);
            orario[i].setPadding(5, 0, 0, 20);
            orario[i].setTextSize(15);
            orario[i].setLayoutParams(params);

            arraySpaziatore[i] = new TextView(Le_mie_offerte.this);
            arraySpaziatore[i].setHeight(2);
            arraySpaziatore[i].setBackgroundColor(Color.BLACK);
            arraySpaziatore[i].setPadding(5, 0, 0, 20);
            arraySpaziatore[i].setLayoutParams(setSpaziatore);

        }


        if (num_offerte == 1) {
            LLVO1.addView(elimina[0]);
            LLVO1.addView(modifica[0]);
            LLVO1.addView(citta[0]);
            LLVO1.addView(orario[0]);
            LLSpaziatore1.addView(arraySpaziatore[0]);
        }

        if (num_offerte == 2) {
            LLVO1.addView(elimina[0]);
            LLVO1.addView(modifica[0]);
            LLVO1.addView(citta[0]);
            LLVO1.addView(orario[0]);
            LLSpaziatore1.addView(arraySpaziatore[0]);
            LLVO2.addView(elimina[1]);
            LLVO2.addView(modifica[1]);
            LLVO2.addView(citta[1]);
            LLVO2.addView(orario[1]);
            LLSpaziatore2.addView(arraySpaziatore[1]);
        }

        if (num_offerte == 3) {
            LLVO1.addView(elimina[0]);
            LLVO1.addView(modifica[0]);
            LLVO1.addView(citta[0]);
            LLVO1.addView(orario[0]);
            LLSpaziatore1.addView(arraySpaziatore[0]);
            LLVO2.addView(elimina[1]);
            LLVO2.addView(modifica[1]);
            LLVO2.addView(citta[1]);
            LLVO2.addView(orario[1]);
            LLSpaziatore2.addView(arraySpaziatore[1]);
            LLVO3.addView(elimina[2]);
            LLVO3.addView(modifica[2]);
            LLVO3.addView(citta[2]);
            LLVO3.addView(orario[2]);
            LLSpaziatore3.addView(arraySpaziatore[2]);
        }

        if (num_offerte == 4) {
            LLVO1.addView(elimina[0]);
            LLVO1.addView(modifica[0]);
            LLVO1.addView(citta[0]);
            LLVO1.addView(orario[0]);
            LLSpaziatore1.addView(arraySpaziatore[0]);
            LLVO2.addView(elimina[1]);
            LLVO2.addView(modifica[1]);
            LLVO2.addView(citta[1]);
            LLVO2.addView(orario[1]);
            LLSpaziatore2.addView(arraySpaziatore[1]);
            LLVO3.addView(elimina[2]);
            LLVO3.addView(modifica[2]);
            LLVO3.addView(citta[2]);
            LLVO3.addView(orario[2]);
            LLSpaziatore3.addView(arraySpaziatore[2]);
            LLVO4.addView(elimina[3]);
            LLVO4.addView(modifica[3]);
            LLVO4.addView(citta[3]);
            LLVO4.addView(orario[3]);
            LLSpaziatore4.addView(arraySpaziatore[3]);
        }

        if (num_offerte == 5) {
            LLVO1.addView(elimina[0]);
            LLVO1.addView(modifica[0]);
            LLVO1.addView(citta[0]);
            LLVO1.addView(orario[0]);
            LLSpaziatore1.addView(arraySpaziatore[0]);
            LLVO2.addView(elimina[1]);
            LLVO2.addView(modifica[1]);
            LLVO2.addView(citta[1]);
            LLVO2.addView(orario[1]);
            LLSpaziatore2.addView(arraySpaziatore[1]);
            LLVO3.addView(elimina[2]);
            LLVO3.addView(modifica[2]);
            LLVO3.addView(citta[2]);
            LLVO3.addView(orario[2]);
            LLSpaziatore3.addView(arraySpaziatore[2]);
            LLVO4.addView(elimina[3]);
            LLVO4.addView(modifica[3]);
            LLVO4.addView(citta[3]);
            LLVO4.addView(orario[3]);
            LLSpaziatore4.addView(arraySpaziatore[3]);
            LLVO5.addView(elimina[4]);
            LLVO5.addView(modifica[4]);
            LLVO5.addView(citta[4]);
            LLVO5.addView(orario[4]);
            LLSpaziatore5.addView(arraySpaziatore[4]);
        }

        if (num_offerte == 6) {
            LLVO1.addView(elimina[0]);
            LLVO1.addView(modifica[0]);
            LLVO1.addView(citta[0]);
            LLVO1.addView(orario[0]);
            LLSpaziatore1.addView(arraySpaziatore[0]);
            LLVO2.addView(elimina[1]);
            LLVO2.addView(modifica[1]);
            LLVO2.addView(citta[1]);
            LLVO2.addView(orario[1]);
            LLSpaziatore2.addView(arraySpaziatore[1]);
            LLVO3.addView(elimina[2]);
            LLVO3.addView(modifica[2]);
            LLVO3.addView(citta[2]);
            LLVO3.addView(orario[2]);
            LLSpaziatore3.addView(arraySpaziatore[2]);
            LLVO4.addView(elimina[3]);
            LLVO4.addView(modifica[3]);
            LLVO4.addView(citta[3]);
            LLVO4.addView(orario[3]);
            LLSpaziatore4.addView(arraySpaziatore[3]);
            LLVO5.addView(elimina[4]);
            LLVO5.addView(modifica[4]);
            LLVO5.addView(citta[4]);
            LLVO5.addView(orario[4]);
            LLSpaziatore5.addView(arraySpaziatore[4]);
            LLVO6.addView(elimina[5]);
            LLVO6.addView(modifica[5]);
            LLVO6.addView(citta[5]);
            LLVO6.addView(orario[5]);
            LLSpaziatore6.addView(arraySpaziatore[5]);
        }

        if (num_offerte == 7) {
            LLVO1.addView(elimina[0]);
            LLVO1.addView(modifica[0]);
            LLVO1.addView(citta[0]);
            LLVO1.addView(orario[0]);
            LLSpaziatore1.addView(arraySpaziatore[0]);
            LLVO2.addView(elimina[1]);
            LLVO2.addView(modifica[1]);
            LLVO2.addView(citta[1]);
            LLVO2.addView(orario[1]);
            LLSpaziatore2.addView(arraySpaziatore[1]);
            LLVO3.addView(elimina[2]);
            LLVO3.addView(modifica[2]);
            LLVO3.addView(citta[2]);
            LLVO3.addView(orario[2]);
            LLSpaziatore3.addView(arraySpaziatore[2]);
            LLVO4.addView(elimina[3]);
            LLVO4.addView(modifica[3]);
            LLVO4.addView(citta[3]);
            LLVO4.addView(orario[3]);
            LLSpaziatore4.addView(arraySpaziatore[3]);
            LLVO5.addView(elimina[4]);
            LLVO5.addView(modifica[4]);
            LLVO5.addView(citta[4]);
            LLVO5.addView(orario[4]);
            LLSpaziatore5.addView(arraySpaziatore[4]);
            LLVO6.addView(elimina[5]);
            LLVO6.addView(modifica[5]);
            LLVO6.addView(citta[5]);
            LLVO6.addView(orario[5]);
            LLSpaziatore6.addView(arraySpaziatore[5]);
            LLVO7.addView(elimina[6]);
            LLVO7.addView(modifica[6]);
            LLVO7.addView(citta[6]);
            LLVO7.addView(orario[6]);
            LLSpaziatore7.addView(arraySpaziatore[6]);
        }

        if (num_offerte == 8) {
            LLVO1.addView(elimina[0]);
            LLVO1.addView(modifica[0]);
            LLVO1.addView(citta[0]);
            LLVO1.addView(orario[0]);
            LLSpaziatore1.addView(arraySpaziatore[0]);
            LLVO2.addView(elimina[1]);
            LLVO2.addView(modifica[1]);
            LLVO2.addView(citta[1]);
            LLVO2.addView(orario[1]);
            LLSpaziatore2.addView(arraySpaziatore[1]);
            LLVO3.addView(elimina[2]);
            LLVO3.addView(modifica[2]);
            LLVO3.addView(citta[2]);
            LLVO3.addView(orario[2]);
            LLSpaziatore3.addView(arraySpaziatore[2]);
            LLVO4.addView(elimina[3]);
            LLVO4.addView(modifica[3]);
            LLVO4.addView(citta[3]);
            LLVO4.addView(orario[3]);
            LLSpaziatore4.addView(arraySpaziatore[3]);
            LLVO5.addView(elimina[4]);
            LLVO5.addView(modifica[4]);
            LLVO5.addView(citta[4]);
            LLVO5.addView(orario[4]);
            LLSpaziatore5.addView(arraySpaziatore[4]);
            LLVO6.addView(elimina[5]);
            LLVO6.addView(modifica[5]);
            LLVO6.addView(citta[5]);
            LLVO6.addView(orario[5]);
            LLSpaziatore6.addView(arraySpaziatore[5]);
            LLVO7.addView(elimina[6]);
            LLVO7.addView(modifica[6]);
            LLVO7.addView(citta[6]);
            LLVO7.addView(orario[6]);
            LLSpaziatore7.addView(arraySpaziatore[6]);
            LLVO8.addView(elimina[7]);
            LLVO8.addView(modifica[7]);
            LLVO8.addView(citta[7]);
            LLVO8.addView(orario[7]);
        }

        final String codice [] = new String [8];
        for(int i = 0; i < num_offerte; i++){
            codice[i] = citta[i].getText().toString().substring(8);
        }

        elimina[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// TODO Auto-generated method stub

                AlertDialog.Builder builder = new AlertDialog.Builder(Le_mie_offerte.this);
                builder.setCancelable(true);
                builder.setTitle("Vuoi eliminare quest'offerta?");
                builder.setInverseBackgroundForced(true);
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                starttask("http://technidos.altervista.org/PhpProject/cancelloOfferta.php?mail=" + mailUtente + "&cod_passaggio=" +  array_codice_offerta[0]);
                                flag = 0;
                                tempo_attesa = 0;
                                Boolean bool = true;
                                while (bool) {
                                    if (tempo_attesa == 1)
                                        break;
                                }
                                dialog.dismiss();
                                if (flag == 0) {
                                    Toast.makeText(Le_mie_offerte.this, "Errore nella cancellazione!!", Toast.LENGTH_SHORT).show();
                                } else {
                                    if(k == 0) {
                                        LLVO1.removeView(elimina[0]);
                                        LLVO1.removeView(modifica[0]);
                                        LLVO1.removeView(citta[0]);
                                        LLVO1.removeView(orario[0]);
                                        LLSpaziatore1.removeView(arraySpaziatore[0]);
                                        AreaUtente.num_offerte_calcolato--;
                                    }
                                }
                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        elimina[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// TODO Auto-generated method stub

                AlertDialog.Builder builder = new AlertDialog.Builder(Le_mie_offerte.this);
                builder.setCancelable(true);
                builder.setTitle("Vuoi eliminare quest'offerta?");
                builder.setInverseBackgroundForced(true);
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                starttask("http://technidos.altervista.org/PhpProject/cancelloOfferta.php?mail=" + mailUtente + "&cod_passaggio=" +  array_codice_offerta[1]);
                                flag = 0;
                                tempo_attesa = 0;
                                Boolean bool = true;
                                while (bool) {
                                    if (tempo_attesa == 1)
                                        break;
                                }
                                dialog.dismiss();
                                if (flag == 0) {
                                    Toast.makeText(Le_mie_offerte.this, "Errore nella cancellazione!!", Toast.LENGTH_SHORT).show();
                                } else {
                                    LLVO2.removeView(elimina[1]);
                                    LLVO2.removeView(modifica[1]);
                                    LLVO2.removeView(citta[1]);
                                    LLVO2.removeView(orario[1]);
                                    LLSpaziatore2.removeView(arraySpaziatore[1]);
                                    AreaUtente.num_offerte_calcolato--;
                                }
                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        elimina[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// TODO Auto-generated method stub

                AlertDialog.Builder builder = new AlertDialog.Builder(Le_mie_offerte.this);
                builder.setCancelable(true);
                builder.setTitle("Vuoi eliminare quest'offerta?");
                builder.setInverseBackgroundForced(true);
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                starttask("http://technidos.altervista.org/PhpProject/cancelloOfferta.php?mail=" + mailUtente + "&cod_passaggio=" +  array_codice_offerta[2]);
                                flag = 0;
                                tempo_attesa = 0;
                                Boolean bool = true;
                                while (bool) {
                                    if (tempo_attesa == 1)
                                        break;
                                }
                                dialog.dismiss();
                                if (flag == 0) {
                                    Toast.makeText(Le_mie_offerte.this, "Errore nella cancellazione!!", Toast.LENGTH_SHORT).show();
                                } else {
                                    LLVO3.removeView(elimina[2]);
                                    LLVO3.removeView(modifica[2]);
                                    LLVO3.removeView(citta[2]);
                                    LLVO3.removeView(orario[2]);
                                    LLSpaziatore3.removeView(arraySpaziatore[2]);
                                    AreaUtente.num_offerte_calcolato--;
                                }
                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });


        elimina[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// TODO Auto-generated method stub

                AlertDialog.Builder builder = new AlertDialog.Builder(Le_mie_offerte.this);
                builder.setCancelable(true);
                builder.setTitle("Vuoi eliminare quest'offerta?");
                builder.setInverseBackgroundForced(true);
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                starttask("http://technidos.altervista.org/PhpProject/cancelloOfferta.php?mail=" + mailUtente + "&cod_passaggio=" +  array_codice_offerta[3]);
                                flag = 0;
                                tempo_attesa = 0;
                                Boolean bool = true;
                                while (bool) {
                                    if (tempo_attesa == 1)
                                        break;
                                }
                                dialog.dismiss();
                                if (flag == 0) {
                                    Toast.makeText(Le_mie_offerte.this, "Errore nella cancellazione!!", Toast.LENGTH_SHORT).show();
                                } else {
                                    LLVO4.removeView(elimina[3]);
                                    LLVO4.removeView(modifica[3]);
                                    LLVO4.removeView(citta[3]);
                                    LLVO4.removeView(orario[3]);
                                    LLSpaziatore4.removeView(arraySpaziatore[3]);
                                    AreaUtente.num_offerte_calcolato--;
                                }
                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        elimina[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// TODO Auto-generated method stub

                AlertDialog.Builder builder = new AlertDialog.Builder(Le_mie_offerte.this);
                builder.setCancelable(true);
                builder.setTitle("Vuoi eliminare quest'offerta?");
                builder.setInverseBackgroundForced(true);
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                starttask("http://technidos.altervista.org/PhpProject/cancelloOfferta.php?mail=" + mailUtente + "&cod_passaggio=" +  array_codice_offerta[4]);
                                flag = 0;
                                tempo_attesa = 0;
                                Boolean bool = true;
                                while (bool) {
                                    if (tempo_attesa == 1)
                                        break;
                                }
                                dialog.dismiss();
                                if (flag == 0) {
                                    Toast.makeText(Le_mie_offerte.this, "Errore nella cancellazione!!", Toast.LENGTH_SHORT).show();
                                } else {
                                    LLVO5.removeView(elimina[4]);
                                    LLVO5.removeView(modifica[4]);
                                    LLVO5.removeView(citta[4]);
                                    LLVO5.removeView(orario[4]);
                                    LLSpaziatore5.removeView(arraySpaziatore[4]);
                                    AreaUtente.num_offerte_calcolato--;
                                }
                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        elimina[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// TODO Auto-generated method stub

                AlertDialog.Builder builder = new AlertDialog.Builder(Le_mie_offerte.this);
                builder.setCancelable(true);
                builder.setTitle("Vuoi eliminare quest'offerta?");
                builder.setInverseBackgroundForced(true);
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                starttask("http://technidos.altervista.org/PhpProject/cancelloOfferta.php?mail=" + mailUtente + "&cod_passaggio=" + array_codice_offerta[5]);
                                flag = 0;
                                tempo_attesa = 0;
                                Boolean bool = true;
                                while (bool) {
                                    if (tempo_attesa == 1)
                                        break;
                                }
                                dialog.dismiss();
                                if (flag == 0) {
                                    Toast.makeText(Le_mie_offerte.this, "Errore nella cancellazione!!", Toast.LENGTH_SHORT).show();
                                } else {
                                    LLVO6.removeView(elimina[5]);
                                    LLVO6.removeView(modifica[5]);
                                    LLVO6.removeView(citta[5]);
                                    LLVO6.removeView(orario[5]);
                                    LLSpaziatore6.removeView(arraySpaziatore[5]);
                                    AreaUtente.num_offerte_calcolato--;
                                }
                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });


        elimina[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// TODO Auto-generated method stub

                AlertDialog.Builder builder = new AlertDialog.Builder(Le_mie_offerte.this);
                builder.setCancelable(true);
                builder.setTitle("Vuoi eliminare quest'offerta?");
                builder.setInverseBackgroundForced(true);
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                starttask("http://technidos.altervista.org/PhpProject/cancelloOfferta.php?mail=" + mailUtente + "&cod_passaggio=" +  array_codice_offerta[6]);
                                flag = 0;
                                tempo_attesa = 0;
                                Boolean bool = true;
                                while (bool) {
                                    if (tempo_attesa == 1)
                                        break;
                                }
                                dialog.dismiss();
                                if (flag == 0) {
                                    Toast.makeText(Le_mie_offerte.this, "Errore nella cancellazione!!", Toast.LENGTH_SHORT).show();
                                } else {
                                    LLVO7.removeView(elimina[6]);
                                    LLVO7.removeView(modifica[6]);
                                    LLVO7.removeView(citta[6]);
                                    LLVO7.removeView(orario[6]);
                                    LLSpaziatore7.removeView(arraySpaziatore[6]);
                                    AreaUtente.num_offerte_calcolato--;
                                }
                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        elimina[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// TODO Auto-generated method stub

                AlertDialog.Builder builder = new AlertDialog.Builder(Le_mie_offerte.this);
                builder.setCancelable(true);
                builder.setTitle("Vuoi eliminare quest'offerta?");
                builder.setInverseBackgroundForced(true);
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                starttask("http://technidos.altervista.org/PhpProject/cancelloOfferta.php?mail=" + mailUtente + "&cod_passaggio=" +  array_codice_offerta[7]);
                                flag = 0;
                                tempo_attesa = 0;
                                Boolean bool = true;
                                while (bool) {
                                    if (tempo_attesa == 1)
                                        break;
                                }
                                dialog.dismiss();
                                if (flag == 0) {
                                    Toast.makeText(Le_mie_offerte.this, "Errore nella cancellazione!!", Toast.LENGTH_SHORT).show();
                                } else {
                                    LLVO8.removeView(elimina[7]);
                                    LLVO8.removeView(modifica[7]);
                                    LLVO8.removeView(citta[7]);
                                    LLVO8.removeView(orario[7]);
                                    AreaUtente.num_offerte_calcolato--;
                                }
                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });



        //////
        /////
        ////
        //Modifica

        modifica[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// TODO Auto-generated method stub
                Intent intent = new Intent(
                        Le_mie_offerte.this,
                        Modifica_offerta.class
                );
                intent.putExtra("cod_offerta", codice[0]); //Invio i dati alla nuova intent
                intent.putExtra("MailUtente",mailUtente); //Invio i dati alla nuova intent
                intent.putExtra("data", array_data_offerta[0]); //Invio i dati alla nuova intent
                startActivity(intent);
            }
        });
        modifica[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// TODO Auto-generated method stub
                Intent intent = new Intent(
                        Le_mie_offerte.this,
                        Modifica_offerta.class
                );
                intent.putExtra("cod_offerta",codice[1]); //Invio i dati alla nuova intent
                intent.putExtra("MailUtente",mailUtente); //Invio i dati alla nuova intent
                intent.putExtra("data", array_data_offerta[1]); //Invio i dati alla nuova intent
                startActivity(intent);
            }
        });
        modifica[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// TODO Auto-generated method stub
                Intent intent = new Intent(
                        Le_mie_offerte.this,
                        Modifica_offerta.class
                );
                intent.putExtra("cod_offerta",codice[2]); //Invio i dati alla nuova intent
                intent.putExtra("MailUtente",mailUtente); //Invio i dati alla nuova intent
                intent.putExtra("data", array_data_offerta[2]); //Invio i dati alla nuova intent
                startActivity(intent);
            }
        });
        modifica[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// TODO Auto-generated method stub
                Intent intent = new Intent(
                        Le_mie_offerte.this,
                        Modifica_offerta.class
                );
                intent.putExtra("cod_offerta",codice[3]); //Invio i dati alla nuova intent
                intent.putExtra("MailUtente",mailUtente); //Invio i dati alla nuova intent
                intent.putExtra("data", array_data_offerta[3]); //Invio i dati alla nuova intent
                startActivity(intent);
            }
        });
        modifica[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// TODO Auto-generated method stub
                Intent intent = new Intent(
                        Le_mie_offerte.this,
                        Modifica_offerta.class
                );
                intent.putExtra("cod_offerta",codice[4]); //Invio i dati alla nuova intent
                intent.putExtra("MailUtente",mailUtente); //Invio i dati alla nuova intent
                intent.putExtra("data", array_data_offerta[4]); //Invio i dati alla nuova intent
                startActivity(intent);
            }
        });
        modifica[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// TODO Auto-generated method stub
                Intent intent = new Intent(
                        Le_mie_offerte.this,
                        Modifica_offerta.class
                );
                intent.putExtra("cod_offerta",codice[5]); //Invio i dati alla nuova intent
                intent.putExtra("MailUtente",mailUtente); //Invio i dati alla nuova intent
                intent.putExtra("data", array_data_offerta[5]); //Invio i dati alla nuova intent
                startActivity(intent);
            }
        });
        modifica[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// TODO Auto-generated method stub
                Intent intent = new Intent(
                        Le_mie_offerte.this,
                        Modifica_offerta.class
                );
                intent.putExtra("cod_offerta",codice[6]); //Invio i dati alla nuova intent
                intent.putExtra("MailUtente",mailUtente); //Invio i dati alla nuova intent
                intent.putExtra("data", array_data_offerta[6]); //Invio i dati alla nuova intent
                startActivity(intent);
            }
        });
        modifica[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// TODO Auto-generated method stub
                Intent intent = new Intent(
                        Le_mie_offerte.this,
                        Modifica_offerta.class
                );
                intent.putExtra("cod_offerta",codice[7]); //Invio i dati alla nuova intent
                intent.putExtra("MailUtente",mailUtente); //Invio i dati alla nuova intent
                intent.putExtra("data", array_data_offerta[7]); //Invio i dati alla nuova intent
                startActivity(intent);
            }
        });

    }


////////////////////////////////////////////////////////////////////////////////////


    private static void starttaskDati(String link, int var) {
        new Le_mie_offerte.FileAsyncDati(link,var).execute("start");
        //   Toast toast = Toast.makeText(Registrazione.this," start:",1);
        // toast.show();

    }


    static class FileAsyncDati extends AsyncTask<Object, Object, JSONArray> {

        private String link;
        private int var;
        private String JSONData;

        // ProgressDialog m_ProgressDialog;
        public FileAsyncDati(String link,int var){
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
            if(result.equals("Error")){
                flag = 0;
            }
            else {
                flag = 1;
                jsa = null;
                try {
                    jsa = new JSONArray("[" + result + "]");
                    for (int i = 0; i < jsa.length(); i++) {
                        json_data = jsa.getJSONObject(i);
                    }
                    array_codice_offerta[var]=json_data.getInt("cod_passaggio");
                    array_data_offerta[var] = json_data.getString("data");
                    cod_provvisorio = json_data.getInt("cod_passaggio");;
                } catch (JSONException jsex) {
                    //Log.e("TAG_ERROR_TRANSMITTER", jsex.toString());
                }
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
    private static JSONObject json_data = null;
    private static int tempo_attesa = 0;
    private static int array_codice_offerta[] = new int[8];
    private static String array_data_offerta[] = new String[8];
    private static int cod_provvisorio = 0;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    private static void starttask(String link) {
        new Le_mie_offerte.FileAsync(link).execute("start");
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
            if(result.equals("Error")){
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

}

