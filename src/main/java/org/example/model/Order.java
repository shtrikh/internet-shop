package org.example.model;

public class Order {
    private int orderId;
    private int userId;
    private int productId;
    private String status;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class Builder {
        private Order newOrder;

        public Builder() {
            newOrder = new Order();
        }


        public Builder withOrderId(int orderId) {
            newOrder.orderId = orderId;
            return this;
        }

        public Builder withUserId(int userId) {
            newOrder.userId = userId;
            return this;
        }

        public Builder withProductId(int productId) {
            newOrder.productId = productId;
            return this;
        }

        public Builder withStatus(String status) {
            newOrder.status = status;
            return this;
        }

        public Order build() {
            return newOrder;
        }

    }
}
