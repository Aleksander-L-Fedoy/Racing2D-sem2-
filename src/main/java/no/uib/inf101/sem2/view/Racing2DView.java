package no.uib.inf101.sem2.view;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.RacingModel;

public class Racing2DView extends JPanel {
  private boolean checkHeight;
  private Graphics2D graphics2d;
  private RacingModel racingModel;
  private int mainCarXPos, obstacleCarXPos;
  private int tileYPos, screenWitdh, screenHeight, sideMargin;

  private final int ROWS, COLS;
  private final int TILEWIDTH = 32;
  private final Tile[][] tiles, backgroundTiles;
  private final Font SCORE_FONT = new Font("Arial", Font.BOLD, 14);
  private final Color SEMI_TRANSPARENT_BLACK = new Color(0, 0, 0, 128);
  private final BufferedImage MAINCAR = ViewHandler.loadImageFromResources("/car.png");
  private final BufferedImage BLUECAR = ViewHandler.loadImageFromResources("/blue_car.png");
  private final BufferedImage ROADTILE = ViewHandler.loadImageFromResources("/road_tile_v2.png");
  private final BufferedImage GRASSTILE = ViewHandler.loadImageFromResources("/grass_tile_v2.png");
  private final BufferedImage APEXTILE = ViewHandler.loadImageFromResources("/apex_tile_v2.png");
  private final BufferedImage LANESEPERATORTILE = ViewHandler
      .loadImageFromResources("/yellow_lane_seperator_v2.png");

  public Racing2DView(RacingModel racingModel) {
    this.racingModel = racingModel;

    this.screenWitdh = 1200;
    this.screenHeight = 800;
    this.checkHeight = false;

    this.ROWS = racingModel.getRows();
    this.COLS = racingModel.getCols();
    this.tiles = racingModel.getTiles();
    this.mainCarXPos = this.screenWitdh / 2;
    this.obstacleCarXPos = this.screenWitdh / 2;
    this.tileYPos = this.screenHeight - this.TILEWIDTH;
    this.backgroundTiles = racingModel.getBackgroundTiles();
    this.sideMargin = (this.screenWitdh - this.COLS * this.TILEWIDTH) / 2;

    this.setBackground(Color.BLACK);
    this.setFocusable(true);
    this.setPreferredSize(new Dimension(screenWitdh, screenHeight));
  }

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    this.graphics2d = (Graphics2D) graphics;
    drawGame();
  }

  /**
   * Draws the game components on the screen.
   * Calls the drawRoad, drawMainCar, drawObsticleCars, drawScore, and
   * drawGameStarted/drawGameOver methods
   * based on the game state.
   */
  private void drawGame() {
    drawRoad();
    drawMainCar();
    drawObsticleCars();
    drawScore();
    GameState gameState = racingModel.getGameState();
    if (gameState == GameState.GAME_STARTED) {
      drawGameStarted();
    } else if (gameState == GameState.GAME_OVER) {
      drawGameOver();
    }
  }

  /**
   * Helper method that draws the road tiles on the screen.
   */
  private void drawRoad() {
    int sideMargin = (this.getWidth() - this.COLS * this.TILEWIDTH) / 2;
    int x = sideMargin;
    int y = this.tileYPos;
    for (int row = ROWS - 1; row > 0; row--) {
      for (int col = 0; col < COLS; col++) {
        if (backgroundTiles[row][col] == Tile.ROADTILE) {
          ViewHandler.drawImage(graphics2d, ROADTILE, x, y, 0.5);
        }
        if (backgroundTiles[row][col] == Tile.GRASSTILE) {
          ViewHandler.drawImage(graphics2d, GRASSTILE, x, y, 0.5);
        }
        if (tiles[row][col] == Tile.APEXTILE) {
          ViewHandler.drawImage(graphics2d, APEXTILE, x, y, 0.5);
        }
        if (tiles[row][col] == Tile.LANESEPERATORTILE) {
          ViewHandler.drawImage(graphics2d, LANESEPERATORTILE, x, y, 0.5);
        }
        x += this.TILEWIDTH;
      }
      y -= this.TILEWIDTH;
      x = sideMargin;
    }
  }

  /**
   * Helper method that draws the main car on the screen.
   */
  private void drawMainCar() {
    int x = this.mainCarXPos;
    int y = this.screenHeight / 2;
    ViewHandler.drawImage(graphics2d, MAINCAR, x, y, 1);
  }

  /**
   * Helper method that draws the obstacle cars on the screen.
   */
  private void drawObsticleCars() {
    int x = this.obstacleCarXPos;
    int y = racingModel.getObstacleCarYPos();
    ViewHandler.drawImage(graphics2d, BLUECAR, x, y, 1);
  }

  /**
   * Helper method that draws the "Game Started" message with instructions on how
   * to start the game.
   */
  private void drawGameStarted() {
    int gameStartedFontSize = (int) screenWitdh / 10;
    int centerX = this.screenWitdh / 2;
    int centerY = this.screenHeight / 2;
    Rectangle2D box = new Rectangle2D.Double(0, 0, this.screenWitdh, this.screenHeight);
    graphics2d.setColor(SEMI_TRANSPARENT_BLACK);
    graphics2d.fill(box);
    graphics2d.setColor(Color.BLUE);
    graphics2d.setFont(new Font("Arial", Font.BOLD, gameStartedFontSize));
    ViewHandler.drawCenteredString(graphics2d, "Press", centerX, centerY - gameStartedFontSize);
    ViewHandler.drawCenteredString(graphics2d, "ENTER", centerX, centerY);
    ViewHandler.drawCenteredString(graphics2d, "to start", centerX, centerY + gameStartedFontSize);
  }

  /**
   * Helper method that updates the active game state.
   */
  public void updateActiveGame() {
    GameState gameState = racingModel.getGameState();
    if (gameState == GameState.ACTIVE_GAME) {
      this.sideMargin = (this.getWidth() - this.COLS * this.TILEWIDTH) / 2;
      this.tileYPos += this.TILEWIDTH / 4;
      racingModel.increaseObstacleCarYPos(this.TILEWIDTH / 5 - 2);
      tileBoardLoop();
      nextObstacleSpawnTimer();
      colisonDetector();
      if (this.checkHeight == true) {
        this.screenHeight = this.getHeight();
      }
      if (!racingModel.isScoreAllreadyUpdated()) {
        if (racingModel.getObstacleCarYPos() > this.screenHeight / 2 + MAINCAR.getHeight()) {
          racingModel.updateScore();
          racingModel.setScoreAllreadyUpdated(true);
        }
      }
    } else if (gameState == GameState.GAME_STARTED) {
      this.mainCarXPos = this.screenWitdh / 2;
      racingModel.setObstacleCarYPos(-200);
      racingModel.resetNextObstacleSpawnTime();
      racingModel.setLives(3);
      racingModel.setScore(0);
    }
  }

  /**
   * Helper method that loops the tile board if the bottom of the screen is
   * reached.
   */
  private void tileBoardLoop() {
    if (this.tileYPos >= (this.screenHeight - this.TILEWIDTH) * 2) {
      this.tileYPos = this.screenHeight - this.TILEWIDTH;
      this.checkHeight = true;
    }
  }

  /**
   * Helper method that sets a timer for spawning a new obstacle car.
   */
  private void nextObstacleSpawnTimer() {
    if (racingModel.spawnObstacleCar(this.screenHeight)) {
      obstacleCarXPos = (int) (Math.random() * (this.getWidth() - 2 * this.sideMargin - 2 * TILEWIDTH)) + sideMargin;
    }
  }

  /**
   * Helper method that detects a collision between the main car and the obstacle
   * car.
   */
  private void colisonDetector() {
    Double margin = 10D;
    Rectangle2D mainCar = new Rectangle2D.Double(this.mainCarXPos + margin, this.screenHeight / 2 + margin,
        MAINCAR.getWidth() - margin,
        MAINCAR.getHeight() - margin);
    Rectangle2D nextObstacleCar = new Rectangle2D.Double(this.obstacleCarXPos + margin,
        racingModel.getObstacleCarYPos() + margin,
        BLUECAR.getWidth() - margin, BLUECAR.getHeight() - margin);
    if (mainCar.intersects(nextObstacleCar)) {
      racingModel.reduceLives();
      racingModel.setNextObstacleSpawnTime(-100);
      racingModel.setObstacleCarYPos(this.screenHeight);
    }
  }

  /**
   * Helper method that draws the "Game Over" message with instructions on how to
   * restart the game.
   */
  private void drawGameOver() {
    int gameOverBigFontSize = (int) screenWitdh / 8;
    int gameOverSmallFontSize = (int) screenWitdh / 10;
    int centerX = this.screenWitdh / 2;
    int centerY = this.screenHeight / 2;
    Rectangle2D box = new Rectangle2D.Double(0, 0, this.screenWitdh, this.screenHeight);
    graphics2d.setColor(SEMI_TRANSPARENT_BLACK);
    graphics2d.fill(box);
    graphics2d.setColor(Color.RED);
    graphics2d.setFont(new Font("Arial", Font.BOLD, gameOverBigFontSize));
    ViewHandler.drawCenteredString(graphics2d, "Game Over!", centerX, centerY);
    graphics2d.setFont(new Font("Arial", Font.BOLD, gameOverBigFontSize / 2));
    ViewHandler.drawCenteredString(graphics2d, "Press 'R' to restart", centerX, centerY + gameOverSmallFontSize);
  }

  /**
   * Draws the game score, the highscore and lives on the screen.
   */
  private void drawScore() {
    graphics2d.setColor(Color.GREEN);
    graphics2d.setFont(SCORE_FONT);
    graphics2d.drawString("Highscore " + racingModel.getHighScore(), 20, 20);
    graphics2d.drawString("Score " + racingModel.getGameScore(), 20, 40);
    int lives = racingModel.getLives();
    String heartString;
    if (lives == 3) {
      heartString = "♥ ♥ ♥";
    } else if (lives == 2) {
      heartString = "♥ ♥";
    } else if (lives == 1) {
      heartString = "♥";
    } else {
      heartString = "";
    }
    graphics2d.drawString(heartString, 20, 60);
  }

  /*---Setters and getters---*/
  /**
   * Setter for the x position of the main car.
   * 
   * @param x the new x position of the main car
   */
  public void setMainCarXPos(int x) {
    this.mainCarXPos = x;
  }

  /**
   * Getter for the x position of the main car.
   * 
   * @return the x position of the main car
   */
  public int getMainCarXPos() {
    return mainCarXPos;
  }

  /**
   * Getter for the side margin.
   * 
   * @return the side margin
   */
  public int getSideMargin() {
    return sideMargin;
  }

}