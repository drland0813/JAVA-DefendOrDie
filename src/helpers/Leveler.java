package helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import data.Tile;
import data.TileGrid;
import data.TileType;

public class Leveler {

	public static void saveMap(String mapName, TileGrid grid) {
		String mapData = "";
		for (int i = 0; i < grid.getTilesWide(); i++) {
			for (int j = 0; j < grid.getTilesHigh(); j++) {
				mapData += getTileID(grid.getTile(i, j));
			}
		}

		try {
			File file = new File(mapName);
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(mapData);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static TileGrid loadMap(String mapName) {
		TileGrid grid = new TileGrid();
		try {
			BufferedReader br = new BufferedReader(new FileReader(mapName));
			String data = br.readLine();
			for (int i = 0; i < grid.getTilesWide(); i++) {
				for (int j = 0; j < grid.getTilesHigh(); j++) {
					grid.setTile(i, j,
							getTileType(data.substring(i * grid.getTilesHigh() + j, i * grid.getTilesHigh() + j + 1)));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return grid;

	}

	public static TileType getTileType(String ID) {
		TileType tp = TileType.NULL;
		switch (ID) {
		case "0":
			tp = TileType.TTopL;
			break;
		case "1":
			tp = TileType.Way;
			break;
		case "2":
			tp = TileType.Space0;
			break;
		case "3":
			tp = TileType.NULL;
			break;
		}
		return tp;
	}

	public static String getTileID(Tile t) {
		String ID = "E";
		switch (t.getType()) {
		case TTopL:
			ID = "0";
			break;
		case Way:
			ID = "1";
			break;
		case Space0:
			ID = "2";
			break;
		case NULL:
			ID = "3";
			break;
		}
		return ID;
	}
}
