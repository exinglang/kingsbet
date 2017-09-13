package com.kingsbet.wzry.entity;

import com.sun.net.httpserver.HttpExchange;
import org.springframework.util.StringUtils;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class HttpSession {
    Map<String, Object> map = new HashMap<String, Object>();
    Date lastVisitTime = new Date(); // 最后访问时间

    public void addAttribute(String name, Object value) {
        map.put(name, value);
    }

    public Object getAttribute(String name) {
        return map.get(name);
    }

    public Map<String, Object> getAllAttribute() {
        return map;
    }

    public Set<String> getAllNames() {
        return map.keySet();
    }

    public boolean containsName(String name) {
        return map.containsKey(name);
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public Date getLastVisitTime() {
        return lastVisitTime;
    }

    public void setLastVisitTime(Date lastVisitTime) {
        this.lastVisitTime = lastVisitTime;
    }

}
