package com.example.abhinav.medrx;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.abhinav.medrx.Model.Pharmaceuticals;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FormActivity extends AppCompatActivity {

   // private PrefManager prefManager;
    EditText nameEditText;
    EditText usernameEditText;
    EditText contactEditText;
    EditText addressEditText;
    EditText latEditText;
    EditText longEditText;
    EditText medicineEditText;
    EditText expiryEditText;
    Button submitButton;
    Pharmaceuticals pharmaceuticals;
    LocationManager manager;
    LocationListener listener;
    DatabaseReference database;
    Location loc=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        final SimpleDateFormat ISO_8601_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss'Z'");



        manager=(LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        listener=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {



                    loc=location;
                    latEditText.setText(String.valueOf(location.getLatitude()));
                    longEditText.setText(String.valueOf(location.getLongitude()));

                    Log.i("latitude",String.valueOf(location.getLatitude()));
                    Log.i("longitude",String.valueOf(location.getLongitude()));



            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if(checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){


            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }

        else{


            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
        }

        //prefManager = new PrefManager(this);
       // if (!prefManager.isFirstTimeLaunch()) {
        //    launchHomeScreen();
        //    finish();
        //}
        nameEditText=(EditText)findViewById(R.id.nameEditText) ;
        usernameEditText=(EditText)findViewById(R.id.usernameEditText) ;
        contactEditText=(EditText)findViewById(R.id.contactEditText) ;
        addressEditText=(EditText)findViewById(R.id.addressEditText) ;
        latEditText=(EditText)findViewById(R.id.latitudeEditText) ;
        longEditText=(EditText)findViewById(R.id.longitudeEditText) ;
        medicineEditText=(EditText)findViewById(R.id.medicineEditText);
        expiryEditText=(EditText)findViewById(R.id.expiryEditText);



        submitButton=(Button)findViewById(R.id.submitButton);


        nameEditText.setText(MainActivity.user.getDisplayName());
        usernameEditText.setText(MainActivity.user.getEmail());

        database=FirebaseDatabase.getInstance().getReference();

        pharmaceuticals=new Pharmaceuticals();

        final Calendar myCalendar = Calendar.getInstance();

                final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                expiryEditText.setText(sdf.format(myCalendar.getTime()));
            }

        };

        expiryEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(FormActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });




        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pharmaceuticals.setName(nameEditText.getText().toString());
                pharmaceuticals.setUsername(usernameEditText.getText().toString());
                pharmaceuticals.setContact(Integer.parseInt(contactEditText.getText().toString()));
                pharmaceuticals.setAddress(addressEditText.getText().toString());
                pharmaceuticals.setLat(loc.getLatitude());
                pharmaceuticals.setLong(loc.getLongitude());
                pharmaceuticals.setMedicine(medicineEditText.getText().toString());
                pharmaceuticals.setExpirydate(expiryEditText.getText().toString());

                database.child("Pharmaceuticals").push().setValue(pharmaceuticals).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Log.i("evemt","success");

                    }
                });



                launchHomeScreen();
            }
        });


    }

    public void launchHomeScreen(){

        //prefManager.setFirstTimeLaunch(false);

        startActivity(new Intent(FormActivity.this,SideBar.class));


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode==1 ){

            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){

                if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED) {

                    manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);


                }


            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
