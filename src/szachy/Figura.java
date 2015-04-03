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
    private boolean stan;
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
            System.err.println("Plik jest w" + path);
            return new ImageIcon(imgURL);
        }
        else {
            System.err.println("Pliku nie ma w /src" + path);
            return null; 
        }
    }
    
}
