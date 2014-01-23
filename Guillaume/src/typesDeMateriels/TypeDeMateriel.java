package typesDeMateriels;

import java.util.ArrayList;

import materiels.Materiel;

/**
 * Cette classe permet de creer un TypeDeMateriel. C'est une classe abstraite car on ne veut pas instancier d'objets de cette classe.
 * @author RAHBARI Guillaume - TIJANI Yassine
 * @version version 2.0
 */

public abstract class TypeDeMateriel 
{
    // Variable indiquant le nom de ce type de materiel.
    protected String nom;
    // Une liste contenant des materiels de ce type.
    protected ArrayList<Materiel> listeMateriel;
    // Cette variable indique le nombre d'exemplaires de materiels de ce type de materiel.
    protected int nombre;
    // Cette variable indique le nombre de fois qu'un materiel de ce type a ete emprunte.
    protected int nombreEmprunt;
    // Cette variable indique le nombre de fois qu'un materiel de ce type est tombe en panne.
    protected int nombrePanne;

    /**
     * Cree un TypeDeMateriel en renseignant le nom de ce type.
     * @param nom Le nom du type de materiel.
     */
    public TypeDeMateriel (String nom)
    {
       	this.nom = nom;
        listeMateriel = new ArrayList<Materiel>();
        nombre = 0;
        nombreEmprunt = 0;
        nombrePanne = 0;
    }

    /**
     * Cree un TypeDeMateriel avec une valeur par defaut.
     */
    public TypeDeMateriel()
    {
        this("Inconnu");
    }

    /**
     * La methode getNom permet de renvoyer le nom du type de materiel.
     * @return Un String indiquant le nom du materiel.
     */
    public String getNom()
    {
        return nom;
    }

    /**
     * La methode getListe permet de renvoyer la liste de materiels du type de materiel.
     * @return Une ArrayList<Materiel> contenant la liste des materiels d'un meme type.
     */
    public ArrayList<Materiel> getListe()
    {
        return listeMateriel;
    }

    /**
     * La methode getNombre permet de renvoyer le nombre d'exemplaire de materiels de ce type.
     * @return Un int indiquant le nombre d'exemplaires de materiels de ce type.
     */
    public int  getNombre()
    {
        return nombre;
    }

    /**
     * La methode getNombreEmprunt permet de renvoyer le nombre de fois que ce TypeDeMateriel a ete emprunte.
     * @return Un int indiquant le nombre de fois que ce TypeDemateriel a ete emprunte.
     */
    public int getNombreEmprunt()
    {
        return nombreEmprunt;
    }

    /**
     * La methode getNombrePanne permet de renvoyer le nombre de fois que ce TypeDeMateriel est tombe en panne.
     * @return Un int indiquant le nombre de fois que ce TypeDemateriel est tombe en panne.
     */
    public int getNombrePanne()
    {
        return nombrePanne;
    }

    /**
     * La methode augmenterNombreEmprunt permet d'incrementer de 1 le nombre de fois que ce TypeDeMateriel a ete emprunte.
     */
    public void augmenterNombreEmprunt()
    {
        nombreEmprunt = nombreEmprunt + 1;
    }

    /**
     * La methode augmenterNombrePanne permet d'incrementer de 1 le nombre de fois que ce TypeDeMateriel est tombe en panne.
     */
    public void augmenterNombrePanne()
    {
        nombrePanne = nombrePanne + 1;
    }

    /**
     * La methode ajouterMateriel permet d'ajouter un Materiel a la liste du TypeDeMateriel et d'incrementer de 1 le nombre de materiels que contient ce type.
     * @param nomMateriel Le nom du Materiel a ajouter a la liste.
     */
    public void ajouterMateriel (String nomMateriel)
    {
        listeMateriel.add(new Materiel(nomMateriel + nombre));
        nombre = nombre + 1;
    }

    /**
     * La methode contient permet de savoir si un Materiel appartient a listeMateriel.
     * @param nomMateriel Le nom du materiel.
     * @return Un boolean indiquant si le Materiel appartient a listeMateriel.
     */
    public boolean contient (String nomMateriel)
    {
        boolean booleanSortie = false;
        int i = 0;
        while (booleanSortie == false && i < listeMateriel.size())
        {
            if (listeMateriel.get(i).getNom().equals(nomMateriel))
            {
                booleanSortie = true;
            }
            else
            {
                i = i +1;
            }
        }
        return booleanSortie;
    }

    /**
     * La methode retirerMateriel permet de retirer un Materiel de la liste TypeDeMateriel.
     * @param nomMateriel Le nom du Materiel a retirer de la liste.
     */
    public void retirerMateriel (String nomMateriel)
    {
        boolean booleanSortie = false;
        int i = 0;
        while (booleanSortie == false || i < listeMateriel.size())
        {
            if (listeMateriel.get(i).getNom().equals(nomMateriel))
            {
                booleanSortie = true;
                listeMateriel.remove(i);
                nombre = nombre - 1;
            }
            else
            {
                i = i + 1;
            }
        }
    }

    /**
     * La methode renvoyerIEMEElement permet de renvoyer le ieme element de la liste.
     * @param position L'entier indiquant l'element de la liste que l'on souhaite recuperer.
     * @return Le Materiel que l'on souhaite recuperer.
     */
    public Materiel renvoyerIEMEElement (int position)
    {
        if (position < listeMateriel.size())
        {
            return listeMateriel.get(position);
        }
        else
        {
            return null;
        }
    }

    /**
     * La methode renvoyerNomIEMEElement permet de renvoyer le nom du ieme element de la liste.
     * @param position L'entier indiquant l'element de la liste que l'on souhaite recuperer.
     * @return Le nom du Materiel que l'on souhaite recuperer.
     */
    public String renvoyerNomIEMEElement (int position)
    {
        if (position < listeMateriel.size())
        {
            return listeMateriel.get(position).getNom();
        }
        else
        {
            return null;
        }
    }

    /**
     * La methode toString permet de renvoyer la liste des materiels contenus dans ce type de materiel.
     * @return Un String affichant la liste des materiels.
     */
    public String toString()
    {
	   return listeMateriel + "";
    }
}
