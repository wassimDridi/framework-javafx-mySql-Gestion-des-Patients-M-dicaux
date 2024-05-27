package com.miniproject.gestionpatients.data;

import javafx.collections.ObservableList;

public interface IMedicamentUtil {
    ObservableList<Medicament> getMedicament();
    Medicament getMedicamentByRef(int ref);
    boolean ajouterMedicament(Medicament p);
    boolean modifierMedicament(Medicament p);
    ObservableList<Medicament> rechercherMedicament(String libelle);
    boolean supprimerMedicament(int ref);
}
