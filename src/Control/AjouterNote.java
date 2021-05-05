package Control;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import Model.PlanteDetail;
import application.CalendarApp;
import dao.Dao;
import entity.Plant;
import entity.Plantlog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 * Ajouter une note.
 * */
public class AjouterNote {

    @FXML
    private Button retourBut;

    @FXML
    private Button ajouterBut;

    @FXML
    private TextField PHSuivi;

    @FXML
    private TextArea noteSuivi;

    @FXML
    private TextField hauteurSuivi;

    @FXML
    private DatePicker dateSuivi;

    @FXML
    private Hyperlink choisirPhoto;


    private File file;
    Dao<Plantlog> plantlogdao = new Dao(new Plantlog());
    @FXML
    void choisirPhoto(ActionEvent event) {
        FileChooser fileChooser1 = new FileChooser();
        Stage cPhoto = new Stage();

        fileChooser1.setTitle("Open Resource File");
        File file = fileChooser1.showOpenDialog(cPhoto);

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG","*.jpg"),
//                new FileChooser.ExtensionFilter("JPEG图片","*.jpeg"),
//                new FileChooser.ExtensionFilter("PNG图片","*.png"),
                new FileChooser.ExtensionFilter("Tous les fichiers","*.*")
        );
        this.file=file;
    }

    /**Revenir à l'interface précédente*/
    @FXML
    void retour(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/Details.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = CalendarApp.primary;
//        Stage stage =new Stage();
//        //set what you want on your stage
//        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Agenda Etudiant Jardinage - liste de plante");
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.show();
    }

    /**Ajouter une note.*/
    @FXML
    void ajouterNote(ActionEvent event) throws IOException {
        Plant planteDetail = CalendarApp.planteDetail;
        dateSuivi.setValue(dateSuivi.getConverter().fromString(dateSuivi.getEditor().getText()));
        String date =dateSuivi.getValue().toString();
       // planteDetail.addDateSuivi(date);

        double hauteur =Double.valueOf(hauteurSuivi.getText());
     //   planteDetail.addHauteur(hauteur);

        double ph=Double.valueOf(PHSuivi.getText());
      //  planteDetail.addPh(ph);

        String note=noteSuivi.getText();
      //  planteDetail.addNotes(note);

     
        String img="";
        if(this.file!=null){
            try {
            	img="ImagePlante/"+planteDetail.getNom()+UUID.randomUUID().toString().replace("-", "")+".jpg";
                FileInputStream fis = new FileInputStream(this.file.getPath());
                DataInputStream dis = new DataInputStream(fis);
                FileOutputStream fos = new FileOutputStream(img);
                DataOutputStream dos = new DataOutputStream(fos);
                byte[] bs = new byte[2048];
                int length=-1;
                while((length = dis.read(bs))!=-1){
                    dos.write(bs,0,length);
                }
                dis.close();
                dos.flush();
                fis.close();
                fos.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (this.file != null) {
            try {
                Image image = new Image("file:"+this.file.getPath());
               // planteDetail.addImagesList(image);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            File file = new File("ImagePlante/noPhoto.jpg");
            String string = file.toURI().toString();
            Image image = new Image(string);
           // planteDetail.addImagesList(image);
        }
        plantlogdao.executeSql("INSERT INTO plantlog(id,nom,suividate,hauteur,ph,note,photo) values ("
				+"'"+UUID.randomUUID().toString().replace("-", "")+"',"+"'"+planteDetail.getNom()+"',"+
				"'"+date+"',"+
				"'"+hauteur+"',"+"'"+ph+"',"+"'"+note+"'"+",'"+img+"')");

        /**
         * Revenir à l'interface précédente
         * */
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/Details.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = CalendarApp.primary;
//        Stage stage =new Stage();
        //set what you want on your stage
//        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Agenda Etudiant Jardinage - detail de plante");
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.show();
    }

}

