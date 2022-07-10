package com.company;

import java.util.ArrayList;
import java.util.List;

public class ColourGridSearch {
    private int numberOfColors;
    private int row;
    private int column;
    private int[][] grid;
    String[][] finalArray;

    public ColourGridSearch(int numberOfColors, int row, int column) {
        this.numberOfColors = numberOfColors;
        this.row = row;
        this.column = column;
        createGame(numberOfColors,row,column);
    }

    private void createGame(int numberOfColors, int row, int column){
        grid = new int[row][column];
        finalArray = new String[row][column];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < column; ++j) {
                this.grid[i][j] = (int) (Math.random() * numberOfColors + 1);
            }
        }

        System.out.println();
        System.out.println("* Problem *");
        print(this.grid);
        solution();
        System.out.println();
        System.out.println("* Solution *");
        print(this.finalArray);
    }

    private void solution(){

        int[][] visited = new int[row][column];
        //directions that need to check left right
        int portions[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int maxCount = 0;
        List<List<Integer>> maxBlock = new ArrayList<>();

        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < column; ++j) {
                finalArray[i][j] = "*";
                List<List<Integer>> curBlock = new ArrayList<>();
                if(visited[i][j] == 0){
                    visited[i][j] = 1;
                    int index=0;

                    curBlock.add(tempValues(i,j));

                    while (index < curBlock.size()){
                        int r = curBlock.get(index).get(0);
                        int c = curBlock.get(index).get(1);

                        for (int[] portion : portions) {
                            int newRow = r + portion[0], newCol = c + portion[1];
                            if (newRow >= 0 && newRow < row && newCol >= 0 && newCol < column && visited[newRow][newCol] == 0 &&
                                    grid[newRow][newCol] == grid[r][c]) {
                                visited[newRow][newCol] = 1;
                                curBlock.add(tempValues(newRow,newCol));
                            }
                        }
                        index++;
                    }
                }
                if(curBlock.size() > maxCount) {
                    maxCount = curBlock.size();
                    maxBlock = curBlock;
                }
            }
        }
        for (List<Integer> item: maxBlock) {
            finalArray[item.get(0)][item.get(1)] = String.valueOf(grid[item.get(0)][item.get(1)]);
        }
    }

    private List<Integer> tempValues(int i,int j){
        List<Integer> temporaryValues = new ArrayList<>();
        temporaryValues.add(i);
        temporaryValues.add(j);
        return temporaryValues;
    }

    private void print(int[][] input){
        for(int i = 0; i < this.row; ++i){
            for(int j=0; j < this.column; ++j){
                System.out.print(input[i][j]+" | ");
            }
            System.out.println();
        }
    }

    private void print(String[][] input){
        for(int i = 0; i < this.row; ++i){
            for(int j=0; j < this.column; ++j){
                System.out.print(input[i][j]+" | ");
            }
            System.out.println();
        }
    }

}
