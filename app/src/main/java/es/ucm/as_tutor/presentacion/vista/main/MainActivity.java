package es.ucm.as_tutor.presentacion.vista.main;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import es.ucm.as_tutor.R;
import es.ucm.as_tutor.integracion.DBHelper;
import es.ucm.as_tutor.presentacion.controlador.Controlador;
import es.ucm.as_tutor.presentacion.controlador.ListaComandos;
import es.ucm.as_tutor.presentacion.vista.ayuda.FragmentDetalleAyuda;
import es.ucm.as_tutor.presentacion.vista.ayuda.FragmentListadoAyuda;
import es.ucm.as_tutor.presentacion.vista.evento.AdaptadorEventoUsuarios;
import es.ucm.as_tutor.presentacion.vista.evento.FragmentListadoEvento;
import es.ucm.as_tutor.presentacion.vista.tutor.FragmentDetalleTutor;
import es.ucm.as_tutor.presentacion.vista.tutor.FragmentListadoTutor;
import es.ucm.as_tutor.presentacion.vista.usuario.FragmentDetalleNuevoUsuario;
import es.ucm.as_tutor.presentacion.vista.usuario.FragmentListadoUsuario;
import es.ucm.as_tutor.presentacion.vista.usuario.evento.FragmentDetalleUsuarioEvento;
import es.ucm.as_tutor.presentacion.vista.usuario.reto.FragmentDetalleNuevoReto;
import es.ucm.as_tutor.presentacion.vista.usuario.tarea.UsuarioTareasActivity;


public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 3;
    private static final int SELECCIONAR_GALERIA = 2;
    private static final int CAMARA = 1;

    private String[] titulos;
    private DrawerLayout NavDrawerLayout;
    private ListView NavList;
    private ArrayList<Item_Navegacion> NavItms;
    private TypedArray NavIcons;
    private ActionBarDrawerToggle mDrawerToggle;
    private AdaptadorNavegacion NavAdapter;
    private Menu menuActionBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Manager.getInstance().setActivity(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.logo);


        NavDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavList = (ListView) findViewById(R.id.lista);
        View header = getLayoutInflater().inflate(R.layout.header, null);
        NavList.addHeaderView(header);
        NavIcons = getResources().obtainTypedArray(R.array.navigation_iconos);
        titulos = getResources().getStringArray(R.array.nav_options);
        NavItms = new ArrayList<Item_Navegacion>();

        //Usuarios
        NavItms.add(new Item_Navegacion(titulos[0], NavIcons.getResourceId(0, -1)));
        //Eventos
        NavItms.add(new Item_Navegacion(titulos[1], NavIcons.getResourceId(1, -1)));
        //Mi perfil
        NavItms.add(new Item_Navegacion(titulos[2], NavIcons.getResourceId(2, -1)));
        //Ayuda
        NavItms.add(new Item_Navegacion(titulos[3], NavIcons.getResourceId(3, -1)));
        NavAdapter = new AdaptadorNavegacion(this, NavItms);
        NavList.setAdapter(NavAdapter);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                NavDrawerLayout,
                R.string.app_name,
                R.string.hello_world
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                Log.e("Cerrando drawer", "!!");
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                Log.e("Abriendo drawe", "!!");
            }
        };

        // Establecemos que mDrawerToggle declarado anteriormente sea el DrawerListener
        NavDrawerLayout.setDrawerListener(mDrawerToggle);
        //Establecemos que el ActionBar muestre el Boton Home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);*/

        //Establecemos la accion al clickear sobre cualquier item del menu.
        //De la misma forma que hariamos en una app comun con un listview.
        NavList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                MostrarFragment(position);
            }
        });

        //Cuando la aplicacion cargue por defecto mostrar la opcion Home
        //MostrarFragment(1);
        /* cuando inicia empieza con el listado del usuario */

        ArrayList<String> nombres = new ArrayList<String>();
        ArrayList<Integer> imagenes = new ArrayList<Integer>();
        ArrayList<String> dnis = new ArrayList<String>();
        ArrayList<String> direcciones = new ArrayList<String>();
        ArrayList<String> telefonos = new ArrayList<String>();
        ArrayList<String> correos = new ArrayList<String>();
        ArrayList<String> colegios = new ArrayList<String>();
        ArrayList<String> estudios = new ArrayList<String>();
        ArrayList<String> cursos = new ArrayList<String>();
        ArrayList<String> notas = new ArrayList<String>();
        ArrayList<String> puntuaciones = new ArrayList<String>();
        ArrayList<String> nombrePadres = new ArrayList<String>();
        ArrayList<String> nombreMadres = new ArrayList<String>();
        ArrayList<String> telfPadres = new ArrayList<String>();
        ArrayList<String> telfMadres = new ArrayList<String>();
        ArrayList<String> correoPadres = new ArrayList<String>();
        ArrayList<String> correoMadres = new ArrayList<String>();
        ArrayList<String> perfiles = new ArrayList<String>();
        ArrayList<String> sincronizaciones = new ArrayList<String>();

        nombres.add("María Salgado");
        imagenes.add(R.drawable.avatar);
        dnis.add("12345678Q");
        direcciones.add("C/ Alacala 46, 6ºA");
        telefonos.add("678 678 678");
        correos.add("correo@gmail.com");
        colegios.add("Pilar");
        estudios.add("ESO");
        cursos.add("4");
        notas.add("Le gusta el chocolate");
        nombrePadres.add("Manuel");
        nombreMadres.add("Carmen");
        telfPadres.add("666 666 666");
        telfMadres.add("666 666 666");
        correoPadres.add("correo@gmail.com");
        correoMadres.add("correo@gmail.com");
        perfiles.add("Perfil A");
        sincronizaciones.add("VIC001");
        puntuaciones.add("9");

        nombres.add("Juanlu Armas");
        imagenes.add(R.drawable.avatar);
        dnis.add("12345678Q");
        direcciones.add("C/ Alacala 46, 6ºA");
        telefonos.add("678 678 678");
        correos.add("correo@gmail.com");
        colegios.add("Pilar");
        estudios.add("ESO");
        cursos.add("4");
        notas.add("Alergia al huevo");
        nombrePadres.add("Manuel");
        nombreMadres.add("Carmen");
        telfPadres.add("666 666 666");
        telfMadres.add("666 666 666");
        correoPadres.add("correo@gmail.com");
        correoMadres.add("correo@gmail.com");
        perfiles.add("Perfil A");
        sincronizaciones.add("VIC001");
        puntuaciones.add("9");

        nombres.add("Jefferson Almache");
        imagenes.add(R.drawable.avatar);
        dnis.add("12345678Q");
        direcciones.add("C/ Alacala 46, 6ºA");
        telefonos.add("678 678 678");
        correos.add("correo@gmail.com");
        colegios.add("Pilar");
        estudios.add("ESO");
        cursos.add("4");
        notas.add("Alergia al huevo");
        nombrePadres.add("Manuel");
        nombreMadres.add("Carmen");
        telfPadres.add("666 666 666");
        telfMadres.add("666 666 666");
        correoPadres.add("correo@gmail.com");
        correoMadres.add("correo@gmail.com");
        perfiles.add("Perfil A");
        sincronizaciones.add("VIC001");
        puntuaciones.add("9");

        nombres.add("Marta García");
        imagenes.add(R.drawable.avatar);
        dnis.add("12345678Q");
        direcciones.add("C/ Alacala 46, 6ºA");
        telefonos.add("678 678 678");
        correos.add("correo@gmail.com");
        colegios.add("Pilar");
        estudios.add("ESO");
        cursos.add("4");
        notas.add("Alergia al huevo");
        nombrePadres.add("Manuel");
        nombreMadres.add("Carmen");
        telfPadres.add("666 666 666");
        telfMadres.add("666 666 666");
        correoPadres.add("correo@gmail.com");
        correoMadres.add("correo@gmail.com");
        perfiles.add("Perfil A");
        sincronizaciones.add("VIC001");
        puntuaciones.add("9");

        Bundle arguments = new Bundle();
        arguments.putStringArrayList("nombres", nombres);
        arguments.putIntegerArrayList("imagenes", imagenes);
        arguments.putStringArrayList("dnis", dnis);
        arguments.putStringArrayList("direcciones", direcciones);
        arguments.putStringArrayList("telefonos", telefonos);
        arguments.putStringArrayList("correos", correos);
        arguments.putStringArrayList("colegios", colegios);
        arguments.putStringArrayList("estudios", estudios);
        arguments.putStringArrayList("cursos", cursos);
        arguments.putStringArrayList("notas", notas);
        arguments.putStringArrayList("nombrePadres", nombrePadres);
        arguments.putStringArrayList("nombreMadres", nombreMadres);
        arguments.putStringArrayList("telfPadres", telfPadres);
        arguments.putStringArrayList("telfMadres", telfMadres);
        arguments.putStringArrayList("correoPadres", correoPadres);
        arguments.putStringArrayList("correoMadres", correoMadres);
        arguments.putStringArrayList("perfiles", perfiles);
        arguments.putStringArrayList("sincronizaciones", sincronizaciones);
        arguments.putStringArrayList("puntuaciones", puntuaciones);
        BlankFragment fragmentBlank = new BlankFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.FrgDetalle, fragmentBlank).commit();

        FragmentListadoUsuario frgListado = new FragmentListadoUsuario();
        frgListado.setArguments(arguments);
        getSupportFragmentManager().beginTransaction().replace(R.id.FrgListado, frgListado).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuActionBar = menu;
        getMenuInflater().inflate(R.menu.menu_usuario, menu);
        return true;
    }


    private void MostrarFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 1: // Usuarios
                //Fragmento en blanco
                BlankFragment fragmentBlank = new BlankFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.FrgDetalle, fragmentBlank).commit();

                ArrayList<String> nombres = new ArrayList<String>();
                ArrayList<Integer> imagenes = new ArrayList<Integer>();
                ArrayList<String> dnis = new ArrayList<String>();
                ArrayList<String> direcciones = new ArrayList<String>();
                ArrayList<String> telefonos = new ArrayList<String>();
                ArrayList<String> correos = new ArrayList<String>();
                ArrayList<String> colegios = new ArrayList<String>();
                ArrayList<String> estudios = new ArrayList<String>();
                ArrayList<String> cursos = new ArrayList<String>();
                ArrayList<String> notas = new ArrayList<String>();
                ArrayList<String> nombrePadres = new ArrayList<String>();
                ArrayList<String> nombreMadres = new ArrayList<String>();
                ArrayList<String> telfPadres = new ArrayList<String>();
                ArrayList<String> telfMadres = new ArrayList<String>();
                ArrayList<String> correoPadres = new ArrayList<String>();
                ArrayList<String> correoMadres = new ArrayList<String>();
                ArrayList<String> perfiles = new ArrayList<String>();
                ArrayList<String> puntuaciones = new ArrayList<String>();
                ArrayList<String> sincronizaciones = new ArrayList<String>();

                nombres.add("María Salgado");
                imagenes.add(R.drawable.avatar);
                dnis.add("12345678Q");
                direcciones.add("C/ Alacala 46, 6ºA");
                telefonos.add("678 678 678");
                correos.add("correo@gmail.com");
                colegios.add("Pilar");
                estudios.add("ESO");
                cursos.add("4");
                notas.add("Alergia al huevo");
                nombrePadres.add("Manuel");
                nombreMadres.add("Carmen");
                telfPadres.add("666 666 666");
                telfMadres.add("666 666 666");
                correoPadres.add("correo@gmail.com");
                correoMadres.add("correo@gmail.com");
                perfiles.add("Perfil A");
                sincronizaciones.add("VIC001");
                puntuaciones.add("9");

                nombres.add("Juanlu Armas");
                imagenes.add(R.drawable.avatar);
                dnis.add("12345678Q");
                direcciones.add("C/ Alacala 46, 6ºA");
                telefonos.add("678 678 678");
                correos.add("correo@gmail.com");
                colegios.add("Pilar");
                estudios.add("ESO");
                cursos.add("4");
                notas.add("Alergia al huevo");
                nombrePadres.add("Manuel");
                nombreMadres.add("Carmen");
                telfPadres.add("666 666 666");
                telfMadres.add("666 666 666");
                correoPadres.add("correo@gmail.com");
                correoMadres.add("correo@gmail.com");
                perfiles.add("Perfil A");
                sincronizaciones.add("VIC001");
                puntuaciones.add("9");

                nombres.add("Jefferson Almache");
                imagenes.add(R.drawable.avatar);
                dnis.add("12345678Q");
                direcciones.add("C/ Alacala 46, 6ºA");
                telefonos.add("678 678 678");
                correos.add("correo@gmail.com");
                colegios.add("Pilar");
                estudios.add("ESO");
                cursos.add("4");
                notas.add("Alergia al huevo");
                nombrePadres.add("Manuel");
                nombreMadres.add("Carmen");
                telfPadres.add("666 666 666");
                telfMadres.add("666 666 666");
                correoPadres.add("correo@gmail.com");
                correoMadres.add("correo@gmail.com");
                perfiles.add("Perfil A");
                sincronizaciones.add("VIC001");
                puntuaciones.add("9");

                nombres.add("Marta García");
                imagenes.add(R.drawable.avatar);
                dnis.add("12345678Q");
                direcciones.add("C/ Alacala 46, 6ºA");
                telefonos.add("678 678 678");
                correos.add("correo@gmail.com");
                colegios.add("Pilar");
                estudios.add("ESO");
                cursos.add("4");
                notas.add("Alergia al huevo");
                nombrePadres.add("Manuel");
                nombreMadres.add("Carmen");
                telfPadres.add("666 666 666");
                telfMadres.add("666 666 666");
                correoPadres.add("correo@gmail.com");
                correoMadres.add("correo@gmail.com");
                perfiles.add("Perfil A");
                sincronizaciones.add("VIC001");
                puntuaciones.add("9");

                Bundle arguments = new Bundle();
                arguments.putStringArrayList("nombres", nombres);
                arguments.putIntegerArrayList("imagenes", imagenes);
                arguments.putStringArrayList("dnis", dnis);
                arguments.putStringArrayList("direcciones", direcciones);
                arguments.putStringArrayList("telefonos", telefonos);
                arguments.putStringArrayList("correos", correos);
                arguments.putStringArrayList("colegios", colegios);
                arguments.putStringArrayList("estudios", estudios);
                arguments.putStringArrayList("cursos", cursos);
                arguments.putStringArrayList("notas", notas);
                arguments.putStringArrayList("nombrePadres", nombrePadres);
                arguments.putStringArrayList("nombreMadres", nombreMadres);
                arguments.putStringArrayList("telfPadres", telfPadres);
                arguments.putStringArrayList("telfMadres", telfMadres);
                arguments.putStringArrayList("correoPadres", correoPadres);
                arguments.putStringArrayList("correoMadres", correoMadres);
                arguments.putStringArrayList("perfiles", perfiles);
                arguments.putStringArrayList("sincronizaciones", sincronizaciones);
                arguments.putStringArrayList("puntuaciones", puntuaciones);

                FragmentListadoUsuario fragmentListaUsuario = new FragmentListadoUsuario();
                fragmentListaUsuario.setArguments(arguments);
                getSupportFragmentManager().beginTransaction().replace(R.id.FrgListado, fragmentListaUsuario).commit();

                NavList.setItemChecked(position, true);
                NavList.setSelection(position);
                //Cambiamos el titulo en donde decia "
                setTitle(titulos[position - 1]);
                //Cerramos el menu deslizable
                NavDrawerLayout.closeDrawer(NavList);
                break;
            case 2: // Eventos


                //Fragmento en blanco
                BlankFragment fragmentDetalleTarea = new BlankFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.FrgDetalle, fragmentDetalleTarea).commit();

                Controlador.getInstancia().ejecutaComando(ListaComandos.LISTADO_EVENTOS,null);



                NavList.setItemChecked(position, true);
                NavList.setSelection(position);
                //Cambiamos el titulo en donde decia "
                setTitle(titulos[position - 1]);
                //Cerramos el menu deslizable
                NavDrawerLayout.closeDrawer(NavList);
                break;
            case 3: // Mi perfil
                menuActionBar.clear();

                FragmentDetalleTutor fragmentDetalleTutor = new FragmentDetalleTutor();
                //fragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction().replace(R.id.FrgDetalle, fragmentDetalleTutor).commit();

                FragmentListadoTutor fragmentListadoTutor = new FragmentListadoTutor();
                //fragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction().replace(R.id.FrgListado, fragmentListadoTutor).commit();

                NavList.setItemChecked(position, true);
                NavList.setSelection(position);
                //Cambiamos el titulo en donde decia "
                setTitle(titulos[position - 1]);
                //Cerramos el menu deslizable
                NavDrawerLayout.closeDrawer(NavList);
                break;
            case 4: // Ayuda
                menuActionBar.clear();

                FragmentDetalleAyuda fragmentDetalleAyuda = new FragmentDetalleAyuda();
                //fragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction().replace(R.id.FrgDetalle, fragmentDetalleAyuda).commit();

                FragmentListadoAyuda fragmentListadoAyuda = new FragmentListadoAyuda();
                //fragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction().replace(R.id.FrgListado, fragmentListadoAyuda).commit();

                NavList.setItemChecked(position, true);
                NavList.setSelection(position);
                setTitle(titulos[position - 1]);
                NavDrawerLayout.closeDrawer(NavList);
                break;

            default:
                //si no esta la opcion mostrara un toast y nos mandara a Home
                Toast.makeText(getApplicationContext(), "Opcion " + titulos[position - 1] + "no disponible!", Toast.LENGTH_SHORT).show();

                position = 1;
                break;
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            //fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            // Actualizamos el contenido segun la opcion elegida
            NavList.setItemChecked(position, true);
            NavList.setSelection(position);
            //Cambiamos el titulo en donde decia "
            setTitle(titulos[position - 1]);
            //Cerramos el menu deslizable
            NavDrawerLayout.closeDrawer(NavList);
        } else {
            //Si el fragment es nulo mostramos un mensaje de error.
            Log.e("Error  ", "MostrarFragment" + position);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
         }else {
            switch (item.getItemId()) {
                case R.id.tareasUsuario:
                    Intent intent = new Intent(this, UsuarioTareasActivity.class);
                    startActivity(intent);
                    break;
                case R.id.retoUsuario:
                    //Aqui iria un if/else
                   /*FragmentDetalleReto fragmentReto = new FragmentDetalleReto();
                    getSupportFragmentManager().beginTransaction().replace(R.id.FrgDetalle, fragmentReto).commit();*/
                    FragmentDetalleNuevoReto fragmentNuevoReto = new FragmentDetalleNuevoReto();
                    getSupportFragmentManager().beginTransaction().replace(R.id.FrgDetalle, fragmentNuevoReto).commit();
                    break;
                case R.id.eventosUsuario:
                    FragmentDetalleUsuarioEvento fragmentEventoUsuario = new FragmentDetalleUsuarioEvento();
                    getSupportFragmentManager().beginTransaction().replace(R.id.FrgDetalle, fragmentEventoUsuario).commit();
                    break;
                case R.id.enviarCorreo:
                    // aquí habrá que ejecutar el comando de enviar correo

                    // Enviar correo abriendo aplicación/////////////////////////////////////////////////////
                    //Instanciamos un Intent del tipo ACTION_SEND
                    Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                    //Definimos la tipologia de datos del contenido dle Email en este caso text/html
                    emailIntent.setType("application/pdf");
                    // Indicamos con un Array de tipo String las direcciones de correo a las cuales
                    //queremos enviar el texto
                    //emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{mail});
                    // Definimos un titulo para el Email
                    emailIntent.putExtra(android.content.Intent.EXTRA_TITLE, "Informe AS");
                    // Definimos un Asunto para el Email
                    emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Informe AS");
                    // Obtenemos la referencia al texto y lo pasamos al Email Intent
                    emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "¡Hola " + /*name + */"!\n " +
                            "Este es tu progreso hasta el momento. Sigue esforzándote para continuar mejorando."
                            + "\n¡Ánimo!" + "\n\nEnviado desde AS");

                    Uri uri = Uri.parse( new File("file://" + "/sdcard/Download/AS/Informe.pdf").toString());
                    emailIntent.putExtra(Intent.EXTRA_STREAM, uri);

                    getApplicationContext().startActivity(emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                    ///////////////////////////////////////////////////////////////////////////////////////////

                    break;
                case R.id.eliminarUsuario:
                    //aqui habrá que ejecutar el comando de eliminar usuario

                default:
                    return super.onOptionsItemSelected(item);
            }
        }
        return true;
    }
    public void nuevoEvento(View view){
        menuActionBar.clear();
        getMenuInflater().inflate(R.menu.menu_usuario, menuActionBar);
        Controlador.getInstancia().ejecutaComando(ListaComandos.CREAR_EVENTO_CONSULTAR_USUARIOS,null);
    }

    public void nuevoUsuario(View view){
        FragmentDetalleNuevoUsuario fragmentNuevoUsuario = new FragmentDetalleNuevoUsuario();
        getSupportFragmentManager().beginTransaction().replace(R.id.FrgDetalle, fragmentNuevoUsuario).commit();
    }

    public void infoPadre(View view){
        AlertDialog a = createInfoProgenitoresDialogo("padre");
        a.show();
    }

    public void infoMadre(View view){
        AlertDialog a = createInfoProgenitoresDialogo("madre");
        a.show();
    }

    public AlertDialog createInfoProgenitoresDialogo(String who) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_info_padres, null);

        EditText name = (EditText) v.findViewById(R.id.name);
        EditText phone = (EditText) v.findViewById(R.id.phone);
        EditText mail = (EditText) v.findViewById(R.id.mail);
        builder.setView(v);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Acciones
            }
        });
        builder.setNegativeButton("Cancelar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        if (who.equals("padre"))
            builder.setTitle("Información del padre");
        else
            builder.setTitle("Información de la madre");
        return builder.create();
    }

    public void cambiarImagenPerfil(View v) {
        final CharSequence[] items = { "Hacer foto", "Elegir de la galeria", "Imagen por defecto" };
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Hacer foto")) {
                    Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, CAMARA);
                } else if (items[item].equals("Elegir de la galeria")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            SELECCIONAR_GALERIA);
                } else if (items[item].equals("Imagen por defecto")) {
                    ImageView iv = (ImageView) findViewById(R.id.avatar);
                    iv.setImageResource(R.drawable.avatar);
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
}