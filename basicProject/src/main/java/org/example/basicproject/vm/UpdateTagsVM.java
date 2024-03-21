package org.example.basicproject.vm;

import lombok.*;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTagsVM {
    public Long tagId;
    public String tagContent;
    public String tagColor;
}

