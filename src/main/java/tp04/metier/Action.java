/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp04.metier;

import java.util.Objects;

/**
 *
 * @author somebody
 */
public abstract class Action {

    private String libelle;

    /**
     * Get the value of libelle
     *
     * @return the value of libelle
     */
    public String getLibelle() {
        return libelle;
    }

    public Action(String libelle) {
        this.libelle = libelle;
    }
    
    
    /**
     * @author Rs yinc
     * @param j jour actuel
     * @return la visualisation de l evolution de la valeur d une action par rapport a la veille
     */
    public String evolutionValeurVeilleAction(Jour j) {
        Jour veille = j.jourPrecedent();
        StringBuilder sb = new StringBuilder();
        
        sb.append("Evolution de la valeur de l'action: \n");
        
        sb.append("valeur au ").append(veille.toString()).append(": ").append(this.valeur(veille)).append("\n");
        
        sb.append("valeur au ").append(j.toString()).append(": ").append(this.valeur(j)).append("\n");
        
        double variationPourcentage = (((this.valeur(j) - this.valeur(veille)) / this.valeur(veille)) * 100);
        double variationArrondie = Math.round(variationPourcentage * 100.0) / 100.0;

        
        if (this.valeur(j) > this.valeur(veille)) {
            sb.append("Evolution : ").append(variationArrondie).append("% ↑");
            return sb.toString();
        }
        sb.append("Evolution : ").append(variationArrondie).append("% ↓");

        
        return sb.toString();
    }
    
    /**
     * @author Rs yinc
     * @param j jour actuel
     * @return la visualisation de l evolution de la valeur d une action par rapport a la semaine precedente
     */
    public String evolutionValeurSemaineAction(Jour j) {
        Jour semainePrecedente = j.semainePrecedente();
        StringBuilder sb = new StringBuilder();
        
        sb.append("Evolution de la valeur de l'action: \n");
        
        sb.append("valeur au ").append(semainePrecedente.toString()).append(": ").append(this.valeur(semainePrecedente)).append("\n");
        
        sb.append("valeur au ").append(j.toString()).append(": ").append(this.valeur(j)).append("\n");
        
        double variationPourcentage = (((this.valeur(j) - this.valeur(semainePrecedente)) / this.valeur(semainePrecedente)) * 100);
        double variationArrondie = Math.round(variationPourcentage * 100.0) / 100.0;
        
        if (this.valeur(j) > this.valeur(semainePrecedente)) {
            sb.append("Evolution : ").append(variationArrondie).append("% ↑");
            return sb.toString();
        }
       
        sb.append("Evolution : ").append(variationArrondie).append("% ↓");
        return sb.toString();
    }

    public abstract float valeur(Jour j);

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.libelle);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Action other = (Action) obj;
        if (!Objects.equals(this.libelle, other.libelle)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return this.getLibelle();
    }
}
