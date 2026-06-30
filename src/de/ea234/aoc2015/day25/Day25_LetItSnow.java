package de.ea234.aoc2015.day25;

/**
 * <pre>
 * 
 * --- Day 25: Let It Snow ---
 * https://adventofcode.com/2015/day/25
 * 
 * https://www.reddit.com/r/adventofcode/comments/3y5jco/day_25_solutions/
 * 
 * Round 17769740  -  Row   5962 Col      1  -  Prev   19532620   Cur   19532620
 * Round 17775702  -  Row   5963 Col      1  -  Prev    7926083   Cur    7926083
 * Round 17781665  -  Row   5964 Col      1  -  Prev   15433580   Cur   15433580
 * Round 17787629  -  Row   5965 Col      1  -  Prev   32825536   Cur   32825536
 * Round 17793594  -  Row   5966 Col      1  -  Prev    6917155   Cur    6917155
 * Round 17799560  -  Row   5967 Col      1  -  Prev    7261160   Cur    7261160
 * Round 17805527  -  Row   5968 Col      1  -  Prev   30349631   Cur   30349631
 * Round 17811495  -  Row   5969 Col      1  -  Prev   29958251   Cur   29958251
 * Round 17817464  -  Row   5970 Col      1  -  Prev   23787508   Cur   23787508
 * Round 17823434  -  Row   5971 Col      1  -  Prev   18364584   Cur   18364584
 * Round 17829405  -  Row   5972 Col      1  -  Prev    2942004   Cur    2942004
 * Round 17835377  -  Row   5973 Col      1  -  Prev    1935933   Cur    1935933
 * Round 17841350  -  Row   5974 Col      1  -  Prev    3860837   Cur    3860837
 * Round 17847324  -  Row   5975 Col      1  -  Prev    3298227   Cur    3298227
 * 
 * Row   2947 Col   3029   -  Prev   21942316    Cur   19980801
 *
 * </pre> 
 */
public class Day25_LetItSnow
{
  private static final long MULTIPLICATOR_PREV_NR = 252533;

  private static final long DIVIDER               = 33554393;

  public static void main( String[] args )
  {
    long total_rows  = 1;

    long grid_row    = 1;

    long grid_col    = 1;

    long cur_number  = 0;

    long prev_number = 20151125;

    for ( int round_nr = 0; round_nr < 80_000_000; round_nr++ )
    {
      /*
       * Decrease the grid row for the next value
       */
      grid_row--;

      if ( grid_row == 0 )
      {
        /*
         * If the row hits 0
         * - The total rows are increased by 1.
         * - The current grid row is set to the total rows
         * - The current grid col is set to the first column.
         */

        total_rows++;

        grid_row = total_rows;

        grid_col = 1;

        /*
         * Do some debug stuff.
         */

        wl( String.format( "Round %6d  -  Row %6d Col %6d  -  Prev %10d   Cur %10d", round_nr, grid_row, grid_col, prev_number, cur_number ) );
      }
      else
      {
        /*
         * Increase the Column, because the row wasn't 0
         */
        grid_col++;
      }

      /*
       * Calculate the next number.
       */
      cur_number = getNextNumber( prev_number );

      /*
       * Enter the code at row 2947, column 3029.
       */
      if ( ( grid_row == 2947 ) && ( grid_col == 3029 ) )
      {
        break;
      }

      prev_number = cur_number;
    }

    wl( String.format( "Row %6d Col %6d   -  Prev %10d    Cur %10d", grid_row, grid_col, prev_number, cur_number ) );

    System.exit( 0 );
  }

  private static long getNextNumber( long pPreviousNumber )
  {
    long multiply_by_x = pPreviousNumber * MULTIPLICATOR_PREV_NR;

    long remainder = multiply_by_x % DIVIDER;

    return remainder;
  }

  private static void wl( String pString ) // wl = short for "write log"
  {
    System.out.println( pString );
  }
}

