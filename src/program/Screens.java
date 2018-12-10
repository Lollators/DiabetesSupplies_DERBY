package program;

/**
 * Enum class Screens contains the screen name and its integer value. The integer value is
 * base on the order that the screen was added to the grid in Main.
 *
 * @author  Luca Missaglia
 * @since   2018-12-09
 */
public enum Screens {
  LOGIN(0),
  HOME(1),
  CHANGEDETAILS(2),
  USERPANEL(3),
  ADDSUPPLIES(4),
  CHANGESITE(5),
  DELETEPRODUCT(6);

  private final int value;

  //Constructor of enum sets integer value
  Screens(final int newValue) {
    value = newValue;
  }

  //return Enum integer value
  public int getValue() {
    return value;
  }
}
