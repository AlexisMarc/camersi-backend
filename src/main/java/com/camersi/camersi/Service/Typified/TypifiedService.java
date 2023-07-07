package com.camersi.camersi.Service.Typified;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camersi.camersi.Mapping.Typified.TypifiedDTO;
import com.camersi.camersi.Model.Pending.PendingEntity;
import com.camersi.camersi.Model.Typified.TypifiedEntity;
import com.camersi.camersi.Model.Typified.TypifiedInterface;
import com.camersi.camersi.Model.Usuario.UsuarioEntity;
import com.camersi.camersi.Service.Pending.PendingService;
import com.camersi.camersi.Service.Result.ResultTypifiendInterface;
import com.camersi.camersi.Service.Usuario.UsuarioService;

@Service
public class TypifiedService implements ResultTypifiendInterface {

    @Autowired
    private TypifiedInterface typified;

    @Autowired
    private UsuarioService usuario;

    @Autowired
    private PendingService pending;

    @Override
    public List<TypifiedEntity> queryAll() {
        return typified.findAll();
    }

    @Override
    public TypifiedEntity queryID(Long id) {
        return typified.getReferenceById(id);
    }

    private TypifiedEntity getEntity(TypifiedDTO DTO) {
        return new TypifiedEntity(DTO.getId(), DTO.getIdentidad(), DTO.getNombre(), DTO.getTelefono(), DTO.getCompany(),
                DTO.getEmail(), DTO.getCertificado(), DTO.getSoporte(), DTO.getSubSoporte(), DTO.getFecha(), DTO.getHora(),
                DTO.getObservacion(), DTO.getEstado(), DTO.getTiempo(), DTO.getCategoria(), getAgente(DTO.getAgente()),
                false, DTO.getPendiente(), DTO.getIncidence(), getPending(DTO.getPending()), DTO.getGuia(),
                DTO.getEvidencia());
    }

    @Override
    public TypifiedEntity mutationCreate(TypifiedDTO entity) {
        return typified.save(getEntity(entity));
    }

    @Override
    public TypifiedEntity mutationUpdate(TypifiedDTO entity) {
        TypifiedEntity typifiedID = typified.getReferenceById(entity.getId());

        if (entity.getIdentidad() != null && entity.getIdentidad() != typifiedID.getIdentidad())
            typifiedID.setIdentidad(entity.getIdentidad());
        if (entity.getNombre() != null && entity.getNombre() != typifiedID.getNombre())
            typifiedID.setNombre(entity.getNombre());
        if (entity.getTelefono() != null && entity.getTelefono() != typifiedID.getTelefono())
            typifiedID.setTelefono(entity.getTelefono());
        if (entity.getCompany() != null && entity.getCompany() != typifiedID.getCompany())
            typifiedID.setCompany(entity.getCompany());
        if (entity.getEmail() != null && entity.getEmail() != typifiedID.getEmail())
            typifiedID.setEmail(entity.getEmail());
        if (entity.getCertificado() != null && entity.getCertificado() != typifiedID.getCertificado())
            typifiedID.setCertificado(entity.getCertificado());
        if (entity.getSoporte() != null && entity.getSoporte() != typifiedID.getSoporte())
            typifiedID.setSoporte(entity.getSoporte());
        if (entity.getSubSoporte() != null && entity.getSubSoporte() != typifiedID.getSubSoporte())
            typifiedID.setSubSoporte(entity.getSubSoporte());
        if (entity.getFecha() != null && entity.getFecha() != typifiedID.getFecha())
            typifiedID.setFecha(entity.getFecha());
        if (entity.getHora() != null && entity.getHora() != typifiedID.getHora())
            typifiedID.setHora(entity.getHora());
        if (entity.getObservacion() != null && entity.getObservacion() != typifiedID.getObservacion())
            typifiedID.setObservacion(entity.getObservacion());
        if (entity.getEstado() != null && entity.getEstado() != typifiedID.getEstado())
            typifiedID.setEstado(entity.getEstado());
        if (entity.getTiempo() != null && entity.getTiempo() != typifiedID.getTiempo())
            typifiedID.setTiempo(entity.getTiempo());
        if (entity.getCategoria() != null && entity.getCategoria() != typifiedID.getCategoria())
            typifiedID.setCategoria(entity.getCategoria());
        if (entity.getAgente() != null)
            typifiedID.setAgente(getAgente(entity.getAgente()));
        if (entity.getEliminado() != null && entity.getEliminado() != typifiedID.getEliminado())
            typifiedID.setEliminado(entity.getEliminado());
        if (entity.getPendiente() != null && entity.getPendiente() != typifiedID.getPendiente())
            typifiedID.setPendiente(entity.getPendiente());
        if (entity.getPending() != null)
            typifiedID.setPending(getPending(entity.getPending()));
        if (entity.getGuia() != null && entity.getGuia() != typifiedID.getGuia())
            typifiedID.setGuia(entity.getGuia());
        if (entity.getEvidencia() != null && entity.getEvidencia() != typifiedID.getEvidencia())
            typifiedID.setEvidencia(entity.getEvidencia());

        return typified.save(typifiedID);
    }

    @Override
    public Boolean exist(Long id) {
        return typified.existsById(id);
    }

    private UsuarioEntity getAgente(String id) {
        if (id == null)
            return null;
        if (!usuario.exist(id))
            return null;
        return usuario.queryID(id);
    }

    private PendingEntity getPending(Long id) {
        if (id == null)
            return null;
        if (!pending.exist(id))
            return null;
        return pending.queryID(id);
    }

}
