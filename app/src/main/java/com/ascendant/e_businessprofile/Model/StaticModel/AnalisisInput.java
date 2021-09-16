package com.ascendant.e_businessprofile.Model.StaticModel;

public class AnalisisInput {
    private String ROI;
    private String CashRatio;
    private String CurrentRatio;
    private String ColectivePeriod;
    private String PP;
    private String TATO;
    private String AktivaBersih;
    private String KolumKe;
    public AnalisisInput(){

    }
    public AnalisisInput(String KolumKe,String ROI, String CashRatio,String CurrentRatio,String ColectivePeriod,String PP,String TATO,String AktivaBersih){
        this.KolumKe=KolumKe;
        this.ROI=ROI;
        this.CashRatio=CashRatio;
        this.CurrentRatio=CurrentRatio;
        this.ColectivePeriod=ColectivePeriod;
        this.PP=PP;
        this.TATO=TATO;
        this.AktivaBersih=AktivaBersih;
    }

    public String getKolumKe() {
        return KolumKe;
    }

    public void setKolumKe(String kolumKe) {
        KolumKe = kolumKe;
    }

    public String getROI() {
        return ROI;
    }

    public void setROI(String ROI) {
        this.ROI = ROI;
    }

    public String getCashRatio() {
        return CashRatio;
    }

    public void setCashRatio(String cashRatio) {
        CashRatio = cashRatio;
    }

    public String getCurrentRatio() {
        return CurrentRatio;
    }

    public void setCurrentRatio(String currentRatio) {
        CurrentRatio = currentRatio;
    }

    public String getColectivePeriod() {
        return ColectivePeriod;
    }

    public void setColectivePeriod(String colectivePeriod) {
        ColectivePeriod = colectivePeriod;
    }

    public String getPP() {
        return PP;
    }

    public void setPP(String PP) {
        this.PP = PP;
    }

    public String getTATO() {
        return TATO;
    }

    public void setTATO(String TATO) {
        this.TATO = TATO;
    }

    public String getAktivaBersih() {
        return AktivaBersih;
    }

    public void setAktivaBersih(String aktivaBersih) {
        AktivaBersih = aktivaBersih;
    }
}

