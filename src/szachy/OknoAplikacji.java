package szachy;

import szachy.menuBar.MenuBar;
import java.awt.*;
import java.net.URL;
import javax.swing.*;

/**
 *
 * @author Michal
 */

public class OknoAplikacji extends JFrame{
    public static final int MARGINES=15, WYSOKOSC_MENU_BAR=25; 
    public static final int WYSOKOSC_PANELI = 40;
    public static final int SZEROKOSC_PLANSZY=400, WYSOKOSC_PLANSZY=400,X_PLANSZY=MARGINES,Y_PLANSZY=MARGINES + WYSOKOSC_MENU_BAR;
    public static final int SZEROKOSC_RAMKI=SZEROKOSC_PLANSZY + 2 * MARGINES;
    public static final int WYSOKOSC_RAMKI=WYSOKOSC_PLANSZY + 2 * MARGINES + WYSOKOSC_MENU_BAR;
    public static final int X_RAMKI=0, Y_RAMKI=0;
    public static final int SZEROKOSC_MENU_BAR=SZEROKOSC_RAMKI;
    private MenuBar menuBar;
    private Plansza panelPlanszy;
    public OknoAplikacji(String nazwaOkna) {
        super(nazwaOkna);
        setLayout(null);
        inicjalizacjaRamki(X_RAMKI, Y_RAMKI, SZEROKOSC_RAMKI, WYSOKOSC_RAMKI);
        inicjalizacjaOrazDodanieMenuBara();
        Plansza.obrazek = createImageIcon("/ikony/chessboardpb.png");
        inicjalizacjaDodaniePlanszy();
        setVisible(true);
    }
    public void inicjalizacjaDodaniePlanszy() {
        panelPlanszy = new Plansza(X_PLANSZY , Y_PLANSZY, SZEROKOSC_PLANSZY, WYSOKOSC_PLANSZY);
        add(panelPlanszy);
        panelPlanszy.repaint();
        revalidate();
        setVisible(true);
    }
    public void usunieciePlanszy() {
        panelPlanszy.removeAll();
        remove(panelPlanszy);
        revalidate();
        panelPlanszy = null;
    }
    private void inicjalizacjaOrazDodanieMenuBara() {
        menuBar = new MenuBar(this);
        menuBar.setBounds(0, 0, SZEROKOSC_MENU_BAR , WYSOKOSC_MENU_BAR);
        add(menuBar, BorderLayout.NORTH);
    }
    private void inicjalizacjaRamki(int x, int y, int szerokosc, int wysokosc) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        Insets insets;
        insets = getInsets();
        setBounds(x, y, szerokosc + insets.left + insets.right, wysokosc + insets.top + insets.bottom);
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
}
