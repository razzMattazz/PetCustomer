/*
 * Business Object for employee
 *
 *
 */
package BusinessObjects;

import java.util.ArrayList;

public class Employee {
    private String eId;
    private String eFname;
    private String eLname;
    private String ePass;
    private String eAdd;
    private String eEmail;
    
    public Employee(){}
    
    public Employee(String empid, String fname, String lname, String pass, String address, String email){
        eId = empid;
        eFname = fname;
        eLname = lname;
        ePass = pass;
        eAdd = address;
        eEmail = email;
    }
    
    
    //Setters
    public void setEid(String empid){eId = empid;}
    public void setEfname(String fname){eFname = fname;}
    public void setElname(String lname){eLname = lname;}
    public void setEpass(String pass){ePass = pass;}
    public void setEadd(String address){eAdd = address;}
    public void setEemail(String email){eEmail = email;}
    
    //Getters
    public String getEid(){return eId;}
    public String getEfname(){return eFname;}
    public String getElname(){return eLname;}
    public String getEpass(){return ePass;}
    public String getEadd(){return eAdd;}
    public String getEemail(){return eEmail;}
    
		// Select Statement for Employee
    public void Select(String eId) {
        
            try{   
            
            AccessDB e1 = new AccessDB();
            String sql = "SELECT * FROM Employee WHERE EmpID = " + eId;
            System.out.println(sql);
            ArrayList<String> reception = e1.select(sql);

            setEid(reception.get(0));
            setEfname(reception.get(1));
            setElname(reception.get(2));
            setEpass(reception.get(3));
            setEadd(reception.get(4));
            setEemail(reception.get(5));
            }
        
            catch(Exception ex) {System.out.println(ex); }   

    }
    
		/* Unused methods 
    public void Update(){
        String sql = "UPDATE Employee SET EmpID='"+eId+"', FName='"+eFname+"', LName='"+eLname+"', Pass='"+ePass+"', Address='"+eAdd+"', Email='"+eEmail+"' where EmpID='"+eId+"';";
        AccessDB e1 = new AccessDB();
        e1.update(sql);
    }
    
    public void Insert(){
        String sql = "INSERT into Employee VALUES('"+eId+"', '"+eFname+"', '"+eLname+"', '"+ePass+"', '"+eAdd+"', '"+eEmail+"');";
        AccessDB e2 = new AccessDB();
        e2.insert(sql);
    }
    
    public void Delete(String id){
        String sql = "DELETE from employee WHERE EmpID = '"+id+"';";
        AccessDB e3 = new AccessDB();
        e3.delete(sql);
    }
		*/
		
    //DISPLAY
    public void Display() {
        System.out.println("=====BEGINNING DEBUG FROM EMPLOYEE.JAVA=====");
        System.out.println(eId);
        System.out.println(eFname);
        System.out.println(eLname);
        System.out.println(ePass);
        System.out.println(eAdd);
        System.out.println(eEmail);
        System.out.println("=====END DEBUG FROM EMPLOYEE.JAVA=====");
    }
}
