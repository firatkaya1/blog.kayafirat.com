package com.kayafirat.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@Table(name = "meta")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "targetType"})
public class Meta extends JdkSerializationRedisSerializer implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "google_id", referencedColumnName = "id")
    private GoogleSEO googleSEO;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "twitter_id", referencedColumnName = "id")
    private TwitterSEO twitterSEO;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "facebook_id", referencedColumnName = "id")
    private FacebookSEO facebookSEO;

    public Meta(GoogleSEO googleSEO, TwitterSEO twitterSEO, FacebookSEO facebookSEO,Long id){
        this.id = id;
        this.googleSEO = googleSEO;
        this.twitterSEO = twitterSEO;
        this.facebookSEO = facebookSEO;
    }

    public Meta(GoogleSEO googleSEO, TwitterSEO twitterSEO, FacebookSEO facebookSEO){
        this.googleSEO = googleSEO;
        this.twitterSEO = twitterSEO;
        this.facebookSEO = facebookSEO;
    }

}
