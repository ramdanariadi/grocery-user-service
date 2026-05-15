package id.grocery.tunas.product.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

public class FindAllProductDTO {

    @NoArgsConstructor
    @Data
    public static class Request{
        private int pageSize;
        private int pageIndex;
    }

    @NoArgsConstructor
    @Data
    public static class Response{
        private List<SimpleProductDTO> data;
        private int pageSize;
        private int pageIndex;
        private long totalData;
    }

    @NoArgsConstructor
    @Data
    public static class SimpleProductDTO {
        private UUID id;
        private UUID shopId;
        private String shopName;
        private BigDecimal price;
        private int weight;
        private String category;
        private int perUnit;
        private String description;
        private String imageUrl;
        private String name;
    }
}
