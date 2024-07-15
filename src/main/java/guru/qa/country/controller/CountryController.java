package guru.qa.country.controller;

import guru.qa.country.model.Country;
import guru.qa.country.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/country")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Country> allCountries() {
        return countryService.allCountries();
    }

    @RequestMapping(method = RequestMethod.GET, params = "code")
    @ResponseStatus(HttpStatus.OK)
    public Country getCountryByCode(@RequestParam("code") String code) {
        return countryService.getCountryByCode(code);
    }

    @RequestMapping(method = RequestMethod.GET, params = "id")
    @ResponseStatus(HttpStatus.OK)
    public Country getCountryById(@RequestParam("id") String id) {
        return countryService.getCountryById(UUID.fromString(id));
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Country addCountry(@RequestBody Country country) {
        return countryService.addCountry(country);
    }

    @PatchMapping("/edit/{code}")
    @ResponseStatus(HttpStatus.OK)
    public Country editCountry(
            @PathVariable String code,
            @RequestBody Country country) {

        return countryService.editCountry(code, country);
    }
}