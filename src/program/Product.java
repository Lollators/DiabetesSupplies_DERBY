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

  //return product name
  public String getName() {
    return name;
  }

  //set product name
  public void setName(String name) {
    this.name = name;
  }

  //return product serial number
  public String getSerialNumber() {
    return serialNumber;
  }

  //set product serial number
  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  //return expiration date of product as a String (yyyy-mm-dd)
  public String getExpDate() {
    return expDate;
  }

  //set expiration date of product as String (yyyy-mm-dd)
  public void setExpDate(String expDate) {
    this.expDate = expDate;
  }
}
