package stocks;

import typesDeMateriels.TypeDeMateriel;

/**
 * Cette classe permet de gerer les 4 stocks differents (le stock de materiels empruntables, empruntes, rendus, et en reparations) utiles pour l'application Stock Manager.
 * @author RAHBARI Guillaume - TIJANI Yassine
 * @version version 2.0
 */

public class StockManager
{
    // Tableau de Stock pour stocker les 4 stocks.
    private Stock[] tableauStock;

    /**
     * Cree un objet StockManager. Celui-ci est un tableau contenant les 4 stocks.
     */
    public StockManager()
    {
        tableauStock = new Stock[4];
        tableauStock[0] = new StockEmpruntable();
        tableauStock[1] = new StockEmprunter();
        tableauStock[2] = new StockRendu();
        tableauStock[3] = new StockAtelier();
    }

    /**
     * La methode ajouterMaterielEmpruntable permet d'ajouter un Materiel a la liste du TypeDeMateriel du Stock Empruntable.
     * @param nomTypeDeMateriel Le nom du type de materiel auxquel on souhaite ajouter le materiel.
     */
    public void ajouterMaterielEmpruntable (String nomTypeDeMateriel)
    {
        tableauStock[0].ajouterMateriel(nomTypeDeMateriel);
        System.out.println("Le materiel a bien ete ajoute au " + tableauStock[0].getNom());
    }

    /**
     * La methode retirerMaterielEmpruntable permet de retirer un Materiel de la liste du TypeDeMateriel du Stock Empruntable.
     * @param nomTypeDeMateriel Le nom du type de materiel auxquel on souhaite retirer le materiel.
     * @param nomMateriel Le materiel a retirer.
     */
    public void retirerMaterielEmpruntable (String nomTypeDeMateriel, String nomMateriel)
    {
        tableauStock[0].retirerMateriel(nomTypeDeMateriel, nomMateriel);
        System.out.println("Le materiel " + nomMateriel + " a bien ete retire du " + tableauStock[0].getNom());
    }

    /**
     * La methode ajouterMaterielEmprunter permet d'ajouter un Materiel a la liste du TypeDeMateriel du Stock Emprunte.
     * @param nomTypeDeMateriel Le nom du type de materiel auxquel on souhaite ajouter le materiel.
     */
    public void ajouterMaterielEmprunter (String nomTypeDeMateriel)
    {
        tableauStock[1].ajouterMateriel(nomTypeDeMateriel);
        System.out.println("Le materiel a bien ete ajoute au " + tableauStock[1].getNom());
    }

    /**
     * La methode retirerMaterielEmprunter permet de retirer un Materiel de la liste du TypeDeMateriel du Stock Emprunte.
     * @param nomTypeDeMateriel Le nom du type de materiel auxquel on souhaite retirer le materiel.
     * @param nomMateriel Le materiel a retirer.
     */
    public void retirerMaterielEmprunter (String nomTypeDeMateriel, String nomMateriel)
    {
        tableauStock[1].retirerMateriel(nomTypeDeMateriel, nomMateriel);
        System.out.println("Le materiel " + nomMateriel + " a bien ete retire du " + tableauStock[1].getNom());
    }

    /**
     * La methode ajouterMaterielRendu permet d'ajouter un Materiel a la liste du TypeDeMateriel du Stock Rendu.
     * @param nomTypeDeMateriel Le nom du type de materiel auxquel on souhaite ajouter le materiel.
     */
    public void ajouterMaterielRendu (String nomTypeDeMateriel)
    {
        tableauStock[2].ajouterMateriel(nomTypeDeMateriel);
        System.out.println("Le materiel a bien ete ajoute au " + tableauStock[2].getNom());
    }

    /**
     * La methode retirerMaterielRendu permet de retirer un Materiel de la liste du TypeDeMateriel du Stock Rendu.
     * @param nomTypeDeMateriel Le nom du type de materiel auxquel on souhaite retirer le materiel.
     * @param nomMateriel Le materiel a retirer.
     */
    public void retirerMaterielRendu (String nomTypeDeMateriel, String nomMateriel)
    {
        tableauStock[2].retirerMateriel(nomTypeDeMateriel, nomMateriel);
        System.out.println("Le materiel " + nomMateriel + " a bien ete retire du " + tableauStock[2].getNom());
    }

    /**
     * La methode ajouterMaterielAtelier permet d'ajouter un Materiel a la liste du TypeDeMateriel du Stock Atelier.
     * @param nomTypeDeMateriel Le nom du type de materiel auxquel on souhaite ajouter le materiel.
     */
    public void ajouterMaterielAtelier (String nomTypeDeMateriel)
    {
        tableauStock[3].ajouterMateriel(nomTypeDeMateriel);
        System.out.println("Le materiel a bien ete ajoute au " + tableauStock[3].getNom());
    }

    /**
     * La methode retirerMaterielAtelier permet de retirer un Materiel de la liste du TypeDeMateriel du Stock Atelier.
     * @param nomTypeDeMateriel Le nom du type de materiel auxquel on souhaite retirer le materiel.
     * @param nomMateriel Le materiel a retirer.
     */
    public void retirerMaterielAtelier (String nomTypeDeMateriel, String nomMateriel)
    {
        tableauStock[3].retirerMateriel(nomTypeDeMateriel, nomMateriel);
        System.out.println("Le materiel " + nomMateriel + " a bien ete retire du " + tableauStock[3].getNom());
    }

    /**
     * La methode contient permet de savoir si un materiel appartient au stock entre en parametre.
     * @param stock Le stock auquel on veut savoir si le materiel appartient.
     * @param nomTypeDeMateriel Le type de materiel auxquel appartient le materiel.
     * @param nomMateriel Le nom du materiel.
     * @return Un boolean indiquant si le materiel appartient ou non au stock.
     */
    private boolean contient (Stock stock, String nomTypeDeMateriel, String nomMateriel)
    {
        if (stock.contient(nomTypeDeMateriel, nomMateriel))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * La methode contientRendu permet de savoir si un materiel appartient au stock rendu.
     * @param nomTypeDeMateriel Le type de materiel auxquel appartient le materiel.
     * @param nomMateriel Le nom du materiel.
     * @return Un boolean indiquant si le materiel appartient ou non au stock rendu.
     */
    public boolean contientRendu (String nomTypeDeMateriel, String nomMateriel)
    {
        if (tableauStock[2].contient(nomTypeDeMateriel, nomMateriel))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * La methode deplacer permet de deplacer un materiel de stock1 vers stock2.
     * @param stock1 Le stock duquel on retire le materiel.
     * @param stock2 Le stock vers lequel on souhaite deplacer le materiel.
     * @param nomTypeDeMateriel Le type de materiel auxquel appartient le materiel.
     * @param nomMateriel Le nom du materiel.
     * @return Un boolean indiquant si le materiel a ete deplace ou non.
     */
    private boolean deplacer (Stock stock1, Stock stock2, String nomTypeDeMateriel, String nomMateriel)
    {
        if (contient(stock1, nomTypeDeMateriel, nomMateriel))
        {
            deplacerSansVerification(stock1, stock2, nomTypeDeMateriel, nomMateriel);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * La methode deplacerSansVerification permet de deplacer un materiel de stock1 vers stock2 sans verifier que le materiel appartient au stock1.
     * @param stock1 Le stock duquel on retire le materiel.
     * @param stock2 Le stock vers lequel on souhaite deplacer le materiel.
     * @param nomTypeDeMateriel Le type de materiel auxquel appartient le materiel.
     * @param nomMateriel Le nom du materiel.
     */
    private void deplacerSansVerification(Stock stock1, Stock stock2, String nomTypeDeMateriel, String nomMateriel)
    {
        stock1.retirerMateriel(nomTypeDeMateriel, nomMateriel);
        stock2.ajouterMateriel(nomTypeDeMateriel);
        System.out.println("Le materiel a bien ete deplace de " + stock1.getNom() + " vers " + stock2.getNom());
    }

    /**
     * La methode emprunter permet de deplacer un materiel de stockEmpruntable vers stockEmprunter. Elle permet egalement d'incrementer de 1 (dans le stockEmprunter) le nombre de fois que le type de materiel a ete emprunte.
     * @param nomTypeDeMateriel Le type de materiel auxquel appartient le materiel.
     * @param nomMateriel Le nom du materiel.
     */
    public void emprunter (String nomTypeDeMateriel, String nomMateriel)
    {
        deplacer (tableauStock[0], tableauStock[1], nomTypeDeMateriel, nomMateriel);
        if (tableauStock[1].tableauTypeDeMateriel[0].getNom().equals(nomTypeDeMateriel))
        {
            tableauStock[1].tableauTypeDeMateriel[0].augmenterNombreEmprunt();
        }
        if (tableauStock[1].tableauTypeDeMateriel[1].getNom().equals(nomTypeDeMateriel))
        {
            tableauStock[1].tableauTypeDeMateriel[1].augmenterNombreEmprunt();
        }
        if (tableauStock[1].tableauTypeDeMateriel[2].getNom().equals(nomTypeDeMateriel))
        {
            tableauStock[1].tableauTypeDeMateriel[2].augmenterNombreEmprunt();
        }
        if (tableauStock[1].tableauTypeDeMateriel[3].getNom().equals(nomTypeDeMateriel))
        {
            tableauStock[1].tableauTypeDeMateriel[3].augmenterNombreEmprunt();
        }
    }

    /**
     * La methode reparation permet de deplacer un materiel de stockRendu vers stockAtelier. Elle permet egalement d'incrementer de 1 (dans le stockAtelier) le nombre de fois que le type de materiel est tombe en panne.
     * @param nomTypeDeMateriel Le type de materiel auxquel appartient le materiel.
     * @param nomMateriel Le nom du materiel.
     * @return Un boolean indiquant si le materiel a ete envoye en reparation ou pas.
     */
    public boolean reparation (String nomTypeDeMateriel, String nomMateriel)
    {
    	boolean booleanfinal = false;
        if (deplacer (tableauStock[2], tableauStock[3], nomTypeDeMateriel, nomMateriel));
        {
            deplacer (tableauStock[2], tableauStock[3], nomTypeDeMateriel, nomMateriel);
            if (tableauStock[3].tableauTypeDeMateriel[0].getNom().equals(nomTypeDeMateriel))
            {
                tableauStock[3].tableauTypeDeMateriel[0].augmenterNombrePanne();
            }
            if (tableauStock[3].tableauTypeDeMateriel[1].getNom().equals(nomTypeDeMateriel))
            {
                tableauStock[3].tableauTypeDeMateriel[1].augmenterNombrePanne();
            }
            if (tableauStock[3].tableauTypeDeMateriel[2].getNom().equals(nomTypeDeMateriel))
            {
                tableauStock[3].tableauTypeDeMateriel[2].augmenterNombrePanne();
            }
            if (tableauStock[3].tableauTypeDeMateriel[3].getNom().equals(nomTypeDeMateriel))
            {
                tableauStock[3].tableauTypeDeMateriel[3].augmenterNombrePanne();
            }
            booleanfinal = true;
        }
        return booleanfinal;
    }

    /**
     * La methode remettre permet de deplacer un materiel de stockRendu vers stockEmpruntable.
     * @param nomTypeDeMateriel Le type de materiel auxquel appartient le materiel.
     * @param nomMateriel Le nom du materiel.
     */
    public void remettre (String nomTypeDeMateriel, String nomMateriel)
    {
        deplacerSansVerification(tableauStock[2], tableauStock[0], nomTypeDeMateriel, nomMateriel);
    }

    /**
     * La methode typeDeMaterielLePlusEmprunter permet de savoir quel est le TypeDeMateriel le plus emprunte.
     * @return Un String indiquant le TypeDeMateriel le plus emprunte.
     */
    public String typeDeMaterielLePlusEmprunter()
    {
        TypeDeMateriel typeDeMaterielLePlusEmprunter = tableauStock[1].tableauTypeDeMateriel[0];
        for (int i = 1; i < tableauStock[1].tableauTypeDeMateriel.length; i = i+1)
        {
            if (tableauStock[1].tableauTypeDeMateriel[i].getNombreEmprunt() >= typeDeMaterielLePlusEmprunter.getNombreEmprunt())
            {
                typeDeMaterielLePlusEmprunter = tableauStock[1].tableauTypeDeMateriel[i];
            }
        }
        return typeDeMaterielLePlusEmprunter.getNom();
    }

    /**
     * La methode typeDeMaterielLePlusEnPanne permet de savoir quel est le TypeDeMateriel le plus en panne.
     * @return Un String indiquant le TypeDeMateriel le plus en panne.
     */
    public String typeDeMaterielLePlusEnPanne()
    {
        TypeDeMateriel typeDeMaterielLePlusEnPanne = tableauStock[3].tableauTypeDeMateriel[0];
        for (int i = 1; i < tableauStock[3].tableauTypeDeMateriel.length; i = i+1)
        {
            if (tableauStock[3].tableauTypeDeMateriel[i].getNombreEmprunt() >= typeDeMaterielLePlusEnPanne.getNombreEmprunt())
            {
                typeDeMaterielLePlusEnPanne = tableauStock[3].tableauTypeDeMateriel[i];
            }
        }
        return typeDeMaterielLePlusEnPanne.getNom();
    }

    /**
     * La methode afficherStockEmpruntable permet d'afficher les 4 types de materiels (ainsi que les materiels que eux memes contiennent) de ce stock.
     * @return Un String affichant la liste des differents materiels selon leur type.
     */
    public String afficherStockEmpruntable()
    {
        return tableauStock[0].toString();
    }

    /**
     * La methode afficherStockEmpruntableTablette permet d'afficher les tablettes empruntables de ce stock.
     * @return Un String affichant la liste des tablettes empruntables.
     */
    public String afficherStockEmpruntableTablette()
    {
        return tableauStock[0].toString(0);
    }

    /**
     * La methode afficherStockEmpruntableTelephone permet d'afficher les telephones empruntables de ce stock.
     * @return Un String affichant la liste des telephones empruntables.
     */
    public String afficherStockEmpruntableTelephone()
    {
        return tableauStock[0].toString(1);
    }

    /**
     * La methode afficherStockEmpruntableCasque permet d'afficher les casques empruntables de ce stock.
     * @return Un String affichant la liste des casques empruntables.
     */
    public String afficherStockEmpruntableCasque()
    {
        return tableauStock[0].toString(2);
    }

    /**
     * La methode afficherStockEmpruntableCamera permet d'afficher les cameras empruntables de ce stock.
     * @return Un String affichant la liste des cameras empruntables.
     */
    public String afficherStockEmpruntableCamera()
    {
        return tableauStock[0].toString(0);
    }

    /**
     * La methode afficherStockEmprunter permet d'afficher les 4 types de materiels (ainsi que les materiels que eux memes contiennent) de ce stock.
     * @return Un String affichant la liste des differents materiels selon leur type.
     */
    public String afficherStockEmprunter()
    {
        return tableauStock[1].toString();
    }

    /**
     * La methode afficherStockRendu permet d'afficher les 4 types de materiels (ainsi que les materiels que eux memes contiennent) de ce stock.
     * @return Un String affichant la liste des differents materiels selon leur type.
     */
    public String afficherStockRendu()
    {
        return tableauStock[2].toString();
    }

    /**
     * La methode afficherStockRenduTablette permet d'afficher les tablettes rendues de ce stock.
     * @return Un String affichant la liste des tablettes rendues.
     */
    public String afficherStockRenduTablette()
    {
        return tableauStock[2].toString(0);
    }

    /**
     * La methode afficherStockRenduTelephone permet d'afficher les telephones rendus de ce stock.
     * @return Un String affichant la liste des telephones rendus.
     */
    public String afficherStockRenduTelephone()
    {
        return tableauStock[2].toString(1);
    }

    /**
     * La methode afficherStockRenduCasque permet d'afficher les casques rendus de ce stock.
     * @return Un String affichant la liste des casques rendus.
     */
    public String afficherStockRenduCasque()
    {
        return tableauStock[2].toString(2);
    }

    /**
     * La methode afficherStockRenduCamera permet d'afficher les cameras rendues de ce stock.
     * @return Un String affichant la liste des cameras rendues.
     */
    public String afficherStockRenduCamera()
    {
        return tableauStock[2].toString(3);
    }

    /**
     * La methode afficherStockAtelier permet d'afficher les 4 types de materiels (ainsi que les materiels que eux memes contiennent) de ce stock.
     * @return Un String affichant la liste des differents materiels selon leur type.
     */
    public String afficherStockAtelier()
    {
        return tableauStock[3].toString();
    }

    /**
     * Cette methode toString permet d'afficher tout ce que les 4 stocks contiennent comme materiels.
     * @return Un String affichant la liste des differents materiels selon leur type.
     */
    public String toString()
    {
        return "Stock Empruntable : " + "\n" +afficherStockEmpruntable() + "\n" + "\n" + "Stock Emprunter : " + "\n" + afficherStockEmprunter() + "\n" + "\n" + "Stock Rendu : " + "\n" + afficherStockRendu() + "\n" +"\n" + "Stock Atelier : " + "\n" +afficherStockAtelier() + "\n" ;
    }
}