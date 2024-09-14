package edu.br.infnet.mstaskhistory.dto;

import edu.br.infnet.mstaskhistory.model.ChangeType;

import java.time.LocalDateTime;

public record TaskHistoryDto(
        Long taskId,
        Long userId,
        String changedBy,
        LocalDateTime changeTimestamp,
        String oldValue,
        String newValue
) {}
