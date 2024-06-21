package cs3500.marblesolitaire.view.view;

import cs3500.marblesolitaire.view.controller.ControllerFeatures;

/**
 * The {@code IBoardPanel} interface represents the contract for a panel in the Marble Solitaire game
 * that can interact with controller features. Implementations of this interface are expected to
 * provide a method to set the controller's features, allowing the panel to handle user inputs and
 * update the game state accordingly.
 */
public interface IBoardPanel {

  /**
   * Sets the controller features for this board panel. This method allows the panel to
   * interact with the controller, enabling it to process user inputs and communicate
   * actions back to the controller.
   *
   * @param features the controller features to be set
   */
  void setFeatures(ControllerFeatures features);

}