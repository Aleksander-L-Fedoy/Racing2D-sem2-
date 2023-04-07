package no.uib.inf101.sem2.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

import no.uib.inf101.sem2.model.RacingBoard;

public class Racing2DView extends JPanel implements ActionListener {
  private int rows = 26;
  private final int COLS = 19;
  private final int WIDTH = 1200;
  private final int HEIGHT = 800;
  private int sideMargin;
  private int x = 0;
  private int y = 0;
  private int delayMs = 100;
  private Timer timer;
  private final BufferedImage car = Inf101Graphics.loadImageFromResources("/car.png");
  private final BufferedImage roadTile = Inf101Graphics.loadImageFromResources("/road_tile_v2.png");
  private final BufferedImage grassTile = Inf101Graphics.loadImageFromResources("/grass_tile_v2.png");
  private final BufferedImage apexTile = Inf101Graphics.loadImageFromResources("/apex_tile_v2.png");
  private final BufferedImage yellowLaneSeperatorTile = Inf101Graphics
      .loadImageFromResources("/yellow_lane_seperator_v2.png");
  private final RacingBoard racingBoard = new RacingBoard(rows, COLS);
  private final String[][] tiles = racingBoard.getTiles();
  private final String[][] backgroundTiles = racingBoard.getBackgroundTiles();

  public Racing2DView() {
    this.setFocusable(true);
    this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    this.setBackground(Color.BLACK);
    this.sideMargin = (this.getWidth() - COLS * 32) / 2;
    this.timer = new Timer(delayMs, this);
    this.timer.start();
  }

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    Graphics2D graphics2d = (Graphics2D) graphics;
    drawRoad(sideMargin, y, graphics2d);
    drawCar(x, graphics2d);
  }

  private void drawRoad(int x, int y, Graphics2D graphics2d) {
    int sideMargin = x;
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < COLS; col++) {
        System.out.println(tiles[row][col]);
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
        x += 32;
      }
      y += 32;
      x = sideMargin;
    }
  }

  private void drawCar(int x, Graphics2D graphics2d) {
    int y = this.getHeight() / 2;
    Inf101Graphics.drawImage(graphics2d, car, x, y, 1);
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    this.rows = this.getHeight() / 32;
    sideMargin = (this.getWidth() - COLS * 32) / 2;
    x++;
    y++;
    repaint();
  }

}