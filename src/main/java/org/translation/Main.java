package org.translation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for this program.
 * Complete the code according to the "to do" notes.<br/>
 * The system will:<br/>
 * - prompt the user to pick a country name from a list<br/>
 * - prompt the user to pick the language they want it translated to from a list<br/>
 * - output the translation<br/>
 * - at any time, the user can type quit to quit the program<br/>
 */
public class Main {

    public static final String QUIT = "quit";

    /**
     * This is the main entry point of our Translation System!<br/>
     * A class implementing the Translator interface is created and passed into a call to runProgram.
     * @param args not used by the program
     */
    public static void main(String[] args) {

        // Task: once you finish the JSONTranslator,
        //            you can use it here instead of the InLabByHandTranslator
        //            to try out the whole program!
        Translator translator = new JSONTranslator(null);
        // Translator translator = new InLabByHandTranslator();

        runProgram(translator);
    }

    /**
     * This is the method which we will use to test your overall program, since
     * it allows us to pass in whatever translator object that we want!
     * See the class Javadoc for a summary of what the program will do.
     * @param translator the Translator implementation to use in the program
     */
    public static void runProgram(Translator translator) {
        while (true) {
            String country = promptForCountry(translator);
            // CheckStyle: The String "quit" appears 3 times in the file.
            // Checkstyle: String literal expressions should be on the left side of an equals comparison
            if (QUIT.equals(country)) {
                break;
            }
            // Task: Once you switch promptForCountry so that it returns the country
            //            name rather than the 3-letter country code, you will need to
            //            convert it back to its 3-letter country code when calling promptForLanguage
            CountryCodeConverter countryCodeConverter = new CountryCodeConverter();
            String countryCode = countryCodeConverter.fromCountry(country);

            String language = promptForLanguage(translator, countryCode);
            if (QUIT.equals(language)) {
                break;
            }
            // Task: Once you switch promptForLanguage so that it returns the language
            //            name rather than the 2-letter language code, you will need to
            //            convert it back to its 2-letter language code when calling translate.
            //            Note: you should use the actual names in the message printed below though,
            //            since the user will see the displayed message.

            LanguageCodeConverter languageCodeConverter = new LanguageCodeConverter();
            String languageCode = languageCodeConverter.fromLanguage(language);
            System.out.println(country + " in " + language + " is " + translator.translate(countryCode, languageCode));
            System.out.println("Press enter to continue or quit to exit.");
            Scanner s = new Scanner(System.in);
            String textTyped = s.nextLine();

            if (QUIT.equals(textTyped)) {
                break;
            }
        }
    }

    // Note: CheckStyle is configured so that we don't need javadoc for private methods
    private static String promptForCountry(Translator translator) {
        List<String> countries = translator.getCountries();
        // Task: replace the following println call, sort the countries alphabetically,
        //            and print them out; one per line
        //      hint: class Collections provides a static sort method
        // Task: convert the country codes to the actual country names before sorting
        CountryCodeConverter countryCodeConverter = new CountryCodeConverter();
        List<String> countryNameAlphabetical = new ArrayList<>();
        for (String countryCode : countries) {
            String countryName = countryCodeConverter.fromCountryCode(countryCode);
            countryNameAlphabetical.add(countryName);
        }
        Collections.sort(countryNameAlphabetical);
        for (String countryName : countryNameAlphabetical) {
            System.out.println(countryName);
        }

        System.out.println("select a country from above:");

        Scanner s = new Scanner(System.in);
        return s.nextLine();

    }

    // Note: CheckStyle is configured so that we don't need javadoc for private methods
    private static String promptForLanguage(Translator translator, String country) {

        List<String> languageCodes = translator.getCountryLanguages(country);
        // Task: replace the line below so that we sort the languages alphabetically and print them out;
        //  one per line
        // Task: convert the language codes to the actual language names before sorting
        LanguageCodeConverter languageCodeConverter = new LanguageCodeConverter();
        List<String> languageNameAlphabetical = new ArrayList<>();
        for (String languageCode : languageCodes) {
            String languageName = languageCodeConverter.fromLanguageCode(languageCode);
            languageNameAlphabetical.add(languageName);
        }
        Collections.sort(languageNameAlphabetical);
        for (String languageName : languageNameAlphabetical) {
            System.out.println(languageName);
        }

        System.out.println("select a language from above:");

        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }
}
