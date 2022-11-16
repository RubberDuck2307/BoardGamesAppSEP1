import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileReader
{
  public static void saveUserList(PlayerList playerList)
      throws ParserConfigurationException, TransformerException
  {

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.newDocument();

    Element rootElement = doc.createElement("PlayerList");
    Player player;
    for (int i = 0; i < playerList.size(); i++)
    {
      player = playerList.getPlayer(i);

      Element subElement = doc.createElement("Player");

      Element subSubElement = doc.createElement("ID");
      subSubElement.appendChild(
          doc.createTextNode(Integer.toString(player.getID())));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("name");
      subSubElement.appendChild(doc.createTextNode(player.getName()));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("phoneNumber");
      subSubElement.appendChild(doc.createTextNode(player.getPhoneNumber()));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("email");
      subSubElement.appendChild(doc.createTextNode(player.getEmail()));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("membership");
      subSubElement.appendChild(
          doc.createTextNode(String.valueOf(player.isMembership())));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("ownedBoardGamesIDs");
      Element subSubSubElement;
      for (int j = 0; j < player.getOwnedBoardGamesIDs().size(); j++)
      {
        subSubSubElement = doc.createElement("ID");
        subSubSubElement.appendChild(doc.createTextNode(
            String.valueOf(player.getOwnedBoardGamesIDs().get(j))));
        subSubElement.appendChild(subSubSubElement);
      }
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("borrowingsIDs");
      for (int j = 0; j < player.getBorrowingsIDs().size(); j++)
      {
        subSubSubElement = doc.createElement("ID");
        subSubSubElement.appendChild(doc.createTextNode(
            String.valueOf(player.getBorrowingsIDs().get(j))));
        subSubElement.appendChild(subSubSubElement);
      }
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("comment");
      subSubElement.appendChild(
          doc.createTextNode(String.valueOf(player.getComment())));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("address");
      subSubElement.appendChild(
          doc.createTextNode(String.valueOf(player.getAddress())));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("voted");
      subSubElement.appendChild(
          doc.createTextNode(String.valueOf(player.getVoted())));
      subElement.appendChild(subSubElement);

      rootElement.appendChild(subElement);

    }
    doc.appendChild(rootElement);

    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
        "2");
    File file = new File("Players.xml");
    transformer.transform(new DOMSource(doc), new StreamResult(file));
  }

  public static PlayerList readPlayerList()
      throws ParserConfigurationException, IOException, SAXException,
      TransformerConfigurationException
  {
    PlayerList playerList = new PlayerList();

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse("Players.xml");
    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "no");

    int ID = -1;
    String name = null;
    String phoneNumber = null;
    String email = null;
    boolean membership = false;
    ArrayList<Integer> ownedBoardGamesIDs = new ArrayList<>();
    ArrayList<Integer> borrowingsIDs = new ArrayList<>();
    String comment = null;
    String address = null;
    boolean voted = false;

    NodeList rootList = doc.getElementsByTagName("Player");
    for (int i = 0; i < rootList.getLength(); i++)
    {
      Node rootNode = rootList.item(i);
      NodeList subNodes = rootNode.getChildNodes();
      for (int j = 0; j < subNodes.getLength(); j++)
      {
        if (subNodes.item(j).getNodeName().equals("ID"))
        {
          ID = Integer.parseInt(subNodes.item(j).getTextContent());
        }
        else if (subNodes.item(j).getNodeName().equals("name"))
        {
          name = subNodes.item(j).getTextContent();
        }
        else if (subNodes.item(j).getNodeName().equals("phoneNumber"))
        {
          phoneNumber = subNodes.item(j).getTextContent();
        }
        else if (subNodes.item(j).getNodeName().equals("email"))
        {
          email = subNodes.item(j).getTextContent();
        }
        else if (subNodes.item(j).getNodeName().equals("membership"))
        {
          membership = Boolean.parseBoolean(subNodes.item(j).getTextContent());
        }
        else if (subNodes.item(j).getNodeName().equals("ownedBoardGamesIDs"))
        {
          NodeList subSubNodes = subNodes.item(j).getChildNodes();
          for (int k = 0; k < subSubNodes.getLength(); k++)
          {
            if (subSubNodes.item(k).getNodeName().equals("ID"))
            {
              ownedBoardGamesIDs.add(
                  Integer.parseInt(subSubNodes.item(k).getTextContent()));
            }
          }
        }
        else if (subNodes.item(j).getNodeName().equals("borrowingsIDs"))
        {
          NodeList subSubNodes = subNodes.item(j).getChildNodes();
          for (int k = 0; k < subSubNodes.getLength(); k++)
          {
            if (subSubNodes.item(k).getNodeName().equals("ID"))
            {
              borrowingsIDs.add(
                  Integer.parseInt(subSubNodes.item(k).getTextContent()));
            }
          }
        }

        else if (subNodes.item(j).getNodeName().equals("comment"))
        {
          comment = subNodes.item(j).getTextContent();
        }

        else if (subNodes.item(j).getNodeName().equals("address"))
        {
          address = subNodes.item(j).getTextContent();
        }

        else if (subNodes.item(j).getNodeName().equals("voted"))
        {
          voted = Boolean.parseBoolean(subNodes.item(j).getTextContent());
        }
      }
      Player player = new Player(ID, name, phoneNumber, email, membership,
          ownedBoardGamesIDs, borrowingsIDs, comment, address, voted);
      playerList.addPlayer(player);
    }
    return playerList;
  }
}
