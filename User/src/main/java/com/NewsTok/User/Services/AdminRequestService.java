package com.NewsTok.User.Services;

import com.NewsTok.User.Dtos.newsreelsDto;
import com.NewsTok.User.Models.AdminRequest;
import com.NewsTok.User.Models.AdminResponse;
import com.NewsTok.User.Models.DashBoard;
import com.NewsTok.User.Models.UserInteractionWithReels;
import com.NewsTok.User.Repositories.UserInteractionWithReelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class AdminRequestService {
    @Autowired
    private UserInteractionWithReelsRepository userInteractionWithReelsRepository;

    public DashBoard getAdminDashboard(){

        List<UserInteractionWithReels> interactionList=new ArrayList<>();
        interactionList=userInteractionWithReelsRepository.findAll();
        DashBoard adminDashboard=new DashBoard();
        adminDashboard.setLikes(this.totalLikes(interactionList));
        adminDashboard.setPublished(this.totalPublished(interactionList));
        adminDashboard.setWatchtime(this.totalWatchTime(interactionList));
        adminDashboard.setNewsReel_Views(this.totalReelsViews(interactionList));
        adminDashboard.setReelsList(this.getAllReels(interactionList));


         return adminDashboard;
    }

    public List<newsreelsDto> getAllReels(List<UserInteractionWithReels> interactionList){


        List<newsreelsDto> newsreelsDtoList=new ArrayList<>();
        Map<Long, newsreelsDto> Interactionmap = new HashMap<>();

        for(UserInteractionWithReels eachInteraction : interactionList){
            if(Interactionmap.containsKey(eachInteraction.getReelsId())){
                newsreelsDto tem=Interactionmap.get(eachInteraction.getReelsId());
                tem.setViews( Long.toString(Long.parseLong( tem.getViews()) +Long.parseLong(eachInteraction.getTime()) > 10L ? 1L : 0L) );
                tem.setLikes(Long.toString(Long.parseLong( tem.getLikes()) + (eachInteraction.getIsLiked()?1L:0L) ));


            }
            else{
                newsreelsDto tem=new newsreelsDto();
                tem.setReelsId(Long.toString( eachInteraction.getReelsId() ));
                tem.setLikes(Long.toString( eachInteraction.getIsLiked()?1L:0L));
                tem.setViews(String.valueOf( Long.parseLong(eachInteraction.getTime()) > 10L ? 1L : 0L));
                tem.setStatus("1");

                Interactionmap.put(eachInteraction.getReelsId(),tem);
            }

        }

        for (Map.Entry<Long, newsreelsDto> entry : Interactionmap.entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
            newsreelsDtoList.add(entry.getValue());
        }

        return  newsreelsDtoList;
    }

    public String totalLikes( List<UserInteractionWithReels> interactionList){

        long likeCount=0;
        for(UserInteractionWithReels eachInteraction : interactionList){
            if(eachInteraction.getIsLiked()){
                likeCount+=1;
            }

        }
        return  String.valueOf(likeCount);
    }

    public String totalWatchTime( List<UserInteractionWithReels> interactionList){
        long timeCount=0;
        for(UserInteractionWithReels eachInteraction : interactionList){
            if(eachInteraction.getIsLiked()){
                timeCount+=Long.parseLong(eachInteraction.getTime());
            }
        }
        return  String.valueOf(timeCount);
    }

    public String totalReelsViews( List<UserInteractionWithReels> interactionList){
        long viewCount=0;
        for(UserInteractionWithReels eachInteraction : interactionList){
            if(Long.parseLong(eachInteraction.getTime())>10L){
                viewCount+=1;
            }
        }

        return  String.valueOf(viewCount);
    }

    public String totalPublished( List<UserInteractionWithReels> interactionList){

        Set<Long> stringSet = new HashSet<>();
        for(UserInteractionWithReels eachInteraction : interactionList){
            stringSet.add(eachInteraction.getReelsId());
        }

        return  String.valueOf(stringSet.size());

    }
}
