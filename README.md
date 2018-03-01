// Author: Samuel Saint-Fleur, Aden Li
// Course : ITI1121 University of Ottawa
// Assignment: 2

Welcome to Minesweeper!

This is a Java built game where the goal of the game is to select and uncover slots without accidentally uncovering a mine!
The game randomly generates mines on the field, so even the first slot you uncover could be a mine. Numbers on opened slots tell you how many mines are neighbour to your current slot, that being a maximum of eight neighbours.
Blank spots automatically uncover any neighbours around itself, until the neighbours have mined neighbours.

It is possible to change the size of the board by passing arguments when calling the application in command line, such that:

			(Width of the board, Height of the board, number of Mines)
#User:>java Minesweeper 50 50 100

*The number of mines cannot be greater than the full size of the board.*

