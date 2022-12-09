package Controllers;

import Application.ViewHandler;
import Model.Election;
import Model.ModelManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Region;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.time.LocalDate;
import java.util.Optional;

public class ElectionAddElectionController implements Controller
{
  @FXML public DatePicker startingDate;
  @FXML public DatePicker endingDate;
  private Region region;
  private ModelManager model;
  private ViewHandler viewHandler;
  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID)
  {
    startingDate.setValue(LocalDate.now().plusDays(1));
    endingDate.setValue(LocalDate.now().plusDays(2));

    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;

  }

  @Override public Region getRegion()
  {
    return region;
  }

  @Override public void reset()
  {

  }

  public void createElection()
      throws ParserConfigurationException, TransformerException
  {
    try
    {
      if (Election.VALIDATE_DATA(startingDate.getValue(), endingDate.getValue()))
      {
        model.setElection(new Election(startingDate.getValue(), endingDate.getValue()));
        model.saveElection();
        viewHandler.openView(4, -1);
      }
    }
    catch (Exception e){
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Invalid data");
      alert.setHeaderText(e.getMessage());
      Optional<ButtonType> result = alert.showAndWait();
    }
  }

  @FXML public void goBack(){
    viewHandler.openView(4,-1);
  }
}
