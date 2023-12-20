package com.ohdogcat.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ohdogcat.dto.product.ProductListDto;
import com.ohdogcat.dto.product.ProductListItemDto;
import com.ohdogcat.repository.ProductDao;

import lombok.Builder;
import lombok.Data;

import com.ohdogcat.dto.product.ProductPetTypeDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

	private final ProductDao productDao;
	
	public List<ProductListDto> getProducts(Long petType, String orderBy) {
		log.debug("getProducts()");
        return productDao.selectProducts(petType, "",  null, null, null, orderBy);
    }

	
	public List<ProductListDto> getFilteredProducts(String keyword, Long petType, Long minPrice, Long maxPrice, Boolean inStock, String orderBy) {
        return productDao.selectProducts(petType, keyword, minPrice, maxPrice, inStock, orderBy);
    }
	

	
	// 강아지 상품에 대한 정렬
    private List<ProductPetTypeDto> getDogSortedList(String sortType) {
        switch (sortType) {
            case "new":
                return productDao.selectDogTypeOrderByCreateDescNewest20();
            case "best":
                return productDao.selectDogTypeOrderByCreateDescBestSeller20();
            case "lowest":
                return productDao.selectDogTypeOrderByLowestPrice20();
            case "highest":
            	return productDao.selectDogTypeOrderByHighestPrice20();
            default:
                return productDao.selectDogTypeOrderByCreateDescNewest20();
        }
    }

    // 고양이 상품에 대한 정렬
    private List<ProductPetTypeDto> getCatSortedList(String sortType) {
        switch (sortType) {
            case "new":
                return productDao.selectCatTypeOrderByCreateDescNewest20();
            case "best":
                return productDao.selectCatTypeOrderByCreateDescBestSeller20();
            case "lowest":
                return productDao.selectCatTypeOrderByLowestPrice20();
            case "highest":
                return productDao.selectCatTypeOrderByHighestPrice20();
            default:
                return productDao.selectCatTypeOrderByCreateDescNewest20();
        }
    }


    // ProductPetTypeDto 리스트를 반환
    private List<ProductPetTypeDto> getSortedList(String sortType) {
        switch (sortType) {
        case "new":
            return productDao.selectByCreatedDesc();
        case "best":
            return productDao.selectByBestSellerDesc();
        case "lowest":
            return productDao.selectByLowestPrice();
        case "highest":
            return productDao.selectByHighestPrice();
        default:
            return productDao.selectByCreatedDesc();
        }
    }

	
	
	// Dog
	public List<ProductPetTypeDto> readDogOrderByNew() {
		log.debug("readDogOrderByNew()");
		List<ProductPetTypeDto> list = productDao.selectDogTypeOrderByCreateDesc();
		return list;
	}
	
	public List<ProductPetTypeDto> readDogOrderByNewAll() {
		log.debug("readDogOrderByNewAll()");
		List<ProductPetTypeDto> list = productDao.selectDogTypeAllProduct();
		return list;
	}

	public List<ProductPetTypeDto> readDogOrderByBest() {
		log.debug("readDogOrderByBest()");
		List<ProductPetTypeDto> list = productDao.selectDogTypeOrderBySoldDesc();
		return list;
	}
	
	public List<ProductPetTypeDto> readDogOrderByNewest20() {
		log.debug("readDogOrderByNewest20()");
		List<ProductPetTypeDto> list = productDao.selectDogTypeOrderByCreateDescNewest20();
		return list;
	}
	
	public List<ProductPetTypeDto> readDogOrderByBestSeller20() {
		log.debug("readDogOrderByBestSeller20()");
		List<ProductPetTypeDto> list = productDao.selectDogTypeOrderByCreateDescBestSeller20();
		return list;
	}
	
	
	// Cat
	public List<ProductPetTypeDto> readCatOrderByNew() {
		log.debug("readCatOrderByNew()");
		List<ProductPetTypeDto> list = productDao.selectCatTypeOrderByCreateDesc();
		return list;
	}
	
	public List<ProductPetTypeDto> readCatOrderByNewAll() {
		log.debug("readCatOrderByNewAll()");
		List<ProductPetTypeDto> list = productDao.selectCatTypeAllProduct();
		return list;
	}

	public List<ProductPetTypeDto> readCatOrderByBest() {
		log.debug("readCatOrderByBest()");
		List<ProductPetTypeDto> list = productDao.selectCatTypeOrderBySoldDesc();
		return list;
	}
	
	public List<ProductPetTypeDto> readCatOrderByNewest20() {
		log.debug("readCatOrderByNewest20()");
		List<ProductPetTypeDto> list = productDao.selectCatTypeOrderByCreateDescNewest20();
		return list;
	}
	
	public List<ProductPetTypeDto> readCatOrderByBestSeller20() {
		log.debug("readCatOrderByBestSeller20()");
		List<ProductPetTypeDto> list = productDao.selectCatTypeOrderByCreateDescBestSeller20();
		return list;
	}
	

	@Data
    @Builder
    public static class SortedProductData {
        private List<ProductListItemDto> products;
        private int count;

        public static SortedProductData fromList(List<ProductPetTypeDto> sortedList) {
            List<ProductListItemDto> productListItemDtos = sortedList.stream()
                .map(p -> ProductListItemDto.builder()
                    .productPk(p.getProductPk())
                    .productName(p.getProductName())
                    .categoryFk(p.getCategoryFk())
                    .imgUrl(p.getImgUrl())
                    .minPrice(p.getMinPrice())
                    .sold(p.getSold())
                    .createDate(p.getCreateDate())
                    .build())
                .collect(Collectors.toList());
            return SortedProductData.builder()
                    .products(productListItemDtos)
                    .count(sortedList.size())
                    .build();
        }
    }


	public List<ProductListDto> filterProducts(Long petType, Long minPrice, Long maxPrice, Boolean inStock,
			String orderBy) {
		return productDao.selectProducts(petType, null, minPrice, maxPrice, inStock, orderBy);
	}

}