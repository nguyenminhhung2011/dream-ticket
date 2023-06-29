package com.ticket.server.dtos.OverViewDto;

import java.sql.Date;
import java.sql.Timestamp;

public interface OverviewProjection {
    long getTotal();
    Date getDate();
}
