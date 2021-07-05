import static org.junit.Assert.assertTrue;

import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextualView;
import cs3500.animator.view.ViewCreator;
import cs3500.animator.view.VisualView;
import cs3500.model.AnimatorModel;
import cs3500.model.Model;
import org.junit.Test;

/**
 * Test the creation of an instance of a view.
 */
public class ViewCreatorTest {

  @Test
  public void testViewCreator() {
    ViewCreator vc = new ViewCreator();
    Model model = new AnimatorModel();
    Appendable ap = new StringBuilder();

    assertTrue(ViewCreator.create("text", model, 2, ap) instanceof TextualView);
    assertTrue(ViewCreator.create("visual", model, 1, null) instanceof VisualView);
    assertTrue(ViewCreator.create("svg", model, 4, ap) instanceof SVGView);

  }
}
