package org.example.basicproject.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.basicproject.dao.TagsRepository;
import org.example.basicproject.dto.ResponseTag;
import org.example.basicproject.entity.Tags;
import org.example.basicproject.services.TagsServices;
import org.example.basicproject.util.TagConverter;
import org.example.basicproject.vm.ResultVM;
//import org.example.basicproject.vm.UpdateTagsVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Tags", description = "The TAG API")
public class TagsController {
    private final TagsRepository tagsRepository;

    public TagsController(TagsServices tagsServices, TagsRepository tagsRepository) {
        this.tagsRepository = tagsRepository;
    }

    @GetMapping("/getAllTags")
    @Operation(summary = "Get Tag With Pagination", description = "This can only be done by the authorized user.")
    @ApiResponse(responseCode = "200", description = "All Tags With Pagination")
    public ResponseEntity<ResultVM<Object>> getTags(Model model, @RequestParam(required = false) String keyword,
                                                    @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize,
                                                    @RequestParam(defaultValue = "tagContent") String sortBy,
                                                    @RequestParam(defaultValue = "ASC") String rotation)
    {
        List<Tags> tags;
        Long totalItemCount = null;
        List<ResponseTag> responseTagsList = null;
        try {
            tags = new ArrayList<Tags>();
            Pageable paging = PageRequest.of(page - 1, pageSize,Sort.Direction.ASC,sortBy);
            if(rotation.equals("DESC")){
                paging = PageRequest.of(page - 1, pageSize,Sort.Direction.DESC,sortBy);
            }

            Page<Tags> pageTags;
            if (keyword == null) {
                pageTags = tagsRepository.findAll(paging);
            } else {
                pageTags = tagsRepository.getAllNoDeletedTagsContainingIgnoreCase(keyword, paging);
                model.addAttribute("keyword", keyword);
            }

            tags = pageTags.getContent();

            responseTagsList = TagConverter.convertToResponseTags(tags);

            model.addAttribute("tags", responseTagsList);
            model.addAttribute("currentPage", pageTags.getNumber() + 1);
            model.addAttribute("totalItems", pageTags.getTotalElements());
            model.addAttribute("totalPages", pageTags.getTotalPages());
            model.addAttribute("pageSize", pageSize);
            totalItemCount = pageTags.getTotalElements();
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        ResultVM<Object> resultVM = new ResultVM<>(200, "Success",totalItemCount, responseTagsList);

        return ResponseEntity.ok(resultVM);
    }


    @GetMapping("/getTagsById/{tag_id}")
    @Operation(summary = "Get Tag By Id", description = "This can only be done by the authorized user.")
    @ApiResponse(responseCode = "200", description = "The tag was successfully extracted based on the selected id")
    public ResponseEntity<Tags> getTagById(@PathVariable int tag_id) {
        List<Tags> tagsList= tagsRepository.findByTagId(tag_id);
        if (!tagsList.isEmpty()) {
            return ResponseEntity.ok(tagsList.get(tag_id));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*@PutMapping("/tagUpdate")
    @ResponseBody
    @Operation(summary = "Update Tag", description = "This can only be done by the authorized user.")
    @ApiResponse(responseCode = "200", description = "The Tag was updated successfully.")
    public int updateTags(@RequestBody UpdateTagsVM vm) {
        try {
            return tagsRepository.updateTags(vm.tagContent,vm.tagColor,vm.tagId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while updating tags", e);
        }
    }
*/

    @PostMapping("/tagCreate")
    @Operation(summary = "Delete Tag", description = "This can only be done by the authorized user.")
    @ApiResponse(responseCode = "200", description = "The tag was created successfully.")
    public void createTag(@RequestBody Tags tag) {
        tagsRepository.insertTag(tag.getTagContent(), tag.getTagcolor());
    }

}

