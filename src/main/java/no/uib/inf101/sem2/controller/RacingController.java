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
    /**
     * Helper method to handle starting the game.
     * If the user presses the Enter key, the game state is set to ACTIVE_GAME.
     *
     * @param keyEvent the KeyEvent object representing the key that was pressed
     */
    private void startedGame(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
            this.RACINGMODEL.setGameState(GameState.ACTIVE_GAME);
        }
    }

    /**
     * Helper method to handle the game being in progress.
     * If the user presses the left or right arrow key, the main car's position is
     * updated if there is no collision.
     *
     * @param keyCode the key code of the key that was pressed
     */
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

    /**
     * Helper method to handle the game ending.
     * If the user presses the R key, the game is reset.
     *
     * @param keyEvent the KeyEvent object representing the key that was pressed
     */
    private void endedGame(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_R) {
            RACINGMODEL.reset();
        }
    }

    /**
     * Helper method to check if the main car would collide with the side walls if
     * moved to the given position.
     *
     * @param nextMainCarXPos the x-position of the main car if moved to the given
     *                        position
     * @return true if there is no collision, false otherwise
     */
    private boolean noCollision(int nextMainCarXPos) {
        return nextMainCarXPos > sideMargin && nextMainCarXPos + CARWIDTH < windowWidth - sideMargin;
    }

    /**
     * Helper method to handle the timer tick.
     * Updates the window size and side margin variables, and calls the appropriate
     * methods based on the game state.
     *
     * @param event the ActionEvent object representing the timer tick
     */
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
