package com.ascendant.e_businessprofile.Activity.ui.Healthcare.Ecosystem.ListOfHospital;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.LocationRequest;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.R;
import com.ascendant.e_businessprofile.databinding.ActivityMapsBinding;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsHospitalFragment extends Fragment implements OnMapReadyCallback {
    TextView Hospital,Bed;
    String PROVINSI_RS,KAB_KOTA_RS,KELAS,SEARCH;
    DB_Helper dbHelper;
    String Token;


    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private GoogleMap mMap;
    private MapView mMapView;
    public MapsHospitalFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_maps_hospital, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Hospital = view.findViewById(R.id.tvJumlahRS);
        Bed = view.findViewById(R.id.tvJumlahBed);
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        Bundle bundle = getArguments();
        PROVINSI_RS = bundle.getString("PROVINSI_RS");
        KAB_KOTA_RS = bundle.getString("KAB_KOTA_RS");
        KELAS = bundle.getString("KELAS");
        SEARCH = bundle.getString("SEARCH");

        LatLng latLng = new LatLng(-6.2468965, 115.2898833);
        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.kelasa);
        Bitmap b=bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b,1,1,false);
        MarkerOptions markerOptions = new MarkerOptions().position(latLng)
                .title("Test")
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,4F));
        googleMap.addMarker(markerOptions);
    }
}