package szachy.ruchy;

import szachy.Figura;

/**
 *
 * @author Michal
 */
public class RuchKrolowej {
    public static int[][] ustalRuch(Figura figura, Figura[][] plansza) {
        int ruchy[][];
        ruchy = new int[8][8];
        for (int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                ruchy[j][i]=0;
            }
        }
        int ruchyW[][] = RuchWiezy.ustalRuch(figura, plansza);
        int ruchyG[][] = RuchGonca.ustalRuch(figura, plansza);
        for (int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                if (ruchyW[i][j]!=0) {
                    ruchy[i][j]=ruchyW[i][j];
                }
                if (ruchyG[i][j]!=0) {
                    ruchy[i][j]=ruchyG[i][j];
                }
            }
        }
        
        return ruchy;
    }
}
