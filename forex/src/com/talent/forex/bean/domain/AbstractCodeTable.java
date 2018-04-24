package com.talent.forex.bean.domain;



/**
 * AbstractCodeTable entity provides the base persistence definition of the CodeTable entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractCodeTable  implements java.io.Serializable {


    // Fields    

     private CodeTableId id;
     private String descr;


    // Constructors

    /** default constructor */
    public AbstractCodeTable() {
    }

    
    /** full constructor */
    public AbstractCodeTable(CodeTableId id, String descr) {
        this.id = id;
        this.descr = descr;
    }

   
    // Property accessors

    public CodeTableId getId() {
        return this.id;
    }
    
    public void setId(CodeTableId id) {
        this.id = id;
    }

    public String getDescr() {
        return this.descr;
    }
    
    public void setDescr(String descr) {
        this.descr = descr;
    }
   








}