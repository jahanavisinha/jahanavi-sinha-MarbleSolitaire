package cs3500.marblesolitaire.view.controller;

public interface ControllerFeatures {

  /**
   * takes in user input, update the satet and view
   * @param row row of the user's move
   * @param col column of the user's move
   */

  void input(int row, int col);
}