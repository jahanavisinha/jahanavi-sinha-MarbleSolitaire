package cs3500.marblesolitaire.view.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import cs3500.marblesolitaire.view.controller.ControllerFeatures;
import cs3500.marblesolitaire.view.model.MarbleSolitaireModelState;

/**
 * The {@code BoardPanel} class represents the visual component of the Marble Solitaire game board.
 * It extends {@code JPanel} and is responsible for rendering the game board using images
 * and handling mouse click events to interact with the game.
 */
public class BoardPanel extends JPanel implements IBoardPanel {
  private MarbleSolitaireModelState modelState;
  private Image emptySlot, marbleSlot, blankSlot;
  private final int cellDimension;
  private int originX, originY;
  private ControllerFeatures features;
  private int highlightRow, highlightCol;

  /**
   * Constructs a {@code BoardPanel} with the given game state.
   * Loads and scales the images for the board cells, and sets the preferred size of the panel.
   *
   * @param state the current state of the Marble Solitaire game
   * @throws IllegalStateException if the required icon files cannot be loaded
   */
  public BoardPanel(MarbleSolitaireModelState state) throws IllegalStateException {
    super();
    this.modelState = state;
    this.setBackground(Color.WHITE);
    this.cellDimension = 50;
    try {
      emptySlot = ImageIO.read(new FileInputStream("res/empty.png"));
      emptySlot = emptySlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      marbleSlot = ImageIO.read(new FileInputStream("res/marble.png"));
      marbleSlot = marbleSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      blankSlot = ImageIO.read(new FileInputStream("res/blank.png"));
      blankSlot = blankSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      this.setPreferredSize(
              new Dimension((this.modelState.getBoardSize() + 4) * cellDimension,
                      (this.modelState.getBoardSize() + 4) * cellDimension));
    } catch (IOException e) {
      throw new IllegalStateException("Icons not found!");
    }
  }

  /**
   * Paints the game board on the panel. This method is called whenever the panel needs to be rendered.
   * It draws the appropriate image for each cell based on its state (marble, empty, or invalid).
   *
   * @param g the {@code Graphics} context in which to paint
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    originX = (int) (this.getPreferredSize().getWidth() / 2 - this.modelState.getBoardSize() * cellDimension / 2);
    originY = (int) (this.getPreferredSize().getHeight() / 2 - this.modelState.getBoardSize() * cellDimension / 2);

    // Paint the board
    for (int i = 0; i < this.modelState.getBoardSize(); i++) {
      for (int j = 0; j < this.modelState.getBoardSize(); j++) {
        switch (this.modelState.getSlotAt(i, j)) {
          case Invalid:
            g.drawImage(blankSlot, originX + j * cellDimension, originY + i * cellDimension, null);
            break;
          case Marble:
            g.drawImage(marbleSlot, originX + j * cellDimension, originY + i * cellDimension, null);
            break;
          case Empty:
            g.drawImage(emptySlot, originX + j * cellDimension, originY + i * cellDimension, null);
            break;
        }
      }
    }
  }

  /**
   * Sets the controller features and adds a mouse listener to the panel to handle user interactions.
   *
   * @param features the controller features that handle user input
   */
  @Override
  public void setFeatures(ControllerFeatures features) {
    this.features = features;
    this.addMouseListener(new BoardMouseListener());
  }

  /**
   * The {@code BoardMouseListener} class is a private inner class that extends {@code MouseAdapter}.
   * It listens for mouse click events and translates the click position into board coordinates,
   * which are then passed to the controller to handle the move.
   */
  private class BoardMouseListener extends MouseAdapter {

    /**
     * Handles mouse click events. Converts the click coordinates to board row and column
     * and sends this input to the controller.
     *
     * @param e the {@code MouseEvent} containing the details of the click
     */
    @Override
    public void mouseClicked(MouseEvent e) {
      int row = (e.getY() - originY) / BoardPanel.this.cellDimension;
      int col = (e.getX() - originX) / BoardPanel.this.cellDimension;
      BoardPanel.this.features.input(row, col);
    }
  }
}