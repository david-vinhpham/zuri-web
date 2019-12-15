package com.ocha.boc.entity;

import com.ocha.boc.base.AbstractEntity;
import com.ocha.boc.enums.EmployeeRole;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Builder
@Document(collection = Employee.COLLECTION_NAME)
public class Employee extends AbstractEntity implements Serializable {

    public static final String COLLECTION_NAME = "employee";

    private String restaurantId;

    private String username;

    private String password;

    private String fullName;

    private EmployeeRole employeeRole;

}
