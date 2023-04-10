package no.uib.inf101.sem2.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

import no.uib.inf101.sem2.controller.RacingController;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.RacingModel;

public class Racing2DView extends JPanel implements ActionListener, java.awt.event.KeyListener {
  private Timer timer;
  private boolean checkHeight;
  private GameState gameState;
  private Graphics2D graphics2d;
  private Rectangle2D.Double box;
  private RacingModel racingModel;
  private double centerX, centerY;
  private long nextObstacleSpawnTime;
  private RacingController racingController;
  private String[][] tiles, backgroundTiles;
  private int obsticleCarXPos, obsticleCarYPos;
  private int x, y, rows, cols, width, height, sideMargin;
  private int score, highScore, gameOverFontSize, gameStartedFontSize;
  private final int delayMs = 10;
  private final int tileWidth = 32;
  private final BufferedImage car = Inf101Graphics.loadImageFromResources("/car.png");
  private final BufferedImage blueCar = Inf101Graphics.loadImageFromResources("/blue_car.png");
  private final BufferedImage roadTile = Inf101Graphics.loadImageFromResources("/road_tile_v2.png");
  private final BufferedImage grassTile = Inf101Graphics.loadImageFromResources("/grass_tile_v2.png");
  private final BufferedImage apexTile = Inf101Graphics.loadImageFromResources("/apex_tile_v2.png");
  private final BufferedImage yellowLaneSeperatorTile = Inf101Graphics
      .loadImageFromResources("/yellow_lane_seperator_v2.png");

  public Racing2DView(RacingModel racingModel, RacingController racingController) {
    this.racingModel = racingModel;
    this.gameState = racingModel.getGameState();
    this.checkHeight = false;
    this.racingController = racingController;
    this.rows = racingModel.getRows();
    this.cols = racingModel.getCols();
    this.width = 1200;
    this.height = 800;
    this.x = this.width / 2;
    this.y = this.height - this.tileWidth;
    this.tiles = racingModel.getTiles();
    this.backgroundTiles = racingModel.getBackgroundTiles();
    this.setFocusable(true);
    this.setPreferredSize(new Dimension(width, height));
    this.setBackground(Color.BLACK);
    this.addKeyListener(this);
    this.timer = new Timer(delayMs, this);
    this.timer.start();
    this.obsticleCarXPos = this.x;
    this.obsticleCarYPos = -200;
    this.nextObstacleSpawnTime = (long) (Math.random() * 2000) + 2000;

    racingController.setX(this.x);
  }

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    this.graphics2d = (Graphics2D) graphics;
    drawGame();
  }

  private void drawGame() {
    drawRoad(this.sideMargin, this.y);
    drawMainCar(x);
    drawObsticleCars();
    this.gameOverFontSize = (int) width / 8;
    this.gameStartedFontSize = (int) width / 10;
    this.centerX = this.getWidth() / 2;
    this.centerY = this.getHeight() / 2;
    this.box = new Rectangle2D.Double(0, 0, width, height);
    if (this.gameState == GameState.GAME_STARTED) {
      drawGameStarted();
    }
    if (this.gameState == GameState.GAME_OVER) {
      drawGameOver();
    }
  }

  private void drawRoad(int x, int y) {
    int sideMargin = (this.getWidth() - this.cols * this.tileWidth) / 2;
    for (int row = rows - 1; row > 0; row--) {
      for (int col = 0; col < cols; col++) {
        if (backgroundTiles[row][col] == "roadTile") {
          Inf101Graphics.drawImage(graphics2d, roadTile, x, y, 0.5);
        }
        if (backgroundTiles[row][col] == "grassTile") {
          Inf101Graphics.drawImage(graphics2d, grassTile, x, y, 0.5);
        }
        if (tiles[row][col] == "apexTile") {
          Inf101Graphics.drawImage(graphics2d, apexTile, x, y, 0.5);
        }
        if (tiles[row][col] == "yellowLaneSeperatorTile") {
          Inf101Graphics.drawImage(graphics2d, yellowLaneSeperatorTile, x, y, 0.5);
        }
        x += this.tileWidth;
      }
      y -= this.tileWidth;
      x = sideMargin;
    }
  }

  private void drawMainCar(int x) {
    int y = this.height / 2;
    Inf101Graphics.drawImage(graphics2d, car, x, y, 1);
  }

  private void drawObsticleCars() {
    int x = this.obsticleCarXPos;
    int y = this.obsticleCarYPos;
    Inf101Graphics.drawImage(graphics2d, blueCar, x, y, 1);
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    if (this.gameState == GameState.ACTIVE_GAME) {
      drawActiveGame();
    }
    repaint();
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
    Inf101Graphics.drawCenteredString(graphics2d, "Press", centerX, centerY - gameStartedFontSize);
    Inf101Graphics.drawCenteredString(graphics2d, "ENTER", centerX, centerY);
    Inf101Graphics.drawCenteredString(graphics2d, "to start", centerX, centerY + gameStartedFontSize);
  }

  private void drawActiveGame() {
    if (this.checkHeight == true)

    {
      this.height = this.getHeight();
    }
    this.sideMargin = (this.getWidth() - this.cols * this.tileWidth) / 2;
    this.y += this.tileWidth / 5;
    this.obsticleCarYPos += 8;
    if (this.y >= (this.height - this.tileWidth) * 2) {
      this.y = this.height - this.tileWidth;
      this.checkHeight = true;
    }

    nextObstacleSpawnTime -= delayMs;
    if (nextObstacleSpawnTime <= 0) {
      obsticleCarXPos = (int) (Math.random() * (this.getWidth() - 2 * this.sideMargin - 2 * tileWidth)) + sideMargin;
      obsticleCarYPos = -200;
      nextObstacleSpawnTime = (long) (Math.random() * 2000) + 2000;
    }

    if (this.y == this.obsticleCarYPos && this.x == this.obsticleCarXPos) {
      this.gameState = GameState.GAME_OVER;
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
    Inf101Graphics.drawCenteredString(graphics2d, "Game Over!", centerX, centerY);
    graphics2d.setFont(new Font("Arial", Font.BOLD, gameOverFontSize / 2));
    Inf101Graphics.drawCenteredString(graphics2d, "Press 'R' to restart", centerX, centerY + gameStartedFontSize);
  }

  @Override
  public void keyPressed(KeyEvent keyEvent) {
    racingController.setSideMargin(this.sideMargin);
    racingController.setWindowWidth(this.getWidth());
    racingController.keyPressed(keyEvent);
    this.x = racingController.getX();
    this.gameState = racingModel.getGameState();
    repaint();
  }

  /*---Methods to comply with interfaces---*/
  @Override
  public void keyReleased(KeyEvent keyEvent) {
  }

  @Override
  public void keyTyped(KeyEvent keyEvent) {
  }

}