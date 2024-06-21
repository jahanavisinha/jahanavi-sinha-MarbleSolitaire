package cs3500.marblesolitaire.view.view;

import java.io.IOException;

import cs3500.marblesolitaire.view.model.MarbleSolitaireModelState;

/**
 * A textual view for the Marble Solitaire game. This class is responsible for rendering
 * the current state of the game board and transmitting messages to the user.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {

  private final MarbleSolitaireModelState state;
  private final Appendable appendable;

  /**
   * Constructs a {@code MarbleSolitaireTextView} with a specified model state.
   * Uses {@code System.out} as the default appendable for rendering outputs.
   *
   * @param state the current state of the Marble Solitaire game.
   * @throws IllegalArgumentException if the provided state is null.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState state) throws IllegalArgumentException {
    this(state, System.out);
  }

  /**
   * Constructs a {@code MarbleSolitaireTextView} with a specified model state and appendable.
   *
   * @param state the current state of the Marble Solitaire game.
   * @param appendable the destination for rendering outputs.
   * @throws IllegalArgumentException if either the provided state or appendable is null.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState state, Appendable appendable) throws IllegalArgumentException {
    if (state == null || appendable == null) {
      throw new IllegalArgumentException("Model or appendable is null");
    }
    this.state = state;
    this.appendable = appendable;
  }

  /**
   * Adds a space before each board piece, except for the first column in a row.
   *
   * @param col the column index of the board piece.
   * @param boardPiece the representation of the board piece (e.g., "O" for marble, "_" for empty).
   * @return a string combining the board piece with a leading space if it's not in the first column,
   *         otherwise just the board piece.
   */
  private String addSpaceBetween(int col, String boardPiece) {
    if (col != 0) {
      return " " + boardPiece;
    }
    return boardPiece;
  }

  /**
   * Returns a string representation of the current state of the game board.
   * Marbles are represented by "O", empty slots by "_", and invalid positions by " ".
   *
   * @return the string representation of the game board.
   */
  @Override
  public String toString() {
    StringBuilder board = new StringBuilder();
    int boardSize = state.getBoardSize();

    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        if (state.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Marble) {
          board.append(addSpaceBetween(j, "O"));
        } else if (state.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Empty) {
          board.append(addSpaceBetween(j, "_"));
        } else {
          board.append(addSpaceBetween(j, " "));
        }

        // Add a new line after each row, except for the last row
        if (j == boardSize - 1 && i < boardSize - 1) {
          board.append("\n");
        }
      }
    }
    return board.toString();
  }

  /**
   * Renders the current state of the game board to the appendable.
   *
   * @throws IOException if the board state cannot be transmitted to the appendable.
   */
  @Override
  public void renderBoard() throws IOException {
    try {
      appendable.append(this.toString());
    } catch (IOException e) {
      throw new IOException("Cannot transmit the state of the board", e);
    }
  }

  /**
   * Transmits a given message to the appendable.
   *
   * @param message the message to be sent to the appendable.
   * @throws IOException if the message cannot be transmitted to the appendable.
   */
  @Override
  public void renderMessage(String message) throws IOException {
    try {
      appendable.append(message);
    } catch (IOException e) {
      throw new IOException("Cannot transmit the message.", e);
    }
  }
}