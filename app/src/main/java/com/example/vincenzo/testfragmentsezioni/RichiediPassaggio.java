package com.example.vincenzo.testfragmentsezioni;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.GregorianCalendar;
import java.util.Objects;

public class RichiediPassaggio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_richiedi_passaggio);
        Bundle datipassati = getIntent().getExtras();
        final String mailUtente = datipassati.getString("MailUtente");
        this.setTitle("Richiedi passaggio");
        final EditText città = (EditText) findViewById(R.id.citta);
        final CheckBox cercaRitorno = (CheckBox) findViewById(R.id.cercaRitorno);
        final Button cerca = (Button) findViewById(R.id.cerca);
        final String[] hours = new String[24];
        final String[] insieme = new String[12];
        final String[] minutes = new String[12];
        final Spinner oraAndata = (Spinner) findViewById(R.id.oraAndata);
        final Spinner oraRitorno = (Spinner) findViewById(R.id.oraRitorno);
        final Spinner minutiAndata = (Spinner) findViewById(R.id.minutiAndata);
        final Spinner minutiRitorno = (Spinner) findViewById(R.id.minutiRitorno);
        final Spinner tolleranza = (Spinner) findViewById(R.id.tolleranza);
        final ImageButton sButton = (ImageButton) findViewById(R.id.button_calendar);
        final GridLayout layout = (GridLayout) findViewById(R.id.gridLayout);
        final TextView data = (TextView) findViewById(R.id.dataview);
        layout.setVisibility(View.INVISIBLE);


        for (int i = 0; i < 24; i++) {
            hours[i] = Integer.toString(i);
        }

        for (int i = 0; i < 60; i = i + 5) {
            minutes[i / 5] = Integer.toString(i);
            insieme[i / 5] = "±" + Integer.toString(i) + "min";
        }

        ArrayAdapter<String> giornata = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hours);
        oraAndata.setAdapter(giornata);

        oraRitorno.setAdapter(giornata);

        ArrayAdapter<String> tolleranze = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, insieme);
        tolleranza.setAdapter(tolleranze);

        ArrayAdapter<String> minuti = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, minutes);
        minutiAndata.setAdapter(minuti);
        minutiRitorno.setAdapter(minuti);


        minutiAndata.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            final String[] selectedItemMinuti = new String[1];

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                selectedItemMinuti[0] = (String) minutiAndata.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        minutiRitorno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            final String[] selectedItemMinuti = new String[1];

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                selectedItemMinuti[0] = (String) minutiRitorno.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        oraAndata.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            final String[] selectedItemOre = new String[1];

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                selectedItemOre[0] = (String) oraAndata.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        oraRitorno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            final String[] selectedItemOre = new String[1];

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                selectedItemOre[0] = (String) oraRitorno.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        tolleranza.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            final String[] selectedItemTolleranza = new String[1];

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                selectedItemTolleranza[0] = (String) tolleranza.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        sButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DialogFragment dialogfragment = new OffriPassaggio.DatePickerDialogTheme1();
                dialogfragment.show(getFragmentManager(), "Theme 1");
            }

        });

        cercaRitorno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cercaRitorno.isChecked())
                    layout.setVisibility(View.VISIBLE);
                else layout.setVisibility(View.INVISIBLE);
            }
        });

        cerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (città.getText().toString().isEmpty()) {
                    Toast.makeText(RichiediPassaggio.this, "Il campo città è vuoto", Toast.LENGTH_SHORT).show();
                } else if (città.getText().toString().substring(0, 1).contains(" ")||città.getText().toString().lastIndexOf(' ')>0) {
                    Toast.makeText(RichiediPassaggio.this, "Rimuovi gli spazi", Toast.LENGTH_SHORT).show();
                } else if (data.getText().toString().isEmpty()) {
                    Toast.makeText(RichiediPassaggio.this, "Il campo data è vuoto", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checked = false;
                    if (cercaRitorno.isChecked())
                        checked = true;
                    Intent intent = new Intent(RichiediPassaggio.this, ResultPage.class);
                    intent.putExtra("mail", mailUtente);
                    intent.putExtra("città",città.getText().toString());
                    intent.putExtra("oraAndata",Integer.parseInt(oraAndata.getSelectedItem().toString()));
                    intent.putExtra("minutiAndata",Integer.parseInt(minutiAndata.getSelectedItem().toString()));
                    intent.putExtra("oraRitorno",Integer.parseInt(oraRitorno.getSelectedItem().toString()));
                    intent.putExtra("minutiRitorno",Integer.parseInt(minutiRitorno.getSelectedItem().toString()));
                    intent.putExtra("tolleranza",tolleranza.getSelectedItem().toString());
                    intent.putExtra("data",data.getText().toString());
                    intent.putExtra("isChecked",checked);
                    intent.putExtra("title","Passaggi trovati");
                    startActivity(intent);
                }

            }
        });
    }
}
