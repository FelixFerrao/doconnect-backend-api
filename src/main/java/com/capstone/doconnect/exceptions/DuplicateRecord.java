package com.capstone.doconnect.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class DuplicateRecord extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String errorMessage;

}
