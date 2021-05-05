package entity;
/**connexion sql et installation*/
public class Plantlog extends BaseEntity implements java.io.Serializable {

	private String id;
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String nom;
	public String getNom() {
		return this.nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	private String suividate;
	public String getSuividate() {
		return this.suividate;
	}
	public void setSuividate(String suividate) {
		this.suividate = suividate;
	}
	private String hauteur;
	public String getHauteur() {
		return this.hauteur;
	}
	public void setHauteur(String hauteur) {
		this.hauteur = hauteur;
	}
	private String ph;
	public String getPh() {
		return this.ph;
	}
	public void setPh(String ph) {
		this.ph = ph;
	}
	private String note;
	public String getNote() {
		return this.note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	private String photo;
	public String getPhoto() {
		return this.photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	

}
