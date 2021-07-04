package com.kayafirat.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "postDetail")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "targetType"})
public class PostDetail extends JdkSerializationRedisSerializer implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String body;

    @JsonIgnoreProperties("postDetail")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @JsonIgnoreProperties("postDetail")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "meta_id", referencedColumnName = "id")
    private Meta meta;

}
