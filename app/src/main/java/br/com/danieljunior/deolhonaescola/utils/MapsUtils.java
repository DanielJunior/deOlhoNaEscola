package br.com.danieljunior.deolhonaescola.utils;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.danieljunior.deolhonaescola.R;
import br.com.danieljunior.deolhonaescola.models.School;

/**
 * Created by danieljunior on 10/01/17.
 */

public class MapsUtils {

    public static MarkerOptions createMarker(School school){
        LatLng latLng = new LatLng(school.getLatitude(), school.getLongitude());
        MarkerOptions marker = new MarkerOptions()
                .position(latLng)
                .anchor(0.5f, 0.5f)
                .title(school.getName())
                .snippet("Ver Detalhes")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_local_library_black));
        return marker;
    }
}
