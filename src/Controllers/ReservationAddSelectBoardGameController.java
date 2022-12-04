package Controllers;

import Application.ViewHandler;
import Model.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

public class ReservationAddSelectBoardGameController implements Controller
{

  @FXML public TableView<BoardgameTable> gamesTable;
  @FXML public TableColumn<BoardgameTable, Integer> availability;
  @FXML public TableColumn<BoardgameTable, String> type;
  @FXML public TableColumn<BoardgameTable, String> name;
  @FXML public TableColumn<BoardgameTable, String> numberOfPlayers;
  public ChoiceBox status;
  public ChoiceBox genre;
  private String genreValue;
  private String statusValue;
  public TextField numberOfPlayersFilter;
  public TextField searchField;

  private Region region;
  private ModelManager model;
  private ViewHandler viewHandler;
  private int IDOfPlayer;
  private ObservableList<BoardgameTable> gamesInTable = FXCollections.observableArrayList();

  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;
    IDOfPlayer = ID;

    fillData();
    ObservableList<String> items = FXCollections.observableArrayList(
        BoardGame.getAllowedTypes());
    genre.setItems(items);
    ObservableList<String> items2 = FXCollections.observableArrayList(
        BoardGame.getAllowedStatuses());
    status.setItems(items2);

    genre.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> {
          System.out.println(newValue.toString());
          getGenre(observable, oldValue, newValue);
          fillData();

        });
    status.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> {
          System.out.println(newValue.toString());
          getStatus(observable, oldValue, newValue);
          fillData();

        });

    name.setCellValueFactory(new PropertyValueFactory<>("name"));
    type.setCellValueFactory(new PropertyValueFactory<>("type"));
    availability.setCellValueFactory(new PropertyValueFactory<>("availability"));
    numberOfPlayers.setCellValueFactory(new PropertyValueFactory<>("numberOfPlayers"));
    gamesTable.setItems(gamesInTable);
  }

  @Override public Region getRegion()
  {
    return region;
  }

  @Override public void reset()
  {

  }

  @FXML public void fillData(){
    gamesInTable.clear();
    BoardGamesList boardGamesList;

    boardGamesList = model.getBoardGamesList()
        .getBoardGameListByStatus(BoardGame.AVAILABLE_STATUS);

    boardGamesList = boardGamesList.filterBoardGameList(searchField.getText());
    String numberString = numberOfPlayersFilter.getText();

    if (!numberString.equals(""))
    {
      int number = Integer.parseInt(numberOfPlayersFilter.getText());
      boardGamesList = boardGamesList.getBoardGameListByNumberOfPlayers(number);
    }

    if (genreValue != null)
    {
      boardGamesList = boardGamesList.getBoardGameListByGenre(genreValue);
    }
    if (statusValue != null)
    {
      boardGamesList = boardGamesList.getBoardGameListByStatus(statusValue);
    }



    for (int i = boardGamesList.size() -1 ;  i >= 0; i--)
    {
      BoardGame game = boardGamesList.getBoardGame(i);
      String numberOfPlayers = game.getNumberOfPlayersMin() + " - " + game.getNumberOfPlayersMax();
      gamesInTable.add(new BoardgameTable(game.getName(), game.getType(), game.getAvailabilityStatus(), numberOfPlayers,
          game.getID()));
    }
  }

  @FXML public void goBack(){
    viewHandler.openView(202,-1);
}

  @FXML public void confirmBoardGame(){
    System.out.println("hekko");
    viewHandler.openView(204,IDOfPlayer, gamesTable.getSelectionModel().getSelectedItem().getID());
  }
  public void getGenre(ObservableValue observableValue, Object oldValue,
      Object newValue)
  {
    this.genreValue = newValue.toString();

  }

  public void getStatus(ObservableValue observableValue, Object oldValue,
      Object newValue)
  {
    this.statusValue = newValue.toString();

  }
  public void clearFilters(ActionEvent actionEvent)
  {
    genre.setValue("");
    status.setValue("");
    gamesInTable.clear();
    BoardGamesList boardGamesList;

    boardGamesList = model.getBoardGamesList()
        .getBoardGameListByStatus(BoardGame.AVAILABLE_STATUS);

    boardGamesList = boardGamesList.filterBoardGameList(searchField.getText());
    String numberString = numberOfPlayersFilter.getText();
    if (!numberString.equals(""))
    {
      int number = Integer.parseInt(numberOfPlayersFilter.getText());
      boardGamesList = boardGamesList.getBoardGameListByNumberOfPlayers(number);
    }
    for (int i = boardGamesList.size() -1 ;  i >= 0; i--)
    {
      BoardGame game = boardGamesList.getBoardGame(i);
      String numberOfPlayers = game.getNumberOfPlayersMin() + " - " + game.getNumberOfPlayersMax();
      gamesInTable.add(new BoardgameTable(game.getName(), game.getType(), game.getAvailabilityStatus(), numberOfPlayers,
          game.getID()));
    }
  }

}
