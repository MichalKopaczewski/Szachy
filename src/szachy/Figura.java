package szachy;

import java.awt.*;
import java.awt.event.MouseListener;
import java.net.URL;
import javax.swing.*;

/**
 *
 * @author Michal
 */
public class Figura extends JLabel{
    private final int MNOZNIK_POLA=70;
    private int idGracza;
    private int szerokosc = 70, wysokosc=70;
    private int szerPustego = 60, wysPustego = 60;
    private String nazwa;
    private Icon obrazek;
    private boolean stan,pierwszyRuch;
    private int ruchy[][];
    //mozliwe ruchy

    public Figura(Point pozycja, int idGracza, String nazwa, MouseListener ml) {
        
        this.setLocation(new Point(pozycja.x * MNOZNIK_POLA, pozycja.y * MNOZNIK_POLA));
        this.setSize(szerokosc, wysokosc);
        addMouseListener(ml);
        this.idGracza = idGracza;
        this.nazwa = nazwa;
        this.stan = false;
        setOpaque(false);
        this.obrazek = createImageIcon("/ikony/" + this.nazwa + ".png");
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
        setIcon(obrazek);
        this.pierwszyRuch = figura.isPierwszyRuch();
        addMouseListener(ml);
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

    public void ustalRuch(Figura[][] plansza) {
        ruchy = new int[8][8];
        for (int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                ruchy[j][i]=0;
            }
        }
        if (("wtower".equals(this.nazwa)) || ("btower".equals(this.nazwa))) {
            ustalenieRuchuWiezy(plansza);
        } else if (("wpawn".equals(this.nazwa)) || ("bpawn".equals(this.nazwa))) {
            ustalenieRuchuPiona(plansza);
        }
        //wypiszRuch();
    }
    public void ustalenieRuchuPiona(Figura[][] plansza) {
        if (this.pierwszyRuch) {
            if (this.idGracza==1) {
                for (int i = this.getY() / 70 + 1; i < (this.getY() / 70) + 3; i++) {
                    if (plansza[this.getX() / 70][i].getIdGracza()==0) {
                        this.ruchy[this.getX() / 70][i] = 1;
                    } else {
                        break;
                    }
                }
                if ((this.getX() / 70 < 7) && (this.getY() / 70 < 7) && (plansza[this.getX() / 70 + 1][this.getY() / 70 + 1].getIdGracza() == 2)) {
                    ruchy[this.getX() / 70 + 1][this.getY() / 70 + 1] = 4;
                }
                if ((this.getX() / 70 > 0) && (this.getY() / 70 < 7)  && (plansza[this.getX() / 70 - 1][this.getY() / 70 + 1].getIdGracza() == 2)) {
                    ruchy[this.getX() / 70 - 1][this.getY() / 70 + 1] = 4;
                }
            } else if (this.idGracza==2) {
                for (int i = this.getY() / 70 - 1; i > (this.getY() / 70) - 3; i--) {
                    if (plansza[this.getX() / 70][i].getIdGracza()==0) {
                        this.ruchy[this.getX() / 70][i] = 1;
                    } else {
                        break;
                    }
                }
                if ((this.getX() / 70 > 0) && (this.getY() / 70 > 0) && (plansza[this.getX() / 70 - 1][this.getY() / 70 - 1].getIdGracza() == 1)) {
                    ruchy[this.getX() / 70 - 1][this.getY() / 70 - 1] = 4;
                }
                if ((this.getX() / 70<7) && (this.getY() / 70 > 0) && (plansza[this.getX() / 70 + 1][this.getY() / 70 - 1].getIdGracza() == 1)) {
                    ruchy[this.getX() / 70 + 1][this.getY() / 70 - 1] = 4;
                }
            }
        } else {
            if (this.idGracza==1) {
                for (int i = this.getY() / 70 + 1; i < (this.getY() / 70) + 2; i++) {
                    if (plansza[this.getX() / 70][i].getIdGracza()==0) {
                        this.ruchy[this.getX() / 70][i] = 1;
                    } else {
                        break;
                    }
                }
                if ((this.getX() / 70 < 7) && (this.getY() / 70 < 7) && (plansza[this.getX() / 70 + 1][this.getY() / 70 + 1].getIdGracza() == 2)) {
                    ruchy[this.getX() / 70 + 1][this.getY() / 70 + 1] = 4;
                }
                if ((this.getX() / 70 > 0) && (this.getY() / 70 < 7)  && (plansza[this.getX() / 70 - 1][this.getY() / 70 + 1].getIdGracza() == 2)) {
                    ruchy[this.getX() / 70 - 1][this.getY() / 70 + 1] = 4;
                }
            } else if (this.idGracza==2) {
                for (int i = this.getY() / 70 - 1; i > (this.getY() / 70) - 2; i--) {
                    if (plansza[this.getX() / 70][i].getIdGracza()==0) {
                        this.ruchy[this.getX() / 70][i] = 1;
                    } else {
                        break;
                    }
                }
                if ((this.getX() / 70 > 0) && (this.getY() / 70 > 0) && (plansza[this.getX() / 70 - 1][this.getY() / 70 - 1].getIdGracza() == 1)) {
                    ruchy[this.getX() / 70 - 1][this.getY() / 70 - 1] = 4;
                }
                if ((this.getX() / 70<7) && (this.getY() / 70 > 0) && (plansza[this.getX() / 70 + 1][this.getY() / 70 - 1].getIdGracza() == 1)) {
                    ruchy[this.getX() / 70 + 1][this.getY() / 70 - 1] = 4;
                }
            }
        }
    }
    public void ustalenieRuchuWiezy(Figura[][] plansza) {
        for (int i = (this.getY() / 70) + 1; i < 8; i++) {
            if (plansza[this.getX() / 70][i].getIdGracza()==0) {
                this.ruchy[this.getX() / 70][i] = 1;
            } else {
                if (this.idGracza != plansza[this.getX() / 70][i].getIdGracza()) {
                    this.ruchy[this.getX() / 70][i] = 4;
                }
                break;
            }
        }
        for (int i = (this.getY() / 70) - 1; i >=0 ; i--) {
            if (plansza[this.getX() / 70][i].getIdGracza()==0) {
                this.ruchy[this.getX() / 70][i] = 1;
            } else {
                if (this.idGracza != plansza[this.getX() / 70][i].getIdGracza()) {
                    this.ruchy[this.getX() / 70][i] = 4;
                }
                break;
            }
        }
        for (int i = (this.getX() / 70) - 1; i >= 0 ; i--) {
            if (plansza[i][this.getY() / 70].getIdGracza()==0) {
                this.ruchy[i][this.getY() / 70] = 1;
            } else {
                if (this.idGracza != plansza[i][this.getY() / 70].getIdGracza()) {
                    this.ruchy[i][this.getY() / 70] = 4;
                }
                break;
            }
        }
        for (int i = (this.getX() / 70) + 1; i < 8 ; i++) {
            if (plansza[i][this.getY() / 70].getIdGracza()==0) {
                this.ruchy[i][this.getY() / 70] = 1;
            } else {
                if (this.idGracza != plansza[i][this.getY() / 70].getIdGracza()) {
                    this.ruchy[i][this.getY() / 70] = 4;
                }
                break;
            }
        }
    }
    public void wypiszRuch() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(ruchy[i][j]);
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

    public Icon getObrazek() {
        return obrazek;
    }

    @Override
    public void setLocation(Point point) {
        super.setLocation(point); //To change body of generated methods, choose Tools | Templates.
    }
        
}
