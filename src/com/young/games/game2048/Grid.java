package com.young.games.game2048;

import java.util.ArrayList;

public class Grid {

	public Tile[][] field; //已经被填充的方块
	public Tile[][] undoField; //还未被填充的方块

	public Grid(int sizeX, int sizeY) {
		field = new Tile[sizeX][sizeY];
		undoField = new Tile[sizeX][sizeY];
		clearGrid();
		clearUndoGrid();
	}

	/**
	 * 在可用的空余块中随机获取一个Cell
	 * @return
	 */
	public Cell randomAvailableCell() {
		ArrayList<Cell> availableCells = getAvailableCells();
		if (availableCells.size() >= 1) {
			return availableCells.get((int) Math.floor(Math.random()
					* availableCells.size()));
		}
		return null;
	}

	/**
	 * 把所有的空的方块添加到field里面
	 * @return
	 */
	public ArrayList<Cell> getAvailableCells() {
	    Util.debugLog("Grid.getAvailableCells " + field.length);
		ArrayList<Cell> availableCells = new ArrayList<Cell>();
		for (int xx = 0; xx < field.length; xx++) {
			for (int yy = 0; yy < field[xx].length; yy++) {
				if (field[xx][yy] == null) {
					availableCells.add(new Cell(xx, yy));
				}
			}
		}
		return availableCells;
	}


	public boolean isCellsAvailable() {
		return (getAvailableCells().size() >= 1);
	}

	public boolean isCellAvailable(Cell cell) {
		return !isCellOccupied(cell);
	}

	public boolean isCellOccupied(Cell cell) {
		return (getCellContent(cell) != null);
	}

	public Tile getCellContent(Cell cell) {
		if (cell != null && isCellWithinBounds(cell)) {
			return field[cell.getX()][cell.getY()];
		} else {
			return null;
		}
	}

	public Tile getCellContent(int x, int y) {
		if (isCellWithinBounds(x, y)) {
			return field[x][y];
		} else {
			return null;
		}
	}

	public boolean isCellWithinBounds(Cell cell) {
		return 0 <= cell.getX() && cell.getX() < field.length
				&& 0 <= cell.getY() && cell.getY() < field[0].length;
	}

	public boolean isCellWithinBounds(int x, int y) {
		return 0 <= x && x < field.length && 0 <= y && y < field[0].length;
	}

	public void insertTile(Tile tile) {
		field[tile.getX()][tile.getY()] = tile;
	}

	public void removeTile(Tile tile) {
		field[tile.getX()][tile.getY()] = null;
	}


	/**
	 * 把已经填充的给clear
	 */
	public void clearGrid() {
		for (int xx = 0; xx < field.length; xx++) {
			for (int yy = 0; yy < field[0].length; yy++) {
				field[xx][yy] = null;
			}
		}
	}

	/**
	 * 把还未填充的给clear
	 */
	public void clearUndoGrid() {
		for (int xx = 0; xx < field.length; xx++) {
			for (int yy = 0; yy < field[0].length; yy++) {
				undoField[xx][yy] = null;
			}
		}
	}
}
