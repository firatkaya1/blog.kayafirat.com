package com.kayafirat.blog.repository;

import com.kayafirat.blog.dto.CommentDTO;
import com.kayafirat.blog.dto.CommentViewDTO;
import com.kayafirat.blog.dto.CommentVoteDTO;
import com.kayafirat.blog.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT " +
            "c.id,c.body,c.created_date,c.update_date,u.username as user_name,u.photo as profile_photo,c.post_id,count(cv.comment_id) as total_vote \n" +
            "FROM comment c \n" +
            "LEFT  JOIN comment_vote cv on c.id = cv.comment_id \n" +
            "RIGHT  JOIN user u on c.user_id = u.id where c.is_hide = 0 and c.is_delete = 0 and c.post_id = :postId \n" +
            "group by c.id",
            countQuery = "SELECT count(1) from comment c where c.is_hide = 0 and c.is_delete = 0 and c.post_id = :postId",nativeQuery = true)
    Page<CommentDTO> findAllPageable(@Param("postId") Long postId,Pageable pageable);

    void deleteByPostId(Long id);

    long countByPostIdAndIsHideFalseAndIsDeleteFalse(Long id);

    @Query(value = "SELECT cv.id,date,u.id as user_id,u.photo as profile_photo,u.username as user_name FROM comment_vote as cv\n" +
            "INNER JOIN user u on cv.user_id = u.id\n" +
            "WHERE cv.comment_id = :commentID",
            countQuery = "SELECT count(id) from comment_vote as cv where cv.comment_id=:commentID",nativeQuery = true)
    Page<CommentVoteDTO> findAllVotes(@Param("commentID") Long commentId,Pageable pageable);

    @Query(value = "SELECT  \n" +
            "c.id,c.body,c.created_date,c.update_date,u.username as user_name,u.photo as profile_photo,c.post_id,count(cv.comment_id) as total_vote \n" +
            "FROM comment c  \n" +
            "LEFT JOIN comment_vote cv on c.id = cv.comment_id  \n" +
            "RIGHT JOIN user u on c.user_id = u.id where c.user_id = :userID group by c.id",nativeQuery = true)
    List<CommentDTO> findAllByUserId(@Param("userID") Long userId);

    @Query(value = "SELECT " +
            "c.id,c.body,c.created_date,c.update_date,u.username as user_name,u.photo as profile_photo,c.post_id,count(cv.comment_id) as total_vote \n" +
            "FROM comment c \n" +
            "LEFT  JOIN comment_vote cv on c.id = cv.comment_id \n" +
            "RIGHT  JOIN user u on c.user_id = u.id where c.is_hide = 0 and c.is_delete = 0 and c.post_id = :postId and c.id = :commentId \n" +
            "group by c.id",nativeQuery = true)
    CommentDTO findCommentById(@Param("commentId") Long commentId,@Param("postId") Long postId);

    Long countCommentByPostId(Long postId);

    @Query(value = "select c1.id as commentId,c1.body as commentBody,c1.is_delete as commentIsdelete, c1.is_hide as commentIshide,\n" +
            "p1.title as postTitle,u1.username as username,(select count(id) from comment_vote where comment_vote.comment_id = c1.id) as totalVote,\n" +
            "c1.created_date as commentCreatedDate \n" +
            "from comment c1 \n" +
            "left join user as u1 on u1.id = c1.user_id \n" +
            "left join post as p1 on p1.id = c1.post_id \n",nativeQuery = true)
    Page<CommentViewDTO> findAllCommentViews(Pageable pageable);
}
