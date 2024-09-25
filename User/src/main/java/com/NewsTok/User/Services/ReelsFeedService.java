package com.NewsTok.User.Services;

import com.NewsTok.User.Models.*;
import com.NewsTok.User.Repositories.UserInteractionWithReelsRepository;
import com.NewsTok.User.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReelsFeedService {

    private static final double W_LIKE = 0.7;
    private static final double W_WATCH_TIME = 0.3;
    private static final double reelsDuration=20;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInteractionWithReelsRepository userInteractionWithReelsRepository;

    @Autowired
    private AdminLoginService adminLoginService;

    @Value("${UserToAdminAuthentication.email}")
    private String email;

    @Value("${UserToAdminAuthentication.password}")
    private String password;

    private final RestTemplate restTemplate;



    public ReelsFeedService() {

        this.restTemplate = new RestTemplate();
    }


    public List<FinalRecommendationResponse> getReelsFeed(String email){



        AdminRequest adminRequest=new AdminRequest();
        adminRequest.setEmail(this.email);
        adminRequest.setPassword(this.password);

        AdminResponse adminResponse =adminLoginService.loginUser(adminRequest);


        String url = "http://localhost:8080/admin/getReelsRecommendation";


        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + adminResponse.getToken());
       UserInteractionRequest userInteractionTem=this.getUserInteraction(email);

        HttpEntity<UserInteractionRequest> requestEntity = new HttpEntity<>(userInteractionTem, headers);

        System.out.println("interest : "+ userInteractionTem.getInterest());
        // Send the POST request to the specified endpoint
        ResponseEntity<List<FinalRecommendationResponse>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<List<FinalRecommendationResponse>>() {}
        );


        // Return the response body
        return responseEntity.getBody();


    }

    public UserInteractionRequest getUserInteraction(String email) {

        UserInteractionRequest userRequest=new UserInteractionRequest();
        User user=userRepository.findByEmail(email);
        System.out.println(user.getUserId());

        userRequest.setInterest(user.getInterests());
        userRequest.setUser_id( this.makeInt(user.getUserId()) );

        List<UserInteractionWithReels> interactionList=userInteractionWithReelsRepository.findAll();
        List<Interaction> finalinteractionsList=new ArrayList<>();

        for(UserInteractionWithReels eachOne : interactionList){

            double scoreCount=this.calculateScore(eachOne.getIsLiked(),Double.parseDouble(eachOne.getTime()),reelsDuration);

            Interaction interaction=new Interaction();
            interaction.setUser_id(this.makeInt( eachOne.getUserId()));
            interaction.setReels_id(this.makeInt(eachOne.getReelsId()));
            interaction.setScore(scoreCount);

            finalinteractionsList.add(interaction);
        }

        userRequest.setInteractions(finalinteractionsList);

        return  userRequest;

    }

    public  int makeInt(Long id){
        return  Integer.parseInt(Long.toString(id));

    }

    /**
     * Calculates the score based on user interaction with the reel.
     *
     * @param like           boolean indicating if the user liked the reel (true = liked, false = not liked)
     * @param watchTime      the time the user spent watching the reel (in seconds)
     * @param totalDuration  the total duration of the reel (in seconds)
     * @return               the calculated score as a double
     */

    public static double calculateScore(boolean like, double watchTime, double totalDuration) {
        // Normalize watch time to a scale between 0 and 1

        double normalizedWatchTime = watchTime / totalDuration;


        double likeScore = like ? 1.0 : 0.0;

        // Calculate the final score using the weighted formula
        double finalScore = (W_LIKE * likeScore) + (W_WATCH_TIME * normalizedWatchTime);

        return finalScore;
    }
}
