package ShopApp;

import javax.swing.*;
import java.awt.*;

public class main {

    JFrame frame;
    JLabel displayField;
    ImageIcon image;

    public static void main(String[] args) {
     
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu1().setVisible(true);
            }
        });
    }

}
