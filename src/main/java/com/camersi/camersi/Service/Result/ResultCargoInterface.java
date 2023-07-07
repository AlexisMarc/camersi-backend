package com.camersi.camersi.Service.Result;

import java.util.List;

import com.camersi.camersi.Mapping.Usuario.Cargo.CargoDTO;
import com.camersi.camersi.Model.Usuario.Cargo.CargoEntity;

public interface ResultCargoInterface {
    List<CargoEntity> queryAll();
    CargoEntity queryID(Integer id);
    Boolean exist(Integer id);
    Boolean existCargo(String carg);
    CargoEntity mutationCreate(CargoDTO entity);
    CargoEntity mutationUpdate(CargoDTO entity);
}
