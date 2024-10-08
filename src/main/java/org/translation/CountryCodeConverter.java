package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class provides the service of converting country codes to their names.
 */
public class CountryCodeConverter {

    // Task: pick appropriate instance variable(s) to store the data necessary for this class
    private Map<String, String> countryCodesMap = new HashMap<>();

    /**
     * Default constructor which will load the country codes from "country-codes.txt"
     * in the resources folder.
     */
    public CountryCodeConverter() {
        this("country-codes.txt");
    }

    /**
     * Overloaded constructor which allows us to specify the filename to load the country code data from.
     * @param filename the name of the file in the resources folder to load the data from
     * @throws RuntimeException if the resource file can't be loaded properly
     */
    public CountryCodeConverter(String filename) {

        try {
            List<String> lines = Files.readAllLines(Paths.get(getClass()
                    .getClassLoader().getResource(filename).toURI()));

            // Task: use lines to populate the instance variable(s)
            for (String line : lines.subList(1, lines.size())) {
                String[] countryAndCode = line.split("\\s+");
                countryCodesMap.put(countryAndCode[0], countryAndCode[2]);
            }
        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }

    }

    /**
     * Returns the name of the country for the given country code.
     * @param code the 3-letter code of the country
     * @return the name of the country corresponding to the code
     */
    public String fromCountryCode(String code) {
        // Task: update this code to use an instance variable to return the correct value
        for (String countryName : countryCodesMap.keySet()) {
            if (countryCodesMap.get(countryName).equalsIgnoreCase(code)) {
                return countryName;
            }
        }
        return null;
    }

    /**
     * Returns the code of the country for the given country name.
     * @param country the name of the country
     * @return the 3-letter code of the country
     */
    public String fromCountry(String country) {
        // Task: update this code to use an instance variable to return the correct value
        for (String countryName : countryCodesMap.keySet()) {
            if (countryName.equalsIgnoreCase(country)) {
                return countryCodesMap.get(countryName);
            }
        }
        return country;
    }

    /**
     * Returns how many countries are included in this code converter.
     * @return how many countries are included in this code converter.
     */
    public int getNumCountries() {
        // Task: update this code to use an instance variable to return the correct value
        return countryCodesMap.size();
    }
}
