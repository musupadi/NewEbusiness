package com.ascendant.e_businessprofile.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.OldRetroServer;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.Ecosystem.ListOfHospital.DetailHospitalActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.Ecosystem.ListOfHospital.HospitalListActivity;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterListHospital extends RecyclerView.Adapter<AdapterListHospital.HolderData> {
    private List<DataModel> mList;
    private Context ctx;
    Ascendant ascendant;
    Dialog myDialog;
    TextView JumlahRumahSakit,JumlahBed,NamaRs,KelasRs,JumlahPekerjaRs,TotalBedRs;
    Button detail;
    public AdapterListHospital(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_hospital,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        final DataModel dm = mList.get(posistion);
        ascendant = new Ascendant();
        holderData.Name.setText(dm.getNama_rs());
        holderData.Kelas.setText("Class "+dm.getKelas_rs());
        holderData.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String locAddress = dm.getNama_rs();
                getData(locAddress);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        LinearLayout card;
        TextView Name,Address,Kelas;

        public HolderData(View v) {
            super(v);
            card = v.findViewById(R.id.linearCard);
            Name = v.findViewById(R.id.tvName);
            Address = v.findViewById(R.id.tvAddress);
            Kelas = v.findViewById(R.id.tvKelas);
        }
    }
    private void getData(String namaRS){
        myDialog = new Dialog(ctx);
        myDialog.setContentView(R.layout.dialog_data_rs);
        NamaRs=myDialog.findViewById(R.id.tvNamaRS);
        KelasRs=myDialog.findViewById(R.id.tvKelasRS);
        JumlahPekerjaRs=myDialog.findViewById(R.id.tvPekerjaRS);
        TotalBedRs=myDialog.findViewById(R.id.tvTotalJumlahBedRS);
        detail=myDialog.findViewById(R.id.btnDetail);
        ApiRequest api = OldRetroServer.getClient().create(ApiRequest.class);
        Call<ResponseArrayObject> getData = api.listHospital(ascendant.AUTH(),"0","0",namaRS,"0","FABAJakartaIndonesia2019kunci");
        getData.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                java.util.List<DataModel> mItems = new ArrayList<>();
                if (response == null){
                    Toast.makeText(ctx, "", Toast.LENGTH_SHORT).show();
                }else{
                    if (response.isSuccessful()) {
                        mItems = response.body().getList();
                        int w = 0;
                        final DataModel dm = mItems.get(w);

                        myDialog.show();
                        NamaRs.setText(dm.getNama_rs());
                        KelasRs.setText(dm.getKelas_rs());
                        JumlahPekerjaRs.setText(dm.getJumlah_tenaga_rs());
                        TotalBedRs.setText(dm.getJumlah_bed_rs());
                        detail.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DetailRS(dm.getKode_rs());
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                Toast.makeText(ctx, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void DetailRS(String kode) {
        ApiRequest api = OldRetroServer.getClient().create(ApiRequest.class);
        Call<ResponseArrayObject> DetailRS = api.detailHospital(ascendant.AUTH(),kode,"FABAJakartaIndonesia2019kunci");
        DetailRS.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                if (response.isSuccessful()){
                    Intent goInput = new Intent(ctx, DetailHospitalActivity.class);
                    goInput.putExtra("Kode",response.body().getKode_rs());
                    goInput.putExtra("Registrasi",response.body().getTgl_registrasi_rs());
                    goInput.putExtra("NamaRS",response.body().getNama_rs());
                    goInput.putExtra("Jenis",response.body().getJenis_rs());
                    goInput.putExtra("KlsRS",response.body().getKelas_rs());
                    goInput.putExtra("DirekturRS",response.body().getDirektur_rs());
                    goInput.putExtra("LatarBelakangPendidikan",response.body().getLatar_pendidikan_direktur_rs());
                    goInput.putExtra("Pemilik",response.body().getPemilik_rs());
                    goInput.putExtra("Alamat",response.body().getAlamat_rs());
                    goInput.putExtra("Kota",response.body().getNama_kab_kota());
                    goInput.putExtra("KodePos",response.body().getKode_pos_rs());
                    goInput.putExtra("Telepon",response.body().getTelepon_rs());
                    goInput.putExtra("Fax",response.body().getFax_rs());
                    goInput.putExtra("Email",response.body().getEmail_rs());
                    goInput.putExtra("TeleponHumas",response.body().getTelepon_humas_rs());
                    goInput.putExtra("Website",response.body().getWebsite_rs());
                    goInput.putExtra("LuasTanah",response.body().getLuas_tanah_rs());
                    goInput.putExtra("LuasBangunan",response.body().getLuas_bangunan_rs());
                    goInput.putExtra("NoSuratIzin",response.body().getNo_surat_ijin());
                    goInput.putExtra("TanggalSuratIzin",response.body().getTanggal_surat_ijin());
                    goInput.putExtra("SuratIzinDari",response.body().getSurat_ijin_dari());
                    goInput.putExtra("SifatSuratIjin",response.body().getSifat_surat_ijin());
                    goInput.putExtra("MasaBerlakuSuratIjin",response.body().getMasa_berlaku_ijin());
                    goInput.putExtra("StatusPenyelenggara",response.body().getStatus_penyelenggara());
                    goInput.putExtra("StatusAkreditasi",response.body().getStatus_akreditasi());
                    goInput.putExtra("TglAkreditasi",response.body().getTgl_akreditasi());
                    goInput.putExtra("BerlakuSampai",response.body().getBerlaku_sampai_dengan());
                    goInput.putExtra("VVIP",response.body().getVvip_bed());
                    goInput.putExtra("VIP",response.body().getVip_bed());
                    goInput.putExtra("Kelas1",response.body().getKelas_1_bed());
                    goInput.putExtra("Kelas2",response.body().getKelas_2_bed());
                    goInput.putExtra("Kelas3",response.body().getKelas_3_bed());
                    goInput.putExtra("ICU",response.body().getIcu_bed());
                    goInput.putExtra("PICU",response.body().getPicu_bed());
                    goInput.putExtra("NICU",response.body().getNicu_bed());
                    goInput.putExtra("TTBayiBaru",response.body().getTt_bayi_baru_lahir_bed());
                    goInput.putExtra("HCU",response.body().getHcu_bed());
                    goInput.putExtra("ICCU",response.body().getIccu_bed());
                    goInput.putExtra("IGD",response.body().getIgd_bed());
                    goInput.putExtra("TTRuangOperasi",response.body().getTt_di_ruang_operasi_bed());
                    goInput.putExtra("TTRuangIsolasi",response.body().getTt_di_ruang_operasi_bed());
                    goInput.putExtra("DrUmum",response.body().getDr_umum());
                    goInput.putExtra("DrOG",response.body().getDr_sp_og());
                    goInput.putExtra("DrPD",response.body().getDr_sp_pd());
                    goInput.putExtra("DrB",response.body().getDr_sp_b());
                    goInput.putExtra("DrRad",response.body().getDr_sp_rad());
                    goInput.putExtra("DrRM",response.body().getDr_sp_rm());
                    goInput.putExtra("DrAn",response.body().getDr_sp_an());
                    goInput.putExtra("DrJp",response.body().getDr_sp_jp());
                    goInput.putExtra("DrM",response.body().getDr_sp_m());
                    goInput.putExtra("DrTHT",response.body().getDr_sp_tht());
                    goInput.putExtra("DrPK",response.body().getDr_sp_pk());
                    goInput.putExtra("DrParu",response.body().getDr_sp_paru());
                    goInput.putExtra("DrBedahThoraks",response.body().getDr_sp_bedah_thoraks());
                    goInput.putExtra("DrBedahAnak",response.body().getDr_sp_bedah_anak());
                    goInput.putExtra("DrBedahOrhopedi",response.body().getDr_sp_bedah_orthopedi());
                    goInput.putExtra("DrA",response.body().getDr_sp_a());
                    goInput.putExtra("DrOkupasi",response.body().getDr_sp_okupasi());
                    goInput.putExtra("DrUrologi",response.body().getDr_sp_urologi());
                    goInput.putExtra("DrOrthopedi",response.body().getDr_sp_orthopedi());
                    goInput.putExtra("DrKulit",response.body().getDr_sp_kulit_dan_kelamin());
                    goInput.putExtra("DrForensik",response.body().getDr_sp_forensik());
                    goInput.putExtra("DrPsikiatri",response.body().getDr_sp_psikiatri());
                    goInput.putExtra("DrOfthalmologi",response.body().getDr_sp_ofthamologi());
                    goInput.putExtra("DrAnatomi",response.body().getDr_sp_patologi_anatomi());
                    goInput.putExtra("DrJiwa",response.body().getDr_sp_kes_kejiwaan());
                    goInput.putExtra("DrSaraf",response.body().getDr_sp_saraf());
                    goInput.putExtra("DrLainnya",response.body().getDr_sp_lainnya());
                    goInput.putExtra("DrBedahSaraf",response.body().getDr_sp_bedah_saraf());
                    goInput.putExtra("DrBedahPlastik",response.body().getDr_sp_bedah_plastik());
                    goInput.putExtra("DrSubSpesialis",response.body().getDr_sub_spesialis());
                    goInput.putExtra("DrGigi",response.body().getDr_gigi());
                    goInput.putExtra("DrBedahMulut",response.body().getDr_gigi_sp_bedah_mulut());
                    goInput.putExtra("DrKonservasi",response.body().getDr_gigi_sp_konservasi());
                    goInput.putExtra("DrPenyakitMulut",response.body().getDr_gigi_sp_penyakit_mulut());
                    goInput.putExtra("DrRadiologi",response.body().getDr_gigi_sp_radiologi());
                    goInput.putExtra("DrKarangGigi",response.body().getDr_gigi_sp_karang_gigi());
                    goInput.putExtra("DrGigiAnak",response.body().getDr_gigi_sp_anak());
                    goInput.putExtra("DrGigiTiruan",response.body().getDr_gigi_sp_gigi_tiruan());
                    goInput.putExtra("DrPeriodonsia",response.body().getDr_gigi_sp_periodonsia());
                    goInput.putExtra("DrGigiLainnya",response.body().getDr_gigi_sp_lainnya());
                    goInput.putExtra("BidanPendidik",response.body().getBidan_pendidik());
                    goInput.putExtra("BidanKlinik",response.body().getBidan_klinik());
                    goInput.putExtra("Apoteker",response.body().getApoteker());
                    goInput.putExtra("AnalisFarmasi",response.body().getAnalis_farmasi());
                    goInput.putExtra("Radiografer",response.body().getRadiografer());
                    goInput.putExtra("Radioterapis",response.body().getRadioterapis());
                    goInput.putExtra("Elektromedis",response.body().getElektromedis());
                    goInput.putExtra("TeknisiGigi",response.body().getTeknisi_gigi());
                    goInput.putExtra("AnalisKesehataan",response.body().getAnalis_kesehatan());
                    goInput.putExtra("Refraksionis",response.body().getRefaksionis());
                    goInput.putExtra("RekamMedik",response.body().getRekam_medik());
                    goInput.putExtra("Ortotik",response.body().getOrtotik());
                    goInput.putExtra("TransfusiDarah",response.body().getTeknisi_transfusi_darah());
                    goInput.putExtra("Kardiovaskular",response.body().getTeknisi_kardiovaskular());
                    goInput.putExtra("Epidemiologi",response.body().getEpidemiologi());
                    goInput.putExtra("PromosiKesehatan",response.body().getPromosi_kesehatan());
                    goInput.putExtra("Perilaku",response.body().getPerilaku());
                    goInput.putExtra("Kesja",response.body().getKesja());
                    goInput.putExtra("AdministrasiKesehatan",response.body().getAdministrasi_kesehatan());
                    goInput.putExtra("Biostatik",response.body().getBiostatistik());
                    goInput.putExtra("Reproduksi",response.body().getReproduksi());
                    goInput.putExtra("InformasiKesehatan",response.body().getInformasi_kesehatan());
                    goInput.putExtra("KesmasLainnya",response.body().getKesmas_lainnya());
                    //Tenaga Kesehatan Lainnya
                    goInput.putExtra("Sanitasi",response.body().getSanitasi());
                    goInput.putExtra("Entomologi",response.body().getEntomologi());
                    goInput.putExtra("Mikrobiologi",response.body().getMikrobiologi());
                    goInput.putExtra("KesehatanLingkungan",response.body().getKesehatan_lingkungan());
                    goInput.putExtra("TerapiWicara",response.body().getTerapi_wicara());
                    goInput.putExtra("Nutrisionis",response.body().getNutrisionis());
                    goInput.putExtra("Dietsien",response.body().getDietsien());
                    goInput.putExtra("Fisioterapi",response.body().getFisioterapi());
                    goInput.putExtra("TerapiOkupasi",response.body().getTerapi_okupasi());
                    goInput.putExtra("Akupunturis",response.body().getAkupunturis());
                    //Tenaga Non Kesehatan
                    goInput.putExtra("ProgramKesehatan",response.body().getProgram_kesehatan());
                    goInput.putExtra("Administrasikeuangan",response.body().getAdministrasi_keuangan());
                    goInput.putExtra("Humas",response.body().getHumas());
                    goInput.putExtra("Perencanaan",response.body().getPerencanaan());
                    goInput.putExtra("JaminanKesehatan",response.body().getJaminan_kesehatan());
                    goInput.putExtra("Dosen",response.body().getDosen());
                    goInput.putExtra("Psikologi",response.body().getPsikologi());
                    goInput.putExtra("Pelaporan",response.body().getPelaporan());
                    goInput.putExtra("InformasiTeknologi",response.body().getInformasi_teknologi());
                    goInput.putExtra("Hukum",response.body().getHukum());
                    goInput.putExtra("Pekarya",response.body().getPekarya());
                    goInput.putExtra("Perpustakaan",response.body().getPerpustakaan());
                    goInput.putExtra("Widyaswara",response.body().getWidyaiswara());
                    goInput.putExtra("TenagaNonKers",response.body().getTenaga_non_kes());
                    //Data Peralatan Di Rumah Sakit
                    goInput.putExtra("MejaOperasi",response.body().getMeja_operasi());
                    goInput.putExtra("MesinAnestesi",response.body().getMesin_anestesi());
                    goInput.putExtra("Ventilator",response.body().getVentilator());
                    goInput.putExtra("Inkubator",response.body().getInkubator());
                    goInput.putExtra("BlueLight",response.body().getBlue_light());
                    goInput.putExtra("U_S_G",response.body().getUsg());
                    goInput.putExtra("XRay",response.body().getX_ray());
                    goInput.putExtra("CTScan",response.body().getCt_scan());
                    goInput.putExtra("MRI",response.body().getMri());
                    goInput.putExtra("EEG",response.body().getEeg());
                    goInput.putExtra("EKG",response.body().getEkg());
                    goInput.putExtra("Defibrilator",response.body().getDefibrilator());
                    goInput.putExtra("Autoclav",response.body().getAutoclav());
                    goInput.putExtra("RawatJalan",response.body().getRawat_jalan());
                    goInput.putExtra("RawatInap",response.body().getRawat_inap());
                    goInput.putExtra("I_G_D",response.body().getI_g_dbank());
                    goInput.putExtra("BOR",response.body().getBor());
                    goInput.putExtra("ALOS",response.body().getAlos());
                    goInput.putExtra("TOI",response.body().getToi());
                    goInput.putExtra("NDR",response.body().getNdr());
                    goInput.putExtra("GDR",response.body().getGdr());
                    goInput.putExtra("LayananUnggulan",response.body().getLayanan_unggulan());
                    goInput.putExtra("SIMRS",response.body().getSimrs());
                    goInput.putExtra("Ambulan",response.body().getAmbulan());
                    goInput.putExtra("BankDarah",response.body().getBank_darah());
                    goInput.putExtra("TanggalUpdate",response.body().getTgl_update());
                    ctx.startActivities(new Intent[]{goInput});
                }else{

                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                Toast.makeText(ctx, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
