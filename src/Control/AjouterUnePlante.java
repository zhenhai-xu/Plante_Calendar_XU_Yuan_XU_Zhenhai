package Control;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

import Model.PlanteDetail;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Main;

/**Interface d'ajouter une plante*/
public class AjouterUnePlante implements Initializable {

    @FXML
    private Hyperlink choirsirPhoto;

    @FXML
    private TextField nomDePlante;

    @FXML
    private AnchorPane ajoutePLPane;
    @FXML
    private TextArea noteText;
    @FXML
    private Button modifierBut;
    @FXML
    private Button retourBut;
    @FXML
    private DatePicker dateDeRem;
    @FXML
    private DatePicker dateDePla;
    @FXML
    private DatePicker dateDeArr;
    @FXML
    private DatePicker dateDeEnt;
    @FXML
    private DatePicker dateDeCou;
    @FXML
    private DatePicker dateDeRec;
    @FXML
    private DatePicker dateDeMes;
    @FXML
    private TextField PH;
    @FXML
    private TextField hauteur;
    @FXML
    private ComboBox<String> interval1;
    @FXML
    private ComboBox<String> interval2;
    @FXML
    private ComboBox<String> interval3;
    @FXML
    private ComboBox<String> interval4;
    
    private File file;
    Dao<Plant> plantdao = new Dao(new Plant());
	Dao<Plantlog> plantlogdao = new Dao(new Plantlog());

    @FXML
    void choisirPhoto(ActionEvent event) {
        FileChooser fileChooser1 = new FileChooser();
        Stage cPhoto = new Stage();

        fileChooser1.setTitle("Open Resource File");
        File file = fileChooser1.showOpenDialog(cPhoto);

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG图片","*.jpg"),
//                new FileChooser.ExtensionFilter("JPEG图片","*.jpeg"),
//                new FileChooser.ExtensionFilter("PNG图片","*.png"),
                new FileChooser.ExtensionFilter("所有文件","*.*")
        );
        this.file=file;


    }

    @FXML
    void retourBut(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/main.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = CalendarApp.primary;

        stage.setTitle("Agenda Etudiant Jardinage - ajouter une plante");
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void AjouterBut(ActionEvent event) throws IOException {
        String nom = nomDePlante.getText();

        String note = noteText.getText();

        Map date = new HashMap<String ,String>();


       
        dateDePla.setValue(dateDePla.getConverter()
                .fromString(dateDePla.getEditor().getText()));
        dateDeRem.setValue(dateDeRem.getConverter()
                .fromString(dateDeRem.getEditor().getText()));
        dateDeArr.setValue(dateDeArr.getConverter()
                .fromString(dateDeArr.getEditor().getText()));
        dateDeEnt.setValue(dateDeEnt.getConverter()
                .fromString(dateDeEnt.getEditor().getText()));
        dateDeCou.setValue(dateDeCou.getConverter()
                .fromString(dateDeCou.getEditor().getText()));
        dateDeRec.setValue(dateDeRec.getConverter()
                .fromString(dateDeRec.getEditor().getText()));

        if(dateDePla.getValue()!=null) {
            String datePla = dateDePla.getValue().toString();
            date.put("datePla", datePla);
        }else {
            date.put("datePla", null);
        }

        if(dateDeRem.getValue()!=null) {
            String dateRem = dateDeRem.getValue().toString();
            date.put("dateRem", dateRem);
        }else {
            date.put("dateRem", null);
        }

        if(dateDeArr.getValue()!=null) {
            String dateArr = dateDeArr.getValue().toString();
            date.put("dateArr", dateArr);
        }else {
            date.put("dateArr", null);
        }

        if(dateDeEnt.getValue()!=null) {
            String dateEnt = dateDeEnt.getValue().toString();
            date.put("dateEnt", dateEnt);
        }else {
            date.put("dateEnt", null);
        }

        if(dateDeCou.getValue()!=null) {
            String dateCou = dateDeCou.getValue().toString();
            date.put("dateCou", dateCou);
        }else {
            date.put("dateCou", null);
        }

        if(dateDeRec.getValue()!=null) {
            String dateRec = dateDeRec.getValue().toString();
            date.put("dateRec", dateRec);}
        else {
            date.put("dateRec", null);
        }


        PlanteDetail plante = new PlanteDetail(nom, date, note);

        if(this.file!=null){
            try {
                FileInputStream fis = new FileInputStream(this.file.getPath());
                DataInputStream dis = new DataInputStream(fis);
                FileOutputStream fos = new FileOutputStream("ImagePlante/"+nom+".jpg");
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
                plante.setImage(image);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        Main.listeDetail.put(plante.getNom(),plante);

        plante.addHauteur(Double.valueOf(hauteur.getText()));
        plante.addPh(Double.valueOf(PH.getText()));
        plante.addImagesList(plante.getImage());
        dateDeMes.setValue(dateDeMes.getConverter()
                .fromString(dateDeMes.getEditor().getText()));
        plante.addDateSuivi(dateDeMes.getValue().toString());
        plante.addNotes(plante.getNote());
        String inv1="";String inv2="";String inv3="";String inv4="";
        if(interval1.getSelectionModel().getSelectedItem()!=null){
        	inv1=interval1.getSelectionModel().getSelectedItem().toString();
        }
        if(interval2.getSelectionModel().getSelectedItem()!=null){
        	inv2=interval2.getSelectionModel().getSelectedItem().toString();
        }
        if(interval3.getSelectionModel().getSelectedItem()!=null){
        	inv3=interval3.getSelectionModel().getSelectedItem().toString();
        }
        if(interval4.getSelectionModel().getSelectedItem()!=null){
        	inv4=interval4.getSelectionModel().getSelectedItem().toString();
        }
		plantdao.executeSql("INSERT INTO plant(nom,photo,plantationdate,rempotagedate,arronsagedate,entretiendate,coupedate,recottedate,hauteur,ph,mesuresdate,"
				+ "notes,interval1,interval2,interval3,interval4) values "
				+ "("+"'"+nom+"',"+"'"+nom+"',"+"'"+dateDePla.getValue().toString()+"',"+"'"+dateDeRem.getValue().toString()
				+"',"+"'"+dateDeArr.getValue().toString()+"',"+"'"+dateDeEnt.getValue().toString()
				+"',"+"'"+dateDeCou.getValue().toString()+"',"+"'"+dateDeRec.getValue().toString()+"',"
				+"'"+hauteur.getText()+"',"+"'"+PH.getText()+"',"+"'"+dateDeMes.getValue().toString()+
				"',"+"'"+note+"',"+"'"+inv1+"',"+"'"+inv2+"',"+"'"+inv3+"',"+"'"+inv4+"'"+")");

		plantlogdao.executeSql("INSERT INTO plantlog(id,nom,suividate,hauteur,ph,note,photo) values ("
				+"'"+UUID.randomUUID().toString().replace("-", "")+"',"+"'"+nom+"',"+
				"'"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"',"+
				"'"+hauteur.getText()+"',"+"'"+PH.getText()+"',"+"'"+note+"'"+",'"+nom+"')");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/main.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = CalendarApp.primary;
//        Stage stage =new Stage();
        //set what you want on your stage


        stage.setTitle("Agenda Etudiant Jardinage - Liste des plantes");
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.show();
    }
    @Override
    //初始化窗口，如果加载界面之后 还要做点什么的话
    public void initialize(URL location, ResourceBundle resources) {


    }
}