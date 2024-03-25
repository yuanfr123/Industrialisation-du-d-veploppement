/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp04.metier;

/**
 *
 * @author somebody
 */
public class Jour {

    private int annee;
    private int noJour;

    /**
     * Get the value of annee
     *
     * @return the value of annee
     */
    public int getAnnee() {
        return annee;
    }

    /**
     * Get the value of noJour
     *
     * @return the value of noJour
     */
    public int getNoJour() {
        return noJour;
    }

    public Jour(int annee, int noJour) {
        this.annee = annee;
        this.noJour = noJour;
    }

    /**
     * @author RS & Yinc
     * @param numJour numero du jour
     * @param numAnnee numero de l'annee
     * @return object jour de la veille ou de la semaine precedente
     */
    public Jour getJour(int numJour, int numAnnee){
        // Gérer le cas où le jour précédent tombe avant le début de l'année
        if (numJour <= 0) {
            numAnnee--;
            numJour = Jour.nbJoursDansAnnee(numAnnee) + numJour; // Nb de jours dans l'année précédente
        }

        return new Jour(numAnnee, numJour);
    }

    
    /**
     * @author Rs yinc
     * @return le jour precedent a un jour donne
     */
    public Jour jourPrecedent() {
        int nouveauJour = this.getNoJour() - 1;
        int nouvelleAnnee = this.annee;

        return getJour(nouveauJour, nouvelleAnnee);

    }
    
    /**
     * @author Rs yinc
     * @return le jour qui se situe exactement une semaine avant un jour donné.
     */
    public Jour semainePrecedente() {
        int nouveauJour = this.getNoJour() - 7;
        int nouvelleAnnee = this.annee;

        return getJour(nouveauJour, nouvelleAnnee);
    }

    
    /**
     * @author Rs yinc
     * @param annee que l on souhaite le nombre de jours
     * @return nombre de jours dans une annee
     */
    private static int nbJoursDansAnnee(int annee) {
        return (annee % 4 == 0 && (annee % 100 != 0 || annee % 400 == 0)) ? 366 : 365;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.annee;
        hash = 61 * hash + this.noJour;
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
        final Jour other = (Jour) obj;
        if (this.annee != other.annee) {
            return false;
        }
        if (this.noJour != other.noJour) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Jour{" + "annee=" + annee + ", noJour=" + noJour + '}';
    }

}
