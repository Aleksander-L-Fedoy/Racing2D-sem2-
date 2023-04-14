package no.uib.inf101.sem2.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import no.uib.inf101.sem2.midi.SongHandler;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.RacingModel;
import no.uib.inf101.sem2.view.Racing2DView;

public class RacingController implements java.awt.event.KeyListener {
    private RacingModel racingModel;
    private int x, sideMargin, windowWidth;
    private SongHandler song;
    private Timer moveTimer, frameTimer;
    private Racing2DView racing2DView;
    private final int CARWIDTH = 63;
    private final int OFFSET = 10;
    private int activeKey = -1;

    public RacingController(RacingModel racingModel, Racing2DView racing2DView) {
        this.moveTimer = new Timer(20, this::moveClockTick);
        this.moveTimer.start();
        this.frameTimer = new Timer(10, this::clockTick);
        this.frameTimer.start();
        this.racingModel = racingModel;
        this.racing2DView = racing2DView;
        this.song = new SongHandler();
        this.song.run();
        this.racing2DView.addKeyListener(this);
        this.windowWidth = 1200;
        this.x = windowWidth / 2;
        this.sideMargin = racing2DView.getSideMargin();
        System.out.println(sideMargin);
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (racingModel.getGameState() == GameState.GAME_STARTED) {
            startedGame(keyEvent);
        }
        if (racingModel.getGameState() == GameState.GAME_OVER) {
            endedGame(keyEvent);
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT || keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            activeKey = keyEvent.getKeyCode();
        }
    }

    /**
     * Handles key events when the game has started but is not yet active.
     *
     * @param keyEvent The key event that occurred.
     */
    private void startedGame(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
            this.racingModel.setGameState(GameState.ACTIVE_GAME);
        }
    }

    private void activeGame(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) {
            if (noCollision(x - OFFSET)) {
                x -= OFFSET;
            }
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            if (noCollision(x + OFFSET)) {
                x += OFFSET;
            }
        }
        racing2DView.setX(x);
    }

    /**
     * Handles key events when the game is over.
     *
     * @param keyEvent The key event that occurred.
     */
    private void endedGame(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_R) {
            racingModel.reset();
        }
    }

    private boolean noCollision(int x) {
        return x > sideMargin && x + CARWIDTH < windowWidth - sideMargin;
    }

    private void moveClockTick(ActionEvent event) {
        if (racingModel.getGameState() == GameState.ACTIVE_GAME) {
            activeGame(activeKey);
        }
    }

    private void clockTick(ActionEvent event) {
        this.windowWidth = racing2DView.getWidth();
        this.sideMargin = racing2DView.getSideMargin();
        racing2DView.activeGame();
        racing2DView.repaint();
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        this.activeKey = -1;
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }

}
