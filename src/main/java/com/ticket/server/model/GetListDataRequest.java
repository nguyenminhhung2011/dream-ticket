package com.ticket.server.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class GetListDataRequest {
    private final int perPage;
    private final int page;
    private final String keyword;
}
