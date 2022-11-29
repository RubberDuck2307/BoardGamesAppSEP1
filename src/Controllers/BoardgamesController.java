package Controllers;

import Application.ViewHandler;
import Model.BoardGame;
import Model.BoardgameTable;
import Model.ModelManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

public class BoardgamesController implements Controller{

    Region region;
    ModelManager model;
    ViewHandler viewHandler;

    @FXML
    private TableView<BoardgameTable> playersTable;
    @FXML public TableColumn<BoardgameTable, String> name;
    @FXML public TableColumn<BoardgameTable, String> type;
    @FXML public TableColumn<BoardgameTable, String> availability;
    @FXML public TableColumn<BoardgameTable, String> numberOfPlayers;

    public BoardgamesController() {}


    @Override public void init(Region region, ModelManager model, ViewHandler viewHandler, int ID)
    {
        this.region = region;
        this.model = model;
        this.viewHandler = viewHandler;

        ObservableList<BoardgameTable> boardgameTables = FXCollections.observableArrayList();

        for (int i = 0; i < model.getBoardGamesList().size(); i++) {
            BoardGame boardGame = model.getBoardGamesList().getBoardGame(i);
            String numberOfPlayers = boardGame.getNumberOfPlayersMin() + "-" + boardGame.getNumberOfPlayersMax();
            boardgameTables.add(new BoardgameTable(boardGame.getName(), boardGame.getType(), boardGame.getAvailabilityStatus(),numberOfPlayers, boardGame.getID()));
        }


        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        availability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        numberOfPlayers.setCellValueFactory(new PropertyValueFactory<>("numberOfPlayers"));

        playersTable.setItems(boardgameTables);

    }

    @Override
    public Region getRegion() {
        return null;
    }

    @Override
    public void reset() {

    }
}
