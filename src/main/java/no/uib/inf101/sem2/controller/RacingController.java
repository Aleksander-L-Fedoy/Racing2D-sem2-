package no.uib.inf101.sem2.controller;

import java.awt.event.KeyEvent;

import no.uib.inf101.sem2.midi.SongHandler;
import no.uib.inf101.sem2.model.GameState;

public class RacingController {
    private int x;
    private SongHandler song;

    public RacingController() {
        this.song = new SongHandler();
        this.song.run();
    }

    public void keyPressed(KeyEvent keyEvent) {
        // if (racingModel.getGameState() == GameState.GAME_OVER) {
        // endedGame(keyEvent);
        // } else if (racingModel.getGameState() == GameState.GAME_STARTED) {
        // startedGame(keyEvent);
        // } else {
        // activeGame(keyEvent);
        // }
        activeGame(keyEvent);
    }

    /**
     * Handles key events when the game is over.
     *
     * @param keyEvent The key event that occurred.
     */
    private void endedGame(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_R) {
            // racingModel.reset();
        }
    }

    /**
     * Handles key events when the game has started but is not yet active.
     *
     * @param keyEvent The key event that occurred.
     */
    private void startedGame(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
            // this.racingModel.setGameStateToActive();
        }
    }

    private void activeGame(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                this.x -= 10;
                break;
            case KeyEvent.VK_RIGHT:
                this.x += 10;
                break;
            default:
                break;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

}