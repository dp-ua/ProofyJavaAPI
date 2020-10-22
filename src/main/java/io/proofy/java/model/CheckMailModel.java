package io.proofy.java.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CheckMailModel {
    public static final String CID_KEY_NAME = "cid";

    private int cid;
}

