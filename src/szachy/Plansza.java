package szachy;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

/**
 *
 * @author Michal
 */
/*  plansza:
        kolumna,wiersz
    tablica:
        wiersz, kolumna
*/

public class Plansza extends JPanel implements MouseListener {
    private Icon obrazek;
    private JLabel tlo;
    private Figura wTower1, wBishop1, wHorse1, wKing, wQueen, wTower2, wBishop2, wHorse2;
    private Figura[] wPionki;
    private Figura bTower1, bBishop1, bHorse1, bKing, bQueen, bTower2, bBishop2, bHorse2;
    private Figura[] bPionki;
    private Figura[][] figury;
    private Figura aktualnaFigura;
    private int miejscaNaPlanszy[][];
    
    public Plansza(int x, int y, int szerokosc, int wysokosc) {
        setBounds(x, y, szerokosc, wysokosc);
        setLayout(null);
        figury = new Figura[8][8];
        miejscaNaPlanszy = new int[8][8];
        obrazek = createImageIcon("/ikony/chessboard.png");
        wTower1 = new Figura(new Point(0, 0), 1, "wtower", this);
        figury[0][0] = wTower1;
        wTower2 = new Figura(new Point(7, 0), 1, "wtower", this);
        figury[7][0] = wTower2;
        wHorse1 = new Figura(new Point(1, 0), 1, "whorse", this);
        figury[1][0] = wHorse1;
        wHorse2 = new Figura(new Point(6, 0), 1, "whorse", this);
        figury[6][0] = wHorse2;
        wBishop1 = new Figura(new Point(2, 0), 1, "wbishop", this);
        figury[2][0] = wBishop1;
        wBishop2 = new Figura(new Point(5, 0), 1, "wbishop", this);
        figury[5][0] = wBishop2;
        wKing = new Figura(new Point(3, 0), 1, "wking", this);
        figury[3][0] = wKing;
        wQueen = new Figura(new Point(4, 0), 1, "wqueen", this);
        figury[4][0] = wQueen;
        wPionki = new Figura[8];
        for (int j = 2; j < 6; j++) {
            for (int k = 0; k < 8; k++) {
                figury[k][j] = new Figura(new Point(k, j), 0, null);
                add(figury[k][j]);
                miejscaNaPlanszy[k][j] = 0;
            }
        }
        for (int i = 0; i < 8; i++) {
            wPionki[i] = new Figura(new Point(i, 1), 1, "wpawn", this);
            figury[i][1] = wPionki[i];
            add(figury[i][1]);
            miejscaNaPlanszy[i][1] = 1;
        }
        add(figury[0][0]);
        miejscaNaPlanszy[0][0] = 1;
        add(figury[7][0]);
        miejscaNaPlanszy[7][0] = 1;
        add(figury[1][0]);
        miejscaNaPlanszy[1][0] = 1;
        add(figury[6][0]);
        miejscaNaPlanszy[6][0] = 1;
        add(figury[2][0]);
        miejscaNaPlanszy[2][0] = 1;
        add(figury[5][0]);
        miejscaNaPlanszy[5][0] = 1;
        add(figury[3][0]);
        miejscaNaPlanszy[3][0] = 1;
        add(figury[4][0]);
        miejscaNaPlanszy[4][0] = 1;
        bTower1 = new Figura(new Point(0, 7), 2, "btower", this);               // 0 7
        figury[0][7] = bTower1;
        bTower2 = new Figura(new Point(7, 7), 2, "btower", this);
        figury[7][7] = bTower2;
        bHorse1 = new Figura(new Point(1, 7), 2, "bhorse", this);
        figury[1][7] = bHorse1;
        bHorse2 = new Figura(new Point(6, 7), 2, "bhorse", this);
        figury[6][7] = bHorse2;
        bBishop1 = new Figura(new Point(2, 7), 2, "bbishop", this);
        figury[2][7] = bBishop1;
        bBishop2 = new Figura(new Point(5, 7), 2, "bbishop", this);
        figury[5][7] = bBishop2;
        bKing = new Figura(new Point(3, 7), 2, "bking", this);
        figury[3][7] = bKing;
        bQueen = new Figura(new Point(4, 7), 2, "bqueen", this);
        figury[4][7] = bQueen;
        bPionki = new Figura[8];
        for (int i = 0; i < 8; i++) {
            bPionki[i] = new Figura(new Point(i, 6), 2, "bpawn", this);
            figury[i][6] = bPionki[i];
            add(figury[i][6]);
            miejscaNaPlanszy[i][6] = 2;
        }
        add(figury[0][7]);
        miejscaNaPlanszy[0][7] = 2;
        add(figury[7][7]);
        miejscaNaPlanszy[7][7] = 2;
        add(figury[1][7]);
        miejscaNaPlanszy[1][7] = 2;
        add(figury[6][7]);
        miejscaNaPlanszy[6][7] = 2;
        add(figury[2][7]);
        miejscaNaPlanszy[2][7] = 2;
        add(figury[5][7]);
        miejscaNaPlanszy[5][7] = 2;
        add(figury[3][7]);
        miejscaNaPlanszy[3][7] = 2;
        add(figury[4][7]);
        miejscaNaPlanszy[4][7] = 2;
        
        
        tlo = new JLabel(obrazek);
        tlo.setBounds(0, 0, 560, 560);
        add(tlo);
        //wypiszMiejscaNaPlanszy();
        
    }
    @Override
    public void mousePressed(MouseEvent me) {
        Figura source = (Figura) me.getComponent();
//        System.out.println(source);
//        System.out.println("");
//        wypiszMiejscaNaPlanszy();
//        System.out.println("");
        if (source.getIdGracza()==0) {
            int posX = source.getX() / 70, posY = source.getY() / 70;
            int aktX = aktualnaFigura.getX() / 70, aktY = aktualnaFigura.getY() / 70;
            //wygasPola();
            //wylaczPustePola();
            wygasPola();
            wylaczPustePola();
//            remove(figury[aktX][aktY]);
//            remove(figury[posX][posY]);
            aktualnaFigura.setLocation(posX * 70, posY * 70);
            aktualnaFigura.setPierwszyRuch(false);
            figury[aktX][aktY] = new Figura(new Point(aktX, aktY), 0, this);
            figury[posX][posY] = new Figura(aktualnaFigura);
            miejscaNaPlanszy[posX][posY] = figury[posX][posY].getIdGracza();
            miejscaNaPlanszy[aktX][aktY] = figury[aktX][aktY].getIdGracza();
            add(figury[posX][posY]);
            add(figury[aktX][aktY]);
            //figury[posX][posY].addMouseListener(this);
            //wlaczPozostalePionki(figury[posX][posY]);
            //wlaczWszystkiePionki();
//            System.out.println(posX);
//            System.out.println(posY);
            //wypiszMiejscaNaPlanszy();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    System.out.print(figury[i][j].isValid() + " ");
                }
                System.out.println("");
            }
        } else if (source.getIdGracza()==1 || source.getIdGracza()==2){
            if (source.isStan()==false) {
                wylaczPozostalePionki(source);
                source.setStan(true);
                source.ustalRuch(this.miejscaNaPlanszy);
                podswietlPustePola(source.getRuchy());
                podswietlBicia(source.getRuchy());
                //source.wypiszRuch();
                wlaczPustePola(source.getRuchy());
                aktualnaFigura = source;

            } else {
                wlaczPozostalePionki(source);
                source.setStan(false);
                wygasPola();
                wylaczPustePola();
                aktualnaFigura = null;
            }
        }
//        System.out.println("");
//        wypiszMiejscaNaPlanszy();
//        System.out.println("");
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    public void wygasPola() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                figury[i][j].setOpaque(false);
            }
        }
    }
    public void podswietlBicia(int pola[][]) {                  //sprawdzic
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pola[i][j]==4) {
                    figury[i][j].setEnabled(true);
                }
            }
        }
    }
    public void dodajFigureDoPlanszy(Figura figura) {
        add(figura);
    }
    public void podswietlPustePola(int pola[][]) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pola[i][j]==1) {
                    //System.out.println("ale");
                    if (figury[i][j].isValid()==false) {
                        add(figury[i][j]);
                    }
                   figury[i][j].setOpaque(true);
                   figury[i][j].setBackground(Color.yellow);
                    //System.out.println(figury[i][j]);
                }
            }
        }
    }
    public void wylaczPozostalePionki(Figura figura) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (figury[i][j]!=figura) {
                    figury[i][j].setEnabled(false);
                    figury[i][j].removeMouseListener(this);
                }
            }
        }
    }
    public void wlaczWszystkiePionki() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (figury[i][j].getMouseListeners().length==0) {
                    figury[i][j].addMouseListener(this);
                }
                figury[i][j].setEnabled(true);
            }
        }
    }
    public void wlaczPozostalePionki(Figura figura) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((figury[i][j]!=figura && figury[i][j].getIdGracza()!=0)) {
                    figury[i][j].addMouseListener(this);
                }
                figury[i][j].setEnabled(true);
            }
        }
    }
    public void wlaczPustePola(int[][] ruchy) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (ruchy[i][j] == 1 && figury[i][j].getIdGracza()==0) {
                    figury[i][j].addMouseListener(this);
                }
            }
        }
    }
    public void wylaczPustePola() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (figury[i][j].getIdGracza()==0) {
                    figury[i][j].removeMouseListener(this);
                }
            }
        }
    }
    public void wypiszMiejscaNaPlanszy() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(miejscaNaPlanszy[i][j]);
            }
            System.out.println("");
        }
    }
    private ImageIcon createImageIcon(String path) {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            //System.err.println("Plik jest w" + path);
            return new ImageIcon(imgURL);
        }
        else {
            //System.err.println("Pliku nie ma w /src" + path);
            return null; 
        }
    }


}
 