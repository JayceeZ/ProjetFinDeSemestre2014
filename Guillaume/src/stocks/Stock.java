package stocks;

import typesDeMateriels.Camera;
import typesDeMateriels.Casque;
import typesDeMateriels.Tablette;
import typesDeMateriels.Telephone;
import typesDeMateriels.TypeDeMateriel;

/**
 * Une classe permettant de gerer un stock contenant des TypeDeMateriel. C'est une classe abstraite car on ne veut pas instancier d'objets de cette classe.
 * @author RAHBARI Guillaume - TIJANI Yassine
 * @version version 2.0
 */

abstract class Stock
{
    // Le nom du stock
    protected String nomStock;
    // Liste de TypeDeMateriel pour stocker les types de materiel en stock.
    protected TypeDeMateriel[] tableauTypeDeMateriel;

    /**
     * Cree un objet Stock. Celui-ci est un tableau contenant les 4 types de materiels predefinis (Tablette, Telephone, Camera, Casque).
     */
    public Stock (String nomStock)
    {
        this.nomStock = nomStock;
        tableauTypeDeMateriel = new TypeDeMateriel[4];
        tableauTypeDeMateriel[0] = new Tablette();
        tableauTypeDeMateriel[1] = new Telephone();
        tableauTypeDeMateriel[2] = new Casque();
        tableauTypeDeMateriel[3] = new Camera();
    }

    /**
     * La methode getNom permet de recuperer le nom du stock.
     * @return Un String indiquant le nom du stock.
     */
    public String getNom()
    {
        return nomStock;
    }

    /**
     * La methode contient permet de savoir si un materiel appartient a un stock.
     * @param nomTypeDeMateriel Le type de materiel auxquel appartient le materiel.
     * @param nomMateriel Le nom du materiel.
     * @return Un boolean indiquant si le stock contient le materiel ou non.
     */
    public boolean contient(String nomTypeDeMateriel, String nomMateriel)
    {
        if (tableauTypeDeMateriel[0].getNom().equals(nomTypeDeMateriel))
        {
            return tableauTypeDeMateriel[0].contient(nomMateriel);
        }
        if (tableauTypeDeMateriel[1].getNom().equals(nomTypeDeMateriel))
        {
            return tableauTypeDeMateriel[1].contient(nomMateriel);
        }
        if (tableauTypeDeMateriel[2].getNom().equals(nomTypeDeMateriel))
        {
            return tableauTypeDeMateriel[2].contient(nomMateriel);
        }
        if (tableauTypeDeMateriel[3].getNom().equals(nomTypeDeMateriel))
        {
            return tableauTypeDeMateriel[3].contient(nomMateriel);
        }
        else
        {
            return false;
        }
    }

    /**
     * La methode ajouterMateriel permet d'ajouter un Materiel a la liste du TypeDeMateriel renseigne.
     * @param nomTypeDeMateriel Le nom du type de materiel auxquel on souhaite ajouter le materiel.
     */
    public void ajouterMateriel (String nomTypeDeMateriel)
    {
        if (tableauTypeDeMateriel[0].getNom().equals(nomTypeDeMateriel))
        {
        	((Tablette)tableauTypeDeMateriel[0]).ajouterMateriel();
        }
        if (tableauTypeDeMateriel[1].getNom().equals(nomTypeDeMateriel))
        {
            ((Telephone)tableauTypeDeMateriel[1]).ajouterMateriel();
        }
        if (tableauTypeDeMateriel[2].getNom().equals(nomTypeDeMateriel))
        {
            ((Casque)tableauTypeDeMateriel[2]).ajouterMateriel();
        }
        if (tableauTypeDeMateriel[3].getNom().equals(nomTypeDeMateriel))
        {
            ((Camera)tableauTypeDeMateriel[3]).ajouterMateriel();
        }
    }

    /**
     * La methode retirerMateriel permet de retirer un Materiel de la liste du TypeDeMateriel renseigne.
     * @param nomTypeDeMateriel Le nom du type de materiel auxquel on souhaite retirer le materiel.
     * @param nomMateriel Le materiel a retirer.
     */
    public void retirerMateriel (String nomTypeDeMateriel, String nomMateriel)
    {
        if (tableauTypeDeMateriel[0].getNom().equals(nomTypeDeMateriel))
        {
            tableauTypeDeMateriel[0].retirerMateriel(nomMateriel);
        }
        if (tableauTypeDeMateriel[1].getNom().equals(nomTypeDeMateriel))
        {
            tableauTypeDeMateriel[1].retirerMateriel(nomMateriel);
        }
        if (tableauTypeDeMateriel[2].getNom().equals(nomTypeDeMateriel))
        {
            tableauTypeDeMateriel[2].retirerMateriel(nomMateriel);
        }
        if (tableauTypeDeMateriel[3].getNom().equals(nomTypeDeMateriel))
        {
            tableauTypeDeMateriel[3].retirerMateriel(nomMateriel);
        }
    }

    /**
     * Cette methode toString permet de renvoyer le type de materiel en position i.
     * @param i La position (dans le tableau) du type de materiel que l'on veut afficher.
     * @return Un String affichant (dans un tableau) les materiels contenus dans le type de materiel a la position i.
     */
    public String toString(int i) 
    {
        return tableauTypeDeMateriel[i].toString();
    }

    /**
     * Cette methode toString permet d'afficher les 4 types de materiels (ainsi que les materiels que eux memes contiennent).
     * @return Un String affichant la liste des differents materiels selon leur type.
     */
    public String toString()
    {
        String s = "Liste des Tablettes : " + toString(0);
        for (int i=1; i < tableauTypeDeMateriel.length ; i = i+1)
        {
            s = s + "\n" + "Liste des " + tableauTypeDeMateriel[i].getNom() + " : " + toString(i);
        }
        return s;
    }
}