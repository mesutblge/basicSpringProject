package org.example.basicproject.util;

import org.example.basicproject.dto.ResponseTag;
import org.example.basicproject.entity.Tags;

import java.util.List;
import java.util.stream.Collectors;

public class TagConverter {

    public static List<ResponseTag> convertToResponseTags(List<Tags> tagsList) {
        return tagsList.stream()
                .map(TagConverter::convertToResponseTag)
                .collect(Collectors.toList());
    }

    public static ResponseTag convertToResponseTag(Tags tag) {
        ResponseTag responseTag = new ResponseTag();
        responseTag.setId(tag.getTagId());
        responseTag.setColor(tag.getTagcolor());
        responseTag.setContent(tag.getTagContent());
     /*   responseTag.setIsDeleted(tag.getTagIsDeleted());
        responseTag.setCreatedTime(tag.getTagCreatedTime());
        responseTag.setSharing(tag.getTagsSharing());*/
        return responseTag;
    }


}
