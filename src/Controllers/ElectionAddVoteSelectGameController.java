package Controllers;

import Application.ViewHandler;
import Model.BoardGame;
import Model.BoardGamesList;
import Model.ConsideredToBeBoughtTable;
import Model.ModelManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class ElectionAddVoteSelectGameController implements Controller
{
  @FXML public ChoiceBox genre;
  @FXML public TableView<ConsideredToBeBoughtTable> gamesTable;
  @FXML public TableColumn<ConsideredToBeBoughtTable, Integer> numberOfVotes;
  @FXML public TableColumn<ConsideredToBeBoughtTable, String> type;
  @FXML public TableColumn<ConsideredToBeBoughtTable, String> name;
  @FXML public TableColumn<ConsideredToBeBoughtTable, String> numberOfPlayers;

  private Region region;
  private ModelManager model;
  private ViewHandler viewHandler;
  private int IDOfPlayer;
  private ObservableList<ConsideredToBeBoughtTable> gamesInTable = FXCollections.observableArrayList();
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
    numberOfPlayers.setCellValueFactory(new PropertyValueFactory<>("numberOfPlayers"));
    numberOfVotes.setCellValueFactory(new PropertyValueFactory<>("numberOfVotes"));
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
    BoardGamesList boardGamesList = model.getBoardGamesList().getConsideredToBeBoughtGames();

    for (int i = boardGamesList.size() -1 ;  i >= 0; i--)
    {
      BoardGame game = boardGamesList.getBoardGame(i);
      String numberOfPlayers = game.getNumberOfPlayersMin() + " - " + game.getNumberOfPlayersMax();
      gamesInTable.add(new ConsideredToBeBoughtTable(game.getName(), game.getType(), numberOfPlayers, game.getNumberOfVotes(),
          game.getID()));
    }
  }
  @FXML public void goBack(){
    viewHandler.openView(33,-1);
  }

  @FXML public void vote()
      throws ParserConfigurationException, TransformerException
  {
    if (gamesTable.getSelectionModel().getSelectedItem() != null)
    {
      model.getPlayersList().getPlayerByID(IDOfPlayer).setVoted(true);
      model.getBoardGamesList().getBoardGameByID(
          gamesTable.getSelectionModel().getSelectedItem().getID()).addVote();
      model.savePlayers();
      model.saveBoardGames();
      viewHandler.openView(32, -1);
    }
  }

  public void clearFilters(ActionEvent actionEvent)
  {
  }
}
