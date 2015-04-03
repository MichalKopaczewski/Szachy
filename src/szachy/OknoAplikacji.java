package szachy;

import szachy.menuBar.MenuBar;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Michal
 */

public class OknoAplikacji extends JFrame{
    public static final int MARGINES=20, WYSOKOSC_MENU_BAR=25; 
    public static final int WYSOKOSC_PANELI = 40;
    public static final int SZEROKOSC_PLANSZY=560, WYSOKOSC_PLANSZY=560,X_PLANSZY=MARGINES,Y_PLANSZY=MARGINES + WYSOKOSC_MENU_BAR;
    public static final int X_PANELU_PRZYCISKOW = MARGINES, Y_PANELU_PRZYCISKOW = 2 * MARGINES + WYSOKOSC_PLANSZY + WYSOKOSC_MENU_BAR;
    public static final int SZEROKOSC_PANELU_PRZYCISKOW = SZEROKOSC_PLANSZY, WYSOKOSC_PANELU_PRZYCISKOW = WYSOKOSC_PANELI;
    public static final int X_PANELU_CZASOW = MARGINES, Y_PANELU_CZASOW = 3 * MARGINES + WYSOKOSC_PLANSZY + WYSOKOSC_MENU_BAR + WYSOKOSC_PANELU_PRZYCISKOW;
    public static final int SZEROKOSC_PANELU_CZASOW = SZEROKOSC_PLANSZY, WYSOKOSC_PANELU_CZASOW = WYSOKOSC_PANELI;
    public static final int X_PANELU_KOMUNIKATOW = MARGINES;
    public static final int Y_PANELU_KOMUNIKATOW = 4 * MARGINES + WYSOKOSC_PLANSZY + WYSOKOSC_MENU_BAR + WYSOKOSC_PANELU_PRZYCISKOW + WYSOKOSC_PANELU_CZASOW;
    public static final int WYSOKOSC_PANELU_KOMUNIKATOW = WYSOKOSC_PANELI, SZEROKOSC_PANELU_KOMUNIKATOW = SZEROKOSC_PLANSZY;
    public static final int SZEROKOSC_RAMKI=SZEROKOSC_PLANSZY + 2 * MARGINES;
    public static final int WYSOKOSC_RAMKI=WYSOKOSC_PLANSZY + 5 * MARGINES + WYSOKOSC_PANELU_PRZYCISKOW + WYSOKOSC_PANELU_CZASOW + WYSOKOSC_MENU_BAR + WYSOKOSC_PANELU_KOMUNIKATOW;
    public static final int X_RAMKI=0, Y_RAMKI=0;
    public static final int SZEROKOSC_MENU_BAR=SZEROKOSC_RAMKI;
    private MenuBar menuBar;
    private PanelPrzyciskow panelPrzyciskow,panelCzasow,panelKomunikatow;
    private Plansza panelPlanszy;

    public OknoAplikacji(String nazwaOkna) {
        super(nazwaOkna);
        setLayout(null);
        inicjalizacjaRamki(X_RAMKI, Y_RAMKI, SZEROKOSC_RAMKI, WYSOKOSC_RAMKI);
        inicjalizacjaOrazDodanieMenuBara();
        panelPlanszy = new Plansza(X_PLANSZY , Y_PLANSZY, SZEROKOSC_PLANSZY, WYSOKOSC_PLANSZY);
        add(panelPlanszy);
        panelPrzyciskow = new PanelPrzyciskow(X_PANELU_PRZYCISKOW , Y_PANELU_PRZYCISKOW, SZEROKOSC_PANELU_PRZYCISKOW, WYSOKOSC_PANELU_PRZYCISKOW);
        add(panelPrzyciskow);
        panelPrzyciskow.setBackground(Color.BLUE);
        panelCzasow = new PanelPrzyciskow(X_PANELU_CZASOW , Y_PANELU_CZASOW, SZEROKOSC_PANELU_CZASOW, WYSOKOSC_PANELU_CZASOW);
        add(panelCzasow);
        panelCzasow.setBackground(Color.GREEN);             //dodac panel komunikatow
        panelKomunikatow = new PanelPrzyciskow(X_PANELU_KOMUNIKATOW, Y_PANELU_KOMUNIKATOW, SZEROKOSC_PANELU_KOMUNIKATOW, WYSOKOSC_PANELU_KOMUNIKATOW);
        add(panelKomunikatow);
        panelKomunikatow.setBackground(Color.PINK);
        
        setVisible(true);                                               //zawsze na koncu
    }
    private void inicjalizacjaOrazDodanieMenuBara() {
        menuBar = new MenuBar();
        menuBar.setBounds(0, 0, SZEROKOSC_MENU_BAR , WYSOKOSC_MENU_BAR);
        add(menuBar, BorderLayout.NORTH);
    }
    private void inicjalizacjaRamki(int x, int y, int szerokosc, int wysokosc) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        Insets insets;
        insets = getInsets();
        setBounds(x, y, szerokosc + insets.left + insets.right, wysokosc + insets.top + insets.bottom);
        setResizable(false);
    }
        
}
