package com.project.noonee.domain.product;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductImgFile {

	private int id;
    private int product_code;
    private String origin_name;
    private String temp_name;
    private LocalDateTime create_date;
    private LocalDateTime update_date;
}
