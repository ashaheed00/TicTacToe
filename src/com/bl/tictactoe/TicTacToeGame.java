package com.bl.tictactoe;

import java.util.*;
import java.util.stream.Stream;

public class TicTacToeGame {
	private char[] board = new char[10];
	private char playerLetter;
	private final byte PLAYER = 0;
	private final byte COMPUTER = 1;
	private byte activePlayer;
	private final int[][] WIN_POSITIONS = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 1, 4, 7 }, { 2, 5, 8 },
			{ 3, 6, 9 }, { 1, 5, 9 }, { 3, 5, 7 } };
	private final Map<Integer, Integer> availableMoves = new HashMap<>();

	public TicTacToeGame() {
		boardInitiate();
		Stream.iterate(1, i -> i + 1).limit(9).forEach(i -> availableMoves.put(i, i));
	}

	// Initiating the board
	private char[] boardInitiate() {
		Arrays.fill(board, ' ');
		board[0] = 'z'; // ignoring 0th index
		return board;
	}

	// Performing a toss to decide who plays first
	public void toss() {
		byte toss = (byte) (Math.random() * 10 % 2);
		if (toss == PLAYER) {
			activePlayer = PLAYER;
			System.out.println("Your are playing first.");
			chooseLettter();
		} else {
			activePlayer = COMPUTER;
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
	private char computerLetter() {
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

	// Checks free space of a given index
	private boolean movePossible(int position) {
		return board[position] == ' ';
	}

	// player places her/his move
	private void playerMove() {
		System.out.println("Enter your next move(1 to 9): ");
		int position = sc.nextInt();
		try {
			if (movePossible(position) && position >= 1 && position <= 9) {
				board[position] = playerLetter;
				showBoard();
				availableMoves.remove(position);
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
	private void computerMove() {
		int computerPosition = (int) (Math.random() * 10 % 9) + 1;
		computerPosition = moveToCorner(computerPosition);
		computerPosition = predictsWinOrBlockMove(computerPosition, playerLetter);
		computerPosition = predictsWinOrBlockMove(computerPosition, computerLetter());

		if (movePossible(computerPosition)) {
			board[computerPosition] = computerLetter();
			showBoard();
			availableMoves.remove(computerPosition);
			System.out.println("Your available moves: " + availableMoves);
			return;
		} else {
			computerMove();
			return;
		}
	}

	// checking whether the board is filled or not
	private boolean boardNotFilled() {
		for (char ch : board)
			if (ch == ' ')
				return true;

		return false;
	}

	// Predicts next position to win or to block the opponent
	private int predictsWinOrBlockMove(int position, char ch) {
		for (int p : availableMoves.keySet()) {
			if (p == 1) {
				if (board[2] + board[3] == ch * 2 || board[4] + board[7] == ch * 2)
					return p;
			}
			if (p == 2) {
				if (board[1] + board[3] == ch * 2 || board[5] + board[8] == ch * 2)
					return p;
			}
			if (p == 3) {
				if (board[1] + board[2] == ch * 2 || board[6] + board[9] == ch * 2 || board[5] + board[7] == ch * 2)
					return p;
			}
			if (p == 2) {
				if (board[1] + board[3] == ch * 2 || board[5] + board[8] == ch * 2)
					return p;
			}
			if (p == 4) {
				if (board[1] + board[7] == ch * 2 || board[5] + board[6] == ch * 2)
					return p;
			}
			if (p == 5) {
				if (board[1] + board[9] == ch * 2 || board[4] + board[6] == ch * 2 || board[2] + board[8] == ch * 2
						|| board[3] + board[7] == ch * 2)
					return p;
			}
			if (p == 6) {
				if (board[4] + board[5] == ch * 2 || board[3] + board[9] == ch * 2)
					return p;
			}
			if (p == 7) {
				if (board[1] + board[4] == ch * 2 || board[9] + board[8] == ch * 2 || board[3] + board[5] == ch * 2)
					return p;
			}
			if (p == 8) {
				if (board[2] + board[5] == ch * 2 || board[7] + board[9] == ch * 2)
					return p;
			}
			if (p == 9) {
				if (board[1] + board[5] == ch * 2 || board[7] + board[8] == ch * 2 || board[3] + board[6] == ch * 2)
					return p;
			}
		}

		return position;
	}

	// checking the winner providing their playing char
	private boolean checkWinner(char ch) {
		for (int[] win : WIN_POSITIONS) {
			if (board[win[0]] == ch && board[win[1]] == ch && board[win[2]] == ch)
				return true;
		}
		return false;
	}

	/* It places the to corner if it's available */
	private int moveToCorner(int position) {
		for (int p : availableMoves.keySet()) {
			if (p == 1 || p == 3 || p == 7 || p == 9)
				return p;
		}
		return position;
	}

	// play happens until board is filled
	// or a player wins
	public void play() {
		while (boardNotFilled()) {
			if (activePlayer == PLAYER) {
				playerMove();
				activePlayer = COMPUTER;
				if (checkWinner(playerLetter)) {
					System.out.println("You are the winner!!");
					return;
				}
			} else {
				computerMove();
				activePlayer = PLAYER;
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