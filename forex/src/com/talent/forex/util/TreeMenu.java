package com.talent.forex.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.talent.menu.Directory;
import com.talent.menu.Item;
import com.talent.menu.MenuAbs;
import com.talent.menu.MenuCmp;
import com.talent.menu.MenuIterator;

public class TreeMenu{

private ArrayList dirList = new ArrayList();

private boolean containLeaf = false;

private int subDirCnt = 0;

public static void main(String[] args)
{
}

public String genMenu(List permission, String menuTitle, String configFile)
{
  Document doc = null;

  String content = null;
  try {
    doc = new SAXReader().read(super.getClass()
      .getResourceAsStream(configFile));
    MenuCmp menuCmp = new MenuCmp();

    List nodeList = doc.selectNodes("menu");
    Node node = null;

    for (int i = 0; i < nodeList.size(); ++i) {
      MenuAbs rootDir = new Directory();
      node = (Node)nodeList.get(i);

      setMenuAtrr(rootDir, node);
      rootDir.setCaption(menuTitle);
      menuCmp.add(rootDir);

      List sonNodeList = node.selectNodes("item");
      iteratorItem(sonNodeList, menuCmp);

      sonNodeList = node.selectNodes("directory");
      iteratorDirectory(sonNodeList, menuCmp);

      filterPermission(menuCmp, genPermissionSet(permission));

      filterDirectory(menuCmp);

      content = genHtmlByXml(menuCmp);
    }
  } catch (Exception e) {
    e.printStackTrace();
  }

  return content;
}

public HashSet genPermissionSet(List permission)
{
  HashSet permissionSet = new HashSet();
  Iterator it = permission.iterator();
  while (it.hasNext()) {
    permissionSet.add(it.next());
  }
  return permissionSet;
}

public void iteratorDirectory(List nodeList, MenuCmp menuCmp)
{
  for (int j = 0; j < nodeList.size(); ++j) {
    Node node = (Node)nodeList.get(j);
    Directory dir = new Directory();
    setDirAtrr(dir, node);
    menuCmp.add(dir);

    List sonNodeList = node.selectNodes("item");
    if (sonNodeList.size() > 0) {
      iteratorItem(sonNodeList, dir);
    }

    sonNodeList = node.selectNodes("directory");

    if (sonNodeList.size() > 0)
      iteratorDirectory(sonNodeList, dir);
  }
}

public void iteratorDirectory(List nodeList, Directory upperDir)
{
  for (int j = 0; j < nodeList.size(); ++j) {
    Node node = (Node)nodeList.get(j);
    Directory dir = new Directory();
    setDirAtrr(dir, node);
    upperDir.add(dir);

    List sonNodeList = node.selectNodes("item");
    if (sonNodeList.size() > 0) {
      iteratorItem(sonNodeList, dir);
    }
    sonNodeList = node.selectNodes("directory");

    if (sonNodeList.size() > 0)
      iteratorDirectory(sonNodeList, dir);
  }
}

public void iteratorItem(List nodeList, MenuCmp menuCmp)
{
  for (int j = 0; j < nodeList.size(); ++j) {
    Item item = new Item();
    Node node = (Node)nodeList.get(j);
    setItemAtrr(item, node);
    menuCmp.add(item);
  }
}

public void iteratorItem(List nodeList, Directory directory)
{
  for (int j = 0; j < nodeList.size(); ++j) {
    Item item = new Item();
    Node node = (Node)nodeList.get(j);
    setItemAtrr(item, node);
    directory.add(item);
  }
}

public void filterPermission(MenuCmp menuCmp, HashSet permissionSet)
{
  MenuIterator it = new MenuIterator(menuCmp.getMenuList().iterator());
  while (it.hasNext()) {
    MenuAbs item = (MenuAbs)it.next();

    if (item.getPermission() != null) {
      if (item.getPermission().equals("*"))
        continue;
      if (!(permissionSet.contains(item.getPermission()))) {
        item.setPermission("forbidden_show");
      }
    }

    if (item.getItemType().equals("directory"))
      this.dirList.add(item);
  }
}

public void filterDirectory(MenuCmp menuCmp)
{
  Iterator it = this.dirList.iterator();
  while (it.hasNext()) {
    MenuAbs item = (MenuAbs)it.next();
    this.containLeaf = false;
    judgeDirectory(item);
    if (this.containLeaf)
      item.setContainLeaf(this.containLeaf);
  }
}

public void judgeDirectory(MenuAbs item)
{
  Iterator it = item.deepIterator();
  while (it.hasNext()) {
    MenuAbs subItem = (MenuAbs)it.next();
    if ((subItem.getItemType().equals("item")) && 
      (!(subItem.getPermission().equals("forbidden_show")))) {
      this.containLeaf = true;
      return;
    }

    if (subItem.getItemType().equals("directory"))
      judgeDirectory(subItem);
  }
}

public String genHtmlByXml(MenuCmp menuCmp)
{
  StringBuffer sb = new StringBuffer();
  int directoryCnt = 0;
  int myId=0;
  Iterator it = menuCmp.getMenuList().iterator();
  while (it.hasNext()) {
	  	
	    MenuAbs item = (MenuAbs)it.next();
	    if (item.getItemType().equals("menu")) {
	      constructMenu(sb, item);
	    }
	
	    if ((item.getItemType().equals("directory")) && 
	      (item.isContainLeaf())) {
	      ++myId;
	      ++directoryCnt;
	      if (directoryCnt == 2) {
	        sb.append("</li></ul>\n");
	        --directoryCnt;
	      }
	      constructDirectory(sb, item,2);
	      this.subDirCnt = 0;
	      genDirectory(sb, item);
	    }
	
	    if ((!(item.getItemType().equals("item"))) || 
	      (item.getPermission().equals("forbidden_show"))) continue;
	    constructItem(sb, item);
  }

  sb.append("</li></ul>");

  return sb.toString();
}

public void compStringBuffer(StringBuffer sb, MenuAbs item)
{
  if (item.getItemType().equals("directory")) {
    constructDirectory(sb, item,3);
  }
  if ((!(item.getItemType().equals("item"))) || 
    (item.getPermission().equals("forbidden_show"))) return;
  constructItem(sb, item);
}

public void genDirectory(StringBuffer sb, MenuAbs dir)
{
  Iterator it = dir.deepIterator();

  while (it.hasNext()) {
    MenuAbs item = (MenuAbs)it.next();
    if (item.getItemType().equals("directory")) {
      if (item.isContainLeaf()) {
        this.subDirCnt += 1;
        compStringBuffer(sb, item);
        genDirectory(sb, item);
        sb.append("</li></ul>\n");
      }
    }
    else if (item.getItemType().equals("item"))
      compStringBuffer(sb, item);
  }
}

public void constructMenu(StringBuffer sb, MenuAbs item)
{
  sb.append("<ul><li cascade='1' class='");
  sb.append(item.getExpanded());
  sb.append("'>\n");
  //sb.append("<img class=s src='css/s.gif' onclick='javascript:ChangeStatus(this);'>\n");
  	sb.append("<a href='#' class=l0 onclick='javascript:ChangeStatus(this);'>\n" );
 // sb.append("<a href='javascript:ChangeStatus(this);' class=l0>\n");
  sb.append(item.getCaption());
  sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>\n");
}

public void constructDirectory(StringBuffer sb, MenuAbs item)
{
  sb.append("<ul><li isParent='yes' class='");
  sb.append(item.getExpanded());
  sb.append("'>\n");
  //sb.append("<img class=s src='css/s.gif' onclick='javascript:ChangeStatus(this);'>\n");
  sb.append("<a href='#' onclick='javascript:ChangeStatus(this);'>\n");
  sb.append(item.getCaption());
  sb.append("</a>\n");
}

public void constructDirectory(StringBuffer sb, MenuAbs item,int cascade)
{
  sb.append("<ul><li isParent='yes' cascade='");
  sb.append(cascade);
  sb.append("' class='");
  sb.append(item.getExpanded());
  sb.append("'>\n");
  //sb.append("<img class=s src='css/s.gif' onclick='javascript:ChangeStatus(this);'>\n");
  sb.append("<a href='#' class=l2 onclick='javascript:ChangeStatus(this);'>\n");
  sb.append(item.getCaption());
  sb.append("</a>\n");
}

public void constructItem(StringBuffer sb, MenuAbs item)
{
  sb.append("<ul><li class='Child'>\n");
  //sb.append("<img class=s src='css/s.gif'>\n");
  sb.append("<a class=l3 href='");
  sb.append(item.getUrl());
  sb.append("' ");
  sb.append("target=");
  sb.append(item.getTarget());

  sb.append(">\n");
  sb.append(item.getCaption());
  sb.append("</a></li></ul>\n");
}

public void setMenuAtrr(MenuAbs rootDir, Node node)
{
  rootDir.setExpanded(node.selectSingleNode("expanded").getText().trim());

  rootDir.setItemType("menu");
}

public void setDirAtrr(MenuAbs dir, Node node)
{
  dir.setCaption(node.selectSingleNode("caption").getText().trim());
  dir.setExpanded(node.selectSingleNode("expanded").getText().trim());

  dir.setItemType("directory");
}

public void setItemAtrr(MenuAbs item, Node node)
{
  item.setCaption(node.selectSingleNode("caption").getText().trim());
  item.setUrl(node.selectSingleNode("url").getText().trim());
  item.setPermission(node.selectSingleNode("permission").getText().trim());
  item.setTarget(node.selectSingleNode("target").getText().trim());

  item.setItemType("item");
}

}