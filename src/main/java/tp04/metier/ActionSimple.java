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
 * @author somebody
 */
public class ActionSimple extends Action {

    // attribut lien
    private Map<Jour, Cours> mapCours;

    // constructeur
    /**
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
     */
    public void enrgCours(Jour j, float v) throws IllegalArgumentException {
        if (this.mapCours.containsKey(j) == false) {
            if (v>0) {
                this.mapCours.put(j, new Cours(j, v));
            }
            else {
                throw new IllegalArgumentException("on ne peut pas enregistrer un cours négatif pour l'action "+ this.getLibelle());
            }
        }
        else {
            throw new IllegalArgumentException("le cours est pour "+this.getLibelle()+" est déja enregistré a cette date");
        }
             
    }

    @Override
    public float valeur(Jour j) {
        if (this.mapCours.containsKey(j) == true) {
            return this.mapCours.get(j).getValeur();
        } else {
            return 0; // definition d'une constante possible
        }
    }
    
}
