package program;

/**
 * Class Product defines what a product that can be found in the DB would be. This product
 * has basic attributes such as name, serial number, and expiration date.
 *
 * @author  Luca Missaglia
 * @since   2018-12-09
 */
public class Product {

  private String name;
  private String serialNumber;
  private String expDate;

  /**
   * Constructor of class Product that receives 3 parameters and sets local variables equal to
   * those parameters.
   *
   * @param serialNumber - String that represents the serial number of a product.
   * @param name - String that represents the name of a product.
   * @param expDate - String that represents the expiration date of a product in form yyyy-mm-dd.
   */
  public Product(String serialNumber, String name,  String expDate) {
    this.name = name;
    this.serialNumber = serialNumber;
    this.expDate = expDate;
  }

  /**
   * Function getName returns product name.
   *
   * @return name - Product Name
   */
  public String getName() {
    return name;
  }

  /**
   * Function setName sets product name passed as parameter to variable.
   *
   * @param name - Product Name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Function getSerialNumber returns product serial number.
   *
   * @return serialNumber - Product Serial Number
   */
  public String getSerialNumber() {
    return serialNumber;
  }

  /**
   * Function setSerialNumber sets product serial number passed as parameter to variable.
   *
   * @param serialNumber - Product Serial Number
   */
  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  /**
   * Function getExpDate returns expiration date of product as a String (yyyy-mm-dd).
   *
   * @return expDate - Product Expiration Date
   */
  public String getExpDate() {
    return expDate;
  }

  /**
   * Function setExpDate sets expiration date of product as String (yyyy-mm-dd).
   *
   * @param expDate - Product Expiration Date
   */
  public void setExpDate(String expDate) {
    this.expDate = expDate;
  }
}
