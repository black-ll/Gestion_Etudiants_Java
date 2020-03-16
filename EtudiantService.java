package interfaceEtudiant;

import java.util.ArrayList;
import java.util.List;


public class EtudiantService implements IDao<Etudiant> {

	ArrayList<Etudiant> listeEtudiants = new ArrayList<Etudiant>();
	
	@Override
	public boolean create(Etudiant o) {
	
		return listeEtudiants.add(o);
	}

	@Override
	public boolean delete(Etudiant o) {
		
		return listeEtudiants.remove(o);
	}

	@Override
	public boolean update(Etudiant o) {
	// on recherche Etudiant via son id avec la method findById et on récupère le contenu de cet id
			Etudiant e = findById(o.getId());
			if (e != null)
			{
				listeEtudiants.set(listeEtudiants.indexOf(e), o);
				return true;
			}
			return false;
	}

	@Override
	public Etudiant findById(int id) {
	// on recherche l'Etudiant via son id ds la listeEtudiants et on affiche son contenu
			for (Etudiant e : listeEtudiants)
				if (e.getId() == id)
					return e;
			return null;
	}

	@Override
	public List<Etudiant> findAll() {
		// TODO Auto-generated method stub
		return listeEtudiants;
	}

	@Override
	public String toString() {
		return "EtudiantService [listeEtudiants=" + listeEtudiants + "]";
	}

	
	
}
