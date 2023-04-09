package no.uib.inf101.sem2.controller;

import java.awt.event.KeyEvent;

import no.uib.inf101.sem2.midi.SongHandler;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.RacingModel;

public class RacingController {
    private RacingModel racingModel;
    private int x, sideMargin, windowWidth;
    private SongHandler song;
    private final int CARWIDTH = 63;
    private final int OFFSET = 15;

    public RacingController(RacingModel racingModel) {
        this.racingModel = racingModel;
        this.song = new SongHandler();
        this.song.run();
    }

    public void keyPressed(KeyEvent keyEvent) {
        if (racingModel.getGameState() == GameState.GAME_OVER) {
            endedGame(keyEvent);
        } else if (racingModel.getGameState() == GameState.GAME_STARTED) {
            startedGame(keyEvent);
        } else {
            activeGame(keyEvent);
        }
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
            this.racingModel.setGameStateToActive();
        }
    }

    private void activeGame(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            if (noCollision(x - OFFSET)) {
                x -= OFFSET;
            }
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            if (noCollision(x + OFFSET)) {
                x += OFFSET;
            }
        }
    }

    private boolean noCollision(int x) {
        return x > sideMargin && x + CARWIDTH < windowWidth - sideMargin;
    }

    /*---Setters and getters---*/
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setSideMargin(int sideMargin) {
        this.sideMargin = sideMargin;
    }

    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }

}
