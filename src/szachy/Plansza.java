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
    
    public Plansza(int x, int y, int szerokosc, int wysokosc) {
        setBounds(x, y, szerokosc, wysokosc);
        setLayout(null);
        figury = new Figura[8][8];
        obrazek = createImageIcon("/ikony/chessboard.png");
        tlo = new JLabel(obrazek);
        tlo.setBounds(0, 0, 560, 560);
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
            }
        }
        for (int i = 0; i < 8; i++) {
            wPionki[i] = new Figura(new Point(i, 1), 1, "wpawn", this);
            figury[i][1] = wPionki[i];
            add(figury[i][1]);
        }
        add(figury[0][0]);
        add(figury[7][0]);
        add(figury[1][0]);
        add(figury[6][0]);
        add(figury[2][0]);
        add(figury[5][0]);
        add(figury[3][0]);
        add(figury[4][0]);
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
        }
        add(figury[0][7]);
        add(figury[7][7]);
        add(figury[1][7]);
        add(figury[6][7]);
        add(figury[2][7]);
        add(figury[5][7]);
        add(figury[3][7]);
        add(figury[4][7]);
        //wypiszMiejscaNaPlanszy();
        
        //add(tlo);
        add(tlo);
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
//            System.out.println(figury[posX][posY]);
//            System.out.println(aktualnaFigura);
            remove(aktualnaFigura);
            remove(figury[posX][posY]);
            wygasPola();
            wylaczPustePola();
            validate();
            repaint();
            validate();
            figury[aktX][aktY] = new Figura(new Point(aktX, aktY), 0, this);
            aktualnaFigura.setLocation(posX * 70, posY * 70);
            aktualnaFigura.setPierwszyRuch(false);
            figury[posX][posY] = new Figura(aktualnaFigura);
            figury[posX][posY].addMouseListener(this);
            add(figury[posX][posY], 2);                                 // przetestowac
//            for (int i = 0; i < 8; i++) {
//                for (int j = 0; j < 8; j++) {
//                    System.out.print(figury[i][j].getIdGracza()+ " ");
//                }
//                System.out.println("");
//            }
//            System.out.println("cos1");
        } else if (source.getIdGracza()==1 || source.getIdGracza()==2){
            if (source.isStan()==false) {
                wylaczPozostalePionki(source);
                source.setStan(true);
                source.ustalRuch(this.figury);
                podswietlPustePola(source.getRuchy());
                podswietlBicia(source.getRuchy());
                wlaczPustePola(source.getRuchy());
                aktualnaFigura = source;

            //System.out.println("cos2");
            } else {
            //System.out.println("cos3");
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
                if (i!=figura.getX()/70 || j!=figura.getY()/70) {
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
                System.out.print(figury[i][j]);
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
 