package no.uib.inf101.sem2.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

import no.uib.inf101.sem2.controller.RacingController;
import no.uib.inf101.sem2.model.RacingModel;

public class Racing2DView extends JPanel implements ActionListener, java.awt.event.KeyListener {
  private Timer timer;
  private Graphics2D graphics2d;
  private RacingModel racingModel;
  private boolean checkHeight = false;
  private RacingController racingController;
  private String[][] tiles, backgroundTiles;
  private int x, y, rows, cols, width, height, sideMargin;
  private final int delayMs = 10;
  private final int tileWidth = 32;
  private final BufferedImage car = Inf101Graphics.loadImageFromResources("/car.png");
  private final BufferedImage roadTile = Inf101Graphics.loadImageFromResources("/road_tile_v2.png");
  private final BufferedImage grassTile = Inf101Graphics.loadImageFromResources("/grass_tile_v2.png");
  private final BufferedImage apexTile = Inf101Graphics.loadImageFromResources("/apex_tile_v2.png");
  private final BufferedImage yellowLaneSeperatorTile = Inf101Graphics
      .loadImageFromResources("/yellow_lane_seperator_v2.png");

  public Racing2DView(RacingModel racingModel, RacingController racingController) {
    this.racingModel = racingModel;
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
    this.sideMargin = (this.getWidth() - this.cols * this.tileWidth) / 2;
    this.timer = new Timer(delayMs, this);
    this.timer.start();

    racingController.setX(this.x);
  }

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    this.graphics2d = (Graphics2D) graphics;
    drawRoad(this.sideMargin, this.y);
    drawCar(x);
  }

  private void drawRoad(int x, int y) {
    int sideMargin = x;
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

  public void drawCar(int x) {
    int y = this.height / 2;
    Inf101Graphics.drawImage(graphics2d, car, x, y, 1);
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    if (this.checkHeight) {
      this.height = this.getHeight();
    }
    this.sideMargin = (this.getWidth() - this.cols * this.tileWidth) / 2;
    this.y += this.tileWidth / 5;
    if (this.y >= (this.height - this.tileWidth) * 2) {
      this.y = this.height - this.tileWidth;
      this.checkHeight = true;
    }
    repaint();
  }

  @Override
  public void keyPressed(KeyEvent keyEvent) {
    racingController.setSideMargin(this.sideMargin);
    racingController.setWindowWidth(this.getWidth());
    racingController.keyPressed(keyEvent);
    this.x = racingController.getX();
    repaint();
  }

  @Override
  public void keyReleased(KeyEvent keyEvent) {
  }

  @Override
  public void keyTyped(KeyEvent keyEvent) {
  }

}