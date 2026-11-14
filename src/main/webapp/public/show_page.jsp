<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <div class="pagin" ${requestScope.pageUtil.isShow() }>
    <div class="message">共<i class="blue">${requestScope.page.totalRocords }</i>条记录，共<i class="blue">${requestScope.page.totalPages }</i>页，当前显示第&nbsp;<i class="blue">${requestScope.page.pageNo }&nbsp;</i>页</div>
     <ul class="paginList">
        <li class="paginItem"><a href="${requestScope.pageUtil.firstHref }" >首页</a></li>
        <li class="paginItem"><a href="${requestScope.pageUtil.preHref  }" ${requestScope.pageUtil.hasPre()  }><span class="pagepre"></span></a></li>        
        <li class="paginItem"><a href="${requestScope.pageUtil.nextHref }" ${requestScope.pageUtil.hasNext()  }><span class="pagenxt"></span></a></li>
        <li class="paginItem"><a href="${requestScope.pageUtil.endHref}">末页</a></li>
     </ul>
 </div>
