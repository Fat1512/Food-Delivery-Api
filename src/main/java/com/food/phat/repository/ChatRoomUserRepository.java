package com.food.phat.repository;

import com.food.phat.entity.ChatRoomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomUserRepository extends JpaRepository<ChatRoomUser, Integer> {

    @Query(value = "select cru.* from chat_room_user cru where cru.user_fkey = ?1", nativeQuery = true)
    List<ChatRoomUser> getChatRoomUserBasedOnUserId(Integer userId);
}
