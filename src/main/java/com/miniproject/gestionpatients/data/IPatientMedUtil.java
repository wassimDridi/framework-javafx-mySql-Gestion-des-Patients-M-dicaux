package com.miniproject.gestionpatients.data;

import javafx.collections.ObservableList;

public interface IPatientMedUtil {
    ObservableList<PatientMed> getPatientMed();
    PatientMed getPatientMedByRefMed(int refMed);
    boolean ajouterPatientMed(PatientMed pm);
    boolean modifierPatientMed(PatientMed pm);
    ObservableList<PatientMed> rechercherPatientMedByRefMed(int refMed);
    ObservableList<PatientMed> rechercherPatientMedByPatient(String cinPat);
    ObservableList<PatientMed> rechercherPatientMedByPersonnel(String cinPer);
    boolean supprimerPatientMed(int refMed , String cinPat);
}
