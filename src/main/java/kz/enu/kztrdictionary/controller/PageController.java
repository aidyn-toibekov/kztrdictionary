package kz.enu.kztrdictionary.controller;

import kz.enu.kztrdictionary.dao.kzword.*;
import kz.enu.kztrdictionary.model.*;
import kz.enu.kztrdictionary.model.kzword.*;
import org.joda.time.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@CrossOrigin(maxAge = 3600)
@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class PageController {

    //Главная страница
    @RequestMapping("/")
    public String index() throws Exception {
        return "index";
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Map<String, Object> test() {

        Map<String, Object> data = new HashMap<>();
        data.put("status", 1);
        data.put("dte", DateTime.now());


        Map<String, Object> map = new HashMap<>();
        map.put("data", data);

        return map;
    }


}