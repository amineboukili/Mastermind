package com.ymagis;

import java.io.IOException;

import com.ymagis.api.Api;
import com.ymagis.api.ResponseVO;

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
		ResponseVO resultVO = Api.sendWithMsgBody("POST", "{\"token\" : \"tokenmm4\"}", "start");
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
		Integer size = getSizeFormStart(); //8
		String[] tabFin = getSizeOffTabStart(size);
	    String[] tabIndCorr = getSizeOffTabStart(size);
		String[] t = new String[size];
		int stop=0;
		for (int i = 0; i <= 9; i++) {
			t = chargerTab(size, String.valueOf(i));
			// appel API
			String tabString = getStringFromTab(t);
			ResponseVO resultVO = Api.sendWithMsgBody("POST",
					"{\"token\" : \"tokenmm4\",  \"result\" : \"" + tabString + "\"}", "test");
			//System.out.println(getStringFromTab(tabFin) + " -->  " + mapResult.toString());
			if (null != resultVO) {
				Integer gPlace = Integer.parseInt(resultVO.getgPlace());
				Integer wPlace = Integer.parseInt(resultVO.getwPlace());
				if(gPlace > 0 || wPlace > 0 ) {
					ok++;
					for (int k = 0; k < size; k++) {
						t = chargerTabStar(size, k, i, tabIndCorr);
						// appel API
						tabString = getStringFromTab(t);
						resultVO = Api.sendWithMsgBody("POST",
								"{\"token\" : \"tokenmm4\",  \"result\" : \"" + tabString + "\"}", "test");
						//System.out.println(getStringFromTab(tabFin) + " -->  " + mapResult.toString());
						gPlace = Integer.parseInt(resultVO.getgPlace());
						if (gPlace >= 1) {
							ok++;
							tabFin[k] = t[k];
							tabIndCorr[k] = String.valueOf(k);
							stop++;
						}
					}
				}
			}
			if(stop==size) {
				break;
			}
		}
		System.out.println(getStringFromTab(tabFin));
		//ResponseVO resultVO = Api.sendWithMsgBody("POST", "{\"token\" : \"tokenmm4\",  \"result\" : \"" + getStringFromTab(tabFin) + "\"}", "test");
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
