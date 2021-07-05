package cs3500.animator;

import cs3500.animator.controller.Controller;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.IView;
import cs3500.animator.view.ViewCreator;
import cs3500.model.AnimatorModel;
import cs3500.model.Model;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A class to perform an animation based on input given from the user as command line arguments.
 */
public final class Excellence {

  /**
   * Main method to create an animation based off input from the user.
   *
   * @param args the given command line arguments
   */
  public static void main(String[] args) {

    String input = "";
    String viewStr = "";
    String output = "";
    int tempo = 1;

    for (int i = 0; i < args.length; i++) {
      try {
        switch (args[i]) {
          case ("-view"):
            viewStr = args[i + 1];
            i++;
            break;
          case ("-in"):
            input = args[i + 1];
            i++;
            break;
          case ("-out"):
            output = args[i + 1];
            i++;
            break;
          case ("-speed"):
            try {
              if (Integer.parseInt(args[i + 1]) <= 0) {
                throw new IllegalArgumentException("negative tempo");
              }
              tempo = Integer.parseInt(args[i + 1]);
              i++;
            } catch (NumberFormatException n) {
              throw new IllegalArgumentException("tempo not an int");
            }
            break;
          default:
            break;
        }
      } catch (IndexOutOfBoundsException index) {
        throw new IllegalArgumentException("not enough input");
      }
    }

    Appendable ap = System.out;

    //if the parsing encounters a specification for output,
    //change the output from System.out to the specified output
    if (!output.equals("")) {
      if (output.equals("out")) {
        ap = System.out;
      } else {
        try {
          //checks to see if the file ends with the correct extension for svg
          if (!output.endsWith("svg")) {
            throw new IllegalArgumentException("invalid output extension");
          }
          ap = new FileWriter(output, true);
        } catch (IOException e) {
          throw new IllegalArgumentException("error with file");
        }
      }
    }

    try {
      Readable readable = new FileReader(input);
      //creates the model used for the readable
      Model model = AnimationReader.parseFile(readable, new AnimatorModel.Builder());
      //view that will be used
      IView view = null;
      if (output.equals("")) {
        view = ViewCreator.create(viewStr, model, tempo, ap);
        if (view == null) {
          throw new IllegalStateException("The view is null");
        }
      } else {
        view = ViewCreator.create(viewStr, model, tempo, ap, output);
        if (view == null) {
          throw new IllegalStateException("The view is null");
        }
      }
      view.display();
      Controller controller = new Controller(model, view);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("error with file");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}