package com.futureboost.FutureBoostify.service;

import com.futureboost.FutureBoostify.dto.CommentDTO;
import com.futureboost.FutureBoostify.exception.CustomCommentException;
import com.futureboost.FutureBoostify.model.Comment;
import com.futureboost.FutureBoostify.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


// Example after code:

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private static final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);
    private final ModelMapper modelMapper;
    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public CommentDTO createComment(CommentDTO commentDTO) {
        if (commentDTO == null || commentDTO.getCampaignId() == null || commentDTO.getUserId() == null || commentDTO.getText() == null) {
            log.warn("Received invalid commentDTO: {}", commentDTO);
            throw new IllegalArgumentException("CommentDTO must have non-null campaignId, userId, and text");
        }
        log.info("Creating a new comment: {}", commentDTO);
        try {
            Comment comment = modelMapper.map(commentDTO, Comment.class);
            Comment savedComment = commentRepository.save(comment);
            return modelMapper.map(savedComment, CommentDTO.class);
        } catch (Exception e) {
            log.error("Error creating comment: {}", e.getMessage());
            throw new CustomCommentException("Error creating comment: " + e.getMessage());
        }
    }
}
