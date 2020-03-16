package interfaceEtudiant;

public class Etudiant {

	private int id;
	private String nom;
	private String prenom;
	private String sexe;
	private String filiere;
	
	private static int count = 0;	//  var qui va permettre de stocker id 
	
	// constructor avec id
	public Etudiant(int id, String nom, String prenom, String sexe, String filiere) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.filiere = filiere;
	}
	
	// constructor avec id auto-increment
	public Etudiant(String nom, String prenom, String sexe, String filiere) {
		super();
		this.id = ++count;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.filiere = filiere;
	}

	public Etudiant() {
		super();
	}

	
	// getters et setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getFiliere() {
		return filiere;
	}

	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}

	@Override
	public String toString() {
		return "Etudiant [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + ", filiere=" + filiere
				+ "]";
	}
	
	
	
	
	
	
	
	
}
