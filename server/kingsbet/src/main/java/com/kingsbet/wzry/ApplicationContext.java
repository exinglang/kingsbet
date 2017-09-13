package com.kingsbet.wzry;

import com.kingsbet.wzry.entity.HttpSession;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 全局数据和会话相关数据,单例

 */
public class ApplicationContext {
    private Map<String, Object> appMap = new HashMap<String, Object>(); // ApplicationContext全局数据

    /**
     * 线程安全?
     *
     */
    private ConcurrentMap<String, HttpSession> sessionMap = new ConcurrentHashMap<String, HttpSession>(); // session数据

    private ApplicationContext(){
    }

    /**
     * 内部类实现单例
     */
    private static class ApplicationContextHolder {
        private static ApplicationContext instance = new ApplicationContext();
    }

    public static ApplicationContext getApplicationContext() {
        return ApplicationContextHolder.instance;
    }

    public void addAttribute(String name, Object value) {
        ApplicationContextHolder.instance.appMap.put(name, value);
    }

    public Object getAttribute(String name) {
        return ApplicationContextHolder.instance.appMap.get(name);
    }

    public Map<String, Object> getAllAttribute() {
        return ApplicationContextHolder.instance.appMap;
    }

    public Set<String> getAllNames() {
        return ApplicationContextHolder.instance.appMap.keySet();
    }

    public boolean containsName(String name) {
        return ApplicationContextHolder.instance.appMap.containsKey(name);
    }

    public void addSession(String sessionId) {
        HttpSession httpSession = new HttpSession();
        httpSession.setLastVisitTime(new Date());
        ApplicationContextHolder.instance.sessionMap.put(sessionId, httpSession);
        for(Map.Entry<String, HttpSession> entry:ApplicationContextHolder.instance.sessionMap.entrySet())
        {
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }

    /**
     * 获取session
     */
    public HttpSession getSession(String sessionId) {
//        String sessionId = getSessionId(httpExchange);
        if (StringUtils.isEmpty(sessionId)) {
            return null;
        }
        HttpSession httpSession = ApplicationContextHolder.instance.sessionMap.get(sessionId);
        if (null == httpSession) {
            httpSession = new HttpSession();
            ApplicationContextHolder.instance.sessionMap.put(sessionId, httpSession);
        }
        return httpSession;
    }

//    /**
//     * 获取sessionId
//     */
//    public String getSessionId(HttpExchange httpExchange) {
//        String cookies = httpExchange.getRequestHeaders().getFirst("Cookie");
//        String sessionId = "";
//        if (StringUtils.isEmpty(cookies)) {
//            cookies = httpExchange.getResponseHeaders().getFirst("Set-Cookie");
//        }
//
//        if (StringUtils.isEmpty(cookies)) {
//            return null;
//        }
//
//        String[] cookiearry = cookies.split(";");
//        for(String cookie : cookiearry){
//            cookie = cookie.replaceAll(" ", "");
//            if (cookie.startsWith("EH_SESSION=")) {
//                sessionId = cookie.replace("EH_SESSION=", "").replace(";", "");
//            }
//        }
//
//        return sessionId;
//    }

    /**
     * 获取所有session
     */
    public ConcurrentMap<String, HttpSession> getAllSession() {
        return ApplicationContextHolder.instance.sessionMap;
    }

    /**
     * 设置session最后访问时间
     */
    public void setSessionLastTime(String sessionId) {
        HttpSession httpSession = ApplicationContextHolder.instance.sessionMap.get(sessionId);
        httpSession.setLastVisitTime(new Date());
    }
}