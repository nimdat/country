package guru.qa.country.service;

import guru.qa.country.data.CountryEntity;
import guru.qa.country.data.CountryRepository;
import guru.qa.country.ex.CountryNotFoundException;
import guru.qa.country.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> allCountries() {
        return countryRepository.findAll()
                .stream()
                .map(e -> new Country(
                        e.getId(),
                        e.getName(),
                        e.getCode(),
                        e.getDate()
                ))
                .toList();
    }

    public Country getCountryByCode(String code) {
        CountryEntity countryEntity = countryRepository.findCountryByCode(code).orElseThrow(() ->
                new CountryNotFoundException(String.format("Country with code %s is not found", code)));

        return Country.fromEntity(countryEntity);
    }

    public Country getCountryById(UUID id) {
        CountryEntity countryEntity = countryRepository.findById(id).orElseThrow(() ->
                new CountryNotFoundException(String.format("Country with id %s is not found", id)));

        return Country.fromEntity(countryEntity);
    }

    public Country addCountry(Country country) {
        return Country.fromEntity(countryRepository.save(CountryEntity.fromJson(country)));
    }

    @Transactional
    public Country editCountry(String id, Country country) {
        CountryEntity countryEntity = CountryEntity.fromJson(getCountryById(UUID.fromString((id))));
        countryEntity.setName(country.name());

        return Country.fromEntity(countryRepository.save(countryEntity));
    }
}