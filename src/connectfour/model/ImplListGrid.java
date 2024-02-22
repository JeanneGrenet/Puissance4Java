package connectfour.model;

import java.util.ArrayList;
import java.util.List;

public class ImplListGrid implements Grid {

	private final List<List<Tokens>> grid;
	private Integer rowOfLastPutToken;
	private int rows;
	private int columns;

	public ImplListGrid(int columns, int rows) {
		if (columns < 0 || rows < 0) {
			throw new IllegalArgumentException("Le nombre de colonne ou de ligne ne peut pas être négatif");
		}
		grid = new ArrayList<>();
		this.rows = rows;
		this.columns = columns;
		for (int col = 0; col < columns; col++) {
			grid.add(new ArrayList<>());
		}
	}

	@Override
	public Tokens getToken(int x, int y) {
		if (x < 0 || x >= columns || y < 0 || y >= rows) {
			throw new IllegalArgumentException("Cette position n'existe pas");
		}
		if (y >= grid.get(x).size()) {
			return null;
		}
		return grid.get(x).get(y);
	}

	@Override
	public void putToken(Tokens token, int x) throws ConnectException {
		if (token == null) {
			throw new IllegalArgumentException("Ce jeton ne peut pas être inséré car il ne correspond à aucun joueur");
		}
		if (x < 0 || x >= columns) {
			throw new ConnectException("La colonne est invalide");
		}
		List<Tokens> column = grid.get(x);
		if (column.size() == rows) {
			throw new ConnectException("La colonne est pleine");
		}
		column.add(token);
		rowOfLastPutToken = column.size() - 1;
	}

	@Override
	public Integer getRowOfLastPutToken() {
		// TODO Auto-generated method stub
		return rowOfLastPutToken;
	}

	@Override
	public void init() {
		rowOfLastPutToken = null;
		for (int column = 0; column < columns; column++) {
			grid.get(column).clear();
		}
	}

}
