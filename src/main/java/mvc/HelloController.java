package mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HelloController {
    @RequestMapping("/hello")
    public ModelAndView hello( @RequestParam(value="firstname") String firstName,
                        @RequestParam(value="lastname") String lastName) {

        String message=firstName+" "+lastName;

        Map<String, Object> params = new HashMap<>();
        params.put("message", message);

        return new ModelAndView("hello",params);
    }

    @RequestMapping("/multiply")
    public ModelAndView multiply( @RequestParam(value="number1") Integer firstNumber,
                               @RequestParam(value="number2") Integer secondNumber) {

        Integer result = firstNumber*secondNumber;

        Map<String, Object> params = new HashMap<>();
        params.put("number1", firstNumber);
        params.put("number2", secondNumber);
        params.put("operator", "*");
        params.put("product", result);
        //params.put("message", message);

        return new ModelAndView("hello",params);
    }
}

