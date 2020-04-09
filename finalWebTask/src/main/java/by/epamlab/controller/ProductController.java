package by.epamlab.controller;

import by.epamlab.dto.ProductDto;
import by.epamlab.model.beans.Product;
import by.epamlab.service.CartService;
import by.epamlab.service.ProductService;
import by.epamlab.validation.product.ProductExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

import static by.epamlab.constants.Constants.*;


@Controller
public class ProductController {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    @Qualifier("productValidator")
    private Validator validator;

    @GetMapping("/main")
    public String getAllProducts(@RequestParam(value = "category", required = false) String category,
                                 Map<String, Object> model){
        Iterable<Product> products = productService.findProductsByCategory(category);
        model.put("products", products);
        model.put("itemQuantity", cartService.getCart().getItemQuantity());
        return "main";
    }

    @GetMapping("/getProductDescription")
    public String getProductWithDescription(@RequestParam(value = "productId", required = true) Integer productId,
                                Model model){
        Product product = productService.findProduct(productId);
        model.addAttribute("product", product);
        model.addAttribute("itemQuantity", cartService.getCart().getItemQuantity());
        return "productDescription";
    }

    @GetMapping("/showProductDescription")
    public String getProductDescription(Model model,
                                @ModelAttribute("product") Object product,
                                @ModelAttribute("itemQuantity") Object itemQuantity){
        model.addAttribute("product", product);
        model.addAttribute("itemQuantity", itemQuantity);
        return "productDescription";
    }

    @GetMapping("/addProduct")
    public String addProduct(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "admin/addProduct";
    }

    @GetMapping("/updateProduct")
    public String updateProductForm(@RequestParam(value = "productId", required = true) Integer productId, Model model){
        Product product = productService.findProduct(productId);
        model.addAttribute("product", product);
        return "admin/editProduct";
    }

    @PostMapping("/addProduct")
    public ModelAndView registerNewProduct
            (@ModelAttribute("product") @Valid ProductDto productDto,
             BindingResult bindingResult,
             WebRequest request,
             Errors errors) {

        validator.validate(productDto, errors);

        Product newProduct = new Product();
        if (!bindingResult.hasErrors()) {
            newProduct = createProduct(productDto);
        }
        if (newProduct == null) {
            bindingResult.rejectValue("name", "message.prodError");
        }
        if (bindingResult.hasErrors()) {
            return new ModelAndView("admin/addProduct", "product", productDto);
        }  else {
            return new ModelAndView("redirect:/main");
        }
    }

    @PostMapping("/updateProduct")
    public ModelAndView changeProductData(@RequestParam(value = "productId", required = true) Integer productId,
                                          @ModelAttribute("product") @Valid ProductDto productDto,
                                          BindingResult bindingResult,
                                          WebRequest request,
                                          Errors errors) {

        validator.validate(productDto, errors);

        Product newProduct = new Product();
        if (!bindingResult.hasErrors()) {
            newProduct = updateProduct(productId, productDto);
        }
        if (newProduct == null) {
            bindingResult.rejectValue("name", "UpdatedProductId.product.name");
        }
        if (bindingResult.hasErrors()) {
            return new ModelAndView("admin/addProduct", "product", productDto);
        } else {
            return new ModelAndView("redirect:/main");
        }
    }

    @PostMapping("/deleteProduct")
    public ModelAndView deleteProduct(@RequestParam(value = "productId", required = true) Integer productId) {
        try {
            productService.deleteProduct(productId);
            LOGGER.info(PRODUCT_DELETED + productId);
        } catch (NumberFormatException e) {
            LOGGER.info(productId + "->" + e.getMessage());
        }
        return new ModelAndView("redirect:/main");
    }

    private Product createProduct(ProductDto productDto) {
        Product product  = null;
        try {
            product = productService.createProduct(productDto);
            LOGGER.info(NEW_PRODUCT_REGISTERED + productDto.getName());
        } catch (ProductExistsException e) {
            return null;
        }
        return product;
    }

    private Product updateProduct(int productId, ProductDto productDto){
        Product updatedProduct  = null;
        try {
            System.out.println(productDto);
            updatedProduct = productService.updateProduct(productDto, productId);
            LOGGER.info(PRODUCT_UPDATED + productId);
        } catch (ProductExistsException e) {
            return null;
        }
        return updatedProduct;
    }



}
