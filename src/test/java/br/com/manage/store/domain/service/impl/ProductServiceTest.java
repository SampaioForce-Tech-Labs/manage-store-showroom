package br.com.manage.store.domain.service.impl;

import br.com.manage.store.application.api.request.ProductRequest;
import br.com.manage.store.application.api.response.ProductResponse;
import br.com.manage.store.domain.entity.ProductEntity;
import br.com.manage.store.domain.mapper.GenericMapper;
import br.com.manage.store.infrastructure.handler.exceptions.InvalidArgumentException;
import br.com.manage.store.infrastructure.handler.exceptions.NotFoundException;
import br.com.manage.store.infrastructure.repository.ProductRepository;
import br.com.manage.store.infrastructure.util.VerifyNotNull;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private GenericMapper mapper;

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
            Mockito.when(mapper.map(productRequest, ProductEntity.class)).thenReturn(productEntity);
            Mockito.when(productRepository.save(productEntity)).thenReturn(savedEntity);
            Mockito.when(mapper.map(savedEntity, ProductResponse.class)).thenReturn(productResponse);
            var result = productService.create(productRequest);
            Assertions.assertNotNull(result);
            Assertions.assertEquals(1L, result.getId());
            Assertions.assertEquals(productEntity.getName(), result.getName());
            Assertions.assertEquals(productEntity.getPrice(), result.getPrice());
            Assertions.assertEquals(productEntity.getAmount(), result.getAmount());
            Assertions.assertEquals(productEntity.getSize(), result.getSize());
            Assertions.assertEquals(productEntity.getCategory(), result.getCategory());
            Assertions.assertEquals(productEntity.getDescription(), result.getDescription());
            Assertions.assertEquals(productEntity.getStock(), result.getStock());
            Assertions.assertEquals(ProductResponse.class, result.getClass());
            Mockito.verify(mapper).map(productRequest, ProductEntity.class);
            Mockito.verify(productRepository).save(productEntity);
            Mockito.verify(mapper).map(savedEntity, ProductResponse.class);
            Mockito.verify(productRepository, Mockito.times(1)).save(productEntity);
            Mockito.verify(mapper, Mockito.times(1)).map(savedEntity, ProductResponse.class);
            Mockito.verify(mapper, Mockito.times(1)).map(productRequest, ProductEntity.class);
        }

        @Test
        @DisplayName("Not should create the product whan the object or value is null")
        void notCreateProductAndGenerationExceptionOfVariableNull() {
            try (MockedStatic<VerifyNotNull> mockedStatic = Mockito.mockStatic(VerifyNotNull.class)) {
                mockedStatic.when(() -> VerifyNotNull.notNull(productRequest)).thenThrow(new InvalidArgumentException());

                InvalidArgumentException argumentException = Assertions.assertThrows(InvalidArgumentException.class, () -> {
                    productService.create(productRequest);
                });

                Mockito.verify(productRepository, Mockito.never()).save(productEntity);
            }
        }

        @Test
        @DisplayName("Not create product whan the price is smaller than zero")
        void notCreateProductAndGenerationExceptionPriceSmallerZero() {
            productRequest.setPrice(BigDecimal.valueOf(-30));

            InvalidArgumentException thrown = Assertions.assertThrows(InvalidArgumentException.class, () -> {
                productService.create(productRequest);
            });

            Assertions.assertTrue(thrown.getMessage().contains("PREÇO R$ " + productRequest.getPrice()));

            Mockito.verify(productRepository, Mockito.never()).save(productEntity);
        }
    }

    @Nested
    class findById {

        @Test
        @DisplayName("Should get the product by id")
        void findByIdProductSuccess() {
            Long id = 1L;
            Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(savedEntity));
            Mockito.when(mapper.map(savedEntity, ProductResponse.class)).thenReturn(productResponse);
            var entity = productService.findById(id);
            Assertions.assertNotNull(entity);
            Assertions.assertEquals(id, entity.getId());
            Assertions.assertEquals(entity.getClass(), ProductResponse.class);
            Mockito.verify(productRepository, Mockito.times(1)).findById(Mockito.anyLong());
            Mockito.verify(mapper, Mockito.times(1)).map(savedEntity, ProductResponse.class);
        }

        @Test
        @DisplayName("Generate exception if the value received is null")
        void findByIdExceptionIfValueIsNull() {
            Assertions.assertThrows(InvalidArgumentException.class, () -> {
                productService.findById(null);
            });
            Mockito.verify(productRepository, Mockito.never()).findById(null);
        }
    }

    @Nested
    class deleteProduct {

        @Test
        @DisplayName("Should delete the product with success")
        void deleteProductWithSuccess() {
            Long id = 1L;
            Mockito.when(productRepository.existsById(id)).thenReturn(true);
            productService.delete(id);
            Assertions.assertTrue(id instanceof Long);
            Assertions.assertNotNull(id);
            Mockito.verify(productRepository, Mockito.times(1)).existsById(id);
            Mockito.verify(productRepository, Mockito.times(1)).deleteById(id);
        }

        @Test
        @DisplayName("Generate exception if the value received is null")
        void exceptionDeleteWhaTheIdReceiverIsNull() {
            Assertions.assertThrows(InvalidArgumentException.class, () -> {
                productService.delete(null);
            });
            Mockito.verify(productRepository, Mockito.never()).findById(null);
        }

        @Test
        @DisplayName("Generate exception if the object not is localization in database")
        void exceptionDeleteWhaObjectNotLocalization() {
            Long id = 1L;
            NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> {
                productService.delete(id);
            });
            Assertions.assertEquals(exception.getMessage(), "ID: " + id);
            Mockito.verify(productRepository, Mockito.times(1)).existsById(id);
            Mockito.verify(productRepository, Mockito.never()).deleteById(id);
        }
    }

    @Nested
    class updateProduct {

        @Test
        @DisplayName("Update product with success")
        void updateProductWithSuccess() {
            Long id = 1L;
            Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(savedEntity));
            Mockito.when(mapper.map(productRepository.save(productEntity), ProductResponse.class)).thenReturn(productResponse);
            var find = productService.update(id, productRequest);
            Assertions.assertNotNull(find);
            Assertions.assertNotNull(id);
            Assertions.assertEquals(find.getClass(), ProductResponse.class);
            Mockito.verify(productRepository, Mockito.times(1)).findById(id);
            Mockito.verify(mapper, Mockito.times(1)).map(productRepository.save(productEntity), ProductResponse.class);
        }

        @Test
        @DisplayName("Not should update the product whan the object or value is null")
        void notUpdateProductAndGenerationExceptionOfVariableNull() {
            InvalidArgumentException argumentException = Assertions.assertThrows(InvalidArgumentException.class, () -> {
                productService.update(null, null);
            });
            Mockito.verify(productRepository, Mockito.never()).save(productEntity);
        }

        @Test
        @DisplayName("Generate exception wha the id not is localization in data base")
        void updateExceptionWhaNotIsLocalizationInDataBase() {
            Long id = 1L;
            Mockito.when(productRepository.findById(Mockito.anyLong())).thenThrow(new NotFoundException("ID: " + id));
            NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> {
                productService.update(id, productRequest);
            });
            Assertions.assertEquals(exception.getMessage(), "ID: " + id);
            Mockito.verify(productRepository, Mockito.never()).save(Mockito.any());
            Mockito.verify(productRepository, Mockito.times(1)).findById(id);
        }
    }

    @Nested
    class findAllProducts {

        @Test
        @DisplayName("Should list producst with success")
        void findAllProductsWithSuccess() {
            int page = 10;
            int size = 10;
            Page<ProductEntity> originalPage = new PageImpl<>(entityList, PageRequest.of(page, size), entityList.size());
            Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
            Mockito.when(productRepository.findAll(specification, pageable)).thenReturn(originalPage);
            var list = productService.findAll(specification, page, size);
            Assertions.assertNotNull(list);
            Mockito.verify(productRepository, Mockito.times(1)).findAll(specification, pageable);
        }
    }

    void initPojo() {


        productRequest = new ProductRequest(
                "Iphone 15",
                BigDecimal.valueOf(4500.00),
                9,
                "",
                9,
                "Celular",
                "Eletronicos"
        );

        entityList.add(new ProductEntity(
                1L,
                "Iphone 15",
                BigDecimal.valueOf(4500.00),
                9,
                "",
                9,
                "Celular",
                "Eletronicos",
                LocalDateTime.now()
        ));

        productEntity = new ProductEntity(
                null,
                "Iphone 15",
                BigDecimal.valueOf(4500.00),
                9,
                "",
                9,
                "Celular",
                "Eletronicos",
                LocalDateTime.now()
        );

        savedEntity = new ProductEntity(
                1L,
                "Iphone 15",
                BigDecimal.valueOf(4500.00),
                9,
                "",
                9,
                "Celular",
                "Eletronicos",
                LocalDateTime.now()
        );

        productResponse = new ProductResponse(
                1L,
                "Iphone 15",
                BigDecimal.valueOf(4500.00),
                9,
                "",
                9,
                "Celular",
                "Eletronicos",
                LocalDateTime.now());

    }

}