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
import java.util.Optional;

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
    comment.setMouseTransparent(true);
    nameField.setMouseTransparent(true);
    min.setMouseTransparent(true);
    max.setMouseTransparent(true);
    type.setMouseTransparent(true);
    status.setMouseTransparent(true);
    owner.setMouseTransparent(true);
    numberOfVotes.setMouseTransparent(true);

    comment.setFocusTraversable(false);
    nameField.setFocusTraversable(false);
    min.setFocusTraversable(false);
    max.setFocusTraversable(false);
    type.setFocusTraversable(false);
    status.setFocusTraversable(false);
    owner.setFocusTraversable(false);
    numberOfVotes.setFocusTraversable(false);
    if (boardGame.getOwnerID() == 0)
    {
      owner.setText("Association");
      selectAsocAsOwner.setMouseTransparent(true);
      selectAsocAsOwner.setFocusTraversable(false);
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

    comment.setMouseTransparent(false);
    nameField.setMouseTransparent(false);
    min.setMouseTransparent(false);
    max.setMouseTransparent(false);
    type.setMouseTransparent(false);
    status.setMouseTransparent(false);

    comment.setFocusTraversable(true);
    nameField.setFocusTraversable(true);
    min.setFocusTraversable(true);
    max.setFocusTraversable(true);
    type.setFocusTraversable(true);
    status.setFocusTraversable(true);
    //numberOfVotes.setDisable(false);
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
      int ownerName = Integer.parseInt(owner.getText());
      try{
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


      } catch (Exception e){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Data");
        alert.setHeaderText(e.getMessage());
        Optional<ButtonType> result = alert.showAndWait();
      }

    };

    edit.setOnAction(save);
  }

  public void delete() throws ParserConfigurationException, TransformerException
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Are you sure you want to delete the player ");
    Optional<ButtonType> result = alert.showAndWait();
    if(result.isPresent() && result.get() == ButtonType.OK){
    model.getBoardGamesList().deleteByID(ID);
    model.saveBoardGames();
    viewHandler.openView(3, -1);
    }
  }

  public void goBack()
  {
    viewHandler.openView(3, -1);
  }


  public void selectAsocAsOwner()
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

  public void showRatings(ActionEvent actionEvent)
  {
    viewHandler.openView(3, ID);
  }
}
