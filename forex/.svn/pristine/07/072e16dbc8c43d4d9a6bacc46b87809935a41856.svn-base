package com.talent.forex.core;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;

public class PagerDAO
{
  HttpServletRequest request;
  HttpServletResponse response;
  Session session;
  String hql;
  public int PageSize;
  public int intPageCount;
  public int intCountic;
  public int intPage;
  public String nowPage;
  public String HttpFile;
  public String PageParameter;
  public List list;

  public PagerDAO(HttpServletRequest request, HttpServletResponse response, Session session, String hql)
  {
	    this.request = request;
	    this.response = response;
	    this.session = session;
	    this.hql = hql;
  }

  public Session getSession() {
	    this.session = HibernateSessionFactory.getSession();
	    return this.session;
  }

  public int getRecordsCount()
  {
	    Query query = getSession().createQuery("select count(*) from " + getPojo());
	    List list = query.list();
	
	    int i = Integer.parseInt(list.get(0).toString());
	    return i;
  }

  public String getPojo() {
	    String[] str = this.hql.split("from");
	    String[] table = str[1].trim().split(" ");
	    String[] strWhere = this.hql.split("where");
	    String[] strPojoNameAs = this.hql.split(" ");
	    String[] strCondition = strWhere[1].split("order");
	
	    String str4Return = table[0] + " " + strPojoNameAs[2] + " where " + strCondition[0];
	
	    return str4Return;
  }

  public String getTotalRows() {
    return String.valueOf(this.intCountic);
  }

  public String getTotalPages() {
    return String.valueOf(this.intPageCount);
  }

  public String getNowPage() {
    String np = "1";
    if ((this.nowPage != null) && (!("".equals(this.nowPage)))) {
      np = this.nowPage;
    }
    return np;
  }

  public List getList()
  {
    try
    {
      if (this.PageSize == 0) this.PageSize = 20;
      this.HttpFile = this.request.getRequestURI();
      this.nowPage = this.request.getParameter("pages");
      this.PageParameter = urlPath(this.request);

      this.intCountic = getRecordsCount();
      this.intPageCount = ((this.intCountic + this.PageSize - 1) / this.PageSize);

      if (this.nowPage == null) {
        this.intPage = 1;
      } else {
        this.intPage = Integer.parseInt(this.nowPage);
        if ((this.intPage < 1) || (this.intPage > this.intPageCount)) {
          this.intPage = 1;
        }

      }

      Query query = this.session.createQuery(this.hql);

      this.list = query.setFirstResult((this.intPage - 1) * this.PageSize).setMaxResults(this.PageSize).list();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    } finally {
      if (this.session != null) this.session.close();
    }
    return this.list;
  }

  public String urlPath(HttpServletRequest request)
  {
    String path = "";
    String pagepath = "pages=";
    String url = request.getQueryString();

    if ((url == null) || (url.equals(""))) {
      return pagepath;
    }

    List lista = new ArrayList();
    StringTokenizer ss = new StringTokenizer(url, "&");

    while (ss.hasMoreTokens()) {
      String s = ss.nextToken();
      if (s.indexOf("pages") == -1) {
        lista.add(s);
      }
    }
    for (int i = 0; i < lista.size(); ++i) {
      String param = "";
      try
      {
        param = new String(lista.get(i).toString().getBytes("iso-8859-1"), "utf-8");
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
      path = path + param + "&";
    }
    return path + pagepath;
  }

  public int getPageSize() {
    return this.PageSize;
  }

  public void setPageSize(int pageSize) {
    this.PageSize = pageSize;
  }

  public String PageFooter()
  {
    String str = "";

    String pstr = this.HttpFile + "?" + this.PageParameter.replaceAll("&pages=", "");

    str = str + " &nbsp; 共" + this.intCountic + "条纪录 每页" + this.PageSize + "条 共" + this.intPageCount + "页 当前" + this.intPage + "/" + this.intPageCount + "页";

    int prev = this.intPage - 1;
    int next = this.intPage + 1;

    if (this.intPage > 1)
      str = str + " <A href=" + pstr + "&pages=1>首页</A>";
    else {
      str = str + " 首页 ";
    }
    if (this.intPage > 1)
      str = str + " <A href=" + pstr + "&pages=" + prev + ">上一页</A> ";
    else {
      str = str + " 上一页 ";
    }
    if (this.intPage < this.intPageCount)
      str = str + " <A href=" + pstr + "&pages=" + next + ">下一页</A> ";
    else {
      str = str + " 下一页 ";
    }
    if ((this.intPageCount > 1) && (this.intPage != this.intPageCount))
      str = str + " <A href=" + pstr + "&pages=" + this.intPageCount + ">尾页</A>";
    else {
      str = str + " 尾页 ";
    }
    String strx = "";
    strx = strx + "<form action='" + pstr + "' method='post' style='margin:0px;padding:0px;' align=\"center\" onsubmit='return check4nav()'>";
    strx = strx + "\t<input type='text' size='3' name='pages'>";
    strx = strx + "\t<input type='submit' value='跳转' class='btn'>";
    strx = strx + "</form>";

    String nav = "";
    nav = nav + "<table width='98%' class=\"pagertable\" align=\"center\" border='0' cellpadding='0' cellspacing='0' style='margin-top:10px'>";
    nav = nav + "\t<tr >";
    nav = nav + "\t<td align='left'>" + str + "</td>";
    nav = nav + "\t<td align='right'>" + strx + "</td>";
    nav = nav + "\t</tr>";
    nav = nav + "</table>";

    return nav;
  }
}
