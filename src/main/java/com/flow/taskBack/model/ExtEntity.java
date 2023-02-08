package com.flow.taskBack.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "FILE_EXT")
public class ExtEntity {
    @Id
    @Column(name = "name")
    private String name; 		// 확장자 이름

    @Column(name = "fix_yn")
    private String fix_yn; 		// 고정 확장자 여부

    @Column(name = "seq")
    private int seq; 			// 확장자 순서

    @Column(name = "checked")
    private String checked;     // 선택 여부
}
