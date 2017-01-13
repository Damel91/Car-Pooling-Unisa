package com.example.vincenzo.testfragmentsezioni;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public static Class<MainActivity> mia_activity = MainActivity.class  ;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */


    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }



        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
           // View rootView = inflater.inflate(R.layout.fragment_main, container, false);
          //  TextView textView = (TextView) rootView.findViewById(R.id.section_label);
         //   textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            final ProgressDialog dialog = new ProgressDialog(this.getActivity());

            if (getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)).equals("Hello World from section: 1")) {
                View rootView = inflater.inflate(R.layout.login, container, false);
                final EditText mail = (EditText) rootView.findViewById(R.id.username);
                final EditText password = (EditText) rootView.findViewById(R.id.password);
                final TextView registrati =  (TextView) rootView.findViewById(R.id.reg);
                final Button prova = (Button) rootView.findViewById(R.id.prova);
                Button Loggo = (Button) rootView.findViewById(R.id.loggo);

                Loggo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mail.getText().toString().equals("") || password.getText().toString().equals("")) {    // Controllo se ci sono campi vuoti...e non faccio fare nulla!!
                            Toast.makeText(getActivity(), "Errore,riempi i campi!", Toast.LENGTH_SHORT).show();
                        }
                        else if(mail.getText().toString().equals("") && password.getText().toString().equals("")) {
                            Toast.makeText(getActivity(), "Errore,riempi i campi!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            MailUtente = "";
                            tempo_attesa = 0;
                            flag_area_utente = false;
                            starttask("http://technidos.altervista.org/PhpProject/read.php?mail=" + mail.getText().toString() + "&password=" + password.getText().toString(),getActivity());
                            Boolean bool = true;
                            while(bool){
                                if(tempo_attesa == 1)
                                    break;
                            }

                            if(flag_area_utente == true){
                                Toast.makeText(getActivity(), "Benevenuto: "+ MailUtente, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(
                                        getContext(),
                                        AreaUtente.class
                                );
                                intent.putExtra("MailUtente",MailUtente); //Invio i dati alla nuova intent
                                startActivity(intent);
                            }
                            else
                                Toast.makeText(getActivity(), "Mail o password errate!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                registrati.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(
                                getContext(),
                                Registrazione.class
                        );
                        startActivity(intent);
                    }
                });


                return rootView;
            }

            return null;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 1;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Login";
                case 1:
                    return "Storia";
                case 2:
                    return "Da definire";
            }
            return null;
        }
    }

    private static void starttask(String link,Activity activity) {
        new FileAsync(link,activity).execute("start");
        //   Toast toast = Toast.makeText(Registrazione.this," start:",1);
        // toast.show();

    }

    static class FileAsync extends AsyncTask<Object, Object, String> {

        private String link;
        private Activity activity;
       // ProgressDialog m_ProgressDialog;
        public FileAsync(String link,Activity activity){

            this.link = link;
            this.activity = activity;
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

            if(result.equals("Error")){
                flag_area_utente = false;
            }
            else {
                MailUtente = result.substring(9,result.length()-2);
                flag_area_utente = true;
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
            //m_ProgressDialog.dismiss();
        }
    }

    static String MailUtente = "";
    static volatile boolean flag_area_utente = false;
    static int tempo_attesa = 0;
}
