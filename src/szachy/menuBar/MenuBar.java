package szachy.menuBar;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {
    JMenu menuEdycja,menuWidok,menuPomoc;
    JMenuItem kopiuj,wklej,standardowy,naukowy,programisty,statystyka,showHelp,about;

    public MenuBar () {
        super();
        dodanieMenuEdycji();
        dodanieMenuWidoku();
        dodanieMenuPomocy();
    }
    public void dodanieMenuEdycji() {
        menuEdycja = new JMenu("Edycja");
        add(menuEdycja);
        dodanieOpcjiDoMenuEdycji();
    }
    public void dodanieOpcjiDoMenuEdycji() {
        kopiuj = new JMenuItem("Kopiuj");
        wklej = new JMenuItem("Wklej");
        menuEdycja.add(kopiuj);
        menuEdycja.add(wklej);
    }
    public void dodanieMenuWidoku() {
        menuWidok = new JMenu("Widok");
        add(menuWidok);
        dodanieTypowKalkulatora();
    }
    private void dodanieTypowKalkulatora() {
        standardowy = new JMenuItem("Kolory planszy 1");
        naukowy = new JMenuItem("Kolory pionkow 2");
        programisty = new JMenuItem("Kolory 3");
        statystyka = new JMenuItem("Kolory 4");
        menuWidok.add(standardowy);
        menuWidok.add(naukowy);
        menuWidok.add(programisty);
        menuWidok.add(statystyka);
    }
    private void dodanieMenuPomocy() {
        menuPomoc = new JMenu("Pomoc");
        add(menuPomoc);
        dodanieOpcjiPomocy();
    }
    private void dodanieOpcjiPomocy() {
        showHelp = new JMenuItem("Pokaz pomoc");
        about = new JMenuItem("Informacje");
        menuPomoc.add(showHelp);
        menuPomoc.addSeparator();
        menuPomoc.add(about);
    }
}