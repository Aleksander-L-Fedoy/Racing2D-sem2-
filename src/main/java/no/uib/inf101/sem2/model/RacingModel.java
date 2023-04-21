package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.view.Tile;

public class RacingModel {
    private GameState gameState;
    private long nextObstacleSpawnTime;
    private boolean scoreAllreadyUpdated;
    private int obstacleCarYPos, score, lives;

    private final int DELAY_MS = 10;
    private final RacingBoard RACINGBOARD;
    private final HighScoreHandler HIGHSCOREHANDLER = new HighScoreHandler();

    // Constructor
    public RacingModel(RacingBoard racingBoard) {
        this.RACINGBOARD = racingBoard;
        this.score = 0;
        this.lives = 3;
        this.obstacleCarYPos = -200;
        this.scoreAllreadyUpdated = false;
        this.gameState = GameState.GAME_STARTED;
    }

    // Game logic methods
    public void reset() {
        this.gameState = GameState.GAME_STARTED;
        this.obstacleCarYPos = -200;
    }

    public void increaseObstacleCarYPos(int deltaY) {
        this.obstacleCarYPos += deltaY;
    }

    public void reduceLives() {
        this.lives--;
        if (this.lives == 0) {
            this.gameState = GameState.GAME_OVER;
        }
    }

    public void resetNextObstacleSpawnTime() {
        this.nextObstacleSpawnTime = (long) (Math.random() * 2000) + 2000;
    }

    public boolean spawnObstacleCar(int screenHeight) {
        nextObstacleSpawnTime -= DELAY_MS;
        if (nextObstacleSpawnTime <= 0 && this.obstacleCarYPos > screenHeight) {
            resetNextObstacleSpawnTime();
            this.obstacleCarYPos = -200;
            this.scoreAllreadyUpdated = false;
            return true;
        }
        return false;
    }

    public void updateScore() {
        this.score += 100;
        if (score > HIGHSCOREHANDLER.getHighscore()) {
            HIGHSCOREHANDLER.setHighscore(score);
        }
    }

    // Setters and getters
    public Tile[][] getBackgroundTiles() {
        return RACINGBOARD.getBackgroundTiles();
    }

    public Tile[][] getTiles() {
        return RACINGBOARD.getTiles();
    }

    public GameState getGameState() {
        return this.gameState;
    }

    public int getRows() {
        return RACINGBOARD.getRows();
    }

    public int getCols() {
        return RACINGBOARD.getCols();
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getObstacleCarYPos() {
        return obstacleCarYPos;
    }

    public void setObstacleCarYPos(int obsticleCarYPos) {
        this.obstacleCarYPos = obsticleCarYPos;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getGameScore() {
        return this.score;
    }

    public int getHighScore() {
        return this.HIGHSCOREHANDLER.getHighscore();
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public long getNextObstacleSpawnTime() {
        return nextObstacleSpawnTime;
    }

    public void setNextObstacleSpawnTime(long nextObstacleSpawnTime) {
        this.nextObstacleSpawnTime = nextObstacleSpawnTime;
    }

    public boolean isScoreAllreadyUpdated() {
        return scoreAllreadyUpdated;
    }

    public void setScoreAllreadyUpdated(boolean scoreAllreadyUpdated) {
        this.scoreAllreadyUpdated = scoreAllreadyUpdated;
    }

}