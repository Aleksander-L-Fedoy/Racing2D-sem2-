package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.view.Tile;

public class RacingBoard {
    private final int rows, cols;
    private final Tile[][] tiles, backgroundTiles;

    public RacingBoard(int cols) throws IllegalArgumentException {
        if (cols < 5) {
            throw new IllegalArgumentException("Column size must be at least 5");
        } else {
            this.rows = 80;
            this.cols = cols;
            this.backgroundTiles = new Tile[rows][cols];
            this.tiles = new Tile[rows][cols];

            initializeBackground();
            initializeTiles();
        }
    }

    private boolean initializeBackground() {
        try {
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (col == 0 || col + 1 == cols) {
                        this.backgroundTiles[row][col] = Tile.GRASSTILE;
                    } else {
                        this.backgroundTiles[row][col] = Tile.ROADTILE;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean initializeTiles() {
        try {
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (col == 1 || col + 2 == cols) {
                        this.tiles[row][col] = Tile.APEXTILE;
                    }
                    if (col == cols / 2 && row % 2 == 0) {
                        this.tiles[row][col] = Tile.LANESEPERATORTILE;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /*---Getters---*/
    public Tile[][] getBackgroundTiles() {
        return backgroundTiles;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
