package com.food.phat.utils;

import java.util.HashMap;
import java.util.Map;

public enum NotificationMessage {
    MENU {
        public Map<String, String> notifyMessage(String objectName) {
            Map<String, String> map = new HashMap<>();
            map.put("subject", "%s added a new menu".formatted(objectName));
            map.put("content", "Check it out !");
            return map;
        }
    },
    PRODUCT {
        public Map<String, String> notifyMessage(String objectName) {
            Map<String, String> map = new HashMap<>();
            map.put("subject", "%s added a new product".formatted(objectName));
            map.put("content", "Check it out !");
            return map;
        }
    };
    public abstract Map<String, String> notifyMessage(String objectName);

}
