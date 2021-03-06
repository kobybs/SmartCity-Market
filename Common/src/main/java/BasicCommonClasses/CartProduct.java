package BasicCommonClasses;

import java.util.HashMap;


/** CartProduct - The product from the Cart (/costumer) perspective. 
 * @author Lior Ben Ami
 * @since 2016-12-09 */
public class CartProduct {
	CatalogProduct catalogProduct;
	HashMap<SmartCode,ProductPackage> packages;
	int totalAmount;
	
	public CartProduct(CatalogProduct catalogProduct, HashMap<SmartCode,ProductPackage> packages,
			int totalAmount) {
		this.catalogProduct = catalogProduct;
		this.packages = packages != null ? packages : new HashMap<SmartCode,ProductPackage>();
		this.totalAmount = totalAmount;
	}

	public CatalogProduct getCatalogProduct() {
		return catalogProduct;
	}

	public void setCatalogProduct(CatalogProduct ¢) {
		this.catalogProduct = ¢;
	}

	public HashMap<SmartCode,ProductPackage> getPackages() {
		return packages;
	}

	public void setPackages(HashMap<SmartCode,ProductPackage> ¢) {
		this.packages = ¢;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	/**
	 * @param p
	 */
	public void addProductPackage(ProductPackage p) {
		SmartCode sc = p.getSmartCode();
		ProductPackage inCartProductPackage = packages.get(sc);
		if ( inCartProductPackage != null)
			inCartProductPackage.incrementAmount(p.getAmount());
		packages.put(sc, p);
		totalAmount += p.getAmount();
	}
	
	public Boolean removeProductPackage(ProductPackage p) {
		SmartCode sc = p.getSmartCode();
		ProductPackage inCartProductPackage = packages.get(sc);
		if (inCartProductPackage == null || inCartProductPackage.getAmount() < p.getAmount())
			return false;
		int prevAmount = inCartProductPackage.getAmount();
		inCartProductPackage.setAmount(prevAmount - p.getAmount());
		totalAmount -= p.getAmount();
		return true;
	}
	
	public double getTotalSum() {
		return totalAmount * catalogProduct.getPrice();
	}
	
	@Override public String toString(){
		return catalogProduct.name + "		" + catalogProduct.price;
	}
}