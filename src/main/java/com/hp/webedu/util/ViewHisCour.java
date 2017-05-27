package com.hp.webedu.util;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.Action;
import org.springframework.stereotype.Controller;

//浏览商品
public class ViewHisCour {
//	@Resource(name="productInfoServiceBean")
//	private ProductInfoService productInfoService;
//
//	@Override
//	public ActionForward execute(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		FrontProductForm formbean = (FrontProductForm) form;
//		ProductInfo product = productInfoService.find(ProductInfo.class, formbean.getProductid());
//		if(null==product)
//		{
//			request.setAttribute("message", "获取你要的产品");
//			request.setAttribute("urladdress", "/");
//			return mapping.findForward("urladdress");
//		}
//		WebUtil.addCookie(response, "productViewhistory", 
//				buildViewHistory(request,formbean.getProductid()), 30*24*60*60);
//		List<ProductType> stypes=new ArrayList<ProductType>();
//		ProductType type = product.getType();//得到的是他的类型
//		while(null!=type)
//		{
//			stypes.add(type);
//			type=type.getParent();//依次取到他的父类型将他加到父类型里面去
//		}
//		
//		request.setAttribute("product", product);
//		request.setAttribute("stypes", stypes);
//		return mapping.findForward("product");
//	  }
	
	  //调用它来生成cookie的值
	  public static String buildViewHistory(HttpServletRequest request,String courseId)
	  {
		  //如果当前浏览的id已经在浏览历史里面的,则将他移在最前面
		  String cookieValue = WebUtil.getCookieByName(request, "courseViewhistory");
		  LinkedList<String>  prodectids =new LinkedList<String>();;
		  if(null!=cookieValue&&!"".equals(cookieValue.trim()))
		  {
			  String[] ids = cookieValue.split("-"); 
			  for(int i=0;i<ids.length;i++)
			  {
				  prodectids.add(ids[i]);
			  }
			  //若果当前的记录在浏览历史里面则删掉它
			  if(prodectids.contains(courseId)) prodectids.remove(courseId);
			  if(prodectids.size()>=10)
			  {
				  prodectids.poll();//清除最先进入的数据
			  }
		  }//将数据放在前面
		  prodectids.offer(courseId);
		  StringBuffer out =new StringBuffer();
		  for(String id:prodectids)
		  {
			  out.append(id).append("-");
		  }
		  out.deleteCharAt(out.length()-1);
		  return out.toString();
	  }
	

}
