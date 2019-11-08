package com.xu.springbootdemo.config;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class MyInterceptor extends HandlerInterceptorAdapter {

    /**
     * 通过uri获取用户的请求路径，并且将无需拦截的请求保存在数组中，通过contains判断是否需要拦截，
     * 需要拦截的时候，再通过判断用户是否获取session来判断是否登录
     * 并且在一个请求里面，只能适用response一次，getwritter，和返回数据各算一次
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws IOException
     */
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
            System.out.println("========preHandle-start==========");
            System.out.println(request.getRequestURL()); //http://localhost:8082/user/1/xujialiang
            String uri = request.getRequestURI(); ///user/1/xujialiang
            System.out.println(request.getServletPath()); ///user/1/xujialiang
            System.out.println(request.getMethod());     //GET
            System.out.println("-------------------------");
            String[] arr = {"/login","/","/error"};
            ArrayList<String> urls = new ArrayList<>();
            urls.addAll(Arrays.asList(arr));
            if(urls.contains(uri)){
                return  true;
            }else {
                if(request.getSession().getAttribute("user")==null){
                    response.setHeader("content-type", "text/html;charset=UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("用户未登录");
                    return false;
                }else{
                    System.out.println("========preHandle-end==========");
                    return true;
                }
            }

        }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("========afterCompletion-start==========");
        System.out.println(request.getRequestURI());
        System.out.println("========afterCompletion-end==========");
    }
}
