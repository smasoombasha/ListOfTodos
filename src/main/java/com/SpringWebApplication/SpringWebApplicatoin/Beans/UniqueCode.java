package com.SpringWebApplication.SpringWebApplicatoin.Beans;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
@Component
public class UniqueCode {
    private int code;
}
