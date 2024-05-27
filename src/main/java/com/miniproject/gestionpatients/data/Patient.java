package com.miniproject.gestionpatients.data;

public class Patient extends Personne {
//    private String cin;
//    private String nom;
//    private String prenom;
    private String sexe;
    private String tel;
    private String cinPersonnel;
    public  Patient(){
        super();
    }
    public Patient (String cin , String nom , String prenom , String sexe , String tel , String cinPersonnel){
        super(cin , nom , prenom);
        this.sexe = sexe ;
        this.tel = tel ;
        this.cinPersonnel = cinPersonnel ;
    }

//    public String getCin() {
//        return cin;
//    }
//
//    public void setCin(String cin) {
//        this.cin = cin;
//    }
//
//    public String getNom() {
//        return nom;
//    }
//
//    public void setNom(String nom) {
//        this.nom = nom;
//    }
//
//    public String getPrenom() {
//        return prenom;
//    }
//
//    public void setPrenom(String prenom) {
//        this.prenom = prenom;
//    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCinPersonnel() {
        return cinPersonnel;
    }

    public void setCinPersonnel(String cinPersonnel) {
        this.cinPersonnel = cinPersonnel;
    }
}
