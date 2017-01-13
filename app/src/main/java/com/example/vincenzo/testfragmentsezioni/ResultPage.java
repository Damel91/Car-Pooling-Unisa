package com.example.vincenzo.testfragmentsezioni;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class ResultPage extends AppCompatActivity {
    static String idTappa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        Bundle datipassati = getIntent().getExtras();
        String mail = datipassati.getString("mail");
        String citta = datipassati.getString("città");
        int oraAndata = datipassati.getInt("oraAndata");
        int minutiAndata = datipassati.getInt("minutiAndata");
        int oraRitorno = datipassati.getInt("oraAndata");
        int minutiRitorno = datipassati.getInt("minutiAndata");
        String tolleranza = datipassati.getString("tolleranza");
        String data = datipassati.getString("data");
        boolean isChecked = datipassati.getBoolean("isCheked");
        String title = datipassati.getString("title");
        this.setTitle(title);
        Button conferma = (Button) findViewById(R.id.conferma);
        final ListView rs = (ListView) findViewById(R.id.rs);
        final ArrayAdapter<String> risultati = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        rs.setAdapter(risultati);
        risultati.add("Caricamento");
        JSONObject[] result;
        JSONObject tupla;
        GregorianCalendar date = new GregorianCalendar();

        GregorianCalendar andata = new GregorianCalendar();
        andata.set(GregorianCalendar.HOUR_OF_DAY, oraAndata);
        andata.set(GregorianCalendar.MINUTE, minutiAndata);
        GregorianCalendar ritorno = new GregorianCalendar();
        ritorno.set(GregorianCalendar.HOUR_OF_DAY, oraRitorno);
        ritorno.set(GregorianCalendar.MINUTE, minutiRitorno);

        GregorianCalendar max = new GregorianCalendar();
        GregorianCalendar min = new GregorianCalendar();

        int last = tolleranza.lastIndexOf('m');
        int tollerance = Integer.parseInt(tolleranza.substring(1, last));

        if (title.equals("Passaggi trovati")) {
            andata.add(GregorianCalendar.MINUTE, tollerance);
            max.set(GregorianCalendar.HOUR_OF_DAY, andata.get(GregorianCalendar.HOUR_OF_DAY));
            max.set(GregorianCalendar.MINUTE, andata.get(GregorianCalendar.MINUTE));
            andata.add(GregorianCalendar.MINUTE, -tollerance * 2);
            min.set(GregorianCalendar.HOUR_OF_DAY, andata.get(GregorianCalendar.HOUR_OF_DAY));
            min.set(GregorianCalendar.MINUTE, andata.get(GregorianCalendar.MINUTE));
        }

        SimpleDateFormat in = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd");
        String dataD = null;
        try {
            Date d = in.parse(data);
            dataD = out.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        citta.replace(' ', '_');


        ConnectToDatabase obj = new ConnectToDatabase("http://technidos.altervista.org/PhpProject/andata.php?data=" + dataD + "&citta=" + citta + "&maxBound=" + max.get(GregorianCalendar.HOUR_OF_DAY) + ":" + max.get(GregorianCalendar.MINUTE) + "&minBound=" + min.get(GregorianCalendar.HOUR_OF_DAY) + ":" + min.get(GregorianCalendar.MINUTE));

        while (obj.isConnected() == 2) ;
        if (obj.isConnected() == 0)
            risultati.clear();
        risultati.add("Passaggio non trovato");
        if (obj.isConnected() == 1) {
            while (!obj.isReady()) ;
            risultati.clear();
            result = obj.getJsonData();
            try {
                for (int i = 0; i < result.length; i++) {
                    tupla = result[i];
                    risultati.add("Nome: " + tupla.getString("name") + "\n" + "Cognome: " + tupla.getString("surname") + "\n" + "Telefono: " + tupla.getString("cellulare") + "\n" + "Auto: " + tupla.getString("auto") + "\n" + "Colore: " + tupla.getString("colore") + "\n" + "Città: " + tupla.getString("citta") + "\n" + "Via: " + tupla.getString("via") +"\n"+ "Civico: " + tupla.getString("civico") + "\n" + "Data: "+data+"\n" + "Ora partenza: " + tupla.getString("ora_andata").substring(0,5) + "\n" + "Prezzo: " + tupla.getDouble("prezzo")+"€"+ "\n" + "Rating: " + tupla.getInt("rating"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        rs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                idTappa = (String) rs.getItemAtPosition(i);
                try {
                    for (int ctr = 0; ctr <= rs.getCount(); ctr++) {
                        if (i == ctr) {
                            rs.getChildAt(ctr).setBackgroundColor(Color.GRAY);
                        } else {
                            rs.getChildAt(ctr).setBackgroundColor(Color.WHITE);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        conferma.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                        }
                                    }
        );
    }
}