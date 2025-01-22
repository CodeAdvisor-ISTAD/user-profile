package istad.codeadvisor.userservice.base;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseResponse<T> {
    private Integer code;
    private T message;

}
