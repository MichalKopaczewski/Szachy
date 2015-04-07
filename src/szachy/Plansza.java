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
    private boolean czyKolejBialego=true;
    private boolean czySzach=false;
    private int xSzacha,ySzacha;
    
    public Plansza(int x, int y, int szerokosc, int wysokosc) {
        setBounds(x, y, szerokosc, wysokosc);
        setLayout(null);
        figury = new Figura[8][8];
        obrazek = createImageIcon("/ikony/chessboard.png");
        tlo = new JLabel(obrazek);
        tlo.setBounds(0, 0, szerokosc, wysokosc);
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
        wKing = new Figura(new Point(4, 0), 1, "wking", this);
        figury[4][0] = wKing;
        wQueen = new Figura(new Point(3, 0), 1, "wqueen", this);
        figury[3][0] = wQueen;
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
        add(tlo);
        usunListenerCzarnych(this);
    }
    @Override
    public void mousePressed(MouseEvent me) {
        Figura source = (Figura) me.getComponent();
        System.out.println(source);
        if ( (aktualnaFigura!= null) && source.getIdGracza()!=aktualnaFigura.getIdGracza()) {
            int posX = source.getX() / 50, posY = source.getY() / 50;
            int aktX = aktualnaFigura.getX() / 50, aktY = aktualnaFigura.getY() / 50;
            remove(figury[aktX][aktY]);
            remove(figury[posX][posY]);
            wygasPola(aktualnaFigura.getRuchy());
            wylaczPustePola(aktualnaFigura.getRuchy());
            wygasBicia(aktualnaFigura.getRuchy());
            figury[aktX][aktY] = new Figura(new Point(aktX, aktY), 0, null);
            add(figury[aktX][aktY], 2);
            aktualnaFigura.setLocation(posX * 50, posY * 50);
            aktualnaFigura.setPierwszyRuch(false);
            aktualnaFigura.setStan(false);
            aktualnaFigura.removeMouseListener(this);
            figury[posX][posY] = new Figura(aktualnaFigura, null);
            figury[posX][posY].setEnabled(false);
            add(figury[posX][posY], 2);                   
            aktualnaFigura = null;
            zmianaKolejki();
            validate();
        } else if (source.getIdGracza()==1 || source.getIdGracza()==2){
            if (source.isStan()==false) {
                
                wylaczPozostalePionki(source);
                source.setStan(true);
                source.ustalRuch(this.figury);
                podswietlPustePola(source.getRuchy());
                podswietlBicia(source.getRuchy());
                wlaczPustePola(source.getRuchy());
                aktualnaFigura = source;
                validate();

            } else {
                wlaczPozostalePionki(source);
                source.setStan(false);
                wygasPola(source.getRuchy());
                wylaczPustePola(source.getRuchy());
                wygasBicia(source.getRuchy());
                aktualnaFigura = null;
                validate();
            }
        }
        repaint();
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
    public boolean sprawdzSzacha(int[][] ruchy) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (ruchy[i][j]==5) {
                    czySzach=true;
                    return true;
                }
            }
        }
        return false;
    }
    public void zmianaKolejki() {
        if (this.czyKolejBialego==true) {
            this.czyKolejBialego = false;
            ustawListenerCzarnych(this);
        } else {
            this.czyKolejBialego=true;
            ustawListenerBialych(this);
        }
    }
    public void usunListenerBialych(MouseListener ml) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (figury[i][j].getIdGracza()==1) {
                    figury[i][j].removeMouseListener(ml);
                    figury[i][j].removeMouseListener(ml);
                }
            }
        }
    }
    public void ustawListenerBialych(MouseListener ml) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (figury[i][j].getIdGracza()==1) {
                    figury[i][j].addMouseListener(ml);
                    figury[i][j].setEnabled(true);
                }
            }
        }
    }
    public void usunListenerCzarnych(MouseListener ml) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (figury[i][j].getIdGracza()==2) {
                    figury[i][j].removeMouseListener(ml);
                }
            }
        }
    }
    public void ustawListenerCzarnych(MouseListener ml) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (figury[i][j].getIdGracza()==2) {
                    figury[i][j].addMouseListener(ml);
                    figury[i][j].setEnabled(true);
                }
            }
        }
    }
    public void wygasPola(int pola[][]) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pola[i][j]==1) {
                    figury[i][j].setOpaque(false);
                    
                }
            }
        }
    }
    public void podswietlBicia(int pola[][]) {                  //sprawdzic
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pola[i][j]==4) {
                    figury[i][j].setEnabled(true);
                    figury[i][j].addMouseListener(this);
                } else if (pola[i][j]==5) {
                    figury[i][j].setEnabled(true);
                    figury[i][j].addMouseListener(this);
                }
            }
        }
    }
    public void wygasBicia(int pola[][]) {                  //sprawdzic
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pola[i][j]==4) {
                    figury[i][j].setEnabled(false);
                    figury[i][j].removeMouseListener(this);
                } else if (pola[i][j]==5) {
                    figury[i][j].setEnabled(false);
                    figury[i][j].removeMouseListener(this);
                }
            }
        }
    }
    public void podswietlPustePola(int pola[][]) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pola[i][j]==1) {
                    figury[i][j].setOpaque(true);
                    figury[i][j].setBackground(Color.yellow);
                }
            }
        }
    }
    public void wlaczPozostalePionki(Figura figura) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i!=figura.getX()/50 || j!=figura.getY()/50) && figura.getIdGracza()==figury[i][j].getIdGracza()) {
                    figury[i][j].setEnabled(true);
                    figury[i][j].addMouseListener(this);
                }
            }
        }
    }
    public void wylaczPozostalePionki(Figura figura) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i!=figura.getX()/50 || j!=figura.getY()/50) {
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
    public void wlaczPustePola(int[][] ruchy) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (ruchy[i][j] == 1) {
                    figury[i][j].addMouseListener(this);
                }
            }
        }
    }
    public void wylaczPustePola(int[][] ruchy) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (ruchy[i][j] == 1) {
                    figury[i][j].removeMouseListener(this);
                }
            }
        }
    }
    public void wypiszMiejscaNaPlanszy() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(figury[i][j].getBackground().getRGB() + " ");
            }
            System.out.println("");
        }
    }
    private ImageIcon createImageIcon(String path) {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            System.err.println("Plik jest w" + path);
            return new ImageIcon(imgURL);
        }
        else {
            System.err.println("Pliku nie ma w /src" + path);
            return null; 
        }
    }


}
 