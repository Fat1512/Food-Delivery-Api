package com.food.phat.service.Impl;

import com.food.phat.dto.NotificationDetailResponse;
import com.food.phat.service.NotificationService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private JavaMailSender JavaMailSender;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void send(String subject, String content, String ...to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        JavaMailSender.send(message);
    }

    @Override
    public NotificationDetailResponse getSubscriptionDetail(Integer restaurantId) {
        String name = em.createQuery("select r.name from Restaurant r").getSingleResult().toString();
        List<String> emailList =  em.createNativeQuery("""
            select u.email from user u, notification_subscription ns
            where u.user_id = ns.user_fkey and ns.restaurant_fkey = :restaurantId
        """).setParameter("restaurantId", restaurantId).getResultList();

        NotificationDetailResponse notificationDetailResponse = new NotificationDetailResponse();
        notificationDetailResponse.setObjectName(name);
        notificationDetailResponse.setEmailList(emailList);
        return notificationDetailResponse;
    }


}
