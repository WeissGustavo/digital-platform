/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.com.banking.digital_platform.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author gusta
 */
@Getter
@AllArgsConstructor
public enum AccountType {
    CHECKING(1, "Checking"),
    SAVINGS(2, "Savings"),
    JOINT(3, "Joint"),
    SALARY(4, "Salary"),
    CORPORATE(5, "Corporate"),
    STUDENT(6, "Student");

    private final Integer value;
    private final String name;

    public AccountType fromValue(Integer value) {
        for (AccountType a : values()) {
            if (a.value.equals(value)) {
                return a;
            }
        }
        return null;
    }
}
