package com.camersi.camersi.Service.Pending;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camersi.camersi.Mapping.Pending.PendingDTO;
import com.camersi.camersi.Model.Pending.PendingEntity;
import com.camersi.camersi.Model.Pending.PendingInterface;
import com.camersi.camersi.Service.Result.ResultPendingInterface;

@Service
public class PendingService implements ResultPendingInterface {

    @Autowired
    private PendingInterface pending;

    @Override
    public List<PendingEntity> queryAll() {
        return pending.findAll();
    }

    @Override
    public PendingEntity queryID(Long id) {
        return pending.getReferenceById(id);
    }

    private PendingEntity getEntity(PendingDTO DTO){
        return new PendingEntity(DTO.getId(),DTO.getFecha(),new Date().toString(), null);
    }

    @Override
    public PendingEntity mutationCreate(PendingDTO entity) {
        return pending.save(getEntity(entity));
    }

    @Override
    public PendingEntity mutationUpdate(PendingDTO entity) {
        PendingEntity pendingID = pending.getReferenceById(entity.getId());

        if (entity.getCreacion() != null && pendingID.getCreacion() != entity.getCreacion())
            pendingID.setCreacion(entity.getCreacion());
        if (entity.getFecha() != null && pendingID.getFecha() != entity.getFecha())
            pendingID.setFecha(entity.getFecha());

        return pending.save(pendingID);
    }

    @Override
    public Boolean exist(Long id) {
        return pending.existsById(id);
    }

    @Override
    public Boolean existFecha(String fecha) {
        if (pending.findByFecha(fecha) == null)
            return false;
        return true;
    }
}
