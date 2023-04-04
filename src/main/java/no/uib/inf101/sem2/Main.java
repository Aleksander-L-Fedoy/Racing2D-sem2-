package no.uib.inf101.sem2;

import no.uib.inf101.sem2.view.Racing2DView;

import javax.swing.JFrame;

public class Main {
  public static void main(String[] args) {
    Racing2DView view = new Racing2DView();

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Racing 2D");
    frame.setContentPane(view);
    frame.pack();
    frame.setVisible(true);
  }
}
