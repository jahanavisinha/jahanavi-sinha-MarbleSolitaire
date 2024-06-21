package cs3500.marblesolitaire.view.model;

/**
 * Represents a model for the English Solitaire game.
 * The board consists of slots, which can be marbles, empty, or invalid.
 */
public class EnglishSolitaireModel implements MarbleSolitaireModel {
  private static final int DEFAULT_ARM_THICKNESS = 3;
  private SlotState[][] board;
  private int armThickness;
  private int boardSize;
  private int emptyRow;
  private int emptyCol;

  private int removeRow;
  private int removeCol;
  private int marblesRemoved;
  private int invalidPositions;

  /**
   * Constructs a {@code EnglishSolitaireModel} with an arm thickness of 3 and an empty slot in the center.
   */
  public EnglishSolitaireModel() {
    this(DEFAULT_ARM_THICKNESS, DEFAULT_ARM_THICKNESS);
  }

  /**
   * Constructs a {@code EnglishSolitaireModel} with an arm thickness of 3 and an empty slot at the specified row and column.
   *
   * @param sRow the empty slot row
   * @param sCol the empty slot column
   * @throws IllegalArgumentException if the given row or column is greater than the board size
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this(DEFAULT_ARM_THICKNESS, sRow, sCol);
  }

  /**
   * Constructs a {@code EnglishSolitaireModel} with the specified arm thickness and an empty slot in the center.
   *
   * @param armThickness the arm thickness of the board
   * @throws IllegalArgumentException if the arm thickness is negative or even
   */
  public EnglishSolitaireModel(int armThickness) throws IllegalArgumentException {
    this(armThickness, armThickness * 3 / 2 - 1, armThickness * 3 / 2 - 1);
  }

  /**
   * Constructs a {@code EnglishSolitaireModel} with the specified arm thickness, empty slot row, and empty slot column.
   *
   * @param armThickness the arm thickness of the board
   * @param sRow the empty slot row
   * @param sCol the empty slot column
   * @throws IllegalArgumentException if the arm thickness is negative or even, or if the row and column aren't within the board size
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol) throws IllegalArgumentException {
    if (armThickness <= 0 || armThickness % 2 == 0) {
      throw new IllegalArgumentException(String.format("Invalid armThickness %d", armThickness));
    }
    this.armThickness = armThickness;
    this.boardSize = armThickness * 3 - 2;

    if (sRow < 0 || sRow >= boardSize || sCol < 0 || sCol >= boardSize || !validRowCol(sRow, sCol)) {
      throw new IllegalArgumentException(String.format("Invalid empty cell position (%d, %d)", sRow, sCol));
    }

    createBoard(armThickness, sRow, sCol);
  }

  /**
   * Checks whether the specified row and column pair is valid.
   *
   * @param row the specified row
   * @param col the specified column
   * @return true if the row and column pair is valid; false otherwise
   */
  private boolean validRowCol(int row, int col) {
    int armCenter = armThickness - 1;
    int centerSize = armThickness + (armThickness - 2);

    if ((row < armCenter || row >= boardSize - armCenter) && (col < armCenter || col >= boardSize - armCenter)) {
      return false;
    }
    return true;
  }

  /**
   * Sets each coordinate of the board to invalid, empty, or marble.
   *
   * @param armThickness the arm thickness of the board
   * @param emptyRow the empty slot's row
   * @param emptyCol the empty slot's column
   */
  private void createBoard(int armThickness, int emptyRow, int emptyCol) {
    this.armThickness = armThickness;
    this.emptyRow = emptyRow;
    this.emptyCol = emptyCol;
    this.boardSize = armThickness * 3 - 2;
    this.marblesRemoved = 0;
    this.invalidPositions = 0;
    this.board = new SlotState[boardSize][boardSize];

    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        if (!validRowCol(i, j)) {
          board[i][j] = SlotState.Invalid;
          invalidPositions++;
        } else if (i == emptyRow && j == emptyCol) {
          board[i][j] = SlotState.Empty;
        } else {
          board[i][j] = SlotState.Marble;
        }
      }
    }
  }

  /**
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @return true if moving to and from the specified positions is valid, false if not
   */

  private boolean openMove(int fromRow, int fromCol, int toRow, int toCol) {
    if (fromRow < 0 || fromRow >= boardSize || fromCol < 0 || fromCol >= boardSize ||
            toRow < 0 || toRow >= boardSize || toCol < 0 || toCol >= boardSize) {
      return false;
    }
    if (board[fromRow][fromCol] != SlotState.Marble || board[toRow][toCol] != SlotState.Empty) {
      return false;
    }

    int dRow = Math.abs(fromRow - toRow);
    int dCol = Math.abs(fromCol - toCol);
    if ((dRow == 2 && dCol == 0) || (dRow == 0 && dCol == 2)) {
      int middleRow = (fromRow + toRow) / 2;
      int middleCol = (fromCol + toCol) / 2;
      if (board[middleRow][middleCol] == SlotState.Marble) {
        removeRow = middleRow;
        removeCol = middleCol;
        return true;
      }
    }
    return false;
  }

  /**
   * {@inheritDoc}
   */

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (!openMove(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("Invalid move");
    }
    board[removeRow][removeCol] = SlotState.Empty;
    marblesRemoved++;
    board[fromRow][fromCol] = SlotState.Empty;
    board[toRow][toCol] = SlotState.Marble;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isGameOver() {
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        if (board[i][j] == SlotState.Marble) {
          if ((i + 2 < boardSize && openMove(i, j, i + 2, j)) ||
                  (i - 2 >= 0 && openMove(i, j, i - 2, j)) ||
                  (j + 2 < boardSize && openMove(i, j, i, j + 2)) ||
                  (j - 2 >= 0 && openMove(i, j, i, j - 2))) {
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getBoardSize() {
    return boardSize;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || row >= boardSize || col < 0 || col >= boardSize) {
      throw new IllegalArgumentException(String.format("Invalid cell position (%d, %d)", row, col));
    }
    return board[row][col];
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getScore() {
    int totalMarbles = (boardSize * boardSize) - invalidPositions - 1;
    return totalMarbles - marblesRemoved;
  }

}
