package com.brekol.jokes.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * User: Breku
 * Date: 02.07.14
 */
@Document(collection = "counters")
public class Counter {
    @Id
    private String id;

    private long seq;

    public Counter(String id, long seq) {
        this.id = id;
        this.seq = seq;
    }

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }
}
