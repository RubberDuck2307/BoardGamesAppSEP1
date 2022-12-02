package Controllers;

import Application.ViewHandler;
import Model.*;
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
  public Slider numberOfplayers;
  public ChoiceBox genre;
  public ChoiceBox status;
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
    //fillTable();
    ObservableList<BoardgameTable> boardGameTables = FXCollections.observableArrayList();
    ObservableList<String> items = FXCollections.observableArrayList(
        BoardGame.getAllowedTypes());
    genre.setItems(items);
    ObservableList<String> items2 = FXCollections.observableArrayList(
        BoardGame.getAllowedStatuses());
    status.setItems(items2);

    BoardGamesList boardGamesList;

    if (ID == -1){
      boardGamesList = model.getBoardGamesList();
    }

    else {
      addButton.setVisible(false);
      boardGamesList = model.getBoardGamesByOwnership(ID);
      System.out.println(boardGamesList);
    }
        for (int i = 0; i < boardGamesList.size(); i++) {
            BoardGame boardGame = boardGamesList.getBoardGame(i);
            String numberOfPlayer = boardGame.getNumberOfPlayersMin() + " - " + boardGame.getNumberOfPlayersMax();
            boardGameTables.add(new BoardgameTable(boardGame.getName(), boardGame.getType(), boardGame.getAvailabilityStatus(),numberOfPlayer, boardGame.getID()));
        }

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
    viewHandler.openView(9,
        boardGameTable.getSelectionModel().getSelectedItem().getID());
  }

  @Override public void reset()
  {

  }

  public void addBoardgame()
  {
    viewHandler.openView(132, -1);
  }

  /*public void fillTable()
  {
    boardGameTables.clear();
    BoardGamesList boardGamesList = model.getBoardGamesList();
    boardGamesList = boardGamesList.filterBoardGameList(searchField.getText());
    String status1 = (String) status.getValue();
    System.out.println("Toto je status " + status1);
    String genre1 = (String) genre.getValue();
    boolean statusFilter = false;
    boolean genreFilter = false;
    boolean numberFilter = false;
    if (status1 != null)
    {
      statusFilter = true;
      //boardGamesList = boardGamesList.getBoardgameListByStatus(status1);
    }
    if (genre1 != null)
    {
      genreFilter = true;
      //boardGamesList = boardGamesList.getBoardgameListByGenre(genre1);
    }
    if (numberOfplayers != null)
    {
      double numberOfPlayers = numberOfplayers.getValue();
      int numberOfPlayers2 = (int) Math.floor(numberOfPlayers);
      System.out.println("Number of players: " + numberOfPlayers2);
      if (numberOfPlayers2 != 1)
      {
        numberFilter = true;
      }

    }
    if (statusFilter && genreFilter && numberFilter)
    {
      double numberOfPlayers = numberOfplayers.getValue();
      int numberOfPlayers2 = (int) Math.floor(numberOfPlayers);
      boardGamesList = boardGamesList.getBoardgameListByGenre(genre1);
      boardGamesList = boardGamesList.getBoardgameListByNumbeOfPlayers(numberOfPlayers2);
      boardGamesList = boardGamesList.getBoardgameListByStatus(status1);

    }
    else if (genreFilter && numberFilter)
    {
      double numberOfPlayers = numberOfplayers.getValue();
      int numberOfPlayers2 = (int) Math.floor(numberOfPlayers);
      boardGamesList = boardGamesList.getBoardgameListByGenre(genre1);
      boardGamesList = boardGamesList.getBoardgameListByNumbeOfPlayers(numberOfPlayers2);

    }
    else if (statusFilter && numberFilter)
    {
      double numberOfPlayers = numberOfplayers.getValue();
      int numberOfPlayers2 = (int) Math.floor(numberOfPlayers);
      boardGamesList = boardGamesList.getBoardgameListByNumbeOfPlayers(numberOfPlayers2);
      boardGamesList = boardGamesList.getBoardgameListByStatus(status1);
    }
    else if (statusFilter && genreFilter)
    {
      boardGamesList = boardGamesList.getBoardgameListByGenreAndStatus(genre1,
          status1);
    }
    else if (statusFilter)
    {
      boardGamesList = boardGamesList.getBoardgameListByStatus(status1);
    }
    else if (genreFilter)
    {
      boardGamesList = boardGamesList.getBoardgameListByGenre(genre1);

    }
    else if (numberFilter)
    {
      double numberOfPlayers = numberOfplayers.getValue();
      int numberOfPlayers2 = (int) Math.floor(numberOfPlayers);
      //System.out.println("Number of players: " + numberOfPlayers2);
      boardGamesList = boardGamesList.getBoardgameListByNumbeOfPlayers(
          numberOfPlayers2);

    }

    if (boardGamesList.size() != 0)
    {
      for (int i = 0; i < boardGamesList.size(); i++)
      {
        BoardGame boardGame = boardGamesList.getBoardGame(i);
        System.out.println(boardGame.toString());
        String numberOfPlayer = boardGame.getNumberOfPlayersMin() + " - "
            + boardGame.getNumberOfPlayersMax();
        boardGameTables.add(
            new BoardgameTable(boardGame.getName(), boardGame.getType(),
                boardGame.getAvailabilityStatus(), numberOfPlayer,
                boardGame.getID()));
      }
    }

  }*/
}
