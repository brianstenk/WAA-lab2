package mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ProductController {
    @PostMapping("/add")
    public ModelAndView add(HttpSession session, @ModelAttribute("product") Product product) {
        Map<String, Object> params = new HashMap<>();
        if (product != null) {
            //get the productlist from the session
            Map<String, Product> productList = (Map<String, Product>) session.getAttribute("productList");
            //if there is no productList in the session, create one.
            if (productList == null) {
                productList = new HashMap<String, Product>();
                session.setAttribute("productList", productList);
            }
            //add the product to the productList
            productList.put(product.getNumber(), product);
            params.put("productList", productList.values());
        }
        return new ModelAndView("result", params);
    }

    @GetMapping("/products")
    public ModelAndView init(HttpSession session) {
        //get the productList from the session
        Map<String, Product> productList = (Map<String, Product>) session.getAttribute("productList");
        //if there is no productList in the session, create one.
        if (productList == null) {
            productList = new HashMap<String, Product>();
            session.setAttribute("productList", productList);
        }
        Map<String, Object> params = new HashMap<>();
        params.put("productList", productList.values());
        return new ModelAndView("result", params);
    }

    @PostMapping("/addProduct")
    public ModelAndView addProduct(HttpSession session) {
        Map<String, Object> params = new HashMap<>();
        params.put("product", new Product());
        return new ModelAndView("entry", params);
    }

    @PostMapping("/removeProduct")
    public ModelAndView removeProduct(@RequestParam("number") String number, HttpSession session) {
        Map<String, Object> params = new HashMap<>();
        if (number != null) {
            //get the productList from the session
            Map<String, Product> productList = (Map<String, Product>) session.getAttribute("productList");
            //if there is no productList in the session, create one.
            if (productList == null) {
                productList = new HashMap<String, Product>();
                session.setAttribute("productList", productList);
            }
            //remove the Product to the productList
            productList.remove(number);
            params.put("productList", productList.values());
        }
        return new ModelAndView("result", params);
    }

}
