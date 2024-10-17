package com.food.phat.controller;

import com.food.phat.dto.MessageResponse;
import com.food.phat.dto.NotificationDetailResponse;
import com.food.phat.dto.menu.MenuRequest;
import com.food.phat.dto.menu.MenuResponse;
import com.food.phat.service.NotificationService;
import com.food.phat.service.MenuService;
import com.food.phat.utils.ApiResponseMessage;
import com.food.phat.utils.NotificationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class MenuController {

    private final MenuService menuService;
    private final NotificationService notificationService;

    @Autowired
    public MenuController(MenuService menuService, NotificationService notificationService) {
        this.menuService = menuService;
        this.notificationService = notificationService;
    }

    @GetMapping("/restaurants/{restaurantId}/menus")
    public ResponseEntity<MessageResponse> getMenus(@PathVariable Integer restaurantId) {
        List<MenuResponse> menuResponse = menuService.getMenus(restaurantId);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_RETRIEVED.name())
                .data(menuResponse)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @GetMapping("/menus/{menuId}")
    public ResponseEntity<MessageResponse> getMenu(@PathVariable Integer menuId) {
        MenuResponse menuResponse = menuService.getMenu(menuId);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_RETRIEVED.name())
                .data(menuResponse)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @DeleteMapping("/menus/{menuId}")
    public ResponseEntity<MessageResponse> deleteMenu(@PathVariable Integer menuId) {
        menuService.deleteMenu(menuId);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_DELETED.name())
                .data(null)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);    }

    @PostMapping("/restaurants/{restaurantId}/menus")
    public ResponseEntity<MessageResponse> createMenu(@PathVariable Integer restaurantId, @RequestBody MenuRequest menuRequest) {
        menuService.createMenu(restaurantId, menuRequest);
        NotificationDetailResponse notificationDetailResponse  = notificationService.getSubscriptionDetail(restaurantId);

        Map<String, String> notificationResponse = NotificationMessage.MENU.notifyMessage(notificationDetailResponse.getObjectName());

        notificationService.send(notificationResponse.get("subject"),notificationResponse.get("content")
                , notificationDetailResponse.getEmailList().toArray(new String[0]));
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_CREATED.name())
                .data(null)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);    }

    @PutMapping("/menus/{menuId}")
    public ResponseEntity<MessageResponse> modifyMenu(@RequestBody MenuRequest menuRequest) {
        menuService.updateMenu(menuRequest);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_UPDATED.name())
                .data(null)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);    }
}






















/**
 * time: xoa het selling time hien tai + cap nhat lai moi
 * name:
 * status
 *
 * start_time, endtime: null -> khong ban
 * valid_from, valid_through: null -> ban full
 *
 * Menu co the ban tai nhieu thoi diem khac nhau trong ngay
 *
 *  (!start_time && !end_time) ||
 *  (valid_from && valid_through && (currentDate < validFrom ||  currentDate > valid_through)) ||
 *  (!start_time && !end_time && (currentTime < start_time || currentTime > end_time ))
 *  ======> Menu unavailable
 *  -> menu valid trong cac truong hop con lai
 *
 *
 * selling time
 *  isAvailable == null || validfrom && validthrough && currentDate < validFrom && currentDate > validThrough => Unavailable
 * period
 *
 */



















