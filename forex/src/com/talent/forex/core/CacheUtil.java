package com.talent.forex.core;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CacheUtil
{
  public static final int INT_USER_NUMS = 100;
  public static final int INT_QUESTION_NUMS = 1000;

  public static String formatDate(Date date, String partten)
  {
    String s = "";
    SimpleDateFormat sdf = new SimpleDateFormat(partten);
    try {
      s = sdf.format(date);
    } catch (Exception e) {
      System.out.println("com.tom.util.Util.formatDate()发生异常,partten=" + partten);
    }
    return s;
  }

  public static Integer toInt(String s)
  {
    Integer i = null;
    try {
      i = Integer.valueOf(s);
    } catch (Exception e) {
      System.out.println("com.tom.util.Util.toInt()发生异常,s=" + s);
    }
    return i;
  }

  public static String splitString(String s, int len)
  {
    String str = s;
    if ((s != null) && (s.length() > len)) {
      str = s.substring(0, len);
    }
    return str;
  }

//  public static String NEWSTYPE(String currentid)
//  {
//    String html = "";
//    String checked = "";
//    CoreDAO dao = new CoreDAO();
//    List list = dao.query("from Newstype n");
//    if ((list != null) && (list.size() > 0)) {
//      for (Newstype nt : list) {
//        String pid = nt.getParentid();
//        String id = nt.getId();
//        if ("0".equals(pid)) {
//          checked = (currentid.equals(id)) ? " selected" : "";
//          html = html + "<option value=\"" + nt.getId() + "\" " + checked + ">" + nt.getTpname() + "</option>";
//          for (Newstype nts : list) {
//            String spid = nts.getParentid();
//            String sid = nts.getId();
//            if (spid.equals(id)) {
//              checked = (currentid.equals(sid)) ? " selected" : "";
//              html = html + "<option value=\"" + nts.getId() + "\" " + checked + "> - " + nts.getTpname() + "</option>";
//            }
//          }
//        }
//      }
//    }
//
//    dao = null;
//    return html;
//  }
//
//  public static String GET_NEWSTYPE_NAME(String id)
//  {
//    String value = "";
//    CoreDAO dao = new CoreDAO();
//    Newstype nt = (Newstype)dao.findById("Newstype", id);
//    if (nt != null) {
//      value = nt.getTpname();
//    }
//    dao = null;
//    return value;
//  }
//
//  public static String GET_NEWSTYPE_NAME(HttpServletRequest request, String id)
//  {
//    String value = "";
//    Map map = new HashMap();
//
//    if (request.getAttribute("MAP_OF_NEWSTYPE") == null) {
//      CoreDAO dao = new CoreDAO();
//      List list = dao.query("from Newstype n");
//      if ((list != null) && (list.size() > 0)) {
//        for (Newstype nt : list) {
//          map.put(nt.getId(), nt.getTpname());
//        }
//      }
//      request.setAttribute("MAP_OF_NEWSTYPE", map);
//    } else {
//      map = (Map)request.getAttribute("MAP_OF_NEWSTYPE");
//    }
//
//    if (map != null) {
//      Iterator it = map.entrySet().iterator();
//      while (it.hasNext()) {
//        Map.Entry m = (Map.Entry)it.next();
//        String mapkey = m.getKey();
//        if (mapkey.equals(id)) {
//          value = m.getValue();
//          break;
//        }
//      }
//    }
//
//    return value;
//  }

  public static int USER_NUMS()
  {
    CoreDAO dao = new CoreDAO();
    List list = dao.query("from Users u");
    if ((list != null) && (list.size() > 0)) {
      return list.size();
    }
    return 0;
  }

  public static int QUESTION_NUMS()
  {
    CoreDAO dao = new CoreDAO();
    List list = dao.query("from Questions q");
    if ((list != null) && (list.size() > 0)) {
      return list.size();
    }
    return 0;
  }
}
