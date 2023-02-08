package com.flow.taskBack.service;

import com.flow.taskBack.model.ExtEntity;
import com.flow.taskBack.persistence.ExtRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ExtService {
    @Autowired
    private ExtRespository extRespository;

    // select
    public List<ExtEntity> findAll(){
        log.info("확장자 전부 출력 완료.");
        return extRespository.selectAllList();
    }

    // insert
    public List<ExtEntity> create(final ExtEntity entity){
        validate(entity);

        extRespository.insertExt(entity);

        log.info("확장자 : {} 등록 완료.", entity.getName());

        return findAll();
    }

    // delete
    public List<ExtEntity> delete(final ExtEntity entity){
        validate(entity);
        try {
            extRespository.delete(entity);

        } catch(Exception e) {
            log.error("error 삭제시 발생 ", entity.getName(), e);

            throw new RuntimeException("error 삭제시 발생" + entity.getName());
        }
        return findAll();
    }

    // update
    public List<ExtEntity> update(final ExtEntity entity){
        validate(entity);

        final Optional<ExtEntity> original = extRespository.findById(entity.getName());

        original.ifPresent(ext -> {
            ext.setChecked(entity.getChecked());

            extRespository.save(ext);
        });
        return findAll();
    }

    // 검증
    private void validate(final ExtEntity entity) {
        if(entity == null) {
            log.warn("Entity 없음.");
            throw new RuntimeException("Entity 없음");
        }

        if(entity.getName() == null) {
            log.warn("확장자 이름 null");
            throw new RuntimeException("확장자 이름 null");
        }
    }
}
