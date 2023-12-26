package com.usian.apis.behavior;

import com.usian.model.behavior.pojos.ApBehaviorEntry;

public interface ApBehaviorEntryControllerApi {
    public ApBehaviorEntry findByUserIdOrEquipmentId(Integer userId,Integer equipmentId);
}
