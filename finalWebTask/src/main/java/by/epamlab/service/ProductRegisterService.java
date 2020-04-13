package by.epamlab.service;

import by.epamlab.constants.Constants;
import by.epamlab.dto.ProductDto;
import by.epamlab.model.beans.Product;
import by.epamlab.repositories.ProductRepository;
import by.epamlab.storage.StorageService;
import by.epamlab.validation.product.ProductExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service("productRegisterService")
public class ProductRegisterService implements ProductService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProductRegisterService.class);

    @Autowired
    private StorageService storageService;

    @Autowired
    private ProductRepository repository;

    @Autowired
    private MessageSource messageSource;


    @Transactional
    @Override
    public Product createProduct(ProductDto productDto) throws ProductExistsException {
        if(productNameExists(productDto.getName())){
            throw new ProductExistsException(messageSource.getMessage("UniqueProductName.product.name", null, Locale.ENGLISH));
        }
        Product product = initProduct(productDto);
        if (!productDto.getImage().getOriginalFilename().isEmpty()){
            storageService.store(productDto.getImage(), productDto.getCategory());
        } else {
            LOGGER.info(Constants.EMPTY_FILE_STORING_ATTEMPT);
            product.setImage(Constants.NO_PRODUCT_IMAGE);
        }
        return repository.add(product);
    }

    @Override
    public Product updateProduct(ProductDto productDto, int productId) throws ProductExistsException {
        if(findProduct(productId) == null){
            throw new ProductExistsException(messageSource.getMessage("UpdatedProductId.product.name", null, Locale.ENGLISH));
        }
        Product product = initProduct(productDto);
        product.setId(productId);
        if (!productDto.getImage().getOriginalFilename().isEmpty()){
            storageService.store(productDto.getImage(), productDto.getCategory());
        } else {
            LOGGER.info(Constants.EMPTY_FILE_STORING_ATTEMPT + " id = " + productId);
            product.setImage(Constants.NO_PRODUCT_IMAGE);
        }
        return repository.update(product);
    }

    @Override
    public Product findProduct(Integer productId){
        return repository.findOne(productId);
    }

    @Override
    public List<Product> findProductsByCategory(String category){
        System.out.println("category = " + category);
        if (category == null || category.isEmpty()){
            return repository.findAll();
        } else {
            return repository.findAllByCategory(category);
        }
    }

    @Override
    public List<Product> findProductsByCategoryAndPage(String category, int limit, int offset){
        System.out.println("category = " + category);
        if (category == null || category.isEmpty()){
            return repository.findAllByPage(limit, offset);
        } else {
            return repository.findAllByCategoryAndPage(category, limit, offset);
        }
    }

    @Override
    public long getTotalProductsNumber(String category) {
        return  repository.count(category);
    }

    @Override
    public void deleteProduct(Integer productId) {
        Product product = findProduct(productId);
        repository.delete(product);
    }

    private Product initProduct(ProductDto productDto){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImage(productDto.getImage().getOriginalFilename());
        product.setCategory(productDto.getCategory());
        product.setQuantity(productDto.getQuantity());
        return product;
    }

    private boolean productNameExists(String name){
        Product productByName = repository.getProductByName(name);
        boolean rez = false;
        if(productByName != null){
            rez = true;
        }
        return rez;
    }


}
