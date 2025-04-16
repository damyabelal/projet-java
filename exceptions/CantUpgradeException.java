public class CantUpgradeException extends Exception {

  public CantUpgradeException(){
    super("An error occurred while upgrading.");
  }
}