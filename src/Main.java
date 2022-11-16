import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;

public class Main
{
  public static void main(String[] args)
      throws ParserConfigurationException, TransformerException, IOException,
      SAXException
  {
    ArrayList<Integer> borrowings = new ArrayList<>();
    borrowings.add(0);
    borrowings.add(3);
    ArrayList<Integer> ownedGames = new ArrayList<>();
    ownedGames.add(2);
    ownedGames.add(4);
    Player player = new Player("Bob","25", "Hello", true, ownedGames, borrowings, "xxd", "xaxax", true);
    Player player2 = new Player("Wendy", "56", true);
    PlayerList playerList = new PlayerList();
    playerList.addPlayer(player);
    playerList.addPlayer(player2);
    FileReader.saveUserList(playerList);
    PlayerList players = FileReader.readPlayerList();
    System.out.println(players);

  }
}
