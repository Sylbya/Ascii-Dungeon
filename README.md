# Ascii-Dungeon

I have solved an assignment (Exercise 33) from the MOOC Object-Oriented programming with Java, part II




Assignment Summary

In this exercise, you implement a dungeon game. In the game, the player is in a dungeon full of vampires. The player has to destroy the vampires before his lamp runs out of battery and the vampires can suck his blood in the darkness. The player can see the vampires with a blinking of their lamp, after which they have to move blind before the following blinking. With one move, the player can walk as many steps as they want. The game situation, i.e. the dungeon, the player and the vampires are shown in text form. The first line in the print output tells how many moves the player has left (that is to say, how much battery the lamp has). After that, the print output shows player and vampire positions, which in turn are followed by the game map.
The coordinates are calculated starting from the high left corner of the game board. The user can move by giving a sequence of commands and pressing Enter. The commands are:

w go up
s go down
a go left
d go right
When the user commands are executed (the user can give many commands at once), a new game situation is drawn. If the lamp charge reac hes 0, the game ends and the text YOU LOSE is printed on the board.

The vampires move randomly in the game, and they take one step for each step the player takes. If the player and a vampire run into each other (even momentarily) the vampire is destroyed. If a vampire tries to step outside the board, or into a place already occupied by another vampire, the move is not executed. When all the vampires are destroyed, the game ends and it prints YOU WIN. The player starts the game in the position 0,0 Player and vampires can not move out of the dungeon and two vampires cannot step into the same place
