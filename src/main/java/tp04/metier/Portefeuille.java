/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp04.metier;

import java.util.HashMap;
import java.util.Map;

/**
 *Cette classe représente un portefeuille d'actions, où chaque action est associée à une quantité spécifique.
 * @author Tout le groupe.
 */
public class Portefeuille {

    Map<Action, LignePortefeuille> mapLignes;
/**
 * Une collection d'objets, où chaque objet représente une ligne contenant une action et sa quantité correspondante.
 * @author Tout le groupe.
 */
    private class LignePortefeuille {

        private Action action;

        private int qte;

        public int getQte() {
            return qte;
        }

        public void setQte(int qte) {
            this.qte = qte;
        }

        public Action getAction() {
            return this.action;
        }

        public LignePortefeuille(Action action, int qte) {
            this.action = action;
            this.qte = qte;
        }
    /**
     * Méthode qui rend le contenu de la collection;
     * @return a string representation of this LignePortefeuille
     */
        public String toString() {
            return Integer.toString(qte);
        }
    }
/**
 *La création de portefeuille implique la création de la collection des actions sous la forme de hashmap.
 * @author Tout le groupe.
 */
    public Portefeuille() {
        this.mapLignes = new HashMap();
    }
    
    /**
     * @author RY.
     * Méthode qui rend le hashMap des actions et qte contenu dans le portefeuille
     * @return hashMap
     */
    public Map<Action, LignePortefeuille> getMapLignes() {
        return mapLignes;
    }

    public void acheter(Action a, int q) throws IllegalArgumentException {
        if (q < 1) {
            throw new IllegalArgumentException("achter au moins une action");
        }
        if (this.mapLignes.containsKey(a) == false) {
            this.mapLignes.put(a, new LignePortefeuille(a, q));
        } else {
            this.mapLignes.get(a).setQte(this.mapLignes.get(a).getQte() + q);
        }
    }
    /**
     * @author YR.
     * Méthode qui rend la valeur globale contenu dans le portefeuille;
     * @param a Action
     * @param q int
     */
    public void vendre(Action a, int q) throws IllegalArgumentException{
        if (this.mapLignes.containsKey(a) == true) {
            if (this.mapLignes.get(a).getQte() > q) {
                this.mapLignes.get(a).setQte(this.mapLignes.get(a).getQte() - q);
            } else if (this.mapLignes.get(a).getQte() == q) {
                this.mapLignes.remove(a);
            }else{
            throw new IllegalArgumentException("Vous n'avez pas autant d'actions.");
        }
        }
        else{
            throw new IllegalArgumentException("Ne peut pas vendre les actions qu'on n'possède pas.");
        }
    }
    /**
     * @author YR.
     * Méthode qui rend la contenu dans le portefeuille;
     * @return a string representation of this Portefeuille
     */
    public String toString() {
        return this.mapLignes.toString();
    }
    /**
     * @author RY.
     * Méthode qui rend la valeur globale contenu dans le portefeuille;
     * @param j jour
     * @return total en float
     */
    public float valeur(Jour j) throws IllegalArgumentException{
        float total = 0;
        for (LignePortefeuille lp : this.mapLignes.values()) {
            total = total + (lp.getQte() * lp.getAction().valeur(j));
            //illegalArguments Message : le cours pour le jour j n'est pas encore enregistré
        }
        return total;
    }

    /**
     * @author RS & Yinc
     * @param j Jour actuel
     * @param jourPrecedent la veille ou jour de la semaine derniere
     * @return l'evolution du jour actuel par rapport au jour precedent
     */
    private String getStringEvolutionPortefeuille(Jour j, Jour jourPrecedent) {
        StringBuilder sb = new StringBuilder();

        sb.append("Evolution de la valeur du portefeuille: \n");

        sb.append("valeur au ").append(jourPrecedent.toString()).append(": ").append(this.valeur(jourPrecedent)).append("\n");

        sb.append("valeur au ").append(j.toString()).append(": ").append(this.valeur(j)).append("\n");

        double variationPourcentage = (((this.valeur(j) - this.valeur(jourPrecedent)) / this.valeur(jourPrecedent)) * 100);
        double variationArrondie = Math.round(variationPourcentage * 100.0) / 100.0;


        if (this.valeur(j) > this.valeur(jourPrecedent)) {
            sb.append("Evolution : ").append(variationArrondie).append("% ↑");
            return sb.toString();
        }
        sb.append("Evolution : ").append(variationArrondie).append("% ↓");


        return sb.toString();
    }
    
    /**
     * @author Rs yinc
     * @param j jour actuelle
     * @return l'evolution de la valeur par rapport a la veille;
     */
    public String afficherEvolutionVeille(Jour j) {
        Jour veille = j.jourPrecedent();
        return getStringEvolutionPortefeuille(j, veille);
        
    }
    
    /**
     * @author Rs yinc
     * @param j jour actuelle
     * @return l'evolution de la valeur par rapport a la semaine precedente;
     */
    public String afficherEvolutionSemaine(Jour j) {
        Jour semainePrecedente = j.semainePrecedente();
        return getStringEvolutionPortefeuille(j, semainePrecedente);
        
    }
}
