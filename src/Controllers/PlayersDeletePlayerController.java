package Controllers;

import Application.ViewHandler;
import Model.BoardGame;
import Model.BoardGamesList;
import Model.RatingsList;
import TableClasses.ConsideredToBeBoughtTable;
import Model.ModelManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.Optional;

public class PlayersDeletePlayerController implements Controller
{
  @FXML public TableView<ConsideredToBeBoughtTable> gamesTable;
  @FXML public TableColumn<ConsideredToBeBoughtTable, Integer> numberOfVotes;
  @FXML public TableColumn<ConsideredToBeBoughtTable, String> type;
  @FXML public TableColumn<ConsideredToBeBoughtTable, String> name;
  @FXML public TableColumn<ConsideredToBeBoughtTable, String> numberOfPlayers;
  private ObservableList<ConsideredToBeBoughtTable> gamesInTable = FXCollections.observableArrayList();
  private Region region;
  private ModelManager model;
  private ViewHandler viewHandler;
  private int ID;

  private BoardGamesList boardGamesList;

  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;
    this.ID = ID;


    fillTable();



    name.setCellValueFactory(new PropertyValueFactory<>("name"));
    type.setCellValueFactory(new PropertyValueFactory<>("type"));
    numberOfPlayers.setCellValueFactory(new PropertyValueFactory<>("numberOfPlayers"));
    numberOfVotes.setCellValueFactory(new PropertyValueFactory<>("numberOfVotes"));
    gamesTable.setItems(gamesInTable);
  }

  @Override public Region getRegion()
  {
    return region;
  }

  @FXML public void showBoardGame(){
    viewHandler.openView(9, gamesTable.getSelectionModel().getSelectedItem().getID());
  }

  @FXML public void delete()
      throws ParserConfigurationException, TransformerException
  //TODO delete reservation
  {
    boolean borrowed = false;
    for (int i = 0; i < boardGamesList.size(); i++)
    {
      if (model.getBorrowingsList().getByGameID(boardGamesList.getBoardGame(i).getID()).size() < 1)
      {
        model.getBoardGamesList().deleteByID(boardGamesList.getBoardGame(i).getID());
        RatingsList ratingsList = model.getRatingsByGame(boardGamesList.getBoardGame(i).getID());
        for(int j = 0; i<ratingsList.size(); j++){
          model.getRatings().deleteByID(ratingsList.getRating(i).getID());
          model.saveRatings();
        }
      }
      else {
        borrowed = true;
        boardGamesList.getBoardGame(i).setAvailabilityStatus(BoardGame.UNAVAILABLE_STATUS);
      }
    }
    model.saveBoardGames();
    if(borrowed){
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Game is borrowed");
      alert.setHeaderText("One of the players games is currently borrowed, for that reason player cannot be deleted. The game was set to unavailable and the rest of them was deleted");
      Optional<ButtonType> result = alert.showAndWait();
      fillTable();
    }
    else {
      model.getPlayersList().deleteByID(ID);
      viewHandler.openView(2,-1);
      model.savePlayers();
    }
  }

  @Override public void reset()
  {

  }
  @FXML public void goBack(){
    viewHandler.openView(8,ID);
  }

  public void fillTable(){
    gamesInTable.clear();

    boardGamesList = model.getBoardGamesList().findByOwnership(ID);

    for (int i = boardGamesList.size() -1 ;  i >= 0; i--)
    {
      BoardGame game = boardGamesList.getBoardGame(i);
      String numberOfPlayers = game.getNumberOfPlayersMin() + " - " + game.getNumberOfPlayersMax();
      gamesInTable.add(new ConsideredToBeBoughtTable(game.getName(), game.getType(), numberOfPlayers, game.getNumberOfVotes(),
          game.getID()));
    }
  }
}
