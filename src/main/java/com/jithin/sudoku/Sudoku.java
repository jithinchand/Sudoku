package com.jithin.sudoku;

import static java.lang.Math.sqrt;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple Sudoku Generator.
 * <p>
 * This generates a simple 4*4 sudoku. The size ({@link GRID_SIZE}}) of the sudoku grid
 * can be easily increased or decreased with no impact on the code.
 * <p>
 * Currently, the generated sudoku has 5 clues ({@link NUM_OF_PREFILLED_CELLS}).
 * This also can be increased or decreased with no impact
 * on the code.
 * <p>
 * The generated sudoku is printed on the console.
 * 
 * @author Jithinchand
 * @version 1.0
 * @since 1.0
 */
public class Sudoku 
{
	/**
	 * Sudoku grid size. This can be increased with no impact on the code.
	 */
	private static final int GRID_SIZE = 4;
	private static final int INNER_GRID_SIZE = (int) sqrt(GRID_SIZE);
	private static final int INDEX_OF_FIRST_CELL = 0;
	private static final int INDEX_OF_LAST_CELL = GRID_SIZE - 1;
	private static final int MIN_NUM_IN_GRID = 1;
	private static final int MAX_NUM_IN_GRID = GRID_SIZE;
	
	/**
	 * Number of starting values. This can be increased with no impact on the code.
	 */
	private static final int NUM_OF_PREFILLED_CELLS = 5;
	
	/**
	 * Holds the final sudoku.
	 */
	private static int[][] mySudoku = new int[GRID_SIZE][GRID_SIZE];
	
	/**
	 * Holds the sudoku solution.
	 */
	private static int[][] mySudokuValueHolder = new int[GRID_SIZE][GRID_SIZE];
	
	/**
	 * Holds the positions of the clues.
	 */
	private static boolean[][] mySudokuBooleanValues = new boolean[GRID_SIZE][GRID_SIZE];
	
	/**
	 * Starting point of the generator.
	 * <p>
	 * First, the app fills {@link mySudoku} with a randomly selected row or coloumn.
	 * Then it copies those values to {@link mySudokuValueHolder}. {@link mySudokuValueHolder}
	 * is then filled with valid values. Later, {@link mySudokuBooleanValues} is populated
	 * with true for the clues.
	 * <p>
	 * Finally, {@link mySudoku} is populated with values in {@link mySudokuValueHolder} based
	 * on values in {@link mySudokuBooleanValues}.
	 * 
	 * @param args command line arguments, which not used.
	 */
    public static void main( String[] args )
    {
    	createIntialRowWithRandomValues();
        copyInitailValues();
    	fillSudokuWithValidValues(INDEX_OF_FIRST_CELL, INDEX_OF_FIRST_CELL, true, mySudokuValueHolder, mySudoku.clone());
        createStartingCells();
        createNewSudoku();
        print(mySudoku);
    }

	/**
	 * This populates a randomly selected row or coloumn with valid values in {@link mySudoku}.
	 */
    public static void createIntialRowWithRandomValues()
    {
    	Random random = new Random();
    	int rowOrCol = random.nextInt(2); //0 or 1
    	int randomRowOrCol = random.nextInt(INDEX_OF_LAST_CELL - INDEX_OF_FIRST_CELL + 1);
    	List<Integer> randomRowOrColValues = new ArrayList<Integer>();
        for(int i = MIN_NUM_IN_GRID; i <= GRID_SIZE; i++)
        {
        	randomRowOrColValues.add(i);
        }
        Collections.shuffle(randomRowOrColValues);
        for(int i = INDEX_OF_FIRST_CELL; i < GRID_SIZE; i++)
        {
        	if (rowOrCol == 1)
        		mySudoku[randomRowOrCol][i] = randomRowOrColValues.get(i);
        	else
        		mySudoku[i][randomRowOrCol] = randomRowOrColValues.get(i);
        }
    }

	/**
	 * This copies {@link mySudoku} to {@link mySudokuValueHolder}.
	 */
    public static void copyInitailValues()
    {
        for(int i = INDEX_OF_FIRST_CELL; i < GRID_SIZE; i++)
        {
            for(int j = INDEX_OF_FIRST_CELL; j < GRID_SIZE; j++){
            	mySudokuValueHolder[i][j] = mySudoku[i][j];
            }
        }
    }
    
	/**
	 * This generates the final sudoku.
	 */
    public static void createNewSudoku()
    {
    	mySudoku = new int[GRID_SIZE][GRID_SIZE];
    	for(int i = INDEX_OF_FIRST_CELL; i < GRID_SIZE; i++)
    	{
    		for(int j = INDEX_OF_FIRST_CELL; j < GRID_SIZE; j++)
    		{
    			if(mySudokuBooleanValues[i][j])
    			{
    				mySudoku[i][j] = mySudokuValueHolder[i][j];
    			}
    		}
    	}
    }
    
	/**
	 * This generates the positions of the clues.
	 */
    public static void createStartingCells()
    {
    	Random random = new Random();
    	for(int i = MIN_NUM_IN_GRID; i <= NUM_OF_PREFILLED_CELLS; i++)
    	{
    		int startingRowToHoldValue = random.nextInt(INDEX_OF_LAST_CELL - INDEX_OF_FIRST_CELL + 1);
    		int startingColToHoldValue = random.nextInt(INDEX_OF_LAST_CELL - INDEX_OF_FIRST_CELL + 1);
    		
    		if(!mySudokuBooleanValues[startingRowToHoldValue][startingColToHoldValue])
    			mySudokuBooleanValues[startingRowToHoldValue][startingColToHoldValue] = true;
    		else
    			i--;
    	}
    }
    
	/**
	 * This solves the sudoku.
	 * 
	 * @param currentRow current row of the cell
	 * @param currentCol current col of the cell
	 * @param nextCell   true to check next cell, false to check previous cell again
	 * @param grid       contains final solution
	 * @param holder     contains valid values in randomly selected row or col
	 * @return           returns the final solution of the sudoku
	 */
    public static int[][] fillSudokuWithValidValues(int currentRow, int currentCol, boolean nextCell, int[][] grid, final int[][] holder)
    {
        int validRow = INDEX_OF_LAST_CELL;
        int validCol = INDEX_OF_LAST_CELL;
        int flag = 0;
        
        for(int i = INDEX_OF_LAST_CELL; i >= INDEX_OF_FIRST_CELL; i--)
        {
            for(int j = INDEX_OF_LAST_CELL; j >= INDEX_OF_FIRST_CELL; j--)
            {
                if(holder[i][j] == 0)
                {
                	validRow = i; 
                	validCol = j; 
                    flag = 1;
                    break;
                }
            }
            if(flag == 1) break;
        }
        
        while(!validValue(validRow, validCol, grid) || grid[validRow][validCol] == 0)
        {
            if(holder[currentRow][currentCol]!=0)
            {
                if(nextCell == true)
                	nextCell(currentRow, currentCol, nextCell, grid, holder);
                else
                    previousCell(currentRow, currentCol, nextCell, grid, holder);
            } 
            else {
                if(grid[currentRow][currentCol] < MAX_NUM_IN_GRID)
                {
                    grid[currentRow][currentCol]++; 
                    if(validValue(currentRow, currentCol, grid)) 
                    	nextCell(currentRow, currentCol, nextCell, grid, holder);
                } 
                else
                {
                    grid[currentRow][currentCol]=0;
                    nextCell = false;
                    break;
                }
            }
        }
        return grid;
    }
    
	/**
	 * This sets row/col of the next cell.
	 * 
	 * @param currentRow current row of the cell
	 * @param currentCol current col of the cell
	 * @param nextCell   true to check next cell, false to check previous cell again
	 * @param grid       contains final solution
	 * @param holder     contains valid values in randomly selected row or col
	 */
    private static void nextCell(int currentRow, int currentCol, boolean nextCell, int[][] grid, final int[][] holder)
    {
        int nextRow;
        int nextCol;
        if(currentCol == INDEX_OF_LAST_CELL)
        {
            nextRow = currentRow + 1;
            nextCol = 0;
        } 
        else
        {
            nextRow = currentRow; 
            nextCol = currentCol + 1;
        }
        nextCell = true;
        fillSudokuWithValidValues(nextRow, nextCol, nextCell, grid, holder);
    }
    
	/**
	 * This sets row/col of the previous cell.
	 * 
	 * @param currentRow current row of the cell
	 * @param currentCol current col of the cell
	 * @param nextCell   true to check next cell, false to check previous cell again
	 * @param grid       contains final solution
	 * @param holder     contains valid values in randomly selected row or col
	 */
    private static void previousCell(int currentRow, int currentCol, boolean nextCell, int[][] grid, final int[][] holder)
    {
        int nextRow, nextCol;
        if(currentCol == INDEX_OF_FIRST_CELL)
        {
            nextRow = currentRow - 1;
            nextCol = INDEX_OF_LAST_CELL;
        }
        else
        {
            nextRow = currentRow; 
            nextCol = currentCol - 1;
        }
        nextCell = false;
        fillSudokuWithValidValues(nextRow, nextCol, nextCell, grid, holder);
    }
    
	/**
	 * This checks the whether the current cell has valid value or not.
	 * 
	 * @param currentRow current row of the cell
	 * @param currentCol current col of the cell
	 * @param grid       contains final solution
	 * @return           true if the cell contains valid value, otherwise false
	 */
    public static boolean validValue(int currentRow, int currentCol, int[][] grid)
    {
        String cellValues = "";
        for(int i = INDEX_OF_FIRST_CELL; i < GRID_SIZE; i++)
        {
            cellValues += Integer.toString(grid[i][currentCol]); //check if that col already has that value
            cellValues += Integer.toString(grid[currentRow][i]); //check if that row already has that value
            cellValues += Integer.toString(grid[(currentRow/INNER_GRID_SIZE) * INNER_GRID_SIZE +
                        i/INNER_GRID_SIZE][(currentCol/INNER_GRID_SIZE) * INNER_GRID_SIZE + i%INNER_GRID_SIZE]);
                                                                 //check if that subgrid already has that value
        }
        int count = 0; 
        int flag = 0;
        while ((flag = cellValues.indexOf(Integer.toString(grid[currentRow][currentCol]), flag))!=-1)
        {
            flag++;
            count++;
        }
        
        return (count == 3);
    }

	/**
	 * This prints the grid.
	 * 
	 * @param grid any grid with {@link GRID_SIZE}
	 */
    public static void print(int[][] grid)
    {
    	System.out.println();
    	for(int i = INDEX_OF_FIRST_CELL; i < GRID_SIZE; i++)
    	{
    		for(int j = INDEX_OF_FIRST_CELL; j < GRID_SIZE; j++)
    		{
    			System.out.print(grid[i][j]);
    		}
    		System.out.println();
    	}
    }
}
