package org.example.basicproject.dao;

import jakarta.transaction.Transactional;
import org.example.basicproject.entity.Tags;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Transactional
//Page<Tags> findByTagsContentContainingIgnoreCase(String keyword, Pageable pageable);
public interface TagsRepository extends JpaRepository<Tags, Long> {

    @Query(value = "select * from tags",nativeQuery = true)
    List<Tags> findAllTags();
    @Query(value = "select * from tags where tag_id = tag_id",nativeQuery = true)
    List<Tags> findByTagId(@Param("tag_id") int tag_id);
    @Query("select a from Tags a where a.tagContent like %:keyword%")
    Page<Tags> getAllNoDeletedTagsContainingIgnoreCase(@Param("keyword") String keyword, Pageable pageable);

    @Query("UPDATE Tags SET tagContent = :tag_content, tagcolor = :tag_color WHERE tagId = :tag_id")
    @Modifying
    public int updateTags(@Param("tag_content") String tag_content,@Param("tag_color") String tag_color,@Param("tag_id") Long tag_id);

    @Modifying
    @Query(value = "INSERT INTO tags (tag_content, tag_color) VALUES (:tagContent, :tagColor)", nativeQuery = true)
    void insertTag(@Param("tagContent") String tagContent, @Param("tagColor") String tagColor);

}

