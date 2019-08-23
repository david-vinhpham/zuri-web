package com.ocha.boc.entity;

import com.ocha.boc.base.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = MoHinhKinhDoanh.COLLECTION_NAME)
public class MoHinhKinhDoanh extends AbstractEntity {

    public static final String COLLECTION_NAME = "mohinhkinhdoanh";

    private String name;
}
