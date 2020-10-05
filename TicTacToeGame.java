package com.bl.tictactoe;

import java.util.*;

public class TicTacToeGame {
	private char[] board = new char[10];
	private char playerLetter;

	public TicTacToeGame() {

	}

	private char[] boardInitiate() {
		Arrays.fill(board, ' ');
		return board;
	}

	private char chooseLettter() throws Exception {
		System.out.println("Enter your preferred letter (o or x): ");
		playerLetter = sc.next().charAt(0);
		if (playerLetter == 'o' || playerLetter == 'x')
			return playerLetter;
		else {
			playerLetter = ' ';
			throw new Exception("Wrong input. Choose only o or x");
		}
	}

	private char computerLetter() {
		return playerLetter == 'o' ? 'x' : 'o';
	}

	public void showBoard() {
		for (int i = 0; i <= 6; i += 3) {
			System.out.println(" " + board[i + 1] + " | " + board[i + 2] + " | " + board[i + 3]);
			if (i < 6)
				System.out.println("---+---+---");
		}
	}

	public boolean movePossible(int position) {
		return board[position] == ' ';
	}

	public void move() {
		System.out.println("Enter your position(1 to 9): ");
		int position = sc.nextInt();
		try {
			if (movePossible(position) && position >= 1 && position <= 9)
				board[position] = playerLetter;
			else {
				System.err.println("Already occupies the position. Try again.");
				move();
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Enter between 1 to 9");
			move();
		}

	}

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		TicTacToeGame ticTacToeGame = new TicTacToeGame();
		ticTacToeGame.boardInitiate();

		try {
			System.out.println("Player has chosen: " + ticTacToeGame.chooseLettter());
		} catch (Exception e) {
			System.err.println("Wrong input. Choose again: ");
			ticTacToeGame.chooseLettter();
		}
		System.out.println("Computer's letter : " + ticTacToeGame.computerLetter());
		ticTacToeGame.move();
		sc.close();

	}

}