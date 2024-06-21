import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cs3500.marblesolitaire.view.controller.SwingGUIController;
import cs3500.marblesolitaire.view.model.EnglishSolitaireModel;
import cs3500.marblesolitaire.view.view.SwingGuiView;

import java.awt.Graphics;

import org.junit.Test;

/**
 * This class contains unit tests for the {@link SwingGUIController}.
 * It tests the controller's handling of user input and its interaction
 * with the model and view components of a Marble Solitaire game.
 */
public class SwingControllerTest {

  /**
   * Tests the input handling of the {@link SwingGUIController}.
   * It verifies that the controller correctly processes moves by:
   * <ul>
   *   <li>Updating the model's score</li>
   *   <li>Triggering a change in the view's graphics</li>
   * </ul>
   */
  @Test
  public void testInput() {
    // Create a new English Solitaire game model.
    EnglishSolitaireModel model = new EnglishSolitaireModel();

    // Create a Swing-based view for the game.
    SwingGuiView view = new SwingGuiView(model);

    // Create a controller to manage interactions between the model and view.
    SwingGUIController controller = new SwingGUIController(model, view);

    // Capture the initial state of the view's graphics.
    Graphics defaultGraphic = view.getGraphics();

    // Simulate a valid move in the game.
    controller.input(3, 1); // Select the marble at (3,1).
    controller.input(3, 3); // Move the marble to (3,3).

    // Verify that the model's score has decreased by 1.
    assertEquals(31, model.getScore());

    // Capture the view's graphics after the first move.
    Graphics graphicAfterOneMove = view.getGraphics();

    // Ensure the graphics have changed after the first move.
    assertNotEquals(defaultGraphic, graphicAfterOneMove);

    // Simulate another valid move.
    controller.input(1, 2); // Select the marble at (1,2).
    controller.input(3, 2); // Move the marble to (3,2).

    // Capture the view's graphics after the second move.
    Graphics graphicAfterTwoMove = view.getGraphics();

    // Ensure the graphics have changed after the second move and are different from the initial state and first move.
    assertNotEquals(defaultGraphic, graphicAfterTwoMove);
    assertNotEquals(graphicAfterOneMove, graphicAfterTwoMove);
  }

}
