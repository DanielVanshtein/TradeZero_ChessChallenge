package com.chessGame;

import net.serenitybdd.junit5.SerenityTest;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

@SerenityTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ChessKnightTest {

    static LinkedHashSet<String> listOfPaths = new LinkedHashSet<>();
    Boolean valid = true;

    @BeforeAll
    public static void init() {
        listOfPaths = com.chessGame.ChessKnightMoves.getAllPossibleMoves();
    }


    @DisplayName("Length of all the contents of the linkedHashSet paths is 8")
    @Order(1)
    @Test
    public void testTheLengths() {

        for (String eachPath : listOfPaths) {
            if (eachPath.length() != 8) {
                valid = false;
                break;
            }
        }
        Assertions.assertTrue(valid, "The Test has failed. Path length is not always equal to 8");

    }


    @DisplayName("No null values found in any of the paths in the linkedHashSet")
    @Order(2)
    @Test
    public void testForNull() {
        for (String eachPath : listOfPaths) {
            if (eachPath.contains("null")) {
                valid = false;
                break;
            }
        }
        Assertions.assertTrue(valid, "The Test has failed. A null value was found in the string path");
    }


    @DisplayName("No more than two vowels in the paths")
    @Order(3)
    @Test
    public void testForMoreThanTwoVowels() {
        List<String> vowels = Arrays.asList("A", "E", "I", "O", "U", "Y");

        for (String eachPath : listOfPaths) {
            int count = 0;

            for (String eachLetter : eachPath.split("")) {
                if (vowels.contains(eachLetter)) {
                    count++;
                }
            }
            if (count > 2) {
                valid = false;
                break;
            }
        }
        Assertions.assertTrue(valid, "The Test has failed. A path has more than 2 vowels.");
    }

    @DisplayName("Check for digits, special characters or whitespaces")
    @Order(4)
    @Test
    public void test4() {
        for (String eachPath : listOfPaths) {

            for (char ch : eachPath.toCharArray()) {
                if (!Character.isLetter(ch)) {
                    valid = false;

                    break;
                }

            }
            if (!valid) break;
        }
        Assertions.assertTrue(valid);
    }


}
