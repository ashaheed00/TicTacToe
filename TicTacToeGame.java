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

	public void showBoard() {
		System.out.println(" " + board[1] + " | " + board[2] + " | " + board[3]);
		System.out.println("---+---+---");
		System.out.println(" " + board[4] + " | " + board[5] + " | " + board[6]);
		System.out.println("---+---+---");
		System.out.println(" " + board[7] + " | " + board[8] + " | " + board[9]);

	}

	public static void main(String[] args) throws Exception {
		TicTacToeGame ticTacToeGame = new TicTacToeGame();
		Scanner sc = new Scanner(System.in);

		char playerLetter = sc.next().charAt(0);
		try {
			System.out.println("Player has chosen: " + ticTacToeGame.chooseLettter(playerLetter));
		} catch (Exception e) {
			System.err.println("Wrong input. Choose only o or x");
		}
		System.out.println("Computer's letter : " + ticTacToeGame.computerLetter(playerLetter));
		ticTacToeGame.boardInitiate();
		ticTacToeGame.showBoard();
		sc.close();

	}

}