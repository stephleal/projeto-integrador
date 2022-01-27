package com.w4.projetoIntegrador.entities;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Inbound {
    private Long id;
    private LocalDateTime date;
    private Section section;
    private List<Batch> batchList;
}
