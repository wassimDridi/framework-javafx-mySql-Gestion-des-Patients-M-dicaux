package com.miniproject.gestionpatients.data;

import javafx.collections.ObservableList;

public interface IPersonnelUtil {
    ObservableList<Personnel> getPersonnel();
    Personnel getPersonnelByCin(String cin);
    boolean ajouterPersonnel(Personnel p);
    boolean modifierPersonnel(Personnel p);
    ObservableList<Personnel> rechercherPersonnel(String nomR);
    boolean supprimerPersonnel(String cin);
    public Personnel getPersonnelByloginPassword(String login, String password) ;

}
