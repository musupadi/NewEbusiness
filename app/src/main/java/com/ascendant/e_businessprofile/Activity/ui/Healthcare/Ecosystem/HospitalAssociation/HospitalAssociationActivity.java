package com.ascendant.e_businessprofile.Activity.ui.Healthcare.Ecosystem.HospitalAssociation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ascendant.e_businessprofile.Adapter.AdapterHospitalAssociation;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Ecosystem.AsosiasiPersiData;
import com.ascendant.e_businessprofile.Model.StaticModel.Ecosystem.HospitalAssociationModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Ecosystem.ListOfHospitalModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;
import java.util.List;

public class HospitalAssociationActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();
    private List<DataModel> mItems = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_association);

        rv = findViewById(R.id.recyclerNav);
        recyclerView = findViewById(R.id.recycler);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);

        Available.setVisibility(View.VISIBLE);
        pList.addAll(HospitalAssociationModel.getListData());
        rv.setLayoutManager(new LinearLayoutManager(this));
        AdapterNavigator adapters = new AdapterNavigator(this,pList);
        rv.setAdapter(adapters);
        mItems.addAll(AsosiasiPersiData.getListData());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterHospitalAssociation cardViewPresidentAdapter = new AdapterHospitalAssociation(this,mItems);
        recyclerView.setAdapter(cardViewPresidentAdapter);
        cardViewPresidentAdapter.notifyDataSetChanged();
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (more){
                        more = false;
                        ivMore.setImageResource(R.drawable.close_concerate);
                        Available.setVisibility(View.GONE);
                        Navigator.setVisibility(View.VISIBLE);
                    }else{
                        more = true;
                        ivMore.setImageResource(R.drawable.more_vertical_concerate);
                        Available.setVisibility(View.VISIBLE);
                        Navigator.setVisibility(View.GONE);
                    }
                }catch (Exception e){

                }

            }
        });
    }
}