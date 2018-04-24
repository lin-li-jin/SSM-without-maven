package com.talent.forex.bean.domain;



/**
 * Menu entity. @author MyEclipse Persistence Tools
 */
public class Menu extends AbstractMenu implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Menu() {
    }

	/** minimal constructor */
    public Menu(String caption, Long pid, Integer postId) {
        super(caption, pid, postId);        
    }
    
    /** full constructor */
    public Menu(String caption, Long pid, Integer postId, String url) {
        super(caption, pid, postId, url);        
    }
   
}
