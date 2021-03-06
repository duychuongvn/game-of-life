package com.javadanang.gameoflife;

import java.awt.Point;
import java.util.ArrayList;

public class GameOfLifeProcess implements Runnable {

	private GameOfLifePanel gameOfLifePanel;

	public GameOfLifeProcess(GameOfLifePanel gameOfLifePanel) {
		this.gameOfLifePanel = gameOfLifePanel;
	}

	@Override
	public void run() {
		boolean[][] gameBoard = new boolean[gameOfLifePanel.getSize().width + 2][gameOfLifePanel.getSize().height + 2];
		for (Point current : gameOfLifePanel.getLivePoints()) {
			gameBoard[current.x + 1][current.y + 1] = true;
		}
		ArrayList<Point> survivingCells = new ArrayList<Point>(0);
		// Iterate through the array, follow game of life rules
		for (int i = 1; i < gameBoard.length - 1; i++) {
			for (int j = 1; j < gameBoard[0].length - 1; j++) {
				int surrounding = 0;
				if (gameBoard[i - 1][j - 1]) {
					surrounding++;
				}
				if (gameBoard[i - 1][j]) {
					surrounding++;
				}
				if (gameBoard[i - 1][j + 1]) {
					surrounding++;
				}
				if (gameBoard[i][j - 1]) {
					surrounding++;
				}
				if (gameBoard[i][j + 1]) {
					surrounding++;
				}
				if (gameBoard[i + 1][j - 1]) {
					surrounding++;
				}
				if (gameBoard[i + 1][j]) {
					surrounding++;
				}
				if (gameBoard[i + 1][j + 1]) {
					surrounding++;
				}
				if (gameBoard[i][j]) {
					// Cell is alive, Can the cell live? (2-3)
					if ((surrounding == 2) || (surrounding == 3)) {
						survivingCells.add(new Point(i - 1, j - 1));
					}
				} else {
					// Cell is dead, will the cell be given birth? (3)
					if (surrounding == 3) {
						survivingCells.add(new Point(i - 1, j - 1));
					}
				}
			}
		}
		gameOfLifePanel.updateLivePoints(survivingCells);
		try {
			Thread.sleep(500);
			run();
		} catch (InterruptedException e) {

		}
	}

}
