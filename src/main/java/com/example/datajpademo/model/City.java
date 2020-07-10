package com.example.datajpademo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

import static com.example.datajpademo.config.AppConfig.UUID_PK_DEFAULT;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class City {

    @Id
    @GeneratedValue
    @ColumnDefault(UUID_PK_DEFAULT)
    private UUID id;

    private String name;
    private UUID countryId;

    @ColumnDefault("false")
    private boolean isCapital;
}