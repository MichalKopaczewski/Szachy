package szachy.ruchy;

import szachy.Figura;

/**
 *
 * @author Michal
 */
public class RuchKonia {
    public static int[][] ustalRuch(Figura figura, Figura[][] plansza) {
        int ruchy[][];
        ruchy = new int[8][8];
        for (int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                ruchy[j][i]=0;
            }
        }
        int x = figura.getX() / 70, y = figura.getY() / 70;
        if (x>1 && y<7) {                                                                      //1
            if (plansza[x-2][y+1].getIdGracza()==0) {
                ruchy[x-2][y+1]=1;
            } else if (plansza[x-2][y+1].getIdGracza()!=figura.getIdGracza()) {
                ruchy[x-2][y+1]=4;
            }
        }
        if (x>0 && y<6) {                                                                     //2
            if (plansza[x-1][y+2].getIdGracza()==0) {
                ruchy[x-1][y+2]=1;
            } else if (plansza[x-1][y+2].getIdGracza()!=figura.getIdGracza()) {
                ruchy[x-1][y+2]=4;
            }
        }
        if (x<7 && y<6) {                                                           //3
            if (plansza[x+1][y+2].getIdGracza()==0) {
                ruchy[x+1][y+2]=1;
            } else if (plansza[x+1][y+2].getIdGracza()!=figura.getIdGracza()) {
                ruchy[x+1][y+2]=4;
            }
        }
        if (x<6 && y<7) {                                                                               //4
            if (plansza[x+2][y+1].getIdGracza()==0) {
                ruchy[x+2][y+1]=1;
            } else if (plansza[x+2][y+1].getIdGracza()!=figura.getIdGracza()) {
                ruchy[x+2][y+1]=4;
            }
        }
        if (x<6 && y>0) {                                                                          //5
            if (plansza[x+2][y-1].getIdGracza()==0) {
                ruchy[x+2][y-1]=1;
            } else if (plansza[x+2][y-1].getIdGracza()!=figura.getIdGracza()) {
                ruchy[x+2][y-1]=4;
            }
        }
        if (x<7 && y>1) {                                                                          //6
            if (plansza[x+1][y-2].getIdGracza()==0) {
                ruchy[x+1][y-2]=1;
            } else if (plansza[x+1][y-2].getIdGracza()!=figura.getIdGracza()) {
                ruchy[x+1][y-2]=4;
            }
        }
        if (x>0 && y>1) {                                                                         //7
            if (plansza[x-1][y-2].getIdGracza()==0) {
                ruchy[x-1][y-2]=1;
            } else if (plansza[x-1][y-2].getIdGracza()!=figura.getIdGracza()) {
                ruchy[x-1][y-2]=4;
            }
        }
        if (x>1 && y>0) {                                                                         //8
            if (plansza[x-2][y-1].getIdGracza()==0) {
                ruchy[x-2][y-1]=1;
            } else if (plansza[x-2][y-1].getIdGracza()!=figura.getIdGracza()) {
                ruchy[x-2][y-1]=4;
            }
        }
        return ruchy;
    }
}
