package guru.qa.country.ex;

public class CountryNotFoundException extends RuntimeException{

    public CountryNotFoundException(String message) {
        super(message);
    }
}