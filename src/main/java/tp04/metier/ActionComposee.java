/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp04.metier;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author somebody
 */
public class ActionComposee extends Action {

    // attribut lien
    Map<ActionSimple, Float> mapPanier;

    public ActionComposee(String libelle) {
        super(libelle);
        this.mapPanier = new LinkedHashMap();
    }
    
    /**
     * 
     * @param as action simple faisant partie de l'action composee
     * @param pourcentage de l'action simple dans l'action composee
     * @throws IllegalArgumentException si le poucentage est negatif
     */
    public void enrgComposition(ActionSimple as, float pourcentage) throws IllegalArgumentException {
        
        if (pourcentage < 0) {
            throw new IllegalArgumentException("Pas de valeur negative en pourcentage");
        }
        this.mapPanier.put(as, pourcentage);
    }
    
    /**
     * voir la compostion dune action composee
     * @return composition as string
     */
    public String consulterComposition()  {
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("voici la composition de cette action: \n");
                
        for(Map.Entry<ActionSimple, Float> action: this.mapPanier.entrySet()){
           sb.append("Action: ").append(action.getKey()).append(", Composition: ").append(action.getValue() * 100).append("\n");
      
        }
        return sb.toString();
    }

    @Override
    public float valeur(Jour j) {
        float valeur;

        valeur = 0;
        for (ActionSimple as : this.mapPanier.keySet()) {
            valeur = valeur + (as.valeur(j) * this.mapPanier.get(as));
        }

        return valeur;
    }

}
