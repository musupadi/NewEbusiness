package com.ascendant.e_businessprofile.Activity.ui.Healthcare.Ecosystem.ListOfHospital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Mining.Outlook.MiningOutlookModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;
import java.util.List;

public class DetailHospitalActivity extends AppCompatActivity {
    RecyclerView rv;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;

    DB_Helper dbHelper;
    String Token;

    LinearLayout Available,Navigator;
    RecyclerView rv2,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();

    TextView KodeRS,TglRegistrasi,RumahSakit,Jenis,
            KlsRS,DirekturRS,LatarBelakang,Pemilik,
            Alamat,Kota,KodePos,Telepon,Fax,Email,
            TeleponHumas,Website;
    TextView LuasTanah,LuasBangunan,NoSuratIjin,TanggalSuratIjin,
            SuratIjinDari,SifatSuratIjin,MasaBerlakuSuratIjin,StatusPenyelenggara,
            StatusAkreditasi,TglAkreditasi,BerlakuSampai,VVIP,VIP,kelas1,kelas2,kelas3,
            ICU,PICU,NICU,TTBayi,HCU,ICCU,IGD,TTOperasi,TTIsolasi;
    TextView DrUmum,drOg,drPd,drB,drRad,drRm,drAn,drJp,drM,drTHT,drPK,drParu,drBedahThoraks,
            drBedahAnak,drBedahOrthopedi,drA,drOkupasi,drUrologi,drOrhopedi,drKulit,drForensik,
            drPsikiatri,drOfthalmologi,drAnatomi,drJiwa,drSaraf,drLainnya,drBedahSaraf,drBedahPlastik,
            drSubSpesialis;
    TextView DrGigi,DrBedahMulut,DrKonservasi,DrPenyakitMulut,DrRadiologi,DrKarangGigi,DrAnak,
            DrGigiTiruan,DrPeriodonsia,DrGigiLainnya;
    TextView BidanPendidik,BidanKlinik,Apoteker,AnalisFarmasi;
    TextView Radiografer,Radioterapis,Elektromedis,TeknisiGigi,AnalisKesehatan,Refraksionis,
            RekamMedik,Ortotik,TransfusiDarah,Kardiovaskular,Epidemiologi,PromosiKesehatan,
            Perilaku,Kesja,AdministrasiKesehatan,Biostatik,Reproduksi,InformasiKesehatan,KesmasLainnya;
    TextView Sanitasi,Entomologi,Mikrobiologi,KesehatanLingkungan,TerapiWicara,Nutrisionis,Dietsien,
            Fisioterapi,TerapiOkupasi,Akupunturis;
    TextView ProgramKesehatan,AdministrasiKeuangan,Humas,Perencanaan,JaminanKesehatan,Dosen,Psikologi,
            Pelaporan,InformasiTeknologi,Hukum,Pekarya,Perpustakaan,Widyaswara,TenagaNonKers;
    TextView MejaOperasi,MesinAnestesi,Ventilator,Inkubator,BlueLight,U_S_G,XRay,CTScan,MRI,EEG,EKG,
            Defilbrillator,Autoclav,RawatJalan,RawatInap,I_G_D,BOR,ALOS,TOI,NDR,GDR,LayananUnggulan,
            SIMRS,Ambulan,BankDarah,TanggalUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hospital);


        //GetString
        Intent data = getIntent();
        String SKodeRs = data.getStringExtra("Kode");
        String SRegistrasi = data.getStringExtra("Registrasi");
        String SNamaRS = data.getStringExtra("NamaRS");
        String SJenis = data.getStringExtra("Jenis");
        String SKelasRS = data.getStringExtra("KlsRS");
        String SDirekturRS = data.getStringExtra("DirekturRS");
        String SLatarBelakang = data.getStringExtra("LatarBelakangPendidikan");
        String SPemilik = data.getStringExtra("Pemilik");
        String SAlamat = data.getStringExtra("Alamat");
        String SKota = data.getStringExtra("Kota");
        String SKodePos = data.getStringExtra("KodePos");
        String STelepon = data.getStringExtra("Telepon");
        String SFax = data.getStringExtra("Fax");
        String SEmail = data.getStringExtra("Email");
        String STeleponHumas = data.getStringExtra("TeleponHumas");
        String SWebsite = data.getStringExtra("Website");
        String SLuasTanah = data.getStringExtra("LuasTanah");
        String SLuasBangunan = data.getStringExtra("LuasBangunan");
        String SNoSuratIzin = data.getStringExtra("NoSuratIzin");
        String STanggalSuratIzin = data.getStringExtra("TanggalSuratIzin");
        String SSuratIzinDari = data.getStringExtra("SuratIzinDari");
        String SSifatSuratIjin = data.getStringExtra("SifatSuratIjin");
        String SMasaBerlakuSuratIzin = data.getStringExtra("MasaBerlakuSuratIjin");
        String SStatusPenyelenggara = data.getStringExtra("StatusPenyelenggara");
        String SStatusAkreditasi = data.getStringExtra("StatusAkreditasi");
        String STanggalAkreditasi = data.getStringExtra("TglAkreditasi");
        String SBerlakuSampai = data.getStringExtra("BerlakuSampai");
        String SVVIP = data.getStringExtra("VVIP");
        String SVIP = data.getStringExtra("VIP");
        String SKelas1 = data.getStringExtra("Kelas1");
        String SKelas2 = data.getStringExtra("Kelas2");
        String SKelas3 = data.getStringExtra("Kelas3");
        String SICU = data.getStringExtra("ICU");
        String SPICU = data.getStringExtra("PICU");
        String SNICU = data.getStringExtra("NICU");
        String STTBayiBaru = data.getStringExtra("TTBayiBaru");
        String SHCU = data.getStringExtra("HCU");
        String SICCU = data.getStringExtra("ICCU");
        String SIGD = data.getStringExtra("IGD");
        String STTRuangOperasi = data.getStringExtra("TTRuangOperasi");
        String STTRuangIsolasi = data.getStringExtra("TTRuangIsolasi");
        String SDrUmum = data.getStringExtra("DrUmum");
        String SDrOG = data.getStringExtra("DrOG");
        String SDrPD = data.getStringExtra("DrPD");
        String SDrB = data.getStringExtra("DrB");
        String SDrRad = data.getStringExtra("DrRad");
        String SDrRM = data.getStringExtra("DrRM");
        String SDrAn = data.getStringExtra("DrAn");
        String SDrJp = data.getStringExtra("DrJp");
        String SDrM = data.getStringExtra("DrM");
        String SDrTHT = data.getStringExtra("DrTHT");
        String SDrPK = data.getStringExtra("DrPK");
        String SDrParu = data.getStringExtra("DrParu");
        String SDrBedahThoraks= data.getStringExtra("DrBedahThoraks");
        String SDrBedahAnak = data.getStringExtra("DrBedahAnak");
        String SDrBedahOrhopedi = data.getStringExtra("DrBedahOrhopedi");
        String SDrA = data.getStringExtra("DrA");
        String SDrOkupasi = data.getStringExtra("DrOkupasi");
        String SDrUrologi = data.getStringExtra("DrUrologi");
        String SDrOrthopedi = data.getStringExtra("DrOrthopedi");
        String SDrKulit = data.getStringExtra("DrKulit");
        String SDrForensik = data.getStringExtra("DrForensik");
        String SDrPsikiatri = data.getStringExtra("DrPsikiatri");
        String SDrOfthalmologi = data.getStringExtra("DrOfthalmologi");
        String SDrAnatomi = data.getStringExtra("DrAnatomi");
        String SDrJiwa = data.getStringExtra("DrJiwa");
        String SDrSaraf = data.getStringExtra("DrSaraf");
        String SDrLainnya = data.getStringExtra("DrLainnya");
        String SDrBedahSaraf = data.getStringExtra("DrBedahSaraf");
        String SDrBedahPlastik = data.getStringExtra("DrBedahPlastik");
        String SDrSubSpesialis = data.getStringExtra("DrSubSpesialis");
        String SDrGigi = data.getStringExtra("DrGigi");
        String SDrBedahMulut = data.getStringExtra("DrBedahMulut");
        String SDrKonservasi = data.getStringExtra("DrKonservasi");
        String SDrPenyakitMulut = data.getStringExtra("DrPenyakitMulut");
        String SDrRadiologi = data.getStringExtra("DrRadiologi");
        String SDrKarangGigi = data.getStringExtra("DrKarangGigi");
        String SDrGigiAnak = data.getStringExtra("DrGigiAnak");
        String SDrGigiTiruan = data.getStringExtra("DrGigiTiruan");
        String SDrPeriodonsia = data.getStringExtra("DrPeriodonsia");
        String SDrGigiLainnya = data.getStringExtra("DrGigiLainnya");
        String SBidanPendidik = data.getStringExtra("BidanPendidik");
        String SBidanKlinik = data.getStringExtra("BidanKlinik");
        String SApoteker = data.getStringExtra("Apoteker");
        String SAnalisFarmasi = data.getStringExtra("AnalisFarmasi");

        String SRadiografer = data.getStringExtra("Radiografer");
        String SRadioterapis = data.getStringExtra("Radioterapis");
        String SElektromedis = data.getStringExtra("Elektromedis");
        String STeknisiGigi = data.getStringExtra("TeknisiGigi");
        String SAnalisKesehatan = data.getStringExtra("AnalisKesehataan");
        String SRefraksionis = data.getStringExtra("Refraksionis");
        String SRekamMedik = data.getStringExtra("RekamMedik");
        String SOrtotik = data.getStringExtra("Ortotik");
        String STransfusiDarah = data.getStringExtra("TransfusiDarah");
        String Skardiovaskular = data.getStringExtra("Kardiovaskular");
        String SEpidemiologi = data.getStringExtra("Epidemiologi");
        String SPromosiKesehatan = data.getStringExtra("PromosiKesehatan");
        //
        String SPerilaku = data.getStringExtra("Perilaku");
        String SKesja = data.getStringExtra("Kesja");
        String SAdministrasiKesehatan = data.getStringExtra("AdministrasiKesehatan");
        String SBiostatik = data.getStringExtra("Biostatik");
        String SReproduksi = data.getStringExtra("Reproduksi");
        String SInfromasiKesehatan = data.getStringExtra("InformasiKesehatan");
        String SKesmasLainnya = data.getStringExtra("KesmasLainnya");
        //Tenaga Kesehatan Lainnya
        String SSanitasi = data.getStringExtra("Sanitasi");
        String SEntomologi = data.getStringExtra("Entomologi");
        String SMikrobiologi = data.getStringExtra("Mikrobiologi");
        String SKesehatanLingkungan = data.getStringExtra("KesehatanLingkungan");
        String STerapiWicara = data.getStringExtra("TerapiWicara");
        String SNutrisionis = data.getStringExtra("Nutrisionis");
        String SDietsien = data.getStringExtra("Dietsien");
        String SFisioterapi = data.getStringExtra("Fisioterapi");
        String STerapiOkupasi = data.getStringExtra("TerapiOkupasi");
        String SAkupunturis = data.getStringExtra("Akupunturis");
        //Tenaga Non Kesehatan
        String SProgramKesehatan = data.getStringExtra("ProgramKesehatan");
        String SAdministrasiKeuangan = data.getStringExtra("Administrasikeuangan");
        String SHumas = data.getStringExtra("Humas");
        String SPerencaan = data.getStringExtra("Perencanaan");
        String SJaminanKesehatan = data.getStringExtra("JaminanKesehatan");
        String SDosen = data.getStringExtra("Dosen");
        String SPsikologi = data.getStringExtra("Psikologi");
        String SPelaporan = data.getStringExtra("Pelaporan");
        String SInformasiTeknologi = data.getStringExtra("InformasiTeknologi");
        String SHukum = data.getStringExtra("Hukum");
        String SPekarya = data.getStringExtra("Pekarya");
        String SPerpustakaan = data.getStringExtra("Perpustakaan");
        String SWidyaswara = data.getStringExtra("Widyaswara");
        String STenagaNonKers = data.getStringExtra("TenagaNonKers");
        //Data Peralatan Di Rumah Sakit
        String SMejaOperasi = data.getStringExtra("MejaOperasi");
        String SMesinAnestesi = data.getStringExtra("MesinAnestesi");
        String SVentilator = data.getStringExtra("Ventilator");
        String SInkubator = data.getStringExtra("Inkubator");
        String SBlueLight = data.getStringExtra("BlueLight");
        String SUSG = data.getStringExtra("U_S_G");
        String SXray = data.getStringExtra("XRay");
        String SCTScan = data.getStringExtra("CTScan");
        String SMRI = data.getStringExtra("MRI");
        String SEEG = data.getStringExtra("EEG");
        String SEKG = data.getStringExtra("EKG");
        String SDefibrilator = data.getStringExtra("Defibrilator");
        String SAutoclav = data.getStringExtra("Autoclav");
        String SRawatJalan = data.getStringExtra("RawatJalan");
        String SRawatInap = data.getStringExtra("RawatInap");
        String SI_G_D = data.getStringExtra("I_G_D");
        String SBOR = data.getStringExtra("BOR");
        String SALOS = data.getStringExtra("ALOS");
        String STOI = data.getStringExtra("TOI");
        String SNDR = data.getStringExtra("NDR");
        String SGDR = data.getStringExtra("GDR");
        String SLayananUnggulan = data.getStringExtra("LayananUnggulan");
        String SSIMRS = data.getStringExtra("SIMRS");
        String SAmbulan = data.getStringExtra("Ambulan");
        String SBankDarah = data.getStringExtra("BankDarah");
        String STanggalUpdate = data.getStringExtra("TanggalUpdate");
        //Done
        //FINDVIEWBYID
        KodeRS = findViewById(R.id.tvKodeRS);
        TglRegistrasi = findViewById(R.id.tvTglRegistrasi);
        RumahSakit = findViewById(R.id.tvRumahSakit);
        Jenis = findViewById(R.id.tvJenisRS);
        KlsRS = findViewById(R.id.tvKelasRS);
        DirekturRS = findViewById(R.id.tvDirekturRS);
        LatarBelakang = findViewById(R.id.tvLatarBelakangPendidikan);
        Pemilik = findViewById(R.id.tvPemilik);
        Alamat = findViewById(R.id.tvAlamat);
        Kota = findViewById(R.id.tvKabKota);
        KodePos = findViewById(R.id.tvKodePOS);
        Telepon = findViewById(R.id.tvTelepon);
        TeleponHumas = findViewById(R.id.tvTeleponHumas);
        Fax = findViewById(R.id.tvFAX);
        Email = findViewById(R.id.tvEmail);
        Website = findViewById(R.id.tvWebsite);
        LuasTanah = findViewById(R.id.tvLuasTanah);
        LuasBangunan = findViewById(R.id.tvLuasBangunan);
        NoSuratIjin = findViewById(R.id.tvNoSuratIjin);
        TanggalSuratIjin = findViewById(R.id.tvTglSuratIjin);
        SuratIjinDari = findViewById(R.id.tvSuratIjinDari);
        SifatSuratIjin = findViewById(R.id.tvSifatSuratIjin);
        MasaBerlakuSuratIjin = findViewById(R.id.tvMasaBerlakuSuratIjin);
        StatusPenyelenggara = findViewById(R.id.tvStatusPenyelenggara);
        StatusAkreditasi = findViewById(R.id.tvStatusAkreditasi);
        TglAkreditasi = findViewById(R.id.tvTanggalAkreditasi);
        BerlakuSampai = findViewById(R.id.tvBerlakuSampai);
        VVIP = findViewById(R.id.tvVVIP);
        VIP = findViewById(R.id.tvVIP);
        kelas1 = findViewById(R.id.tvKelas1);
        kelas2 = findViewById(R.id.tvKelas2);
        kelas3 = findViewById(R.id.tvKelas3);
        ICU = findViewById(R.id.tvICU);
        PICU = findViewById(R.id.tvPICU);
        NICU = findViewById(R.id.tvNICU);
        TTBayi = findViewById(R.id.tvTTBayiBaruLahir);
        HCU = findViewById(R.id.tvHCU);
        ICCU = findViewById(R.id.tvICCU);
        IGD = findViewById(R.id.tvIGD);
        TTOperasi = findViewById(R.id.tvTTOPerasi);
        TTIsolasi = findViewById(R.id.tvTTIsolasi);
        DrUmum = findViewById(R.id.tvDrUmum);
        drOg = findViewById(R.id.tvDrOG);
        drPd = findViewById(R.id.tvDrSpPD);
        drB = findViewById(R.id.tvDrB);
        drRad = findViewById(R.id.tvRad);
        drRm = findViewById(R.id.tvRm);
        drAn = findViewById(R.id.tvAn);
        drJp = findViewById(R.id.tvJp);
        drM = findViewById(R.id.tvM);
        drTHT = findViewById(R.id.tvTHT);
        drPK = findViewById(R.id.tvPk);
        drParu = findViewById(R.id.tvParu);
        drBedahThoraks = findViewById(R.id.tvBedahThoraks);
        drBedahAnak = findViewById(R.id.tvBedahAnak);
        drBedahOrthopedi = findViewById(R.id.tvBedahOrhopedi);
        drA = findViewById(R.id.tvA);
        drOkupasi = findViewById(R.id.tvOkupasi);
        drUrologi = findViewById(R.id.tvUrologi);
        drOkupasi = findViewById(R.id.tvOkupasi);
        drUrologi = findViewById(R.id.tvUrologi);
        drOrhopedi = findViewById(R.id.tvOrthopedi);
        drKulit = findViewById(R.id.tvKulit);
        drForensik = findViewById(R.id.tvForensik);
        drPsikiatri = findViewById(R.id.tvPsikiatri);
        drOfthalmologi = findViewById(R.id.tvOfthalmologi);
        drAnatomi = findViewById(R.id.tvAnatomi);
        drJiwa = findViewById(R.id.tvKesJiwa);
        drSaraf = findViewById(R.id.tvSaraf);
        drLainnya = findViewById(R.id.tvLainnya);
        drBedahSaraf = findViewById(R.id.tvBedahSaraf);
        drBedahPlastik = findViewById(R.id.tvBedahPlastik);
        drSubSpesialis = findViewById(R.id.tvBedahSpesialis);
        DrGigi = findViewById(R.id.tvDokterGigi);
        DrBedahMulut = findViewById(R.id.tvDrBedahMulut);
        DrKonservasi = findViewById(R.id.tvDrKonservasi);
        DrPenyakitMulut = findViewById(R.id.tvDrPenyakitMulut);
        DrRadiologi = findViewById(R.id.tvDrRadiologi);
        DrKarangGigi = findViewById(R.id.tvDrKarangGigi);
        DrAnak = findViewById(R.id.tvDrAnak);
        DrGigiTiruan = findViewById(R.id.tvDrGigiTiruan);
        DrPeriodonsia = findViewById(R.id.tvDrPeriodonsia);
        DrGigiLainnya = findViewById(R.id.tvDrGigiLainnya);
        BidanPendidik = findViewById(R.id.tvBidanPendidik);
        BidanKlinik = findViewById(R.id.tvBidanKlinik);
        Apoteker = findViewById(R.id.tvApoteker);
        AnalisFarmasi = findViewById(R.id.tvAnalisFarmasi);
        Radiografer = findViewById(R.id.tvRadiografer);
        Radioterapis = findViewById(R.id.tvRadioterapis);
        Elektromedis = findViewById(R.id.tvElektromedis);
        TeknisiGigi = findViewById(R.id.tvTeknisiGigi);
        AnalisKesehatan = findViewById(R.id.tvAnalisKesehatan);
        Refraksionis = findViewById(R.id.tvRefraksionis);
        RekamMedik = findViewById(R.id.tvRekamMedis);
        Ortotik = findViewById(R.id.tvOrtotik);
        TransfusiDarah = findViewById(R.id.tvTeknisiTransfusiDarah);
        Kardiovaskular = findViewById(R.id.tvKardiovaskular);
        Epidemiologi = findViewById(R.id.tvEpidemiologi);
        PromosiKesehatan = findViewById(R.id.tvPromosiKesehatan);
        Perilaku = findViewById(R.id.tvPrilaku);
        Kesja = findViewById(R.id.tvKesja);
        AdministrasiKesehatan = findViewById(R.id.tvAdministrasiKesehatan);
        Biostatik = findViewById(R.id.tvRBiostatik);
        Reproduksi = findViewById(R.id.tvReproduksi);
        InformasiKesehatan = findViewById(R.id.tvInformasiKesehatan);
        KesmasLainnya = findViewById(R.id.tvKesmasLainnya);
        Sanitasi = findViewById(R.id.tvSanitasi);
        Entomologi = findViewById(R.id.tvEntomologi);
        Mikrobiologi = findViewById(R.id.tvMikrobiologi);
        KesehatanLingkungan = findViewById(R.id.tvKesehatanLingkungan);
        TerapiWicara = findViewById(R.id.tvTerapiWicara);
        Nutrisionis = findViewById(R.id.tvNutrisionis);
        Dietsien = findViewById(R.id.tvDietsien);
        Fisioterapi = findViewById(R.id.tvFisioterapi);
        TerapiOkupasi = findViewById(R.id.tvTerapiOkupasi);
        Akupunturis = findViewById(R.id.tvAkupunturis);
        ProgramKesehatan = findViewById(R.id.tvProgramKesehatan);
        AdministrasiKeuangan = findViewById(R.id.tvAdministrasiKeuangan);
        Humas = findViewById(R.id.tvHumas);
        Perencanaan = findViewById(R.id.tvPerencanaan);
        JaminanKesehatan = findViewById(R.id.tvJaminanKesehatan);
        Dosen = findViewById(R.id.tvDosen);
        Psikologi = findViewById(R.id.tvPsikologi);
        Pelaporan = findViewById(R.id.tvPelaporan);
        InformasiTeknologi = findViewById(R.id.tvInformasiTeknologi);
        Hukum = findViewById(R.id.tvHukum);
        Pekarya = findViewById(R.id.tvPekarya);
        Perpustakaan = findViewById(R.id.tvPerpustakaan);
        Widyaswara = findViewById(R.id.tvWidyaswara);
        TenagaNonKers = findViewById(R.id.tvTenagaNonKers);
        MejaOperasi = findViewById(R.id.tvMejaOperasi);
        MesinAnestesi = findViewById(R.id.tvMesinAnestesi);
        Ventilator = findViewById(R.id.tvVentilator);
        Inkubator = findViewById(R.id.tvInkubator);
        BlueLight = findViewById(R.id.tvBlueLight);
        U_S_G = findViewById(R.id.tvU_S_G);
        XRay = findViewById(R.id.tvXRay);
        CTScan = findViewById(R.id.tvCTScan);
        MRI = findViewById(R.id.tvMRI);
        EEG = findViewById(R.id.tvEEG);
        EKG = findViewById(R.id.tvEKG);
        Defilbrillator = findViewById(R.id.tvDefibrilator);
        Autoclav = findViewById(R.id.tvAutoclav);
        RawatJalan = findViewById(R.id.tvRawatJalan);
        RawatInap = findViewById(R.id.tvRawatInap);
        I_G_D = findViewById(R.id.tvI_G_D);
        BOR = findViewById(R.id.tvBOR);
        ALOS = findViewById(R.id.tvALOS);
        TOI = findViewById(R.id.tvTOI);
        NDR = findViewById(R.id.tvNDR);
        GDR = findViewById(R.id.tvGDR);
        LayananUnggulan = findViewById(R.id.tvLayananUnggulan);
        SIMRS = findViewById(R.id.tvSIMRS);
        Ambulan = findViewById(R.id.tvAmbulan);
        BankDarah = findViewById(R.id.tvBankDarah);
        TanggalUpdate = findViewById(R.id.tvTanggalUpdate);
        //DONE
        //SETTEXT
        KodeRS.setText(SKodeRs);
        TglRegistrasi.setText(SRegistrasi);
        RumahSakit.setText(SNamaRS);
        Jenis.setText(SJenis);
        KlsRS.setText(SKelasRS);
        DirekturRS.setText(SDirekturRS);
        LatarBelakang.setText(SLatarBelakang);
        Pemilik.setText(SPemilik);
        Alamat.setText(SAlamat);
        Kota.setText(SKota);
        KodePos.setText(SKodePos);
        Fax.setText(SFax);
        Email.setText(SEmail);
        Telepon.setText(STelepon);
        TeleponHumas.setText(STeleponHumas);
        Website.setText(SWebsite);
        LuasTanah.setText(SLuasTanah+" m²");
        LuasBangunan.setText(SLuasBangunan+ " m²");
        NoSuratIjin.setText(SNoSuratIzin);
        TanggalSuratIjin.setText(STanggalSuratIzin);
        SuratIjinDari.setText(SSuratIzinDari);
        TanggalSuratIjin.setText(STanggalSuratIzin);
        MasaBerlakuSuratIjin.setText(SMasaBerlakuSuratIzin);
        StatusPenyelenggara.setText(SStatusPenyelenggara);
        StatusAkreditasi.setText(SStatusAkreditasi);
        SifatSuratIjin.setText(SSifatSuratIjin);
        TglAkreditasi.setText(STanggalAkreditasi);
        BerlakuSampai.setText(SBerlakuSampai);
        VVIP.setText(SVVIP+" Tempat Tidur");
        VIP.setText(SVIP+" Tempat Tidur");
        kelas1.setText(SKelas1+" Tempat Tidur");
        kelas2.setText(SKelas2+" Tempat Tidur");
        kelas3.setText(SKelas3+" Tempat Tidur");
        ICU.setText(SICU+" Tempat Tidur");
        PICU.setText(SPICU+" Tempat Tidur");
        NICU.setText(SNICU+" Tempat Tidur");
        TTBayi.setText(STTBayiBaru+" Tempat Tidur");
        HCU.setText(SHCU+" Tempat Tidur");
        ICCU.setText(SICCU+" Tempat Tidur");
        IGD.setText(SIGD+" Tempat Tidur");
        TTOperasi.setText(STTRuangOperasi+" Tempat Tidur");
        TTIsolasi.setText(STTRuangIsolasi+" Tempat Tidur");
        DrUmum.setText(SDrUmum+" Orang");
        drOg.setText(SDrOG+" Orang");
        drPd.setText(SDrPD+" Orang");
        drB.setText(SDrB+" Orang");
        drRad.setText(SDrRad+" Orang");
        drRm.setText(SDrRM+" Orang");
        drAn.setText(SDrAn+" Orang");
        drJp.setText(SDrJp+" Orang");
        drM.setText(SDrM+" Orang");
        drTHT.setText(SDrTHT+" Orang");
        drPK.setText(SDrPK+" Orang");
        drParu.setText(SDrParu+" Orang");
        drBedahThoraks.setText(SDrBedahThoraks+" Orang");
        drBedahAnak.setText(SDrBedahAnak+" Orang");
        drBedahOrthopedi.setText(SDrBedahOrhopedi+" Orang");
        drA.setText(SDrA+" Orang");
        drOkupasi.setText(SDrOkupasi+" Orang");
        drUrologi.setText(SDrUrologi+" Orang");
        drOrhopedi.setText(SDrOrthopedi+" Orang");
        drKulit.setText(SDrKulit+" Orang");
        drForensik.setText(SDrForensik+" Orang");
        drPsikiatri.setText(SDrPsikiatri+" Orang");
        drOfthalmologi.setText(SDrOfthalmologi+" Orang");
        drAnatomi.setText(SDrAnatomi+" Orang");
        drJiwa.setText(SDrJiwa+" Orang");
        drSaraf.setText(SDrSaraf+" Orang");
        drLainnya.setText(SDrLainnya+" Orang");
        drBedahSaraf.setText(SDrBedahSaraf+" Orang");
        drBedahPlastik.setText(SDrBedahPlastik+" Orang");
        drSubSpesialis.setText(SDrSubSpesialis+" Orang");
        DrGigi.setText(SDrGigi+" Orang");
        DrBedahMulut.setText(SDrBedahMulut+" Orang");
        DrKonservasi.setText(SDrKonservasi+" Orang");
        DrPenyakitMulut.setText(SDrPenyakitMulut+" Orang");
        DrRadiologi.setText(SDrRadiologi+" Orang");
        DrKarangGigi.setText(SDrKarangGigi+" Orang");
        DrAnak.setText(SDrGigiAnak+" Orang");
        DrGigiTiruan.setText(SDrGigiTiruan+" Orang");
        DrPeriodonsia.setText(SDrPeriodonsia+" Orang");
        DrGigiLainnya.setText(SDrGigiLainnya+" Orang");
        BidanPendidik.setText(SBidanPendidik+" Orang");
        BidanKlinik.setText(SBidanKlinik+" Orang");
        Apoteker.setText(SApoteker+" Orang");
        AnalisFarmasi.setText(SAnalisFarmasi+" Orang");
        Radiografer.setText(SRadiografer+" Orang");
        Radioterapis.setText(SRadioterapis+" Orang");
        Elektromedis.setText(SElektromedis+" Orang");
        TeknisiGigi.setText(STeknisiGigi+" Orang");
        AnalisKesehatan.setText(SAnalisKesehatan+" Orang");
        Refraksionis.setText(SRefraksionis+" Orang");
        RekamMedik.setText(SRekamMedik+" Orang");
        Ortotik.setText(SOrtotik+" Orang");
        TransfusiDarah.setText(STransfusiDarah+" Orang");
        Kardiovaskular.setText(Skardiovaskular+" Orang");
        Epidemiologi.setText(SEpidemiologi+" Orang");
        PromosiKesehatan.setText(SPromosiKesehatan+" Orang");
        Perilaku.setText(SPerilaku+" Orang");
        Kesja.setText(SKesja+" Orang");
        AdministrasiKesehatan.setText(SAdministrasiKesehatan+" Orang");
        Biostatik.setText(SBiostatik+" Orang");
        Reproduksi.setText(SReproduksi+" Orang");
        InformasiKesehatan.setText(SInfromasiKesehatan+" Orang");
        KesmasLainnya.setText(SKesmasLainnya+" Orang");
        //Tenaga Kesehatan Lainnya
        Sanitasi.setText(SSanitasi+" Orang");
        Entomologi.setText(SEntomologi+" Orang");
        Mikrobiologi.setText(SMikrobiologi+" Orang");
        KesehatanLingkungan.setText(SKesehatanLingkungan+" Orang");
        TerapiWicara.setText(STerapiWicara+" Orang");
        Nutrisionis.setText(SNutrisionis+" Orang");
        Dietsien.setText(SDietsien+" Orang");
        Fisioterapi.setText(SFisioterapi+" Orang");
        TerapiOkupasi.setText(STerapiOkupasi+" Orang");
        Akupunturis.setText(SAkupunturis+" Orang");
        //Tenaga Non Kesehatan
        ProgramKesehatan.setText(SProgramKesehatan+" Orang");
        AdministrasiKeuangan.setText(SAdministrasiKeuangan+" Orang");
        Humas.setText(SHumas+" Orang");
        Perencanaan.setText(SPerencaan+" Orang");
        JaminanKesehatan.setText(SJaminanKesehatan+" Orang");
        Dosen.setText(SDosen+" Orang");
        Psikologi.setText(SPsikologi+" Orang");
        Pelaporan.setText(SPelaporan+" Orang");
        InformasiTeknologi.setText(SInformasiTeknologi+" Orang");
        Hukum.setText(SHukum+" Orang");
        Pekarya.setText(SPekarya+" Orang");
        Perpustakaan.setText(SPerpustakaan+" Orang");
        Widyaswara.setText(SWidyaswara+" Orang");
        TenagaNonKers.setText(STenagaNonKers+" Orang");
        MejaOperasi.setText(SMejaOperasi);
        MesinAnestesi.setText(SMesinAnestesi);
        Ventilator.setText(SVentilator);
        Inkubator.setText(SInkubator);
        BlueLight.setText(SBlueLight);
        U_S_G.setText(SUSG);
        XRay.setText(SXray);
        CTScan.setText(SCTScan);
        MRI.setText(SMRI);
        EEG.setText(SEEG);
        EKG.setText(SEKG);
        Defilbrillator.setText(SDefibrilator);
        Autoclav.setText(SAutoclav);
        RawatJalan.setText(SRawatJalan);
        RawatInap.setText(SRawatInap);
        I_G_D.setText(SI_G_D);
        BOR.setText(SBOR+" %");
        ALOS.setText(SALOS+" Hari");
        TOI.setText(STOI+" %");
        NDR.setText(SNDR);
        GDR.setText(SGDR);
        LayananUnggulan.setText(SLayananUnggulan);
        SIMRS.setText(SSIMRS);
        Ambulan.setText(SAmbulan);
        BankDarah.setText(SBankDarah);
        TanggalUpdate.setText(STanggalUpdate);
        dbHelper = new DB_Helper(this);
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Token = cursor.getString(0);
            }
        }
        //Cut Here
        rv2 = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Available.setVisibility(View.VISIBLE);
        pList.addAll(MiningOutlookModel.getListData());
        rv2.setLayoutManager(new LinearLayoutManager(this));
        AdapterNavigator adapters = new AdapterNavigator(this,pList);
        rv2.setAdapter(adapters);

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

        //Cut Here
    }
}