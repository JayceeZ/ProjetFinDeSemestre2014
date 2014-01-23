package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("unchecked")
/**
* Classe modelisant la base de donnees contenant la liste des emprunts et des emprunteurs.
* La liste des emprunts est vide au depart mais pas la liste des emprunteurs.
* @author Nabil ELMOUSSAID
*
*/
public class Database {

    // Liste des emprunts
    private List<Emprunt> emprunts;

    // Liste des emprunteurs potentiels
    private ArrayList<Emprunteur> emprunteurs;

    // Nom du fichier par defaut contenant les emprunteurs
    private String fichierUtilisateurs = "Utilisateurs";

    // Version du fichier par defaut contenant les emprunteurs
    private String versionFichierUtilisateurs = "1.0.0";

    // Nom du fichier par defaut contenant les emprunts
    private String fichierEmprunts = "Emprunts";

    // Version du fichier par defaut contenant les emprunts
    private String versionFichierEmprunts = "1.0.0";

    /**
* Constructeur par defaut. Recupere la liste des emprunts depuis le fichier
* xml.
*/
    public Database() {
        // Charge le fichier decrivant les personnes pouvant emprunter
        emprunteurs = (ArrayList<Emprunteur>) DataXML.load(fichierUtilisateurs,
                versionFichierUtilisateurs);

        // Charge le fichier contenant les emprunts actuels
        try {
            emprunts = (ArrayList<Emprunt>) DataXML.load(fichierEmprunts,
                    versionFichierEmprunts);
        } catch (Exception e) {
            emprunts = new ArrayList<Emprunt>();
        }
    }

    /**
* Retourne la liste des emprunts
*
* @return liste des emprunts
*/
    public List<Emprunt> getEmprunts() {
        return emprunts;
    }

    /**
* Fixe un nouvel etat des emprunts
*
* @param emprunts
* Nouvel etat des emprunts
*/
    public void setEmprunts(List<Emprunt> emprunts) {
        this.emprunts = emprunts;
    }

    /**
* Retourne la liste des emprunteurs potentiels
*
* @return liste des emprunteurs potentiels
*/
    public ArrayList<Emprunteur> getEmprunteurs() {
        return emprunteurs;
    }

    /**
* Fixe la liste des emprunteurs potentiel
*
* @param emprunteurs
* liste des emprunteurs potentiel
*/
    public void setEmprunteurs(ArrayList<Emprunteur> emprunteurs) {
        this.emprunteurs = emprunteurs;
    }

    /**
     * Ajout d'un emprunt a liste des emprunts
     * 
     * @param e
     *            Emprunt a ajouter
     */
    public void ajouterEmprunt(Emprunt e) {
        e.setId(getPlusGrosIdEmprunt());
    	emprunts.add(e);
        
    }
    /**
     * Retourne le plus gros id des emprunts déjà existants
     * @return
     */
    public int getPlusGrosIdEmprunt() {
    	int id = 0;
    	for(Emprunt emprunt : this.emprunts){
    		if (emprunt.getId() > id)
    			id = emprunt.getId();
    	}
    	return id;
    }
    
    /**
     * Ajout d'un emprunteur a liste des emprunteurs
     * 
     * @param e
     *            Emprunteur a ajouter
     */
    public void ajouterEmprunteur(Emprunteur e)
    {
    	e.setId(this.getPlusGrosIdEmprunteur());
    	emprunteurs.add(e);
    }
    
/**
 * Retourne le plus gros id des emprunteurs déjà existants
 * @return
 */
    public String getPlusGrosIdEmprunteur() {
    	int id = 0;
    	for(Emprunteur emprunteur : this.emprunteurs){
    		if (Integer.parseInt(emprunteur.getId()) > id)
    			id = Integer.parseInt(emprunteur.getId());
    	}
    	return Integer.toString(id);
    }
    

    
    /**
* Retire un emprunteur a liste des emprunteurs
*
* @param e
* Emprunteur a ajouter
*/
    public void retirerEmprunteur(Emprunteur e)
    {
            emprunteurs.remove(e);
    }

    /**
* Enregistre la liste des emprunts dans un fichier xml
*/
    public void enregistrerListeEmprunt() {
        DataXML.store(emprunts, fichierEmprunts, versionFichierEmprunts);
    }
    
    /**
* Enregistre la liste des emprunteurs dans un fichier xml
*/
    public void enregistrerListeEmprunteur() {
        DataXML.store(emprunteurs, fichierUtilisateurs, versionFichierUtilisateurs);
    }

    /**
* Retire les emprunts specifie en parametre
*
* @param ids
* Liste des emprunts a retirer
*/
    public void retirerEmprunts(List<Integer> ids) {
        Iterator<Emprunt> iter = emprunts.iterator();
        while (iter.hasNext()) {
            Emprunt emp = iter.next();
            for (int id : ids) {
                if (emp.getId() == id) {
                    iter.remove();
                }
            }
        }
    }

}


