package com.camersi.camersi.Service.Result;

import java.util.List;

import com.camersi.camersi.Mapping.Pending.PendingDTO;
import com.camersi.camersi.Model.Pending.PendingEntity;

public interface ResultPendingInterface {
    List<PendingEntity> queryAll();
    PendingEntity queryID(Long id);
    Boolean exist(Long id);
    Boolean existFecha(String fecha);
    PendingEntity mutationCreate(PendingDTO entity);
    PendingEntity mutationUpdate(PendingDTO entity);
}
