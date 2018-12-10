package program;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Class LoginController will handle the Login screen of the user. The class checks with
 * the database if a user is present, based on username and pw inputted. If user exists, and the
 * credentials are correct, then the home page will load.
 *
 * @author  Luca Missaglia
 * @since   2018-12-09
 */
public class LoginController {

  @FXML
  public TextField username;

  @FXML
  public PasswordField password;

  @FXML
  public Label wrongLoginText;

  /**
   * Function login runs query on db to check if username and password that the user has
   * typed match a record in the user table.
   * If so, the user is redirected to the home page.
   * If not, an error label is shown.
   *
   * @param actionEvent - mouse click
   */
  public void login(ActionEvent actionEvent) {

    //create query
    String query = "SELECT * FROM ACCOUNT WHERE USERNAME='" + username.getText() + "' AND PASSWORD='"
        + password.getText() + "'";
    try {
      //run query and save result in ResultSet
      ResultSet rs = DBconn.queryDb(query, DBconn.derbyConn());
      if (rs != null) {
        //if ResultSet has a value then it found a match
        if (rs.next()) {
          //set loggedUser to the username typed
          Main.loggedUser = username.getText();

          //hide error label
          wrongLoginText.setVisible(false);

          //redirect to Home page
          Main.setPane(Screens.HOME.getValue());

          //clear username textField (otherwise if user logs in and out data will still be there)
          username.setText("");

          //clear password textField (otherwise if user logs in and out data will still be there)
          password.setText("");
        } else {
          wrongLoginText.setVisible(true);
        }
      } else {
        System.err.println("FAILED TO RUN QUERY");
      }
    } catch (SQLException e) {
      System.out.println(e.toString());
    }
  }
}
