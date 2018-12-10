package program;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Class Main loads all the fxml files onto an AnchorPane, so that they can be swapped without
 * creating other windows (cleaner design).
 *
 * @author  Luca Missaglia
 * @since   2018-12-09
 */
public class Main extends Application {

  //create variable loggedUser that indicates which user is logged in
  public static String loggedUser = "";

  //create anchorpane as a base
  static AnchorPane root;

  //create an arraylist of anchorpanes that will be swapped when user changes screen
  static List<AnchorPane> grid = new ArrayList<AnchorPane>();

  //variable that indicates current anchorpane
  private static int current = 0;

  @Override
  public void start(Stage primaryStage) throws Exception {
    try {
      //root loads first anchorpane anchor, base for the others
      root = FXMLLoader.load(Main.class.getResource("anchor.fxml"));

      //add every screen in order based on the Screens Enum
      grid.add(FXMLLoader.load(Main.class.getResource("login.fxml")));
      grid.add(FXMLLoader.load(Main.class.getResource("home.fxml")));
      grid.add(FXMLLoader.load(Main.class.getResource("changeDetails.fxml")));
      grid.add(FXMLLoader.load(Main.class.getResource("userPanel.fxml")));
      grid.add(FXMLLoader.load(Main.class.getResource("addSupplies.fxml")));
      grid.add(FXMLLoader.load(Main.class.getResource("changeSite.fxml")));
      grid.add(FXMLLoader.load(Main.class.getResource("deleteProduct.fxml")));

      //load as first screen the login one
      root.getChildren().add(grid.get(Screens.LOGIN.getValue()));

      //set dimension of window
      Scene scene = new Scene(root, 1280, 720);

      //set title of program
      primaryStage.setTitle("My Diabetes Supplies");

      // add scene to primaryStage
      primaryStage.setScene(scene);

      //do not allow user to resize window to maintain proper GUI items placement
      primaryStage.setResizable(false);

      //display stage
      primaryStage.show();
    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }

  /**
   * Function getPane that receives as a parameter an integer current, which symbolize the
   * Screen enum int that the anchorpane is assigned.
   * Returns an AnchorPane object that matches with the specified integer current.
   *
   * @param current - integer of current anchorpane, based on its Screen enum
   * @return AnchorPane - current anchorpane object
   */
  public static AnchorPane getPane(int current) {
    return grid.get(current);
  }

  /**
   * Function setPane that receives an integer value that represents the new AnchorPane Screen
   * Enum integer. Removes the previous AnchorPane, sets the new one, and swaps integer variable
   * current with new screens value (based on its Screen Enum).
   *
   * @param newCurrent - integer that represents new AnchorPane Screen Enum
   */
  public static void setPane(int newCurrent) {
    //remove current AnchorPane, based on previous integer
    root.getChildren().remove((grid.get(current)));

    //get new AnchorPane, based on new integer
    root.getChildren().add(grid.get(newCurrent));

    //set new AnchorPane integer value
    current = newCurrent;
  }

  public static void main(String[] args) {
    launch(args);
  }
}
