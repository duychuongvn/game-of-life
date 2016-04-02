package com.javadanang.gameoflife;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadMap {

	public ArrayList<Point> readLivePoints(File mapFile) {
		ArrayList<Point> livePoints = new ArrayList<Point>();
		try {
			FileInputStream fis = new FileInputStream(mapFile);
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(fis);
			int row = 0;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (!line.trim().isEmpty()) {
					int column = 0;
					String[] cols = line.split(",");
					for (String col : cols) {
						if ("O".equals(col)) {
							livePoints.add(new Point(row, column));
						}
						column++;
					}
				}
				row++;

			}
		} catch (Exception exception) {

		}
		return livePoints;
	}
}
