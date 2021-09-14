package com.ascendant.e_businessprofile.Activity.ui.Healthcare.BusinessRefrence;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ascendant.e_businessprofile.R;

public class BusinessRefrenceFragment extends Fragment {
    RelativeLayout eBook,Regulations,Newsletter,BusinessReview;
    public BusinessRefrenceFragment() {
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
        return inflater.inflate(R.layout.fragment_business_refrence, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        eBook = view.findViewById(R.id.relativeEBook);
        Regulations = view.findViewById(R.id.relativeRegulations);
        Newsletter = view.findViewById(R.id.relativeNewsletter);
        BusinessReview = view.findViewById(R.id.relativeBusinessReview);

        eBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), eBookActivity.class);
                i.putExtra("MODULE", "ebook");
                getActivity().startActivity(i);
            }
        });
        Newsletter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), eBookActivity.class);
                i.putExtra("MODULE", "newsletter");
                getActivity().startActivity(i);
            }
        });
        BusinessReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), eBookActivity.class);
                i.putExtra("MODULE", "business_review");
                getActivity().startActivity(i);
            }
        });
        Regulations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), RegulationActivity.class);
                getActivity().startActivity(i);
            }
        });
    }
}