package szachy.ruchy;

import szachy.Figura;

/**
 *
 * @author Michal
 */
public class RuchGonca {

    public static int[][] ustalRuch(Figura figura, Figura[][] plansza) {
        int ruchy[][];
        ruchy = new int[8][8];
        for (int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                ruchy[j][i]=0;
            }
        }
        int x = figura.getX() / 70, y = figura.getY() / 70;
        System.out.println(String.valueOf(x) + " " + String.valueOf(y));
        int i,j;
        if (x>0 && y<7) {
            j = y + 1;
            for (i = x-1; i >= 0; i--) {
                if (plansza[i][j].getIdGracza()==0) {
                    ruchy[i][j]=1;
                } else if (plansza[i][j].getIdGracza()!=figura.getIdGracza()) {
                    ruchy[i][j]=4;
                    break;
                } else if (plansza[i][j].getIdGracza()==figura.getIdGracza()) {
                    break;
                }
                j++;
                if (j==8) {
                    break;
                }
            }
        }
        if (x<7 && y<7) {
            j = y + 1;
            for (i = x+1; i < 8; i++) {
                if (plansza[i][j].getIdGracza()==0) {
                    ruchy[i][j]=1;
                } else if (plansza[i][j].getIdGracza()!=figura.getIdGracza()) {
                    ruchy[i][j]=4;
                    break;
                } else if (plansza[i][j].getIdGracza()==figura.getIdGracza()) {
                    break;
                }
                j++;
                if (j==8) {
                    break;
                }
            }
        }
        if (x<7 && y>0) {
            j = y - 1;
            for (i = x+1; i < 8; i++) {
                if (plansza[i][j].getIdGracza()==0) {
                    ruchy[i][j]=1;
                } else if (plansza[i][j].getIdGracza()!=figura.getIdGracza()) {
                    ruchy[i][j]=4;
                    break;
                } else if (plansza[i][j].getIdGracza()==figura.getIdGracza()) {
                    break;
                }
                j--;
                if (j==-1) {
                    break;
                }
            }
        }
        if (x>0 && y>0) {
            j = y - 1;
            for (i = x-1; i >= 0; i--) {
                if (plansza[i][j].getIdGracza()==0) {
                    ruchy[i][j]=1;
                } else if (plansza[i][j].getIdGracza()!=figura.getIdGracza()) {
                    ruchy[i][j]=4;
                    break;
                } else if (plansza[i][j].getIdGracza()==figura.getIdGracza()) {
                    break;
                }
                j--;
                if (j==-1) {
                    break;
                }
            }
        }
        return ruchy;
    }
    
}
