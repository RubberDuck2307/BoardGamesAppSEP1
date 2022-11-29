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

public class BoardGamesController implements Controller{

    Region region;
    ModelManager model;
    ViewHandler viewHandler;

    @FXML
    private TableView<BoardgameTable> boardGameTable;
    @FXML public TableColumn<BoardgameTable, String> name;
    @FXML public TableColumn<BoardgameTable, String> type;
    @FXML public TableColumn<BoardgameTable, String> availability;
    @FXML public TableColumn<BoardgameTable, String> numberOfPlayers;

    public BoardGamesController() {}


    @Override public void init(Region region, ModelManager model, ViewHandler viewHandler, int ID)
    {
        this.region = region;
        this.model = model;
        this.viewHandler = viewHandler;

        ObservableList<BoardgameTable> boardGameTables = FXCollections.observableArrayList();

        for (int i = 0; i < model.getBoardGamesList().size(); i++) {
            BoardGame boardGame = model.getBoardGamesList().getBoardGame(i);
            String numberOfPlayer = boardGame.getNumberOfPlayersMin() + "" + boardGame.getNumberOfPlayersMax();
            System.out.println(numberOfPlayer);
            boardGameTables.add(new BoardgameTable(boardGame.getName(), boardGame.getType(), boardGame.getAvailabilityStatus(),numberOfPlayer, boardGame.getID()));
        }


        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        availability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        numberOfPlayers.setCellValueFactory(new PropertyValueFactory<>("numberOfPlayers"));

        boardGameTable.setItems(boardGameTables);

    }

    @Override
    public Region getRegion() {
        return region;
    }

    @FXML public void backToHomePage(){

    }

    @Override
    public void reset() {

    }
}
