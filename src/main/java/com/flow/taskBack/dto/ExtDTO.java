package com.flow.taskBack.dto;

import com.flow.taskBack.model.ExtEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExtDTO {
    private String name;
    private String fix_yn;
    private int seq;
    private String checked;

    public ExtDTO(final ExtEntity entity) {
        this.name 	= entity.getName();
        this.fix_yn = entity.getFix_yn();
        this.seq 	= entity.getSeq();
        this.checked = entity.getChecked();
    }

    public static ExtEntity toEntity(final ExtDTO dto) {
        return ExtEntity.builder()
                .name(dto.getName())
                .fix_yn(dto.getFix_yn())
                .seq(dto.getSeq())
                .checked(dto.getChecked())
                .build();
    }
}
