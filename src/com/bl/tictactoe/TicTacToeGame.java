package com.bl.tictactoe;

import java.util.*;

public class TicTacToeGame {
	private char[] board = new char[10];
	private char playerLetter;
	private final byte PLAYER = 0;
	private final byte COMPUTER = 1;
	private final int[][] WIN_POSITIONS = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 1, 4, 7 }, { 2, 5, 8 },
			{ 3, 6, 9 }, { 1, 5, 9 }, { 3, 5, 7 } };


	public TicTacToeGame() {
		boardInitiate();
	}

	// Initiating the board
	private char[] boardInitiate() {
		Arrays.fill(board, ' ');
		return board;
	}

	// Performing a toss to decide who plays first
	private void toss() {
		byte toss = (byte) (Math.random() * 10 % 2);
		if (toss == PLAYER) {
			System.out.println("Your are playing first.");
			chooseLettter();
		} else {
			playerLetter = 'x';
			System.out.println("Computer's turn first.");
		}
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

	// It'll show the board in the console
	public void showBoard() {
		System.out.println("***Board***");
		for (int i = 0; i <= 6; i += 3) {
			System.out.println(" " + board[i + 1] + " | " + board[i + 2] + " | " + board[i + 3]);
			if (i < 6)
				System.out.println("---+---+---");
		}
	}

	// Checks free spcae of a given index
	public boolean movePossible(int position) {
		return board[position] == ' ';
	}
	
	// Ask the player to give an index and moves there if possible
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

	// checking the winner providing their playing char
	public boolean checkWinner(char ch) {
		for (int[] win : WIN_POSITIONS) {
			if (board[win[0]] == ch && board[win[1]] == ch && board[win[2]] == ch)
				return true;
		}
		return false;
	}

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		TicTacToeGame ticTacToeGame = new TicTacToeGame();

		char playerLetter = sc.next().charAt(0);
		System.out.println("Player has choosen: " + ticTacToeGame.chooseLettter(playerLetter));
		System.out.println("Computer's letter : " + ticTacToeGame.computerLetter(playerLetter));

		ticTacToeGame.move();
		ticTacToeGame.showBoard();

		sc.close();

	}
}