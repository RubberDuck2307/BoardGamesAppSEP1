package Controllers;

import Application.ViewHandler;
import Model.BoardGame;
import Model.BoardGamesList;
import Model.BoardgameTable;
import Model.ModelManager;
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

public class BorrowingsAddSelectBoardGameController implements Controller
{
  public TableColumn<Object, Object> name;
  public TableColumn<Object, Object> type;
  public TableColumn<Object, Object> availability;
  public TableColumn<Object, Object> numberOfPlayers;
  public TableView<BoardgameTable> boardGameTable;
  public ChoiceBox status;
  public TextField numberOfPlayersFilter;
  public TextField searchField;
  public ChoiceBox genre;
  Region region;
  ModelManager model;
  ViewHandler viewHandler;
  private String genreValue;
  private String statusValue;
  private int ID;

  ObservableList<BoardgameTable> boardGameTables = FXCollections.observableArrayList();

  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;
    this.ID = ID;
    fillTable();
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
          fillTable();

        });
    status.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> {
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

  @Override public void reset()
  {

  }

  @FXML private void chooseBoardgame()
  {
    if (boardGameTable.getSelectionModel().getSelectedItem() != null)
    {
      viewHandler.openView(135, ID,
          boardGameTable.getSelectionModel().getSelectedItem().getID());
    }
  }

  public void goBack()
  {
    viewHandler.openView(133, -1);
  }

  public void fillTable()
  {
    boardGameTables.clear();
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
    for (int i = 0; i < boardGamesList.size(); i++)
    {
      BoardGame boardGame = boardGamesList.getBoardGame(i);
      String numberOfPlayer = boardGame.getNumberOfPlayersMin() + " - "
          + boardGame.getNumberOfPlayersMax();
      boardGameTables.add(
          new BoardgameTable(boardGame.getName(), boardGame.getType(),
              boardGame.getAvailabilityStatus(), numberOfPlayer,
              boardGame.getID()));
    }
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
    genreValue = null;
    statusValue = null;
    searchField.setText("");
    numberOfPlayersFilter.setText("");
    boardGameTables.clear();
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

    for (int i = 0; i < boardGamesList.size(); i++)
    {
      BoardGame boardGame = boardGamesList.getBoardGame(i);
      String numberOfPlayer = boardGame.getNumberOfPlayersMin() + " - "
          + boardGame.getNumberOfPlayersMax();
      boardGameTables.add(
          new BoardgameTable(boardGame.getName(), boardGame.getType(),
              boardGame.getAvailabilityStatus(), numberOfPlayer,
              boardGame.getID()));
    }
  }
}
