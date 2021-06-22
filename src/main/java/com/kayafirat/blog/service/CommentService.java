package com.kayafirat.blog.service;

import com.kayafirat.blog.dto.CommentDTO;
import com.kayafirat.blog.dto.CommentVoteDTO;
import com.kayafirat.blog.entity.Comment;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentService {

    Page<CommentDTO> getComments(Long id, int pageNumber, int pageSize, String sortedBy, String orderBy);

    List<CommentDTO> getUserComments(Long id);

    Comment get(Long id);

    Comment addComment(Comment comment);

    Comment updateComment(Comment comment);

    void deleteComment(Long id);

    long getTotalComment(Long id);

    Comment addVote(Long id);

    Page<CommentVoteDTO> getVotes(Long id,int pageNumber,int pageSize,String sortedBy,String orderBy);

}
