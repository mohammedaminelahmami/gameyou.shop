package com.youcode.gameyou.Enum;

public enum OrderStatus {
    // "Pending" - This is the initial status when an order is created but payment has not been received yet.
    PENDING,

    // "Processing" - This status indicates that payment has been received and the order is being prepared for shipping.
    PROCESSING,

    // "Shipped" - This status indicates that the order has been shipped and is on its way to the customer.
    SHIPPED,

    // "Delivered" - This status indicates that the order has been delivered to the customer.
    DELIVERED,

    // "Cancelled" - This status indicates that the order has been cancelled either by the customer or by the seller.
    CANCELLED
}