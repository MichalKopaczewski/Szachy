package szachy;

import java.awt.EventQueue;

public class Szachy {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                OknoAplikacji okno = new OknoAplikacji("Szachy");
            }
        });
    }
    
}
