package com.bl.tictactoe;

import java.util.*;

public class TicTacToeGame {
	private char[] board = new char[10];
	private char playerLetter;
	private final byte PLAYER = 0;
	private final byte COMPUTER = 1;
	private byte activePlayer;
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
	
	// player places her/his move
	public void playerMove() {
		System.out.println("Enter your next move(1 to 9): ");
		int position = sc.nextInt();
		try {
			if (movePossible(position) && position >= 1 && position <= 9) {
				board[position] = playerLetter;
				showBoard();
			} else {
				System.err.println("Already occupies the position. Try again.");
				playerMove();
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Enter between 1 to 9");
			playerMove();
		}
	}

	// computer places its move
	public void computerMove() {
		int computerPosition = (int) (Math.random() * 10 % 9) + 1;
		if (movePossible(computerPosition)) {
			board[computerPosition] = computerLetter();
			showBoard();
			return;
		} else {
			computerMove();
			return;
		}
	}

	// checking whether the board is filled or not
	public boolean boardNotFilled() {
		for (char ch : board)
			if (ch == ' ')
				return true;

		return false;
	}

	// checking the winner providing their playing char
	public boolean checkWinner(char ch) {
		for (int[] win : WIN_POSITIONS) {
			if (board[win[0]] == ch && board[win[1]] == ch && board[win[2]] == ch)
				return true;
		}
		return false;
	}

	// play happens until board is filled
	// or a player wins
	public void play() {
		while (boardNotFilled()) {
			if (activePlayer == 0) {
				playerMove();
				activePlayer = 1;
				if (checkWinner(playerLetter)) {
					System.out.println("You are the winner!!");
					return;
				}
			} else {
				computerMove();
				activePlayer = 0;
				if (checkWinner(computerLetter())) {
					System.out.println("Oops!! Computer has won.");
					return;
				}
			}
		}
		System.out.println("Game is a tie.");
	}

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		TicTacToeGame ticTacToeGame = new TicTacToeGame();

		ticTacToeGame.toss();
		ticTacToeGame.play();

		sc.close();

	}
}