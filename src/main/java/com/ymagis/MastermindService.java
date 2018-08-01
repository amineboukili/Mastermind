package com.ymagis;

import com.ymagis.api.Api;
import com.ymagis.api.ResponseVO;

/**
 *
 * @author Team_4
 */
public class MastermindService {

    private int n;
    private int k;
    private long sommeTemps;
    private boolean showResult = true;
    
    /**
     * 
     * @param n
     * @param k
     * @param showResult
     */
    public MastermindService(int n, int k, boolean showResult) {
        this.n = n;
        this.k = k;
        this.showResult = showResult;
    }
    
    /**
     * 
     */
    public void startGame() {
        Chrono chrono = new Chrono();
        long temps = 0;
        chrono.start();
        findCombination(n, k);
        chrono.stop();
        temps = chrono.getMilliSec();
        sommeTemps += temps;
        if (showResult) {
            System.out.println("[ " + temps + " ms ]");
        }
        System.out.println("Temps d'execution : " + sommeTemps + "\n");
    }

    /**
     * 
     * @param n
     * @param k
     * @return
     */

    public void findCombination(int n, int k) {
        int coul = 1; // Chiffre bidon, on commence par le chiffre 1
        int[] combiTente = new int[k]; // Tableau contenant la combinaison tente
        int[] combiTrouve = new int[k]; // Tableau contenant les chiffres trouvees,
        int nbBienPlace = 0;
        int nbMalPlace = 0;
        int nbTrouve = 0; // Nombre de chiffres dont ont a trouve la position
        int pos = 0;
        try {
            // tant que le nb de bien place est inferieur aux nb de cases
            while (nbBienPlace < k) {
                // On cree la nouvelle combinaison a tente, qui determine la
                // presence ou non d'un chiffre. Tout en tenant compte des
                // positions des chiffres deja trouvees
                for (int i = 0; i < k; i++) {
                    if (combiTrouve[i] == 0)
                        combiTente[i] = coul; //Remplir la case non avec le prochain chiffre
                    else
                        combiTente[i] = combiTrouve[i]; // Il faut tjrs garder le chiffre bien place dans sa position
                }
                String combiTenteFound = "";
                for (int i = 0; i < combiTente.length; i++) {
                    combiTenteFound += combiTente[i] + "";
                }
                ResponseVO resultVO =  Api.sendWithMsgBody("POST", "{\"token\" : \"tokenmm4\",  \"result\" : \"" + combiTenteFound + "\"}", "test");
                nbBienPlace = Integer.parseInt(resultVO.getgPlace());
                nbMalPlace = 0;
                // On determine le nombre des chiffres bien places
                if (showResult)
                    displayCombinationDetected(combiTente, nbBienPlace, nbMalPlace);
                int nbChiffres = nbBienPlace - nbTrouve;
                // Si le chiffre teste est present ( nbBienPlace-nbTrouve >= 1)
                if (nbChiffres >= 1 && coul <= (n + 1)) {
                    for (int x = 1; x <= nbChiffres; x++) {
                        // On met nbMalPlace a un nombre different de 0
                        nbMalPlace = 1;
                        // indice de la position teste pour trouver l'emplacement du chiffre. On ne test pas une position dont on
                        // connais deja la position. Donc on cree une boucle qui cherche une position possible.
                        pos = 0;
                        // tant que nbMalPlace != 0 faire
                        while (nbMalPlace > 0) {
                            while ((pos < k) && combiTrouve[pos] != 0) // chercher la position disponible #0
                                pos++;
                            // On cree la nouvelle combinaison a tente, qui cherche
                            // la position exacte du chiffre en cour. Tout en tenant compte des positions des chiffres deja trouvees
                            for (int i = 0; i < k; i++) {
                                if (combiTrouve[i] == 0) // Si la case courante,
                                // n'est pas une case dont on connais la chiffre
                                {
                                    if (i != pos) // Si la case n'est pas la case
                                        // teste, on met le chiffre  superieur
                                        combiTente[i] = coul + 1;
                                    else
                                        combiTente[i] = coul;

                                } else
                                    combiTente[i] = combiTrouve[i];
                            }

                            // Test de la nouvelle tentative
                            // Calcul du nombre de chiffre mal place 
                            combiTenteFound = "";
                            for (int i = 0; i < combiTente.length; i++) {
                                combiTenteFound += combiTente[i] + "";
                            }
                            resultVO = Api.sendWithMsgBody("POST", "{\"token\" : \"tokenmm4\",  \"result\" : \"" + combiTenteFound + "\"}", "test");
                            nbBienPlace = Integer.parseInt(resultVO.getgPlace());
                            nbMalPlace = 0;
                            if (showResult)
                                displayCombinationDetected(combiTente, nbBienPlace, nbMalPlace);
                            // on se prepare a tester la position suivante
                            pos++;
                        }

                        // A la sortie de la boucle, on a la position du chiffre -> pos - 1
                        // On ajoute donc ce chiffre a la combinaison contenant les chiffres Trouvees
                        combiTrouve[pos - 1] = coul;
                        // on incremente le nombre de chiffre trouvees
                        nbTrouve++;
                    }
                }
                coul++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayCombinationDetected(int tab[], int nbBienPlace, int nbMalPlace) {
        String combinationFound = "";
        for (int i = 0; i < tab.length; i++) {
            combinationFound += tab[i] + "";
        }
        System.out.println(combinationFound + " --> " + nbBienPlace + " Bien place.\n");
    }
}

/**
 * 
 * @author Team_4
 *
 */
class Chrono {
    long start;
    long stop;
    Chrono() {
    }
    public void start() {
        start = System.currentTimeMillis();
    }
    public void stop() {
        stop = System.currentTimeMillis();
    }
    public long getMilliSec() {
        return (stop - start);
    }

}
