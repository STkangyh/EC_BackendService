package ECService.demo.repository.mapping;

import java.time.LocalDateTime;

public interface ListInfoMapping {
    Long getId();
    String getName();
    String getState();
    LocalDateTime getCreateTime();
}