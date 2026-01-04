package nl.cybernetix.demo.items;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class Order {
    private final UUID orderId = UUID.randomUUID();
    private final LocalDateTime createdAt = LocalDateTime.now();
    @NonNull
    private List<String> order;
}