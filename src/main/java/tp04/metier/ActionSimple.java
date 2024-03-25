/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp04.metier;

import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author groupe 1
 * Classe concernant les actions dites simples, uniques 
 */
public class ActionSimple extends Action {

    // attribut lien
    private Map<Jour, Cours> mapCours;

    // constructeur
    /**
     * @author groupe 1
     * constructeur permettant de créer une action simple
     * @param libelle correspond au nom de l'action
     */
    public ActionSimple(String libelle) throws IllegalArgumentException {
        // Action simple initialisée comme 1 action
        super(libelle);
        if (!libelle.isEmpty()) {
            // init spécifique
            this.mapCours = new HashMap();
            System.out.println("action crée");
        }
        else {
            String erreur = "le vide n'est pas un lib valide";
            throw new IllegalArgumentException(erreur);
        }
    }

    // enrg possible si pas de cours pour ce jour

    /**
     * méthode permettant l'enregistrement du cours d'une action a un jour donné
     * @param j correspond au jours sur lequel on enregistre le cour
     * @param v correspond a la valeur a définir pour ce jour et action
     * @author groupe 1
     */
    public void enrgCours(Jour j, float v) throws IllegalArgumentException {
        if (this.mapCours.containsKey(j) == false) {
            if (v>0) {
                this.mapCours.put(j, new Cours(j, v));
            }
            else {
                throw new IllegalArgumentException("on ne peut pas enregistrer"
                        + " un cours négatif pour l'action "+ this.getLibelle());
            }
        }
        else {
            throw new IllegalArgumentException("le cours pour "+this.getLibelle()
                    +" est déja enregistré a cette date");
        }
             
    }

    /**
     * @author groupe 1
     * consulter la valeur de l'action pour un jour donné
     * @param j représente le jour pour lequel 
     * nous voulons savoir la valeur de l'action
     * @return un float représentanrt la valeur de l'action ce jour là
     */
    @Override
    public float valeur(Jour j) throws IllegalArgumentException{
        if (this.mapCours.containsKey(j) == true) {
            return this.mapCours.get(j).getValeur();
        } else {
            throw new IllegalArgumentException("le cours pour "+j.toString()
                    +" n'est pas encore enregistré");
        }
    }
}
