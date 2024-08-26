package com.NewsTok.User.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.NewsTok.User.Models.UserInteractionWithReels;

public interface UserInteractionWithReelsRepository extends JpaRepository<UserInteractionWithReels, Long> {
    UserInteractionWithReels findByReelsIdAndUserId(Long reelsId, Long userId);
}
