package com.javadanang.gameoflife;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.swing.JPanel;

public class GameOfLifePanel extends JPanel implements GameOfLifeEventListener {

	private static final long serialVersionUID = 6305747250569708030L;
	private static final int CELL_SIZE = 20;
	private Dimension panelDimension;
	private ArrayList<Point> livePoints = new ArrayList<>();

	public GameOfLifePanel(Dimension panelDimension) {
		this.panelDimension = panelDimension;
		addComponentListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public ArrayList<Point> getLivePoints() {
		return livePoints;
	}

	public void resetPanel() {
		livePoints.clear();
		repaint();
	}
	public void updateLivePoints(ArrayList<Point> newLivePoints) {
		livePoints.clear();
		livePoints.addAll(newLivePoints);
		repaint();
	}

	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		drawLiveCell(graphics);
		drawGrid(graphics);
	}

	private void drawGrid(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		for (int i = 0; i <= panelDimension.width; i++) {
			graphics.drawLine(calculateX(i), CELL_SIZE, calculateY(i), CELL_SIZE + (CELL_SIZE * panelDimension.height));
		}
		for (int i = 0; i <= panelDimension.height; i++) {
			graphics.drawLine(CELL_SIZE, calculateY(i), CELL_SIZE * (panelDimension.width + 1), calculateX(i));
		}
	}

	private void drawLiveCell(Graphics graphics) {
		try {
			for (Point newPoint : livePoints) {
				graphics.setColor(Color.yellow);
				graphics.fillRect(calculateX(newPoint.x), calculateY(newPoint.y), CELL_SIZE, CELL_SIZE);
			}
		} catch (ConcurrentModificationException cme) {
		}
	}

	public void addPoint(int x, int y) {
		Point checkPoint = new Point(x, y);
		if (!livePoints.contains(checkPoint)) {
			livePoints.add(checkPoint);
		}
		repaint();
	}

	public void addPoint(MouseEvent me) {
		int x = me.getPoint().x / CELL_SIZE - 1;
		int y = me.getPoint().y / CELL_SIZE - 1;
		if ((x >= 0) && (x < panelDimension.width) && (y >= 0) && (y < panelDimension.height)) {
			addPoint(x, y);
		}
	}

	private int calculateX(int x) {
		return CELL_SIZE + (CELL_SIZE * x);
	}

	private int calculateY(int y) {
		return CELL_SIZE + (CELL_SIZE * y);
	}

	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		addPoint(e);
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	@Override
	public void componentMoved(ComponentEvent e) {

	}

	@Override
	public void componentResized(ComponentEvent e) {

	}

	@Override
	public void componentShown(ComponentEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		addPoint(e);
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {

	}

}
