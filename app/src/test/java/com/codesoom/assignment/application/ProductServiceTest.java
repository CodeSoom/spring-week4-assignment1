package com.codesoom.assignment.application;

import com.codesoom.assignment.domain.Product;
import com.codesoom.assignment.domain.ProductRepository;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest
@DisplayName("ProductService 테스트")
class ProductServiceTest {
    private ProductService productService;
    private ProductRepository productRepository;

    private final String SETUP_PRODUCT_NAME = "setupName";
    private final String SETUP_PRODUCT_MAKER = "setupMaker";
    private final int SETUP_PRODUCT_PRICE = 100;
    private final String SETUP_PRODUCT_IMAGE = "setupImage";

    private final Long EXISTED_ID = 1L;
    private final Long NOT_EXISTED_ID = 100L;

    private List<Product> products;
    private Product setupProduct;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductService(productRepository);

        products = new ArrayList<>();
        setupProduct = new Product();
        setupProduct.setId(EXISTED_ID);
        setupProduct.setName(SETUP_PRODUCT_NAME);
        setupProduct.setMaker(SETUP_PRODUCT_MAKER);
        setupProduct.setPrice(SETUP_PRODUCT_PRICE);
        setupProduct.setImage(SETUP_PRODUCT_IMAGE);

        products.add(setupProduct);
    }

    @Nested
    @DisplayName("getProducts 메서드는")
    class Describe_getProducts {
        @Test
        @DisplayName("고양이 장난감 목록을 리턴한다")
        void itReturnsListOfProducts() {
            given(productRepository.findAll()).willReturn(new ArrayList<>());

            List<Product> products = productRepository.findAll();

            verify(productRepository).findAll();
        }
    }

    @Nested
    @DisplayName("getProduct 메서드는")
    class Describe_getProduct {
        @Nested
        @DisplayName("만약 저장되어 있는 고양이 장난감의 id가 주어진다면")
        class Context_WithExistedId {
            private final Long givenExistedId = EXISTED_ID;

            @Test
            @DisplayName("주어진 id에 해당하는 고양이 장난감을 리턴한다")
            void itReturnsWithExistedProduct() {
                given(productRepository.findById(givenExistedId)).willReturn(Optional.of(setupProduct));

                Product detailProduct = productService.getProduct(givenExistedId);

                verify(productRepository).findById(givenExistedId);
                Assertions.assertEquals(detailProduct.getId(), EXISTED_ID, "조회하여 리턴 된 고양이 장난감은 id값이 1L이어야 한다");
                Assertions.assertEquals(detailProduct.getName(), SETUP_PRODUCT_NAME, "조회하여 리턴 된 고양이 장난감은 name값이 setupName이어야 한다");
                Assertions.assertEquals(detailProduct.getMaker(), SETUP_PRODUCT_MAKER, "조회하여 리턴 된 고양이 장난감은 maker값이 setupMaker이어야 한다");
                Assertions.assertEquals(detailProduct.getPrice(), SETUP_PRODUCT_PRICE, "조회하여 리턴 된 고양이 장난감은 price값이 100이어야 한다");
                Assertions.assertEquals(detailProduct.getImage(), SETUP_PRODUCT_IMAGE, "조회하여 리턴 된 고양이 장난감은 image값이 setupImage이어야 한다");
            }
        }
    }
}