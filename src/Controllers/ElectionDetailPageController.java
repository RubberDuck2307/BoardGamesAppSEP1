package Controllers;

import Application.ViewHandler;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.time.LocalDate;
import java.util.Optional;

public class ElectionDetailPageController implements Controller
{
  @FXML public DatePicker startingDate;
  @FXML public DatePicker endingDate;
  @FXML public TableView<ConsideredToBeBoughtTable> gamesTable;
  @FXML public TableColumn<ConsideredToBeBoughtTable, Integer> numberOfVotes;
  @FXML public TableColumn<ConsideredToBeBoughtTable, Integer> position;
  @FXML public TableColumn<ConsideredToBeBoughtTable, String> name;
  @FXML public Button finishElectionButton;
  private Region region;
  private ModelManager model;
  private ViewHandler viewHandler;
  private Election election;

  private ObservableList<ConsideredToBeBoughtTable> gamesInTable = FXCollections.observableArrayList();

  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;
    this.election = model.getElection();

    if (!LocalDate.now().isAfter(election.getEndingDate()))
    {
      finishElectionButton.setDisable(true);
    }

    BoardGamesList boardGamesList = model.getBoardGamesList()
        .getConsideredToBeBoughtGames();
    int j = 1;
    for (int i = boardGamesList.size() - 1; i >= 0; i--)
    {
      BoardGame game = boardGamesList.getBoardGame(i);

      gamesInTable.add(new ConsideredToBeBoughtTable(game.getName(), j,
          game.getNumberOfVotes(), game.getID()));
      j++;
    }
    name.setCellValueFactory(new PropertyValueFactory<>("name"));
    position.setCellValueFactory(new PropertyValueFactory<>("position"));
    numberOfVotes.setCellValueFactory(
        new PropertyValueFactory<>("numberOfVotes"));
    gamesTable.setItems(gamesInTable);

    startingDate.setValue(election.getStartingDate());
    startingDate.setMouseTransparent(true);
    startingDate.setFocusTraversable(false);
    endingDate.setValue(election.getEndingDate());
    endingDate.setMouseTransparent(true);
    endingDate.setFocusTraversable(false);

  }

  @Override public Region getRegion()
  {
    return region;
  }

  @Override public void reset()
  {

  }

  @FXML public void goBack()
  {
    viewHandler.openView(4, -1);
  }

  @FXML public void finishElection()
      throws ParserConfigurationException, TransformerException
  {
    System.out.println("finished");

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("The winner of the election is: " + model.getBoardGamesList().getBoardGameWithMostVotes().getName() );
    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK)
    {
      model.resetVotedOfAllPlayers();
      model.getBoardGamesList().getBoardGameWithMostVotes().setAvailabilityStatus(BoardGame.UNAVAILABLE_STATUS);
      model.setAllVotesTo0();
      model.saveBoardGames();
      model.savePlayers();
      model.setElection(null);
      model.saveElection();
      viewHandler.openView(4, -1);
    }

  }

  @FXML public void deleteElection()
      throws ParserConfigurationException, TransformerException
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Are you sure you want to delete the election");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK)
    {
      model.setElection(null);
      model.saveElection();
      model.setAllVotesTo0();
      model.resetVotedOfAllPlayers();
      model.savePlayers();
      model.saveBoardGames();
      viewHandler.openView(4, -1);
    }
  }
}
