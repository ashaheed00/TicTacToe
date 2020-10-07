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

	// Giving user a chance to choose preferred letter
	private char chooseLettter() {
		System.out.println("Enter your preferred letter (o or x): ");
		playerLetter = sc.next().charAt(0);
		if (playerLetter == 'o' || playerLetter == 'x')
			return playerLetter;
		else {
			System.err.println("Wrong input. Taking 'o' as your choice.");
			return playerLetter = 'o';
		}
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