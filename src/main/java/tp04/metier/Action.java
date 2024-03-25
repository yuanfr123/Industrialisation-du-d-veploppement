/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp04.metier;

import java.util.Objects;

/**
 * Cette classe abstraite représente une action.
 * Elle contient un libellé et une méthode pour connaitre la valeur de l'action pour un jour donné.
 * @author somebody
 * 
 */
public abstract class Action {

    private String libelle;

    /**
     * Récupère la valeur du libelée
     * @return the value of libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Constructeur de la classe Action
     * @param libelle : le libéllé de l'action
     */
    public Action(String libelle) {
        this.libelle = libelle;
    }

    /**
     * @author RS & Yinc
     * @param j Jour actuel
     * @param jourPrecedent la veille ou jour de la semaine derniere
     * @return l'evolution du jour actuel par rapport au jour precedent
     */
    private String getStringEvolutionAction(Jour j, Jour jourPrecedent) {
        StringBuilder sb = new StringBuilder();

        sb.append("Evolution de la valeur de l'action: \n");

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
     * @param j jour actuel
     * @return la visualisation de l evolution de la valeur d une action par rapport a la veille
     */
    public String evolutionValeurVeilleAction(Jour j) {
        Jour veille = j.jourPrecedent();
        return getStringEvolutionAction(j, veille);
    }

    /**
     * @author Rs yinc
     * @param j jour actuel
     * @return la visualisation de l evolution de la valeur d une action par rapport a la semaine precedente
     */
    public String evolutionValeurSemaineAction(Jour j) {
        Jour semainePrecedente = j.semainePrecedente();
        return getStringEvolutionAction(j, semainePrecedente);
    }

    /**
     * Méthode pour connaitre la valeur de l'action pour un jour donné.
     * @param j : le jour pour lequel on souhaite connaitre la valeur de l'action
     * @return la valeur de l'action pour le jour donné
     */
    public abstract float valeur(Jour j);

    /**
     * Rédefinition de la méthode hashCode
     * @return le code de hashage de l'objet
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.libelle);
        return hash;
    }

    /**
     * Rédefinition de la méthode equals
     * @param obj : l'objet à compater
     * @return true si les deux objets sont egaux, false dans l'autre cas
     */
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

    /**
     * Définition de la méthode toString
     * @return nom textuel (libelle) de l'action
     */
    public String toString() {
        return this.getLibelle();
    }
}
