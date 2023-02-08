package com.flow.taskBack.controller;


import com.flow.taskBack.dto.ExtDTO;
import com.flow.taskBack.dto.ResponseDTO;
import com.flow.taskBack.model.ExtEntity;
import com.flow.taskBack.service.ExtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("ext")
public class ExtController {
    @Autowired
    private ExtService service;

    // 확장자 불러오기
    @GetMapping
    public ResponseEntity<?> retrieveExtList(){

        List<ExtEntity> entities = service.findAll();

        List<ExtDTO> dtos = entities.stream().map(ExtDTO::new).collect(Collectors.toList());

        ResponseDTO<ExtDTO> response = ResponseDTO.<ExtDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    // 확장자 등록
    @PostMapping
    public ResponseEntity<?> createExt(@RequestBody ExtDTO dto){
        try {

            ExtEntity entity = ExtDTO.toEntity(dto);

            List<ExtEntity> entities = service.create(entity);

            List<ExtDTO> dtos = entities.stream().map(ExtDTO::new).collect(Collectors.toList());

            ResponseDTO<ExtDTO> response = ResponseDTO.<ExtDTO>builder().data(dtos).build();

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<ExtDTO> response = ResponseDTO.<ExtDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    // 확장자 삭제
    @DeleteMapping
    public ResponseEntity<?> deleteExt(@RequestParam(required = false) String name, @RequestBody ExtDTO dto){
        try {

            ExtEntity entity = ExtDTO.toEntity(dto);

            entity.setName(name);

            List<ExtEntity> entities = service.delete(entity);

            List<ExtDTO> dtos = entities.stream().map(ExtDTO::new).collect(Collectors.toList());

            ResponseDTO<ExtDTO> response = ResponseDTO.<ExtDTO>builder().data(dtos).build();

            return ResponseEntity.ok().body(response);
        } catch(Exception e) {
            String error = e.getMessage();
            ResponseDTO<ExtDTO> response = ResponseDTO.<ExtDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    // 확장자 수정
    @PutMapping
    public ResponseEntity<?> updateExt(@RequestBody ExtDTO dto){

        ExtEntity entity = ExtDTO.toEntity(dto);

        List<ExtEntity> entities = service.update(entity);

        List<ExtDTO> dtos = entities.stream().map(ExtDTO::new).collect(Collectors.toList());

        ResponseDTO<ExtDTO> response = ResponseDTO.<ExtDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

}
