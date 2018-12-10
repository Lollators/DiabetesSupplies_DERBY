package program;

import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Class UserPanelController will handle the screen responsible for adding and deleting users.
 *
 * @author  Luca Missaglia
 * @since   2018-12-09
 */
public class UserPanelController {

  @FXML
  public TextField username;

  @FXML
  public PasswordField password;

  @FXML
  public TextField name;

  @FXML
  public TextField usernameDel;

  @FXML
  public CheckBox deletionCheck;

  /**
   * Function createAccount gets name, password, and username from user input.
   * Checks if user already exists, and if not runs query to add new user to user table.
   *
   * @param actionEvent - mouse click
   */
  public void createAccount(ActionEvent actionEvent) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Success!");
    alert.setHeaderText(null);
    Alert alert2 = new Alert(AlertType.ERROR);
    alert2.setTitle("Error!");
    alert2.setHeaderText(null);

    String query = "SELECT * FROM ACCOUNT WHERE USERNAME='" + username.getText() + "'";
    try {
      //run query and save result set
      ResultSet rs = DBconn.queryDb(query, DBconn.derbyConn());
      if (rs == null) {
        //if the result set has at least one value then it means the value inserted was valid
        //then update with new value typed by the user
        //create query
        query = "INSERT INTO ACCOUNT(name, username, password) VALUES ('" + name.getText()
            + "', '" + username.getText() + "', '" + password.getText() + "')";
        if (DBconn.updateDb(query, DBconn.derbyConn()) != -1) {
          alert.setContentText("New account created successfully!");
          alert.showAndWait();

          //reset TextFields and PasswordField so that they are empty for next use
          name.setText("");
          username.setText("");
          password.setText("");

          //return to home screen
          Main.setPane(Screens.HOME.getValue());
        } else {
          System.err.println("FAILED TO RUN QUERY");
        }
      } else {
        alert2.setContentText("An account with this username already exists!");
        username.clear();
        password.clear();
        alert2.showAndWait();
      }
    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }

  /**
   * Function deleteAccount gets username of user to delete from user input.
   * Runs query only if deletionCheck is selected (as it is an irreversible operation).
   * Check if user exists before trying to delete it.
   *
   * @param actionEvent - mouse click
   */
  public void deleteAccount(ActionEvent actionEvent) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Success!");
    alert.setHeaderText(null);
    Alert alert2 = new Alert(AlertType.ERROR);
    alert2.setTitle("Error!");
    alert2.setHeaderText(null);

    //if deletioncheck is selected it means the user is sure to remove this account
    if (deletionCheck.isSelected()) {

      //check that the user exists
      String query = "SELECT * FROM ACCOUNT WHERE USERNAME='" + usernameDel.getText() + "'";
      try {
        //run query and save result set
        ResultSet rs = DBconn.queryDb(query, DBconn.derbyConn());
        if (rs != null) {
          //if the result set has at least one value then it means the value inserted was valid
          if (rs.next()) {
            //create query to delete account
            query = "DELETE FROM ACCOUNT WHERE USERNAME='" + usernameDel.getText() + "'";
            //run query
            if (DBconn.updateDb(query, DBconn.derbyConn()) != -1) {
              alert.setContentText("Account deleted successfully!");
              alert.showAndWait();

              //reset textField and deselect deletionCheck for next use
              usernameDel.setText("");
              deletionCheck.setSelected(false);

              //return to home screen
              Main.setPane(Screens.HOME.getValue());
            } else {
              System.err.println("FAILED TO RUN QUERY");
            }
          } else {
            alert2.setContentText("Account not found, double check username!");
            alert2.showAndWait();
            usernameDel.clear();
          }
        }
      } catch (Exception e) {
        System.out.println(e.toString());
      }
    }

  }

  /**
   * Return to main menu.
   *
   * @param actionEvent - Mouse Click
   */
  public void back(ActionEvent actionEvent) {
    Main.setPane(Screens.HOME.getValue());
  }
}
