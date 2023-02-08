package com.flow.taskBack.persistence;

import com.flow.taskBack.model.ExtEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface  ExtRespository extends JpaRepository<ExtEntity, String> {
    @Query(value = "select e.name, e.fix_yn, e.seq, e.checked from FILE_EXT e order by e.fix_yn, e.seq", nativeQuery = true)
    List<ExtEntity> selectAllList();

    @Modifying
    @Transactional
    @Query(value = "insert into FILE_EXT (name, fix_yn, seq) values " +
            "(:#{#entity.name}, :#{#entity.fix_yn}, (select count(e2.name) from file_ext e2 where e2.fix_yn='N'))"
            , nativeQuery = true)
    public int insertExt(@Param("entity") ExtEntity entity);

    @Query(value = "delete from FILE_EXT e where e.name = :#{#entity.name}", nativeQuery = true)
    public int deleteExt(@Param("entity") ExtEntity entity);

    List<ExtEntity> findByName(String name);
}
