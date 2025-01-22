package istad.codeadvisor.userservice.base;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseError<T> {
    private String code;
    private T description;
}