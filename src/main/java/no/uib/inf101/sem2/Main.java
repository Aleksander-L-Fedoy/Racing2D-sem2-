package no.uib.inf101.sem2;

import javax.swing.JFrame;

import no.uib.inf101.sem2.controller.RacingController;
import no.uib.inf101.sem2.model.RacingBoard;
import no.uib.inf101.sem2.model.RacingModel;
import no.uib.inf101.sem2.view.Racing2DView;

public class Main {
  public static final String WINDOW_TITLE = "Racing 2D";

  public static void main(String[] args) {
    RacingBoard racingBoard = new RacingBoard(80, 21);
    RacingModel racingModel = new RacingModel(racingBoard);
    Racing2DView racing2dView = new Racing2DView(racingModel);
    new RacingController(racingModel, racing2dView);
    JFrame frame = new JFrame();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(racing2dView);
    frame.pack();
    frame.setTitle(WINDOW_TITLE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}