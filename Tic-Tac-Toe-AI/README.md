In this project we have game Tic Tac Toe, which have different modes to play.  
To start game we need to type "start * *". in place of the stars, you need to choose two players.  
There are four types of players : "user", "easy", "medium" and "hard".  
If you have selected a "user", you can choose the cells on which you want to put a cross or a zero.  
You can play with computer or you can play with another user, you can also watch how the computer plays with itself.  
  
Examples : "start user user", "start user hard", "start medium user", "start easy hard".

In the mode easy, computer choose cell with random.  
In the mode medium computer at each step checks ways. Firstly it checks if it can win, and if it can it make move.  
Secondly it checks if his opponent can win, and if he can, he make move to prevent the enemy.  
And if there are no such moves, then he makes a move using random.  

And the last mode, the hard mode.  
Minimax is a kind of backtracking algorithm that is used in decision making and game theory to find the optimal move for a player, assuming that your opponent also plays optimally.  
It is widely used in two player turn-based games such as Tic-Tac-Toe, Backgammon, Mancala, Chess, etc.  
In Minimax the two players are called maximizer and minimizer. The maximizer tries to get the highest score possible while the minimizer tries to do the opposite and get the lowest score possible.  
Every board state has a value associated with it. In a given state if the maximizer has upper hand then, the score of the board will tend to be some positive value. If the minimizer has the upper hand in that board state then it will tend to be some negative value. The values of the board are calculated by some heuristics which are unique for every type of game.  

In this project I use pattern Factory Method and Abstract Factory.

https://hyperskill.org/projects/81
