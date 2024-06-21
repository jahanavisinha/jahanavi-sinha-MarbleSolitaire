package cs3500.marblesolitaire.view.view;

import cs3500.marblesolitaire.view.controller.ControllerFeatures;

/**
 * A mock implementation of the {@link MarbleSolitaireGuiView} interface for testing purposes.
 * This class simulates interactions with a graphical user interface (GUI) by appending messages
 * to an {@link Appendable} object instead of displaying them in a GUI. It is useful for testing
 * how the view interacts with the controller without requiring an actual GUI.
 */
public class MockSwingGUIView implements MarbleSolitaireGuiView {
  private Appendable appendable;

  /**
   * Constructs a MockSwingGUIView that will write its output to the provided Appendable.
   *
   * @param appendable the appendable object to which output is written
   */
  public MockSwingGUIView(Appendable appendable) {
    this.appendable = appendable;
  }

  /**
   * Simulates refreshing the GUI by appending a "Tried to refresh" message to the appendable.
   * This method is used to mimic the behavior of a real GUI's refresh operation.
   *
   * @throws IllegalArgumentException if appending to the appendable fails
   */
  @Override
  public void refresh() {
    try {
      appendable.append("Tried to refresh\n");
    } catch (Exception e) {
      throw new IllegalArgumentException("Cannot append");
    }
  }

  /**
   * Appends the given message followed by a newline character to the appendable.
   * This method is used to simulate displaying a message in the GUI.
   *
   * @param message the message to be appended
   * @throws IllegalArgumentException if appending to the appendable fails
   */
  @Override
  public void renderMessage(String message) {
    try {
      appendable.append(message + "\n");
    } catch (Exception e) {
      throw new IllegalArgumentException("Cannot append");
    }
  }

  /**
   * Simulates setting controller features by appending a "Used controller" message to the appendable.
   * This method is used to mimic the association of controller functionalities with the view.
   *
   * @param callbacks the controller features to be set in the view
   * @throws IllegalArgumentException if appending to the appendable fails
   */
  @Override
  public void setFeatures(ControllerFeatures callbacks) {
    try {
      appendable.append("Used controller");
    } catch (Exception e) {
      throw new IllegalArgumentException("Cannot append");
    }
  }
}
