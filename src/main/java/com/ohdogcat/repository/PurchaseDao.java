package com.ohdogcat.repository;

import com.ohdogcat.model.Payment;
import com.ohdogcat.model.Purchase;
import com.ohdogcat.model.PurchaseProduct;

public interface PurchaseDao {

    Long insertPurchase(Purchase purchase);

    Long insertPurchasedProduct(PurchaseProduct purchasedProduct);

    Long insertPayment(Payment payment);
}
