# Manual Unit-tests for Racing2D

## Test 1 – Test car movement (left/right)

### Goal:

- Verify that the car moves left and right using arrow keys.

### Method:

Before all: Launch game and press enter.

- Press left arrow and verify that the player moves left.
- Press right arrow and verify that the player moves right.

### Result:

- Pass ✅

## Test 2 – Test background moves

### Goal:

- Verify that the background moves (making it look like the car is moving forward)

### Method:

Before all: Launch game and press enter.

- Let the game run for a half a minute or so.
- Verify that the background moves without any obvious glitches.

### Result:

- Pass ✅

## Test 3 – Test Game Started Screen is showing when launching the game.

### Goal:

- Verify that the game start screen is shown when the game starts.

### Method:

Before all: Launch game.

- Verify that the game start screen is showing.

### Result:

- Pass ✅

## Test 4 – Test collsion with side terrain

### Goal:

- Verify that it is not possible to move the car outside of the road.

### Method:

Before all: Launch game and press enter.

- Hold down the left arrow for a few seconds.
- Verify that the car stops moving left before it is outside the road.
- Hold down the right arrow for a few seconds.
- Verify that the car stops moving right before it is outside the road.

### Result:

- Pass ✅

## Test 5 – Test obstacles spawn

### Goal:

- Verify that the obstacles spawn

### Method:

Before all: Launch game and press enter.

- Play the game for a few seconds.
- Verify that obstacles (blue car) spawns.

### Result:

- Pass ✅

## Test 6 – Verify object collision

### Goal:

- Verify that the car colides/interacts with objects on screen as intended

### Method:

Before all: Launch game and press enter.

- Play the game for a few seconds.
- When a blue car has spawn drive the main car into it.
- Verify that the blue car dissapears and the remaining lives reduces.

### Result:

- Pass ✅

## Test 7 – Verify death and game over screen

### Goal:

- Verify that the player dies when supposed to and correct game over screen shows.

### Method:

Before each: Launch game and press enter.

- Play the game and colide with the blue car three times.
- Verify that the lives remmaing reduces and the game over screen appears.

### Result:

- Pass ✅

## Test 8 – Verify right soundtrack is playing

### Goal:

- Verify that the soundtrack ‘blur_song_2.mid’ plays after starting the game.

### Method:

- Launch game and verify that the soundtrack ‘blur_song_2.mid’ plays while on the game started screen.
- Press enter and verify that the soundtrack continues to play while in game.

### Result:

- Pass ✅

## Test 9 – Verify right graphics

### Goal:

- Verify that the right graphical components render correctly.

### Method:

- Launch game and verify that the graphical components are rendering correctly on game started screen:
  -- Background: the road and main car but dimmed
  -- Text: "Press enter to start the game"
- Press enter and verify that the graphical components are rendering correctly on game screen:
  -- Background: the road
  -- Objects: main (orange) car is displayed at all times and blue car intermittend
  -- Game info: highscore, score and remaining lives are shown in upper left corner
- Die and verify that the graphical components are rendering correctly on game over screen:
  -- Background: the road and main car but dimmed
  -- Text: "Game over! Press 'R' to restart the game"

### Result:

- Pass ✅

## Test 10 – Verify looping of map

### Goal:

- Verify that the map is infinite, where the road keeps on going and obsticales keep spawning.

### Method:

Before all: Launch game and press enter.

- Play the game for some amount of time until it is obvious that it is infinite.

### Result:

- Pass ✅
