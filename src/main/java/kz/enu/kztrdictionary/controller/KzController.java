package kz.enu.kztrdictionary.controller;

import kz.enu.kztrdictionary.dao.kzword.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(maxAge = 3600)
@RestController
public class KzController extends RequestUtil {

    @Autowired
    KzWordDAO kzWordDAO;

    @RequestMapping(value = "/kztotr", method = RequestMethod.POST)
    public Map<String, Object> kztotr(@ModelAttribute("word") String word) {

        List<String> translate = kzWordDAO.translate(word);

        return toRequest(translate, null);
    }

    @RequestMapping(value = "/addkz", method = RequestMethod.POST)
    public Map<String, Object> addkz(@ModelAttribute("word") String word,@ModelAttribute("trans") String[] trans) {

        try {
            kzWordDAO.addTranslate(word, trans);
            return toRequest("OK",null);
        } catch (Exception e){
            return toRequest("FAULT",new Exception("Ошибка!"));
        }
    }
}
