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
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

import static rps.bll.game.Move.Rock;
import static rps.bll.game.Move.Scissor;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {

    @FXML
    private Button BtnPaper;
    @FXML
    private Button BtnRock;
    @FXML
    private Button BtnScissor;
    @FXML
    private ImageView ImgRock;

    private String input;




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
        return input;

    }

    private String onBtnRockClick(ActionEvent actionEvent) {
        System.out.println("Rock clicked");
        input = "Rock";
        return input;

    }

    private String  onBtnScissorClick(ActionEvent actionEvent) {
        System.out.println("Scissor clicked");
        input = "Scissor";
        return input;

    }

    private String getPlayerMove(){

        String Click = "";

        if (BtnPaper.isPressed() || BtnRock.isPressed() || BtnScissor.isPressed()) {
            input = Click;
        }
        return Click;
    }

    public void startGame() {
        System.out.println("Welcome to the classic Rock, Paper and Scissor game!");
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Please type in your player name: ");
        String playerName = keyboard.next();

        IPlayer human = new Player(playerName, PlayerType.Human);
        IPlayer bot = new Player(getRandomBotName(), PlayerType.AI);

        System.out.println("Your opponent is " + bot.getPlayerName());
        System.out.println("Starting game.... good luck both!");

        GameManager ge = new GameManager(human, bot);

        while (true) {
            String playerMove = getPlayerMove();

            if (playerMove.equalsIgnoreCase("exit"))
                break;

            ge.playRound(Move.valueOf(playerMove));

            ge.getGameState().getHistoricResults().forEach((result) -> {
                System.out.println(getResultAsString(result));
            });
        }

        if (ge.getGameState().getHistoricResults().size() > 0)
            System.out.println("Game stats: ....ehmmmm..not implemented yet...please FIXME");
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
