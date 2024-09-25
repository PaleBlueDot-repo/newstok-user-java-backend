package com.NewsTok.User.Controllers;
import com.NewsTok.User.Models.FinalRecommendationResponse;
import com.NewsTok.User.Models.Reels;
import com.NewsTok.User.Services.ReelsFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class ReelsFeedController {

    @Autowired
    private ReelsFeedService reelsFeedService;


    @GetMapping("/getReelsFeed")
    public ResponseEntity<List<FinalRecommendationResponse>> getReelsFeed(@RequestParam("email") String email) {
        System.out.println(email);
        return ResponseEntity.ok(reelsFeedService.getReelsFeed(email));
    }

}
