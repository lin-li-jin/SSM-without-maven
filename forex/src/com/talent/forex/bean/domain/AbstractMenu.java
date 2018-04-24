package com.talent.forex.bean.domain;



/**
 * AbstractMenu entity provides the base persistence definition of the Menu entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractMenu  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String caption;
     private Long pid;
     private Integer postId;
     private String url;


    // Constructors

    /** default constructor */
    public AbstractMenu() {
    }

	/** minimal constructor */
    public AbstractMenu(String caption, Long pid, Integer postId) {
        this.caption = caption;
        this.pid = pid;
        this.postId = postId;
    }
    
    /** full constructor */
    public AbstractMenu(String caption, Long pid, Integer postId, String url) {
        this.caption = caption;
        this.pid = pid;
        this.postId = postId;
        this.url = url;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaption() {
        return this.caption;
    }
    
    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Long getPid() {
        return this.pid;
    }
    
    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getPostId() {
        return this.postId;
    }
    
    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
   








}