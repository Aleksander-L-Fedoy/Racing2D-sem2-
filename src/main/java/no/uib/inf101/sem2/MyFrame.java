package no.uib.inf101.sem2;

import javax.swing.JFrame;

import no.uib.inf101.sem2.view.Racing2DView;

public class MyFrame extends JFrame {

    Racing2DView panel;

    MyFrame() {
        panel = new Racing2DView();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setTitle("Racing 2D");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
