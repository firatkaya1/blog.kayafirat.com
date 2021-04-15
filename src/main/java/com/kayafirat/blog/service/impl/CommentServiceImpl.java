package com.kayafirat.blog.service.impl;

import com.kayafirat.blog.dto.CommentDTO;
import com.kayafirat.blog.dto.CommentVoteDTO;
import com.kayafirat.blog.entity.Comment;
import com.kayafirat.blog.entity.CommentVote;
import com.kayafirat.blog.exception.custom.CommentIDNotFoundException;
import com.kayafirat.blog.repository.CommentRepository;
import com.kayafirat.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public Page<CommentDTO> getComments(Long id, int pageNumber, int pageSize, String sortedBy, String orderBy) {
        Sort sort = orderBy.equals("asc".toLowerCase()) ? Sort.by(sortedBy).ascending() : Sort.by(sortedBy).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return commentRepository.findAllPageable(id,pageable);
    }

    @Override
    public List<CommentDTO> getUserComments() {
        return commentRepository.findAllByUserId(301l);
    }

    @Override
    public Comment get(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(()->new CommentIDNotFoundException(id));
    }

    @Override
    public Comment addComment(Comment comment) {
        comment.setUserId(301l);
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Comment comment) {
        if(!commentRepository.existsById(comment.getId()))
            throw new CommentIDNotFoundException(comment.getId());
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long id) {
        if(!commentRepository.existsById(id))
            throw new CommentIDNotFoundException(id);
        commentRepository.deleteById(id);
    }

    @Override
    public long getTotalComment(Long id) {
        return commentRepository.countByPostIdAndIsHideFalseAndIsDeleteFalse(id);
    }

    @Override
    public Comment addVote(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(()->new CommentIDNotFoundException(id));
        CommentVote commentVote = new CommentVote();
        commentVote.setUserId((long)301l);
        commentVote.setComment(comment);
        comment.getCommentVotes().add(commentVote);
        return commentRepository.save(comment);
    }

    @Override
    public Page<CommentVoteDTO> getVotes(Long id, int pageNumber, int pageSize, String sortedBy, String orderBy) {
        Sort sort = orderBy.equals("asc".toLowerCase()) ? Sort.by(sortedBy).ascending() : Sort.by(sortedBy).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return commentRepository.findAllVotes(id,pageable);
    }
}
