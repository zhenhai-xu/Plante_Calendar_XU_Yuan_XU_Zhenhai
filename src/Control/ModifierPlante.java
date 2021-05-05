package Control;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ModifierPlante implements Initializable {

    @FXML
    private Button modifierBut;

    @FXML
    private DatePicker dateDePla;

    @FXML
    private TextArea noteText;

    @FXML
    private AnchorPane ajoutePLPane;

    @FXML
    private Button retourBut;

    @FXML
    private DatePicker dateDeArr;

    @FXML
    private Hyperlink choirsirPhoto;

    @FXML
    private DatePicker dateDeRec;

    @FXML
    private DatePicker dateDeEnt;

    @FXML
    private DatePicker dateDeRem;

    @FXML
    private DatePicker dateDeCou;

    @FXML
    private TextField nomDePlante;
    @FXML
    private File file;

    
    Dao<Plant> plantdao = new Dao(new Plant());
    
    /**
     * Action pour retour button.
     */
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


    /**
     * Action pour modifier button.
     */
    @FXML
    void modifierBut(ActionEvent event) throws IOException {
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
                System.out.println("文件保存完成!");
                String s ="file:src/ImagePlante/"+nom+".jpg";
//                Image image = new Image("file:"+this.file.getPath());
                Image image = new Image(s);
                plante.setImage(image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
           // plante.setImage(CalendarApp.planteDetail.getImage());
        }

        /**Exporter les données vers la base de données*/
		plantdao.executeSql("update plant set "+"nom='"+nom+"',"+"photo='"+nom+"',"+"plantationdate='"+
				dateDePla.getValue().toString()+"',"+"rempotagedate='"+dateDeRem.getValue().toString()
				+"',"+"arronsagedate='"+dateDeArr.getValue().toString()+"',"+
				"entretiendate='"+dateDeEnt.getValue().toString()+"',"+"coupedate='"+
				dateDeCou.getValue().toString()+"',"+"recottedate='"+dateDeRec.getValue().toString()
				+"',"+"notes='"+note+"'" +" where nom='"+nom+"'");		

        System.out.println("添加成功");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/main.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = CalendarApp.primary;
//        Stage stage =new Stage();
        //set what you want on your stage


        stage.setTitle("Agenda Etudiant Jardinage - ajouter une plante");
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
        //Ajouter la photo.
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

    /**
     * Initialiser l'interface.
     * */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(CalendarApp.planteDetail.getPlantationdate()!=null) {
            String datetest = "" + CalendarApp.planteDetail.getPlantationdate();
            dateDePla.setValue(LocalDate.parse(datetest));
        }
        if(CalendarApp.planteDetail.getRempotagedate()!=null) {
            String datetest = "" + CalendarApp.planteDetail.getRempotagedate();
            dateDeRem.setValue(LocalDate.parse(datetest));
        }
        if(CalendarApp.planteDetail.getArronsagedate()!=null) {
            String datetest = "" + CalendarApp.planteDetail.getArronsagedate();
            dateDeArr.setValue(LocalDate.parse(datetest));
        }
        if(CalendarApp.planteDetail.getEntretiendate()!=null) {
            String datetest = "" + CalendarApp.planteDetail.getEntretiendate();
            dateDeEnt.setValue(LocalDate.parse(datetest));
        }
        if(CalendarApp.planteDetail.getCoupedate()!=null) {
            String datetest = "" + CalendarApp.planteDetail.getCoupedate();
            dateDeCou.setValue(LocalDate.parse(datetest));
        }
        if(CalendarApp.planteDetail.getRecottedate()!=null) {
            String datetest = "" + CalendarApp.planteDetail.getRecottedate();
            dateDeRec.setValue(LocalDate.parse(datetest));
        }
        if(CalendarApp.planteDetail.getNom()!=null) {
        String nom = CalendarApp.planteDetail.getNom();
        nomDePlante.setText(nom);
        }
    }
}

