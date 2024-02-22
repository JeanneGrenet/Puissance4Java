package connectfour.model;

public class ImplGame implements Game {

	private final ImplListGrid grid = new ImplListGrid(COLUMNS, ROWS);
	private Tokens currentPlayer;
	private final static Tokens[] TOKEN_VALUES = Tokens.values();
	private boolean over;
	private Tokens winner;

	public ImplGame() {
		init();
	}

	@Override
	public Tokens getToken(int x, int y) {
		// TODO Auto-generated method stub
		return grid.getToken(x, y);
	}

	@Override
	public Tokens getCurrentPlayer() {
		// TODO Auto-generated method stub
		return currentPlayer;
	}

	private int inspectNWSE(int x, int y) {
		int foundInLine = 0;
		for (int i = 1; x - i >= 0 && y + i < ROWS && getToken(x - i, y + i) == currentPlayer; i++) {
			foundInLine++;
		}
		for (int i = 1; x + i < COLUMNS && y - i >= 0 && getToken(x + i, y - i) == currentPlayer; i++) {
			foundInLine++;
		}
		return foundInLine + 1;
	}

	private int inspectNESW(int x, int y) {
		int foundInLine = 0;
		for (int i = 1; x + i < COLUMNS && y + i < ROWS && getToken(x + i, y + i) == currentPlayer; i++) {
			foundInLine++;
		}
		for (int i = 1; x - i >= 0 && y - i >= 0 && getToken(x - i, y - i) == currentPlayer; i++) {
			foundInLine++;
		}
		return foundInLine + 1;
	}

	private int inspectSouth(int x, int y) {
		int foundInLine = 0;
		for (int i = 1; y - i >= 0 && getToken(x, y - i) == currentPlayer; i++) {
			foundInLine++;
		}
		return foundInLine + 1;
	};


	private int inspectWestEast(int x, int y) {
		int foundInLine = 0;
		for (int i = 1; x - i >= 0 && getToken(x - i, y) == currentPlayer; i++) {
			foundInLine++;
		}
		for (int i = 1; x + i < COLUMNS && getToken(x + i, y) == currentPlayer; i++) {
			foundInLine++;
		}
		return foundInLine + 1;
	}

	@Override
	public boolean isOver() {
		return over;
	}

	public boolean calculateOver(int col) {
		int row = grid.getRowOfLastPutToken();
		if (inspectWestEast(col, row) >= REQUIRED_TOKENS || inspectSouth(col, row) >= REQUIRED_TOKENS
				|| inspectNWSE(col, row) >= REQUIRED_TOKENS || inspectNESW(col, row) >= REQUIRED_TOKENS) {
			winner = currentPlayer;
			return true;
		}
		int numberFullCol = 0;
		for (int x = 0; x <= COLUMNS - 1; x++) {
			if (grid.getToken(x, ROWS - 1) != null) {
				numberFullCol++;
			}
		}
		if (numberFullCol == 7) {
			return true;
		}
		return false;
	}

	@Override
	public Tokens getWinner() {
		return winner;
	}

	@Override
	public void putToken(int column) throws ConnectException {
		// TODO Auto-generated method stub
		if (!over) {
			grid.putToken(currentPlayer, column);
		}
		over = calculateOver(column);
		getNextPlayer();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		over = false;
		winner = null;
		grid.init();
		Tokens[] players = TOKEN_VALUES;
		currentPlayer = players[(int) (Math.random() * 2)];
	}

	private Tokens getNextPlayer() {
		Tokens[] players = TOKEN_VALUES;
		currentPlayer = players[(currentPlayer.ordinal() + 1) % players.length];
		return currentPlayer;
	}

}
