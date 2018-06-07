package kz.enu.kztrdictionary.controller;

import kz.enu.kztrdictionary.dao.kzword.*;
import kz.enu.kztrdictionary.dao.trword.*;
import kz.enu.kztrdictionary.model.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(maxAge = 3600)
@RestController
public class TrController extends RequestUtil {

    @Autowired
    TrWordDAO trWordDAO;

    @RequestMapping(value = "/trtokz", method = RequestMethod.POST)
    public Map<String, Object> trtokz(@ModelAttribute("word") String word ) {
        List<String> translate = trWordDAO.translate(word);

        return toRequest(translate, null);
    }

    @RequestMapping(value = "/addtr", method = RequestMethod.POST)
    public Map<String, Object> addtr(@ModelAttribute("word") String word,@ModelAttribute("trans") String[] trans) {

        try {
            trWordDAO.addTranslate(word, trans);
            return toRequest("OK",null);
        } catch (Exception e){
            return toRequest("FAULT",new Exception("Ошибка!"));
        }
    }
}
