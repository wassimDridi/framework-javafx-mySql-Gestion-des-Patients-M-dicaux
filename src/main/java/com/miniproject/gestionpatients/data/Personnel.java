package com.miniproject.gestionpatients.data;

public class Personnel extends Personne {
    private String login;
    private String password;
    private String fonction;

    public Personnel(String cin, String nom, String prenom, String login, String password, String fonction) {
        super(cin , nom , prenom);
//        this.cin = cin;
//        this.nom = nom;
//        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.fonction = fonction;
    }
    public Personnel(){
        super();
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }
}