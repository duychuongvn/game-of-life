package com.javadanang.gameoflife;

import javax.swing.JFrame;

public class Main {
	

	public static void main(String[] args) {
		GameOfLifeFrame gameOfLifeFrame = new GameOfLifeFrame();
		gameOfLifeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameOfLifeFrame.setTitle("Danang Java Community - CodeRetreat 2015");
		gameOfLifeFrame.setVisible(true);
	}
}
