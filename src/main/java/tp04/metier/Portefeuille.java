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

    public void vendre(Action a, int q) {
        if (this.mapLignes.containsKey(a) == true) {
            if (this.mapLignes.get(a).getQte() > q) {
                this.mapLignes.get(a).setQte(this.mapLignes.get(a).getQte() - q);
            } else if (this.mapLignes.get(a).getQte() == q) {
                this.mapLignes.remove(a);
            }
        }
    }

    public String toString() {
        return this.mapLignes.toString();
    }

    public float valeur(Jour j) {
        float total = 0;
        for (LignePortefeuille lp : this.mapLignes.values()) {
            total = total + (lp.getQte() * lp.getAction().valeur(j));
        }
        return total;
    }
    
    /**
     * 
     * @param j jour actuelle
     * @return l'evolution de la valeur par rapport a la veille;
     */
    public String afficherEvolutionVeille(Jour j) {
        Jour veille = j.jourPrecedent();
        StringBuilder sb = new StringBuilder();
        
        sb.append("Evolution de la valeur du portefeuille: \n");
        
        sb.append("valeur au ").append(veille.toString()).append(": ").append(this.valeur(veille)).append("\n");
        
        sb.append("valeur au ").append(j.toString()).append(": ").append(this.valeur(j)).append("\n");
        
        if (this.valeur(j) > this.valeur(veille)) {
            sb.append("Evolution : ").append((this.valeur(j) - this.valeur(veille)) / 100).append("↓");
            return sb.toString();
        }
       
        sb.append("Evolution : ").append((this.valeur(j) - this.valeur(veille)) / 100).append("↑");
        return sb.toString();
        
    }
    
    public String afficherEvolutionSemaine(Jour j) {
        Jour semainePrecedente = j.semainePrecedente();
        StringBuilder sb = new StringBuilder();
        
        sb.append("Evolution de la valeur du portefeuille: \n");
        
        sb.append("valeur au ").append(semainePrecedente.toString()).append(": ").append(this.valeur(semainePrecedente)).append("\n");
        
        sb.append("valeur au ").append(j.toString()).append(": ").append(this.valeur(j)).append("\n");
        
        if (this.valeur(j) > this.valeur(semainePrecedente)) {
            sb.append("Evolution : ").append((this.valeur(j) - this.valeur(semainePrecedente)) / 100).append("↓");
            return sb.toString();
        }
       
        sb.append("Evolution : ").append((this.valeur(j) - this.valeur(semainePrecedente)) / 100).append("↑");
        return sb.toString();
        
    }
}
