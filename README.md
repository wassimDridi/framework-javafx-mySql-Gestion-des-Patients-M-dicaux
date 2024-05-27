Gestion des Patients Médicaux

Introduction

Ce projet est une application Java pour la gestion des patients dans un hôpital. Elle permet aux administrateurs et au personnel hospitalier de gérer les informations sur les patients, les médicaments et le personnel de manière sécurisée et efficace.

Prérequis
Avant de commencer, assurez-vous d'avoir les éléments suivants installés sur votre 
machine :
Java Development Kit (JDK) 8+
Apache Maven
MySQL

Installation
Cloner le dépôt
git clone https://github.com/wassimDridi/framework-javafx-mySql-Gestion-des-Patients-M-dicaux

Configurer la base de données

Créez une base de données MySQL et importez le script SQL fourni pour créer les tables nécessaires.

sql
file gestion_patients.sql
Mettez à jour les informations de connexion à la base de données dans le fichier src/main/resources/database.properties.

Compiler et exécuter le projet

Utilisez Maven pour compiler le projet et exécuter l'application.

mvn clean install

Utilisation
Authentification
L'application démarre avec une interface d'authentification où les utilisateurs doivent entrer leur identifiant et leur mot de passe pour accéder aux fonctionnalités.

Gestion des Patients
Ajouter un patient : Remplissez le formulaire avec les informations du patient et cliquez sur "Valider".
Modifier un patient : Sélectionnez un patient dans la liste, cliquez sur "Modifier", mettez à jour les informations et cliquez sur "Valider".
Supprimer un patient : Sélectionnez un patient dans la liste et cliquez sur "Supprimer".
Générer un PDF : Sélectionnez un patient et cliquez sur "Générer PDF" pour créer un fichier PDF avec les détails du patient.
Gestion des Médicaments
La gestion des médicaments suit le même principe que la gestion des patients, permettant l'ajout, la modification, la suppression et la recherche de médicaments.

Structure du Projet
Le projet est organisé selon le modèle MVC (Modèle-Vue-Contrôleur) :

src/main/java/com/hopital/model : Contient les classes de modèle (par exemple, Patient, Medicament).
src/main/java/com/hopital/controller : Contient les classes de contrôleur qui gèrent la logique d'application.
src/main/java/com/hopital/view : Contient les fichiers FXML et les classes JavaFX pour les interfaces utilisateur.
Auteurs

Wassim Dridi - [Profil GitHub](https://github.com/wassimDridi)


Licence
Ce projet est sous licence MIT - voir le fichier LICENSE pour plus de détails.

