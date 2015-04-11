package szachy;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

/**
 *
 * @author Michal
 */
public class Plansza extends JPanel implements MouseListener {
    public static Icon obrazek;
    private JLabel tlo;
    private Figura wTower1, wBishop1, wHorse1, wKing, wQueen, wTower2, wBishop2, wHorse2;
    private Figura[] wPionki;
    private Figura bTower1, bBishop1, bHorse1, bKing, bQueen, bTower2, bBishop2, bHorse2;
    private Figura[] bPionki;
    private Figura[][] figury;
    private Figura aktualnaFigura;
    private boolean czyKolejBialego=true;
    
    public Plansza(int x, int y, int szerokosc, int wysokosc) {
        setBounds(x, y, szerokosc, wysokosc);
        setLayout(null);
        figury = new Figura[8][8];
        tlo = new JLabel(obrazek);
        tlo.setBounds(0, 0, szerokosc, wysokosc);
        wTower1 = new Figura(new Point(0, 0), 1, "wtower", this);
        wTower2 = new Figura(new Point(7, 0), 1, "wtower", this);
        wHorse1 = new Figura(new Point(1, 0), 1, "whorse", this);
        wHorse2 = new Figura(new Point(6, 0), 1, "whorse", this);
        wBishop1 = new Figura(new Point(2, 0), 1, "wbishop", this);
        wBishop2 = new Figura(new Point(5, 0), 1, "wbishop", this);
        wKing = new Figura(new Point(3, 0), 1, "wking", this);
        wQueen = new Figura(new Point(4, 0), 1, "wqueen", this);
        figury[0][0] = wTower1;
        figury[7][0] = wTower2;
        figury[1][0] = wHorse1;
        figury[6][0] = wHorse2;
        figury[2][0] = wBishop1;
        figury[5][0] = wBishop2;
        figury[3][0] = wKing;
        figury[4][0] = wQueen;
        wPionki = new Figura[8];
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                figury[j][i] = new Figura(new Point(j, i), 0, null);
                add(figury[j][i]);
            }
        }
        for (int i = 0; i < 8; i++) {
//            wPionki[i] = new Figura(new Point(i, 1), 1, "wpawn", this);
            wPionki[i] = new Figura(new Point(i, 1), 0, null);
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
        bTower1 = new Figura(new Point(0, 7), 2, "btower", this);
        bTower2 = new Figura(new Point(7, 7), 2, "btower", this);
        bHorse1 = new Figura(new Point(1, 7), 2, "bhorse", this);
        bHorse2 = new Figura(new Point(6, 7), 2, "bhorse", this);
        bBishop1 = new Figura(new Point(2, 7), 2, "bbishop", this);
        bBishop2 = new Figura(new Point(5, 7), 2, "bbishop", this);
        bKing = new Figura(new Point(3, 7), 2, "bking", this);
        bQueen = new Figura(new Point(4, 7), 2, "bqueen", this);
        figury[0][7] = bTower1;
        figury[7][7] = bTower2;
        figury[1][7] = bHorse1;
        figury[6][7] = bHorse2;
        figury[2][7] = bBishop1;
        figury[5][7] = bBishop2;
        figury[3][7] = bKing;
        figury[4][7] = bQueen;
        bPionki = new Figura[8];
        for (int i = 0; i < 8; i++) {
//            bPionki[i] = new Figura(new Point(i, 6), 2, "bpawn", this);
            bPionki[i] = new Figura(new Point(i, 6), 0, null);
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
        repaint();
        usunListenerCzarnych(this);
    }

    @Override
    public void mousePressed(MouseEvent me) {
        Figura source = (Figura) me.getComponent();
        if ( (aktualnaFigura!= null) && source.getIdGracza()!=aktualnaFigura.getIdGracza()) {
            int posX = source.getX() / 50, posY = source.getY() / 50;
            int aktX = aktualnaFigura.getX() / 50, aktY = aktualnaFigura.getY() / 50;
            if (aktualnaFigura.getRuchy()[posX][posY]==5) {
                koniecGry(source);
            }
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
            if (aktualnaFigura.getRuchy()[posX][posY]==5) {
                koniecGry(source);
            } else {
                aktualnaFigura = null;
                zmianaKolejki();
                validate();
            }    
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
    
    public void zmianaKolejki() {
        if (this.czyKolejBialego==true) {
            sprawdzSzacha(true);
            this.czyKolejBialego = false;
            if (sprawdzCzyJestRuch(czyKolejBialego)==false) {
                koniecGry(new Figura(new Point(0, 0), 2, "btower", this));
            }
            ustawListenerCzarnych(this);
        } else {
            sprawdzSzacha(false);
            this.czyKolejBialego=true;
            if (sprawdzCzyJestRuch(czyKolejBialego)==false) {
                koniecGry(new Figura(new Point(0, 0), 1, "wtower", this));
            }
            ustawListenerBialych(this);
        }
    }
    private boolean sprawdzCzyJestRuch(boolean czyKolejBialego) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (czyKolejBialego==false) {
                    if (figury[i][j].getIdGracza()==2) {
                        figury[i][j].ustalRuch(figury);
                        int[][] ruch = figury[i][j].getRuchy();
                        for (int k = 0; k < 8; k++) {
                            for (int l = 0; l < 8; l++) {
                                if (ruch[k][l]!=0) {
                                    return true;
                                }
                            }
                        }
                    }
                } else {
                    if (figury[i][j].getIdGracza()==1) {
                        figury[i][j].ustalRuch(figury);
                        int[][] ruch = figury[i][j].getRuchy();
                        for (int k = 0; k < 8; k++) {
                            for (int l = 0; l < 8; l++) {
                                if (ruch[k][l]!=0) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    private void sprawdzSzacha(boolean czyKolejBialego) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (czyKolejBialego) {
                    if (figury[i][j].getIdGracza()==1) {
                        int[][] ruch = figury[i][j].ustalRuchyBezSzacha(figury);
                        for (int k = 0; k < 8; k++) {
                            for (int l = 0; l < 8; l++) {
                                if (ruch[k][l]==5) {
                                    figury[k][l].setIcon(figury[k][l].obrazekBicia);
                                    return;
                                }
                            }
                        }
                    }
                    for (int k = 0; k < 8; k++) {
                        for (int l = 0; l < 8; l++) {
                            if (figury[k][l].getNazwa()!=null && figury[k][l].getNazwa().endsWith("king")) {
                                figury[k][l].setIcon(figury[k][l].obrazek);
                            }
                        }
                    }
                } else {
                    if (figury[i][j].getIdGracza()==2) {
                        int[][] ruch = figury[i][j].ustalRuchyBezSzacha(figury);
                        for (int k = 0; k < 8; k++) {
                            for (int l = 0; l < 8; l++) {
                                if (ruch[k][l]==5) {
                                    figury[k][l].setIcon(figury[k][l].obrazekBicia);
                                    return;
                                }
                            }
                        }
                    }
                    for (int k = 0; k < 8; k++) {
                        for (int l = 0; l < 8; l++) {
                            if (figury[k][l].getNazwa()!=null && figury[k][l].getNazwa().endsWith("king")) {
                                figury[k][l].setIcon(figury[k][l].obrazek);
                            }
                        }
                    }
                }
            }
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
    public void wygasPola(int pola[][]) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pola[i][j]==1) {
                    figury[i][j].setOpaque(false);
                    
                }
            }
        }
    }
    public void podswietlBicia(int pola[][]) {
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
    public void wygasBicia(int pola[][]) {
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
    public ImageIcon createImageIcon(String path) {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        }
        else {
            return null; 
        }
    }
    public void koniecGry(Figura figura) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                figury[i][j].removeMouseListener(this);
            }
        }
        Icon obrazekWygranego;
        if (figura.getIdGracza()==1) {
            obrazekWygranego = createImageIcon("/ikony/czarny.png");
        } else {
            obrazekWygranego = createImageIcon("/ikony/bialy.png");
        }
        JLabel wygrany = new JLabel(obrazekWygranego);
        wygrany.setBounds(50, 125, 300, 150);
        add(wygrany, 2);
    }



}
 