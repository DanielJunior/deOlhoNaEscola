package br.com.danieljunior.deolhonaescola;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import br.com.danieljunior.deolhonaescola.models.School;
import br.com.danieljunior.deolhonaescola.utils.MapsUtils;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    School school;
    ArrayList<School> listSchool;
    LatLng current = new LatLng(-22.9027800, -43.2075000);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        try {
            LocationManager mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000,
                    100, this);
        } catch (SecurityException e) {

        } finally {
            if (getIntent().hasExtra("school")) {
                school = (School) getIntent().getExtras().getBundle("school").get("school");
            } else if (getIntent().hasExtra("list")) {
                listSchool = getIntent().getParcelableArrayListExtra("list");
            }
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (school != null) {
            setSchool();
        } else if (listSchool != null) {
            for (School school : listSchool) {
                setSchool(school);
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLng(current));
            mMap.moveCamera(CameraUpdateFactory.zoomTo(14));
            final Context ctx = this;
            mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

                @Override
                public void onInfoWindowClick(Marker marker) {
                    School s = findByName(marker.getTitle());
                    Intent intent = new Intent(ctx, SchoolDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("school", s);
                    intent.putExtra("school", bundle);
                    startActivity(intent);
                }
            });
        }

    }

    private void setSchool(School school) {
        MarkerOptions marker = MapsUtils.createMarker(school);
        mMap.addMarker(marker);
    }

    public void setSchool() {
        // Add a marker in Sydney and move the camera
        MarkerOptions marker = MapsUtils.createMarker(school);
        mMap.addMarker(marker);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(14));
        final Context ctx = this;
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(ctx, SchoolDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("school", school);
                intent.putExtra("school", bundle);
                startActivity(intent);
            }
        });
    }

    public School findByName(String name) {
        for (School s : listSchool) {
            if (s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null;
    }


    @Override
    public void onLocationChanged(Location location) {
        current = new LatLng(location.getLatitude(), location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
