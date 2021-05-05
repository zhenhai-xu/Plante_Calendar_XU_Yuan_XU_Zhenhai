package Control;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import Model.PlanteDetail;
import application.CalendarApp;
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
import sample.Main;

public class Controller implements Initializable {

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



    private File file;


    @FXML
    //在添加植物中添加照片
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
                new FileChooser.ExtensionFilter("toutes les fichiers","*.*")
        );
        this.file=file;


    }

    @FXML
    //添加植物的窗口里的返回键的事件
    void retourBut(ActionEvent event) throws IOException {
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
    /**Ajouter des plantes à la fenêtre. Action deajouter buttom.*/
    void AjouterBut(ActionEvent event) throws IOException {
        String nom = nomDePlante.getText();

        String note = noteText.getText();

        HashMap date = new HashMap();

        String dateRem = dateDeRem.getValue().toString();
        date.put("dateRem", dateRem);
        String datePla = dateDePla.getValue().toString();
        date.put("datePla", datePla);
        String dateArr = dateDeArr.getValue().toString();
        date.put("dateArr", dateArr);
        String dateEnt = dateDeEnt.getValue().toString();
        date.put("dateEnt", dateEnt);
        String dateCou = dateDeCou.getValue().toString();
        date.put("dateCou", dateCou);
        String dateRec = dateDeRec.getValue().toString();
        date.put("dateRec", dateRec);
        System.out.println(nom + "\n" + note + "\n" + dateArr);

        PlanteDetail plante = new PlanteDetail(nom, date, note);

        if(this.file!=null){
            try {
                FileInputStream fis = new FileInputStream(this.file.getPath());
                DataInputStream dis = new DataInputStream(fis);
                FileOutputStream fos = new FileOutputStream("src/ImagePlante/"+nom+".jpg");
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

        /**
         * Revenir à l'interface précédente
         * */
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
    @Override

    public void initialize(URL location, ResourceBundle resources) {
    }
}