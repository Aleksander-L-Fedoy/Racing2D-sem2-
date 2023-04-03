package no.uib.inf101.sem2.view;

// Denne klassen er bare et eksempel, og bør slettes når du begynner å
// komme i gang med ditt eget program.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 * A sample view to get you inspired for your own project.
 */
public class Racing2DView extends JPanel {
  private int score;
  private int highscore;
  private int gameOverFontSize;
  private int gameStartedFontSize;
  private double width;
  private double height;
  private double centerX;
  private double centerY;
  private Rectangle2D.Double box;
  private final double MARGIN = 2;

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

  /**
   * Draws the game by rendering the cells on the board, the tetromino falling on
   * the board, the game score and the game over message if applicable.
   *
   * @param graphics2d the Graphics2D object used to draw the game
   */
  public void drawGame(Graphics2D graphics2d) {
    double sideMargin = this.getWidth() / 4;
    this.width = this.getWidth() - 2 * sideMargin;
    this.height = this.getHeight() - 2 * MARGIN;
    this.gameOverFontSize = (int) width / 6;
    this.gameStartedFontSize = (int) width / 8;
    this.centerX = (this.getWidth() - 2 * MARGIN) / 2;
    this.centerY = (this.getHeight() - 2 * MARGIN) / 2;
    this.box = new Rectangle2D.Double(sideMargin, MARGIN, width, height);

    graphics2d.setColor(Color.GREEN);
    graphics2d.fill(box);
  }

}