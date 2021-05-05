package application;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Calendar.Style;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;

import dao.Dao;
import entity.Plant;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CalendarApp extends Application {
	 public static Stage primary=new Stage();
	 public static Stage indexstage=new Stage();
	 
	 public static Plant planteDetail;
	 static Dao<Plant> plantdao = new Dao(new Plant());
	 static SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
	 static CalendarView calendarView = new CalendarView(); 
	 public static boolean betweetDate(String mdate,String startdate,String enddate){
		 java.util.Calendar start = java.util.Calendar.getInstance();
		 java.util.Calendar end = java.util.Calendar.getInstance();
		 java.util.Calendar m = java.util.Calendar.getInstance();
			 try {
				start.setTime(s.parse(startdate));
				end.setTime(s.parse(enddate));
				m.setTime(s.parse(mdate));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			 if(m.after(start)&&m.before(end))return true;
			return false;
		}
	 public static String getdate(String mydate, int i) // //获取前后日期 i为正数 向后推迟i天，负数时向前提前i天
	 {
		 Date dat = null;
		 java.util.Calendar cd = java.util.Calendar.getInstance();
		 try {
			cd.setTime(s.parse(mydate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 cd.add(java.util.Calendar.DATE, i);
		 dat = cd.getTime();
		 
		 return s.format(dat);
	 }
	 
        @Override
        public void start(Stage primaryStage) throws Exception {
        	
            	
               initCalen(primaryStage);
        }

        public static void initCalen(Stage primaryStage){
        	indexstage=primaryStage;
        	refresh();
			
            calendarView.setRequestedTime(LocalTime.now());
            AnchorPane root = new AnchorPane();
			Button btn=new Button();
			btn.setLayoutX(1200);
			btn.setLayoutY(50);
			btn.setPrefSize(70, 30);
			btn.setText("Plante");
			btn.setOnAction(new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent event) {
				        try
				        {
				            Parent root = FXMLLoader.load(getClass().getResource("../View/main.fxml"));
				            primary.setTitle("Agenda Etudiant Jardinage - Liste de plantes");
				            primary.setScene(new Scene(root,800,600));
				            primary.centerOnScreen();
				            primary.show();
				            primaryStage.hide();

				        }
				        catch (IOException e)
				        {
				            e.printStackTrace();
				        }						
				}
			});
			root.getChildren().add(btn);
			calendarView.setPrefSize(1200, 1000);
	        root.getChildren().add(calendarView);
            primaryStage.setTitle("Calendar");
            primaryStage.setScene(new Scene(root, 1300, 1000));
            primaryStage.setWidth(1300);
            primaryStage.setHeight(1000);
            primaryStage.centerOnScreen();
            primaryStage.show();
        }
        public static void refresh(){
        	Calendar inteval1plantdate = new Calendar("inteval1"); 
        	//inteval1plantdate.addEntries(dentistAppointment);
        	inteval1plantdate.setStyle(Style.STYLE1);
        	Calendar inteval2plantdate = new Calendar("inteval2"); 
        	inteval2plantdate.setStyle(Style.STYLE2);
        	Calendar inteval3plantdate = new Calendar("inteval3"); 
        	inteval3plantdate.setStyle(Style.STYLE3);
        	Calendar inteval4plantdate = new Calendar("inteval4"); 
        	inteval4plantdate.setStyle(Style.STYLE4);
        	
        	List<Plant> plantlist=plantdao.getAll();
        	for (int i = 0; i < plantlist.size(); i++) {
				Plant pt=plantlist.get(i);
				if(pt.getInterval1()!=null&&!pt.getInterval1().equals("")){
					int invl = 0;int k=0;
					if(pt.getInterval1().equals("everyday")){
						invl=1;
					}else if(pt.getInterval1().equals("everyweek")){
						invl=7;
					}else if(pt.getInterval1().equals("everymonth")){
						invl=30;
					}
					String mdate=getdate(pt.getRempotagedate(), k*invl);
					while(betweetDate(mdate, pt.getPlantationdate(), pt.getRecottedate())){
						Entry<String> dentistAppointment = new Entry<>(plantlist.get(i).getNom()+" rempotage");
						try {
							Date startdate = s.parse(mdate);
							Date enddate=s.parse(mdate);
				        	dentistAppointment.setInterval(startdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
				        			enddate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				        	inteval1plantdate.addEntry(dentistAppointment);
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						k++;
						mdate=getdate(pt.getRempotagedate(), k*invl);
					}
				}
				if(pt.getInterval2()!=null&&!pt.getInterval2().equals("")){
					int invl = 0;int k=0;
					if(pt.getInterval2().equals("everyday")){
						invl=1;
					}else if(pt.getInterval2().equals("everyweek")){
						invl=7;
					}else if(pt.getInterval2().equals("everymonth")){
						invl=30;
					}
					String mdate=getdate(pt.getArronsagedate(), k*invl);
					while(betweetDate(mdate, pt.getPlantationdate(), pt.getRecottedate())){
						Entry<String> dentistAppointment = new Entry<>(plantlist.get(i).getNom()+" arronsage");
						try {
							Date startdate = s.parse(mdate);
							Date enddate=s.parse(mdate);
				        	dentistAppointment.setInterval(startdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
				        			enddate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				        	inteval2plantdate.addEntry(dentistAppointment);
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						k++;
						mdate=getdate(pt.getArronsagedate(), k*invl);
					}
				}
				if(pt.getInterval3()!=null&&!pt.getInterval3().equals("")){
					int invl = 0;int k=0;
					if(pt.getInterval3().equals("everyday")){
						invl=1;
					}else if(pt.getInterval3().equals("everyweek")){
						invl=7;
					}else if(pt.getInterval3().equals("everymonth")){
						invl=30;
					}
					String mdate=getdate(pt.getEntretiendate(), k*invl);
					while(betweetDate(mdate, pt.getPlantationdate(), pt.getRecottedate())){
						Entry<String> dentistAppointment = new Entry<>(plantlist.get(i).getNom()+" entretien");
						try {
							Date startdate = s.parse(mdate);
							Date enddate=s.parse(mdate);
				        	dentistAppointment.setInterval(startdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
				        			enddate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				        	inteval3plantdate.addEntry(dentistAppointment);
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						k++;
						mdate=getdate(pt.getEntretiendate(), k*invl);
					}
				}
				if(pt.getInterval4()!=null&&!pt.getInterval4().equals("")){
					int invl = 0;int k=0;
					if(pt.getInterval4().equals("everyday")){
						invl=1;
					}else if(pt.getInterval4().equals("everyweek")){
						invl=7;
					}else if(pt.getInterval4().equals("everymonth")){
						invl=30;
					}
					System.out.println(pt.getCoupedate());
					String mdate=getdate(pt.getCoupedate(), k*invl);
					while(betweetDate(mdate, pt.getPlantationdate(), pt.getRecottedate())){
						Entry<String> dentistAppointment = new Entry<>(plantlist.get(i).getNom()+" coupe");
						try {
							Date startdate = s.parse(mdate);
							Date enddate=s.parse(mdate);
				        	dentistAppointment.setInterval(startdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
				        			enddate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				        	inteval4plantdate.addEntry(dentistAppointment);
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						k++;
						mdate=getdate(pt.getCoupedate(), k*invl);
					}
				}
			}
            CalendarSource myCalendarSource = new CalendarSource("My Calendars"); 
            myCalendarSource.getCalendars().addAll(inteval1plantdate);
            myCalendarSource.getCalendars().addAll(inteval2plantdate);
            myCalendarSource.getCalendars().addAll(inteval3plantdate);
            myCalendarSource.getCalendars().addAll(inteval4plantdate);
            calendarView.getCalendarSources().clear();
            calendarView.getCalendarSources().addAll(myCalendarSource); 
        }
        public static void main(String[] args) {
                launch(args);
        }
}