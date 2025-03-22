package game.action;
import game.PlayerDemeter;


public class ExchangeRessourcesTest{

  private PlayerDemeter player1;
  private PlayerDemeter player2;
  private ExchangeRessources action;
  

  void setUp(){

    player1=new PlayerDemeter("jul");
    player2=new PlayerDemeter("moustafa");
    action= new ExchangeRessources<>(player1);


}
    
}