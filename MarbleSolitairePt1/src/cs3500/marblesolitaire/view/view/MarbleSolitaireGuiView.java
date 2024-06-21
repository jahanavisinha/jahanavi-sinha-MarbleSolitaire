package cs3500.marblesolitaire.view.view;

import cs3500.marblesolitaire.view.controller.ControllerFeatures;

/**
 * The {@code MarbleSolitaireGuiView} interface defines the contract for a graphical user interface (GUI)
 * view for the Marble Solitaire game. Implementations of this interface provide methods to refresh the
 * display, render messages, and connect with the controller to handle user inputs.
 */
public interface MarbleSolitaireGuiView {

  /**
   * Refreshes the GUI screen. This method should be called whenever the game state changes and
   * the screen needs to be updated to reflect these changes. It ensures that the display is
   * redrawn to show the current state of the game.
   */
  void refresh();

  /**
   * Displays a message to the user in an appropriate area of the GUI. This can be used to
   * provide feedback, notifications, or error messages during the game.
   *
   * @param message the message to be displayed
   */
  void renderMessage(String message);

  /**
   * Sets the controller features for the GUI view. This method connects the GUI to the controller,
   * enabling the view to handle user inputs and communicate actions back to the controller.
   *
   * @param callbacks the controller features to be set, providing the necessary operations for the view
   */
  void setFeatures(ControllerFeatures callbacks);
}