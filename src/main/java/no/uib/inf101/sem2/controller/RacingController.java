package no.uib.inf101.sem2.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import no.uib.inf101.sem2.midi.SongHandler;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.RacingModel;
import no.uib.inf101.sem2.view.Racing2DView;

public class RacingController implements java.awt.event.KeyListener {
    private final int OFFSET = 8;
    private final int CARWIDTH = 63;
    private final RacingModel RACINGMODEL;
    private final Racing2DView RACING2DVIEW;
    private final SongHandler SONG = new SongHandler();
    private final Timer TIMER = new Timer(10, this::clockTick);

    private int mainCarXPos, sideMargin, windowWidth, activeKey;

    public RacingController(RacingModel racingModel, Racing2DView racing2DView) {
        this.SONG.run();
        this.TIMER.start();

        this.activeKey = -1;
        this.windowWidth = 1200;
        this.RACINGMODEL = racingModel;
        this.RACING2DVIEW = racing2DView;
        this.mainCarXPos = windowWidth / 2;

        this.RACING2DVIEW.addKeyListener(this);

        this.sideMargin = racing2DView.getSideMargin();
    }

    /*--- KeyListener methods ---*/
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (RACINGMODEL.getGameState() == GameState.GAME_STARTED) {
            this.mainCarXPos = RACING2DVIEW.getMainCarXPos();
            startedGame(keyEvent);
        }
        if (RACINGMODEL.getGameState() == GameState.GAME_OVER) {
            endedGame(keyEvent);
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT || keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            activeKey = keyEvent.getKeyCode();
        }
    }

    /*--- Methods to comply with super class ---*/
    @Override
    public void keyReleased(KeyEvent arg0) {
        this.activeKey = -1;
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }
    /*--- Ends here ---*/

    /*--- Private helper methods ---*/
    private void startedGame(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
            this.RACINGMODEL.setGameState(GameState.ACTIVE_GAME);
        }
    }

    private void activeGame(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) {
            if (noCollision(mainCarXPos - OFFSET)) {
                mainCarXPos -= OFFSET;
            }
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            if (noCollision(mainCarXPos + OFFSET)) {
                mainCarXPos += OFFSET;
            }
        }
        RACING2DVIEW.setMainCarXPos(mainCarXPos);
    }

    private void endedGame(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_R) {
            RACINGMODEL.reset();
        }
    }

    private boolean noCollision(int nextMainCarXPos) {
        return nextMainCarXPos > sideMargin && nextMainCarXPos + CARWIDTH < windowWidth - sideMargin;
    }

    private void clockTick(ActionEvent event) {
        this.windowWidth = RACING2DVIEW.getWidth();
        this.sideMargin = RACING2DVIEW.getSideMargin();
        if (RACINGMODEL.getGameState() == GameState.ACTIVE_GAME) {
            activeGame(activeKey);
        }
        RACING2DVIEW.updateActiveGame();
        RACING2DVIEW.repaint();
    }
}
