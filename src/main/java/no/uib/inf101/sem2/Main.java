package no.uib.inf101.sem2;

import javax.swing.JFrame;

import no.uib.inf101.sem2.controller.RacingController;
import no.uib.inf101.sem2.model.RacingBoard;
import no.uib.inf101.sem2.model.RacingModel;
import no.uib.inf101.sem2.view.Racing2DView;

public class Main {
  public static final String WINDOW_TITLE = "Racing 2D";

  public static void main(String[] args) {
    RacingBoard racingBoard = new RacingBoard(80, 19);
    RacingModel racingModel = new RacingModel(racingBoard);
    RacingController racingController = new RacingController();
    Racing2DView racing2dView = new Racing2DView(racingModel, racingController);
    JFrame frame = new JFrame();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(racing2dView);
    frame.pack();
    frame.setTitle("Racing 2D");
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}