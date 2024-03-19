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

    public Portefeuille() {
        this.mapLignes = new HashMap();
    }
    
    /**
     * R&Y.
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
     * Y&R.
     * Méthode qui rend la valeur globale contenu dans le portefeuille;
     * @param a Action
     * @param q int
     * @return visualisation en string
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
     * Y&R.
     * Méthode qui rend la valeur globale contenu dans le portefeuille;
     * @return a string representation of this Portefeuille
     */
    public String toString() {
        return this.mapLignes.toString();
    }
    /**
     * R&Y.
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
}
