// package no.uib.inf101.sem2.controller;

// import java.awt.event.ActionEvent;
// import java.awt.event.KeyEvent;

// import javax.swing.Timer;

// import no.uib.inf101.sem2.view.Racing2DView;

// public class RacingController implements java.awt.event.KeyListener {
// private Racing2DView racing2dView;
// private Timer timer;

// public RacingController(Racing2DView racing2dView) {
// this.racing2dView = racing2dView;
// this.timer = new Timer(1000, this::clockTick);
// racing2dView.setFocusable(true);
// racing2dView.addKeyListener(this);
// this.timer.start();
// }

// @Override
// public void keyPressed(KeyEvent arg0) {
// // TODO Auto-generated method stub
// throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
// }

// @Override
// public void keyReleased(KeyEvent arg0) {
// // TODO Auto-generated method stub
// throw new UnsupportedOperationException("Unimplemented method
// 'keyReleased'");
// }

// @Override
// public void keyTyped(KeyEvent arg0) {
// // TODO Auto-generated method stub
// throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
// }

// public void clockTick(ActionEvent actionEvent) {
// this.racing2dView.repaint();
// }
// }
