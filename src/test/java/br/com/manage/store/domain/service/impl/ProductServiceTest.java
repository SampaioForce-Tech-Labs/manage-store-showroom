package br.com.manage.store.domain.service.impl;

import br.com.manage.store.application.api.request.ProductRequest;
import br.com.manage.store.application.api.response.ProductResponse;
import br.com.manage.store.domain.entity.ProductEntity;
import br.com.manage.store.domain.mapper.GenericMapper;
import br.com.manage.store.infrastructure.component.ProductExists;
import br.com.manage.store.infrastructure.handler.exceptions.ConflictException;
import br.com.manage.store.infrastructure.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private GenericMapper mapper;

    @Mock
    private ProductExists productExists;

    @InjectMocks
    private ProductService productService;

    private ProductEntity productEntity;
    private ProductRequest productRequest;
    private ProductResponse productResponse;
    private ProductEntity savedEntity;
    private List<ProductEntity> entityList = new ArrayList<>();

    @Mock
    private Specification<ProductEntity> specification;

    @BeforeEach
    void setUp() {
        initPojo();
    }

    @Nested
    class createProduct {

        @Test
        @DisplayName("Should create a product with success")
        void createProductWithSuccess() {
            Mockito.doNothing().when(productExists).verifyConflictProduct(anyString());
            Mockito.when(mapper.map(productRequest, ProductEntity.class)).thenReturn(productEntity);
            Mockito.when(productRepository.save(productEntity)).thenReturn(productEntity);
            Mockito.when(mapper.map(productEntity, ProductResponse.class)).thenReturn(productResponse);

            // Act (Chamada do método real)
            ProductResponse response = productService.create(productRequest);

            // Assert (Verificações)
            assertNotNull(response);
            assertEquals("4454", response.getCode());
            assertEquals("Camiseta", response.getName());
            verify(productExists).verifyConflictProduct(anyString());
            verify(productRepository).save(any(ProductEntity.class));
        }

        @Test
        @DisplayName("")
        void verifyConflictProduct_ShouldThrowException() {
            // Arrange
            String conflictMessage = "Product already exists";
            Mockito.doThrow(new ConflictException(conflictMessage))
                    .when(productExists).verifyConflictProduct(anyString());

            // Act & Assert
            ConflictException exception = Assertions.assertThrows(ConflictException.class,
                    () -> productService.create(productRequest));

            Assertions.assertEquals(conflictMessage, exception.getMessage());
            Mockito.verify(productExists).verifyConflictProduct(anyString());
            Mockito.verify(productRepository, never()).save(any(ProductEntity.class));
        }
    }

    void initPojo() {

        productRequest = new ProductRequest("4454", "Camiseta", 10.0, new BigDecimal("59.90"), 20, "M", "Camiseta de algodão premium", "Roupas", "Masculino");

        entityList.add(new ProductEntity(1L, "4454", "Camiseta", new BigDecimal(150.00), 10.0, new BigDecimal(600.00), 20, "M", "Camiseta de algodão premium", "Roupas", "Masculino", LocalDateTime.now()));

        productEntity = new ProductEntity(1L, "4454", "Camiseta", new BigDecimal(10.00), 10.0, new BigDecimal(100.00), 20, "M", "Camiseta de algodão premium", "Roupas", "Masculino", LocalDateTime.now());

        savedEntity = new ProductEntity(1L, "4454", "Camiseta", new BigDecimal(150.00), 10.0, new BigDecimal(600.00), 20, "M", "Camiseta de algodão premium", "Roupas", "Masculino", LocalDateTime.now());

        productResponse = new ProductResponse(1L, "4454", "Camiseta", new BigDecimal(150.00), 10.0, new BigDecimal(600.00), 20, "M", "Camiseta de algodão premium", "Roupas", "Masculino", LocalDateTime.now());
    }

}