package no.uib.inf101.sem2.controller;

import java.awt.event.KeyEvent;

import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.RacingModel;
import no.uib.inf101.sem2.view.Racing2DView;

public class RacingController implements java.awt.event.KeyListener {
    private RacingModel racingModel;
    private Racing2DView racing2dView;

    public RacingController(RacingModel racingModel, Racing2DView racing2dView) {
        this.racingModel = racingModel;
        this.racing2dView = racing2dView;
        racing2dView.addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        // if (racingModel.getGameState() == GameState.GAME_OVER) {
        // endedGame(keyEvent);
        // } else if (racingModel.getGameState() == GameState.GAME_STARTED) {
        // startedGame(keyEvent);
        // } else {
        // activeGame(keyEvent);
        // }
        activeGame(keyEvent);
        this.racing2dView.repaint();
    }

    /**
     * Handles key events when the game is over.
     *
     * @param keyEvent The key event that occurred.
     */
    private void endedGame(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_R) {
            racingModel.reset();
            racing2dView.repaint();
        }
    }

    /**
     * Handles key events when the game has started but is not yet active.
     *
     * @param keyEvent The key event that occurred.
     */
    private void startedGame(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
            this.racingModel.setGameStateToActive();
            System.out.println("Enter was pressed");
        }
    }

    private void activeGame(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                moveCar(-10);
                System.out.println("Left arrow was pressed");
                break;
            case KeyEvent.VK_RIGHT:
                moveCar(10);
                System.out.println("Right arrow was pressed");
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        System.out.println("Key released");
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        System.out.println("Key typed");
    }

    public void moveCar(int deltaX) {
        int newX = racing2dView.getX() + deltaX;
        racing2dView.drawCar(newX);
    }

}
