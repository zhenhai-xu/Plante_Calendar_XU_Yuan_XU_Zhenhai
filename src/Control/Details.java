package Control;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.CalendarApp;
import dao.Dao;
import entity.Plant;
import entity.Plantlog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Main;

public class Details implements Initializable {

    @FXML
    private Label labelEnt;

    @FXML
    private AnchorPane graphePane;

    @FXML
    private Label labelCou;

    @FXML
    private Button retourBut;

    @FXML
    private Label labelRec;

    @FXML
    private Button ajoutSuiviBut;

    @FXML
    private Label labelNom;

    @FXML
    private Label labelRem;

    @FXML
    private AnchorPane recordPane;

    @FXML
    private Label labelPlan;

    @FXML
    private ImageView imageView;

    @FXML
    private Label labelArr;

    @FXML
    private AnchorPane detailsPane;
    private double paneY;
    Dao<Plantlog> plantlogdao = new Dao(new Plantlog());
    public void setImageView(Plant p) {
        ImageView imageView = new ImageView();
        imageView.setLayoutX(30);
        imageView.setLayoutY(40);
        imageView.setFitWidth(140);
        imageView.setFitHeight(160);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(false);
        imageView.setImage(new Image("file:ImagePlante\\"+p.getNom()+".jpg"));
        detailsPane.getChildren().add(imageView);
    }

    @FXML
    void retourBut(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/main.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = CalendarApp.primary;
//        Stage stage =new Stage();

        stage.setTitle("Agenda Etudiant Jardinage - ajouter une plante");
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void ajoutSuiviBut(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/ajouterNote.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = CalendarApp.primary;
//        Stage stage =new Stage();
        //set what you want on your stage

        stage.setTitle("Agenda Etudiant Jardinage - ajouter une note");
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.show();
    }
        /**
         * Afficher les notes de suivi de l'usine
         * */
    public void  afficherPlanteSuivi(Plantlog planteDetail,int i){
        AnchorPane panePlante = new AnchorPane();
        panePlante.setId(planteDetail.getNom());
        panePlante.prefHeight(180.0);
        panePlante.prefWidth(799);
        panePlante.minHeight(0);
        panePlante.minWidth(0);
        panePlante.setStyle("-fx-background-color: #E4FFA8");
        panePlante.setLayoutX(1.0);
        panePlante.setLayoutY(paneY);

        Button button2 = new Button("Modifier");
        Button button3 = new Button("Supprimer");


        button2.setId("modifierBut1");
        button3.setId("suprimerBut1");

        button2.setLayoutX(410);
        button2.setLayoutY(80);
        button3.setLayoutX(400);
        button3.setLayoutY(120);
        button2.setPrefWidth(90.0);
        button3.setPrefWidth(100.0);



        Label label1 = new Label("date : " + planteDetail.getSuividate());
        Label label2 = new Label("hauteur : " + planteDetail.getHauteur());
        Label label3 = new Label("PH : " + planteDetail.getPh());
        Label label5 = new Label("note : " + planteDetail.getNote());

        label1.setLayoutX(180);
        label1.setLayoutY(40);
        label2.setLayoutX(180);
        label2.setLayoutY(70);
        label3.setLayoutX(180);
        label3.setLayoutY(100);
        label5.setLayoutX(180);
        label5.setLayoutY(130);

        ImageView imageView = new ImageView();
        imageView.setLayoutX(15);
        imageView.setLayoutY(15);
        button2.setOnAction(e -> {
            Main.index=i;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/modifierNote.fxml"));
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

        /**
         * Action pour Supprimer Button*/
        button3.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("warning");
            alert.headerTextProperty().set("confirmer la suppression");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
//                CalendarApp.planteDetail.getPh().remove(i);
//                CalendarApp.planteDetail.getDateSuivi().remove(i);
//                CalendarApp.planteDetail.getHauteur().remove(i);
//                CalendarApp.planteDetail.getNotes().remove(i);
//                CalendarApp.planteDetail.getImagesList().remove(i);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/Details.fxml"));
                Parent root1 = null;
                try {
                    root1 = (Parent) fxmlLoader.load();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                Stage stage = CalendarApp.primary;
//        Stage stage =new Stage();
                //set what you want on your stage
                stage.setTitle("Agenda Etudiant Jardinage - ajouter une plante");
                stage.setScene(new Scene(root1));
                stage.setResizable(false);
                stage.show();
            }
        });
//
//        if(planteDetail.getImagesList().get(i)!=null){
//            imageView.setImage(planteDetail.getImagesList().get(i));
//            imageView.setFitWidth(130);
//            imageView.setFitHeight(140);
//            imageView.setPickOnBounds(true);
//            imageView.setPreserveRatio(false);
//
//        }else {
//            File file = new File("src\\ImagePlante\\noPhoto.jpg");
//            String string = file.toURI().toString();
//            Image image = new Image(string);
//            imageView.setImage(image);
//            System.out.println("图片设置成功");
//            imageView.setFitWidth(130);
//            imageView.setFitHeight(140);
//            imageView.setPickOnBounds(true);
//            imageView.setPreserveRatio(false);
//
//        }
        this.paneY+=150;
        recordPane.setPrefHeight(recordPane.getPrefHeight()+150);

        panePlante.getChildren().addAll(button2,button3,label1,label2,label3,label5,imageView);

        recordPane.getChildren().add(panePlante);

    }

    /**
     * Affichage du graphie de hauteur.
     */
    public void afficherGrapheHauteur(List<Plantlog> plogs){

        NumberAxis xAxis = new NumberAxis(1, 15, 3);
        xAxis.setLabel("Nombre de mesures");
        NumberAxis yAxis = new NumberAxis(0, 200, 40);
        yAxis.setLabel("Hauteur");
        LineChart linechart = new LineChart(xAxis, yAxis);
        XYChart.Series series = new XYChart.Series();
        series.setName("Hauteur de la plante");

        for(int i = 0 ; i <plogs.size();i++ ) {
            series.getData().add(new XYChart.Data(i+1,Double.valueOf(plogs.get(i).getHauteur())));
        }

        linechart.getData().add(series);
        linechart.setPrefWidth(500);
        linechart.setPrefHeight(250);
        linechart.setLayoutX(0);
        linechart.setLayoutY(0);
        graphePane.getChildren().add(linechart);

    }

    /**
     * Affichage du graphie de ph.
     */
    public void afficherGraphePH(List<Plantlog> plogs){

        NumberAxis xAxis = new NumberAxis(1, 15, 3);
        xAxis.setLabel("Nombre de mesures");
        NumberAxis yAxis = new NumberAxis(-2, 15, 2);
        yAxis.setLabel("PH ");
        LineChart linechart = new LineChart(xAxis, yAxis);
        XYChart.Series series = new XYChart.Series();
        series.setName("changement de PH");

        for(int i = 0 ; i <plogs.size();i++ ) {
            series.getData().add(new XYChart.Data(i+1,Double.valueOf(plogs.get(i).getPh())));
        }

        linechart.getData().add(series);
        linechart.setPrefWidth(500);
        linechart.setPrefHeight(250);
        linechart.setLayoutX(0);
        linechart.setLayoutY(270);
        graphePane.getChildren().add(linechart);

    }


    /**
     * Initialiser l'interface, afficher le contenu de notes
     * */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Plant planteDetail = CalendarApp.planteDetail;
        labelNom.setText(planteDetail.getNom());
        labelPlan.setText("date de plantation : "+planteDetail.getPlantationdate());
        labelRem.setText("date de rempotage : "+planteDetail.getRempotagedate());
        labelArr.setText("date de arronsage : "+planteDetail.getArronsagedate());
        labelEnt.setText("date de entretien : "+planteDetail.getEntretiendate());
        labelCou.setText("date de coupe : "+planteDetail.getCoupedate());
        labelRec.setText("date de recolte : "+planteDetail.getRecottedate());
        setImageView(planteDetail);

        List<Plantlog> plogs=plantlogdao.queryByKey("nom", planteDetail.getNom());
        for (int i = 0; i < plogs.size(); i++) {
        	afficherPlanteSuivi(plogs.get(i),i);
		}
       /* for(int i = 0; i<planteDetail.getHauteur().size();i++){
            
        }*/
        afficherGrapheHauteur(plogs);
        afficherGraphePH(plogs);
    }
}
