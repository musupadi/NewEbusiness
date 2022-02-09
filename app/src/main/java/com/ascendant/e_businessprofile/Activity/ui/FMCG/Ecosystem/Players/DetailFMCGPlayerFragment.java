package com.ascendant.e_businessprofile.Activity.ui.FMCG.Ecosystem.Players;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.OldRetroServer;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Adapter.AdapterAnakPerusahaan;
import com.ascendant.e_businessprofile.Adapter.AdapterPemegangSaham;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Perusahaan.AnakPerusahaan;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Perusahaan.PemegangSaham;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Perusahaan.Perusahaan;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailFMCGPlayerFragment extends Fragment {
    Ascendant method = new Ascendant();
    TextView nama,alamat,telpon,fax,npwp,website,bidangusaha,kategori,direkturutama,komisarisutama,profit,kota,provinsi;
    Button downloadRingkasan;
    RecyclerView rvPemegangSaham,rvAnakPerusahaan;
    private List<AnakPerusahaan> mItemsAnakPerusahaan = new ArrayList<>();
    private List<PemegangSaham> mItemsPemegangSaham = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    CardView cardAnakPerusahaan,cardPemegangSaham;
    ImageView back,home;
    WebView webView;

    public DetailFMCGPlayerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_f_m_c_g_player, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nama=view.findViewById(R.id.tvNamaPerusahaan);
        webView=view.findViewById(R.id.webView);
        kota=view.findViewById(R.id.tvKota);
        provinsi=view.findViewById(R.id.tvProvinsi);
        alamat=view.findViewById(R.id.tvAlamatPerusahaan);
        telpon=view.findViewById(R.id.tvTelponPerusahaan);
        fax=view.findViewById(R.id.tvFaxPerusahaan);
        npwp=view.findViewById(R.id.tvNPWP);
        website=view.findViewById(R.id.tvWebsite);
        bidangusaha=view.findViewById(R.id.tvBidangUsaha);
        kategori=view.findViewById(R.id.tvKategori);
        direkturutama=view.findViewById(R.id.tvDirektur);
        komisarisutama=view.findViewById(R.id.tvKomisaris);
        rvAnakPerusahaan=view.findViewById(R.id.recyclerAnakPerusahaan);
        rvPemegangSaham=view.findViewById(R.id.recyclerPemegangSaham);
        downloadRingkasan=view.findViewById(R.id.btnDownloadRingkasan);
        cardAnakPerusahaan=view.findViewById(R.id.cardAnakPerusahaan);
        cardPemegangSaham=view.findViewById(R.id.cardPemegangSaham);
        downloadRingkasan.setVisibility(View.GONE);

        Bundle bundle = getArguments();
        String ID = bundle.getString("ID");
        ApiRequest api = OldRetroServer.getClient().create(ApiRequest.class);
        Call<Perusahaan> detail = api.DataPerusahaan(method.AUTH(),
                "FABAJakartaIndonesia2019kunci",
                ID);
        rvPemegangSaham.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAnakPerusahaan.setLayoutManager(new LinearLayoutManager(getActivity()));
        detail.enqueue(new Callback<Perusahaan>() {
            @Override
            public void onResponse(Call<Perusahaan> call, final Response<Perusahaan> response) {
                try {
                    //Detail
                    nama.setText(response.body().detailPerusahaan.nama_perusahaan);
                    alamat.setText(response.body().detailPerusahaan.alamat_perusahaan);
                    telpon.setText(response.body().detailPerusahaan.telepon_perusahaan);
                    fax.setText(response.body().detailPerusahaan.faks_perusahaan);
                    npwp.setText(response.body().detailPerusahaan.npwp_perusahaan);
                    website.setText(response.body().detailPerusahaan.website_perusahaan);
                    bidangusaha.setText(response.body().detailPerusahaan.bidang_usaha_perusahaan);
                    kategori.setText(response.body().detailPerusahaan.kategori_fmcg);
                    direkturutama.setText(response.body().detailPerusahaan.direktur_utama_perusahaan);
                    komisarisutama.setText(response.body().detailPerusahaan.komisaris_utama_perusahaan);
                    provinsi.setText(response.body().provinsi);
                    kota.setText(response.body().kab_kota);
                    String param1 = response.body().detailPerusahaan.provit_size;
                    if (param1.equals("-")){
                        webView.setVisibility(View.GONE);
                    }else{
                        webView.setVisibility(View.VISIBLE);
                        webView.loadData(param1,"text/html","UTF-8");
                    }
                    if (response.body().detailPerusahaan.ringkasan_performa.equals("")){

                    }else{
                        downloadRingkasan.setVisibility(View.VISIBLE);
                        downloadRingkasan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setMessage("Download File ?")
                                        .setCancelable(false)
                                        .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                String Changer = response.body().detailPerusahaan.ringkasan_performa;
                                                String replace = Changer.replace("http://mandiri-ebusinessraw.the-urbandev.com/","");
                                                Toast.makeText(getActivity(), replace, Toast.LENGTH_SHORT).show();
                                                method.RingkasanDownload(response.body().detailPerusahaan.ringkasan_performa,"Ringkasan "+response.body().detailPerusahaan.nama_perusahaan,getActivity(),"pdf");
                                            }
                                        })
                                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        })
                                        //Set your icon here
                                        .setTitle("Perhatian !!!")
                                        .setIcon(R.drawable.print_primary);
                                AlertDialog alert = builder.create();
                                alert.show();
                            }
                        });
                    }



                    //Pemegang Saham
                    mItemsPemegangSaham=response.body().getPemegang_saham();
                    if (mItemsPemegangSaham.size()<1){
                        cardPemegangSaham.setVisibility(View.VISIBLE);
                        AdapterPemegangSaham adapter = new AdapterPemegangSaham(getActivity(),mItemsPemegangSaham);
                        rvPemegangSaham.setAdapter(adapter);
                    }else{
                        cardPemegangSaham.setVisibility(View.GONE);
                        AdapterPemegangSaham adapter = new AdapterPemegangSaham(getActivity(),mItemsPemegangSaham);
                        rvPemegangSaham.setAdapter(adapter);
                    }

                    //Anak Perusahaan
                    mItemsAnakPerusahaan=response.body().getAnak_perusahaan();
                    if (mItemsAnakPerusahaan.size()<1){
                        cardAnakPerusahaan.setVisibility(View.VISIBLE);
                        AdapterAnakPerusahaan adapter2 = new AdapterAnakPerusahaan(getActivity(),mItemsAnakPerusahaan);
                        rvAnakPerusahaan.setAdapter(adapter2);
                    }else{
                        cardAnakPerusahaan.setVisibility(View.GONE);
                        AdapterAnakPerusahaan adapter2 = new AdapterAnakPerusahaan(getActivity(),mItemsAnakPerusahaan);
                        rvAnakPerusahaan.setAdapter(adapter2);
                    }

                }catch (Exception e){
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Perusahaan> call, Throwable t) {
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}