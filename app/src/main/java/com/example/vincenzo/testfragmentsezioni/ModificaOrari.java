package com.example.vincenzo.testfragmentsezioni;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.StaticLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ModificaOrari extends AppCompatActivity {

    private static ArrayAdapter<String> setAd(ArrayAdapter<String> ad, String[]array){
        for(int i=0; i<array.length;i++){
            ad.add(array[i]);
        }
        return ad;
    }

    ArrayAdapter<String> tolleranze;
    ArrayAdapter<String> ad1;
    ArrayAdapter<String> ad2;
    ArrayAdapter<String> ad3;
    ArrayAdapter<String> ad4;
    ArrayAdapter<String> ad5;
    ArrayAdapter<String> ad6;
    ArrayAdapter<String> ad7;
    ArrayAdapter<String> ad8;
    ArrayAdapter<String> ad9;
    ArrayAdapter<String> ad10;
    ArrayAdapter<String> ad11;
    ArrayAdapter<String> ad12;
    ArrayAdapter<String> ad13;
    ArrayAdapter<String> ad14;
    ArrayAdapter<String> ad15;
    ArrayAdapter<String> ad16;
    ArrayAdapter<String> ad17;
    ArrayAdapter<String> ad18;
    ArrayAdapter<String> ad19;
    ArrayAdapter<String> ad20;
    Spinner spin1;
    Spinner spin2;
    Spinner spin3;
    Spinner spin4;
    Spinner spin5;
    Spinner spin6;
    Spinner spin7;
    Spinner spin8;
    Spinner spin9;
    Spinner spin10;
    Spinner spin11;
    Spinner spin12;
    Spinner spin13;
    Spinner spin14;
    Spinner spin15;
    Spinner spin16;
    Spinner spin17;
    Spinner spin18;
    Spinner spin19;
    Spinner spin20;
    Spinner tolleranza;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_orari);
        Bundle datipassati = getIntent().getExtras();
        final String mailUtente = datipassati.getString("MailUtente");
        this.setTitle("Modifica Orari");
        spin1 = (Spinner) findViewById(R.id.spin1);
        spin2 = (Spinner) findViewById(R.id.spin2);
        spin3 = (Spinner) findViewById(R.id.spin3);
        spin4 = (Spinner) findViewById(R.id.spin4);
        spin5 = (Spinner) findViewById(R.id.spin5);
        spin6 = (Spinner) findViewById(R.id.spin6);
        spin7 = (Spinner) findViewById(R.id.spin7);
        spin8 = (Spinner) findViewById(R.id.spin8);
        spin9 = (Spinner) findViewById(R.id.spin9);
        spin10 = (Spinner) findViewById(R.id.spin10);
        spin11 = (Spinner) findViewById(R.id.spin11);
        spin12 = (Spinner) findViewById(R.id.spin12);
        spin13 = (Spinner) findViewById(R.id.spin13);
        spin14 = (Spinner) findViewById(R.id.spin14);
        spin15 = (Spinner) findViewById(R.id.spin15);
        spin16 = (Spinner) findViewById(R.id.spin16);
        spin17 = (Spinner) findViewById(R.id.spin17);
        spin18 = (Spinner) findViewById(R.id.spin18);
        spin19 = (Spinner) findViewById(R.id.spin19);
        spin20 = (Spinner) findViewById(R.id.spin20);
        tolleranza = (Spinner) findViewById(R.id.tolleranza);
        final String[] insieme = new String[13];
        final String[]hours=new String[25];
        final String[]minutes=new String[13];
        final Button salva = (Button) findViewById(R.id.salva);
        tolleranze=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ad1=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ad2=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ad3=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ad4=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ad5=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ad6=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ad7=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ad8=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ad9=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ad10=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ad11=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ad12=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ad13=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ad14=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ad15=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ad16=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ad17=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ad18=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ad19=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        ad20=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        minutes[0]=" ";
        insieme[0]=" ";
        hours[0]=" ";
        for (int i=0; i<24; i++) {
            hours[i+1]=Integer.toString(i);
        }

        for (int i=0;i<60;i=i+5){
            minutes[i/5+1]=Integer.toString(i);
            insieme[i/5+1]="±"+Integer.toString(i)+"min";
        }

        try {
            final FileInputStream fis = openFileInput("mailUtente");
            try {
                ad1.add(""+fis.read());
                ad2.add(""+fis.read());
                ad3.add(""+fis.read());
                ad4.add(""+fis.read());
                ad5.add(""+fis.read());
                ad6.add(""+fis.read());
                ad7.add(""+fis.read());
                ad8.add(""+fis.read());
                ad9.add(""+fis.read());
                ad10.add(""+fis.read());
                ad11.add(""+fis.read());
                ad12.add(""+fis.read());
                ad13.add(""+fis.read());
                ad14.add(""+fis.read());
                ad15.add(""+fis.read());
                ad16.add(""+fis.read());
                ad17.add(""+fis.read());
                ad18.add(""+fis.read());
                ad19.add(""+fis.read());
                ad20.add(""+fis.read());
                tolleranze.add("±"+fis.read()+"min");
                fis.close();
            }catch(IOException e){
            }
        } catch (FileNotFoundException e) {
        }

        spin1.setAdapter(ad1);
        spin2.setAdapter(ad2);
        spin3.setAdapter(ad3);
        spin4.setAdapter(ad4);
        spin5.setAdapter(ad5);
        spin6.setAdapter(ad6);
        spin7.setAdapter(ad7);
        spin8.setAdapter(ad8);
        spin9.setAdapter(ad9);
        spin10.setAdapter(ad10);
        spin11.setAdapter(ad11);
        spin12.setAdapter(ad12);
        spin13.setAdapter(ad13);
        spin14.setAdapter(ad14);
        spin15.setAdapter(ad15);
        spin16.setAdapter(ad16);
        spin17.setAdapter(ad17);
        spin18.setAdapter(ad18);
        spin19.setAdapter(ad19);
        spin20.setAdapter(ad20);
        tolleranza.setAdapter(tolleranze);

        ad1 = ModificaOrari.setAd(ad1, hours);
        ad2 = ModificaOrari.setAd(ad2, minutes);
        ad3 = ModificaOrari.setAd(ad3, hours);
        ad4 = ModificaOrari.setAd(ad4, minutes);
        ad5 = ModificaOrari.setAd(ad5, hours);
        ad6 = ModificaOrari.setAd(ad6, minutes);
        ad7 = ModificaOrari.setAd(ad7, hours);
        ad8 = ModificaOrari.setAd(ad8, minutes);
        ad9 = ModificaOrari.setAd(ad9, hours);
        ad10 = ModificaOrari.setAd(ad10, minutes);
        ad11 = ModificaOrari.setAd(ad11, hours);
        ad12 = ModificaOrari.setAd(ad12, minutes);
        ad13 = ModificaOrari.setAd(ad13, hours);
        ad14 = ModificaOrari.setAd(ad14, minutes);
        ad15 = ModificaOrari.setAd(ad15, hours);
        ad16 = ModificaOrari.setAd(ad16, minutes);
        ad17 = ModificaOrari.setAd(ad17, hours);
        ad18 = ModificaOrari.setAd(ad18, minutes);
        ad19 = ModificaOrari.setAd(ad19, hours);
        ad20 = ModificaOrari.setAd(ad20, minutes);
        tolleranze = ModificaOrari.setAd(tolleranze, insieme);

        tolleranza.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            final String[] selectedItemTolleranza = new String[1];
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                selectedItemTolleranza[0] = (String) tolleranza.getSelectedItem();
                tolleranze.remove(tolleranze.getItem(0));
                tolleranze.insert(selectedItemTolleranza[0], 0);
                if(selectedItemTolleranza[0]!=" "&& tolleranze.getCount()<14){
                    tolleranze.insert(" ",1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            final String[] selectedItem = new String[1];
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                selectedItem[0] = (String) spin1.getSelectedItem();
                ad1.remove(ad1.getItem(0));
                ad1.insert(selectedItem[0], 0);
                if(selectedItem[0]!=" "&& ad1.getCount()<26){
                    ad1.insert(" ",1);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            final String[] selectedItem = new String[1];
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                selectedItem[0] = (String) spin2.getSelectedItem();
                ad2.remove(ad2.getItem(0));
                ad2.insert(selectedItem[0], 0);
                if(selectedItem[0]!=" " && ad2.getCount()<14){
                    ad2.insert(" ",1);
                }
                }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            final String[] selectedItem = new String[1];
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                selectedItem[0] = (String) spin3.getSelectedItem();
                ad3.remove(ad3.getItem(0));
                ad3.insert(selectedItem[0], 0);
                if(selectedItem[0]!=" "&& ad3.getCount()<26){
                    ad3.insert(" ",1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spin4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            final String[] selectedItem = new String[1];
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                selectedItem[0] = (String) spin4.getSelectedItem();
                ad4.remove(ad4.getItem(0));
                ad4.insert(selectedItem[0], 0);
                if(selectedItem[0]!=" "&& ad4.getCount()<14){
                    ad4.insert(" ",1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spin5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            final String[] selectedItem = new String[1];
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                selectedItem[0] = (String) spin5.getSelectedItem();
                ad5.remove(ad5.getItem(0));
                ad5.insert(selectedItem[0], 0);
                if(selectedItem[0]!=" "&& ad5.getCount()<26){
                    ad5.insert(" ",1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spin6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            final String[] selectedItem = new String[1];
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                selectedItem[0] = (String) spin6.getSelectedItem();
                ad6.remove(ad6.getItem(0));
                ad6.insert(selectedItem[0], 0);
                if(selectedItem[0]!=" "&& ad6.getCount()<14){
                    ad6.insert(" ",1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spin7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            final String[] selectedItem = new String[1];
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                selectedItem[0] = (String) spin7.getSelectedItem();
                ad7.remove(ad7.getItem(0));
                ad7.insert(selectedItem[0], 0);
                if(selectedItem[0]!=" "&& ad7.getCount()<26){
                    ad7.insert(" ",1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spin8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            final String[] selectedItem = new String[1];
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                selectedItem[0] = (String) spin8.getSelectedItem();
                ad8.remove(ad8.getItem(0));
                ad8.insert(selectedItem[0], 0);
                if(selectedItem[0]!=" "&& ad8.getCount()<14){
                    ad8.insert(" ",1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spin9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            final String[] selectedItem = new String[1];
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                selectedItem[0] = (String) spin9.getSelectedItem();
                ad9.remove(ad9.getItem(0));
                ad9.insert(selectedItem[0], 0);
                if(selectedItem[0]!=" "&& ad9.getCount()<26){
                    ad9.insert(" ",1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spin10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            final String[] selectedItem = new String[1];
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                selectedItem[0] = (String) spin10.getSelectedItem();
                ad10.remove(ad10.getItem(0));
                ad10.insert(selectedItem[0], 0);
                if(selectedItem[0]!=" "&& ad10.getCount()<14){
                    ad10.insert(" ",1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spin11.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            final String[] selectedItem = new String[1];
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                selectedItem[0] = (String) spin11.getSelectedItem();
                ad11.remove(ad11.getItem(0));
                ad11.insert(selectedItem[0], 0);
                if(selectedItem[0]!=" "&& ad11.getCount()<26){
                    ad11.insert(" ",1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spin12.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            final String[] selectedItem = new String[1];
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                selectedItem[0] = (String) spin12.getSelectedItem();
                ad12.remove(ad12.getItem(0));
                ad12.insert(selectedItem[0], 0);
                if(selectedItem[0]!=" "&& ad12.getCount()<14){
                    ad12.insert(" ",1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spin13.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            final String[] selectedItem = new String[1];
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                selectedItem[0] = (String) spin13.getSelectedItem();
                ad13.remove(ad13.getItem(0));
                ad13.insert(selectedItem[0], 0);
                if(selectedItem[0]!=" "&& ad13.getCount()<26){
                    ad13.insert(" ",1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spin14.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            final String[] selectedItem = new String[1];
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                selectedItem[0] = (String) spin14.getSelectedItem();
                ad14.remove(ad14.getItem(0));
                ad14.insert(selectedItem[0], 0);
                if(selectedItem[0]!=" "&& ad14.getCount()<14){
                    ad14.insert(" ",1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spin15.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            final String[] selectedItem = new String[1];
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                selectedItem[0] = (String) spin15.getSelectedItem();
                ad15.remove(ad15.getItem(0));
                ad15.insert(selectedItem[0], 0);
                if(selectedItem[0]!=" "&& ad15.getCount()<26){
                    ad15.insert(" ",1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spin16.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            final String[] selectedItem = new String[1];
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                selectedItem[0] = (String) spin16.getSelectedItem();
                ad16.remove(ad16.getItem(0));
                ad16.insert(selectedItem[0], 0);
                if(selectedItem[0]!=" "&& ad16.getCount()<14){
                    ad16.insert(" ",1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spin17.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            final String[] selectedItem = new String[1];
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                selectedItem[0] = (String) spin17.getSelectedItem();
                ad17.remove(ad17.getItem(0));
                ad17.insert(selectedItem[0], 0);
                if(selectedItem[0]!=" "&& ad17.getCount()<26){
                    ad17.insert(" ",1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spin18.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            final String[] selectedItem = new String[1];
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                selectedItem[0] = (String) spin18.getSelectedItem();
                ad18.remove(ad18.getItem(0));
                ad18.insert(selectedItem[0], 0);
                if(selectedItem[0]!=" "&& ad18.getCount()<14){
                    ad18.insert(" ",1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spin19.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            final String[] selectedItem = new String[1];
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                selectedItem[0] = (String) spin19.getSelectedItem();
                ad19.remove(ad19.getItem(0));
                ad19.insert(selectedItem[0], 0);
                if(selectedItem[0]!=" "&& ad19.getCount()<26){
                    ad19.insert(" ",1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spin20.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            final String[] selectedItem = new String[1];
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                selectedItem[0] = (String) spin20.getSelectedItem();
                ad20.remove(ad20.getItem(0));
                ad20.insert(selectedItem[0], 0);
                if(selectedItem[0]!=" " && ad20.getCount()<14){
                    ad20.insert(" ",1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        salva.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                boolean b = false;
                if (spin1.getSelectedItem().toString().contains(" ")) {
                    b = true;
                }
                if (spin2.getSelectedItem().toString().contains(" ")) {
                    b = true;
                }
                if (spin3.getSelectedItem().toString().contains(" ")) {
                    b = true;
                }
                if (spin4.getSelectedItem().toString().contains(" ")) {
                    b = true;
                }
                if (spin5.getSelectedItem().toString().contains(" ")) {
                    b = true;
                }
                if (spin6.getSelectedItem().toString().contains(" ")) {
                    b = true;
                }
                if (spin7.getSelectedItem().toString().contains(" ")) {
                    b = true;
                }
                if (spin8.getSelectedItem().toString().contains(" ")) {
                    b = true;
                }
                if (spin9.getSelectedItem().toString().contains(" ")) {
                    b = true;
                }
                if (spin10.getSelectedItem().toString().contains(" ")) {
                    b = true;
                }
                if (spin11.getSelectedItem().toString().contains(" ")) {
                    b = true;
                }
                if (spin12.getSelectedItem().toString().contains(" ")) {
                    b = true;
                }
                if (spin13.getSelectedItem().toString().contains(" ")) {
                    b = true;
                }
                if (spin14.getSelectedItem().toString().contains(" ")) {
                    b = true;
                }
                if (spin15.getSelectedItem().toString().contains(" ")) {
                    b = true;
                }
                if (spin16.getSelectedItem().toString().contains(" ")) {
                    b = true;
                }
                if (spin17.getSelectedItem().toString().contains(" ")) {
                    b = true;
                }
                if (spin18.getSelectedItem().toString().contains(" ")) {
                    b = true;
                }
                if (spin19.getSelectedItem().toString().contains(" ")) {
                    b = true;
                }
                if (spin20.getSelectedItem().toString().contains(" ")) {
                    b = true;
                }
                if (tolleranza.getSelectedItem().toString().contains(" ")) {
                    b = true;
                }
                if (!b) {
                    try {
                        FileOutputStream fos = openFileOutput("mailUtente", Context.MODE_WORLD_READABLE);
                        fos.write(Integer.parseInt(spin1.getSelectedItem().toString()));
                        fos.write(Integer.parseInt(spin2.getSelectedItem().toString()));
                        fos.write(Integer.parseInt(spin3.getSelectedItem().toString()));
                        fos.write(Integer.parseInt(spin4.getSelectedItem().toString()));
                        fos.write(Integer.parseInt(spin5.getSelectedItem().toString()));
                        fos.write(Integer.parseInt(spin6.getSelectedItem().toString()));
                        fos.write(Integer.parseInt(spin7.getSelectedItem().toString()));
                        fos.write(Integer.parseInt(spin8.getSelectedItem().toString()));
                        fos.write(Integer.parseInt(spin9.getSelectedItem().toString()));
                        fos.write(Integer.parseInt(spin10.getSelectedItem().toString()));
                        fos.write(Integer.parseInt(spin11.getSelectedItem().toString()));
                        fos.write(Integer.parseInt(spin12.getSelectedItem().toString()));
                        fos.write(Integer.parseInt(spin13.getSelectedItem().toString()));
                        fos.write(Integer.parseInt(spin14.getSelectedItem().toString()));
                        fos.write(Integer.parseInt(spin15.getSelectedItem().toString()));
                        fos.write(Integer.parseInt(spin16.getSelectedItem().toString()));
                        fos.write(Integer.parseInt(spin17.getSelectedItem().toString()));
                        fos.write(Integer.parseInt(spin18.getSelectedItem().toString()));
                        fos.write(Integer.parseInt(spin19.getSelectedItem().toString()));
                        fos.write(Integer.parseInt(spin20.getSelectedItem().toString()));
                        fos.write(Integer.parseInt(tolleranza.getSelectedItem().toString().substring(1, tolleranza.getSelectedItem().toString().lastIndexOf('m'))));
                        fos.close();
                    } catch (FileNotFoundException e) {
                        Toast.makeText(ModificaOrari.this, "File non trovato", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        Toast.makeText(ModificaOrari.this, "Problema di input output", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(ModificaOrari.this, "Dati salvati", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(ModificaOrari.this, "Ci sono una o più caselle vuote", Toast.LENGTH_SHORT).show();
            }
        });

    }
}


