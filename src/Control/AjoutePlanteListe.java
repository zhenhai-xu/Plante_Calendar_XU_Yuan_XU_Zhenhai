package Control;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

import Model.PlanteDetail;
import application.CalendarApp;
import dao.Dao;
import entity.Plant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Main;
/**
 * Interface pour Ajouter une plante.
 * */

public class AjoutePlanteListe implements Initializable {

    @FXML
    private Button modifierBut1;

    @FXML
    private Button suprimerBut1;

    @FXML
    private Button detaileBut1;

    @FXML
    private Pane listePlante;

    @FXML
    private AnchorPane listePane;
    @FXML
    private Button ajouterPlante;
    @FXML
            private Pane panee;
    AjouterUnePlante childcontroller;

    double paneY=0;
    Dao<Plant> plantdao = new Dao(new Plant());

    /**Afficher les plantes à l'interface*/
    public void afficherUnePlante(Plant planteDetail){
        AnchorPane panePlante = new AnchorPane();
        panePlante.setId(planteDetail.getNom());
        panePlante.prefHeight(180.0);
        panePlante.prefWidth(797);
        panePlante.minHeight(0);
        panePlante.minWidth(0);
        panePlante.setStyle("-fx-background-color: #AFEEEE");
        panePlante.setLayoutX(1.0);
        panePlante.setLayoutY(paneY);


        Button button1 = new Button("Detaile");
        Button button2 = new Button("Modifier");
        Button button3 = new Button("Supprimer");

        button1.setId("detaileBut1");
        button2.setId("suprimerBut1");
        button3.setId("modifierBut1");
        button1.setLayoutX(710);
        button1.setLayoutY(50);
        button2.setLayoutX(686);
        button2.setLayoutY(90);
        button3.setLayoutX(676);
        button3.setLayoutY(130);
        button1.setPrefWidth(65.0);
        button2.setPrefWidth(90.0);
        button3.setPrefWidth(100.0);

        /** action de detail button*/
        button1.setOnAction(e -> {
            CalendarApp.planteDetail =planteDetail;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/details.fxml"));
            Parent root1 = null;
            try {
                root1 = (Parent) fxmlLoader.load();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            Stage stage = CalendarApp.primary;
//        Stage stage =new Stage();
            //set what you want on your stage

            stage.setTitle("Agenda Etudiant Jardinage - details de plante");
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
        });

        /** action de modifier button*/
        button2.setOnAction(e -> {
            CalendarApp.planteDetail =planteDetail;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/modifierPlante.fxml"));
            Parent root1 = null;
            try {
                root1 = (Parent) fxmlLoader.load();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            Stage stage = CalendarApp.primary;
//        Stage stage =new Stage();
            //set what you want on your stage

            stage.setTitle("Agenda Etudiant Jardinage - details de plante");
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
        });

        /** action de supprimer button*/
        button3.setOnAction(e -> {
        	CalendarApp.planteDetail =planteDetail;
        	System.out.println("@@@@@@@@");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("warning");
            alert.headerTextProperty().set("Veuillez confirmer votre suppression？");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
            	System.out.println(CalendarApp.planteDetail.getNom());
                plantdao.delBykey("nom",  CalendarApp.planteDetail.getNom());
                List<Plant> plist=plantdao.getAll();
                for (int i = 0; i < plist.size(); i++) {
                	afficherUnePlante(plist.get(i));
				}
                System.out.println("\n----------------------");
            } else {

            }
        });
        //les labels affiche
        Label label1 = new Label("date de plantation : " + planteDetail.getPlantationdate());
        Label label2 = new Label("date de rempotage : " + planteDetail.getRempotagedate());
        Label label3 = new Label("date de arronsage : " + planteDetail.getArronsagedate());
        Label label4 = new Label("date de entretien : " + planteDetail.getEntretiendate());
        Label label5 = new Label("nom de plante : " + planteDetail.getNom());

        label1.setLayoutX(180);
        label1.setLayoutY(40);
        label2.setLayoutX(180);
        label2.setLayoutY(70);
        label3.setLayoutX(180);
        label3.setLayoutY(100);
        label4.setLayoutX(180);
        label4.setLayoutY(130);
        label5.setLayoutX(180);
        label5.setLayoutY(10);

        ImageView imageView = new ImageView();
        imageView.setLayoutX(15);
        imageView.setLayoutY(10);
       /* if(planteDetail.getImage()!=null) {
            imageView.setImage(planteDetail.getImage());
            System.out.println("图片设置成功");
            imageView.setFitWidth(130);
            imageView.setFitHeight(150);
            imageView.setPickOnBounds(true);
            imageView.setPreserveRatio(false);

        }else {
            File file = new File("src\\ImagePlante\\noPhoto.jpg");
            String string = file.toURI().toString();
            Image image = new Image(string);
            imageView.setImage(image);
            System.out.println("图片设置成功");
            imageView.setFitWidth(130);
            imageView.setFitHeight(150);
            imageView.setPickOnBounds(true);
            imageView.setPreserveRatio(false);
            planteDetail.setImage(image);
        }*/


        //Après avoir ajouté des plantes, vous devez augmenter la hauteur de la pane.
        this.paneY+=180;
        listePane.setPrefHeight(listePane.getPrefHeight()+180);
        panePlante.getChildren().addAll(button1,button2,button3,label1,label2,label3,label4,label5,imageView);
        listePane.getChildren().add(panePlante);

    }




    @FXML
    //切换界面到添加植物
    private void ajouterUnePlante(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/ajouterUnePlante.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = CalendarApp.primary;
//        Stage stage =new Stage();
        //set what you want on your stage


        stage.setTitle("Agenda Etudiant Jardinage - ajouter une plante");
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.show();
    }




    @Override

    public void initialize(URL location, ResourceBundle resources) {
        Set keys = Main.listeDetail.keySet();
        for (Object key : keys) {
            afficherUnePlante((Plant) Main.listeDetail.get(key));
        }
        System.out.println("\n----------------------");

    }
}
