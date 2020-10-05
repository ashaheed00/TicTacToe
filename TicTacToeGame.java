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

	private void toss() {
		byte toss = (byte) (Math.random() * 10 % 2);
		if (toss == 0) {
			System.out.println("Your are playing first.");
			chooseLettter();
		} else {
			playerLetter = 'x';
			System.out.println("Computer's turn first.");
		}
	}

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
			if (movePossible(position) && position >= 1 && position <= 9) {
				board[position] = playerLetter;
				showBoard();
			} else {
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

		ticTacToeGame.toss();
		ticTacToeGame.move();

		sc.close();
	}

}