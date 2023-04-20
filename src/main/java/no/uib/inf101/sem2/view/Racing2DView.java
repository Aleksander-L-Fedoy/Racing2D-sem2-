package no.uib.inf101.sem2.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.RacingModel;

public class Racing2DView extends JPanel {
  private boolean checkHeight, scoreAllreadyUpdated;
  private GameState gameState;
  private Graphics2D graphics2d;
  private Rectangle2D.Double box;
  private RacingModel racingModel;
  private double centerX, centerY;
  private long nextObstacleSpawnTime;
  private String[][] tiles, backgroundTiles;
  private int obsticleCarXPos, obsticleCarYPos;
  private int x, y, rows, cols, width, height, sideMargin;
  private int score, highscore, gameOverFontSize, gameStartedFontSize;
  private final int delayMs = 10;
  private final int tileWidth = 32;
  private final BufferedImage car = ViewHandler.loadImageFromResources("/car.png");
  private final BufferedImage blueCar = ViewHandler.loadImageFromResources("/blue_car.png");
  private final BufferedImage roadTile = ViewHandler.loadImageFromResources("/road_tile_v2.png");
  private final BufferedImage grassTile = ViewHandler.loadImageFromResources("/grass_tile_v2.png");
  private final BufferedImage apexTile = ViewHandler.loadImageFromResources("/apex_tile_v2.png");
  private final BufferedImage yellowLaneSeperatorTile = ViewHandler
      .loadImageFromResources("/yellow_lane_seperator_v2.png");

  public Racing2DView(RacingModel racingModel) {
    this.racingModel = racingModel;
    this.gameState = racingModel.getGameState();
    this.checkHeight = false;
    this.scoreAllreadyUpdated = false;
    this.rows = racingModel.getRows();
    this.cols = racingModel.getCols();
    this.width = 1200;
    this.height = 800;
    this.x = this.width / 2;
    this.y = this.height - this.tileWidth;
    this.tiles = racingModel.getTiles();
    this.sideMargin = (this.width - this.cols * this.tileWidth) / 2;
    this.backgroundTiles = racingModel.getBackgroundTiles();
    this.setFocusable(true);
    this.setPreferredSize(new Dimension(width, height));
    this.setBackground(Color.BLACK);
    this.obsticleCarXPos = (int) (Math.random() * (this.getWidth() - 2 * this.sideMargin - 2 * tileWidth)) + sideMargin;
    this.obsticleCarYPos = -200;
    this.nextObstacleSpawnTime = (long) (Math.random() * 2000) + 2000;
  }

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    this.graphics2d = (Graphics2D) graphics;
    drawGame();
  }

  public void drawGame() {
    drawRoad(this.sideMargin, this.y);
    drawMainCar(this.x);
    drawObsticleCars();
    drawScore();
    this.gameOverFontSize = (int) width / 8;
    this.gameStartedFontSize = (int) width / 10;
    this.centerX = this.getWidth() / 2;
    this.centerY = this.getHeight() / 2;
    this.box = new Rectangle2D.Double(0, 0, width, height);
    if (this.gameState == GameState.GAME_STARTED) {
      drawGameStarted();
    } else if (this.gameState == GameState.GAME_OVER) {
      drawGameOver();
    }
  }

  private void drawRoad(int x, int y) {
    int sideMargin = (this.getWidth() - this.cols * this.tileWidth) / 2;
    for (int row = rows - 1; row > 0; row--) {
      for (int col = 0; col < cols; col++) {
        if (backgroundTiles[row][col] == "roadTile") {
          ViewHandler.drawImage(graphics2d, roadTile, x, y, 0.5);
        }
        if (backgroundTiles[row][col] == "grassTile") {
          ViewHandler.drawImage(graphics2d, grassTile, x, y, 0.5);
        }
        if (tiles[row][col] == "apexTile") {
          ViewHandler.drawImage(graphics2d, apexTile, x, y, 0.5);
        }
        if (tiles[row][col] == "yellowLaneSeperatorTile") {
          ViewHandler.drawImage(graphics2d, yellowLaneSeperatorTile, x, y, 0.5);
        }
        x += this.tileWidth;
      }
      y -= this.tileWidth;
      x = sideMargin;
    }
  }

  private void drawMainCar(int x) {
    int y = this.height / 2;
    ViewHandler.drawImage(graphics2d, car, x, y, 1);
  }

  private void drawObsticleCars() {
    int x = this.obsticleCarXPos;
    int y = this.obsticleCarYPos;
    ViewHandler.drawImage(graphics2d, blueCar, x, y, 1);
  }

  /**
   * Draws the "Game Started" message with instructions on how to start the game.
   * 
   * @param graphics2d the Graphics2D object used to draw the "Game Started"
   *                   message
   */
  private void drawGameStarted() {
    graphics2d.setColor(new Color(0, 0, 0, 128));
    graphics2d.fill(box);
    graphics2d.setColor(Color.BLUE);
    graphics2d.setFont(new Font("Arial", Font.BOLD, gameStartedFontSize));
    ViewHandler.drawCenteredString(graphics2d, "Press", centerX, centerY - gameStartedFontSize);
    ViewHandler.drawCenteredString(graphics2d, "ENTER", centerX, centerY);
    ViewHandler.drawCenteredString(graphics2d, "to start", centerX, centerY + gameStartedFontSize);
  }

  public void activeGame() {
    this.gameState = racingModel.getGameState();
    if (this.gameState == GameState.ACTIVE_GAME) {
      if (this.checkHeight == true) {
        this.height = this.getHeight();
      }
      this.sideMargin = (this.getWidth() - this.cols * this.tileWidth) / 2;
      this.y += this.tileWidth / 4;
      this.obsticleCarYPos += this.tileWidth / 5 - 2;
      resetYPosGate();
      nextObstacleSpawnTimer();
      colisonDetector();
      if (this.scoreAllreadyUpdated == false) {
        if (this.obsticleCarYPos > this.height / 2 + car.getHeight()) {
          racingModel.updateScore();
          scoreAllreadyUpdated = true;
        }
      }
    } else if (this.gameState == GameState.GAME_STARTED) {
      this.x = this.width / 2;
      obsticleCarYPos = -200;
      nextObstacleSpawnTime = (long) (Math.random() * 2000) + 2000;
    }
  }

  private void resetYPosGate() {
    if (this.y >= (this.height - this.tileWidth) * 2) {
      this.y = this.height - this.tileWidth;
      this.checkHeight = true;
    }
  }

  private void nextObstacleSpawnTimer() {
    nextObstacleSpawnTime -= delayMs;
    if (nextObstacleSpawnTime <= 0) {
      obsticleCarXPos = (int) (Math.random() * (this.getWidth() - 2 * this.sideMargin - 2 * tileWidth)) + sideMargin;
      obsticleCarYPos = -200;
      nextObstacleSpawnTime = (long) (Math.random() * 2000) + 2000;
      scoreAllreadyUpdated = false;
    }
  }

  private void colisonDetector() {
    Double margin = 10D;
    Rectangle2D mainCar = new Rectangle2D.Double(this.x + margin, this.height / 2 + margin, car.getWidth() - margin,
        car.getHeight() - margin);
    Rectangle2D nextObstacleCar = new Rectangle2D.Double(this.obsticleCarXPos + margin, this.obsticleCarYPos + margin,
        blueCar.getWidth() - margin, blueCar.getHeight() - margin);
    if (mainCar.intersects(nextObstacleCar)) {
      racingModel.setGameState(GameState.GAME_OVER);
    }
  }

  /**
   * Draws the "Game Over" message with instructions on how to restart the game.
   * 
   * @param graphics2d the Graphics2D object used to draw the "Game Over" message
   */
  private void drawGameOver() {
    graphics2d.setColor(new Color(0, 0, 0, 128));
    graphics2d.fill(box);
    graphics2d.setColor(Color.RED);
    graphics2d.setFont(new Font("Arial", Font.BOLD, gameOverFontSize));
    ViewHandler.drawCenteredString(graphics2d, "Game Over!", centerX, centerY);
    graphics2d.setFont(new Font("Arial", Font.BOLD, gameOverFontSize / 2));
    ViewHandler.drawCenteredString(graphics2d, "Press 'R' to restart", centerX, centerY + gameStartedFontSize);
  }

  /**
   * Draws the game score and the highscore on the screen.
   *
   * @param graphics2d the Graphics2D object used to draw the game score and
   *                   highscore
   */
  private void drawScore() {
    this.score = racingModel.gameScore();
    this.highscore = racingModel.highScore();
    graphics2d.setColor(Color.GREEN);
    graphics2d.setFont(new Font("Arial", Font.BOLD, 14));
    graphics2d.drawString("Highscore " + this.highscore, 20, 20);
    graphics2d.drawString("Score " + this.score, 20, 40);
  }

  /*---Setters and getters---*/
  public void setX(int x) {
    this.x = x;
  }

  public int getXvalue() {
    return x;
  }

  public int getSideMargin() {
    return sideMargin;
  }

}