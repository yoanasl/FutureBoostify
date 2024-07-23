package com.futureboost.FutureBoostify.controller;

import com.futureboost.FutureBoostify.dto.CommentDTO;
import com.futureboost.FutureBoostify.service.CommentService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentController {
    private final CommentService commentService;
    private static final Logger log = LoggerFactory.getLogger(CommentController.class);

    @PostMapping("/create")
    public ResponseEntity<CommentDTO> createComment(@RequestBody @Valid CommentDTO commentDTO) {
        try {
            log.info("Creating a new comment: {}", commentDTO);
            CommentDTO createdComment = commentService.createComment(commentDTO);
            log.info("Comment created successfully: {}", createdComment);
            return ResponseEntity.ok(createdComment);
        } catch (Exception e) {
            log.error("Error creating comment: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
