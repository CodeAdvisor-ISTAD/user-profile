package istad.codeadvisor.userservice.base;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseErrorResponse {
    private BaseError Error;
}
