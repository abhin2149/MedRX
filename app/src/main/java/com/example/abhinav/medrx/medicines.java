package com.example.abhinav.medrx;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.abhinav.medrx.Model.Pharmaceuticals;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class medicines extends AppCompatActivity {
    private DatabaseReference dbref;
    private ArrayList<Pharmaceuticals> pharmaceuticals;
    EditText findMedicine;
    ArrayAdapter arrayAdapter;
    ArrayList<String> info;
    ListView newsList;
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines);


        pharmaceuticals=new ArrayList<>();
        findMedicine=(EditText)findViewById(R.id.searchMedicine);
        dbref= FirebaseDatabase.getInstance().getReference();

        search=(Button)findViewById(R.id.searchButton);
        newsList=(ListView)findViewById(R.id.searchList);

        info=new ArrayList<>();

        arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, info);

        newsList.setAdapter(arrayAdapter);



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbref.child("Pharmaceuticals").orderByChild("medicine").equalTo(findMedicine.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> docList = dataSnapshot.getChildren();

                        Log.i("number",docList+"");


                        //GenericTypeIndicator<List<Pharmaceuticals>> genericTypeIndicator =new GenericTypeIndicator<List<Pharmaceuticals>>(){};

                        //List<Pharmaceuticals> taskDesList=dataSnapshot.getValue(genericTypeIndicator);



                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                            String bookName = postSnapshot.child("medicine").getValue(String.class);
                            String address=postSnapshot.child("address").getValue(String.class);
                            String contact=postSnapshot.child("contact").getValue(String.class);
                            //Use the dataType you are using and also use the reference of those childs inside arrays\\

                            // Putting Data into Getter Setter \\


                            info.add(bookName+" "+address+ " "+contact);

                        }


                        arrayAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}
