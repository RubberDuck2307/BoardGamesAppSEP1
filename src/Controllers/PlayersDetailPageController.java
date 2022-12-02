package Controllers;

import Application.FileReader;
import Application.ViewHandler;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class PlayersDetailPageController implements Controller
{
  public TextArea commentField;
  public TextField nameField;
  public TextField phoneNumberField;
  public TextField emailField;
  public TextField addressField;
  public DatePicker paymentField;
  public CheckBox membershipBox;
  public CheckBox votedBox;
  public Button editButton;
  private Region region;
  private ModelManager model;
  private ViewHandler viewHandler;
  private int ID;

  private EventHandler save;

  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;
    this.ID = ID;
    setData();
  }

  public void setData()
  {
    Player player = model.getPlayersList().getPlayerByID(ID);
    votedBox.setSelected(player.getVoted());
    nameField.setText(player.getName());
    commentField.setText(player.getComment());
    emailField.setText(player.getEmail());
    phoneNumberField.setText(player.getPhoneNumber());
    addressField.setText(player.getAddress());
    nameField.setDisable(true);
    commentField.setDisable(true);
    emailField.setDisable(true);
    phoneNumberField.setDisable(true);
    addressField.setDisable(true);
    if (player.isMembership())
    {
      membershipBox.setSelected(true);
    }
    membershipBox.setDisable(true);
    votedBox.setDisable(true);
    paymentField.setValue(player.getFeePaymentDate());
    paymentField.setDisable(true);
  }

  @Override public Region getRegion()
  {
    return region;
  }

  @Override public void reset()
  {

  }

  public void edit(ActionEvent actionEvent)
  {
    nameField.setDisable(false);
    commentField.setDisable(false);
    emailField.setDisable(false);
    phoneNumberField.setDisable(false);
    addressField.setDisable(false);
    membershipBox.setDisable(false);
    editButton.setText("Save Changes");
    paymentField.setDisable(false);

    save = event -> {
      System.out.println("hello");
      String name = nameField.getText();
      String phone = phoneNumberField.getText();
      String email = emailField.getText();
      LocalDate feePayment = paymentField.getValue();
      String comment = commentField.getText();
      String address = addressField.getText();
      boolean membership = membershipBox.isSelected();
      try
      {

        if (Player.validateData(name, phone, email))
        {
          Player player = new Player(ID, name, phone, email, membership,
              comment, address, votedBox.isSelected(), feePayment);
          model.setPlayer(player, ID);
          try
          {
            model.savePlayers();
          }
          catch (ParserConfigurationException e)
          {
            throw new RuntimeException(e);
          }
          catch (TransformerException e)
          {
            throw new RuntimeException(e);
          }
          setData();
          editButton.setOnAction(this::edit);
          editButton.setText("Edit");

        }
      }
      catch (Exception e)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid data");
        alert.setHeaderText(e.getMessage());
        Optional<ButtonType> result = alert.showAndWait();
      }

    };

    editButton.setOnAction(save);

  }

  public void delete() throws ParserConfigurationException, TransformerException
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Are you sure you want to delete the player ");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK)
    {
      RatingsList ratingsList = model.getRatingsByPlayer(ID);

      for (int i = 0; i < ratingsList.size(); i++)
      {
        model.deleteRatingByID(ratingsList.getRating(i).getID());
      }

      ReservationsList reservationsList = model.getReservationsByPlayer(ID);
      for (int i = 0; i < reservationsList.size(); i++)
      {
        model.deleteReservationByID(reservationsList.getReservation(i).getID());
      }
      model.saveRatings();
      for (int i = 0; i < reservationsList.size(); i++)
      {
        reservationsList.delete(i);
      }

      BoardGamesList ownedBoardGames = model.getBoardGamesByOwnership(ID);
      if (ownedBoardGames.size() > 0)
      {
        viewHandler.openView(17, ID);
      }
      else
      {
        model.getPlayersList().deleteByID(ID);
        model.savePlayers();
        viewHandler.openView(2, -1);
      }
    }

  }

  @FXML public void showOwnedGames()
  {
    viewHandler.openView(3, ID);
  }

  @FXML public void showReservations()
  {
    viewHandler.openView(5, ID);

  }

  public void goBack()
  {
    viewHandler.openView(2, -1);
  }
}
