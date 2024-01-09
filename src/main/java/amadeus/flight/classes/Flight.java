package amadeus.flight.classes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Flight extends BaseEntity{
    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    private Airport departureAirport;

    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    private Airport arrivalAirport;

    @NonNull
    private String departureTime;

    @NonNull
    private String returnTime;

    private int Price;

    public Flight(@NonNull String departureTime, @NonNull String returnTime) {
        this.departureTime=departureTime;
        this.returnTime=returnTime;
    }
}
