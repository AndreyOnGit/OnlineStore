package ru.geekbrains.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.dto.ProductDto;
import ru.geekbrains.market.exceptions_handling.ResourceNotFoundException;
import ru.geekbrains.market.models.Product;
import ru.geekbrains.market.repositories.ProductRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<ProductDto> findProductDtoById(Long id) {
        return Optional.of(new ProductDto(productRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No product's been found with id " + id + "."))));
    }

    public ProductDto saveOrUpdate(Product product) {
        return new ProductDto(productRepository.save(product));
    }


    public Page<ProductDto> findAll(Specification<Product> spec, int page, int pageSize) {
        return productRepository.findAll(spec, PageRequest.of(page - 1, pageSize)).map(ProductDto::new);
    }

}
