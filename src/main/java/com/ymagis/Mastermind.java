package com.ymagis;

import com.ymagis.api.Api;
import com.ymagis.api.ResponseVO;

/**
 *
 * @author Team_4
 */
public class Mastermind {

    private int n; // Nombre maximale
    private int k; // Nombre de chiffre representant la combinason

    public static void main(String[] args) {
        try {
            ResponseVO responseVO = Api.sendWithMsgBody("POST","{\"token\" : \"tokenmm4\"}","start");
            int nbrCase = null != responseVO ? Integer.parseInt(responseVO.getSize()) : 5;
            Mastermind mastermind = new Mastermind(9, nbrCase);
            mastermind.startGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Mastermind(int n, int k) {
        this.n = n;
        this.k = k;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setK(int k) {
        this.k = k;
    }

    /**
     * 
     * @throws Exception
     */
    public void startGame() throws Exception {
        MastermindService service = new MastermindService(n, k, true);
        service.startGame();
    }
}
