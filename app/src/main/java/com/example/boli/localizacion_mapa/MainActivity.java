package com.example.boli.localizacion_mapa;

import android.app.Activity;
import android.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends ActionBarActivity implements GoogleMap.OnMapClickListener  {

    // SE DECLARAN VARIABLES
    private GoogleMap mapa;

    private final LatLng CASA = new LatLng(18.3989219, -100.30982025);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       //SE ENLAZA EL MAPFRAGMENT
        mapa = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapa)).getMap();
        mapa.setMapType((GoogleMap.MAP_TYPE_SATELLITE));

        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(CASA, 15));

        // SE ABILITAN LOS CONTROLES.
        mapa.setMyLocationEnabled(true);
        mapa.getUiSettings().setZoomControlsEnabled(true);
        mapa.getUiSettings().setMyLocationButtonEnabled(true);
        mapa.getUiSettings().setCompassEnabled(true);
        mapa.getUiSettings().setRotateGesturesEnabled(true);
        mapa.getUiSettings().setZoomGesturesEnabled(true);


   //  Muestra un titulo cuando se esta en las cordenadas establecidas.
        mapa.addMarker( new MarkerOptions().position(CASA).title("Mi Hogar").snippet("Benito Juarez #21").icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_menu_compass)).anchor(0.5f, 0.5f));
        mapa.setOnMapClickListener(this);
    }

    //MANDA A LA POSICION DE LA CORDENADAS ESTABLECIDAS
    public void moveCamera(View view) {
        mapa.moveCamera(CameraUpdateFactory.newLatLng(CASA));
    }

    //METODO QUE MUESTRA LA POSICION ACTUAL
    public void animateCamera(View view) {
        if (mapa.getMyLocation() != null)
           mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(
                   new LatLng(mapa.getMyLocation().getLatitude(), mapa.getMyLocation().getLongitude()), 15));
    }

    //COLOCA EL MARCADOR DONDE SE LE INDICA EN EL MAPA CUANDO SE PRESIONA EL BOTON MARCADOR
    public void addMarker(View view) {
        mapa.addMarker(new MarkerOptions().position(
                new LatLng(mapa.getCameraPosition().target.latitude,
                        mapa.getCameraPosition().target.longitude)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //METODO PARA DARLE FUNCIONALIDAD A LOS MENUS
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            mapa.setMapType((GoogleMap.MAP_TYPE_TERRAIN));
            return true;
        }
        if (id == R.id.action_settings2) {
            mapa.setMapType((GoogleMap.MAP_TYPE_HYBRID));
            return true;
        }
        if (id == R.id.action_settings3) {
            mapa.setMapType((GoogleMap.MAP_TYPE_SATELLITE));
            return true;
        }
        if (id == R.id.action_settings4) {
            mapa.setMapType((GoogleMap.MAP_TYPE_NORMAL));
            return true;
        }
        if (id == R.id.action_settings5) {
            mapa.setMapType((GoogleMap.MAP_TYPE_NONE));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

 //METODO PARA CREAR UN MARCADOR CUANDO SE TOCA UN LUGAR EN EL MAPA Y SE LE ASIGNA UN COLOR DIFERENTE.
    @Override
    public void onMapClick(LatLng puntoPulsado) {
       mapa.addMarker(new MarkerOptions().position(puntoPulsado).
               icon(BitmapDescriptorFactory
                       .defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
    }
}
