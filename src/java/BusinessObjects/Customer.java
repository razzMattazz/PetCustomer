package BusinessObjects;

import java.util.ArrayList;

/*******************************
	Customer Business Object
	Author: Matthew Vaughn

********************************/


/*MAYYYBE NEEDS TO BE ABLE TO HANDLE GUEST ACCOUNT. THIS bOBJECT WILL BE WHERE
A HOLDER ACCOUNT WILL BE CREATED FOR A GUEST UNTIL THEY FINISH THEIR BUSINESS, 
guest account destruction will be here as well i think */

public class Customer {	
	// ATTRIBUTES
	private String cid;
	private String fName;
	private String lName;
	private String pass;
	private String address;
	private String eMail;
	private String acctC;
	
	/**********
         * Default Constructor initializes all attributes using setters.
         **********/
	public Customer() {
		setID("");
		setfName("");
		setlName("");
		setPass("");
		setAddress("");
		setEMail("");
		setAcctC("");
	}
        /**********
         * Constructor initializes all attributes using provided strings.
         * @param id    Unique identifier for the customer
         * @param fn    First name
         * @param ln    Last name
         * @param pw    Password
         * @param add   Address
         * @param em    Email
         * @param ac    Account boolean placeholder
         **********/
	public Customer (String id, String fn, String ln, 
	String pw, String add, String em, String ac) {
		setID(id);
		setfName(fn);
		setlName(ln);
		setPass(pw);
		setAddress(add);
		setEMail(em);
		setAcctC(ac);
	}
	
	
	/***
         * Setter method for ID attribute
         * @param newID 
         */
	public void setID(String newID) {
		cid = newID;
	}
        /***
         * Setter method for First Name attribute
         * @param newfName 
         */
	public void setfName(String newfName) {
		fName = newfName;
	}
        /***
         * Setter method for Last Name attribute
         * @param newlName 
         */
	public void setlName(String newlName) {
		lName = newlName;
	}
        /***
         * Setter method for Password attribute
         * @param newPass 
         */
	public void setPass(String newPass) {
		pass = newPass;
	}
        /***
         * Setter method for Address attribute
         * @param newAddress 
         */
	public void setAddress(String newAddress) {
		address = newAddress;
	}
        /***
         * Setter method for Email attribute
         * @param newEMail 
         */
	public void setEMail(String newEMail) {
		eMail = newEMail;
	}
        /***
         * Setter method for Account attribute
         * @param newAcctC 
         */
	public void setAcctC(String newAcctC) {
		acctC = newAcctC;
	}
	
	// GETTERS
	public String getID() {
		return cid;
	}
	public String getFN() {
		return fName;
	}
	public String getLN() {
		return lName;
	}
	public String getPass() {
		return pass;
	}
	public String getAddress() {
		return address;
	}
	public String getEMail() {
		return eMail;
	}
	public String getAcctC() {
		return acctC;
	}
	
	/**********
         * Uses AccessDB class to call on the database and fill attributes
         * with data where the Customer ID in the database corresponds to the
         * sID parameter.
         * @param sID Customer ID
         **********/
	public void selectC(String sID) {
            AccessDB a2 = new AccessDB();
            String sql = "SELECT * FROM Customers where CustID = " + sID;
            ArrayList<String> reception = a2.select(sql);
            
            try{
                setID(reception.get(0));
                setfName(reception.get(1));
                setlName(reception.get(2));
                setPass(reception.get(3));
                setAddress(reception.get(4));
                setEMail(reception.get(5));
                setAcctC(reception.get(6));
            }catch(NullPointerException npe){
                System.out.println(npe);
            }
            catch(IndexOutOfBoundsException oob){
                System.out.println(oob);
            }
        }
        
        /**********
         * Updates database where the instance's ID matches a record in the
         * database.
         * @param sID       ID passed to grab a record from the database
         * @param newVal    value used to update database field
         * @param column    name of column being updated
         **********/
        public void updateC(String sID, String newVal, String column) {
           String sql = "UPDATE Customers SET " + column + " = '" + newVal + 
                   "' WHERE CustID = " + sID;
           AccessDB a2 = new AccessDB();
           a2.update(sql);
        }
        
        /**********
         * Inserts a record into the Customer table of the database using the
         * provided attributes.
         * @param id
         * @param fName
         * @param lName
         * @param pass
         * @param add
         * @param eM
         * @param aC 
         **********/
        public void insertC(String id, String fName, String lName, String pass, String add, String eM, String aC) {
            String sql = "INSERT into Customers VALUES("+id+", '"+fName+"', '"+lName+"', '"
                +pass+"', '"+add+"', '"+eM+"', '"+aC+"')";
            AccessDB a3 = new AccessDB();
            a3.insert(sql);
        }
        
        
        /**********
         * Deletes a record where the provided id matches a Customer ID in
         * the database.
         * @param id 
         **********/
        public void deleteC(String id) {
            String sql = "DELETE from Customers WHERE CustID = "+id;
            AccessDB a4 = new AccessDB();
            a4.delete(sql);
        }
	
        /**********
         * Displays attributes to the console.
         **********/
	public void display() {
		System.out.println("CID = " + cid);
		System.out.println(fName);
		System.out.println(lName);
		System.out.println(pass);
		System.out.println(address);
		System.out.println(eMail);
		System.out.println(acctC);
	}
        
        public static void main(String[] args){
        
            Customer c1 = new Customer();
        }
}
