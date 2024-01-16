# KalahStandardStarter

This is a Java project developed by Moyan Pan in the COMPSCI 701 Course with two Design Patterns followed:
1. Command Pattern
2. Memento Pattern</br>
With strictly adhere to other principles of design & Object-oriented designs such as Coding Format(e.g.camel case), SOLID PRinciples and more.
To run this java project, add the environment packages - junit-3.8.2 jar and kalah-compsci701-a3-20210910.jar into the source path location in Classpath Configuration then run the project.


## README Contents

1. Functional
2. Repository Contents

## Functional Requirements

The details of the game (adapted from the Wikipedia article on Kalah) are as follows.

Kalah is a board game between two players. Each player has a set number of houses (default 6) and a store. Each house begins with a set number of seeds (default 4). The houses and stores (sometimes collectively called "pits") are arranged on a rectangular board, with the stores at each end and the houses along the edge. A player controls the houses closest to her and the store on her right. The object of the game is to capture more seeds than the opponent.

A move by a player consists of the player choosing one of her houses with seeds in it, and "sowing" all of those seeds on the board. Sowing consists of moving around the board in an anti-clockwise direction starting with the house following the one chosen, and placing 1 seed in each house until all the seeds from the original house have been sown. A seed is also sown in the player's store, but not the opponent's store. Possible outcomes of a move are:

The last seed is sown in a house that is either not owned by the player, or the house already contains at least one seed. In which case it is the other player's turn.
The last seed is sown in the player's store. In which case the same player gets another move. There is no limit to how often this can happen.
The last seed is sown in one of the player's own houses, that house was empty before that seed was sown in, and the opposite house owned by the opponent has at least one seed in it. In which case the seed just sown and all the seeds in the opposite house are moved to the player's store. This is a "capture". If the opposite house is empty, then no capture takes place.
The game ends when the player whose turn it is has no possible moves (that is, all houses owned by the player are empty). The score for a player is determined by adding all the seeds in that player's houses and store.

Your implementation must assume keyboard input (stdin) and ASCII console output (stdout). The output is presenting using ASCII characters. The houses for each player are numbered 1—6, with the number of seeds in each house in square brackets ("[","]") beside the house number. The houses for P1 are along the bottom, running left to right, and the houses for P2 are along the top. running right to left. P1's store is on the right and P2's store is on the left. A store has the label for the player the store belongs to and the number of seeds in it.

A player indicates their move by choosing the number of the house, or 'q' to end the game.

The specifics (format, exactly what happens when the game ends, and so on) can be found in the various test specification files provided.

An example of how one move might look like is:

+----+-------+-------+-------+-------+-------+-------+----+
| P2 | 6[ 5] | 5[ 1] | 4[ 2] | 3[ 3] | 2[ 4] | 1[ 0] | 14 |
|    |-------+-------+-------+-------+-------+-------|    |
|  4 | 1[ 5] | 2[ 5] | 3[ 2] | 4[ 0] | 5[ 0] | 6[ 3] | P1 |
+----+-------+-------+-------+-------+-------+-------+----+
prompt> Player 1's turn - Specify house number or 'q' to quit: 3
+----+-------+-------+-------+-------+-------+-------+----+
| P2 | 6[ 5] | 5[ 1] | 4[ 2] | 3[ 3] | 2[ 0] | 1[ 0] | 19 |
|    |-------+-------+-------+-------+-------+-------|    |
|  4 | 1[ 5] | 2[ 5] | 3[ 0] | 4[ 1] | 5[ 0] | 6[ 3] | P1 |
+----+-------+-------+-------+-------+-------+-------+----+
Player P1 chooses house 3, which has two seeds in it. One of those seeds is placed in house 4 (meaning there is now 1 seed in it) and the last seed in house 5. Since house 5 was empty before that seed was placed in it and the opposing house (P2's house 2) is empty, this is a "capture", and that seed, plus the 4 seeds in P2's house 2 are placed in P1's store (5 is added to P1's store).

Here is a longer example, with some moves starting from the beginning of the game.

+----+-------+-------+-------+-------+-------+-------+----+
| P2 | 6[ 4] | 5[ 4] | 4[ 4] | 3[ 4] | 2[ 4] | 1[ 4] |  0 |
|    |-------+-------+-------+-------+-------+-------|    |
|  0 | 1[ 4] | 2[ 4] | 3[ 4] | 4[ 4] | 5[ 4] | 6[ 4] | P1 |
+----+-------+-------+-------+-------+-------+-------+----+
Player P1's turn - Specify house number or 'q' to quit: 2
+----+-------+-------+-------+-------+-------+-------+----+
| P2 | 6[ 4] | 5[ 4] | 4[ 4] | 3[ 4] | 2[ 4] | 1[ 4] |  0 |
|    |-------+-------+-------+-------+-------+-------|    |
|  0 | 1[ 4] | 2[ 0] | 3[ 5] | 4[ 5] | 5[ 5] | 6[ 5] | P1 |
+----+-------+-------+-------+-------+-------+-------+----+
Player P2's turn - Specify house number or 'q' to quit: 3
+----+-------+-------+-------+-------+-------+-------+----+
| P2 | 6[ 5] | 5[ 5] | 4[ 5] | 3[ 0] | 2[ 4] | 1[ 4] |  0 |
|    |-------+-------+-------+-------+-------+-------|    |
|  1 | 1[ 4] | 2[ 0] | 3[ 5] | 4[ 5] | 5[ 5] | 6[ 5] | P1 |
+----+-------+-------+-------+-------+-------+-------+----+
Player P2's turn - Specify house number or 'q' to quit: 5
+----+-------+-------+-------+-------+-------+-------+----+
| P2 | 6[ 6] | 5[ 0] | 4[ 5] | 3[ 0] | 2[ 4] | 1[ 4] |  0 |
|    |-------+-------+-------+-------+-------+-------|    |
|  2 | 1[ 5] | 2[ 1] | 3[ 6] | 4[ 5] | 5[ 5] | 6[ 5] | P1 |
+----+-------+-------+-------+-------+-------+-------+----+
Player P1's turn - Specify house number or 'q' to quit: q
Game over
+----+-------+-------+-------+-------+-------+-------+----+
| P2 | 6[ 6] | 5[ 0] | 4[ 5] | 3[ 0] | 2[ 4] | 1[ 4] |  0 |
|    |-------+-------+-------+-------+-------+-------|    |
|  2 | 1[ 5] | 2[ 1] | 3[ 6] | 4[ 5] | 5[ 5] | 6[ 5] | P1 |
+----+-------+-------+-------+-------+-------+-------+----+

The functional requirements for the game are the same as for Assignment 1. There is, however, a change in the format of the prompt. This is needed to support the new features required. The prompt now looks like this:
<pre>
Player P1
    (1-6) - house number for move
    n - New game
    s - Save game
    l - Load game
    q - Quit
Choice:
</pre>
The new features do not affect the rules or presentation of the game.

As has been the case in the past, the details of how the implementation should
behave are given in the test specification files.

### New Game

At any point, either player can restart the game. When this happens, it is as if nothing has happened before this point. So it doesn't matter which player restarts it, P1 will have the first move, and no saves (see below) have taken place.

### Load/Save Game

At any point, either player can save the current state of the game. This will record the state of the board and whose turn it is. At some later point, either player can load the saved game. The game will then proceed from that point. The game can be saved multiple times, but the load will only restore from the most recent save. If a player restarts the game, any saved game is lost.

### VS Bot Player
In this project, it supports multiple players (max.2) against each other or vsing a bot player.If bot player, it has injected the algorithm which provides the capability to let the bot to make the best choice in an order of Capture > AnotherTurn > Move to increase the competitive ability and funability from the game.

## Repository Contents
3. `README.md` - this file (markdown) 
4. `resources` - directory containing:
    * `IO.html` - documentation for `IO` interface in test infrastructure
    * `kalah-compsci701-a3-20210910.jar` - `jar` file containing infrastructure, including test class and test specifications. Has to be on the classpath for compiling and testing
    * `test-ST-6-4-SK-H-H-SH-LSN` - directory containing the specifications for the tests for the original (horizontal, 2 human players) variant, as well as the load-save-new features. This is what is in the jar file, but has been unpacked for easy access.
    *  `junit-3.8.2.jar` - `jar` file for 3.8 JUnit. Has to be on classpath for testing.
5. `src/kalah` - directory containing:
    * `Kalah.java` - Stub class for Kalah set up to use test infrastructure. The CI will perform all of the testing by calling the `void play(IO)` method so this is what you need to implement.
