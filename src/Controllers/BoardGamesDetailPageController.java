package Controllers;

import Application.ViewHandler;
import Model.BoardGame;
import Model.ModelManager;
import Model.Player;
import Model.PlayersList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.time.LocalDate;

public class BoardGamesDetailPageController implements Controller
{
  public PlayersList playersList;
  public Player player;
  public Label heading;
  public TextArea comment;
  public TextField nameField;
  public Button edit;
  public TextField min;
  public TextField max;
  public ChoiceBox type;
  public ChoiceBox status;
  public TextField owner;
  public TextField numberOfVotes;
  public Button selectAsocAsOwner;
  private Region region;
  private ModelManager model;
  private ViewHandler viewHandler;
  private int ID;
  private EventHandler save;

  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;
    this.ID = ID;
    setData();
  }

  public void setData()
  {
    BoardGame boardGame = model.getBoardGamesList().getBoardGameByID(ID);
    PlayersList playersList = model.getPlayersList();
    heading.setText(boardGame.getName());
    comment.setText(boardGame.getComment());
    nameField.setText(boardGame.getName());
    min.setText(boardGame.getNumberOfPlayersMin() + "");
    max.setText(boardGame.getNumberOfPlayersMax() + "");
    numberOfVotes.setText(boardGame.getNumberOfVotes() + "");
    comment.setDisable(true);
    nameField.setDisable(true);
    min.setDisable(true);
    max.setDisable(true);
    type.setDisable(true);
    status.setDisable(true);
    owner.setDisable(true);
    numberOfVotes.setDisable(true);
    if (boardGame.getOwnerID() == 0)
    {
      owner.setText("Association");
      selectAsocAsOwner.setDisable(true);
    }
    else
    {
      owner.setText(playersList.getNameByID(boardGame.getOwnerID()));
    }
    ObservableList<String> items = FXCollections.observableArrayList(
        BoardGame.getAllowedTypes());
    type.setItems(items);
    ObservableList<String> items2 = FXCollections.observableArrayList(
        BoardGame.getAllowedStatuses());
    status.setItems(items2);
    type.setValue(boardGame.getType());
    status.setValue(boardGame.getAvailabilityStatus());
  }

  @Override public Region getRegion()
  {
    return region;
  }

  @Override public void reset()
  {

  }

  public void edit(ActionEvent actionEvent)
  {

    comment.setDisable(false);
    nameField.setDisable(false);
    min.setDisable(false);
    max.setDisable(false);
    type.setDisable(false);
    status.setDisable(false);
    numberOfVotes.setDisable(false);
    edit.setText("Save Changes");
    save = event -> {
      //System.out.println("hello");
      String name = nameField.getText();
      String comments = comment.getText();
      int minimum = Integer.parseInt(min.getText());
      int maximum = Integer.parseInt(max.getText());
      int numberOfVotesOfGame = Integer.parseInt(numberOfVotes.getText());
      String typeOfGame = type.getValue().toString();
      String statusOfGame = status.getValue().toString();
      int ownerName = 1;
      if (BoardGame.validateData(name, minimum, maximum))
      {
        BoardGame boardGame = new BoardGame(ID, name, typeOfGame, minimum,
            maximum, statusOfGame, comments, ownerName, numberOfVotesOfGame);
        model.setBoardGame(boardGame, ID);
        try
        {
          model.saveBoardGames();
        }
        catch (ParserConfigurationException e)
        {
          throw new RuntimeException(e);
        }
        catch (TransformerException e)
        {
          throw new RuntimeException(e);
        }
        setData();
        edit.setOnAction(this::edit);
        edit.setText("Edit");

      }
      else
      {
      }

    };

    edit.setOnAction(save);
  }

  public void delete() throws ParserConfigurationException, TransformerException
  {
    model.getBoardGamesList().deleteByID(ID);
    model.saveBoardGames();
    viewHandler.openView(3, -1);

  }

  public void goBack()
  {
    viewHandler.openView(3, -1);
  }

  public void selectOwner(ActionEvent actionEvent)
  {
    viewHandler.openView(2, -1);
  }

  public void selectAsocAsOwner(ActionEvent actionEvent)
  {
    //System.out.println("hello");
    String name = nameField.getText();
    String comments = comment.getText();
    int minimum = Integer.parseInt(min.getText());
    int maximum = Integer.parseInt(max.getText());
    int numberOfVotesOfGame = Integer.parseInt(numberOfVotes.getText());
    String typeOfGame = type.getValue().toString();
    String statusOfGame = status.getValue().toString();
    int ownerName = 0;
    if (BoardGame.validateData(name, minimum, maximum))
    {
      BoardGame boardGame = new BoardGame(ID, name, typeOfGame, minimum,
          maximum, statusOfGame, comments, ownerName, numberOfVotesOfGame);
      model.setBoardGame(boardGame, ID);
      try
      {
        model.saveBoardGames();
      }
      catch (ParserConfigurationException e)
      {
        throw new RuntimeException(e);
      }
      catch (TransformerException e)
      {
        throw new RuntimeException(e);
      }
      setData();
    }

  }

}
