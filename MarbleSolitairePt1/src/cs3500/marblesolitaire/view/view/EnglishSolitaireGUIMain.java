package cs3500.marblesolitaire.view.view;

import cs3500.marblesolitaire.view.controller.SwingGUIController;
import cs3500.marblesolitaire.view.model.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.model.EnglishSolitaireModel;
import cs3500.marblesolitaire.view.view.SwingGuiView;

/**
 * The {@code EnglishSolitaireGUIMain} class is the main entry point for running the Marble Solitaire game
 * with a graphical user interface (GUI) using the English Solitaire model.
 * It sets up the game model, view, and controller, and initializes the GUI for user interaction.
 */
public class EnglishSolitaireGUIMain {

  /**
   * The main method initializes and starts the Marble Solitaire game.
   * It creates an instance of the English Solitaire model, the Swing GUI view, and the Swing GUI controller.
   * This method connects these components to run the game with a GUI interface.
   *
   * @param args command-line arguments (not used in this application)
   */
  public static void main(String[] args) {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireGuiView view = new SwingGuiView(model);
    SwingGUIController controller = new SwingGUIController(model, view);
  }
}