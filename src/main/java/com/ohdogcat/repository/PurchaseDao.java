package com.ohdogcat.repository;

import com.ohdogcat.dto.purchase.OptionOrderDto;
import com.ohdogcat.dto.purchase.PurchaseListDto;
import com.ohdogcat.dto.purchase.PurchaseListPagenationDto;
import com.ohdogcat.dto.purchase.PurchaseStatusChangeDto;
import com.ohdogcat.model.Payment;
import com.ohdogcat.model.Purchase;
import com.ohdogcat.model.PurchaseProduct;
import com.ohdogcat.model.PurchaseStatus;
import java.util.List;

public interface PurchaseDao {

    Long insertPurchase(Purchase purchase);

    void insertPurchasedProduct(PurchaseProduct purchasedProduct);

    Long insertPayment(Payment payment);

    Purchase getPurchaseInfo(Long purchasePk);

    List<OptionOrderDto> getProductByPurchasePk(Long purchaseFk);

    Payment retrievePaymentByPurchaseFk(Long purchaseFk);

    PurchaseStatus getPurchaseStatusByPk(Long statusPk);

    List<PurchaseListDto> getMemberPurchaseList(PurchaseListPagenationDto pageInfo);

    Long retrievePayment(Payment payment);

    Long cancelPurchase (PurchaseStatusChangeDto purchaseStatusChangeDto);

    Integer getPurchaseCount (Long memberPk);
}
