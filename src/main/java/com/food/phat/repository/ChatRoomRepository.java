package com.food.phat.repository;

import com.food.phat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {

    @Query(value = """
        select * from chat_room cr, chat_room_user cru where
            cr.chat_room_id = cru.chat_room_fkey and
            cru.user_fkey = ?1
        """, nativeQuery = true)
    List<ChatRoom> findByUserId(Integer userId);
}
