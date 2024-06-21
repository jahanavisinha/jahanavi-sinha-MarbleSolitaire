package cs3500.marblesolitaire.view.controller;

import cs3500.marblesolitaire.view.model.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.view.MarbleSolitaireGuiView;

/**
 * The {@code SwingGUIController} class implements the {@code ControllerFeatures} interface
 * and provides the functionality to control the Marble Solitaire game using a graphical user interface (GUI).
 * It interacts with both the model and the view to handle user input and update the game state.
 */
public class SwingGUIController implements ControllerFeatures {

  private MarbleSolitaireModel model;
  private MarbleSolitaireGuiView view;

  private int fromRow, fromCol, toRow, toCol;

  /**
   * Constructs a {@code SwingGUIController} with the given model and view.
   * Initializes the controller, sets up the view to use this controller, and refreshes the view.
   *
   * @param model the model representing the game state
   * @param view  the view to display the game
   */
  public SwingGUIController(MarbleSolitaireModel model, MarbleSolitaireGuiView view) {
    this.model = model;
    this.view = view;
    this.view.refresh();
    this.view.setFeatures(this);
    fromRow = -1;
    fromCol = -1;
    toRow = -1;
    toCol = -1;
  }

  /**
   * Handles the input of a move. If a starting position is selected, it waits for a destination position.
   * Once both positions are selected, it attempts to move a marble from the starting position to the destination.
   * If the move is invalid, it displays an "Invalid Move!" message. If the move is valid, it updates the game state.
   * If the game is over after the move, it displays a "Game Over!" message.
   *
   * @param row the row of the marble selected
   * @param col the column of the marble selected
   * @throws IllegalArgumentException if the row or column is out of bounds
   */
  @Override
  public void input(int row, int col) throws IllegalArgumentException {
    this.view.renderMessage("");
    if (row >= 0 && col >= 0) {
      if (fromRow == -1) {
        fromRow = row;
        fromCol = col;
      }
      else {
        toRow = row;
        toCol = col;

        try {
          model.move(fromRow, fromCol, toRow, toCol);

          if (model.isGameOver()) {
            this.view.renderMessage("Game Over!");
          }

        }
        catch (IllegalArgumentException e) {
          this.view.renderMessage("Invalid Move!");
        }
        fromRow = fromCol = toRow = toCol = -1;
      }
      this.view.refresh();
    }
  }
}