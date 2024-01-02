package com.ohdogcat.repository;

import com.ohdogcat.dto.product.ProductOptionListDto;
import com.ohdogcat.dto.product.ProductPetTypeDto;
import com.ohdogcat.dto.purchase.OptionOrderDto;
import com.ohdogcat.dto.purchase.OrderParameterDto;
import com.ohdogcat.model.ProductOption;
import com.ohdogcat.model.PurchaseProduct;
import java.util.List;

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
}
