package org.example.basicproject.vm;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResultVM<T> {

    private int status;
    private String message;
    private Long TotalCount;
    private T data;

}