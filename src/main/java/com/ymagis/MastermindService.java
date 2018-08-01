package com.ymagis;

import java.io.IOException;

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
			getNum();
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
		ResponseVO resultVO = Api.sendWithMsgBody(Constantes.POST_METHOD, "{\"token\" : \"tokenmm4\"}", Constantes.START_END_POINT);
		if (null != resultVO) {
			size = Integer.parseInt(resultVO.getSize());
		}
		return size;
	}

	/**
	 * 
	 * @throws IOException
	 */
	public static void getNum() throws IOException {
		Integer ok = 0;
		//Appel API start
		Integer size = getSizeFormStart();
		String[] tabFin = getSizeOffTabStart(size);
	    String[] tabIndCorr = getSizeOffTabStart(size);
		String[] t = new String[size];
		int stop=0;
		for (int i = 0; i <= Constantes.MAX_NUMBER; i++) {
			t = chargerTab(size, String.valueOf(i));
			// appel API
			String combinaison = getStringFromTab(t);
			ResponseVO response = Api.sendWithMsgBody(Constantes.POST_METHOD, "{\"token\" : \"tokenmm4\",  \"result\" : \"" + combinaison + "\"}", Constantes.TEST_END_POINT);
			if (null != response) {
				Integer gPlace = Integer.parseInt(response.getgPlace());
				Integer wPlace = Integer.parseInt(response.getwPlace());
				if(gPlace > 0 || wPlace > 0 ) {
					ok++;
					for (int k = 0; k < size; k++) {
						if(tabFin[k] == Constantes.CARAC_SPEC_ETOILE) {
							t = chargerTabStar(size, k, i, tabIndCorr);
							// appel API
							combinaison = getStringFromTab(t);
							response = Api.sendWithMsgBody(Constantes.POST_METHOD,"{\"token\" : \"tokenmm4\",  \"result\" : \"" + combinaison + "\"}", Constantes.TEST_END_POINT);
							gPlace = Integer.parseInt(response.getgPlace());
							if (gPlace >= 1) {
								ok++;
								tabFin[k] = t[k];
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
		Api.sendWithMsgBody(Constantes.POST_METHOD, "{\"token\" : \"tokenmm4\",  \"result\" : \"" + getStringFromTab(tabFin) + "\"}", "test");
	}
	
	/**
	 * 
	 * @param size
	 * @return
	 */
	public static String[] getSizeOffTabStart(Integer size) {
	    String[] tab = new String[size];
	    for(int i=0; i<size; i++) {
	      tab[i] = "*";
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
	 * @param size
	 * @param i
	 * @return
	 */
	public static String[] chargerTab(int size, String i) {
		String[] tab = new String[size];
		for (int j = 0; j < size; j++) {
			tab[j] = i;
		}
		return tab;
	}

	/**
	 * 
	 * @param size
	 * @param indice
	 * @param i
	 * @param tabIndCorr
	 * @return
	 */
	public static String[] chargerTabStar(int size, int indice, int i, String[] tabIndCorr) {
		String[] tabStar = new String[size];
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
