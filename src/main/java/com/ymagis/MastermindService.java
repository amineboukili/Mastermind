package com.ymagis;

import java.io.IOException;
import java.util.Date;
import com.ymagis.api.Api;
import com.ymagis.api.ResponseVO;
import com.ymagis.api.tools.Constantes;

public class MastermindService {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
		  long debut = new Date().getTime();
            startGame();
          long fin = new Date().getTime();
          System.out.println("Méthode exécutée en " + Long.toString(fin - debut) + " millisecondes");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public static Integer getSizeFormStart() throws IOException {
		// appel start
		Integer size = 8;
		ResponseVO response = Api.sendWithMsgBody(Constantes.POST_METHOD, "{\"token\" : \"tokenmm4\"}", Constantes.START_END_POINT);
		if (null != response) {
			size = Integer.parseInt(response.getSize());
		}
		return size;
	}

	/**
	 * 
	 * @throws IOException
	 */
	public static void startGame() throws IOException {
		Integer ok = 0;
		//Appel API start
		Integer size = getSizeFormStart();
		String[] tabFin = getSizeOffTabStart(size);
	    String[] tabIndCorr = getSizeOffTabStart(size);
		String[] tempTable = new String[size];
		int stop=0;
		for (int i = 0; i <= Constantes.MAX_NUMBER; i++) {
			tempTable = chargerTab(tempTable,size, String.valueOf(i));
			// appel API
			String combinaison = getStringFromTab(tempTable);
			ResponseVO response = Api.sendWithMsgBody(Constantes.POST_METHOD, "{\"token\" : \"tokenmm4\",  \"result\" : \"" + combinaison + "\"}", Constantes.TEST_END_POINT);
			if (null != response) {
				Integer goodPlace = Integer.parseInt(response.getgPlace());
				Integer wrongPlace = Integer.parseInt(response.getwPlace());
				if(goodPlace > 0 || wrongPlace > 0 ) {
					ok++;
					for (int k = 0; k < size; k++) {
						if(tabFin[k] == Constantes.CARAC_SPEC_ETOILE) {
							tempTable = chargerTabStar(tempTable,size, k, i, tabIndCorr);
							// appel API
							combinaison = getStringFromTab(tempTable);
							response = Api.sendWithMsgBody(Constantes.POST_METHOD,"{\"token\" : \"tokenmm4\",  \"result\" : \"" + combinaison + "\"}", Constantes.TEST_END_POINT);
							goodPlace = Integer.parseInt(response.getgPlace());
							if (goodPlace >= 1) {
								ok++;
								tabFin[k] = tempTable[k];
								tabIndCorr[k] = String.valueOf(k);
								stop++;
							}
						}
					}
				}
			}
			if(stop==size) {
				break;
			}
		}
		//System.out.println(getStringFromTab(tabFin));
		Api.sendWithMsgBody(Constantes.POST_METHOD,"{\"token\" : \"tokenmm4\",  \"result\" : \"" + getStringFromTab(tabFin) + "\"}", Constantes.TEST_END_POINT);
	}
	
	/**
	 * 
	 * @param size
	 * @return
	 */
	public static String[] getSizeOffTabStart(Integer size) {
	    String[] tab = new String[size];
	    for(int i=0; i<size; i++) {
	      tab[i] = Constantes.CARAC_SPEC_ETOILE;
	    }
	    return tab;
	  }

	/**
	 * 
	 * @param combiTente
	 * @return
	 */
	public static String getStringFromTab(String[] combiTente) {
		String combiTenteFound = "";
		for (int i = 0; i < combiTente.length; i++) {
			combiTenteFound += combiTente[i] + "";
		}
		return combiTenteFound;
	}

	/**
	 * 
	 * @param tab
	 * @param size
	 * @param i
	 * @return
	 */
	public static String[] chargerTab(String[] tab,int size, String i) {
		for (int j = 0; j < size; j++) {
			tab[j] = i;
		}
		return tab;
	}

	/**
	 * 
	 * @param tabStar
	 * @param size
	 * @param indice
	 * @param i
	 * @param tabIndCorr
	 * @return
	 */
	public static String[] chargerTabStar(String[] tabStar,int size, int indice, int i, String[] tabIndCorr) {
		for (int j = 0; j < size; j++) {
			if (j != indice) {
				tabStar[j] = tabIndCorr[j];
			} else {
				tabStar[j] = String.valueOf(i);
			}
		}
		return tabStar;
	}
}
