package szachy.menuBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import szachy.OknoAplikacji;
import szachy.Plansza;

public class MenuBar extends JMenuBar implements ActionListener{
    JMenu menuGra,menuWidok,menuPomoc;
    JMenuItem nowaGra,wyjscie,bialoCzarne,bordowoZolte,bialoZielone,bialoSiwe,bordowoBiale,zasady,about;
    private OknoAplikacji parentFrame;
    public MenuBar (OknoAplikacji parentFrame) {
        super();
        this.parentFrame = parentFrame;
        dodanieMenuEdycji();
        dodanieMenuWidoku();
        dodanieMenuPomocy();
    }
    public void dodanieMenuEdycji() {
        menuGra = new JMenu("Gra");
        add(menuGra);
        dodanieOpcjiDoMenuEdycji();
    }
    public void dodanieOpcjiDoMenuEdycji() {
        nowaGra = new JMenuItem("Nowa gra");
        wyjscie = new JMenuItem("Wyjście");
        nowaGra.addActionListener(this);
        wyjscie.addActionListener(this);
        menuGra.add(nowaGra);
        menuGra.add(wyjscie);
    }
    public void dodanieMenuWidoku() {
        menuWidok = new JMenu("Widok planszy");
        add(menuWidok);
        dodanieTypowPlanszy();
    }
    private void dodanieTypowPlanszy() {
        bialoCzarne = new JMenuItem("Białe i czarne");
        bialoCzarne.addActionListener(this);
        bordowoZolte = new JMenuItem("Materiał bordowy i żółty");
        bordowoZolte.addActionListener(this);
        bialoZielone = new JMenuItem("Białe i zielone");
        bialoZielone.addActionListener(this);
        bialoSiwe = new JMenuItem("Białe i siwe");
        bialoSiwe.addActionListener(this);
        bordowoBiale = new JMenuItem("Białe i bordowe");
        bordowoBiale.addActionListener(this);
        menuWidok.add(bialoCzarne);
        menuWidok.add(bordowoZolte);
        menuWidok.add(bialoZielone);
        menuWidok.add(bialoSiwe);
        menuWidok.add(bordowoBiale);
    }
    private void dodanieMenuPomocy() {
        menuPomoc = new JMenu("Pomoc");
        add(menuPomoc);
        dodanieOpcjiPomocy();
    }
    private void dodanieOpcjiPomocy() {
        zasady = new JMenuItem("Pokaz pomoc");
        about = new JMenuItem("Informacje");
        about.addActionListener(this);
        zasady.addActionListener(this);
        menuPomoc.add(zasady);
        menuPomoc.addSeparator();
        menuPomoc.add(about);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JMenuItem source = (JMenuItem) ae.getSource();
        System.out.println(source);
        if (source==about) {
            wyswietlOknoInformacji();
        } else if (source==zasady) {
            wyswietlOknoZasad();
        } else if (source==nowaGra) {
            parentFrame.usunieciePlanszy();
            parentFrame.inicjalizacjaDodaniePlanszy();
        } else if (source==wyjscie) {
            System.exit(0);
                    
        } else {
            zmianaPlanszy(source);
        }
    }
    public void zmianaPlanszy(JMenuItem source) {
        if (source==bialoCzarne) {
            Plansza.obrazek = createImageIcon("/ikony/chessboardbc.png");
            JOptionPane.showMessageDialog(parentFrame, "Nowe ustawienia zostaną zastosowane w nowej grze.", "", JOptionPane.INFORMATION_MESSAGE);
        } else if (source==bialoSiwe) {
            Plansza.obrazek = createImageIcon("/ikony/chessboardbs.png");
            JOptionPane.showMessageDialog(parentFrame, "Nowe ustawienia zostaną zastosowane w nowej grze.", "", JOptionPane.INFORMATION_MESSAGE);
        } else if (source==bialoZielone) {
            Plansza.obrazek = createImageIcon("/ikony/chessboardbz.png");
            JOptionPane.showMessageDialog(parentFrame, "Nowe ustawienia zostaną zastosowane w nowej grze.", "", JOptionPane.INFORMATION_MESSAGE);
        } else if (source==bordowoBiale) {
            Plansza.obrazek = createImageIcon("/ikony/chessboardbb.png");
            JOptionPane.showMessageDialog(parentFrame, "Nowe ustawienia zostaną zastosowane w nowej grze.", "", JOptionPane.INFORMATION_MESSAGE);
        } else if (source==bordowoZolte) {
            Plansza.obrazek = createImageIcon("/ikony/chessboardpb.png");
            JOptionPane.showMessageDialog(parentFrame, "Nowe ustawienia zostaną zastosowane w nowej grze.", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void wyswietlOknoInformacji() {
        String[] informacje = new String[3];
        informacje[0] = "Szachy";
        informacje[1] = "Wersja: 1.0";
        informacje[2] = "Autor: Michał Kopaczewski";
        JOptionPane.showMessageDialog(parentFrame, informacje, "O grze", JOptionPane.INFORMATION_MESSAGE);
    }
    public void wyswietlOknoZasad() {
        String[] informacje = new String[7];
        informacje[0] = "Grę rozpoczyna Biały, następnie gracze wykonują ruchy na zmianę." ;
        informacje[1] = " ";
        informacje[2] = "Należy kliknąć bierkę, a następnie kliknąć pole, na które ma zostać przeniesiona.";
        informacje[3] = "Po wybraniu bierki gra wskazuje możliwe ruchy, wyróżniając pola na żółto.";
        informacje[4] = "Możliwe do zdobycia pola z bierkami przeciwnika są podświetlone.";
        informacje[5] = " ";
        informacje[6] = "Grę wygrywa gracz który pierwszy zdobędzie króla przeciwnika.";
        JOptionPane.showMessageDialog(parentFrame, informacje, "O grze", JOptionPane.INFORMATION_MESSAGE);
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