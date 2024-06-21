package cs3500.marblesolitaire.view.view;

import java.awt.*;
import javax.swing.*;
import cs3500.marblesolitaire.view.controller.ControllerFeatures;
import cs3500.marblesolitaire.view.model.MarbleSolitaireModelState;

/**
 * The {@code SwingGuiView} class represents a graphical user interface (GUI) view
 * for the Marble Solitaire game using Java Swing. This class extends {@code JFrame}
 * and implements {@code MarbleSolitaireGuiView}. It provides a visual representation
 * of the game board and allows user interaction through the GUI.
 */
public class SwingGuiView extends JFrame implements MarbleSolitaireGuiView {

  // The custom panel where the game board will be drawn
  private JPanel boardPanel;
  // The model state representing the game state
  private MarbleSolitaireModelState modelState;
  // A label to display the current score
  private JLabel scoreLabel;
  // A label to display any messages to the user
  private JLabel messageLabel;

  /**
   * Constructs a {@code SwingGuiView} with the given game state.
   * Initializes the main frame, sets up the layout and components,
   * including the game board panel, score label, and message label.
   *
   * @param state the current state of the Marble Solitaire game
   */
  public SwingGuiView(MarbleSolitaireModelState state) {
    super("Marble Solitaire");
    this.modelState = state;

    // Set the layout of the main frame to BorderLayout
    this.setLayout(new BorderLayout());

    // Initialize the custom board panel with the model state
    boardPanel = new BoardPanel(this.modelState);
    // Add the custom board to the center of the frame
    this.add(boardPanel, BorderLayout.CENTER);

    // Create the score label
    this.scoreLabel = new JLabel();
    // Create the message label
    this.messageLabel = new JLabel();

    // Create a panel to arrange the score and message labels
    JPanel panel = new JPanel();
    // Set the panel layout to GridLayout with two columns
    panel.setLayout(new GridLayout(0, 2));
    panel.add(scoreLabel);
    panel.add(messageLabel);

    // Add the panel to the bottom of the frame
    this.add(panel, BorderLayout.PAGE_END);

    // Pack the components within the frame
    pack();
    // Make the frame visible
    setVisible(true);
  }

  /**
   * Refreshes the GUI view. Updates the score displayed and repaints the frame
   * to reflect any changes in the game state.
   */
  public void refresh() {
    // Update the score label with the current score
    this.scoreLabel.setText("Score: " + modelState.getScore());
    // Repaint the frame, which updates all components
    this.repaint();
  }

  /**
   * Renders a message in the GUI's message label.
   * This can be used to display notifications or error messages to the user.
   *
   * @param message the message to be displayed
   */
  @Override
  public void renderMessage(String message) {
    this.messageLabel.setText(message);
  }

  /**
   * Sets the controller features for the board panel.
   * This method connects the board panel with the controller, allowing it to handle
   * user inputs and interact with the game logic.
   *
   * @param callbacks the controller features to be set
   */
  @Override
  public void setFeatures(ControllerFeatures callbacks) {
    ((IBoardPanel) this.boardPanel).setFeatures(callbacks);
  }
}