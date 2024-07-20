import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Window extends JFrame {

    public Window() {
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        MyPanel panel = new MyPanel();
        this.add(panel);
        repaint();

    }
}
