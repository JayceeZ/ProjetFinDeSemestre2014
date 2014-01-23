import java.util.Calendar;

import materiels.Materiel;


/**
 * Une classe permettant de gerer les emprunts.
 * @author RAHBARI Guillaume - TIJANI Yassine
 * @version version 2.0
 */

public class Emprunt {
    private String nomEmprunteur;
    private Calendar dateEmprunt;
    private Materiel materielEmprunter;
    private String nomTypeDeMateriel;
    private int dureeEmprunt;

/**
* Constructeur, permet de creer un Objet de type Emprunt
* @param nomEmprunteur Le nom de l'emprunteur
* @param materielEmprunter Le materiel emprunter
* @param nomTypeDeMateriel Le nom du type de materiel
* @param dureeEmprunt La duree de l'emprunt
*/
    public Emprunt(String nomEmprunteur, Materiel materielEmprunter, String nomTypeDeMateriel, int dureeEmprunt) {
    	this.nomEmprunteur = nomEmprunteur;
    	this.materielEmprunter = materielEmprunter;
    	dateEmprunt = Calendar.getInstance();
    	this.nomTypeDeMateriel = nomTypeDeMateriel;
        this.dureeEmprunt = dureeEmprunt;
    }

 /**
 * Constructeur par defaut, permet de creer un materiel non referencer
 */
    public Emprunt() {
    	this("Inconnu", new Materiel(), "Tablette", 7);
    }

 /**
 * Retourne le nom de l'emprunteur
 * @return String nomEmprunteur
 */
    public String getNomEmprunteur() {
    	return nomEmprunteur;
    }
/**
* La methode getNomTypeDeMateriel permet de recuperer le type de Materiel de l'emprunt
* @return String nomTypeDeMateriel
*/
    public String getNomTypeDeMateriel() {
        return nomTypeDeMateriel;
    }

 /**
 * Retourne la duree de l'emprunt
 * @return int dureeEmprunt
 */
    public int getDuree() {
        return dureeEmprunt;
    }

 /**
 * Retourne le Materiel Emprunter pour cet emprunt
 * @return String nomEmprunteur
 */
    public Materiel getMaterielEmprunter () {
        return materielEmprunter;
    }

 /**
 * Methode pour recuperer la date de l'emprunt, ainsi que l'emprunteur
 */
    private String getDateEmprunt() {
        int month = dateEmprunt.get(Calendar.MONTH) + 1;
    	return "date d'emprunt: " + dateEmprunt.get(Calendar.DAY_OF_MONTH)+"/"+month+"/"+ dateEmprunt.get(Calendar.YEAR) + " l'emprunteur est: " + nomEmprunteur;
    }
/**
* Methode toString() qui affiche toutes les informations sur cet emprunt, notament grace a la methode getDateEmprunt()
*/
    public String toString () {
        return  "\n"+getDateEmprunt() + " \n"+ "le type du est materiel est : " + nomTypeDeMateriel +"\n"+ materielEmprunter + " duree d'emprunt : " + dureeEmprunt + " j."+"\n";
    }
}
