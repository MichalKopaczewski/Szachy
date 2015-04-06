package szachy.ruchy;

import szachy.Figura;

/**
 *
 * @author Michal
 */
public class RuchKrola {
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
        if (x>0 && y>0 && x<7 && y<7) {
            ruchy[x-1][y] = ustawBicieNaPolu(figura, plansza, x-1, y);
            ruchy[x-1][y+1] = ustawBicieNaPolu(figura, plansza, x-1, y+1);
            ruchy[x][y+1] = ustawBicieNaPolu(figura, plansza, x, y+1);
            ruchy[x+1][y+1] = ustawBicieNaPolu(figura, plansza, x+1, y+1);
            ruchy[x+1][y] = ustawBicieNaPolu(figura, plansza, x+1, y);
            ruchy[x+1][y-1] = ustawBicieNaPolu(figura, plansza, x+1, y-1);
            ruchy[x][y-1] = ustawBicieNaPolu(figura, plansza, x, y-1);
            ruchy[x-1][y-1] = ustawBicieNaPolu(figura, plansza, x-1, y-1);
        } else if (x==0 && y>0 && y<7) {
            ruchy[x][y+1] = ustawBicieNaPolu(figura, plansza, x, y+1);
            ruchy[x+1][y+1] = ustawBicieNaPolu(figura, plansza, x+1, y+1);
            ruchy[x+1][y] = ustawBicieNaPolu(figura, plansza, x+1, y);
            ruchy[x+1][y-1] = ustawBicieNaPolu(figura, plansza, x+1, y-1);
            ruchy[x][y-1] = ustawBicieNaPolu(figura, plansza, x, y-1);
        } else if (x==7 && y>0 && y<7) {
            ruchy[x-1][y] = ustawBicieNaPolu(figura, plansza, x-1, y);
            ruchy[x-1][y+1] = ustawBicieNaPolu(figura, plansza, x-1, y+1);
            ruchy[x][y+1] = ustawBicieNaPolu(figura, plansza, x, y+1);
            ruchy[x][y-1] = ustawBicieNaPolu(figura, plansza, x, y-1);
            ruchy[x-1][y-1] = ustawBicieNaPolu(figura, plansza, x-1, y-1);
        } else if (y==7 && x>0 && x<7) {
            ruchy[x-1][y] = ustawBicieNaPolu(figura, plansza, x-1, y);
            ruchy[x+1][y] = ustawBicieNaPolu(figura, plansza, x+1, y);
            ruchy[x+1][y-1] = ustawBicieNaPolu(figura, plansza, x+1, y-1);
            ruchy[x][y-1] = ustawBicieNaPolu(figura, plansza, x, y-1);
            ruchy[x-1][y-1] = ustawBicieNaPolu(figura, plansza, x-1, y-1);
        } else if (y==0 && x>0 && x<7) {
            ruchy[x-1][y] = ustawBicieNaPolu(figura, plansza, x-1, y);
            ruchy[x-1][y+1] = ustawBicieNaPolu(figura, plansza, x-1, y+1);
            ruchy[x][y+1] = ustawBicieNaPolu(figura, plansza, x, y+1);
            ruchy[x+1][y+1] = ustawBicieNaPolu(figura, plansza, x+1, y+1);
            ruchy[x+1][y] = ustawBicieNaPolu(figura, plansza, x+1, y);
        } else if (x==0 && y==0) {
            ruchy[x][y+1] = ustawBicieNaPolu(figura, plansza, x, y+1);
            ruchy[x+1][y+1] = ustawBicieNaPolu(figura, plansza, x+1, y+1);
            ruchy[x+1][y] = ustawBicieNaPolu(figura, plansza, x+1, y);
        } else if (x==7 && y==0) {
            ruchy[x-1][y] = ustawBicieNaPolu(figura, plansza, x-1, y);
            ruchy[x-1][y+1] = ustawBicieNaPolu(figura, plansza, x-1, y+1);
            ruchy[x][y+1] = ustawBicieNaPolu(figura, plansza, x, y+1);
        } else if (x==7 && y==7) {
            ruchy[x-1][y] = ustawBicieNaPolu(figura, plansza, x-1, y);
            ruchy[x][y-1] = ustawBicieNaPolu(figura, plansza, x, y-1);
            ruchy[x-1][y-1] = ustawBicieNaPolu(figura, plansza, x-1, y-1);
        } else if (x==0 && y==7) {
            ruchy[x+1][y] = ustawBicieNaPolu(figura, plansza, x+1, y);
            ruchy[x+1][y-1] = ustawBicieNaPolu(figura, plansza, x+1, y-1);
            ruchy[x][y-1] = ustawBicieNaPolu(figura, plansza, x, y-1);
        }
        return ruchy;
    }
    public static int ustawBicieNaPolu(Figura figura, Figura[][] plansza, int xB, int yB) {
        int x = figura.getX() / 70, y = figura.getY() / 70;
        if (plansza[xB][yB].getIdGracza()==0) {
            return 1;
        } else if (plansza[xB][yB].getIdGracza()!=figura.getIdGracza()) {
            return 4;
        } else {
            return 0;
        }
    }
}
