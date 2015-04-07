package szachy.ruchy;

import szachy.Figura;

/**
 *
 * @author Michal
 */
public class RuchWiezy {
    public static int[][] ustalRuch(Figura figura, Figura[][] plansza) {
        int ruchy[][];
        ruchy = new int[8][8];
        for (int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                ruchy[j][i]=0;
            }
        }
        int x = figura.getX() / 50, y = figura.getY() / 50;
        for (int i = y + 1; i < 8; i++) {
            if (plansza[x][i].getIdGracza()==0) {
                ruchy[x][i] = 1;
            } else {
                if (figura.getIdGracza() != plansza[x][i].getIdGracza()) {
                    if (plansza[x][i].getNazwa().endsWith("king")) {
                        ruchy[x][i] = 5;
                    } else {
                        ruchy[x][i] = 4; 
                    }
                }
                break;
            }
        }
        for (int i = y - 1; i >=0 ; i--) {
            if (plansza[x][i].getIdGracza()==0) {
                ruchy[x][i] = 1;
            } else {
                if (figura.getIdGracza() != plansza[x][i].getIdGracza()) {
                    if (plansza[x][i].getNazwa().endsWith("king")) {
                        ruchy[x][i] = 5;
                    } else {
                        ruchy[x][i] = 4; 
                    }
                }
                break;
            }
        }
        for (int i = x - 1; i >= 0 ; i--) {
            if (plansza[i][y].getIdGracza()==0) {
                ruchy[i][y] = 1;
            } else {
                if (figura.getIdGracza() != plansza[i][y].getIdGracza()) {
                    if (plansza[i][y].getNazwa().endsWith("king")) {
                        ruchy[i][y] = 5;
                    } else {
                        ruchy[i][y] = 4; 
                    }
                }
                break;
            }
        }
        for (int i = x + 1; i < 8 ; i++) {
            if (plansza[i][y].getIdGracza()==0) {
                ruchy[i][y] = 1;
            } else {
                if (figura.getIdGracza() != plansza[i][y].getIdGracza()) {
                    if (plansza[i][y].getNazwa().endsWith("king")) {
                        ruchy[i][y] = 5;
                    } else {
                        ruchy[i][y] = 4; 
                    }
                }
                break;
            }
        }
        return ruchy;
    }
}
