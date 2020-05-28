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
public class Feedback {
     private int id;
    private int event_id;
    private int rating;
  public Feedback() {}    

    public Feedback(int event_id, int rating) {
       this.event_id=event_id;
       this.rating=rating;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
     
   public Feedback(int id, int event_id, int rating) {
     this.id=id;
     this.event_id=event_id;
     this.rating=rating;
    }


    @Override
    public String toString() {
        return "Feedback{" + "id=" + id + ", event_id=" + event_id + ", rating=" + rating + '}';
    }
}
