package com.miniproject.gestionpatients.data;

public class PatientMed {
    private int refMed;
    private String cinPat;
    private String cinPer;

    public PatientMed(int refMed , String cinPat , String cinPer){
        this.refMed = refMed ;
        this.cinPat = cinPat ;
        this.cinPer = cinPer ;
    }
    public PatientMed(){

    }

    public int getRefMed() {
        return refMed;
    }

    public void setRefMed(int refMed) {
        this.refMed = refMed;
    }

    public String getCinPat() {
        return cinPat;
    }

    public void setCinPat(String cinPat) {
        this.cinPat = cinPat;
    }

    public String getCinPer() {
        return cinPer;
    }

    public void setCinPer(String cinPer) {
        this.cinPer = cinPer;
    }
}
