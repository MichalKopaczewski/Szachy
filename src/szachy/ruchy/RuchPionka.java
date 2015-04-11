package szachy.ruchy;

import szachy.Figura;

public class RuchPionka {
    public static int[][] ustalRuch (Figura figura, Figura[][] plansza) {
        int ruchy[][];
        ruchy = new int[8][8];
        for (int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                ruchy[j][i]=0;
            }
        }
        int x = figura.getX() / 50, y = figura.getY() / 50;
        if (figura.isPierwszyRuch()) {
            if (figura.getIdGracza()==1) {
                for (int i = y + 1; i < y + 3; i++) {
                    if (plansza[x][i].getIdGracza()==0) {
                        ruchy[x][i] = 1;
                    } else {
                        break;
                    }
                }
                if ((x<7) && (y<7) && (plansza[x+1][y+1].getIdGracza() == 2) && (plansza[x+1][y+1].getNazwa().equals("bking"))) {
                    ruchy[x+1][y+1]=5;
                } else if ((x<7) && (y<7) && (plansza[x+1][y+1].getIdGracza() == 2)) {
                    ruchy[x+1][y+1] = 4;
                }
                if ((x>0) && (y<7)  && (plansza[x-1][y+1].getIdGracza() == 2) && (plansza[x-1][y+1].getNazwa().equals("bking"))) {
                    ruchy[x-1][y+1]=5;
                } else if ((x>0) && (y<7)  && (plansza[x-1][y+1].getIdGracza() == 2)) {
                    ruchy[x-1][y+1] = 4;
                }
            } else if (figura.getIdGracza()==2) {
                for (int i = y - 1; i > y - 3; i--) {
                    if (plansza[x][i].getIdGracza()==0) {
                        ruchy[x][i] = 1;
                    } else {
                        break;
                    }
                }
                if ((x>0) && (y>0) && (plansza[x-1][y-1].getIdGracza() == 1)  && (plansza[x-1][y-1].getNazwa().equals("wking"))) {
                    ruchy[x-1][y-1] = 5;
                } else if ((x>0) && (y>0) && (plansza[x-1][y-1].getIdGracza() == 1)) {
                    ruchy[x-1][y-1] = 4;
                }
                if ((x<7) && (y>0) && (plansza[x+1][y-1].getIdGracza() == 1)  && (plansza[x+1][y-1].getNazwa().equals("wking"))) {
                    ruchy[x+1][y-1] = 5;
                } else if ((x<7) && (y>0) && (plansza[x+1][y-1].getIdGracza() == 1)) {
                    ruchy[x+1][y-1] = 4;
                }
            }
        } else {
            if (figura.getIdGracza()==1) {
                if ( y<7 && plansza[x][y+1].getIdGracza()==0) {
                    ruchy[x][y+1] = 1;
                }
                if ((x<7) && (y<7) && (plansza[x+1][y+1].getIdGracza() == 2) && plansza[x+1][y+1].getNazwa().equals("bking")) {
                    ruchy[x+1][y+1] = 5;
                } else if ((x<7) && (y<7) && (plansza[x+1][y+1].getIdGracza() == 2)) {
                    ruchy[x+1][y+1] = 4;
                }
                if ((x>0) && (y<7)  && (plansza[x-1][y+1].getIdGracza() == 2) && (plansza[x-1][y+1].getNazwa().equals("bking"))) {
                    ruchy[x-1][y+1] = 5;
                } else if ((x>0) && (y<7)  && (plansza[x-1][y+1].getIdGracza() == 2)) {
                    ruchy[x-1][y+1] = 4;
                }
            } else if (figura.getIdGracza()==2) {
                if (y>0 && plansza[x][y-1].getIdGracza()==0) {
                    ruchy[x][y-1] = 1;
                }
                if ((x>0) && (y>0) && (plansza[x-1][y-1].getIdGracza() == 1) && (plansza[x-1][y-1].getNazwa().equals("wking"))) {
                    ruchy[x-1][y-1] = 5;
                } else if ((x>0) && (y>0) && (plansza[x-1][y-1].getIdGracza() == 1)) {
                    ruchy[x-1][y-1] = 4;
                }
                if ((x<7) && (y>0) && (plansza[x+1][y-1].getIdGracza() == 1) && (plansza[x+1][y-1].getNazwa().equals("wking"))) {
                    ruchy[x+1][y-1] = 5;
                } else if ((x<7) && (y>0) && (plansza[x+1][y-1].getIdGracza() == 1)) {
                    ruchy[x+1][y-1] = 4;
                }
            }
        }
        return ruchy;
    }
    
}
