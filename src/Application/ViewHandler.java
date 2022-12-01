package Application;

import Controllers.*;
import Model.ModelManager;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class ViewHandler
{

  private Scene currentScene;
  private Stage primaryStage;
  private ModelManager model;
  private HomeController homeController;
  private AddPlayerController addPlayerController;
  private PlayersDetailPageController playersDetailPageController;
  private PlayersController playersController;

  private BoardGamesController boardGamesController;
  private BoardGamesDetailPageController boardGamesDetailPageController;
  private ElectionController electionController;
  private ReservationController reservationController;
  private BorrowingsController borrowingsController;
  private EventsController eventsController;
  private PlayersAddPlayerController playersAddPlayerController;
  private EventsDetailPageController eventsDetailPageController;
  private EventsAddEventController eventsAddEventController;
  private BoardGamesAddBoardGameController boardGamesAddBoardGameController;
  private BorrowingsDetailPageController borrowingsDetailPageController;
  private ElectionAddElectionController electionAddElectionController;
  private ElectionAddVoteSelectMemberController electionAddVoteSelectMemberController;
  private ElectionDetailPageController electionDetailPageController;
  private ElectionAddVoteSelectGameController electionAddVoteSelectGameController;
  private BoardGamesAddBoardGameControllerSelectOwner boardGamesAddBoardGameControllerSelectOwner;

  public ViewHandler(ModelManager model)
  {

    this.model = model;
    this.currentScene = new Scene(new Region());
  }

  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    openView(1, -1);
  }

  public void openView(int IDofPage, int IDOfItem)
  {
    Region root = null;
    switch (IDofPage)
    {
      case 1:
        root = loadSimpleGuiView("/FXML/Home.fxml", homeController, -1);
        break;
      case 2:
        root = loadSimpleGuiView("/FXML/Players.fxml", playersController, -1);
        break;
      case 3:
        root = loadSimpleGuiView("/FXML/Boardgames.fxml", boardGamesController,
            -1);
        break;
      case 4:
        root = loadSimpleGuiView("/FXML/Election.fxml", electionController, -1);
        break;
      case 5:
        root = loadSimpleGuiView("/FXML/Reservations.fxml",
            reservationController, -1);
        break;
      case 6:
        root = loadSimpleGuiView("/FXML/Borrowings.fxml", borrowingsController,
            -1);
        break;
      case 7:
        root = loadSimpleGuiView("/FXML/Events.fxml", eventsController, -1);
        break;
      case 8:
        root = loadSimpleGuiView("/FXML/PlayersDetailPage.fxml",
            playersDetailPageController, IDOfItem);
        break;
      case 9:
        root = loadSimpleGuiView("/FXML/BoardgamesDetailPage.fxml",
            boardGamesDetailPageController, IDOfItem);
        break;
      case 10:
        root = loadSimpleGuiView("/FXML/PlayersAddPlayer.fxml",
            playersAddPlayerController, -1);
        break;
      case 31:
        root = loadSimpleGuiView("/FXML/ElectionAddElection.fxml",
            electionAddElectionController, -1);
        break;
      case 32:
        root = loadSimpleGuiView("/FXML/ElectionDetailPage.fxml",
            electionDetailPageController, -1);
        break;
      case 33:
        root = loadSimpleGuiView("/FXML/ElectionAddVoteSelectMember.fxml",
            electionAddVoteSelectMemberController, -1);
        break;
      case 34:
        root = loadSimpleGuiView("/FXML/ElectionAddVoteSelectBoardgame.fxml",
            electionAddVoteSelectGameController, IDOfItem);
        break;
      case 11:
        root = loadSimpleGuiView("/FXML/EventsDetailPage.fxml",
            eventsDetailPageController, IDOfItem);
        break;
      case 12:
        root = loadSimpleGuiView("/FXML/EventsAddEvent.fxml",
            eventsAddEventController, -1);
        break;
      case 130:
        root = loadSimpleGuiView("/FXML/BoardgamesAddBoardgame.fxml",
            boardGamesAddBoardGameController, IDOfItem);
        break;
      case 131:
        root = loadSimpleGuiView("/FXML/BorrowingsDetailPage.fxml",
            borrowingsDetailPageController, IDOfItem);
        break;
      case 132:
        root = loadSimpleGuiView("/FXML/BoardgamesAddBoardgameSelectOwner.fxml",
            boardGamesAddBoardGameControllerSelectOwner, -1);
        break;
    }

    currentScene.setRoot(root);
    primaryStage.setTitle("Board Games App");
    primaryStage.setScene(currentScene);
    primaryStage.setWidth(root.getPrefWidth());
    primaryStage.setHeight(root.getPrefHeight());
    primaryStage.show();
  }
  public void openView(int IDofPage, int IDOfItem, int IDOfItem2)
  {
    Region root = null;
    switch (IDofPage)
    {
    }

    currentScene.setRoot(root);
    primaryStage.setTitle("Board Games App");
    primaryStage.setScene(currentScene);
    primaryStage.setWidth(root.getPrefWidth());
    primaryStage.setHeight(root.getPrefHeight());
    primaryStage.show();
  }

  public void CloseView()
  {
    primaryStage.close();
  }

  private Region loadSimpleGuiView(String fxmlFile, Controller controller,
      int ID)
  {
    Region root = null;
    if (controller == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        root = loader.load();
        controller = loader.getController();
        controller.init(root, model, this, ID);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      controller.reset();
    }

    return controller.getRegion();

  }
}