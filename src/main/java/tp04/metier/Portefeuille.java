/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp04.metier;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author somebody
 */
public class Portefeuille {

    Map<Action, LignePortefeuille> mapLignes;

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

        public String toString() {
            return Integer.toString(qte);
        }
    }

    public Portefeuille() {
        this.mapLignes = new HashMap();
    }
    
    /**
     * R&Y
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
            throw new IllegalArgumentException("Ne peut pas vendre les actions qu'on n'a pas.");
        }
    }

    public String toString() {
        return this.mapLignes.toString();
    }
    /**
     * R&Y
     * Méthode qui rend la valeur globale contenu dans le portefeuille
     * @param j jour
     * @return valeur totale en float
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
