package ru.geekbrains.market.tests.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.market.dto.ProductDto;
import ru.geekbrains.market.models.Product;
import ru.geekbrains.market.repositories.ProductRepository;
import ru.geekbrains.market.services.ProductService;

import java.util.Optional;

@SpringBootTest(classes = ProductService.class)
public class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @MockBean
    private ProductRepository productRepository;

    @Test
    public void testFindProductDtoById() {
        Product demoProduct = new Product();
        demoProduct.setTitle("SmthUseful");
        demoProduct.setPrice(5);
        demoProduct.setId(1L);

        Mockito.doReturn(Optional.of(demoProduct)).when(productRepository).findById(1L);

        ProductDto productDto = productService.findProductDtoById(1L).get();
        Mockito.verify(productRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(1L));
        Assertions.assertEquals("SmthUseful", productDto.getTitle());
    }

}

