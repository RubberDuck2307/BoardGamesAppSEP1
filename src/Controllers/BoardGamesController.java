package Controllers;

import Application.ViewHandler;
import Model.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

public class BoardGamesController implements Controller
{

  public TextField searchField;
  public ChoiceBox genre;
  public ChoiceBox status;
  public TextField numberOfPlayersFilter;
  Region region;
  ModelManager model;
  ViewHandler viewHandler;

  private int ID;
  @FXML public TableView<BoardgameTable> boardGameTable;
  @FXML public TableColumn<BoardgameTable, String> name;
  @FXML public TableColumn<BoardgameTable, String> type;
  @FXML public TableColumn<BoardgameTable, String> availability;
  @FXML public TableColumn<BoardgameTable, String> numberOfPlayers;
  @FXML public Button addButton;
  private String genreValue;
  private String statusValue;
  ObservableList<BoardgameTable> boardGameTables = FXCollections.observableArrayList();

  public BoardGamesController()
  {
  }

  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;
    this.ID = ID;
    fillTable();
    //ObservableList<BoardgameTable> boardGameTables = FXCollections.observableArrayList();
    ObservableList<String> items = FXCollections.observableArrayList(
        BoardGame.getAllowedTypes());
    genre.setItems(items);
    ObservableList<String> items2 = FXCollections.observableArrayList(
        BoardGame.getAllowedStatuses());
    status.setItems(items2);

    genre.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
      System.out.println(newValue.toString());
      getGenre(observable, oldValue, newValue);
      fillTable();


    });
    status.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
      System.out.println(newValue.toString());
      getStatus(observable, oldValue, newValue);
      fillTable();


    });
    name.setCellValueFactory(new PropertyValueFactory<>("name"));
    type.setCellValueFactory(new PropertyValueFactory<>("type"));
    availability.setCellValueFactory(
        new PropertyValueFactory<>("availability"));
    numberOfPlayers.setCellValueFactory(
        new PropertyValueFactory<>("numberOfPlayers"));
    boardGameTable.setItems(boardGameTables);

  }

  @Override public Region getRegion()
  {
    return region;
  }

  @FXML public void backToHomePage()
  {
    if (ID == -1)
    {
      viewHandler.openView(1, -1);
    }
    else{
     viewHandler.openView(8, ID);
      }
  }

  @FXML private void chooseBoardgame()
  {
    if (boardGameTable.getSelectionModel().getSelectedItem() != null)
    {
      viewHandler.openView(9,
          boardGameTable.getSelectionModel().getSelectedItem().getID());
    }
  }

  @Override public void reset()
  {

  }

  public void addBoardgame()
  {
    viewHandler.openView(132, -1);
  }
  public void fillTable(){
    boardGameTables.clear();
    BoardGamesList boardGamesList;

    if (ID == -1){
      boardGamesList = model.getBoardGamesList();
    }

    else {
      addButton.setVisible(false);
      boardGamesList = model.getBoardGamesByOwnership(ID);
      System.out.println(boardGamesList);
    }
    boardGamesList = boardGamesList.filterBoardGameList(searchField.getText());
    String numberString = numberOfPlayersFilter.getText();


    if(!numberString.equals("")){
      int number = Integer.parseInt(numberOfPlayersFilter.getText());
      boardGamesList = boardGamesList.getBoardGameListByNumberOfPlayers(number);
    }





    if(genreValue!=null){
      boardGamesList = boardGamesList.getBoardGameListByGenre(genreValue);
    }
    if(statusValue!=null){
      boardGamesList = boardGamesList.getBoardGameListByStatus(statusValue);
    }
    for (int i = 0; i < boardGamesList.size(); i++) {
      BoardGame boardGame = boardGamesList.getBoardGame(i);
      String numberOfPlayer = boardGame.getNumberOfPlayersMin() + " - " + boardGame.getNumberOfPlayersMax();
      boardGameTables.add(new BoardgameTable(boardGame.getName(), boardGame.getType(), boardGame.getAvailabilityStatus(),numberOfPlayer, boardGame.getID()));
    }
  }

  public void getGenre(ObservableValue observableValue, Object oldValue, Object newValue){
    this.genreValue = newValue.toString();

  }
  public void getStatus(ObservableValue observableValue, Object oldValue, Object newValue){
    this.statusValue = newValue.toString();

  }

  public void clearFilters(ActionEvent actionEvent)
  {
    genre.setValue("");
    status.setValue("");
    boardGameTables.clear();
    BoardGamesList boardGamesList;
    if (ID == -1){
      boardGamesList = model.getBoardGamesList();
    }
     else {
    addButton.setVisible(false);
    boardGamesList = model.getBoardGamesByOwnership(ID);
    System.out.println(boardGamesList);
  }
    boardGamesList = boardGamesList.filterBoardGameList(searchField.getText());
    String numberString = numberOfPlayersFilter.getText();


    if(!numberString.equals("")){
      int number = Integer.parseInt(numberOfPlayersFilter.getText());
      boardGamesList = boardGamesList.getBoardGameListByNumberOfPlayers(number);
    }
    for (int i = 0; i < boardGamesList.size(); i++) {
      BoardGame boardGame = boardGamesList.getBoardGame(i);
      String numberOfPlayer = boardGame.getNumberOfPlayersMin() + " - " + boardGame.getNumberOfPlayersMax();
      boardGameTables.add(new BoardgameTable(boardGame.getName(), boardGame.getType(), boardGame.getAvailabilityStatus(),numberOfPlayer, boardGame.getID()));
    }
  }

}
