package de.ea234.aoc2015.day03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * <pre>
 * --- Day 3: Perfectly Spherical Houses in a Vacuum ---
 * https://adventofcode.com/2015/day/3
 * 
 * https://www.reddit.com/r/adventofcode/comments/3v8roh/day_3_solutions/
 * 
 * 
 * </pre>
 */
public class Day03_PerfectlySphericalHousesInAVacuum
{
  private static final char DIRECTION_NORTH = '^';

  private static final char DIRECTION_SOUTH = 'v';

  private static final char DIRECTION_EAST  = '<';

  private static final char DIRECTION_WEST  = '>';

  public static void main( String[] args )
  {
    //calculate01( "^v", true );
    //calculate01( "^>v<", true );
    //calculate01( "^v^v^v^v^v", true );

    calculate01( getListProd(), true );

    System.exit( 0 );
  }

  private static void calculate01( String pListInput, boolean pKnzDebug )
  {
    int result_part_01 = calcResult( pListInput, true );

    int result_part_02 = calcResult( pListInput, false );

    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
    wl( "Result Part 2 " + result_part_02 );
    wl( "" );
  }

  private static int calcResult( String pListInput, boolean pKnzPart1 )
  {
    Properties prop_house_coords = new Properties();

    int start_row = 0;
    int start_col = 0;

    int[] cur_row = { start_row, start_row };
    int[] cur_col = { start_col, start_col };

    prop_house_coords.setProperty( "R" + start_row + "C" + start_col, "1" );

    for ( int idx_direction = 0; idx_direction < pListInput.length(); idx_direction++ )
    {
      char cur_direction = pListInput.charAt( idx_direction );

      int cur_idx = pKnzPart1 ? 0 : ( idx_direction % 2 == 0 ? 1 : 0 );

      if ( cur_direction == DIRECTION_NORTH )
      {
        cur_row[ cur_idx ] -= 1;
      }
      else if ( cur_direction == DIRECTION_SOUTH )
      {
        cur_row[ cur_idx ] += 1;
      }
      else if ( cur_direction == DIRECTION_WEST )
      {
        cur_col[ cur_idx ] -= 1;
      }
      else if ( cur_direction == DIRECTION_EAST )
      {
        cur_col[ cur_idx ] += 1;
      }

      prop_house_coords.put( "R" + cur_row[ cur_idx ] + "C" + cur_col[ cur_idx ], "1" );
    }

    return prop_house_coords.size();
  }

  private static String getListProd()
  {
    String string_array = null;

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2015__day03_input.txt";

    try
    {
      string_array = Files.readString( Path.of( datei_input ) );
    }
    catch ( IOException e )
    {
      e.printStackTrace();
    }

    return string_array;
  }

  private static void wl( String pString ) // wl = short for "write log"
  {
    System.out.println( pString );
  }
}