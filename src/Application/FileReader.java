package Application;

import Model.*;
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
import java.time.LocalDate;
import java.util.ArrayList;

public class FileReader
{
  public static void SAVE_PLAYERS_LIST(PlayersList playersList)
      throws ParserConfigurationException, TransformerException
  {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.newDocument();

    Element rootElement = doc.createElement("PlayersList");
    Player player;
    for (int i = 0; i < playersList.size(); i++)
    {
      player = playersList.getPlayer(i);

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

      if (player.getFeePaymentDate() != null)
      {
        subSubElement = doc.createElement("feePayment");
        Element subSubSubElement = doc.createElement("day");
        subSubSubElement.appendChild(doc.createTextNode(
            String.valueOf(player.getFeePaymentDate().getDayOfMonth())));
        subSubElement.appendChild(subSubSubElement);
        subSubSubElement = doc.createElement("month");
        subSubSubElement.appendChild(doc.createTextNode(
            String.valueOf(player.getFeePaymentDate().getMonthValue())));
        subSubElement.appendChild(subSubSubElement);
        subSubSubElement = doc.createElement("year");
        subSubSubElement.appendChild(doc.createTextNode(
            String.valueOf(player.getFeePaymentDate().getYear())));
        subSubElement.appendChild(subSubSubElement);
        subElement.appendChild(subSubElement);
      }

      rootElement.appendChild(subElement);

    }
    doc.appendChild(rootElement);

    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
        "2");
    File file = new File("./src/XML/Players.xml");
    transformer.transform(new DOMSource(doc), new StreamResult(file));
  }

  public static PlayersList READ_PLAYERS_LIST()
      throws ParserConfigurationException, IOException, SAXException,
      TransformerConfigurationException
  {
    PlayersList playersList = new PlayersList();

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse("./src/XML/Players.xml");
    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "no");

    NodeList rootList = doc.getElementsByTagName("Player");
    for (int i = 0; i < rootList.getLength(); i++)
    {
      int ID = -1;
      String name = null;
      String phoneNumber = null;
      String email = null;
      boolean membership = false;
      String comment = null;
      String address = null;
      boolean voted = false;
      LocalDate feePayment = null;

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

        else if (subNodes.item(j).getNodeName().equals("feePayment"))
        {
          int day = 0;
          int month = 0;
          int year = 0;

          NodeList subSubList = subNodes.item(j).getChildNodes();
          for (int k = 0; k < subSubList.getLength(); k++)
          {
            Node subSubNode = subSubList.item(k);
            if (subSubNode.getNodeName().equals("day"))
            {
              day = Integer.parseInt(subSubNode.getTextContent());
            }
            else if (subSubNode.getNodeName().equals("month"))
            {
              month = Integer.parseInt(subSubNode.getTextContent());
            }
            else if (subSubNode.getNodeName().equals("year"))
            {
              year = Integer.parseInt(subSubNode.getTextContent());
            }

          }
          feePayment = LocalDate.of(year, month, day);
        }
      }
      Player player = new Player(ID, name, phoneNumber, email, membership,
          comment, address, voted, feePayment);

      playersList.addPlayer(player);
    }
    return playersList;
  }

  public static void SAVE_BOARDGAMES_LIST(BoardGamesList boardGamesList)
      throws ParserConfigurationException, TransformerException
  {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.newDocument();

    Element rootElement = doc.createElement("BoardGamesList");
    BoardGame boardGame;
    Element subElement;

    for (int i = 0; i < boardGamesList.size(); i++)
    {
      boardGame = boardGamesList.getBoardGame(i);
      subElement = doc.createElement("BoardGame");

      Element subSubElement = doc.createElement("ID");
      subSubElement.appendChild(
          doc.createTextNode(Integer.toString(boardGame.getID())));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("name");
      subSubElement.appendChild(doc.createTextNode(boardGame.getName()));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("type");
      subSubElement.appendChild(doc.createTextNode(boardGame.getType()));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("numberOfPlayersMin");
      subSubElement.appendChild(doc.createTextNode(
          Integer.toString(boardGame.getNumberOfPlayersMin())));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("numberOfPlayersMax");
      subSubElement.appendChild(doc.createTextNode(
          Integer.toString(boardGame.getNumberOfPlayersMax())));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("availabilityStatus");
      subSubElement.appendChild(
          doc.createTextNode(boardGame.getAvailabilityStatus()));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("comment");
      subSubElement.appendChild(doc.createTextNode(boardGame.getComment()));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("ownerID");
      subSubElement.appendChild(
          doc.createTextNode(Integer.toString(boardGame.getOwnerID())));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("numberOfVotes");
      subSubElement.appendChild(
          doc.createTextNode(Integer.toString(boardGame.getNumberOfVotes())));
      subElement.appendChild(subSubElement);

      rootElement.appendChild(subElement);

    }
    doc.appendChild(rootElement);

    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
        "2");
    File file = new File("./src/XML/BoardGames.xml");
    transformer.transform(new DOMSource(doc), new StreamResult(file));

  }

  static public BoardGamesList READ_BOARD_GAMES_LIST()
      throws ParserConfigurationException, IOException, SAXException,
      TransformerConfigurationException
  {
    BoardGamesList boardGamesList = new BoardGamesList();

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse("./src/XML/BoardGames.xml");
    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "no");

    NodeList rootList = doc.getElementsByTagName("BoardGame");
    for (int i = 0; i < rootList.getLength(); i++)
    {
      int ID = -1;
      String name = null;
      String type = null;
      int numberOfPlayersMin = -1;
      int numberOfPlayersMax = -1;
      String availabilityStatus = null;
      String comment = null;
      int ownerID = -1;
      int numberOfVotes = 0;

      Node rootNode = rootList.item(i);
      NodeList subList = rootNode.getChildNodes();
      Node subNode;
      BoardGame boardGame;

      for (int j = 0; j < subList.getLength(); j++)
      {
        subNode = subList.item(j);
        if (subNode.getNodeName().equals("ID"))
        {
          ID = Integer.parseInt(subNode.getTextContent());
        }
        else if (subNode.getNodeName().equals("name"))
        {
          name = subNode.getTextContent();
        }
        else if (subNode.getNodeName().equals("type"))
        {
          type = subNode.getTextContent();
        }
        else if (subNode.getNodeName().equals("numberOfPlayersMin"))
        {
          numberOfPlayersMin = Integer.parseInt(subNode.getTextContent());
        }
        else if (subNode.getNodeName().equals("numberOfPlayersMax"))
        {
          numberOfPlayersMax = Integer.parseInt(subNode.getTextContent());
        }
        else if (subNode.getNodeName().equals("availabilityStatus"))
        {
          availabilityStatus = subNode.getTextContent();
        }
        else if (subNode.getNodeName().equals("comment"))
        {
          comment = subNode.getTextContent();
        }
        else if (subNode.getNodeName().equals("ownerID"))
        {
          ownerID = Integer.parseInt(subNode.getTextContent());
        }
        else if (subNode.getNodeName().equals("numberOfVotes"))
        {
          numberOfVotes = Integer.parseInt(subNode.getTextContent());
        }

      }
      boardGame = new BoardGame(ID, name, type, numberOfPlayersMin,
          numberOfPlayersMax, availabilityStatus, comment, ownerID,
          numberOfVotes);
      boardGamesList.addBoardGame(boardGame);

    }
    return boardGamesList;
  }

  public static void SAVE_EVENTS_LIST(EventsList eventsList)
      throws ParserConfigurationException, TransformerException
  {

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.newDocument();

    Element rootElement = doc.createElement("EventsList");
    Event event;
    Element subElement;
    Element subSubElement;
    for (int i = 0; i < eventsList.size(); i++)
    {
      event = eventsList.getEvent(i);
      subElement = doc.createElement("Event");

      subSubElement = doc.createElement("ID");
      subSubElement.appendChild(
          doc.createTextNode(Integer.toString(event.getID())));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("name");
      subSubElement.appendChild(doc.createTextNode(event.getName()));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("location");
      subSubElement.appendChild(doc.createTextNode(event.getPlace()));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("fromDate");

      Element subSubSubElement = doc.createElement("day");
      subSubSubElement.appendChild(
          doc.createTextNode(String.valueOf(event.getFrom().getDayOfMonth())));
      subSubElement.appendChild(subSubSubElement);

      subSubSubElement = doc.createElement("month");
      subSubSubElement.appendChild(
          doc.createTextNode(String.valueOf(event.getFrom().getMonthValue())));
      subSubElement.appendChild(subSubSubElement);

      subSubSubElement = doc.createElement("year");
      subSubSubElement.appendChild(
          doc.createTextNode(String.valueOf(event.getFrom().getYear())));
      subSubElement.appendChild(subSubSubElement);

      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("fromHours");
      subSubElement.appendChild(
          doc.createTextNode(String.valueOf(event.getFrom().getHour())));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("fromMinutes");
      subSubElement.appendChild(
          doc.createTextNode(String.valueOf(event.getFrom().getMinute())));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("toDate");

      subSubSubElement = doc.createElement("day");
      subSubSubElement.appendChild(
          doc.createTextNode(String.valueOf(event.getTo().getDayOfMonth())));
      subSubElement.appendChild(subSubSubElement);

      subSubSubElement = doc.createElement("month");
      subSubSubElement.appendChild(
          doc.createTextNode(String.valueOf(event.getTo().getMonthValue())));
      subSubElement.appendChild(subSubSubElement);

      subSubSubElement = doc.createElement("year");
      subSubSubElement.appendChild(
          doc.createTextNode(String.valueOf(event.getTo().getYear())));
      subSubElement.appendChild(subSubSubElement);

      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("toHours");
      subSubElement.appendChild(
          doc.createTextNode(String.valueOf(event.getTo().getHour())));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("toMinutes");
      subSubElement.appendChild(
          doc.createTextNode(String.valueOf(event.getTo().getMinute())));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("description");
      subSubElement.appendChild(doc.createTextNode(event.getDescription()));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("playersIDs");
      for (int ID : event.getParticipantsIDs())
      {
        subSubSubElement = doc.createElement("ID");
        subSubSubElement.appendChild(doc.createTextNode(Integer.toString(ID)));
        subSubElement.appendChild(subSubSubElement);
      }
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("comment");
      subSubElement.appendChild(doc.createTextNode(event.getComment()));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("link");
      subSubElement.appendChild(doc.createTextNode(event.getLink()));
      subElement.appendChild(subSubElement);

      rootElement.appendChild(subElement);

    }
    doc.appendChild(rootElement);

    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
        "2");
    File file = new File("./src/XML/Events.xml");
    transformer.transform(new DOMSource(doc), new StreamResult(file));

  }

  static public EventsList READ_EVENTS_LIST()
      throws ParserConfigurationException, IOException, SAXException,
      TransformerConfigurationException
  {
    EventsList eventsList = new EventsList();

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse("src/XML/Events.xml");
    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "no");

    NodeList rootList = doc.getElementsByTagName("Event");

    for (int i = 0; i < rootList.getLength(); i++)
    {

      int ID = -1;
      String name = null;
      String location = null;
      LocalDate fromDate = null;
      int fromHours = 0;
      int fromMinutes = 0;
      LocalDate toDate = null;
      int toHours = 0;
      int toMinutes = 0;
      String description = null;
      String comment = null;
      String link = null;
      ArrayList<Integer> participantsIDs = new ArrayList<>();

      Node rootNode = rootList.item(i);
      NodeList subList = rootNode.getChildNodes();
      Node subNode;
      Event event;

      for (int j = 0; j < subList.getLength(); j++)
      {
        subNode = subList.item(j);

        if (subNode.getNodeName().equals("ID"))
        {
          ID = Integer.parseInt(subNode.getTextContent());
        }
        else if (subNode.getNodeName().equals("name"))
        {
          name = subNode.getTextContent();
        }
        else if (subNode.getNodeName().equals("location"))
        {
          location = subNode.getTextContent();
        }

        else if (subNode.getNodeName().equals("fromDate"))
        {
          int day = 0;
          int month = 0;
          int year = 0;
          NodeList subSubList = subNode.getChildNodes();
          for (int k = 0; k < subSubList.getLength(); k++)
          {
            Node subSubNode = subSubList.item(k);
            if (subSubNode.getNodeName().equals("day"))
            {
              day = Integer.parseInt(subSubNode.getTextContent());
            }
            else if (subSubNode.getNodeName().equals("month"))
            {
              month = Integer.parseInt(subSubNode.getTextContent());
            }
            else if (subSubNode.getNodeName().equals("year"))
            {
              year = Integer.parseInt(subSubNode.getTextContent());
            }
          }
          fromDate = LocalDate.of(year, month, day);
        }

        else if (subNode.getNodeName().equals("fromHours"))
        {
          String stringFromHours = subNode.getTextContent();
          fromHours = Integer.parseInt(stringFromHours);
        }

        else if (subNode.getNodeName().equals("fromMinutes"))
        {
          String stringFromMinutes = subNode.getTextContent();
          fromMinutes = Integer.parseInt(stringFromMinutes);
        }

        else if (subNode.getNodeName().equals("toDate"))
        {
          int day = 0;
          int month = 0;
          int year = 0;
          NodeList subSubList = subNode.getChildNodes();
          for (int k = 0; k < subSubList.getLength(); k++)
          {
            Node subSubNode = subSubList.item(k);
            if (subSubNode.getNodeName().equals("day"))
            {
              day = Integer.parseInt(subSubNode.getTextContent());
            }
            else if (subSubNode.getNodeName().equals("month"))
            {
              month = Integer.parseInt(subSubNode.getTextContent());
            }
            else if (subSubNode.getNodeName().equals("year"))
            {
              year = Integer.parseInt(subSubNode.getTextContent());
            }
          }
          toDate = LocalDate.of(year, month, day);
        }

        else if (subNode.getNodeName().equals("playersIDs"))
        {
          NodeList subSubList = subNode.getChildNodes();
          for (int k = 0; k < subSubList.getLength(); k++)
          {
            Node subSubNode = subSubList.item(k);
            if (subSubNode.getNodeName().equals("ID"))
            {
              participantsIDs.add(
                  Integer.parseInt(subSubNode.getTextContent()));
            }
          }
        }

        else if (subNode.getNodeName().equals("toHours"))
        {
          String stringToHours = subNode.getTextContent();
          toHours = Integer.parseInt(stringToHours);
        }

        else if (subNode.getNodeName().equals("toMinutes"))
        {
          String stringToMinutes = subNode.getTextContent();
          toMinutes = Integer.parseInt(stringToMinutes);
        }

        else if (subNode.getNodeName().equals("description"))
        {
          description = subNode.getTextContent();
        }
        else if (subNode.getNodeName().equals("comment"))
        {
          comment = subNode.getTextContent();
        }
        else if (subNode.getNodeName().equals("link"))
        {
          link = subNode.getTextContent();
        }
      }
      event = new Event(ID, name, location, fromDate, fromHours, fromMinutes,
          toDate, toHours, toMinutes, description, participantsIDs, comment,
          link);
      eventsList.addEvent(event);
    }
    return eventsList;
  }

  public static void SAVE_RATINGS_LIST(RatingsList ratingsList)
      throws ParserConfigurationException, TransformerException
  {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.newDocument();

    Element rootElement = doc.createElement("RatingsList");
    Rating rating;
    Element subElement;
    Element subSubElement;

    for (int i = 0; i < ratingsList.size(); i++)
    {
      rating = ratingsList.getRating(i);
      subElement = doc.createElement("Rating");

      subSubElement = doc.createElement("ID");
      subSubElement.appendChild(
          doc.createTextNode(Integer.toString(rating.getID())));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("value");
      subSubElement.appendChild(
          doc.createTextNode(Integer.toString(rating.getValue())));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("playerID");
      subSubElement.appendChild(
          doc.createTextNode(Integer.toString(rating.getPlayerID())));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("gameID");
      subSubElement.appendChild(
          doc.createTextNode(Integer.toString(rating.getGameID())));
      subElement.appendChild(subSubElement);

      rootElement.appendChild(subElement);
    }
    doc.appendChild(rootElement);
    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
        "2");
    File file = new File("src/XML/Ratings.xml");
    transformer.transform(new DOMSource(doc), new StreamResult(file));

  }

  static public RatingsList READ_RATINGS_LIST()
      throws ParserConfigurationException, IOException, SAXException,
      TransformerConfigurationException
  {
    RatingsList ratingsList = new RatingsList();

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse("src/XML/Ratings.xml");
    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "no");

    NodeList rootList = doc.getElementsByTagName("Rating");

    for (int i = 0; i < rootList.getLength(); i++)
    {

      int ID = -1;
      int value = -1;
      int playerID = -1;
      int gameID = -1;

      Node rootNode = rootList.item(i);
      NodeList subList = rootNode.getChildNodes();
      Node subNode;
      Rating rating;

      for (int j = 0; j < subList.getLength(); j++)
      {
        subNode = subList.item(j);

        if (subNode.getNodeName().equals("ID"))
        {
          ID = Integer.parseInt(subNode.getTextContent());
        }

        else if (subNode.getNodeName().equals("value"))
        {
          value = Integer.parseInt(subNode.getTextContent());
        }

        else if (subNode.getNodeName().equals("playerID"))
        {
          playerID = Integer.parseInt(subNode.getTextContent());
        }

        else if (subNode.getNodeName().equals("gameID"))
        {
          gameID = Integer.parseInt(subNode.getTextContent());
        }

      }
      rating = new Rating(ID, value, playerID, gameID);
      ratingsList.addRating(rating);
    }
    return ratingsList;
  }

  public static void SAVE_ELECTION(Election election)
      throws ParserConfigurationException, TransformerException
  {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.newDocument();

    Element rootElement = doc.createElement("Election");

    if (!(election == null))
    {

      Element subElement;
      Element subSubElement;

      subElement = doc.createElement("startingDate");
      subSubElement = doc.createElement("day");
      subSubElement.appendChild(doc.createTextNode(
          String.valueOf(election.getStartingDate().getDayOfMonth())));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("month");
      subSubElement.appendChild(doc.createTextNode(
          String.valueOf(election.getStartingDate().getMonthValue())));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("year");
      subSubElement.appendChild(doc.createTextNode(
          String.valueOf(election.getStartingDate().getYear())));
      subElement.appendChild(subSubElement);

      rootElement.appendChild(subElement);

      subElement = doc.createElement("endingDate");
      subSubElement = doc.createElement("day");
      subSubElement.appendChild(doc.createTextNode(
          String.valueOf(election.getEndingDate().getDayOfMonth())));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("month");
      subSubElement.appendChild(doc.createTextNode(
          String.valueOf(election.getEndingDate().getMonthValue())));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("year");
      subSubElement.appendChild(doc.createTextNode(
          String.valueOf(election.getEndingDate().getYear())));
      subElement.appendChild(subSubElement);

      rootElement.appendChild(subElement);
    }

    doc.appendChild(rootElement);

    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
        "2");
    File file = new File("./src/XML/Election.xml");
    transformer.transform(new DOMSource(doc), new StreamResult(file));

  }

  static public Election READ_ELECTION()
      throws ParserConfigurationException, IOException, SAXException,
      TransformerConfigurationException
  {

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse("./src/XML/Election.xml");
    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "no");

    LocalDate startingDate = null;
    LocalDate endingDate = null;

    NodeList rootList = doc.getElementsByTagName("Election");
    Node rootNode;
    try
    {
      rootNode = rootList.item(0);
    }
    catch (IndexOutOfBoundsException e)
    {
      return null;
    }

    NodeList subList = rootNode.getChildNodes();

    for (int i = 0; i < subList.getLength(); i++)
    {

      Node subNode = subList.item(i);

      if (subNode.getNodeName().equals("startingDate"))
      {
        int day = 0;
        int month = 0;
        int year = 0;
        NodeList subSubList = subNode.getChildNodes();
        for (int k = 0; k < subSubList.getLength(); k++)
        {
          Node subSubNode = subSubList.item(k);
          if (subSubNode.getNodeName().equals("day"))
          {
            day = Integer.parseInt(subSubNode.getTextContent());
          }
          else if (subSubNode.getNodeName().equals("month"))
          {
            month = Integer.parseInt(subSubNode.getTextContent());
          }
          else if (subSubNode.getNodeName().equals("year"))
          {
            year = Integer.parseInt(subSubNode.getTextContent());
          }
        }
        startingDate = LocalDate.of(year, month, day);
      }

      else if (subNode.getNodeName().equals("endingDate"))
      {
        int day = 0;
        int month = 0;
        int year = 0;
        NodeList subSubList = subNode.getChildNodes();
        for (int k = 0; k < subSubList.getLength(); k++)
        {
          Node subSubNode = subSubList.item(k);
          if (subSubNode.getNodeName().equals("day"))
          {
            day = Integer.parseInt(subSubNode.getTextContent());
          }
          else if (subSubNode.getNodeName().equals("month"))
          {
            month = Integer.parseInt(subSubNode.getTextContent());
          }
          else if (subSubNode.getNodeName().equals("year"))
          {
            year = Integer.parseInt(subSubNode.getTextContent());
          }
        }
        endingDate = LocalDate.of(year, month, day);
      }
    }
    if (startingDate == null || endingDate == null)
    {
      return null;
    }
    return new Election(startingDate, endingDate);
  }

  public static void SAVE_CURRENT_BORROWINGS(BorrowingsList borrowingsList)
      throws ParserConfigurationException, TransformerException
  {

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.newDocument();

    Element rootElement = doc.createElement("borrowingsList");
    Reservation borrowing;
    Element subElement;
    Element subSubElement;
    for (int i = 0; i < borrowingsList.size(); i++)
    {
      borrowing = borrowingsList.getBorrowing(i);
      subElement = doc.createElement("Borrowing");

      subSubElement = doc.createElement("ID");
      subSubElement.appendChild(
          doc.createTextNode(Integer.toString(borrowing.getID())));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("playerID");
      subSubElement.appendChild(
          doc.createTextNode(Integer.toString(borrowing.getPlayerID())));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("gameID");
      subSubElement.appendChild(
          doc.createTextNode(Integer.toString(borrowing.getGameID())));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("from");

      Element subSubSubElement = doc.createElement("day");
      subSubSubElement.appendChild(doc.createTextNode(
          String.valueOf(borrowing.getFrom().getDayOfMonth())));
      subSubElement.appendChild(subSubSubElement);

      subSubSubElement = doc.createElement("month");
      subSubSubElement.appendChild(doc.createTextNode(
          String.valueOf(borrowing.getFrom().getMonthValue())));
      subSubElement.appendChild(subSubSubElement);

      subSubSubElement = doc.createElement("year");
      subSubSubElement.appendChild(
          doc.createTextNode(String.valueOf(borrowing.getFrom().getYear())));
      subSubElement.appendChild(subSubSubElement);
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("to");

      subSubSubElement = doc.createElement("day");
      subSubSubElement.appendChild(doc.createTextNode(
          String.valueOf(borrowing.getTo().getDayOfMonth())));
      subSubElement.appendChild(subSubSubElement);

      subSubSubElement = doc.createElement("month");
      subSubSubElement.appendChild(doc.createTextNode(
          String.valueOf(borrowing.getTo().getMonthValue())));
      subSubElement.appendChild(subSubSubElement);

      subSubSubElement = doc.createElement("year");
      subSubSubElement.appendChild(
          doc.createTextNode(String.valueOf(borrowing.getTo().getYear())));
      subSubElement.appendChild(subSubSubElement);

      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("comment");
      subSubElement.appendChild(doc.createTextNode(borrowing.getComment()));
      subElement.appendChild(subSubElement);
      rootElement.appendChild(subElement);
    }

    doc.appendChild(rootElement);
    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
        "2");
    File file = new File("./src/XML/CurrentBorrowings.xml");
    transformer.transform(new DOMSource(doc), new StreamResult(file));

  }

  static public BorrowingsList READ_CURRENT_BORROWINGS()
      throws ParserConfigurationException, IOException, SAXException,
      TransformerConfigurationException
  {
    BorrowingsList borrowingsList = new BorrowingsList();

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse("./src/XML/CurrentBorrowings.xml");
    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "no");

    NodeList rootList = doc.getElementsByTagName("Borrowing");

    for (int i = 0; i < rootList.getLength(); i++)
    {
      int ID = -1;
      int playerID = -1;
      int gameID = -1;
      LocalDate from = null;
      LocalDate to = null;
      String comment = null;
      Node rootNode = rootList.item(i);
      NodeList subList = rootNode.getChildNodes();
      Node subNode;
      Reservation borrowing;

      for (int j = 0; j < subList.getLength(); j++)
      {
        subNode = subList.item(j);

        if (subNode.getNodeName().equals("ID"))
        {
          ID = Integer.parseInt(subNode.getTextContent());
        }

        else if (subNode.getNodeName().equals("playerID"))
        {
          playerID = Integer.parseInt(subNode.getTextContent());
        }

        else if (subNode.getNodeName().equals("gameID"))
        {
          gameID = Integer.parseInt(subNode.getTextContent());
        }

        else if (subNode.getNodeName().equals("from"))
        {
          int day = 0;
          int month = 0;
          int year = 0;

          NodeList subSubList = subNode.getChildNodes();
          for (int k = 0; k < subSubList.getLength(); k++)
          {
            Node subSubNode = subSubList.item(k);
            if (subSubNode.getNodeName().equals("day"))
            {
              day = Integer.parseInt(subSubNode.getTextContent());
            }
            else if (subSubNode.getNodeName().equals("month"))
            {
              month = Integer.parseInt(subSubNode.getTextContent());
            }
            else if (subSubNode.getNodeName().equals("year"))
            {
              year = Integer.parseInt(subSubNode.getTextContent());
            }

          }
          from = LocalDate.of(year, month, day);
        }

        else if (subNode.getNodeName().equals("to"))
        {
          int day = 0;
          int month = 0;
          int year = 0;

          NodeList subSubList = subNode.getChildNodes();
          for (int k = 0; k < subSubList.getLength(); k++)
          {
            Node subSubNode = subSubList.item(k);
            if (subSubNode.getNodeName().equals("day"))
            {
              day = Integer.parseInt(subSubNode.getTextContent());
            }
            else if (subSubNode.getNodeName().equals("month"))
            {
              month = Integer.parseInt(subSubNode.getTextContent());
            }
            else if (subSubNode.getNodeName().equals("year"))
            {
              year = Integer.parseInt(subSubNode.getTextContent());
            }

          }
          to = LocalDate.of(year, month, day);
        }

        else if (subNode.getNodeName().equals("comment"))
        {
          comment = subNode.getTextContent();
        }

      }
      borrowing = new Reservation(ID, playerID, gameID, from, to, comment);
      borrowingsList.addBorrowing(borrowing);
    }
    return borrowingsList;
  }

  public static void SAVE_RESERVATIONS_LIST(ReservationsList reservationsList)
      throws ParserConfigurationException, TransformerException
  {

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.newDocument();

    Element rootElement = doc.createElement("reservationsList");
    Reservation reservation;
    Element subElement;
    Element subSubElement;
    for (int i = 0; i < reservationsList.size(); i++)
    {
      reservation = reservationsList.getReservation(i);
      subElement = doc.createElement("reservation");

      subSubElement = doc.createElement("ID");
      subSubElement.appendChild(
          doc.createTextNode(Integer.toString(reservation.getID())));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("playerID");
      subSubElement.appendChild(
          doc.createTextNode(Integer.toString(reservation.getPlayerID())));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("gameID");
      subSubElement.appendChild(
          doc.createTextNode(Integer.toString(reservation.getGameID())));
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("from");

      Element subSubSubElement = doc.createElement("day");
      subSubSubElement.appendChild(doc.createTextNode(
          String.valueOf(reservation.getFrom().getDayOfMonth())));
      subSubElement.appendChild(subSubSubElement);

      subSubSubElement = doc.createElement("month");
      subSubSubElement.appendChild(doc.createTextNode(
          String.valueOf(reservation.getFrom().getMonthValue())));
      subSubElement.appendChild(subSubSubElement);

      subSubSubElement = doc.createElement("year");
      subSubSubElement.appendChild(
          doc.createTextNode(String.valueOf(reservation.getFrom().getYear())));
      subSubElement.appendChild(subSubSubElement);
      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("to");

      subSubSubElement = doc.createElement("day");
      subSubSubElement.appendChild(doc.createTextNode(
          String.valueOf(reservation.getTo().getDayOfMonth())));
      subSubElement.appendChild(subSubSubElement);

      subSubSubElement = doc.createElement("month");
      subSubSubElement.appendChild(doc.createTextNode(
          String.valueOf(reservation.getTo().getMonthValue())));
      subSubElement.appendChild(subSubSubElement);

      subSubSubElement = doc.createElement("year");
      subSubSubElement.appendChild(
          doc.createTextNode(String.valueOf(reservation.getTo().getYear())));
      subSubElement.appendChild(subSubSubElement);

      subElement.appendChild(subSubElement);

      subSubElement = doc.createElement("comment");
      subSubElement.appendChild(doc.createTextNode(reservation.getComment()));
      subElement.appendChild(subSubElement);

      rootElement.appendChild(subElement);

    }

    doc.appendChild(rootElement);
    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
        "2");
    File file = new File("src/XML/Reservations.xml");
    transformer.transform(new DOMSource(doc), new StreamResult(file));

  }

  static public ReservationsList READ_RESERVATIONS()
      throws ParserConfigurationException, IOException, SAXException,
      TransformerConfigurationException
  {
    ReservationsList reservationsList = new ReservationsList();

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse("src/XML/Reservations.xml");
    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "no");

    NodeList rootList = doc.getElementsByTagName("reservation");

    for (int i = 0; i < rootList.getLength(); i++)
    {
      int ID = -1;
      int playerID = -1;
      int gameID = -1;
      LocalDate from = null;
      LocalDate to = null;
      String comment = null;

      Node rootNode = rootList.item(i);
      NodeList subList = rootNode.getChildNodes();
      Node subNode;
      Reservation reservation;

      for (int j = 0; j < subList.getLength(); j++)
      {
        subNode = subList.item(j);

        if (subNode.getNodeName().equals("ID"))
        {
          ID = Integer.parseInt(subNode.getTextContent());
        }

        else if (subNode.getNodeName().equals("playerID"))
        {
          playerID = Integer.parseInt(subNode.getTextContent());
        }

        else if (subNode.getNodeName().equals("gameID"))
        {
          gameID = Integer.parseInt(subNode.getTextContent());
        }

        else if (subNode.getNodeName().equals("from"))
        {
          int day = 0;
          int month = 0;
          int year = 0;

          NodeList subSubList = subNode.getChildNodes();
          for (int k = 0; k < subSubList.getLength(); k++)
          {
            Node subSubNode = subSubList.item(k);
            if (subSubNode.getNodeName().equals("day"))
            {
              day = Integer.parseInt(subSubNode.getTextContent());
            }
            else if (subSubNode.getNodeName().equals("month"))
            {
              month = Integer.parseInt(subSubNode.getTextContent());
            }
            else if (subSubNode.getNodeName().equals("year"))
            {
              year = Integer.parseInt(subSubNode.getTextContent());
            }

          }
          from = LocalDate.of(year, month, day);
        }

        else if (subNode.getNodeName().equals("to"))
        {
          int day = 0;
          int month = 0;
          int year = 0;

          NodeList subSubList = subNode.getChildNodes();
          for (int k = 0; k < subSubList.getLength(); k++)
          {
            Node subSubNode = subSubList.item(k);
            if (subSubNode.getNodeName().equals("day"))
            {
              day = Integer.parseInt(subSubNode.getTextContent());
            }
            else if (subSubNode.getNodeName().equals("month"))
            {
              month = Integer.parseInt(subSubNode.getTextContent());
            }
            else if (subSubNode.getNodeName().equals("year"))
            {
              year = Integer.parseInt(subSubNode.getTextContent());
            }

          }
          to = LocalDate.of(year, month, day);
        }

        else if (subNode.getNodeName().equals("comment"))
        {
          comment = subNode.getTextContent();
        }

      }
      reservation = new Reservation(ID, playerID, gameID, from, to, comment);
      reservationsList.addReservation(reservation);
    }
    return reservationsList;
  }
}
