package com.ecommerce.project.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    @Schema(description = "Id of the category", example = "101")
    private Long categoryId;
    @Schema(description = "Category name for category you wish to create", example = "iPhone")
    private String categoryName;
}
