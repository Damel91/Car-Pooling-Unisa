package com.example.vincenzo.testfragmentsezioni;

/**
 * Created by Vincenzo on 08/11/2016.
 */

import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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

import static java.security.AccessController.getContext;

public class AreaUtente extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static String datopassato="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.area_utente);

        //Prendo i dati passati dal main_activity
       Bundle datipassati = getIntent().getExtras();
       datopassato  = datipassati.getString("MailUtente");

        starttask("http://technidos.altervista.org/PhpProject/count_passaggi.php?mail="+ datopassato,0);
        flag = 0;
        tempo_attesa = 0;
        Boolean bool = true;
        while (bool) {
            if (tempo_attesa == 1)
                break;
        }

        System.out.println("egwouegfuwegfuowgfouwegoufguoegfuewgfouwefouegwfgewoufguowegfougeoufgouewfouewgfouwegfougeoufgewougfouwefgu ............ "+num_offerte_calcolato);

        Button offri_passaggio = (Button) findViewById(R.id.offri_passaggio) ;
        Button le_mie_offerte = (Button) findViewById(R.id.le_mie_offerte) ;
        Button richiediPassaggio = (Button) findViewById(R.id.richiediPassaggio);
        Button organizzaSettimana = (Button) findViewById(R.id.organizzaSettimana);
        Button leMieRichieste = (Button) findViewById(R.id.mieRichieste);


        offri_passaggio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AreaUtente.this,
                        OffriPassaggio.class);
                intent.putExtra("MailUtente",datopassato); //Invio i dati alla nuova intent
                intent.putExtra("NumOfferte",num_offerte_calcolato); //Invio i dati alla nuova intent
                startActivity(intent);
            }
        });

        le_mie_offerte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AreaUtente.this,
                        Le_mie_offerte.class);
                intent.putExtra("MailUtente",datopassato); //Invio i dati alla nuova intent
                intent.putExtra("NumOfferte",num_offerte_calcolato); //Invio i dati alla nuova intent
                startActivity(intent);
            }
        });

        richiediPassaggio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AreaUtente.this, RichiediPassaggio.class);
                intent.putExtra("MailUtente",datopassato); //Invio i dati alla nuova intent
                startActivity(intent);
            }
        }) ;

        organizzaSettimana.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AreaUtente.this, OrganizzaSettimana.class);
                intent.putExtra("MailUtente",datopassato); //Invio i dati alla nuova intent
                startActivity(intent);
            }
        }) ;

        leMieRichieste.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AreaUtente.this, LeMieRichieste.class);
                intent.putExtra("MailUtente",datopassato); //Invio i dati alla nuova intent
                startActivity(intent);
            }
        }) ;


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_areautente);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_area_utente, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.mod_profilo) {
            Intent intent = new Intent(
                    AreaUtente.this,
                    Modifica_dati.class
            );
            intent.putExtra("MailUtente",datopassato); //Invio i dati alla nuova intent
            startActivity(intent);

        } else if (id == R.id.mod_orari) {
            Intent intent = new Intent(
                    AreaUtente.this,
                    ModificaOrari.class
            );
            intent.putExtra("MailUtente",datopassato); //Invio i dati alla nuova intent
            startActivity(intent);


        } else if (id == R.id.mod_prenotazioni) {

        } else if (id == R.id.cancella_profilo) {

            AlertDialog.Builder builder = new AlertDialog.Builder(AreaUtente.this);
            builder.setCancelable(true);
            builder.setTitle("Vuoi eliminare per sempre il tuo profilo?");
            builder.setInverseBackgroundForced(true);
            builder.setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            starttask("http://technidos.altervista.org/PhpProject/eliminoAccount.php?mail=" + datopassato ,-1);
                            flag = 0;
                            tempo_attesa = 0;
                            Boolean bool = true;
                            while (bool) {
                                if (tempo_attesa == 1)
                                    break;
                            }
                            dialog.dismiss();
                            if (flag == 0) {
                                Toast.makeText(AreaUtente.this, "Errore nella cancellazione!!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(AreaUtente.this, "Cancellazione avvenuta con successo...arrivederla!", Toast.LENGTH_SHORT).show();
                                finish();
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


        } else if (id == R.id.logout) {
            finish();
        } /*else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




    private static void starttask(String link,int var) {
        new AreaUtente.FileAsync(link,var).execute("start");
        //   Toast toast = Toast.makeText(Registrazione.this," start:",1);
        // toast.show();

    }



    static class FileAsync extends AsyncTask<Object, Object, JSONArray> {

        private String link;
        private int var;
        private String JSONData;

        // ProgressDialog m_ProgressDialog;
        public FileAsync(String link,int var){
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
                    if(var == 0) {
                        jsa = new JSONArray("[" + result + "]");
                        for (int i = 0; i < jsa.length(); i++) {
                            json_data = jsa.getJSONObject(i);
                        }
                        num_offerte_calcolato = json_data.getInt("mail");
                    }
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

    private static JSONObject json_data = null;
    private static int flag = 0;
    public static int num_offerte_calcolato = 0;
    private static int tempo_attesa = 0;
}
