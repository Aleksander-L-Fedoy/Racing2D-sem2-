package no.uib.inf101.sem2.model;

public class RacingBoard {
    private final int rows;
    private final int cols;
    private final String grassTile = "grassTile";
    private final String roadTile = "roadTile";
    private final String apexTile = "apexTile";
    private final String yellowLaneSeperatorTile = "yellowLaneSeperatorTile";
    private String[][] backgroundTiles;
    private String[][] tiles;

    public RacingBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.backgroundTiles = new String[rows][cols];
        this.tiles = new String[rows][cols];

        initializeBackground();
        initializeTiles();
    }

    private boolean initializeBackground() {
        try {
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (col == 0 || col + 1 == cols) {
                        this.backgroundTiles[row][col] = grassTile;
                    } else {
                        this.backgroundTiles[row][col] = roadTile;
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
                        this.tiles[row][col] = apexTile;
                    }
                    if (col == cols / 2 && row % 2 == 0) {
                        this.tiles[row][col] = yellowLaneSeperatorTile;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public String[][] getBackgroundTiles() {
        return backgroundTiles;
    }

    public String[][] getTiles() {
        return tiles;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
