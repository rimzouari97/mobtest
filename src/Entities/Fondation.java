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
public class Fondation {
   private int id;
   private String nom_fondation;
   private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_fondation() {
        return nom_fondation;
    }

    public void setNom_fondation(String nom_fondation) {
        this.nom_fondation = nom_fondation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
