package com.NewsTok.User.Controllers;

import com.NewsTok.User.Dtos.LikeReelRequest;
import com.NewsTok.User.Dtos.LikeReelResponse;
import com.NewsTok.User.Dtos.ReelTimeRequest;
import com.NewsTok.User.Dtos.ReelTimeResponse;
import com.NewsTok.User.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.NewsTok.User.Repositories.UserInteractionWithReelsRepository;
import com.NewsTok.User.Models.UserInteractionWithReels;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserInteractionWithReelsController {

    @Autowired
    private UserInteractionWithReelsRepository interactionRepository;






    @PostMapping("/add")
    public ResponseEntity<Object> addInteraction(@RequestBody UserInteractionWithReels interaction) {
        try {
            // Check if the interaction already exists
            UserInteractionWithReels existingInteraction = interactionRepository.findByReelsIdAndUserId(interaction.getReelsId(), interaction.getUserId());
    
            if (existingInteraction != null) {
                // Update the existing interaction
                existingInteraction.setTime(interaction.getTime());
                existingInteraction.setIsLiked(interaction.getIsLiked());
                // Save the updated interaction
                interactionRepository.save(existingInteraction);
                return ResponseEntity.ok("Interaction updated successfully");
            } else {
                // Save the new interaction
                interactionRepository.save(interaction);
                return ResponseEntity.ok("Interaction added successfully");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(500).body("An error occurred while processing the interaction");
        }
    }
    

    // Fetch interactions by userId and reelsId
    @GetMapping("/fetchInteractions")
    public ResponseEntity<Object> getInteraction(
            @RequestParam("reelsId") Long reelsId,
            @RequestParam("userId") Long userId) {
        try {
            UserInteractionWithReels interaction = interactionRepository.findByReelsIdAndUserId(reelsId, userId);
            if (interaction == null) {
                return ResponseEntity.status(404).body("Interaction not found");
            }
            return ResponseEntity.ok(interaction);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(500).body("An error occurred while fetching the interaction");
        }
    }

    // Fetch all interactions
    @GetMapping("/allInteractions")
    public ResponseEntity<List<UserInteractionWithReels>> getAllInteractions() {
        return ResponseEntity.ok(interactionRepository.findAll());
    }


    @PostMapping("/saveReelTime")
    public ResponseEntity<ReelTimeResponse> saveReelTime(@RequestBody ReelTimeRequest reelTimeRequest) {
        ReelTimeResponse reelTimeResponse=new ReelTimeResponse();
        reelTimeResponse.setReelsId(reelTimeRequest.getReelsId());
        reelTimeResponse.setUserId(reelTimeRequest.getUserId());

        try {

            UserInteractionWithReels userInteractionWithReels = interactionRepository.findByReelsIdAndUserId(reelTimeRequest.getReelsId(),reelTimeRequest.getUserId());


            if(userInteractionWithReels==null){

                UserInteractionWithReels userInteractionWithReelsnew=new UserInteractionWithReels();

                userInteractionWithReelsnew.setTime(Integer.toString(reelTimeRequest.getTimeSpent()));
                userInteractionWithReelsnew.setIsLiked(false);
                userInteractionWithReelsnew.setReelsId(reelTimeRequest.getReelsId());
                userInteractionWithReelsnew.setUserId(reelTimeRequest.getUserId());

                reelTimeResponse.setTimeSpent(reelTimeRequest.getTimeSpent());

                interactionRepository.save(userInteractionWithReelsnew);
            }
            else {
                userInteractionWithReels.setTime(Integer.toString(reelTimeRequest.getTimeSpent()));
                reelTimeResponse.setTimeSpent(reelTimeRequest.getTimeSpent());
                interactionRepository.save(userInteractionWithReels);
            }


        }catch (Exception e) {

//          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving time");

            System.out.println("error"+e.getMessage());

        }

        return ResponseEntity.ok(reelTimeResponse);

    }

    @PostMapping("/likeReel")
    public ResponseEntity<LikeReelResponse> likeReel(@RequestBody LikeReelRequest likeReelRequest) {

           LikeReelResponse likeReelResponse=new LikeReelResponse();
           likeReelResponse.setReelsId(likeReelRequest.getReelsId());
           likeReelResponse.setUserId(likeReelRequest.getUserId());

        try {

            UserInteractionWithReels userInteractionWithReels = interactionRepository.findByReelsIdAndUserId(likeReelRequest.getReelsId(),likeReelRequest.getUserId());

            if(userInteractionWithReels==null){
                UserInteractionWithReels userInteractionWithReelsnew=new UserInteractionWithReels();
                userInteractionWithReelsnew.setTime(Integer.toString(5));
                userInteractionWithReelsnew.setIsLiked(true);
                userInteractionWithReelsnew.setReelsId(likeReelRequest.getReelsId());
                userInteractionWithReelsnew.setUserId(likeReelRequest.getUserId());

                likeReelResponse.setLikeCount(0);
                likeReelResponse.setLiked(true);
                interactionRepository.save(userInteractionWithReelsnew);


            }
            else {

                List<UserInteractionWithReels> allInteractions = interactionRepository.findAll();
                int likecnt = totalLikes(allInteractions, likeReelRequest.getReelsId());
                likeReelResponse.setLikeCount(likecnt);


                if (userInteractionWithReels.getIsLiked()) {
                    userInteractionWithReels.setIsLiked(false);
                    likeReelResponse.setLiked(false);

                } else {
                    userInteractionWithReels.setIsLiked(true);
                    likeReelResponse.setLiked(true);
                }

                interactionRepository.save(userInteractionWithReels);

            }


        }catch (Exception e) {

//          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving time");

            System.out.println("error"+e.getMessage());

        }

        return ResponseEntity.ok(likeReelResponse);

    }

    public int totalLikes( List<UserInteractionWithReels> interactionList,Long id){

        int likeCount=0;
        for(UserInteractionWithReels eachInteraction : interactionList){
            if(eachInteraction.getReelsId()==id && eachInteraction.getIsLiked()){
                likeCount+=1;
            }

        }

        return  likeCount;
    }



}
