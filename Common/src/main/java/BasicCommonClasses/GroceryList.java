package BasicCommonClasses;

import java.util.HashMap;

import CommonDefs.GroceryListExceptions.AmountIsBiggerThanAvailable;
import CommonDefs.GroceryListExceptions.ProductNotInList;

/**
 * GroceryList class - represent the container of the CartProducts that added to the cart.
 * 
 * @author Lior Ben Ami, Aviad Cohen
 * @since 2017-01-04
 */
public class GroceryList {
	HashMap<SmartCode, ProductPackage> groceryList;

	public void addProduct(ProductPackage newProductPackage) {
		/* initializing new list */
		if (groceryList == null)
			groceryList = new HashMap<SmartCode, ProductPackage>();
		
		
		ProductPackage productPackagInCart = groceryList.get(newProductPackage.getSmartCode());
		
		if (productPackagInCart == null)
			/* Add new ProductPackage to list */
			groceryList.put(newProductPackage.getSmartCode(), newProductPackage);
		else {
			/* Updating amount of existing ProductPackage */
			productPackagInCart.incrementAmount(newProductPackage.getAmount());
			groceryList.put(productPackagInCart.getSmartCode(), productPackagInCart);
		}
	}
	
	public void removeProduct(ProductPackage p) throws ProductNotInList, AmountIsBiggerThanAvailable {
		ProductPackage pp = groceryList.get(p.getSmartCode());
		
		if (pp == null)
			throw new ProductNotInList();
		
		int newAmount = pp.getAmount() - p.getAmount();
		
		if (newAmount < 0)
			throw new AmountIsBiggerThanAvailable();
		
		if (newAmount == 0)
			groceryList.remove(pp.getSmartCode());
		else {
			pp.setAmount(newAmount);
			groceryList.put(pp.getSmartCode(), pp);
		}
	}
	
	public HashMap<SmartCode, ProductPackage> getList() {
		return groceryList;
	}
}