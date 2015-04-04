package szachy;

import java.awt.*;
import java.awt.event.MouseListener;
import java.net.URL;
import javax.swing.*;

/**
 *
 * @author Michal
 */
public class Figura extends JLabel {
    private final int MNOZNIK_POLA=70;
    private Point pozycja;
    private int idGracza;
    private Dimension rozmiarFigury = new Dimension(70, 70);
    private Dimension rozmiarPustegoPola = new Dimension(60, 60);
    private String nazwa;
    private Icon obrazek;
    private boolean stan,pierwszyRuch;
    private int ruchy[][];
    //mozliwe ruchy

    public Figura(Point pozycja, int idGracza, String nazwa, MouseListener ml) {
        this.pozycja = new Point(pozycja.x * MNOZNIK_POLA, pozycja.y * MNOZNIK_POLA);
        this.setLocation(this.pozycja);
        this.setSize(rozmiarFigury);
        addMouseListener(ml);
        this.idGracza = idGracza;
        this.nazwa = nazwa;
        this.stan = false;
        setOpaque(false);
        this.obrazek = createImageIcon("/ikony/" + this.nazwa + ".png");
        this.setIcon(this.obrazek);
        this.pierwszyRuch = true;
        ruchy = new int[8][8];
        for (int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                ruchy[j][i]=0;
            }
        }
    }
    public Figura(Point pozycja) {
        this.pozycja = new Point(pozycja.x * MNOZNIK_POLA + 5, pozycja.y * MNOZNIK_POLA + 5);
        this.setLocation(this.pozycja);
        this.setSize(rozmiarPustegoPola);
        setOpaque(true);
        setBackground(Color.green);
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

    public void ustalRuch(int[][] plansza) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(plansza[i][j]);
            }
            System.out.println("");
        }
        if (("wtower".equals(this.nazwa)) || ("btower".equals(this.nazwa))) {
            ustalenieRuchuWierzy(plansza);
        } else if (("wpawn".equals(this.nazwa)) || ("bpawn".equals(this.nazwa))) {
            ustalenieRuchuPiona(plansza);
        }
    }
    public void ustalenieRuchuPiona(int[][] plansza) {
        if (this.pierwszyRuch) {
            System.err.println(this.getX() / 70);
            System.err.println(this.getY() / 70);
            if (this.idGracza==1) {
                for (int i = this.getY() / 70 + 1; i < (this.getY() / 70) + 3; i++) {
                    if (plansza[this.getX() / 70][i]==0) {
                        this.ruchy[this.getX() / 70][i] = 1;
                    } else {
                        break;
                    }
                }
                if ((this.getX() / 70 < 7) && (this.getY() / 70 < 7) && (plansza[this.getX() / 70 + 1][this.getY() / 70 + 1] == 2)) {
                    ruchy[this.getX() / 70 + 1][this.getY() / 70 + 1] = 4;
                } else if ((this.getX() / 70 > 0) && (this.getY() / 70 < 7)  && (plansza[this.getX() / 70 - 1][this.getY() / 70 + 1] == 2)) {
                    ruchy[this.getX() / 70 - 1][this.getY() / 70 + 1] = 4;
                }
            } else if (this.idGracza==2) {
                for (int i = this.getY() / 70 - 1; i > (this.getY() / 70) - 3; i--) {
                    if (plansza[this.getX() / 70][i]==0) {
                        this.ruchy[this.getX() / 70][i] = 1;
                    } else {
                        break;
                    }
                }
                if ((this.getX() / 70 > 0) && (this.getY() / 70 > 0) && (plansza[this.getX() / 70 - 1][this.getY() / 70 - 1] == 1)) {
                    ruchy[this.getX() / 70 - 1][this.getY() / 70 - 1] = 4;
                } else if ((this.getX() / 70<7) && (this.getY() / 70 > 0) && (plansza[this.getX() / 70 + 1][this.getY() / 70 - 1] == 1)) {
                    ruchy[this.getX() / 70 + 1][this.getY() / 70 - 1] = 4;
                }
            }
        }
        
        wypiszRuch();
    }
    public void ustalenieRuchuWierzy(int[][] plansza) {
        for (int i = (this.getY() / 70) + 1; i < 8; i++) {
            if (plansza[this.getX() / 70][i]==0) {
                this.ruchy[this.getX() / 70][i] = 1;
            } else {
                if (this.idGracza != plansza[this.getX() / 70][i]) {
                    this.ruchy[this.getX() / 70][i] = 4;
                }
                break;
            }
        }
        for (int i = (this.getY() / 70) - 1; i >=0 ; i--) {
            if (plansza[this.getX() / 70][i]==0) {
                this.ruchy[this.getX() / 70][i] = 1;
            } else {
                if (this.idGracza != plansza[this.getX() / 70][i]) {
                    this.ruchy[this.getX() / 70][i] = 4;
                }
                break;
            }
        }
        for (int i = (this.getX() / 70) - 1; i >= 0 ; i--) {
            if (plansza[i][this.getY() / 70]==0) {
                this.ruchy[i][this.getY() / 70] = 1;
            } else {
                if (this.idGracza != plansza[this.getX() / 70][i]) {
                    this.ruchy[this.getX() / 70][i] = 4;
                }
                break;
            }
        }
        for (int i = (this.getX() / 70) + 1; i < 8 ; i++) {
            if (plansza[i][this.getY() / 70]==0) {
                this.ruchy[i][this.getY() / 70] = 1;
            } else {
                if (this.idGracza != plansza[this.getX() / 70][i]) {
                    this.ruchy[this.getX() / 70][i] = 4;
                }
                break;
            }
        }
        wypiszRuch();
    }
    public void wypiszRuch() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(ruchy[i][j]);
            }
            System.out.println("");
        }
    }
    
}
