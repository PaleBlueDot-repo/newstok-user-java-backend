package com.NewsTok.User.Models;

import java.util.List;

public class UserInteractionRequest {

    private int user_id;
    private List<Interaction> interactions;

    private  String interest;

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }
    // Getters and Setters



    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public List<Interaction> getInteractions() {
        return interactions;
    }

    public void setInteractions(List<Interaction> interactions) {
        this.interactions = interactions;
    }

//    public void addInteractions(int user_id,int reels_id,double score){
//       Interaction interaction = new Interaction();
//       interaction.setReels_id(reels_id);
//       interaction.setScore(score);
//       interaction.setUser_id(user_id);
//       this.interactions.add(interaction);
//    }


}
