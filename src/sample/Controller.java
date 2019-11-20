package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private Label resultLabel;

    @FXML
    private Button myButton;

    @FXML
    private TextField usernameTxt;

    @FXML
    private PasswordField passwordTxt;



    public Controller() {
    }

    @FXML
    private void initialize() {
        resultLabel.setText("");
        // Handle Button event.
        myButton.setOnAction((event) -> {
            System.out.println("Button pressed");
/*            if (! (usernameTxt.getText().equals("") && (passwordTxt.getText().equals("")))) {
                resultLabel.setText("Invalid password \n");
            } else {
                loginAction();
            }*/
            if (usernameTxt.getText().equals("NORTHVIEW") && (passwordTxt.getText().equals("PASSWORD"))) {
                loginAction();
            }

        });
    }
    private void loginAction() {
        resultLabel.setText("");

        try{
            Stage stageP = (Stage) myButton.getScene().getWindow();
            // do what you have to do
            stageP.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/secondWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

}
