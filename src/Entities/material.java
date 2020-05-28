/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;



/**
 *
 * @author Memmicha
 */
public class material {
    private int id;
    private int id_address;
    private String Name_address;
    private String Address;
    private String Description;
    String Region;
    String Longitude;
    String Latitude;

    public int getId_address() {
        return id_address;
    }

    public void setId_address(int id_address) {
        this.id_address = id_address;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_address() {
        return Name_address;
    }

    public void setName_address(String Name_address) {
        this.Name_address = Name_address;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    
    public String getRegion() {
        return Region;
    }

    public void setRegion(String Region) {
        this.Region = Region;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String Longitude) {
        this.Longitude = Longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String Latitude) {
        this.Latitude = Latitude;
    }
    
    public material(int id, String Region, String Name_address, String Longitude, String Latitude) {
        this.id = id;
        this.Region = Region;
        this.Name_address=Name_address;
        this.Longitude = Longitude;
        this.Latitude = Latitude;
    }

    public material(String Region, String Name_address, String Longitude, String Latitude) {
        this.Region = Region;
        this.Name_address = Name_address;
        this.Longitude = Longitude;
        this.Latitude = Latitude;
    }

    @Override
    public String toString() {
        return "material{" + "id=" + id + ", Name_address=" + Name_address + ", Address=" + Address + ", Description=" + Description + '}';
    }
    
    public material(){}

    public material(String Name_address, String Address, String Description) {
        this.Name_address = Name_address;
        this.Address = Address;
        this.Description = Description;
    }
    
    
    
public material (int id_address, String Name_address, String Address, String Description){
this.id_address=id_address;
this.Name_address=Name_address;
this.Address=Address;
this.Description=Description;

}

    public material( String Name_address, String Address, String Description, String Region, String Longitude, String Latitude) {
        
        this.Name_address = Name_address;
        this.Address = Address;
        this.Description = Description;
        this.Region = Region;
        this.Longitude = Longitude;
        this.Latitude = Latitude;
    }

   

   

}
