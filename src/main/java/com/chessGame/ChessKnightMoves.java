package com.chessGame;

import java.util.*;

public class ChessKnightMoves {

    private static final List<String> vowels = Arrays.asList("A", "E", "I", "O", "U", "Y");
    private static final Random r = new Random();
    private static final String[][] board = new String[][]{
            {"A", "B", "C", null, "E"},
            {null, "G", "H", "I", "J"},
            {"K", "L", "M", "N", "O"},
            {"P", "Q", "R", "S", "T"},
            {"U", "V", null, null, "Y"}
    };

    public static void main(String[] args) {

        LinkedHashSet<String> result = getMoves();

        System.out.println("result = " + result);
        System.out.println("result.size() = " + result.size());

    }

    public static LinkedHashSet<String> getMoves() {
        ArrayList<ArrayList<int[]>> positionsLevels = new ArrayList<>();
        LinkedHashSet<String> result = new LinkedHashSet<>();

        int[] startPosition = getRandomPosition();
        positionsLevels.add(getPossiblePositions(startPosition));
        while (positionsLevels.get(0).size() != 0) { // first level
            int[] newPosition = positionsLevels.get(0).get(0);
            positionsLevels.add(getPossiblePositions(newPosition));
            while (positionsLevels.get(1).size() != 0) { // second level
                newPosition = positionsLevels.get(1).get(0);
                positionsLevels.add(getPossiblePositions(newPosition));
                while (positionsLevels.get(2).size() != 0) { // third level
                    newPosition = positionsLevels.get(2).get(0);
                    positionsLevels.add(getPossiblePositions(newPosition));
                    while (positionsLevels.get(3).size() != 0) { // fourth level
                        newPosition = positionsLevels.get(3).get(0);
                        positionsLevels.add(getPossiblePositions(newPosition));
                        while (positionsLevels.get(4).size() != 0) { // fifth level
                            newPosition = positionsLevels.get(4).get(0);
                            positionsLevels.add(getPossiblePositions(newPosition));
                            while (positionsLevels.get(5).size() != 0) { // sixth level
                                newPosition = positionsLevels.get(5).get(0);
                                positionsLevels.add(getPossiblePositions(newPosition));
                                while (positionsLevels.get(6).size() != 0) { // eighth last level
                                    StringBuilder combination = new StringBuilder(board[startPosition[0]][startPosition[1]]);
                                    for (ArrayList<int[]> positionsLevel : positionsLevels) {
                                        int y = positionsLevel.get(0)[0];
                                        int x = positionsLevel.get(0)[1];
                                        combination.append(board[y][x]);
                                    }
                                    result.add(combination.toString());
                                    positionsLevels.get(6).remove(0);
                                }
                                if (positionsLevels.get(6).size() == 0) positionsLevels.remove(6);
                                positionsLevels.get(5).remove(0);
                            }
                            if (positionsLevels.get(5).size() == 0) positionsLevels.remove(5);
                            positionsLevels.get(4).remove(0);
                        }
                        if (positionsLevels.get(4).size() == 0) positionsLevels.remove(4);
                        positionsLevels.get(3).remove(0);
                    }
                    if (positionsLevels.get(3).size() == 0) positionsLevels.remove(3);
                    positionsLevels.get(2).remove(0);
                }
                if (positionsLevels.get(2).size() == 0) positionsLevels.remove(2);
                positionsLevels.get(1).remove(0);
            }
            if (positionsLevels.get(1).size() == 0) positionsLevels.remove(1);
            positionsLevels.get(0).remove(0);
        }
        if (positionsLevels.get(0).size() == 0) positionsLevels.remove(0);
        positionsLevels.forEach(p -> {
            System.out.println();
            p.forEach(n -> System.out.print(Arrays.toString(n)));
        });
        result.removeIf(ChessKnightMoves::hasMoreThanTwoVowels);
        return result;
    }

    private static boolean hasMoreThanTwoVowels(String w) {
        List<String> usedVowels = new ArrayList<>();
        for (String s : w.split("")) {
            if (vowels.contains(s)) {
                if (usedVowels.contains(s)) return false;
                if (usedVowels.size() == 2) return true;
                usedVowels.add(s);
            }
        }
        return false;
    }

    private static ArrayList<int[]> getPossiblePositions(int[] givenPosition) {
        int[] Y = {1, -1, 1, -1, 2, -2, 2, -2};
        int[] X = {2, 2, -2, -2, 1, 1, -1, -1};
        ArrayList<int[]> result = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            int y = givenPosition[0] + Y[i];
            int x = givenPosition[1] + X[i];
            if (
                    y >= 0 && y <= 4 &&
                            x >= 0 && x <= 4 &&
                            board[y][x] != null
            ) result.add(new int[]{y, x});
        }
        return result;
    }

    private static int[] getRandomPosition() {
        int y, x;
        do {
            y = r.nextInt(4);
            x = r.nextInt(4);
        } while (board[y][x] == null);
        return new int[]{y, x};
    }

}