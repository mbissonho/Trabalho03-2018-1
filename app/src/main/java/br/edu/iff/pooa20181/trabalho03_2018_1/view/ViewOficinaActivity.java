package br.edu.iff.pooa20181.trabalho03_2018_1.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import br.edu.iff.pooa20181.trabalho03_2018_1.R;
import br.edu.iff.pooa20181.trabalho03_2018_1.model.Mecanico;
import br.edu.iff.pooa20181.trabalho03_2018_1.model.Oficina;
import io.realm.Realm;

public class ViewOficinaActivity extends FragmentActivity implements OnMapReadyCallback {

    private Button toggle, ret;
    private GoogleMap map;
    ZoomControls zoomCtrl;
    Double deviceLat = null, deviceLon = null;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private final static int MY_PERMISSION_FINE_LOCATION = 101;
    private static final int MY_PERMISSION_REQUEST_FINE_LOCATION = 101;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private boolean updatesOn = false;

    private Realm realm;
    private int id;
    private Oficina oficina = null;

    private TextView lNome, lRua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_oficina);
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        this.fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        this.locationRequest = new LocationRequest();
        this.locationRequest.setInterval(1000);
        this.locationRequest.setFastestInterval(1000);
        this.locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        Intent intent = this.getIntent();
        this.id = (int) intent.getSerializableExtra("id");
        this.realm = Realm.getDefaultInstance();

        this.oficina = realm.where(Oficina.class).equalTo("id",this.id).findFirst();

        this.locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        deviceLat  = location.getLatitude();
                        deviceLon = location.getLongitude();
                    }
                }
            }
        };

        this.zoomCtrl = findViewById(R.id.zoomCtrl);
        this.zoomCtrl.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                map.animateCamera(CameraUpdateFactory.zoomOut());
            }
        });

        this.zoomCtrl.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                map.animateCamera(CameraUpdateFactory.zoomIn());

            }
        });


        this.toggle = findViewById(R.id.btnToggle);
        this.toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (map.getMapType() == GoogleMap.MAP_TYPE_NORMAL) {
                    map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                    toggle.setText("NORM");
                } else {
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    toggle.setText("SAT");
                }

            }
        });

        this.ret = findViewById(R.id.btnRet);
        this.ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewOficinaActivity.this.setLocal(ViewOficinaActivity.this.oficina);
            }
        });

        this.bindAndSet();
    }

    private void bindAndSet(){
        //bind
        this.lNome = findViewById(R.id.lNome);
        this.lRua = findViewById(R.id.lRua);

        //set
        this.lNome.setText(this.oficina.getNome());
        this.lRua.setText(this.oficina.getRua());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;

        this.map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                map.addMarker(new MarkerOptions().position(latLng).title("from onMapClick"));
                map.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        });

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);
            }
        }

        this.setLocal(this.oficina);
    }

    private void setLocal(Oficina oficina){
        if (oficina != null){
            LatLng latLng = new LatLng(oficina.getLatitude(), oficina.getLongitude());
            this.map.addMarker(new MarkerOptions().position(latLng).title("from geocoder"));
            this.map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,18.0f));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSION_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        map.setMyLocationEnabled(true);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "This app requires location permissions to be granted", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    private void stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (updatesOn) startLocationUpdates();
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_FINE_LOCATION);
            }
        }

    }
}
