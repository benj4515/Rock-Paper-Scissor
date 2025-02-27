package rps.gui.controller;

// Java imports
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import rps.bll.game.GameManager;
import rps.bll.game.Move;
import rps.bll.game.Result;
import rps.bll.game.ResultType;
import rps.bll.player.IPlayer;
import rps.bll.player.Player;
import rps.bll.player.PlayerType;

import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

/**
 *
 * @author smsj
 */
public class GameViewController3 implements Initializable {

    @FXML
    public ImageView ImgPaper;
    @FXML
    public ImageView ImgScissor;
    @FXML
    public ImageView ImgRock;
    @FXML
    public Label lblResult;
    @FXML
    private Button BtnPaper;
    @FXML
    private Button BtnRock;
    @FXML
    private Button BtnScissor;
    @FXML
    public ImageView ImgGame;
    @FXML
    private Label lblHScore, lblAScore;

    private String input;

    private GameManager gameManager;

    private int humanScore;
    private int aiScore;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        lblResult.setAlignment(Pos.CENTER);

        setButtonImage(BtnRock, "/Ressources/Images/RockPNG.png");
        setButtonImage(BtnPaper, "/Ressources/Images/PaperPNG.png");
        setButtonImage(BtnScissor, "/Ressources/Images/ScissorPNG.png");


        ImgGame.setImage(new Image(getClass().getResourceAsStream("/Ressources/Images/Rock-Scissor.jpg")));
        BtnPaper.setOnAction(this::onBtnPaperClick);
        BtnRock.setOnAction(this::onBtnRockClick);
        BtnScissor.setOnAction(this::onBtnScissorClick);
        startGame();


    }

    private void animateButton(Button button) {
        ScaleTransition st = new ScaleTransition(Duration.millis(200), button);
        st.setFromX(1);
        st.setFromY(1);
        st.setToX(1.2);
        st.setToY(1.2);
        st.setAutoReverse(true);
        st.setCycleCount(2);
        st.play();
    }


    private void setButtonImage(Button button, String imagePath) {
        Image img = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(80); // Adjust size
        imgView.setFitHeight(80);
        button.setGraphic(imgView);
    }

    private String onBtnPaperClick(ActionEvent actionEvent) {
        animateButton(BtnPaper);
        System.out.println("Paper clicked");
        input = "Paper";
        playRound();

        return input;
    }

    private String onBtnRockClick(ActionEvent actionEvent) {
        animateButton(BtnRock);
        System.out.println("Rock clicked");
        input = "Rock";
        playRound();

        return input;
    }

    private String  onBtnScissorClick(ActionEvent actionEvent) {
        animateButton(BtnScissor);
        System.out.println("Scissor clicked");
        input = "Scissor";
        playRound();

        return input;
    }

    private String getPlayerMove(){
        return input;
    }


    public void startGame() {
        System.out.println("Welcome to Rock-Paper-Scissors!");

        IPlayer human = new Player("Player", PlayerType.Human);
        IPlayer bot = new Player(getRandomBotName(), PlayerType.AI);

        gameManager = new GameManager(human, bot);
        System.out.println("Your opponent is " + bot.getPlayerName());
    }

    private void playRound() {
        if (input != null) {
            Move playerMove = Move.valueOf(input);
            gameManager.playRound(playerMove);

            List<Result> results = (List<Result>) gameManager.getGameState().getHistoricResults();
            if (!results.isEmpty()) {
                Result lastResult = results.get(results.size() - 1);
                System.out.println(getResultAsString(lastResult));

                lblResult.setText(getResultAsString(lastResult));

                if (lastResult.getType() != ResultType.Tie) {
                    if(lastResult.getWinnerPlayer().getPlayerType() == PlayerType.Human){
                        humanScore++;
                        lblHScore.setText(humanScore + "");
                    } else if (lastResult.getWinnerPlayer().getPlayerType() == PlayerType.AI){
                        aiScore++;
                        lblAScore.setText(aiScore + "");
                    }
                }
            }
        }
    }

    private String getRandomBotName() {
        String[] botNames = new String[] {
                "R2D2",
                "Mr. Data",
                "3PO",
                "Bender",
                "Marvin the Paranoid Android",
                "Bishop",
                "Robot B-9",
                "HAL"
        };
        int randomNumber = new Random().nextInt(botNames.length - 1);
        return botNames[randomNumber];
    }
    public String getResultAsString(Result result) {
        String statusText = result.getType() == ResultType.Win ? "wins over " : "ties ";

        return "Round #" + result.getRoundNumber() + ":" +
                result.getWinnerPlayer().getPlayerName() +
                " (" + result.getWinnerMove() + ") " +
                statusText + result.getLoserPlayer().getPlayerName() +
                " (" + result.getLoserMove() + ")!";
    }



}
