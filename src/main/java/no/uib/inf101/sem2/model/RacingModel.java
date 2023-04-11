package no.uib.inf101.sem2.model;

public class RacingModel {
    private int obsticleCarYPos;
    private GameState gameState;
    private RacingBoard racingBoard;

    public RacingModel(RacingBoard racingBoard) {
        this.gameState = GameState.GAME_STARTED;
        this.racingBoard = racingBoard;
        this.obsticleCarYPos = -200;
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
        this.obsticleCarYPos = -200;
    }

    public int getRows() {
        return racingBoard.getRows();
    }

    public int getCols() {
        return racingBoard.getCols();
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getObsticleCarYPos() {
        return obsticleCarYPos;
    }

    public void setObsticleCarYPos(int obsticleCarYPos) {
        this.obsticleCarYPos = obsticleCarYPos;
    }

}