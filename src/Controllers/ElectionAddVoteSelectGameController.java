package Controllers;

import Application.ViewHandler;
import Model.BoardGame;
import Model.BoardGamesList;
import Model.ConsideredToBeBoughtTable;
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
  public TextField searchField;
  public TextField numberOfPlayersFilter;

  private Region region;
  private ModelManager model;
  private ViewHandler viewHandler;
  private int IDOfPlayer;
  private String genreValue;
  private ObservableList<ConsideredToBeBoughtTable> gamesInTable = FXCollections.observableArrayList();
  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;
    IDOfPlayer = ID;

    fillData();
    ObservableList<String> items = FXCollections.observableArrayList(
        BoardGame.ALLOWED_TYPES);
    genre.setItems(items);

    genre.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
      getGenre(observable, oldValue, newValue);
      fillData();

    });

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
    gamesInTable.clear();
    BoardGamesList boardGamesList;
    boardGamesList = model.getBoardGamesList().getBoardGameListByStatus(BoardGame.CONSIDERED_TO_BE_BOUGHT_STATUS);
    boardGamesList = boardGamesList.filterBoardGameList(searchField.getText());
    String numberString = numberOfPlayersFilter.getText();

    if (!numberString.equals(""))
    {
      int number = Integer.parseInt(numberOfPlayersFilter.getText());
      boardGamesList = boardGamesList.getBoardGameListByNumberOfPlayers(number);
    }

    if (genreValue != null)
    {
      boardGamesList = boardGamesList.getBoardGameListByType(genreValue);
    }
    for (int i = boardGamesList.size() -1 ;  i >= 0; i--)
    {
      BoardGame game = boardGamesList.getBoardGame(i);
      String numberOfPlayers = game.getNumberOfPlayersMin() + " - " + game.getNumberOfPlayersMax();
      gamesInTable.add(new ConsideredToBeBoughtTable(game.getName(), game.getType(), numberOfPlayers, game.getNumberOfVotes(),
          game.getID()));
    }
  }
  public void getGenre(ObservableValue observableValue, Object oldValue, Object newValue)
  {
    this.genreValue = newValue.toString();

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
