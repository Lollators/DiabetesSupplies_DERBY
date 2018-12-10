package program;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Class HomeController will handle the home screen for the user. It contains a TableView,
 * as I expect the user to view the products right when they log in.
 *
 * @author  Luca Missaglia
 * @since   2018-12-09
 */
public class HomeController {

  //create list of products to be loaded into combobox
  ObservableList<String> products = FXCollections.observableArrayList("Lancets",
      "Test Strips","Sites","Tanks","Insulin");

  @FXML
  public Label welcome;

  @FXML
  public ComboBox productCombo;

  //tableView that is going to contain products
  @FXML
  public TableView<Product> productTable;

  //column name, string from product name
  @FXML
  public TableColumn<Product, String> nameCol;

  //column serial Number, string from product serial number
  @FXML
  public TableColumn<Product, String> serialNumberCol;

  //column expiration date, string from product expiration date
  @FXML
  public TableColumn<Product, String> expCol;

  /**
   * Initialize productTable cleaning it from any residual values (if user logs out and logs in).
   * Set each column of productTable to tableColumns defined (each one is an attribute of Product).
   * Initialize combobox with values in observableList, and set first selection to
   * first item of list.
   */
  public void initialize() {
    //clear table from any product
    productTable.getItems().clear();

    //set each column to each product attribute
    nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    expCol.setCellValueFactory(new PropertyValueFactory<>("expDate"));
    serialNumberCol.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));

    productCombo.setItems(products);
    productCombo.getSelectionModel().select(0);
  }

  /**
   *   Go to changeDetails page.
   */
  public void changeDetails(ActionEvent actionEvent) {
    Main.setPane(Screens.CHANGEDETAILS.getValue());
  }

  /**
   * Logs out user (set static variable loggedUser to "").
   * Clear table from any products loaded into it.
   * Return to login page.
   * @param actionEvent - mouse click
   */
  public void logout(ActionEvent actionEvent) {
    Main.loggedUser = "";
    productTable.getItems().clear();
    Main.setPane(Screens.LOGIN.getValue());

  }

  /**
   * Function showProducts takes the combobox choice the user made, selects product code, and
   * runs query on DB based on beginning of product code.
   * Then with the data from the result of the query, creates an object of type Product that is
   * then added to the TableView to be displayed in ASC order.
   *
   * @param actionEvent - mouse click
   */
  public void showProducts(ActionEvent actionEvent) {
    //get combobox selection, requires casting
    String selection = (String)productCombo.getValue();
    String query = "";
    String code = "";

    //every time the user clicks the button the table should clear from any previous product
    productTable.getItems().clear();

    //based on combobox selection, set product code to serial number
    switch (selection) {
      case "Lancets":
        code = "LN";
        break;
      case "Test Strips":
        code = "TS";
        break;
      case "Sites":
        code = "ST";
        break;
      case "Tanks":
        code = "TN";
        break;
      default:
        code = "IN";
    }

    /*
     * Create query that select products based on the initial two char of their serialNumber (does
     * not matter what the numeric value of it is).
     * This identifies the correct product based on the selection of the user.
     * The result of this query will display in ASC order.
     */
    query = "SELECT * FROM PRODUCT WHERE SERIALNUMBER LIKE '" + code + "%' ORDER BY EXPDATE ASC";

    try {
      //run query and save result in ResultSet
      ResultSet rs = DBconn.queryDb(query, DBconn.derbyConn());
      if (rs != null) {
        while (rs.next()) {
          //for each result of the query create a Product object with the three variables fetched
          //from the query
          Product p = new Product(rs.getString("SERIALNUMBER"),
              rs.getString("NAME"), rs.getString("EXPDATE"));

          //add Product object to the table
          productTable.getItems().add(p);
        }
      }
    } catch (SQLException e) {
      System.out.println(e.toString());
    }
  }

  /**
   * Go to user panel page.
   */
  public void userPanel(ActionEvent actionEvent) {
    Main.setPane(Screens.USERPANEL.getValue());
  }

  /**
   * Go to add supplies page.
   */
  public void addSupplies(ActionEvent actionEvent) {
    Main.setPane(Screens.ADDSUPPLIES.getValue());
  }

  /**
   * Go to change site page.
   */
  public void changeSite(ActionEvent actionEvent) {
    Main.setPane(Screens.CHANGESITE.getValue());
  }

  /**
   * Go to delete product page.
   */
  public void removeSupplies(ActionEvent actionEvent) {
    Main.setPane(Screens.DELETEPRODUCT.getValue());
  }
}
