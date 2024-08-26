package com.NewsTok.User.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
}
