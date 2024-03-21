package org.example.basicproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.Id;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tags",schema = "main")
public class Tags {

    @Id
    @Column(name = "id")
    private int tagId;

    @Column(name = "color")
    private String tagcolor;

    @Column(name = "content")
    private String tagContent;

    @Column(name = "insert_date")
    private Timestamp insertDate;

    @Column(name = "cancel_date")
    private Timestamp cancelDate;


}