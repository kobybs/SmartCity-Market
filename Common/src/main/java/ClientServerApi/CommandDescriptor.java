package ClientServerApi;

/**
 * ****************************************************************************************************
 *
 *  When adding a new command, make sure you add it's name & description here as well.
 *
 * ****************************************************************************************************
 * KEEP THE CONVENTIONS!
 * ****************************************************************************************************
 * ***** General Notes *****
 * 
 * 1. retval is relevant only on success (result_code = SM_OK).
 * 2. retval will be stored in the data field of the CommandWrapper (unless maintained otherwise).
 * 3. The Server/Client will interpret the data field according to the structure which is mentioned in
 *	  the data field, the data will be stored in json.
 * 4. When adding new commands, added reference in Github for all project contributes.
 * 5. *** IMPORTANT ***
 * 
 *    For each command there is also option to get one of the following ResultDescriptors:
 *    a. SM_ERR - a ResultDescriptor for internal failure in the 
 * 	  server, such case must be considered always by the client side.
 * 	  b. SM_INVALID_PARAMETER - one of the given parameter has invalid format.
 * 
 * ****************************************************************************************************
 * 
 * 
 * @author idan atias
 * @author shimon azulay
 * @author Aviad Cohen
 * @author Lior Ben Ami
 */
public enum CommandDescriptor {
	

	/******************************************** Connection **********************************************/

	/**
	 * Description: Client login command to get in the system and receive unique sender id from server.
	 * param1: Login.
	 * retval: CLIENT_TYPE.
	 *
	 * result_codes:
	 * 		success:
	 * 			SM_OK,
	 * 		
	 * 		failure:
	 *			SM_SENDER_IS_ALREADY_CONNECTED,
	 *			SM_USERNAME_DOES_NOT_EXIST_WRONG_PASSWORD,
	 *
	 *	 ***** NOTES *****
	 * 	1. The sender ID returns in senderId field.
	 *  2. Cart use username & password (use "Cart" on both).
	 */
	LOGIN,

	/**
	 * Description: Client logout command for logging out of server.
	 * param1: String username.
	 * retval: void
	 *
	 * result_codes:
	 * 		success:
	 * 			SM_OK,
	 * 		
	 * 		failure:
	 *			SM_SENDER_IS_NOT_CONNECTED
	 *
	 *	 ***** NOTES *****
	 * 	 1. This command has no data from the server side.
	 * 	 2. On Cart, the senderId will be the identification for logout and will ABORT the current GroceryList.
	 */
	LOGOUT,
	
	/**
	 * Description: Return true iff the senderID is connected.
	 * param1: void.
	 * retval: bool.
	 *
	 * result_codes:
	 * 		success:
	 * 			SM_OK,
	 * 		
	 * 		failure:
	 *
	 *	 ***** NOTES *****
	 */
	IS_LOGGED_IN,
	
	/********************************** Shared employee & cart commands **********************************/
	
	/**
	 * Description: Client command for getting the relevant catalog product represented by a barcode.
	 * param1: SmartCode - Smartcode with barcode and null on expertionDate.
	 * retval: CatalogProduct.
	 *
	 * result_codes:
	 * 		success:
	 * 			SM_OK,
	 * 		
	 * 		failure:
	 *			SM_SENDER_IS_NOT_CONNECTED,
	 *			SM_CATALOG_PRODUCT_DOES_NOT_EXIST
	 *
	 */
	VIEW_PRODUCT_FROM_CATALOG,
	
	/********************************** Employee commands **********************************/
	
	/**
	 * Description: Employee add new ProductPackage to warehouse.
	 * param1: ProductPackage - The ProductPackage which will be add to warehouse.
	 * retval: void.
	 *
	 * result_codes:
	 * 		success:
	 * 			SM_OK,
	 * 		
	 * 		failure:
	 *			SM_SENDER_IS_NOT_CONNECTED,
	 *			SM_CATALOG_PRODUCT_DOES_NOT_EXIST,
	 *
	 */
	ADD_PRODUCT_PACKAGE_TO_WAREHOUSE,
	
	/**
	 * Description: Manager add new CatalogProduct to the market catalog.
	 * param1: CatalogProduct - The CatalogProduct which will be add to the market catalog.
	 * retval: void.
	 *
	 * result_codes:
	 * 		success:
	 * 			SM_OK,
	 * 		
	 * 		failure:
	 *			SM_SENDER_IS_NOT_CONNECTED,
	 *			SM_CATALOG_PRODUCT_ALREADY_EXISTS,
	 *
	 */
	ADD_PRODUCT_TO_CATALOG,
	
	/**
	 * Description: Manager remove CatalogProduct from the market catalog.
	 * param1: SmartCode - Smartcode with barcode and null on expertionDate.
	 * retval: void.
	 *
	 * result_codes:
	 * 		success:
	 * 			SM_OK,
	 * 		
	 * 		failure:
	 *			SM_SENDER_IS_NOT_CONNECTED,
	 *			SM_CATALOG_PRODUCT_DOES_NOT_EXIST,
	 *			SM_CATALOG_PRODUCT_STILL_FOR_SALE,
	 *
	 */
	REMOVE_PRODUCT_FROM_CATALOG,

	/**
	 * Description: Employee view ProductPackage from warehouse or shelves.
	 * param1: ProductPackage - The ProductPackage which will be add to warehouse.
	 * retval: Amount.
	 *
	 * result_codes:
	 * 		success:
	 * 			SM_OK,
	 * 		
	 * 		failure:
	 *			SM_SENDER_IS_NOT_CONNECTED,
	 *			SM_PRODUCT_PACKAGE_DOES_NOT_EXIST,
	 *
	 */
	GET_PRODUCT_PACKAGE_AMOUNT,
	
	/**
	 * Description: Manager edit product from the catalog.
	 * param1: CatalogProduct - the new CatalogProduct content to be updated. 
	 * retval: void.
	 *
	 * result_codes:
	 * 		success:
	 * 			SM_OK,
	 * 		
	 * 		failure:
	 *			SM_SENDER_IS_NOT_CONNECTED,
	 *			SM_CATALOG_PRODUCT_DOES_NOT_EXIST,
	 *
	 */
	EDIT_PRODUCT_FROM_CATALOG,

	/**
	 * Description: Employee move product package from warehouse to shelves.
	 * param1: ProductPackage - ProductPackage with SmartCode of the ProductPackage from the warehouse,
	 *                          amount to move and the new location of the ProductPackage.
	 * retval: void.
	 *
	 * result_codes:
	 * 		success:
	 * 			SM_OK,
	 * 		
	 * 		failure:
	 *			SM_SENDER_IS_NOT_CONNECTED,
	 *			SM_PRODUCT_PACKAGE_DOES_NOT_EXIST,
	 *			SM_PRODUCT_PACKAGE_AMOUNT_BIGGER_THEN_AVAILABLE,
	 *
	 */
	PLACE_PRODUCT_PACKAGE_ON_SHELVES,

	/**
	 * Description: Employee remove product package from warehouse or shelves.
	 * param1: ProductPackage - ProductPackage with SmartCode of the ProductPackage,
	 *                          amount to remove and the location of the ProductPackage.
	 * retval: void.
	 *
	 * result_codes:
	 * 		success:
	 * 			SM_OK,
	 * 		
	 * 		failure:
	 *			SM_SENDER_IS_NOT_CONNECTED,
	 *			SM_PRODUCT_PACKAGE_DOES_NOT_EXIST,
	 *			SM_PRODUCT_PACKAGE_AMOUNT_BIGGER_THEN_AVAILABLE,
	 *
	 */
	REMOVE_PRODUCT_PACKAGE_FROM_STORE,
	
	/********************************** Cart commands **********************************/
	
	/**
	 * Description: Cart load it's own grocery list from the server.
	 * retval: GroceryList.
	 *
	 * result_codes:
	 * 		success:
	 * 			SM_OK
	 * 		
	 * 		failure:
	 *			SM_SENDER_IS_NOT_CONNECTED
	 *
	 */
	LOAD_GROCERY_LIST,
	
	/**
	 * Description: Cart add product to grocery list.
	 * param1: ProductPackage - ProductPackage with SmartCode, amount and null on location.
	 * retval: void.
	 *
	 * result_codes:
	 * 		success:
	 * 			SM_OK,
	 * 		
	 * 		failure:
	 *			SM_SENDER_IS_NOT_CONNECTED,
	 *			SM_PRODUCT_PACKAGE_DOES_NOT_EXIST,
	 *			SM_PRODUCT_PACKAGE_NOT_ENOUGH_AMOUNT,
	 *			SM_INVALID_PARAMETER,
	 *
	 */
	ADD_PRODUCT_TO_GROCERY_LIST,

	/**
	 * Description: Cart remove product from grocery list.
	 * param1: ProductPackage - ProductPackage with SmartCode, amount and null on location.
	 * retval: void.
	 *
	 * result_codes:
	 * 		success:
	 * 			SM_OK,
	 * 		
	 * 		failure:
	 *			SM_SENDER_IS_NOT_CONNECTED,
	 *			SM_PRODUCT_PACKAGE_DOES_NOT_EXIST,
	 *			SM_PRODUCT_PACKAGE_AMOUNT_BIGGER_THEN_AVAILABLE,
	 *			SM_INVALID_PARAMETER,
	 *
	 */
	REMOVE_PRODUCT_FROM_GROCERY_LIST,

	/**
	 * Description: Cart checkout it's current active grocery list.
	 * param1: void.
	 * retval: void.
	 *
	 * result_codes:
	 * 		success:
	 * 			SM_OK,
	 * 		
	 * 		failure:
	 *			SM_SENDER_IS_NOT_CONNECTED,
	 *			SM_GROCERY_LIST_IS_EMPTY,
	 *
	 */
	CHECKOUT_GROCERY_LIST,

}