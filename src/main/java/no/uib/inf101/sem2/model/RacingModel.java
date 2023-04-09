package no.uib.inf101.sem2.model;

public class RacingModel {
    private GameState gameState;
    private RacingBoard racingBoard;

    public RacingModel(RacingBoard racingBoard) {
        this.gameState = GameState.GAME_STARTED;
        this.racingBoard = racingBoard;
    }

    public String[][] getBackgroundTiles() {
        return racingBoard.getBackgroundTiles();
    }

    public String[][] getTiles() {
        return racingBoard.getTiles();
    }

    /**
     * Returns the current state of the game.
     *
     * @return The current game state.
     */
    public GameState getGameState() {
        return this.gameState;
    }

    public void reset() {
        this.gameState = GameState.GAME_STARTED;
    }

    /**
     * Sets the game state to ACTIVE_GAME.
     */
    public void setGameStateToActive() {
        this.gameState = GameState.ACTIVE_GAME;
    }

    public int getRows() {
        return racingBoard.getRows();
    }

    public int getCols() {
        return racingBoard.getCols();
    }

}