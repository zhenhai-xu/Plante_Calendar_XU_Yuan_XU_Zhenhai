package View;

import java.io.IOException;

import util.CellFactory;
import dao.DBUnitHelper;
import dao.Dao;
import java.util.List;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import entity.Plantlog;
import entity.Plant;
/**
 * 控制层
 */
public class Controller {
    @FXML
    private TableView<Plantlog> plantlogtableView;
	@FXML
    private TableColumn<Plantlog, String> plantlogidcolumn;
    @FXML
    private TextField plantlogid;
	@FXML
    private TableColumn<Plantlog, String> plantlognomcolumn;
    @FXML
    private TextField plantlognom;
	@FXML
    private TableColumn<Plantlog, String> plantlogsuividatecolumn;
    @FXML
    private TextField plantlogsuividate;
	@FXML
    private TableColumn<Plantlog, String> plantloghauteurcolumn;
    @FXML
    private TextField plantloghauteur;
	@FXML
    private TableColumn<Plantlog, String> plantlogphcolumn;
    @FXML
    private TextField plantlogph;
	@FXML
    private TableColumn<Plantlog, String> plantlognotecolumn;
    @FXML
    private TextField plantlognote;
	@FXML
    private TableColumn<Plantlog, String> plantlogphotocolumn;
    @FXML
    private TextField plantlogphoto;
	private final ObservableList<Plantlog> plantlogdata = FXCollections.observableArrayList();
	Dao<Plantlog> plantlogdao = new Dao(new Plantlog());
	@FXML
    private TableColumn<Plantlog, Boolean> plantlogcolumn;
    @FXML
    private TableView<Plant> planttableView;
	@FXML
    private TableColumn<Plant, String> plantnomcolumn;
    @FXML
    private TextField plantnom;
	@FXML
    private TableColumn<Plant, String> plantphotocolumn;
    @FXML
    private TextField plantphoto;
	@FXML
    private TableColumn<Plant, String> plantplantationdatecolumn;
    @FXML
    private TextField plantplantationdate;
	@FXML
    private TableColumn<Plant, String> plantrempotagedatecolumn;
    @FXML
    private TextField plantrempotagedate;
	@FXML
    private TableColumn<Plant, String> plantarronsagedatecolumn;
    @FXML
    private TextField plantarronsagedate;
	@FXML
    private TableColumn<Plant, String> plantentretiendatecolumn;
    @FXML
    private TextField plantentretiendate;
	@FXML
    private TableColumn<Plant, String> plantcoupedatecolumn;
    @FXML
    private TextField plantcoupedate;
	@FXML
    private TableColumn<Plant, String> plantrecottedatecolumn;
    @FXML
    private TextField plantrecottedate;
	@FXML
    private TableColumn<Plant, String> planthauteurcolumn;
    @FXML
    private TextField planthauteur;
	@FXML
    private TableColumn<Plant, String> plantphcolumn;
    @FXML
    private TextField plantph;
	@FXML
    private TableColumn<Plant, String> plantmesuresdatecolumn;
    @FXML
    private TextField plantmesuresdate;
	@FXML
    private TableColumn<Plant, String> plantnotescolumn;
    @FXML
    private TextField plantnotes;
	@FXML
    private TableColumn<Plant, String> plantinterval1column;
    @FXML
    private TextField plantinterval1;
	@FXML
    private TableColumn<Plant, String> plantinterval2column;
    @FXML
    private TextField plantinterval2;
	@FXML
    private TableColumn<Plant, String> plantinterval3column;
    @FXML
    private TextField plantinterval3;
	@FXML
    private TableColumn<Plant, String> plantinterval4column;
    @FXML
    private TextField plantinterval4;
	private final ObservableList<Plant> plantdata = FXCollections.observableArrayList();
	Dao<Plant> plantdao = new Dao(new Plant());
	@FXML
    private TableColumn<Plant, Boolean> plantcolumn;

    
    /**
     * plantlog新增
     *
     */
    @FXML
    void plantlogadd(ActionEvent event) throws IOException {
			List<Plantlog> ulist= plantlogdao.queryByKey("id", plantlogid.getText());
			if(ulist.size()>0){
				showMsg("此记录已经存在,无法新增");
			}else{
				plantlogdao.executeSql("INSERT INTO plantlog(id,nom,suividate,hauteur,ph,note,photo) values ("+"'"+plantlogid.getText()+"',"+"'"+plantlognom.getText()+"',"+"'"+plantlogsuividate.getText()+"',"+"'"+plantloghauteur.getText()+"',"+"'"+plantlogph.getText()+"',"+"'"+plantlognote.getText()+"',"+"'"+plantlogphoto.getText()+"'"+")");
			}			
        	showMsg("新增成功");
        	plantlogrefresh();
        	
    }

    /**
     * plantlog删除
     */
    @FXML
    void plantlogdelete(ActionEvent event) throws IOException {
       for (int i = 0; i <plantlogdata.size(); i++) {
    	   if(plantlogdata.get(i).isSelected()){
    		   plantlogdao.delBykey("id", plantlogdata.get(i).getId());
    	   }
       }
       showMsg("删除成功");
       plantlogrefresh();
    }

    /**
     * plantlog修改
     */
    @FXML
    void plantlogmodify(ActionEvent event) throws IOException {
        	Plantlog plantlog=null;
        	for (int i = 0; i <plantlogdata.size(); i++) {
         	   if(plantlogdata.get(i).isSelected()){
         		   if(plantlog!=null){
         			  showMsg("请选择一个进行修改");
         			  return;
         		   }else{
         			   plantlog=plantlogdata.get(i);
         		   }
         	   }
            }
        	if(plantlog!=null){
				plantlogdao.executeSql("update plantlog set "+"id='"+plantlogid.getText()+"',"+"nom='"+plantlognom.getText()+"',"+"suividate='"+plantlogsuividate.getText()+"',"+"hauteur='"+plantloghauteur.getText()+"',"+"ph='"+plantlogph.getText()+"',"+"note='"+plantlognote.getText()+"',"+"photo='"+plantlogphoto.getText()+"'" +" where id='"+plantlogid.getText()+"'");		
				showMsg("修改成功");
				plantlogrefresh();
        	}else{
        		 showMsg("请选择一个进行修改");
        	}
            
    }
	public void plantlogrefresh(){
    	plantlogdata.clear();
    	plantlogdata.addAll(plantlogdao.getAll());
    	plantlogid.clear();
    	plantlognom.clear();
    	plantlogsuividate.clear();
    	plantloghauteur.clear();
    	plantlogph.clear();
    	plantlognote.clear();
    	plantlogphoto.clear();
    }
   
    /**
     * plantlog查找
     */
    @FXML
    void plantlogsearch(ActionEvent event) throws CloneNotSupportedException {
    	StringBuffer sql=new StringBuffer("select * from plantlog where 1=1 ");
		if(!plantlogid.getText().equals("")){
			sql.append(" and id like '%"+plantlogid.getText()+"%'");
		}
		if(!plantlognom.getText().equals("")){
			sql.append(" and nom like '%"+plantlognom.getText()+"%'");
		}
		if(!plantlogsuividate.getText().equals("")){
			sql.append(" and suividate like '%"+plantlogsuividate.getText()+"%'");
		}
		if(!plantloghauteur.getText().equals("")){
			sql.append(" and hauteur like '%"+plantloghauteur.getText()+"%'");
		}
		if(!plantlogph.getText().equals("")){
			sql.append(" and ph like '%"+plantlogph.getText()+"%'");
		}
		if(!plantlognote.getText().equals("")){
			sql.append(" and note like '%"+plantlognote.getText()+"%'");
		}
		if(!plantlogphoto.getText().equals("")){
			sql.append(" and photo like '%"+plantlogphoto.getText()+"%'");
		}
		plantlogdata.clear();
		plantlogdata.addAll(DBUnitHelper.executeQuery(sql.toString(), Plantlog.class));
    }
    /**
     * plant新增
     *
     */
    @FXML
    void plantadd(ActionEvent event) throws IOException {
			List<Plant> ulist= plantdao.queryByKey("nom", plantnom.getText());
			if(ulist.size()>0){
				showMsg("此记录已经存在,无法新增");
			}else{
				plantdao.executeSql("INSERT INTO plant(nom,photo,plantationdate,rempotagedate,arronsagedate,entretiendate,coupedate,recottedate,hauteur,ph,mesuresdate,notes,interval1,interval2,interval3,interval4) values ("+"'"+plantnom.getText()+"',"+"'"+plantphoto.getText()+"',"+"'"+plantplantationdate.getText()+"',"+"'"+plantrempotagedate.getText()+"',"+"'"+plantarronsagedate.getText()+"',"+"'"+plantentretiendate.getText()+"',"+"'"+plantcoupedate.getText()+"',"+"'"+plantrecottedate.getText()+"',"+"'"+planthauteur.getText()+"',"+"'"+plantph.getText()+"',"+"'"+plantmesuresdate.getText()+"',"+"'"+plantnotes.getText()+"',"+"'"+plantinterval1.getText()+"',"+"'"+plantinterval2.getText()+"',"+"'"+plantinterval3.getText()+"',"+"'"+plantinterval4.getText()+"'"+")");
			}			
        	showMsg("新增成功");
        	plantrefresh();
        	
    }

    /**
     * plant删除
     */
    @FXML
    void plantdelete(ActionEvent event) throws IOException {
       for (int i = 0; i <plantdata.size(); i++) {
    	   if(plantdata.get(i).isSelected()){
    		   plantdao.delBykey("nom", plantdata.get(i).getNom());
    	   }
       }
       showMsg("删除成功");
       plantrefresh();
    }

    /**
     * plant修改
     */
    @FXML
    void plantmodify(ActionEvent event) throws IOException {
        	Plant plant=null;
        	for (int i = 0; i <plantdata.size(); i++) {
         	   if(plantdata.get(i).isSelected()){
         		   if(plant!=null){
         			  showMsg("请选择一个进行修改");
         			  return;
         		   }else{
         			   plant=plantdata.get(i);
         		   }
         	   }
            }
        	if(plant!=null){
				plantdao.executeSql("update plant set "+"nom='"+plantnom.getText()+"',"+"photo='"+plantphoto.getText()+"',"+"plantationdate='"+plantplantationdate.getText()+"',"+"rempotagedate='"+plantrempotagedate.getText()+"',"+"arronsagedate='"+plantarronsagedate.getText()+"',"+"entretiendate='"+plantentretiendate.getText()+"',"+"coupedate='"+plantcoupedate.getText()+"',"+"recottedate='"+plantrecottedate.getText()+"',"+"hauteur='"+planthauteur.getText()+"',"+"ph='"+plantph.getText()+"',"+"mesuresdate='"+plantmesuresdate.getText()+"',"+"notes='"+plantnotes.getText()+"',"+"interval1='"+plantinterval1.getText()+"',"+"interval2='"+plantinterval2.getText()+"',"+"interval3='"+plantinterval3.getText()+"',"+"interval4='"+plantinterval4.getText()+"'" +" where nom='"+plantnom.getText()+"'");		
				showMsg("修改成功");
				plantrefresh();
        	}else{
        		 showMsg("请选择一个进行修改");
        	}
            
    }
	public void plantrefresh(){
    	plantdata.clear();
    	plantdata.addAll(plantdao.getAll());
    	plantnom.clear();
    	plantphoto.clear();
    	plantplantationdate.clear();
    	plantrempotagedate.clear();
    	plantarronsagedate.clear();
    	plantentretiendate.clear();
    	plantcoupedate.clear();
    	plantrecottedate.clear();
    	planthauteur.clear();
    	plantph.clear();
    	plantmesuresdate.clear();
    	plantnotes.clear();
    	plantinterval1.clear();
    	plantinterval2.clear();
    	plantinterval3.clear();
    	plantinterval4.clear();
    }
   
    /**
     * plant查找
     */
    @FXML
    void plantsearch(ActionEvent event) throws CloneNotSupportedException {
    	StringBuffer sql=new StringBuffer("select * from plant where 1=1 ");
		if(!plantnom.getText().equals("")){
			sql.append(" and nom like '%"+plantnom.getText()+"%'");
		}
		if(!plantphoto.getText().equals("")){
			sql.append(" and photo like '%"+plantphoto.getText()+"%'");
		}
		if(!plantplantationdate.getText().equals("")){
			sql.append(" and plantationdate like '%"+plantplantationdate.getText()+"%'");
		}
		if(!plantrempotagedate.getText().equals("")){
			sql.append(" and rempotagedate like '%"+plantrempotagedate.getText()+"%'");
		}
		if(!plantarronsagedate.getText().equals("")){
			sql.append(" and arronsagedate like '%"+plantarronsagedate.getText()+"%'");
		}
		if(!plantentretiendate.getText().equals("")){
			sql.append(" and entretiendate like '%"+plantentretiendate.getText()+"%'");
		}
		if(!plantcoupedate.getText().equals("")){
			sql.append(" and coupedate like '%"+plantcoupedate.getText()+"%'");
		}
		if(!plantrecottedate.getText().equals("")){
			sql.append(" and recottedate like '%"+plantrecottedate.getText()+"%'");
		}
		if(!planthauteur.getText().equals("")){
			sql.append(" and hauteur like '%"+planthauteur.getText()+"%'");
		}
		if(!plantph.getText().equals("")){
			sql.append(" and ph like '%"+plantph.getText()+"%'");
		}
		if(!plantmesuresdate.getText().equals("")){
			sql.append(" and mesuresdate like '%"+plantmesuresdate.getText()+"%'");
		}
		if(!plantnotes.getText().equals("")){
			sql.append(" and notes like '%"+plantnotes.getText()+"%'");
		}
		if(!plantinterval1.getText().equals("")){
			sql.append(" and interval1 like '%"+plantinterval1.getText()+"%'");
		}
		if(!plantinterval2.getText().equals("")){
			sql.append(" and interval2 like '%"+plantinterval2.getText()+"%'");
		}
		if(!plantinterval3.getText().equals("")){
			sql.append(" and interval3 like '%"+plantinterval3.getText()+"%'");
		}
		if(!plantinterval4.getText().equals("")){
			sql.append(" and interval4 like '%"+plantinterval4.getText()+"%'");
		}
		plantdata.clear();
		plantdata.addAll(DBUnitHelper.executeQuery(sql.toString(), Plant.class));
    }
 	public void showMsg(String msg){
    	 Alert alert = new Alert(AlertType.INFORMATION);
         alert.titleProperty().set("提示");
         alert.headerTextProperty().set(msg);
         alert.showAndWait();
    }
    @FXML
    private void initialize() throws IOException {
        plantlogidcolumn.setCellValueFactory(new PropertyValueFactory<Plantlog, String>("id"));
        plantlognomcolumn.setCellValueFactory(new PropertyValueFactory<Plantlog, String>("nom"));
        plantlogsuividatecolumn.setCellValueFactory(new PropertyValueFactory<Plantlog, String>("suividate"));
        plantloghauteurcolumn.setCellValueFactory(new PropertyValueFactory<Plantlog, String>("hauteur"));
        plantlogphcolumn.setCellValueFactory(new PropertyValueFactory<Plantlog, String>("ph"));
        plantlognotecolumn.setCellValueFactory(new PropertyValueFactory<Plantlog, String>("note"));
        plantlogphotocolumn.setCellValueFactory(new PropertyValueFactory<Plantlog, String>("photo"));
		plantlogcolumn.setCellValueFactory(new PropertyValueFactory<Plantlog, Boolean>("selected"));
		plantlogtableView.setItems(plantlogdata);
		plantlogtableView.setEditable(true);
        plantlogrefresh();
        plantlogcolumn.setCellFactory(
                CellFactory.tableCheckBoxColumn(new Callback<Integer, ObservableValue<Boolean>>() {
                    public ObservableValue<Boolean> call(Integer index) {
                        final Plantlog plantlog = plantlogtableView.getItems().get(index);
                        ObservableValue<Boolean> ret =
                                new SimpleBooleanProperty(plantlog, "selected", plantlog.isSelected());
                        ret.addListener(new ChangeListener<Boolean>() {
                            public void changed(
                                    ObservableValue<? extends Boolean> observable,
                                    Boolean oldValue, Boolean newValue) {
                                plantlog.setSelected(newValue);
                                plantlogid.setText(plantlog.getId());
                                plantlognom.setText(plantlog.getNom());
                                plantlogsuividate.setText(plantlog.getSuividate());
                                plantloghauteur.setText(plantlog.getHauteur());
                                plantlogph.setText(plantlog.getPh());
                                plantlognote.setText(plantlog.getNote());
                                plantlogphoto.setText(plantlog.getPhoto());
                            }
                        });
                        return ret;
                    }
                }));
        plantnomcolumn.setCellValueFactory(new PropertyValueFactory<Plant, String>("nom"));
        plantphotocolumn.setCellValueFactory(new PropertyValueFactory<Plant, String>("photo"));
        plantplantationdatecolumn.setCellValueFactory(new PropertyValueFactory<Plant, String>("plantationdate"));
        plantrempotagedatecolumn.setCellValueFactory(new PropertyValueFactory<Plant, String>("rempotagedate"));
        plantarronsagedatecolumn.setCellValueFactory(new PropertyValueFactory<Plant, String>("arronsagedate"));
        plantentretiendatecolumn.setCellValueFactory(new PropertyValueFactory<Plant, String>("entretiendate"));
        plantcoupedatecolumn.setCellValueFactory(new PropertyValueFactory<Plant, String>("coupedate"));
        plantrecottedatecolumn.setCellValueFactory(new PropertyValueFactory<Plant, String>("recottedate"));
        planthauteurcolumn.setCellValueFactory(new PropertyValueFactory<Plant, String>("hauteur"));
        plantphcolumn.setCellValueFactory(new PropertyValueFactory<Plant, String>("ph"));
        plantmesuresdatecolumn.setCellValueFactory(new PropertyValueFactory<Plant, String>("mesuresdate"));
        plantnotescolumn.setCellValueFactory(new PropertyValueFactory<Plant, String>("notes"));
        plantinterval1column.setCellValueFactory(new PropertyValueFactory<Plant, String>("interval1"));
        plantinterval2column.setCellValueFactory(new PropertyValueFactory<Plant, String>("interval2"));
        plantinterval3column.setCellValueFactory(new PropertyValueFactory<Plant, String>("interval3"));
        plantinterval4column.setCellValueFactory(new PropertyValueFactory<Plant, String>("interval4"));
		plantcolumn.setCellValueFactory(new PropertyValueFactory<Plant, Boolean>("selected"));
		planttableView.setItems(plantdata);
		planttableView.setEditable(true);
        plantrefresh();
        plantcolumn.setCellFactory(
                CellFactory.tableCheckBoxColumn(new Callback<Integer, ObservableValue<Boolean>>() {
                    public ObservableValue<Boolean> call(Integer index) {
                        final Plant plant = planttableView.getItems().get(index);
                        ObservableValue<Boolean> ret =
                                new SimpleBooleanProperty(plant, "selected", plant.isSelected());
                        ret.addListener(new ChangeListener<Boolean>() {
                            public void changed(
                                    ObservableValue<? extends Boolean> observable,
                                    Boolean oldValue, Boolean newValue) {
                                plant.setSelected(newValue);
                                plantnom.setText(plant.getNom());
                                plantphoto.setText(plant.getPhoto());
                                plantplantationdate.setText(plant.getPlantationdate());
                                plantrempotagedate.setText(plant.getRempotagedate());
                                plantarronsagedate.setText(plant.getArronsagedate());
                                plantentretiendate.setText(plant.getEntretiendate());
                                plantcoupedate.setText(plant.getCoupedate());
                                plantrecottedate.setText(plant.getRecottedate());
                                planthauteur.setText(plant.getHauteur());
                                plantph.setText(plant.getPh());
                                plantmesuresdate.setText(plant.getMesuresdate());
                                plantnotes.setText(plant.getNotes());
                                plantinterval1.setText(plant.getInterval1());
                                plantinterval2.setText(plant.getInterval2());
                                plantinterval3.setText(plant.getInterval3());
                                plantinterval4.setText(plant.getInterval4());
                            }
                        });
                        return ret;
                    }
                }));
    }
    @FXML
    void exit(ActionEvent event) {
    	System.exit(0);
    }
}
