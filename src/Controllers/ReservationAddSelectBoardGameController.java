package Controllers;

import Application.ViewHandler;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

public class ReservationAddSelectBoardGameController implements Controller
{

  @FXML public TableView<BoardgameTable> gamesTable;
  @FXML public TableColumn<BoardgameTable, Integer> availability;
  @FXML public TableColumn<BoardgameTable, String> type;
  @FXML public TableColumn<BoardgameTable, String> name;
  @FXML public TableColumn<BoardgameTable, String> numberOfPlayers;

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
    BoardGamesList boardGamesList = model.getBoardGamesList();

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
    viewHandler.openView(204,IDOfPlayer, gamesTable.getSelectionModel().getSelectedItem().getID());
  }
}
