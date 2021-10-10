package com.ascendant.e_businessprofile.Activity.ui.FMCG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.FMCGBusinessRefrenceModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.BusinessRefrence.ActsModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.BusinessRefrence.BusinessReviewModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.BusinessRefrence.ComplianceModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.BusinessRefrence.GovernmentRegulationsModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.BusinessRefrence.HealthMinisterRegulationsModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.BusinessRefrence.NewsletterModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.BusinessRefrence.PresidentialDecreeModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.BusinessRefrence.RegulationModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.BusinessRefrence.eBookModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.Ecosystem.EcosystemModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.Ecosystem.HospitalEquipmentodel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.Ecosystem.ListOfHospitalModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.Ecosystem.SupportingIndustriesModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.ListOfProbing.ListOfProbingModel;
import com.ascendant.e_businessprofile.Model.StaticModel.NewsModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;


public class FMCGNavigatorFragment extends Fragment {

    String Navigator;
    RecyclerView rv;
    private ArrayList<DataModel> pList = new ArrayList<>();

    public FMCGNavigatorFragment() {
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
        return inflater.inflate(R.layout.fragment_f_m_c_g_navigator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv = view.findViewById(R.id.recycler);
        Bundle bundle = getArguments();
        Navigator = bundle.getString("Navigator");
        GetData();
    }


    private void GetData(){
        if (Navigator.equals("Business Refrence")){
            pList.addAll(FMCGBusinessRefrenceModel.getListData());
        }else if (Navigator.equals("e-Book")){
            pList.addAll(eBookModel.getListData());
        }else if (Navigator.equals("Newsletter")){
            pList.addAll(NewsletterModel.getListData());
        }else if (Navigator.equals("Business Review")){
            pList.addAll(BusinessReviewModel.getListData());
        }else if (Navigator.equals("Regulations")){
            pList.addAll(RegulationModel.getListData());
        }else if (Navigator.equals("Acts")){
            pList.addAll(ActsModel.getListData());
        }else if (Navigator.equals("Presidential Decree")){
            pList.addAll(PresidentialDecreeModel.getListData());
        }else if (Navigator.equals("Government Regulations")){
            pList.addAll(GovernmentRegulationsModel.getListData());
        }else if (Navigator.equals("Health Minister Regulations")){
            pList.addAll(HealthMinisterRegulationsModel.getListData());
        }else if (Navigator.equals("List of Probing")){
            pList.addAll(ListOfProbingModel.getListData());
        }else if (Navigator.equals("Compliance")){
            pList.addAll(ComplianceModel.getListData());
        }else if (Navigator.equals("News")){
            pList.addAll(NewsModel.getListData());
        }else if (Navigator.equals("Ecosystem")){
            pList.addAll(EcosystemModel.getListData());
        }else if (Navigator.equals("Supporting Industries")){
            pList.addAll(SupportingIndustriesModel.getListData());
        }else if (Navigator.equals("Hospital Equipment")){
            pList.addAll(HospitalEquipmentodel.getListData());
        }else if (Navigator.equals("List of Hospital")){
            pList.addAll(ListOfHospitalModel.getListData());
        }
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        AdapterNavigator adapters = new AdapterNavigator(getActivity(),pList);
        rv.setAdapter(adapters);
    }
}