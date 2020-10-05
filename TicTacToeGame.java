package com.bl.tictactoe;

import java.util.*;

public class TicTacToeGame {
	private char[] board = new char[10];

	public TicTacToeGame() {

	}

	private char[] boardInitiate() {
		Arrays.fill(board, ' ');
		return board;
	}

	private char chooseLettter(char playerLetter) throws Exception {

		if (playerLetter == 'o' || playerLetter == 'x')
			return playerLetter;
		else
			throw new Exception("Choose only o or x");
	}

	private char computerLetter(char playerLetter) {
		return playerLetter == 'o' ? 'x' : 'o';
	}

	public static void main(String[] args) throws Exception {
		TicTacToeGame ticTacToeGame = new TicTacToeGame();
		Scanner sc = new Scanner(System.in);

		char playerLetter = sc.next().charAt(0);
		System.out.println("Player has choosen: " + ticTacToeGame.chooseLettter(playerLetter));
		System.out.println("Computer's letter : " + ticTacToeGame.computerLetter(playerLetter));

		sc.close();

	}

}