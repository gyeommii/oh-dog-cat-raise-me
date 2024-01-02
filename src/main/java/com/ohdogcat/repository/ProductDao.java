package com.ohdogcat.repository;



import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ohdogcat.dto.product.ProductListDto;
import com.ohdogcat.dto.product.ProductOptionListDto;
import com.ohdogcat.dto.product.ProductPetTypeDto;
import com.ohdogcat.dto.purchase.OptionOrderDto;
import com.ohdogcat.dto.purchase.OrderParameterDto;
import com.ohdogcat.model.ProductOption;
import com.ohdogcat.model.PurchaseProduct;

public interface ProductDao {

    // 강아지/고양이 전체 신규 등록 순
    List<ProductPetTypeDto> selectOrderByCreateDesc();

    // 강아지 타입 신상품 상위 8개
    List<ProductPetTypeDto> selectDogTypeOrderByCreateDesc();

    // 강아지 타입 베스트 상품 상위 8개
    List<ProductPetTypeDto> selectDogTypeOrderBySoldDesc();

    // 고양이 타입 신상품 상위 8개
    List<ProductPetTypeDto> selectCatTypeOrderByCreateDesc();

    // 고양이 타입 베스트 상품 상위 8개
    List<ProductPetTypeDto> selectCatTypeOrderBySoldDesc();

    ProductPetTypeDto selectByProductPk(long productPk);

    List<ProductOptionListDto> selectOptionByProduckPk(long productPk);

    ProductOption selectOptionByOptionPk(long optionPk);

    List<OptionOrderDto> selectProductInfoForOrderFromCart(OrderParameterDto dto);

    Long updateOptionStock(PurchaseProduct purchaseProduct);

    Long restoreOptionStock(PurchaseProduct purchaseProduct);

    OptionOrderDto selectProductInfoForOrderFromDetail(Long optionList);
	
	// 정렬, 필터 적용
	List<ProductListDto> selectProducts(
			@Param("petType") Long petType,
	        @Param("keyword") String keyword,
	        @Param("minPrice") Long minPrice, // minPrice, maxPrice는 가격 별 옵션 필터.
	        @Param("maxPrice") Long maxPrice,
	        @Param("inStock") Boolean inStock, // 품절상품 여부 확인.
	        @Param("orderBy") String orderBy,
	        @Param("limit") int limit,  // 페이지당 상품 수
	        @Param("offset") int offset // 건너뛸 상품 수
	    );
	
	int countProducts(
			@Param("petType") Long petType, 
            @Param("keyword") String keyword,
            @Param("minPrice") Long minPrice, 
            @Param("maxPrice") Long maxPrice,
            @Param("inStock") Boolean inStock
            );
	
}
