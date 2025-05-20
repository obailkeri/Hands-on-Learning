Design a Snakes and Ladders game with the following:

Functional Requirements:
1. A board with size 10*10 (100 cells).
2. Snakes: going from a higher-numbered cell to a lower-numbered cell.
3. Ladders: going from a lower-numbered cell to a higher-numbered cell.
4. Multiple players take turns rolling a dice.
5. A player moves forward based on the dice roll.
6. If a player lands on a snake's head, they move down to the tail.
7. If a player lands on a ladder's bottom, they move up to the top.
8. First player to reach the final cell (100) wins.

Optional (Nice to Have):
1. Multiple dice.
2. Game replay.
3. Player names.
4. Custom board sizes.
-------------------------------------------------------------------------

IMPLEMENTATION:
-> The game logic resides in the Game class which uses all the other components.
-> Board holds special moves (Snake, Ladder) via a SpecialMove ArrayList.
-> Snake and Ladder extends a SpecialMove abstract class. (Extensible to add other special moves like Move to 2*curr position, or to a fixed position, etc..)
-> Dice is an interface, NormalDice is a concrete implementation. (Can extend to implement Biased Dice with high probability of even numbers, etc..)
-> Players are managed through a Queue<Player> for turns.
-> Winner tracking is done through an ArrayList<Player>.

-------------------------------------------------------------------------
