import static org.junit.Assert.assertEquals;

import cs3500.animator.controller.Controller;
import cs3500.animator.view.MockView;
import cs3500.model.AnimatorModel;
import cs3500.model.Model;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Test;

/**
 * Represents the testing for the controllers. Mocks the actions of a controller.
 */
public class MockViewTest {


  @Test
  public void testStartButton() throws IOException {
    Appendable viewLog = new StringBuilder();
    MockView view = new MockView(viewLog);
    Model model = new AnimatorModel();
    Controller controller = new Controller(model, view);
    // controller.actionPerformed(new ActionEvent(view.mockStartButton,0,"start"));
    view.mockStartButton.doClick();
    assertEquals(viewLog.toString(), "goAnimation called ");
  }


  @Test
  public void testPauseButton() throws IOException {
    Appendable viewLog = new StringBuilder();
    MockView view = new MockView(viewLog);
    Model model = new AnimatorModel();
    Controller controller = new Controller(model, view);
    view.mockPauseButton.doClick();
    assertEquals(viewLog.toString(), "stop called ");
  }

  @Test
  public void testRestartButton() throws IOException {
    Appendable viewLog = new StringBuilder();
    MockView view = new MockView(viewLog);
    Model model = new AnimatorModel();
    Controller controller = new Controller(model, view);
    view.mockRestartButton.doClick();
    assertEquals(viewLog.toString(), "setTick method called goAnimation called ");
  }

  @Test
  public void testLoopButton() throws IOException {
    Appendable viewLog = new StringBuilder();
    MockView view = new MockView(viewLog);
    Model model = new AnimatorModel();
    Controller controller = new Controller(model, view);
    view.mockLoopButton.doClick();
    assertEquals(viewLog.toString(), "get loop Button boolean should loop run ");
  }

  @Test
  public void testChangeSpeed() throws IOException {
    Appendable viewLog = new StringBuilder();
    MockView view = new MockView(viewLog);
    Model model = new AnimatorModel();
    Reader in = new StringReader("5");
    Controller controller = new Controller(model, view);
    view.mockchangeSpeed.setText("5");
    controller.actionPerformed(new ActionEvent(view.mockchangeSpeed, 0, "5"));
    assertEquals(viewLog.toString(), "changeTempo called View is displayed");
  }
}