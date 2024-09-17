package com.food.phat.controller;

import com.food.phat.dto.menu.MenuPost;
import com.food.phat.dto.menu.MenuResponse;
import com.food.phat.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurant")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/{restaurantId}/menus")
    public ResponseEntity<?> getMenus(@PathVariable Integer restaurantId) {
        List<MenuResponse> menuResponse = menuService.getMenu(restaurantId);
        return new ResponseEntity<>(menuResponse, HttpStatus.OK);
    }

    @GetMapping("/{restaurantId}/menu/{menuId}")
    public ResponseEntity<?> getMenu(@PathVariable Integer restaurantId, @PathVariable Integer menuId) {
        MenuResponse menuResponse = menuService.getMenu(restaurantId, menuId);
        return new ResponseEntity<>(menuResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{restaurantId}/menu/{menuId}")
    public ResponseEntity<?> deleteMenu(@PathVariable Integer restaurantId, @PathVariable Integer menuId) {
        menuService.deleteMenu(restaurantId, menuId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{restaurantId}/menu")
    public ResponseEntity<?> createMenu(@PathVariable Integer restaurantId, @RequestBody MenuPost menuPost) {
        menuService.createMenu(restaurantId, menuPost);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PutMapping("/{restaurantId}/menu")
//    public ResponseEntity<?> modifyMenu(@PathVariable Integer restaurantId, @RequestBody MenuPut menuPost) {
//        menuService.createMenu(restaurantId, menuPost);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

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



















