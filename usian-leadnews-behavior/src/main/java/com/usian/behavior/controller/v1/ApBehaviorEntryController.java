package com.usian.behavior.controller.v1;

import com.usian.apis.behavior.ApBehaviorEntryControllerApi;
import com.usian.behavior.service.ApBehaviorEntryService;
import com.usian.model.behavior.pojos.ApBehaviorEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/behavior_entry")
public class ApBehaviorEntryController implements ApBehaviorEntryControllerApi {
    @Autowired
    private ApBehaviorEntryService apBehaviorEntryService;

    @GetMapping("/one")
    @Override
    public ApBehaviorEntry findByUserIdOrEquipmentId(@RequestParam("userId") Integer userId,@RequestParam("equipmentId") Integer equipmentId) {
        return apBehaviorEntryService.findByUserIdOrEquipmentId(userId,equipmentId);
    }
}
