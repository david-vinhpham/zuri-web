package com.ocha.boc.controllers;

import com.ocha.boc.request.RestaurantRequest;
import com.ocha.boc.request.RestaurantUpdateRequest;
import com.ocha.boc.response.RestaurantResponse;
import com.ocha.boc.services.impl.RestaurantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Slf4j
@Api(value = "Restaurant Api", description = "Restaurant Api")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    /**
     * Create new Restaurants
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "Create New Restaurants", authorizations = {@Authorization(value = "Bearer")})
    @PostMapping("/restaurants")
    public ResponseEntity<RestaurantResponse> createRestaurants(@RequestBody @Valid RestaurantRequest request) {
        log.info("START: create new Restaurants");
        RestaurantResponse response = restaurantService.createRestaurants(request);
        log.info("END: create new Restaurants");
        return ResponseEntity.ok(response);
    }

    /**
     * Update Restaurants Information
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "Update Restaurants Information", authorizations = {@Authorization(value = "Bearer")})
    @PutMapping("/restaurants")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<RestaurantResponse> updateEmailRestaurants(@RequestBody @Valid RestaurantUpdateRequest request) {
        log.info("START: update Restaurants information ");
        RestaurantResponse response = restaurantService.updateEmailRestaurants(request);
        log.info("END: update Restaurants information");
        return ResponseEntity.ok(response);
    }

    /**
     * Find restaurant Information by restaurantId
     *
     * @param restaurantId
     * @return
     */
    @ApiOperation(value = "Find Restaurant Information by restaurantId", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping("/restaurants/{restaurantId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<RestaurantResponse> findCuaHangByrestaurantId(@PathVariable(value = "restaurantId") String restaurantId) {
        log.info("START: find restaurant information by restaurantId: " + restaurantId);
        RestaurantResponse response = restaurantService.findCuaHangByRestaurantId(restaurantId);
        log.info("END: find restaurant information by restaurantId");
        return ResponseEntity.ok(response);
    }

}
