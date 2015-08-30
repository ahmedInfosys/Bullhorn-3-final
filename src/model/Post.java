package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the POST database table.
 * 
 */
@Entity
@Table(name="POST",schema="TESTDB")
@NamedQuery(name="Post.findAll", query="SELECT p FROM Post p")
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "POST_SEQ", catalog = "",schema="TESTDB",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="POST_SEQ")
	@Column(name="POST_ID")
	private long postId;

	@Column(name="POST_CONTENT")
	private String postContent;

	@Column(name="POST_DATE")
	private String postDate;

	@Column(name="POST_USER_ID")
	private long postUserId;

	public Post() {
	}

	public long getPostId() {
		return this.postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public String getPostContent() {
		return this.postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getPostDate() {
		return this.postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public long getPostUserId() {
		return this.postUserId;
	}

	public void setPostUserId(long postUserId) {
		this.postUserId = postUserId;
	}

}