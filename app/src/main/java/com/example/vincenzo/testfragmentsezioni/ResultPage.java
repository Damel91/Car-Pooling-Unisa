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
        int tollerance =  Integer.parseInt(tolleranza.substring(1, last));

        if (title.equals("Passaggi trovati")){
            andata.add(GregorianCalendar.MINUTE, tollerance);
            max.set(GregorianCalendar.HOUR_OF_DAY, andata.get(GregorianCalendar.HOUR_OF_DAY));
            max.set(GregorianCalendar.MINUTE, andata.get(GregorianCalendar.MINUTE));
            andata.add(GregorianCalendar.MINUTE, -tollerance*2);
            min.set(GregorianCalendar.HOUR_OF_DAY, andata.get(GregorianCalendar.HOUR_OF_DAY));
            min.set(GregorianCalendar.MINUTE, andata.get(GregorianCalendar.MINUTE));
        }

        SimpleDateFormat in = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d = in.parse(data);
            data = out.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        citta.replace(' ','_');


        ConnectToDatabase obj = new ConnectToDatabase("http://technidos.altervista.org/PhpProject/andata.php?data="+data+"&citta="+citta+"&maxBound="+max.get(GregorianCalendar.HOUR_OF_DAY)+":"+max.get(GregorianCalendar.MINUTE)+"&minBound="+min.get(GregorianCalendar.HOUR_OF_DAY)+":"+min.get(GregorianCalendar.MINUTE));

        while (obj.isConnected()==2);
        if(obj.isConnected()==0)
            risultati.clear();
            risultati.add("Passaggio non trovato");
        if(obj.isConnected()==1){
            while(!obj.isReady());
            risultati.clear();
            result = obj.getJsonData();
            try {
            for (int i=0; i<result.length; i++){
                tupla = result[i];
                risultati.add(""+tupla.getInt("rating"));
            }
            }catch (JSONException e) {
                    e.printStackTrace();
            }
        }

        rs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            final String[] selectedItem = new String[1];
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                rs.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
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