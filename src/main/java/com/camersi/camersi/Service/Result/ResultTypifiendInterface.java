package com.camersi.camersi.Service.Result;

import java.util.List;

import com.camersi.camersi.Mapping.Typified.TypifiedDTO;
import com.camersi.camersi.Model.Typified.TypifiedEntity;

public interface ResultTypifiendInterface {
    List<TypifiedEntity> queryAll();
    TypifiedEntity queryID(Long id);
    Boolean exist(Long id);
    TypifiedEntity mutationCreate(TypifiedDTO entity);
    TypifiedEntity mutationUpdate(TypifiedDTO entity);
}
