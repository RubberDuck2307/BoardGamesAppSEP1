package Controllers;

import Application.ViewHandler;
import Model.ModelManager;
import Model.Reservation;
import Model.ReservationTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

public class ReservationController {

    public class PlayersController implements Controller {
        Region region;
        ModelManager model;
        ViewHandler viewHandler;

        @FXML
        private TableView<ReservationTable> reservationTable;
        @FXML
        public TableColumn<ReservationTable, String> boardGameName;
        @FXML
        public TableColumn<ReservationTable, String> memberName;
        @FXML
        public TableColumn<ReservationTable, String> from;
        @FXML
        public TableColumn<ReservationTable, String> to;

        public PlayersController() {
        }


        @Override
        public void init(Region region, ModelManager model, ViewHandler viewHandler, int ID) {
            this.region = region;
            this.model = model;
            this.viewHandler = viewHandler;

            ObservableList<ReservationTable> reservationTables = FXCollections.observableArrayList();

            for (int i = 0; i < model.getPlayersList().size(); i++) {
                Reservation reservation = model.getReservationsList().getReservation(i);

                reservationTables.add(new ReservationTable(String.valueOf(reservation.getGameID()), String.valueOf(reservation.getPlayerID()), String.valueOf(reservation.getFrom()), String.valueOf(reservation.getTo()), reservation.getID()));
            }


            boardGameName.setCellValueFactory(new PropertyValueFactory<>("boardGameName"));
            memberName.setCellValueFactory(new PropertyValueFactory<>("memberName"));
            from.setCellValueFactory(new PropertyValueFactory<>("from"));
            to.setCellValueFactory(new PropertyValueFactory<>("to"));

            reservationTable.setItems(reservationTables);
        }


        @Override
        public Region getRegion() {
            return region;
        }

        @Override
        public void reset() {

        }
    }
}
