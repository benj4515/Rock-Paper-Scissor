package rps.gui.controller;

// Java imports
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ImgRock.setImage(new Image(getClass().getResourceAsStream("/Ressources/Images/Rock-Scissor.jpg")));

    }

    public void onBtnRockClick() {
        System.out.println("Rock clicked");


    }


    public void onBtnPaperClick() {
        System.out.println("Paper clicked");
    }

    public void onBtnScissorClick() {
        System.out.println("Scissor clicked");
    }

}
