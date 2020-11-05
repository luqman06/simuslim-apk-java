package com.example.sinergimuslim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sinergimuslim.adapter.ViewPagerTabAdapter;
import com.example.sinergimuslim.menu.menu_data_anggota.DataAnggotaFragment;
import com.example.sinergimuslim.menu.menu_data_relasi.DataRelasiFragment;
import com.example.sinergimuslim.menu.menu_data_tambah_anggota.TambahAnggotaFragment;
import com.example.sinergimuslim.menu.menu_pengaturan.PengaturanFragment;
import com.example.sinergimuslim.menu.menu_tambah_relasi.TamabahRelasiFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener {

    public static final String tag = MainActivity.class.getSimpleName();
    private ViewPager mViewPager;
    private List<Fragment> mFragments;
    private String username, role, api_key, updated_at;
    TextView kabuapatenPengaturan;
    ImageView imageBtn;

    //map location
//    Double latitude = 0.0;
//    Double longitude = 0.0;
//    static String TAG = "MainActivity";
//    Location gps_loc = null, network_loc = null, final_loc = null;

    public static final String TAG="MainActivity";
    Button getLocationBtn;
    TextView locationText;
    LocationManager locationManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kabuapatenPengaturan = findViewById(R.id.kabupatenPEngaturan);

//        imageBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getLocation();
//            }
//        });



        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }

        getLocation();

        //get lokasi-----
        // GET CURRENT LOCATION



//        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
//                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
//                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(this, "not", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show();
//        }
//
//
//        try {
//            gps_loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//            network_loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if (gps_loc != null) {
//            final_loc = gps_loc;
//            latitude = final_loc.getLatitude();
//            longitude = final_loc.getLongitude();
//        } else if (network_loc != null) {
//            final_loc = network_loc;
//            latitude = final_loc.getLatitude();
//            longitude = final_loc.getLongitude();
//        } else {
//            longitude = 0.0;
//            latitude = 0.0;
//        }
//        tes =  String.valueOf(latitude);
//
//        try {
//            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
//            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
//            if (addresses != null && addresses.size() > 0) {
//                String address = addresses.get(0).getAddressLine(0);
//                String city = addresses.get(0).getLocality();
//                kabuapatenPengaturan.setText(city);
//
//
//
//
//                Toast.makeText(this, tes, Toast.LENGTH_SHORT).show();
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        FusedLocationProviderClient mFusedLocation = LocationServices.getFusedLocationProviderClient(this);
//        mFusedLocation.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
//            @Override
//            public void onSuccess(Location location) {
//                if (location != null){
//                    // Do it all with location
//                    Log.d("My Current location", "Lat : " + location.getLatitude() + " Long : " + location.getLongitude());
//                    // Display in Toast
//                    Toast.makeText(MainActivity.this,
//                            "Lat : " + location.getLatitude() + " Long : " + location.getLongitude(),
//                            Toast.LENGTH_LONG).show();
//                }
//            }
//        });
        //-----


        Intent intentget = getIntent();
        username = intentget.getStringExtra("username");
        role = intentget.getStringExtra("role");
        api_key = intentget.getStringExtra("api_key");
        updated_at = intentget.getStringExtra("updated_at");


        init();


    }
    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5,  this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        //locationText.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());

        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//            locationText.setText(locationText.getText() + "\n"+addresses.get(0).getAddressLine(0)+", "+
//                    addresses.get(0).getAddressLine(1)+", "+addresses.get(0).getAddressLine(2));

            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

            //Log.i(TAG, "Address: "+address + "\n" + "City: "+city + "\n"+"State: " +state+ "\n"+ "Country: "+country+"\n"+ "Postal code: "+postalCode);

            kabuapatenPengaturan.setText(city);
        }catch(Exception e)
        {

        }

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(MainActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }
////////////////////////////////////////////////////////////////////
    private void init() {
        initViews();
    }

    private void initViews() {
        initToolbar();

//      if (role.equals("Admin")) {
//        initViewPager();
//        initTabLayout();
//        Toast.makeText(this, "Admind", Toast.LENGTH_SHORT).show();
//        } else
            if (role.equals("Anggota")) {
            initViewPagerLimite();
            initTabLayoutLimite();
//            Toast.makeText(this, "Anggota", Toast.LENGTH_SHORT).show();
        }else {
          initViewPager();
          initTabLayout();
      }

    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    private void initViewPagerLimite() {
        mViewPager = findViewById(R.id.viewpager);
        List<String> tabNames = new ArrayList<String>();
        tabNames.add("Pengaturan");
        tabNames.add("Data Relasi");
        tabNames.add("Tambah Relasi");
        tabNames.add("Data Anggota");
        ViewPagerTabAdapter viewPagerTabAdapter = new ViewPagerTabAdapter(getSupportFragmentManager(), getFragmentsLimit(), tabNames);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(viewPagerTabAdapter);

        setCurrentItem(mViewPager.getCurrentItem());
    }

    private void initTabLayoutLimite() {
        final TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"));


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tabLayout.getSelectedTabPosition()) {
                    case 0:
                        //do what you want when tab 0 is selected
                        break;
                    case 1:
                        //do what you want when tab 1 is selected
                        break;
                    case 2:
                        //do what you want when tab 2 is selected
                        break;
                    case 3:
                        //do what you want when tab 3 is selected
                        break;
                    case 4:
                        //do what you want when tab 3 is selected
                        break;
                    default:
                        //do what you want when default is selected
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private List<Fragment> getFragmentsLimit() {
        //get fragment
        mFragments = new ArrayList<>();
        mFragments.add(PengaturanFragment.newInstance());
        mFragments.add(DataRelasiFragment.newInstance());
        mFragments.add(TamabahRelasiFragment.newInstance());
        mFragments.add(DataAnggotaFragment.newInstance());

        return mFragments;
    }


    private void initViewPager() {
        mViewPager = findViewById(R.id.viewpager);
        List<String> tabNames = new ArrayList<String>();
        tabNames.add("Pengaturan");
        tabNames.add("Data Relasi");
        tabNames.add("Tambah Relasi");
        tabNames.add("Tambah Anggota");
        tabNames.add("Data Anggota");
        ViewPagerTabAdapter viewPagerTabAdapter = new ViewPagerTabAdapter(getSupportFragmentManager(), getFragments(), tabNames);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(viewPagerTabAdapter);

        setCurrentItem(mViewPager.getCurrentItem());
    }

    //set position when open apps
    private void setCurrentItem(int position) {
        mViewPager.setCurrentItem(position);
    }

    private void initTabLayout() {
        final TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"));


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tabLayout.getSelectedTabPosition()) {
                    case 0:
                        //do what you want when tab 0 is selected
                        break;
                    case 1:
                        //do what you want when tab 1 is selected
                        break;
                    case 2:
                        //do what you want when tab 2 is selected
                        break;
                    case 3:
                        //do what you want when tab 3 is selected
                        break;
                    case 4:
                        //do what you want when tab 4 is seleted
                        break;
                    case 5:
                        //do what you want when tab 4 is seleted
                        break;
                    default:
                        //do what you want when default is selected
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private List<Fragment> getFragments() {
        //get fragment
        mFragments = new ArrayList<>();
        mFragments.add(PengaturanFragment.newInstance());
        mFragments.add(DataRelasiFragment.newInstance());
        mFragments.add(TamabahRelasiFragment.newInstance());
        mFragments.add(TambahAnggotaFragment.newInstance());
        mFragments.add(DataAnggotaFragment.newInstance());

        return mFragments;
    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }


    @Override
    protected void onStart() {
        super.onStart();
        //set tab position one when start Application
        setCurrentItem(1);
        Log.d(tag, " onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tag, " onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(tag, " onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(tag, " onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(tag, " onDestroy()");
    }
}
