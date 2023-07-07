package com.camersi.camersi.Model.Pending;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PendingInterface extends JpaRepository<PendingEntity, Long>  {
    PendingEntity findByFecha(String fecha);
}

