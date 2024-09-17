package com.food.phat.controller;

import com.food.phat.dto.menu.MenuPost;
import com.food.phat.dto.menu.MenuResponse;
import com.food.phat.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restaurant")
public class MenuCategoryController {


//    @GetMapping("/{restaurantId}/menuCategory/{menuId}")
//    public ResponseEntity<?> getMenu(@PathVariable Integer restaurantId, @PathVariable Integer menuId) {
//        MenuResponse menuResponse = menuService.getMenu(restaurantId, menuId);
//        return new ResponseEntity<>(menuResponse, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{restaurantId}/menu/{menuId}")
//    public ResponseEntity<?> deleteMenu(@PathVariable Integer restaurantId, @PathVariable Integer menuId) {
//        menuService.deleteMenu(restaurantId, menuId);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @PostMapping("/{restaurantId}/menu")
//    public ResponseEntity<?> createMenu(@PathVariable Integer restaurantId, @RequestBody MenuPost menuPost) {
//        menuService.createMenu(restaurantId, menuPost);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}
