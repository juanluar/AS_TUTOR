package es.ucm.as_tutor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements FragmentListado.CorreosListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentListado frgListado =
                (FragmentListado)getSupportFragmentManager()
                        .findFragmentById(R.id.FrgListado);

        frgListado.setCorreosListener(this);
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

    @Override
    public void onCorreoSeleccionado(Usuario c) {
        boolean hayDetalle =
                (getSupportFragmentManager().findFragmentById(R.id.FrgDetalle) != null);

        if(hayDetalle) {
            ((FragmentDetalle)getSupportFragmentManager()
                    .findFragmentById(R.id.FrgDetalle)).mostrarDetalle(c.getTexto());
        }
        else {
            Intent i = new Intent(this, DetalleActivity.class);
            i.putExtra(DetalleActivity.EXTRA_TEXTO, c.getTexto());
            startActivity(i);
        }
    }
}