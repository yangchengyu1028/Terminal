package com.ycy.util;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * 服务拦截
// */
//@Component
//public class ApiFilter extends ZuulFilter {
//
//    @Autowired
//    HttpServletRequest httpServletRequest;
//
//    //filterType 为过滤类型，可选值有 pre（路由之前）、routing（路由之时）、post（路由之后）、error（发生错误时调用）。
//    public String filterType() {
//        return "pre";
//    }
//    //filterOrdery 为过滤的顺序，如果有多个过滤器，则数字越小越先执行
//    public int filterOrder() {
//        return 0;
//    }
//    //shouldFilter 表示是否过滤，这里可以做逻辑判断，true 为过滤，false 不过滤
//    public boolean shouldFilter() {
//        return true;
//    }
//    //run 为过滤器执行的具体逻辑，在这里可以做很多事情，比如：权限判断、合法性校验等。
//    public Object run() {
//        //这里写校验代码
//        RequestContext ctx = RequestContext.getCurrentContext();
//        String sessionId = httpServletRequest.getSession().getId();
//        ctx.addZuulRequestHeader("Cookie", "SESSION=" + sessionId);
//        ctx.setSendZuulResponse(true);// 对该请求进行路由
//        ctx.setResponseStatusCode(200); // 返回200正确响应
//
//        return null;
//    }
//
//}
