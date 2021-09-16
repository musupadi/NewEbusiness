package com.ascendant.e_businessprofile.Model.StaticModel;

public class models_fivec {
    private String idfivec;
    private String jawabanfivec;
    private String scorefivec;
    public models_fivec(){

    }

    public models_fivec(String idfivec, String jawabanfivec, String scorefivec){
        this.idfivec=idfivec;
        this.jawabanfivec=jawabanfivec;
        this.scorefivec=scorefivec;
    }

    public String getIdfivec() {
        return idfivec;
    }

    public void setIdfivec(String idfivec) {
        this.idfivec = idfivec;
    }

    public String getJawabanfivec() {
        return jawabanfivec;
    }

    public void setJawabanfivec(String jawabanfivec) {
        this.jawabanfivec = jawabanfivec;
    }

    public String getScorefivec() {
        return scorefivec;
    }

    public void setScorefivec(String scorefivec) {
        this.scorefivec = scorefivec;
    }
}
