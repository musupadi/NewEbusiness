package com.ascendant.e_businessprofile.Activity.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ascendant.e_businessprofile.Activity.Method.Ascendant;
import com.ascendant.e_businessprofile.R;
import com.bumptech.glide.Glide;

public class NewsFragment extends Fragment {
    ImageView Image;
    TextView Name,Date,Category;
    WebView Web;
    String JUDUL,KATEGORI,TGL_UPLOAD,COVER,ISI_BERITA;
    Ascendant ascendant;
    public NewsFragment() {
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
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ascendant = new Ascendant();
        Image = view.findViewById(R.id.ivImage);
        Name = view.findViewById(R.id.tvName);
        Date = view.findViewById(R.id.tvTanggal);
        Category = view.findViewById(R.id.tvCategory);
        Web = view.findViewById(R.id.web);

        Bundle bundle = getArguments();
        JUDUL = bundle.getString("JUDUL");
        KATEGORI = bundle.getString("KATEGORI");
        TGL_UPLOAD = bundle.getString("TGL_UPLOAD");
        COVER = bundle.getString("COVER");
        ISI_BERITA = bundle.getString("ISI_BERITA");

        Glide.with(getActivity())
                .load(ascendant.BASE_URL()+COVER)
                .into(Image);
        Name.setText(JUDUL);
        Category.setText(KATEGORI);
        Date.setText(ascendant.MagicDateChange(TGL_UPLOAD));
        Web.loadData(ISI_BERITA,"text/html","UTF-8");
    }
}