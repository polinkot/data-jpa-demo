package com.example.datajpademo.model;

import lombok.AllArgsConstructor;
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
@Entity
public class City {

    @Id
    @GeneratedValue
    @ColumnDefault(UUID_PK_DEFAULT)
    private UUID id;

    private String name;

    @NotNull
    @Column(name = "country_id", nullable = false, updatable = false)
    private UUID countryId;
}