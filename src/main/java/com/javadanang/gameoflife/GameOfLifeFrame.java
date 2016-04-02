package com.javadanang.gameoflife;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class GameOfLifeFrame extends JFrame implements ActionListener {
	private static final Dimension DEFAULT_WINDOW_SIZE = new Dimension(800, 600);
	private static final long serialVersionUID = -3256860122451629840L;
	private JMenuBar menuBar;

	private JButton buttonPlay;
	private JButton buttonStop;
	private JButton buttonReset;
	private JButton buttonReadMap;
	private GameOfLifePanel gameOfLifePanel;
	private GameOfLifeProcess gameOfLifeProcess;
	private Thread gameOfLifeThread;

	public GameOfLifeFrame() {
		initMenu();
		this.setSize(DEFAULT_WINDOW_SIZE);
		gameOfLifePanel = new GameOfLifePanel(DEFAULT_WINDOW_SIZE);
		add(gameOfLifePanel);
		gameOfLifeProcess = new GameOfLifeProcess(gameOfLifePanel);

	}

	private void initMenu() {
		menuBar = new JMenuBar();
		buttonPlay = new JButton("Play");
		buttonStop = new JButton("Stop");
		buttonReset = new JButton("Reset");
		buttonReadMap = new JButton("Load Map");
		menuBar.add(buttonPlay);
		menuBar.add(buttonStop);
		menuBar.add(buttonReset);
		menuBar.add(buttonReadMap);
		setJMenuBar(menuBar);
		buttonStop.setEnabled(false);
		buttonPlay.addActionListener(this);
		buttonStop.addActionListener(this);
		buttonReset.addActionListener(this);
		buttonReadMap.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource().equals(buttonPlay)) {
			performButtonPlayClicked(actionEvent);
		} else if (actionEvent.getSource().equals(buttonStop)) {
			performButtonStopClicked(actionEvent);
		} else if (actionEvent.getSource().equals(buttonReadMap)) {
			performButtonReadMapClicked(actionEvent);
		} else {
			performButtonResetClicked(actionEvent);
		}

	}

	private void stopGenerate() {
		if (gameOfLifeThread != null && gameOfLifeThread.isAlive()) {
			gameOfLifeThread.interrupt();
		}
		changeStateToStop();
	}

	private void performButtonPlayClicked(ActionEvent actionEvent) {
		changeStateToRunning();
		gameOfLifeThread = new Thread(gameOfLifeProcess);
		gameOfLifeThread.start();
	}

	private void performButtonResetClicked(ActionEvent actionEvent) {
		performButtonStopClicked(actionEvent);
		gameOfLifePanel.resetPanel();
	}

	private void changeStateToRunning() {
		buttonPlay.setEnabled(false);
		buttonStop.setEnabled(true);
	}

	private void changeStateToStop() {
		buttonPlay.setEnabled(true);
		buttonStop.setEnabled(false);
	}

	private void performButtonReadMapClicked(ActionEvent actionEvent) {
		stopGenerate();
		File map = chooseMapFile();
		if (map != null) {
			gameOfLifePanel.updateLivePoints(new ReadMap().readLivePoints(map));
		}

	}

	private void performButtonStopClicked(ActionEvent actionEvent) {
		changeStateToStop();
		gameOfLifeThread.interrupt();
	}

	private File chooseMapFile() {
		JFileChooser fileChooser = new JFileChooser();
		if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(this)) {
			return fileChooser.getSelectedFile();
		}

		return null;
	}
}
