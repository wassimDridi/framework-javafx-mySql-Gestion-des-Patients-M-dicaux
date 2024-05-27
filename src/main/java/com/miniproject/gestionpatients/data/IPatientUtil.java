package com.miniproject.gestionpatients.data;

import javafx.collections.ObservableList;

public interface IPatientUtil {
    ObservableList<Patient> getPatient();
    Patient getPatientByCin(String cin);
    boolean ajouterPatient(Patient p);
    boolean modifierPatient(Patient p);
    ObservableList<Patient> rechercherPatient(String nom);
    boolean supprimerPatient(String cin);
}
