package controller;

import common.SerializableResourceBundleMessageSource;
import dto.Pueblomagico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;
import java.util.Properties;


@Controller
@RequestMapping("/messageBundle")
public class SerializableMessageBundleController {

	@Autowired
    SerializableResourceBundleMessageSource messageBundle;

    /**
     * ReadAll
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> list(@RequestParam String lang) 
    {
    	ResponseEntity<?> result = null;
    	result = new ResponseEntity<Properties>(messageBundle.getAllProperties(new Locale(lang)), HttpStatus.OK);
        return result;
    }
}
