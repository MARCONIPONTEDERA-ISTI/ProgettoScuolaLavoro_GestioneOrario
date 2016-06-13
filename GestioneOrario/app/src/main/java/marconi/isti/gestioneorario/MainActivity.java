package marconi.isti.gestioneorario;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import parser.Orario;
import parser.Professore;
import parser.TabellaOrario;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TabellaOrario tb;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FileInputStream stream = null;
        try {
            stream = getApplicationContext().openFileInput("TabellaOrario.data");
            ObjectInputStream dout = new ObjectInputStream(stream);
            tb = (TabellaOrario) dout.readObject();

            stream.getFD().sync();
        } catch (Exception e) {
        }//Do something intelligent }
        finally {
            try {
                if (stream != null)
                    stream.close();
            } catch (IOException e2) {
            }
        }


        aggiornaElement();

      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void aggiornaElement() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                update();
                Toast.makeText(getApplicationContext(),
                        "Download Completato", Toast.LENGTH_SHORT)
                        .show();

            }
        });


    }


    private void update() {
        if (tb != null) {

            //spinner materie
            Set<String> materie = tb.getMaterie();
            Spinner pinner = (Spinner) findViewById(R.id.spinnerMateria);
            Context c = (Context) getApplicationContext();
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(c, R.layout.spinnercustom, new ArrayList<String>(materie)); //selected item will look like a spinner set from XML
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            pinner.setAdapter(spinnerArrayAdapter);

            //Prof
            ArrayList<String> professori = tb.getProfessori();
            Spinner pinnerf = (Spinner) findViewById(R.id.spinnerProf);
            ArrayAdapter<String> spinnerfArrayAdapter = new ArrayAdapter<String>(c,R.layout.spinnercustom, professori); //selected item will look like a spinner set from XML
            spinnerfArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            pinnerf.setAdapter(spinnerfArrayAdapter);

            //Aule
            ArrayList<String> aule = tb.getAule();
            Spinner pinnera = (Spinner) findViewById(R.id.spinnerAula);
            ArrayAdapter<String> spinneraArrayAdapter = new ArrayAdapter<String>(c, R.layout.spinnercustom, aule); //selected item will look like a spinner set from XML
            spinneraArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            pinnera.setAdapter(spinneraArrayAdapter);

            //Classi
            ArrayList<String> classi = tb.getClassi();
            Spinner pinnerc = (Spinner) findViewById(R.id.spinnerClasse);
            ArrayAdapter<String> spinnercArrayAdapter = new ArrayAdapter<String>(c,R.layout.spinnercustom, classi); //selected item will look like a spinner set from XML
            spinnercArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            pinnerc.setAdapter(spinnercArrayAdapter);




        }
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

    public void goToDATAUI(View v) {
        Spinner materia = (Spinner)findViewById(R.id.spinnerMateria);
        Spinner prof = (Spinner)findViewById(R.id.spinnerProf);
        Spinner aula = (Spinner) findViewById(R.id.spinnerAula);
        Spinner classe = (Spinner) findViewById(R.id.spinnerClasse);
        Spinner giorno = (Spinner) findViewById(R.id.spinnerGiorno);



        Intent i = new Intent(MainActivity.this, DataUIActivity.class);
        if(tb!=null){
            int g = giorno.getSelectedItemPosition();
            String pf = prof.getSelectedItem().toString();

            ArrayList<Orario> lo = tb.SearchbyProf(pf,g+1);
            i.putExtra("ListaOrari", lo);
            i.putExtra("giorno",tb.getGiorno(g+1));
            i.putExtra("tipo",pf);
        }


        startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            Intent i = new Intent(MainActivity.this, ActivitySetting.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_setting) {

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.getMenu().findItem(R.id.nav_setting).setChecked(false);
            AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(this);

// Setting Dialog Title
            alertDialog2.setTitle("Confirm Update Data...");

// Setting Dialog Message
            alertDialog2.setMessage("Are you sure you want update data?");

// Setting Icon to Dialog
            //   alertDialog2.setIcon(R.drawable.delete);

// Setting Positive "Yes" Btn
            alertDialog2.setPositiveButton("YES",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to execute after dialog
                            readAndSaveData();
                            Toast.makeText(getApplicationContext(),
                                    "You clicked on YES", Toast.LENGTH_SHORT)
                                    .show();
                        }
                    });

// Setting Negative "NO" Btn
            alertDialog2.setNegativeButton("NO",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to execute after dialog
                            Toast.makeText(getApplicationContext(),
                                    "NO data Saved", Toast.LENGTH_SHORT)
                                    .show();
                            dialog.cancel();
                        }
                    });

// Showing Alert Dialog
            alertDialog2.show();

            /*Intent i = new Intent(MainActivity.this, ActivitySetting.class);
            startActivity(i);*/
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void readAndSaveData() {

        new Thread(new ClientThreadAggOrario()).start();


    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://marconi.isti.gestioneorario/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://marconi.isti.gestioneorario/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    class ClientThreadAggOrario implements Runnable {

        @Override
        public void run() {
            tb = new TabellaOrario("http://www.marconipontedera.it/dcb/doceboCore/orario/index.html");
            tb.read();
            aggiornaElement();

            FileOutputStream stream = null;
            try {
                stream = getApplicationContext().openFileOutput("TabellaOrario.data", Context.MODE_PRIVATE);
                ObjectOutputStream dout = new ObjectOutputStream(stream);
                dout.writeObject(tb);
                dout.flush();
                stream.getFD().sync();

            } catch (IOException e) {
            }//Do something intelligent }
            finally {
                try {
                    stream.close();
                } catch (IOException e2) {
                }
            }
        }

    }
}
