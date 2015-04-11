package szachy;

import szachy.ruchy.RuchGonca;
import java.awt.*;
import java.awt.event.MouseListener;
import java.net.URL;
import javax.swing.*;
import szachy.ruchy.RuchKonia;
import szachy.ruchy.RuchKrola;
import szachy.ruchy.RuchKrolowej;
import szachy.ruchy.RuchPionka;
import szachy.ruchy.RuchWiezy;

/**
 *
 * @author Michal
 */
public class Figura extends JLabel{
    private final int MNOZNIK_POLA=50;
    private int idGracza;
    private int szerokosc = 50, wysokosc=50;
    private int szerPustego = 40, wysPustego = 40;
    private String nazwa;
    public Icon obrazek,obrazekBicia;
    private boolean stan,pierwszyRuch;
    private int ruchy[][];
    private int[][] ruchPlanszy;
    public Figura(Point pozycja, int idGracza, String nazwa, MouseListener ml) {
        this.setLocation(new Point(pozycja.x * MNOZNIK_POLA, pozycja.y * MNOZNIK_POLA));
        this.setSize(szerokosc, wysokosc);
        addMouseListener(ml);
        this.idGracza = idGracza;
        this.nazwa = nazwa;
        this.stan = false;
        setOpaque(false);
        this.obrazek = createImageIcon("/ikony/" + this.nazwa + ".png");
        this.obrazekBicia = createImageIcon("/ikony/b" + this.nazwa + ".png");
        this.setIcon(this.obrazek);
        this.pierwszyRuch = true;
        
    }
    public Figura(Point pozycja, int idGracza, MouseListener ml) {
        this.setLocation(new Point(pozycja.x * MNOZNIK_POLA + 5, pozycja.y * MNOZNIK_POLA + 5));
        this.setSize(szerPustego, wysPustego);
        this.idGracza = idGracza;
        setOpaque(false);
        addMouseListener(ml);
    }
    public Figura(Figura figura, MouseListener ml) {
        this.idGracza = figura.getIdGracza();
        this.setLocation(figura.getX(), figura.getY());
        this.setSize(figura.getWidth(), figura.getHeight());
        this.nazwa = figura.getNazwa();
        this.stan = figura.isStan();
        this.obrazek = figura.getObrazek();
        this.obrazekBicia = createImageIcon("/ikony/b" + this.nazwa + ".png");
        setIcon(obrazek);
        this.pierwszyRuch = figura.isPierwszyRuch();
        addMouseListener(ml);
    }
    public int[][] ustalRuchyBezSzacha(Figura[][] plansza) {
        int[][] ruch = new int[8][8];
        for (int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                ruch[j][i]=0;
            }
        }
        if (null != this.nazwa) switch (this.nazwa) {
            case "wtower":
            case "btower":
                ruch = RuchWiezy.ustalRuch(this, plansza);
                break;
            case "wpawn":
            case "bpawn":
                ruch = RuchPionka.ustalRuch(this, plansza);
                break;
            case "whorse":
            case "bhorse":
                ruch = RuchKonia.ustalRuch(this, plansza);
                break;
            case "wbishop":
            case "bbishop":
                ruch = RuchGonca.ustalRuch(this, plansza);
                break;
            case "wqueen":
            case "bqueen":
                ruch = RuchKrolowej.ustalRuch(this, plansza);
                break;
            case "wking":
            case "bking":
                ruch = RuchKrola.ustalRuch(this, plansza);
                break;
        }
        return ruch;
    }
    public void ustalRuch(Figura[][] plansza) {
        ruchy = new int[8][8];
        for (int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                ruchy[j][i]=0;
            }
        }
        resetRuchowPlanszy();
        this.ruchy = ustalRuchyBezSzacha(plansza);
        for (int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                if (ruchy[i][j]!=0) {
                    int id1= plansza[this.getX()/50][this.getY()/50].getIdGracza();
                    int id2= plansza[i][j].getIdGracza();
                    int id3=0;
                    String nazwa1 = plansza[this.getX()/50][this.getY()/50].getNazwa();
                    String nazwa2 =  plansza[i][j].getNazwa();
                    String nazwa3=null;
                    if (ruchy[i][j]==4) {
                        id3= plansza[i][j].getIdGracza();
                        id2 = 0;
                        nazwa3 =  plansza[i][j].getNazwa();
                        nazwa2 = null;
                    }
                    plansza[this.getX()/50][this.getY()/50].setIdGracza(id2);
                    plansza[this.getX()/50][this.getY()/50].setNazwa(nazwa2);
                    plansza[i][j].setIdGracza(id1);
                    plansza[i][j].setNazwa(nazwa1);
                    resetRuchowPlanszy();
                    ustalRuchyPlanszy(id1, plansza);
                    if (czySzachuje(ruchPlanszy)) {
                        ruchy[i][j] = 0;
                    }
                    if (id3==1 || id3==2) {
                        plansza[this.getX()/50][this.getY()/50].setIdGracza(id1);
                        plansza[this.getX()/50][this.getY()/50].setNazwa(nazwa1);
                        plansza[i][j].setIdGracza(id3);
                        plansza[i][j].setNazwa(nazwa3);
                    } else {
                        plansza[this.getX()/50][this.getY()/50].setIdGracza(id1);
                        plansza[this.getX()/50][this.getY()/50].setNazwa(nazwa1);
                        plansza[i][j].setIdGracza(id2);
                        plansza[i][j].setNazwa(nazwa2);
                        
                    }
                    
                }
            }
        }
    }
    public void resetRuchowPlanszy() {
        this.ruchPlanszy = new int[8][8];
        for (int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                this.ruchPlanszy[i][j]=0;
            }
        }
    }
    public void ustalRuchyPlanszy(int idGracza, Figura[][] figury) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (figury[i][j].getIdGracza()!=idGracza) {
                    int[][] ruch = figury[i][j].ustalRuchyBezSzacha(figury);
                    for (int k = 0; k < 8; k++) {
                        for (int l = 0; l < 8; l++) {
                            if (ruch[k][l]>=this.ruchPlanszy[k][l]) {
                                this.ruchPlanszy[k][l] = ruch[k][l];
                            }
                        }
                    }
                }
            }
        }
    }
    public boolean czySzachuje(int[][] plansza) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (plansza[i][j]==5) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void wypiszRuch() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(ruchy[j][i] + " ");
            }
            System.out.println("");
        }
    }
    public void setPierwszyRuch(boolean pierwszyRuch) {
        this.pierwszyRuch = pierwszyRuch;
    }
    public boolean isPierwszyRuch() {
        return pierwszyRuch;
    }
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
    public String getNazwa() {
        return nazwa;
    }
    public int[][] getRuchy() {
        return ruchy;
    }
    public void setStan(boolean stan) {
        this.stan = stan;
    }
    public boolean isStan() {
        return stan;
    }
    public int getIdGracza() {
        return idGracza;
    }
    public void setIdGracza(int idGracza) {
        this.idGracza = idGracza;
    }
    public Icon getObrazek() {
        return obrazek;
    }
    private ImageIcon createImageIcon(String path) {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        }
        else {
            return null; 
        }
    } 
}
