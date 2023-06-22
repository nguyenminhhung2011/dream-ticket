package com.ticket.server.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class GetListDataResponse<T> {
    final long total;
    final List<T> data;
}
