package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("FBLA Advisor Administration");
        primaryStage.setScene(new Scene(root, 850, 500));
        primaryStage.show();




    }
    public static void main(String[] args) {
        DBConnection myConnection = new DBConnection();
        ArrayList<Advisor> advArray=  myConnection.readAdvisor(null);
        for (int i=0; i<advArray.size(); i++) {
            Advisor adv = (Advisor)advArray.get(i);
            System.out.println(adv.getName());
        }
        //myConnection.readQuery(student);
        //student.setStudentNumber(null);
        //student.setFirstName("Scott");
        //myConnection.readQuery(student);
        //student.setStudentNumber("1234567");
        //myConnection.readQuery(student);
        launch(args);
    }
}



