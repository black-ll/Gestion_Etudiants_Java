package interfaceEtudiant;

import java.util.List;

// <T> -> interface g�n�rique
public interface IDao<T> {

	// ajouter un objet o de type T
	boolean create(T o);
	
	// supprimer objet
	boolean delete(T o);
	
	// modifier objet
	boolean update(T o);
	
	// renvoie objet de l'id en param�tre
	T findById (int id);
	
	// renvoie liste des objets de type T
	List<T> findAll();
}
