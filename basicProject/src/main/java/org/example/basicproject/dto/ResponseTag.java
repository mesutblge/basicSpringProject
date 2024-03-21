package org.example.basicproject.dto;

import lombok.*;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTag {
    private int id;
    private String color;
    private String content;
}

