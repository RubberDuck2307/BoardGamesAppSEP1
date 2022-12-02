package Controllers;

import Application.ViewHandler;
import Model.BoardGame;
import Model.BoardGamesList;
import Model.BoardgameTable;
import Model.ModelManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

public class BorrowingsAddSelectBoardGameController implements Controller
{
  public TextField serach;
  public TableColumn<Object, Object> name;
  public TableColumn<Object, Object> type;
  public TableColumn<Object, Object> availability;
  public TableColumn<Object, Object> numberofPlayers;
  public TableView<BoardgameTable> boardGameTable;
  Region region;
  ModelManager model;
  ViewHandler viewHandler;
  private int ID;
  ObservableList<BoardgameTable> boardGameTables = FXCollections.observableArrayList();

  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;
    this.ID = ID;
    BoardGamesList boardGamesList;
    boardGamesList = model.getBoardGamesList();
    for (int i = 0; i < boardGamesList.size(); i++) {
      BoardGame boardGame = boardGamesList.getBoardGame(i);
      String numberOfPlayer = boardGame.getNumberOfPlayersMin() + " - " + boardGame.getNumberOfPlayersMax();
      boardGameTables.add(new BoardgameTable(boardGame.getName(), boardGame.getType(), boardGame.getAvailabilityStatus(),numberOfPlayer, boardGame.getID()));
    }
    name.setCellValueFactory(new PropertyValueFactory<>("name"));
    type.setCellValueFactory(new PropertyValueFactory<>("type"));
    availability.setCellValueFactory(
        new PropertyValueFactory<>("availability"));
    numberofPlayers.setCellValueFactory(
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
    viewHandler.openView(135, ID,
        boardGameTable.getSelectionModel().getSelectedItem().getID());
  }
  public void goBack()
  {
    viewHandler.openView(6, -1);
  }
}
