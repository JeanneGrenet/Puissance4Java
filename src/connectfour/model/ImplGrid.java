package connectfour.model;

public class ImplGrid implements Grid {

	private final Tokens[][] grid;
	private Integer rowOfLastPutToken;

	public ImplGrid(int columns, int rows) {
		if (columns < 0 || rows < 0) {
			throw new IllegalArgumentException("Le nombre de colonne ou de ligne ne peut pas être négatif");
		}
		grid = new Tokens[columns][rows];
	}

	@Override
	public Tokens getToken(int x, int y) {
		// TODO Auto-generated method stub
		if (x < 0 || x > grid.length || y < 0 || y > grid[x].length) {
			throw new IllegalArgumentException("Cette position n'éxiste pas");
		}
		Tokens selectedToken = grid[x][y];
		return selectedToken;
	}

	@Override
	public void putToken(Tokens token, int x) throws ConnectException {
		// TODO Auto-generated method stub
		if (token == null) {
			throw new IllegalArgumentException("Ce jeton ne peut pas être inséré car il ne correspond à aucun joueur");
		}
		if (x < 0 || x >= grid.length) {
			throw new ConnectException("La colonne est invalide");
		}
		int y = 0;
		while (y < grid[x].length && getToken(x, y) != null) {
			y++;
		}
		if (y == grid[x].length) {
			throw new ConnectException("La colonne est pleine");
		}
		grid[x][y] = token;
		rowOfLastPutToken = y;
	}

	@Override
	public Integer getRowOfLastPutToken() {
		// TODO Auto-generated method stub
		return rowOfLastPutToken;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		rowOfLastPutToken = null;
		for (int col = 0; col < grid.length; col++) {
			for (int row = 0; row < grid[col].length; row++) {
				grid[col][row] = null;
			}
		}
	}

}
