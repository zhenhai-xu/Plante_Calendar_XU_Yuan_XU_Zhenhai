package sample;


import Model.ListeDetail;
import Model.PlanteDetail;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * Interface testée.
 * */
public class Main extends Application {
    public static Stage primary=new Stage();
    public static ListeDetail listeDetail=new ListeDetail();
    public static PlanteDetail planteDetail;
    public static int index=0;



    @Override
    public void start(Stage primaryStage) throws Exception {
        primary=primaryStage;
        try
        {

            Parent root = FXMLLoader.load(getClass().getResource("../View/main.fxml"));
            primary.setTitle("Agenda Etudiant Jardinage - Liste de plantes");
            primary.setScene(new Scene(root,800,600));
            primary.centerOnScreen();
            primary.show();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}