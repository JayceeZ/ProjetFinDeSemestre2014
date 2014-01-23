package stocks;

/**
 * Une classe permettant de creer un stock Atelier contenant les materiels en reparations. Cette classe herite de Stock.
 * @author RAHBARI Guillaume - TIJANI Yassine
 * @version version 2.0
 */

public class StockAtelier extends Stock
{
	/**
     * Cree un stock atelier contenant les 4 types de materiels.
     */
	public StockAtelier()
	{
		super("stock de materiels en reparations");	
	}
}
