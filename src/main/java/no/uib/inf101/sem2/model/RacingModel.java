package no.uib.inf101.sem2.model;

public class RacingModel {
    private int obsticleCarYPos;
    private GameState gameState;
    private RacingBoard racingBoard;
    private int score;
    private HighScoreHandler highScoreHandler;

    public RacingModel(RacingBoard racingBoard) {
        this.gameState = GameState.GAME_STARTED;
        this.racingBoard = racingBoard;
        this.obsticleCarYPos = -200;
        this.score = 0;
        this.highScoreHandler = new HighScoreHandler();
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

    /**
     * Returns the current game score.
     *
     * @return The game score.
     */
    public int gameScore() {
        return this.score;
    }

    /**
     * Returns the high score.
     *
     * @return The high score.
     */
    public int highScore() {
        return this.highScoreHandler.getHighscore();
    }

    public void updateScore() {
        this.score += 1000;
        if (score > highScoreHandler.getHighscore()) {
            highScoreHandler.setHighscore(score);
        }
    }
}