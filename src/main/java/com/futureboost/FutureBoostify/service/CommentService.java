package com.futureboost.FutureBoostify.service;

import com.futureboost.FutureBoostify.dto.CommentDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public interface CommentService {
    public CommentDTO createComment(CommentDTO commentDTO);
}
