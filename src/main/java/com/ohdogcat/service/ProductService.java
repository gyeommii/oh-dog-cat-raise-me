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
        return productDao.selectProducts("", petType, null, null, null, orderBy);
    }

	
	public List<ProductListDto> getFilteredProducts(String keyword, Long petType, Long minPrice, Long maxPrice, Boolean inStock, String orderBy) {
        return productDao.selectProducts(keyword, petType, minPrice, maxPrice, inStock, orderBy);
    }
	
//	public List<ProductListDto> sortProducts(Long petType, String orderBy) {
//	    // 정렬 로직 구현
//	    return productDao.sortProductsByPetType(petType, orderBy);
//	}
	
	
	
	// 상품
	
	
//	public List<ProductListItemDto> readNewProducts() {
//        log.debug("readNewProducts");
//        List<ProductPetTypeDto> list = productDao.selectByCreatedDesc();
//        return convertToProductListItemDto(list);
//    }
//
//    public List<ProductListItemDto> readBestSellers() {
//        log.debug("readBestSellers");
//        List<ProductPetTypeDto> list = productDao.selectByBestSellerDesc();
//        return convertToProductListItemDto(list);
//    }
//
//    public List<ProductListItemDto> readLowestPrice() {
//        log.debug("readLowestPrice");
//        List<ProductPetTypeDto> list = productDao.selectByLowestPrice();
//        return convertToProductListItemDto(list);
//    }
//
//    public List<ProductListItemDto> readHighestPrice() {
//        log.debug("readHighestPrice");
//        List<ProductPetTypeDto> list = productDao.selectByHighestPrice();
//        return convertToProductListItemDto(list);
//    }
    
//	//@쓰는거@
//    public SortedProductData getSortedProducts(String sortType, String petType) {
//        List<ProductPetTypeDto> sortedList;
//        if ("dog".equals(petType)) {
//            sortedList = getDogSortedList(sortType);
//        } else if ("cat".equals(petType)) {
//            sortedList = getCatSortedList(sortType);
//        } else {
//            sortedList = getSortedList(sortType);
//        }
//        return SortedProductData.fromList(sortedList);
//    }
    
//    // ProductPetTypeDto 리스트를 ProductListItemDto 리스트로 변환
//    private List<ProductListItemDto> convertToProductListItemDto(List<ProductPetTypeDto> productList) {
//        return productList.stream()
//                          .map(p -> new ProductListItemDto(
//                              p.getProductPk(),
//                              p.getProductName(),
//                              p.getCategoryFk(),
//                              p.getImgUrl(),
//                              p.getMinPrice(),
//                              p.getSold(),
//                              p.getCreateDate()
//                          ))
//                          .collect(Collectors.toList());
//    }
//	
//	public SortedProductData getSortedProducts(String sortType, String petType) {
//        List<ProductPetTypeDto> sortedList;
//        if ("dog".equals(petType)) {
//            sortedList = getDogSortedList(sortType);
//        } else if ("cat".equals(petType)) {
//            sortedList = getCatSortedList(sortType);
//        } else {
//            List<ProductPetTypeDto> productList = getSortedList(sortType);
//            sortedList = productList.stream()
//                                    .map(this::convertProductToDto)
//                                    .collect(Collectors.toList());
//        }
//        return SortedProductData.fromList(sortedList);
//    }
//	// Product를 ProductPetTypeDto로 변환
//    private ProductPetTypeDto convertProductToDto(Product product) {
//        return ProductPetTypeDto.builder()
//                .productPk(product.getProductPk())
//                .productName(product.getProductName())
//                .categoryFk(product.getCategoryFk())
//                .imgUrl(product.getImgUrl())
//                .minPrice(product.getMinPrice())
//                .sold(product.getSold())
//                .createDate(product.getCreateDate())
////                .petType(product.getPetType())
//                .build();
//    }
	
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
		return productDao.selectProducts(null, petType, minPrice, maxPrice, inStock, orderBy);
	}

}