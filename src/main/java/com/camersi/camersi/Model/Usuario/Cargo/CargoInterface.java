package com.camersi.camersi.Model.Usuario.Cargo;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CargoInterface extends JpaRepository<CargoEntity, Integer> {
    CargoEntity findByCargo(String cargo);
}
