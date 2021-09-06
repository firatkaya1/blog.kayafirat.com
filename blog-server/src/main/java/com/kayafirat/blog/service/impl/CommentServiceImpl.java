package com.kayafirat.blog.service.impl;

import com.kayafirat.blog.dto.CommentDTO;
import com.kayafirat.blog.dto.CommentViewDTO;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public Page<CommentViewDTO> getAllCommentsAdmin(int pageNumber, int pageSize, String sortedBy, String orderBy) {
        Sort sort = orderBy.equals("asc".toLowerCase()) ? Sort.by(sortedBy).ascending() : Sort.by(sortedBy).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return commentRepository.findAllCommentViews(pageable);
    }

    @Override
    public Page<Comment> getAllComments(int pageNumber, int pageSize, String sortedBy, String orderBy) {
        Sort sort = orderBy.equals("asc".toLowerCase()) ? Sort.by(sortedBy).ascending() : Sort.by(sortedBy).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return commentRepository.findAll(pageable);
    }

    @Override
    public Page<CommentDTO> getComments(Long id, int pageNumber, int pageSize, String sortedBy, String orderBy) {
        Sort sort = orderBy.equals("asc".toLowerCase()) ? Sort.by(sortedBy).ascending() : Sort.by(sortedBy).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return commentRepository.findAllPageable(id,pageable);
    }

    @Override
    public List<CommentDTO> getUserComments(Long id) {
        return commentRepository.findAllByUserId(id);
    }

    @Override
    public Comment get(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(()->new CommentIDNotFoundException(id));
    }

    @Override
    public CommentDTO addComment(Comment comment) {
        comment.setCreatedDate(new Date());
        if(!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")){
            comment.setUserId(Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName()));
        } else {
            comment.setUserId(1L);
        }
        Comment _comment = commentRepository.save(comment);

        return commentRepository.findCommentById(_comment.getId(),_comment.getPostId());
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
        commentVote.setDate(new Date());
        if(!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")){
            commentVote.setUserId(Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName()));
        } else {
            commentVote.setUserId(398l);
        }
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
