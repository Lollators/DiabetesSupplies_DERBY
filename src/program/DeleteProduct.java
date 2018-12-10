package program;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * Class DeleteProduct will handle the screen that allows the user to remove a product from the DB.
 *
 * @author  Luca Missaglia
 * @since   2018-12-09
 */
public class DeleteProduct {

  //create list of products to be loaded into combobox
  ObservableList<String> products = FXCollections.observableArrayList("Lancets",
      "Test Strips","Sites","Tanks","Insulin");

  @FXML
  private ComboBox productType;

  @FXML
  private TextField productSerial;

  @FXML
  private CheckBox deletionCheck;

  /**
   * Initialize combobox with values in observableList, and set first selection to
   * first item of list.
   */
  public void initialize() {
    productType.setItems(products);
    productType.getSelectionModel().select(0);
  }

  /**
   * Function deleteProduct works only if deletionCheck is checked (since this is not
   * reversible). User has to type in the numerical value of the serial number of the product
   * and the function automatically sets the product code in the serial number from combobox.
   *
   * @param actionEvent - mouse click
   */
  public void deleteProduct(ActionEvent actionEvent) {
    //get combobox selection, requires casting
    String selection = (String)productType.getValue();
    String fullSerialNumber = "";
    String query = "";

    Alert alert2 = new Alert(AlertType.ERROR);
    alert2.setTitle("Error!");
    alert2.setHeaderText(null);
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Success!");
    alert.setHeaderText(null);

    //if the deletionCheck is selected and serial number typed has 6 digits then proceed
    if (deletionCheck.isSelected() && productSerial.getLength() == 6) {
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
      fullSerialNumber += productSerial.getText();

      //creates query
      query = "DELETE FROM PRODUCT WHERE SERIALNUMBER='" + fullSerialNumber + "'";

      try {
        //run query
        if (DBconn.updateDb(query, DBconn.derbyConn()) != -1) {
          alert.setContentText("Product removed successfully!");
          alert.showAndWait();

          //reset serial number textField
          productSerial.setText("");

          //deselect deletionCheck
          deletionCheck.setSelected(false);

          //return to main menu
          Main.setPane(Screens.HOME.getValue());
        } else {
          alert2.setContentText("Database/Query issue");
          alert2.showAndWait();
        }
      } catch (Exception e) {
        System.out.println(e.toString());
      }

    } else {
      alert2.setContentText("Please confirm your action!");
      alert2.showAndWait();
    }
  }

  //return to main menu
  public void back(ActionEvent actionEvent) {
    Main.setPane(Screens.HOME.getValue());
  }
}
