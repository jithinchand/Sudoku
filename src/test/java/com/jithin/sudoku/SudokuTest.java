package com.jithin.sudoku;

import static org.junit.Assert.*;

import org.junit.*;

/**
 * Unit test for Sudoku Generator.
 */
public class SudokuTest 
{
	/**
	 * This tests the sudoku solution when a row is filled with valid values.
	 * 
	 * @see fillSudokuWithValidValues
	 */
	@Test
	public void TestFillSudokuWithValidValuesWithPrefilledRow()
	{
		int[][] initialRow = new int[][]{{0,0,0,0},
									     {2,1,3,4},
										 {0,0,0,0},
										 {0,0,0,0}};
		final int[][] gridHolder = new int[][]{{0,0,0,0},
										       {2,1,3,4},
											   {0,0,0,0},
											   {0,0,0,0}};
		int[][] expectedGrid = new int[][]{{3,4,1,2},
									       {2,1,3,4},
										   {1,2,4,3},
										   {4,3,2,1}};							
		int[][] actualGrid = Sudoku.fillSudokuWithValidValues(0, 0, true, initialRow, gridHolder);
		assertArrayEquals(actualGrid, expectedGrid);
	}

	/**
	 * This tests the sudoku solution when a coloumn is filled with valid values.
	 * 
	 * @see fillSudokuWithValidValues
	 */
    @Test
    public void TestFillSudokuWithValidValuesWithPrefilledCol()
    {
		int[][] initialRow = new int[][]{{0,0,0,4},
									     {0,0,0,2},
										 {0,0,0,1},
										 {0,0,0,3}};
		final int[][] gridHolder = new int[][]{{0,0,0,4},
										       {0,0,0,2},
											   {0,0,0,1},
											   {0,0,0,3}};
	    int[][] expectedGrid = new int[][]{{1,2,3,4},
									       {3,4,1,2},
										   {2,3,4,1},
										   {4,1,2,3}};							
        int[][] actualGrid = Sudoku.fillSudokuWithValidValues(0, 0, true, initialRow, gridHolder);
        assertArrayEquals(actualGrid, expectedGrid);
    }

	/**
	 * This tests whether the cell has valid value or not.
	 * 
	 * @see validValue
	 */
    @Test
    public void TestValidValue()
    {
		int[][] initialRow = new int[][]{{0,0,0,0},
									     {0,4,2,0},
										 {0,1,0,0},
										 {4,0,3,0}};
		initialRow[0][0] = 2;
		assertTrue(Sudoku.validValue(0, 0, initialRow));
    }
    
	/**
	 * This tests whether the cell has valid value or not.
	 * 
	 * @see validValue
	 */
    @Test
    public void TesInvalidValue()
    {
		int[][] initialRow = new int[][]{{0,0,0,0},
									     {0,4,2,0},
										 {0,1,0,0},
										 {4,0,3,0}};
		initialRow[0][0] = 4;
		assertFalse(Sudoku.validValue(0, 0, initialRow));
    }
}
