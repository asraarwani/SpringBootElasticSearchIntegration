package com.jpmchase.www.modal;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.List;

@Document(indexName = "asraarindex1")
@Data
public class Employee {

    @Id
    private Integer id;
    @Field(type = FieldType.Text, name = "firstName")
    private String firstName;
    @Field(type = FieldType.Text, name = "lastName")
    private String lastName;
    @Field(type = FieldType.Text, name = "email")
    private String email;
    @Field(type = FieldType.Integer, name = "age")
    private Integer age;
    @Field(type = FieldType.Text, name = "addresses")
    List<Address> addresses;
    @Field(type = FieldType.Date, name = "joiningDate",
            format = DateFormat.date_hour_minute_second_fraction,
            pattern = "uuuu-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime joiningDate;
    @Field(type = FieldType.Text, name = "gender")
    private String gender;

}
