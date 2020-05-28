/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;




/**
 *
 * @author Memmicha
 */
public class transaction {
    private int id;
    private String Name;
    private String Last_Name;
    private String RIB;
    public Date Date_Expiration; 
    private float Montant;
    private String address;
    private int id_user;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
   

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String Last_Name) {
        this.Last_Name = Last_Name;
    }

    public String getRIB() {
        return RIB;
    }

    public void setRIB(String RIB) {
        this.RIB = RIB;
    }

    public Date getDate_Expiration() {
        return Date_Expiration;
    }

    public void setDate_Expiration(Date Date_Expiration) {
        this.Date_Expiration = Date_Expiration;
    }

    public float getMontant() {
        return Montant;
    }

    public void setMontant(float Montant) {
        this.Montant = Montant;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    
   public transaction(int id, String Name,String Last_Name,String RIB,Date Date_Expiration, float Montant,String address) {
    this.id=id;
    this.Name=Name;
    this.Last_Name=Last_Name;
    this.RIB=RIB;
    this.Date_Expiration = Date_Expiration;
    this.Montant=Montant;
    this.address=address;
    
    
    } 

    public transaction(String Name, String Last_Name, String RIB, Date Date_Expiration, float Montant, String address) {
        this.Name = Name;
        this.Last_Name = Last_Name;
        this.RIB = RIB;
        this.Date_Expiration = Date_Expiration;
        this.Montant = Montant;
        this.address = address;
    }

    public transaction(int id, String Name, String Last_Name, String RIB, Date Date_Expiration, float Montant, String address, int id_found, user usert) {
        this.id = id;
        this.Name = Name;
        this.Last_Name = Last_Name;
        this.RIB = RIB;
        this.Date_Expiration = Date_Expiration;
        this.Montant = Montant;
        this.address = address;
      
    }

    public transaction(String Name, String Last_Name, String RIB, float Montant, String address) {
        this.Name = Name;
        this.Last_Name = Last_Name;
        this.RIB = RIB;
         
        this.Montant = Montant;
        this.address = address;
    
    }

 

   
   
   public transaction() { }

    @Override
    public String toString() {
        return "transaction{" + "id=" + id + ", Name=" + Name + ", Last_Name=" + Last_Name + ", RIB=" + RIB + ", Date_Expiration=" + Date_Expiration + ", Montant=" + Montant + ", address=" + address + '}';
    }

   


   

    
}
