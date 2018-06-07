package kz.enu.kztrdictionary.controller;

import java.util.*;

public class RequestUtil {

    public Map<String,Object> toRequest(Object r, Exception e){
        Map<String,Object> result = new HashMap<>();
        if(e==null){
            result.put("status",1);
            result.put("data",r);
        } else {
            result.put("status",0);
            result.put("error",1);
        }

        return result;
    }
}
