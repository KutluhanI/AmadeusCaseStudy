package amadeus.flight.DTOs;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BaseResponse<T> {
    @ToString.Include
    String message;

    @ToString.Include
    private T payload;
}