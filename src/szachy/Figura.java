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
    
    public void ustalRuch(Figura[][] plansza) {
        ruchy = new int[8][8];
        for (int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                ruchy[j][i]=0;
            }
        }
        if (null != this.nazwa) switch (this.nazwa) {
            case "wtower":
            case "btower":
                this.ruchy = RuchWiezy.ustalRuch(this, plansza);
                break;
            case "wpawn":
            case "bpawn":
                this.ruchy = RuchPionka.ustalRuch(this, plansza);
                break;
            case "whorse":
            case "bhorse":
                this.ruchy = RuchKonia.ustalRuch(this, plansza);
                break;
            case "wbishop":
            case "bbishop":
                this.ruchy = RuchGonca.ustalRuch(this, plansza);
                break;
            case "wqueen":
            case "bqueen":
                this.ruchy = RuchKrolowej.ustalRuch(this, plansza);
                break;
            case "wking":
            case "bking":
                this.ruchy = RuchKrola.ustalRuch(this, plansza);
                break;
        }
        wypiszRuch();
    }
    public void wypiszRuch() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(ruchy[i][j] + " ");
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
        int a;
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
