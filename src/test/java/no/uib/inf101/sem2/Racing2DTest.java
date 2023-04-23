package no.uib.inf101.sem2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.RacingBoard;
import no.uib.inf101.sem2.model.RacingModel;
import no.uib.inf101.sem2.view.Tile;

public class Racing2DTest {
    private RacingBoard racingBoard;
    private RacingModel racingModel;

    @BeforeEach
    void setUp() throws Exception {
        racingBoard = new RacingBoard(10);
        racingModel = new RacingModel(racingBoard);
    }

    @Test
    void testGetRows() {
        assertEquals(80, racingModel.getRows());
    }

    @Test
    void testGetCols() {
        assertEquals(10, racingModel.getCols());
    }

    @Test
    void testGetTiles() {
        Tile[][] tiles = racingModel.getTiles();
        assertEquals(80, tiles.length);
        assertEquals(10, tiles[0].length);
    }

    @Test
    void testGetBackgroundTiles() {
        Tile[][] tiles = racingModel.getBackgroundTiles();
        assertEquals(80, tiles.length);
        assertEquals(10, tiles[0].length);
    }

    @Test
    void testGetObstacleCarYPos() {
        assertEquals(-200, racingModel.getObstacleCarYPos());
    }

    @Test
    void testIncreaseObstacleCarYPos() {
        racingModel.increaseObstacleCarYPos(10);
        assertEquals(-190, racingModel.getObstacleCarYPos());
    }

    @Test
    void testUpdateScore() {
        racingModel.setScore(0);
        assertEquals(0, racingModel.getGameScore());
        racingModel.updateScore();
        assertEquals(100, racingModel.getGameScore());
    }

    @Test
    void testSetNextObstacleSpawnTime() {
        racingModel.setNextObstacleSpawnTime(100);
        assertEquals(100, racingModel.getNextObstacleSpawnTime());
    }

    @Test
    void testIsScoreAllreadyUpdated() {
        assertFalse(racingModel.isScoreAllreadyUpdated());
        racingModel.setScoreAllreadyUpdated(true);
        assertTrue(racingModel.isScoreAllreadyUpdated());
    }

    @Test
    void testResetNextObstacleSpawnTime() {
        racingModel.setNextObstacleSpawnTime(100);
        assertEquals(100, racingModel.getNextObstacleSpawnTime());
        racingModel.resetNextObstacleSpawnTime();
        assertTrue(racingModel.getNextObstacleSpawnTime() > 2000);
    }

    @Test
    void testReduceLives() {
        racingModel.reduceLives();
        assertEquals(2, racingModel.getLives());
    }

    @Test
    void testSetLives() {
        racingModel.setLives(3);
        assertEquals(3, racingModel.getLives());
    }

    @Test
    void testSetScore() {
        racingModel.setScore(100);
        assertEquals(100, racingModel.getGameScore());
    }

    @Test
    void testSetScoreAllreadyUpdated() {
        racingModel.setScoreAllreadyUpdated(true);
        assertTrue(racingModel.isScoreAllreadyUpdated());
    }

    @Test
    void testGetGameState() {
        assertEquals(GameState.GAME_STARTED, racingModel.getGameState());
        racingModel.setGameState(GameState.ACTIVE_GAME);
        assertEquals(GameState.ACTIVE_GAME, racingModel.getGameState());
    }

    @Test
    void testRacingBoardThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new RacingBoard(4));
        String expectedMessage = "The number of columns in the racing board must be at least 5.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}
