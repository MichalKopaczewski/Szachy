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
        if ("wtower".equals(this.nazwa) || "btower".equals(this.nazwa)) {
            ustalenieRuchuWiezy(plansza);
        } else if ("wpawn".equals(this.nazwa) || "bpawn".equals(this.nazwa)) {
            ustalenieRuchuPiona(plansza);
        } else if ("whorse".equals(this.nazwa) || "bhorse".equals(this.nazwa)) {
            ustalenieRuchuKonia(plansza);
        } else if ("wbishop".equals(this.nazwa) || "bbishop".equals(this.nazwa)) {
            ustalenieRuchuGonca(plansza);
        } else if ("wqueen".equals(this.nazwa) || "bqueen".equals(this.nazwa)) {
            ustalenieRuchuKrolowej(plansza);
        } else if ("wking".equals(this.nazwa) || "bking".equals(this.nazwa)) {
            ustalenieRuchuKrola(plansza);
        }
        wypiszRuch();
    }
    public void ustalenieRuchuKrolowej(Figura[][] plansza) {
        ustalenieRuchuWiezy(plansza);
        ustalenieRuchuGonca(plansza);
    }
    public void ustalenieRuchuKrola(Figura[][] plansza) {
        int x = this.getX() / 70, y = this.getY() / 70;
        System.out.println(String.valueOf(x) + " " + String.valueOf(y));
        if (x>0 && y>0 && x<7 && y<7) {
            ustawBicieNaPolu(plansza, x-1, y);
            ustawBicieNaPolu(plansza, x-1, y+1);
            ustawBicieNaPolu(plansza, x, y+1);
            ustawBicieNaPolu(plansza, x+1, y+1);
            ustawBicieNaPolu(plansza, x+1, y);
            ustawBicieNaPolu(plansza, x+1, y-1);
            ustawBicieNaPolu(plansza, x, y-1);
            ustawBicieNaPolu(plansza, x-1, y-1);
        } else if (x==0 && y>0 && y<7) {
            ustawBicieNaPolu(plansza, x, y+1);
            ustawBicieNaPolu(plansza, x+1, y+1);
            ustawBicieNaPolu(plansza, x+1, y);
            ustawBicieNaPolu(plansza, x+1, y-1);
            ustawBicieNaPolu(plansza, x, y-1);
        } else if (x==7 && y>0 && y<7) {
            ustawBicieNaPolu(plansza, x-1, y);
            ustawBicieNaPolu(plansza, x-1, y+1);
            ustawBicieNaPolu(plansza, x, y+1);
            ustawBicieNaPolu(plansza, x, y-1);
            ustawBicieNaPolu(plansza, x-1, y-1);
        } else if (y==7 && x>0 && x<7) {
            ustawBicieNaPolu(plansza, x-1, y);
            ustawBicieNaPolu(plansza, x+1, y);
            ustawBicieNaPolu(plansza, x+1, y-1);
            ustawBicieNaPolu(plansza, x, y-1);
            ustawBicieNaPolu(plansza, x-1, y-1);
        } else if (y==0 && x>0 && x<7) {
            ustawBicieNaPolu(plansza, x-1, y);
            ustawBicieNaPolu(plansza, x-1, y+1);
            ustawBicieNaPolu(plansza, x, y+1);
            ustawBicieNaPolu(plansza, x+1, y+1);
            ustawBicieNaPolu(plansza, x+1, y);
        } else if (x==0 && y==0) {
            ustawBicieNaPolu(plansza, x, y+1);
            ustawBicieNaPolu(plansza, x+1, y+1);
            ustawBicieNaPolu(plansza, x+1, y);
        } else if (x==7 && y==0) {
            ustawBicieNaPolu(plansza, x-1, y);
            ustawBicieNaPolu(plansza, x-1, y+1);
            ustawBicieNaPolu(plansza, x, y+1);
        } else if (x==7 && y==7) {
            ustawBicieNaPolu(plansza, x-1, y);
            ustawBicieNaPolu(plansza, x, y-1);
            ustawBicieNaPolu(plansza, x-1, y-1);
        } else if (x==0 && y==7) {
            ustawBicieNaPolu(plansza, x+1, y);
            ustawBicieNaPolu(plansza, x+1, y-1);
            ustawBicieNaPolu(plansza, x, y-1);
        }
        this.wypiszRuch();
    }
    public void ustawBicieNaPolu(Figura[][] plansza, int xB, int yB) {
        int x = this.getX() / 70, y = this.getY() / 70;
        if (plansza[xB][yB].getIdGracza()==0) {
            ruchy[xB][yB] = 1;
        } else if (plansza[xB][yB].getIdGracza()!=this.getIdGracza()) {
            ruchy[xB][yB] = 4;
        }
    }
    public void ustalenieRuchuGonca(Figura[][] plansza) {
        int x = this.getX() / 70, y = this.getY() / 70;
        System.out.println(String.valueOf(x) + " " + String.valueOf(y));
        int i,j;
        if (x>0 && y<7) {
            j = y + 1;
            for (i = x-1; i >= 0; i--) {
                if (plansza[i][j].getIdGracza()==0) {
                    ruchy[i][j]=1;
                } else if (plansza[i][j].getIdGracza()!=this.getIdGracza()) {
                    ruchy[i][j]=4;
                    break;
                } else if (plansza[i][j].getIdGracza()==this.getIdGracza()) {
                    break;
                }
                j++;
                if (j==8) {
                    break;
                }
            }
        }
        if (x<7 && y<7) {
            j = y + 1;
            for (i = x+1; i < 8; i++) {
                if (plansza[i][j].getIdGracza()==0) {
                    ruchy[i][j]=1;
                } else if (plansza[i][j].getIdGracza()!=this.getIdGracza()) {
                    ruchy[i][j]=4;
                    break;
                } else if (plansza[i][j].getIdGracza()==this.getIdGracza()) {
                    break;
                }
                j++;
                if (j==8) {
                    break;
                }
            }
        }
        if (x<7 && y>0) {
            j = y - 1;
            for (i = x+1; i < 8; i++) {
                if (plansza[i][j].getIdGracza()==0) {
                    ruchy[i][j]=1;
                } else if (plansza[i][j].getIdGracza()!=this.getIdGracza()) {
                    ruchy[i][j]=4;
                    break;
                } else if (plansza[i][j].getIdGracza()==this.getIdGracza()) {
                    break;
                }
                j--;
                if (j==-1) {
                    break;
                }
            }
        }
        if (x>0 && y>0) {
            j = y - 1;
            for (i = x-1; i >= 0; i--) {
                if (plansza[i][j].getIdGracza()==0) {
                    ruchy[i][j]=1;
                } else if (plansza[i][j].getIdGracza()!=this.getIdGracza()) {
                    ruchy[i][j]=4;
                    break;
                } else if (plansza[i][j].getIdGracza()==this.getIdGracza()) {
                    break;
                }
                j--;
                if (j==-1) {
                    break;
                }
            }
        }
    }
    
    public void ustalenieRuchuPiona(Figura[][] plansza) {
        int x = this.getX() / 70, y = this.getY() / 70;
        if (this.pierwszyRuch) {
            if (this.idGracza==1) {
                for (int i = y + 1; i < y + 3; i++) {
                    if (plansza[x][i].getIdGracza()==0) {
                        this.ruchy[x][i] = 1;
                    } else {
                        break;
                    }
                }
                if ((x < 7) && (y < 7) && (plansza[x + 1][y + 1].getIdGracza() == 2)) {
                    ruchy[x + 1][y + 1] = 4;
                }
                if ((x > 0) && (y < 7)  && (plansza[x - 1][y + 1].getIdGracza() == 2)) {
                    ruchy[x - 1][y + 1] = 4;
                }
            } else if (this.idGracza==2) {
                for (int i = y - 1; i > y - 3; i--) {
                    if (plansza[x][i].getIdGracza()==0) {
                        this.ruchy[x][i] = 1;
                    } else {
                        break;
                    }
                }
                if ((x > 0) && (y > 0) && (plansza[x - 1][y - 1].getIdGracza() == 1)) {
                    ruchy[x - 1][y - 1] = 4;
                }
                if ((x<7) && (y > 0) && (plansza[x + 1][y - 1].getIdGracza() == 1)) {
                    ruchy[x + 1][y - 1] = 4;
                }
            }
        } else {
            if (this.idGracza==1) {
                for (int i = y + 1; i < y + 2; i++) {
                    if (plansza[x][i].getIdGracza()==0) {
                        this.ruchy[x][i] = 1;
                    } else {
                        break;
                    }
                }
                if ((x < 7) && (y < 7) && (plansza[x + 1][y + 1].getIdGracza() == 2)) {
                    ruchy[x + 1][y + 1] = 4;
                }
                if ((x > 0) && (y < 7)  && (plansza[x - 1][y + 1].getIdGracza() == 2)) {
                    ruchy[x - 1][y + 1] = 4;
                }
            } else if (this.idGracza==2) {
                for (int i = y - 1; i > y - 2; i--) {
                    if (plansza[x][i].getIdGracza()==0) {
                        this.ruchy[x][i] = 1;
                    } else {
                        break;
                    }
                }
                if ((x > 0) && (y > 0) && (plansza[x - 1][y - 1].getIdGracza() == 1)) {
                    ruchy[x - 1][y - 1] = 4;
                }
                if ((x<7) && (y > 0) && (plansza[x + 1][y - 1].getIdGracza() == 1)) {
                    ruchy[x + 1][y - 1] = 4;
                }
            }
        }
    }
    public void ustalenieRuchuWiezy(Figura[][] plansza) {
        int x = this.getX() / 70, y = this.getY() / 70;
        for (int i = y + 1; i < 8; i++) {
            if (plansza[x][i].getIdGracza()==0) {
                this.ruchy[x][i] = 1;
            } else {
                if (this.idGracza != plansza[x][i].getIdGracza()) {
                    this.ruchy[x][i] = 4;
                }
                break;
            }
        }
        for (int i = y - 1; i >=0 ; i--) {
            if (plansza[x][i].getIdGracza()==0) {
                this.ruchy[x][i] = 1;
            } else {
                if (this.idGracza != plansza[x][i].getIdGracza()) {
                    this.ruchy[x][i] = 4;
                }
                break;
            }
        }
        for (int i = x - 1; i >= 0 ; i--) {
            if (plansza[i][y].getIdGracza()==0) {
                this.ruchy[i][y] = 1;
            } else {
                if (this.idGracza != plansza[i][y].getIdGracza()) {
                    this.ruchy[i][y] = 4;
                }
                break;
            }
        }
        for (int i = x + 1; i < 8 ; i++) {
            if (plansza[i][y].getIdGracza()==0) {
                this.ruchy[i][y] = 1;
            } else {
                if (this.idGracza != plansza[i][y].getIdGracza()) {
                    this.ruchy[i][y] = 4;
                }
                break;
            }
        }
    }
    public void ustalenieRuchuKonia(Figura[][] plansza) {
        int x = this.getX() / 70, y = this.getY() / 70;
        if (x>1 && y<7) {                                                                      //1
            if (plansza[x-2][y+1].getIdGracza()==0) {
                this.ruchy[x-2][y+1]=1;
            } else if (plansza[x-2][y+1].getIdGracza()!=this.getIdGracza()) {
                this.ruchy[x-2][y+1]=4;
            }
        }
        if (x>0 && y<6) {                                                                     //2
            if (plansza[x-1][y+2].getIdGracza()==0) {
                this.ruchy[x-1][y+2]=1;
            } else if (plansza[x-1][y+2].getIdGracza()!=this.getIdGracza()) {
                this.ruchy[x-1][y+2]=4;
            }
        }
        if (x<7 && y<6) {                                                           //3
            if (plansza[x+1][y+2].getIdGracza()==0) {
                this.ruchy[x+1][y+2]=1;
            } else if (plansza[x+1][y+2].getIdGracza()!=this.getIdGracza()) {
                this.ruchy[x+1][y+2]=4;
            }
        }
        if (x<6 && y<7) {                                                                               //4
            if (plansza[x+2][y+1].getIdGracza()==0) {
                this.ruchy[x+2][y+1]=1;
            } else if (plansza[x+2][y+1].getIdGracza()!=this.getIdGracza()) {
                this.ruchy[x+2][y+1]=4;
            }
        }
        if (x<6 && y>0) {                                                                          //5
            if (plansza[x+2][y-1].getIdGracza()==0) {
                this.ruchy[x+2][y-1]=1;
            } else if (plansza[x+2][y-1].getIdGracza()!=this.getIdGracza()) {
                this.ruchy[x+2][y-1]=4;
            }
        }
        if (x<7 && y>1) {                                                                          //6
            if (plansza[x+1][y-2].getIdGracza()==0) {
                this.ruchy[x+1][y-2]=1;
            } else if (plansza[x+1][y-2].getIdGracza()!=this.getIdGracza()) {
                this.ruchy[x+1][y-2]=4;
            }
        }
        if (x>0 && y>1) {                                                                         //7
            if (plansza[x-1][y-2].getIdGracza()==0) {
                this.ruchy[x-1][y-2]=1;
            } else if (plansza[x-1][y-2].getIdGracza()!=this.getIdGracza()) {
                this.ruchy[x-1][y-2]=4;
            }
        }
        if (x>1 && y>0) {                                                                         //8
            if (plansza[x-2][y-1].getIdGracza()==0) {
                this.ruchy[x-2][y-1]=1;
            } else if (plansza[x-2][y-1].getIdGracza()!=this.getIdGracza()) {
                this.ruchy[x-2][y-1]=4;
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
        int a;
        return obrazek;
    }


        
}
