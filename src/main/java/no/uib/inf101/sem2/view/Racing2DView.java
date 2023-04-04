package no.uib.inf101.sem2.view;

// Denne klassen er bare et eksempel, og bør slettes når du begynner å
// komme i gang med ditt eget program.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import no.uib.inf101.sem2.model.RacingBoard;

/**
 * A sample view to get you inspired for your own project.
 */
public class Racing2DView extends JPanel {
  private int ROWS = 26;
  private int COLS = 19;
  private int yCordinate = 0;
  private double width;
  private double height;
  private Rectangle2D.Double box;
  private final double MARGIN = 2;
  private final BufferedImage roadTile = Inf101Graphics.loadImageFromResources("/road_tile_v2.png");
  private final BufferedImage grassTile = Inf101Graphics.loadImageFromResources("/grass_tile_v2.png");
  private final BufferedImage apexTile = Inf101Graphics.loadImageFromResources("/apex_tile_v2.png");
  private final BufferedImage yellowLaneSeperatorTile = Inf101Graphics
      .loadImageFromResources("/yellow_lane_seperator_v2.png");
  private final RacingBoard racingBoard = new RacingBoard(ROWS, COLS);
  private final String[][] tiles = racingBoard.getTiles();
  private final String[][] backgroundTiles = racingBoard.getBackgroundTiles();

  public Racing2DView() {
    this.setFocusable(true);
    this.setPreferredSize(new Dimension(1200, 800));
    this.setBackground(Color.BLACK);
  }

  @Override
  public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    Graphics2D graphics2d = (Graphics2D) graphics;
    drawGame(graphics2d);
  }

  public void drawGame(Graphics2D graphics2d) {
    double sideMargin = this.getWidth() / 4;
    this.width = this.getWidth() - 2 * sideMargin;
    this.height = this.getHeight() - 2 * MARGIN;
    this.box = new Rectangle2D.Double(sideMargin, MARGIN, width, height);

    graphics2d.setColor(Color.CYAN);
    graphics2d.fill(box);

    int x = (int) sideMargin;
    int y = (int) MARGIN;
    drawRoad(x, y, graphics2d);
  }

  public void drawRoad(int x, int y, Graphics2D graphics2d) {
    int sideMargin = x;
    for (int row = 1; row < ROWS; row++) {
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

  public static void wait(int ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
  }

}