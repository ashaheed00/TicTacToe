package com.bl.tictactoe;

import java.util.*;

public class TicTacToeGame {
	private char[] board = new char[10];

	public TicTacToeGame() {
		boardInitiate();
	}

	// Initiating the board
	private char[] boardInitiate() {
		Arrays.fill(board, ' ');
		return board;
	}

	// player chooses letter
	private char chooseLettter(char playerLetter) throws Exception {

		if (playerLetter == 'o' || playerLetter == 'x')
			return playerLetter;
		else
			throw new Exception("Choose only o or x");
	}

	// determines computer's letter based on player's
	private char computerLetter(char playerLetter) {
		return playerLetter == 'o' ? 'x' : 'o';
	}

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		TicTacToeGame ticTacToeGame = new TicTacToeGame();

		char playerLetter = sc.next().charAt(0);
		System.out.println("Player has choosen: " + ticTacToeGame.chooseLettter(playerLetter));
		System.out.println("Computer's letter : " + ticTacToeGame.computerLetter(playerLetter));

		sc.close();

	}

}