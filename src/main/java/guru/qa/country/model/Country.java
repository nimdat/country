package guru.qa.country.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import guru.qa.country.data.CountryEntity;

import java.util.Date;
import java.util.UUID;

public record Country(
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("id")
        UUID id,
        @JsonProperty("name")
        String name,
        @JsonProperty("code")
        String code,
        @JsonProperty("date")
        Date date) {

    public static Country fromEntity(CountryEntity countryEntity) {
        return new Country(
                countryEntity.getId(),
                countryEntity.getName(),
                countryEntity.getCode(),
                countryEntity.getDate()
        );
    }
}