package com.camersi.camersi.Service.Usuario.Cargo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camersi.camersi.Mapping.Usuario.Cargo.CargoDTO;
import com.camersi.camersi.Model.Usuario.Cargo.CargoEntity;
import com.camersi.camersi.Model.Usuario.Cargo.CargoInterface;
import com.camersi.camersi.Service.Result.ResultCargoInterface;

@Service
public class CargoService implements ResultCargoInterface {

    @Autowired
    private CargoInterface cargo;

    @Override
    public List<CargoEntity> queryAll() {
        return cargo.findAll();
    }

    @Override
    public CargoEntity queryID(Integer id) {
        return cargo.getReferenceById(id);
    }

    private CargoEntity getEntity(CargoDTO DTO){
        return new CargoEntity(DTO.getId(), DTO.getDescripcion(), DTO.getCargo(), false, null);
    }

    @Override
    public CargoEntity mutationCreate(CargoDTO entity) {
        return cargo.save(getEntity(entity));
    }

    @Override
    public CargoEntity mutationUpdate(CargoDTO entity) {
        CargoEntity cargoID = cargo.getReferenceById(entity.getId());
        if (entity.getCargo() != null && entity.getCargo() != cargoID.getCargo())
            cargoID.setCargo(entity.getCargo());
        if (entity.getDescripcion() != null && entity.getDescripcion() != cargoID.getDescripcion())
            cargoID.setDescripcion(entity.getDescripcion());
        if (entity.getEstado() != null && entity.getEstado() != cargoID.getEstado())
            cargoID.setEstado(entity.getEstado());

        return cargo.save(cargoID);
    }

    @Override
    public Boolean exist(Integer id) {
        return cargo.existsById(id);
    }

    @Override
    public Boolean existCargo(String carg) {
        if (cargo.findByCargo(carg) == null)
            return false;
        return true;
    }

}
