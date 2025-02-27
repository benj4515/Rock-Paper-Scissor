package rps.gui.controller;

// Java imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import java.util.Scanner;

/**
 *
 * @author smsj
 */
public class GameViewController2 implements Initializable {

    @FXML
    private Button BtnPaper;
    @FXML
    private Button BtnRock;
    @FXML
    private Button BtnScissor;
    @FXML
    private ImageView ImgRock;

    private String input;

    private GameManager gameManager;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ImgRock.setImage(new Image(getClass().getResourceAsStream("/Ressources/Images/Rock-Scissor.jpg")));
        BtnPaper.setOnAction(this::onBtnPaperClick);
        BtnRock.setOnAction(this::onBtnRockClick);
        BtnScissor.setOnAction(this::onBtnScissorClick);
        startGame();


    }

    private String onBtnPaperClick(ActionEvent actionEvent) {
        System.out.println("Paper clicked");
        input = "Paper";
        playRound();

        return input;
    }

    private String onBtnRockClick(ActionEvent actionEvent) {
        System.out.println("Rock clicked");
        input = "Rock";
        playRound();

        return input;
    }

    private String  onBtnScissorClick(ActionEvent actionEvent) {
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
