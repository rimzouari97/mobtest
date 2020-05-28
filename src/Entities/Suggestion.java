/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author mon
 */
public class Suggestion {
   private int id ;
   private String mail;
   private String fondation;
   private String  description;

    
     public Suggestion(){}
     public Suggestion(String mail,String fondation,String description){
         this.mail=mail;
         this.fondation=fondation;
         this.description=description;
     }

    public Suggestion(int id,String mail,String fondation,String description){
          this.id=id;
         this.mail=mail;
         this.fondation=fondation;
         this.description=description;
     }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFondation() {
        return fondation;
    }

    public void setFondation(String fondation) {
        this.fondation = fondation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
        @Override
    public String toString() {
        return "Suggestion{" + "id=" + id + ", mail=" + mail + ", fondation=" + fondation + ", description=" + description + '}';
    }

}
