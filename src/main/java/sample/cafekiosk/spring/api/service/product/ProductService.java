package sample.cafekiosk.spring.api.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafekiosk.spring.api.service.product.dto.ProductResponseDTO;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductStatusType;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductResponseDTO> getSellableProducts() {
        List<Product> products = productRepository.findAllByStatusIn(ProductStatusType.forDisplay());
        return products.stream().map(ProductResponseDTO::of).collect(Collectors.toList());
    }
}
