package com.jpmchase.www.modal;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
public class Address {

    @Field(type = FieldType.Text, name = "addressLine1")
    private String addressLine1;
    @Field(type = FieldType.Double, name = "zip")
    private Integer zip;
}
