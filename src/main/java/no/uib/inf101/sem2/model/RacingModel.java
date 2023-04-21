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

    public RacingModel(RacingBoard racingBoard) {
        this.RACINGBOARD = racingBoard;
        this.score = 0;
        this.lives = 3;
        this.obstacleCarYPos = -200;
        this.scoreAllreadyUpdated = false;
        this.gameState = GameState.GAME_STARTED;
    }

    /*---Game logic methods---*/
    /**
     * Resets the game state and the obstacle car position.
     */
    public void reset() {
        this.gameState = GameState.GAME_STARTED;
        this.obstacleCarYPos = -200;
    }

    /**
     * Increases the obstacle car's y position by the given deltaY.
     *
     * @param deltaY the amount to increase the obstacle car's y position
     */
    public void increaseObstacleCarYPos(int deltaY) {
        this.obstacleCarYPos += deltaY;
    }

    /**
     * Reduces the player's remaining lives by 1.
     * If the player has no lives remaining, sets the game state to GAME_OVER.
     */
    public void reduceLives() {
        this.lives--;
        if (this.lives == 0) {
            this.gameState = GameState.GAME_OVER;
        }
    }

    /**
     * Resets the time until the next obstacle car should spawn to a random value
     * between 2000 and 4000.
     */
    public void resetNextObstacleSpawnTime() {
        this.nextObstacleSpawnTime = (long) (Math.random() * 2000) + 2000;
    }

    /**
     * Spawns a new obstacle car if the time until the next spawn is 0 and there is
     * no obstacle car on the screen.
     * Resets the time until the next spawn and the score already updated flag if a
     * new obstacle car is spawned.
     *
     * @param screenHeight the height of the game screen
     * @return true if a new obstacle car is spawned, false otherwise
     */
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

    /**
     * Increases the player's score by 100 and updates the high score if the score
     * is higher than the current high score.
     */
    public void updateScore() {
        this.score += 100;
        if (score > HIGHSCOREHANDLER.getHighscore()) {
            HIGHSCOREHANDLER.setHighscore(score);
        }
    }

    /*---Setters and getters---*/
    /**
     * Returns the background tiles of the game board.
     *
     * @return a 2D array of Tile objects representing the background tiles
     */
    public Tile[][] getBackgroundTiles() {
        return RACINGBOARD.getBackgroundTiles();
    }

    /**
     * Returns the tiles of the game board.
     *
     * @return a 2D array of Tile objects representing the tiles
     */
    public Tile[][] getTiles() {
        return RACINGBOARD.getTiles();
    }

    /**
     * Returns the game state.
     *
     * @return the game state
     */
    public GameState getGameState() {
        return this.gameState;
    }

    /**
     * Returns the number of rows on the game board.
     *
     * @return the number of rows
     */
    public int getRows() {
        return RACINGBOARD.getRows();
    }

    /**
     * Returns the number of columns on the game board.
     *
     * @return the number of columns
     */
    public int getCols() {
        return RACINGBOARD.getCols();
    }

    /**
     * Sets the game state to the given state.
     *
     * @param gameState the new game state
     */
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * Returns the y position of the obstacle car.
     *
     * @return the y position of the obstacle car
     */
    public int getObstacleCarYPos() {
        return obstacleCarYPos;
    }

    /**
     * Sets the y position of the obstacle car.
     *
     * @param obsticleCarYPos the new y position of the obstacle car
     */
    public void setObstacleCarYPos(int obsticleCarYPos) {
        this.obstacleCarYPos = obsticleCarYPos;
    }

    /**
     * Sets the score to the given value.
     *
     * @param score the new score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Returns the player's current score.
     *
     * @return the player's score
     */
    public int getGameScore() {
        return this.score;
    }

    /**
     * Returns the high score.
     *
     * @return the high score
     */
    public int getHighScore() {
        return this.HIGHSCOREHANDLER.getHighscore();
    }

    /**
     * Returns the number of lives the player has remaining.
     *
     * @return the number of lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * Sets the number of lives the player has remaining.
     *
     * @param lives the new number of lives
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * Returns the time until the next obstacle car should spawn.
     *
     * @return the time until the next obstacle car should spawn
     */
    public long getNextObstacleSpawnTime() {
        return nextObstacleSpawnTime;
    }

    /**
     * Sets the time until the next obstacle car should spawn.
     *
     * @param nextObstacleSpawnTime the new time until the next obstacle car should
     *                              spawn
     */
    public void setNextObstacleSpawnTime(long nextObstacleSpawnTime) {
        this.nextObstacleSpawnTime = nextObstacleSpawnTime;
    }

    /**
     * Returns true if the score has already been updated during the current
     * obstacle car's lifetime, false otherwise.
     * 
     * @return true if the score has already been updated during the current
     *         obstacle car's lifetime, false otherwise
     */
    public boolean isScoreAllreadyUpdated() {
        return scoreAllreadyUpdated;
    }

    /**
     * Sets the scoreAllreadyUpdated flag to the given value.
     * 
     * @param scoreAllreadyUpdated the new value of the scoreAllreadyUpdated flag
     */
    public void setScoreAllreadyUpdated(boolean scoreAllreadyUpdated) {
        this.scoreAllreadyUpdated = scoreAllreadyUpdated;
    }

}