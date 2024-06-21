import org.junit.Test;
import static org.junit.Assert.*;

import cs3500.marblesolitaire.view.controller.ControllerFeatures;
import cs3500.marblesolitaire.view.view.MockSwingGUIView;

/**
 * This class contains unit tests for the {@link MockSwingGUIView}.
 * It tests the mock view's logging functionality for various actions.
 */
public class MockSwingGUIViewTest {

  /**
   * Tests the {@link MockSwingGUIView#refresh()} method.
   * Verifies that calling refresh logs the correct message to the appendable.
   */
  @Test
  public void testRefresh() {
    StringBuilder log = new StringBuilder();
    MockSwingGUIView mockView = new MockSwingGUIView(log);

    mockView.refresh();
    assertEquals("Tried to refresh\n", log.toString());
  }

  /**
   * Tests the {@link MockSwingGUIView#renderMessage(String)} method.
   * Verifies that rendering a message logs the message correctly to the appendable.
   */
  @Test
  public void testRenderMessage() {
    StringBuilder log = new StringBuilder();
    MockSwingGUIView mockView = new MockSwingGUIView(log);

    String message = "This is a test message.";
    mockView.renderMessage(message);
    assertEquals("This is a test message.\n", log.toString());
  }

  /**
   * Tests the {@link MockSwingGUIView#setFeatures(ControllerFeatures)} method.
   * Verifies that setting features logs the expected message to the appendable.
   */
  @Test
  public void testSetFeatures() {
    StringBuilder log = new StringBuilder();
    MockSwingGUIView mockView = new MockSwingGUIView(log);

    ControllerFeatures dummyFeatures = new ControllerFeatures() {
      @Override
      public void input(int row, int col) {
        // no-op
      }
    };

    mockView.setFeatures(dummyFeatures);
    assertEquals("Used controller", log.toString());
  }

  /**
   * Tests the sequence of operations in the {@link MockSwingGUIView}.
   * Verifies that the log captures the correct sequence of actions.
   */
  @Test
  public void testSequenceOfOperations() {
    StringBuilder log = new StringBuilder();
    MockSwingGUIView mockView = new MockSwingGUIView(log);

    ControllerFeatures dummyFeatures = new ControllerFeatures() {
      @Override
      public void input(int row, int col) {
      }
    };

    mockView.refresh();
    mockView.renderMessage("First message");
    mockView.setFeatures(dummyFeatures);
    mockView.renderMessage("Second message");

    String expectedLog = "Tried to refresh\n" +
            "First message\n" +
            "Used controller" +
            "Second message\n";

    assertEquals(expectedLog, log.toString());
  }
}
