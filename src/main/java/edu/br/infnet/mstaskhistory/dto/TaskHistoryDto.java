package edu.br.infnet.mstaskhistory.dto;

import edu.br.infnet.mstaskhistory.model.ChangeType;

import java.time.LocalDateTime;

public record TaskHistoryDto(
        Long taskId,
        Long userId,
        ChangeType changeType,
        LocalDateTime changeTimestamp,
        String oldValue,
        String newValue
) {}
