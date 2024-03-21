package org.example.basicproject.services;


import org.example.basicproject.dao.TagsRepository;
import org.springframework.stereotype.Service;

@Service
public class TagsServices {
    private final TagsRepository tagsRepository;

    public TagsServices(TagsRepository tagsRepository) {
        this.tagsRepository = tagsRepository;
    }

    public static String tagsColor() {
        String[] colorStatuses = {"Blue", "Red", "Yellow", "Black", "White", "Orange"};
        int randomColor = (int) (Math.random() * colorStatuses.length);
        return colorStatuses[randomColor];
    }

    public static String tagsContent() {
        String[] contentStatuses = {"Kaçakçılık", "Uyuşturucu", "Terör", "Hırsızlık"};
        int randomContent = (int) (Math.random() * contentStatuses.length);
        return contentStatuses[randomContent];
    }
}

    /*
    //create dummy tag
    public ResponseEntity<List<Tags>> getTags(){

        List<Tags> resTags = new ArrayList<>();
        for (int i=0; i<=100; i++){
            String tagsColor=tagsColor();
            String tagsContent=tagsContent();
            Tags tags =new Tags();
            tags.setTagId((int) i);
            tags.setTagcolor(tagsColor);
            tags.setTagContent(tagsContent);
            tags.setTagsSharing("Owner");
            tags.setTagIsDeleted(false);
            Timestamp createdTime = new Timestamp(System.currentTimeMillis());
            tags.setTagCreatedTime(createdTime);
            tagsRepository.save(tags);
        }

        return ResponseEntity.ok(resTags);
    }*/

