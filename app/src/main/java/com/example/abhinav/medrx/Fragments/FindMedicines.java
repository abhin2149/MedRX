package com.example.abhinav.medrx.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.abhinav.medrx.Model.Pharmaceuticals;
import com.example.abhinav.medrx.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FindMedicines extends Fragment {

    private DatabaseReference dbref;
    private ArrayList<Pharmaceuticals> pharmaceuticals;
    EditText findMedicine;
    ArrayAdapter arrayAdapter;
    ArrayList<String> info;
    ListView newsList;
    Button search;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View mView=inflater.inflate(R.layout.findmedicines,container,false);

        pharmaceuticals=new ArrayList<>();
        findMedicine=(EditText)mView.findViewById(R.id.searchMedicine);
        dbref= FirebaseDatabase.getInstance().getReference();

        search=(Button)mView.findViewById(R.id.searchButton);
        newsList=(ListView)mView.findViewById(R.id.searchList);

        info=new ArrayList<>();

        arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, info);

        newsList.setAdapter(arrayAdapter);



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbref.child("Pharmaceuticals").orderByChild("medicine").equalTo(findMedicine.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> docList = dataSnapshot.getChildren();

                        Log.i("number",docList+"");


                        GenericTypeIndicator<ArrayList<Pharmaceuticals>> genericTypeIndicator =new GenericTypeIndicator<ArrayList<Pharmaceuticals>>(){};

                        ArrayList<Pharmaceuticals> taskDesList=dataSnapshot.getValue(genericTypeIndicator);


                        for(int i=0;i<taskDesList.size();i++){

                            pharmaceuticals.add(taskDesList.get(i));
                            info.add(taskDesList.get(i).getName()+" "+taskDesList.get(i).getAddress());
                            Log.i("name",taskDesList.get(i).getName());

                        }
                        arrayAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });






        return super.onCreateView(inflater, container, savedInstanceState);

    }
}
