package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * An implementation of the Translator interface which reads in the translation
 * data from a JSON file. The data is read in once each time an instance of this class is constructed.
 */
public class JSONTranslator implements Translator {

    // Task: pick appropriate instance variables for this class
    public static final int START_INDEX = 3;
    public static final int END_INDEX = 38;
    public static final String THREE_LETTER_CODE_INDEX = "alpha3";
    private final JSONArray jsonArray;

    /**
     * Constructs a JSONTranslator using data from the sample.json resources file.
     */
    public JSONTranslator() {
        this("sample.json");
    }

    /**
     * Constructs a JSONTranslator populated using data from the specified resources file.
     * @param filename the name of the file in resources to load the data from
     * @throws RuntimeException if the resource file can't be loaded properly
     */
    public JSONTranslator(String filename) {
        // read the file to get the data to populate things...
        try {

            String jsonString = Files.readString(Paths.get(getClass().getClassLoader().getResource(filename).toURI()));

            this.jsonArray = new JSONArray(jsonString);

            // Task: use the data in the jsonArray to populate your instance variables
            //            Note: this will likely be one of the most substantial pieces of code you write in this lab.

        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<String> getCountryLanguages(String country) {
        // Task: return an appropriate list of language codes,
        //            but make sure there is no aliasing to a mutable object
        int i = 0;
        int length = jsonArray.length();
        while (i < length) {
            JSONObject countryInfo = jsonArray.getJSONObject(i);
            if (countryInfo.getString(THREE_LETTER_CODE_INDEX).equalsIgnoreCase(country)) {
                Set<String> keys = countryInfo.keySet();
                List<String> keysAsList = new ArrayList<>(keys);
                return keysAsList.subList(START_INDEX, END_INDEX);
            }
            else {
                i++;
            }
        }
        return null;
    }

    @Override
    public List<String> getCountries() {
        // Task: return an appropriate list of country codes,
        //            but make sure there is no aliasing to a mutable object
        int i = 0;
        int length = jsonArray.length();
        List<String> countries = new ArrayList<>();
        while (i < length) {
            countries.add(jsonArray.getJSONObject(i).getString(THREE_LETTER_CODE_INDEX));
            i++;
        }
        return countries;
    }

    @Override
    public String translate(String country, String language) {
        // Task: complete this method using your instance variables as needed
        int i = 0;
        int length = jsonArray.length();
        while (i < length) {
            JSONObject countryInfo = jsonArray.getJSONObject(i);
            if (countryInfo.getString(THREE_LETTER_CODE_INDEX).equalsIgnoreCase(country)) {
                return countryInfo.getString(language);
            }
            else {
                i++;
            }
        }
        return null;
    }
}
