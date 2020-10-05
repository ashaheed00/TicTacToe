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

	public static void main(String[] args) {
		TicTacToeGame ticTacToeGame = new TicTacToeGame();

	}

}
