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
