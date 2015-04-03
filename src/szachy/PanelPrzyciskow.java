package szachy;

import java.awt.Color;
import javax.swing.JPanel;

public class PanelPrzyciskow extends JPanel {

    public PanelPrzyciskow(int x, int y, int szerokosc, int wysokosc) {
        setLayout(null);
        setBackground(Color.yellow);
        setBounds(x, y, szerokosc, wysokosc);
    }
    
    
}
