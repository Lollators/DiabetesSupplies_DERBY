package program;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Class AddSupplies will handle the screen that allows the user to add supplies to the DB.
 *
 * @author  Luca Missaglia
 * @since   2018-12-09
 */
public class AddSupplies {

  //create list of products to be loaded into combobox
  ObservableList<String> products = FXCollections.observableArrayList("Lancets",
      "Test Strips","Sites","Tanks","Insulin");

  @FXML
  public TextField serialNumber;

  @FXML
  public ComboBox supplyType;

  @FXML
  public TextArea supplyName;

  @FXML
  public DatePicker expDate;

  /**
   * Initialize combobox with values in observableList, and set first selection to
   * first item of list.
   */
  public void initialize() {
    supplyType.setItems(products);
    supplyType.getSelectionModel().select(0);
  }

  /**
   * Return to main menu.
   *
   * @param actionEvent - Mouse Click
   */
  public void goBack(ActionEvent actionEvent) {
    Main.setPane(Screens.HOME.getValue());
  }

  /**
   * Function addSupply, being called whenever the user presses the button.
   * Checks for type of supply, adds its code to the serial number of product.
   * Concatenates user input, and then runs query to add product into database.
   *
   * @param actionEvent - button click
   */
  public void addSupply(ActionEvent actionEvent) {
    //get combobox selection, requires casting
    String selection = (String)supplyType.getValue();
    String query = "";
    String fullSerialNumber = "";

    //get date in string form (yyyy-mm-dd) to ensure that is ok for query
    String date = expDate.getValue().toString();

    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Success!");
    alert.setHeaderText(null);
    Alert alert2 = new Alert(AlertType.ERROR);
    alert2.setTitle("Error!");
    alert2.setHeaderText(null);

    //serial number without letters has to be 6 digits
    if (serialNumber.getLength() == 6) {
      //based on combobox selection, set product code to serial number
      switch (selection) {
        case "Lancets":
          fullSerialNumber = "LN";
          break;
        case "Test Strips":
          fullSerialNumber = "TS";
          break;
        case "Sites":
          fullSerialNumber = "ST";
          break;
        case "Tanks":
          fullSerialNumber = "TN";
          break;
        default:
          fullSerialNumber = "IN";
      }

      //adds user input to serial number
      fullSerialNumber += serialNumber.getText();

      //creates query
      query = "INSERT INTO PRODUCT (serialNumber, name, expDate, username) VALUES ('"
          + fullSerialNumber + "', '" + supplyName.getText() + "', '" + date + "', '"
          + Main.loggedUser + "')";

      //try to run query and handle exception that might be thrown
      try {
        if (DBconn.updateDb(query, DBconn.derbyConn()) != -1) {
          alert.setContentText("Supply successfully added!");
          alert.showAndWait();
        } else {
          alert2.setContentText("Database/Query issue");
          alert2.showAndWait();
        }
      } catch (Exception e) {
        System.out.println(e.toString());
      }
    } else {
      alert2.setContentText("Serial Number length is wrong!");
      alert2.showAndWait();
      serialNumber.setText("");
    }

  }
}
