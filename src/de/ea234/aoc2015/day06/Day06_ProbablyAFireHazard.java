package de.ea234.aoc2015.day06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 * 
 * --- Day 6: Probably a Fire Hazard ---
 * https://adventofcode.com/2015/day/6
 * 
 * https://www.reddit.com/r/adventofcode/comments/3vmltn/day_6_solutions/
 * 
 * 
 * Result Part 1 400410
 * Result Part 2 15343601
 *  
 * </pre>
 */
public class Day06_ProbablyAFireHazard
{
  private static final int     LIGHT_ON       = 1;

  private static final int     LIGHT_OFF      = 0;

  private static final String  CHAR_LIGHT_ON  = "#";

  private static final String  CHAR_LIGHT_OFF = ".";

  private static final Pattern PATTERN        = Pattern.compile( "(\\d+),(\\d+)\\s+through\\s+(\\d+),(\\d+)" );

  public static void main( String[] args )
  {
    calculate01( getListProd(), true );

    System.exit( 0 );
  }

  private static void calculate01( List< String > pListInput, boolean pKnzDebug )
  {
    long result_part_01 = 0;

    long result_part_02 = 0;

    long[][] grid = new long[ 1001 ][ 1001 ];

    /*
     * ********************************************************************************************
     * Part 1
     * ********************************************************************************************
     */

    setLightStatus( grid, 0, 0, 1000, 1000, LIGHT_OFF );

    for ( String input_str : pListInput )
    {
      if ( !input_str.isBlank() )
      {
        Matcher matcher = PATTERN.matcher( input_str );

        if ( matcher.find() )
        {
          int row_start = Integer.parseInt( matcher.group( 1 ) );
          int col_start = Integer.parseInt( matcher.group( 2 ) );
          int row_end   = Integer.parseInt( matcher.group( 3 ) );
          int col_end   = Integer.parseInt( matcher.group( 4 ) );

          if ( input_str.startsWith( "toggle" ) )
          {
            toggleLightStatus( grid, row_start, col_start, row_end, col_end );
          }
          else if ( input_str.startsWith( "turn on " ) )
          {
            setLightStatus( grid, row_start, col_start, row_end, col_end, LIGHT_ON );
          }
          else if ( input_str.startsWith( "turn off" ) )
          {
            setLightStatus( grid, row_start, col_start, row_end, col_end, LIGHT_OFF );
          }
        }
        else
        {
          throw new IllegalArgumentException( "Format: " + input_str );
        }
      }
    }

    result_part_01 = countLightStatus( grid, 0, 0, 1000, 1000, LIGHT_ON );

    /*
     * ********************************************************************************************
     * Part 2
     * ********************************************************************************************
     */

    setLightStatus( grid, 0, 0, 1000, 1000, LIGHT_OFF );

    for ( String input_str : pListInput )
    {
      if ( !input_str.isBlank() )
      {
        Matcher matcher = PATTERN.matcher( input_str );

        if ( matcher.find() )
        {
          int row_start = Integer.parseInt( matcher.group( 1 ) );
          int col_start = Integer.parseInt( matcher.group( 2 ) );
          int row_end   = Integer.parseInt( matcher.group( 3 ) );
          int col_end   = Integer.parseInt( matcher.group( 4 ) );

          if ( input_str.startsWith( "toggle" ) )
          {
            addLightBrightness( grid, row_start, col_start, row_end, col_end, 2 );
          }
          else if ( input_str.startsWith( "turn on " ) )
          {
            addLightBrightness( grid, row_start, col_start, row_end, col_end, 1 );
          }
          else if ( input_str.startsWith( "turn off" ) )
          {
            addLightBrightness( grid, row_start, col_start, row_end, col_end, -1 );
          }
        }
        else
        {
          throw new IllegalArgumentException( "Format: " + input_str );
        }
      }
    }

    result_part_02 = countLightBrightness( grid, 0, 0, 1000, 1000 );

    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
    wl( "Result Part 2 " + result_part_02 );
    wl( "" );
  }

  private static void setLightStatus( long[][] grid, int pRowStart, int pColStart, int pRowEnd, int pColEnd, long pStatus )
  {
    for ( int cur_row = pRowStart; cur_row <= pRowEnd; cur_row++ )
    {
      for ( int cur_col = pColStart; cur_col <= pColEnd; cur_col++ )
      {
        grid[ cur_row ][ cur_col ] = pStatus;
      }
    }
  }

  private static void toggleLightStatus( long[][] grid, int pRowStart, int pColStart, int pRowEnd, int pColEnd )
  {
    for ( int cur_row = pRowStart; cur_row <= pRowEnd; cur_row++ )
    {
      for ( int cur_col = pColStart; cur_col <= pColEnd; cur_col++ )
      {
        grid[ cur_row ][ cur_col ] = grid[ cur_row ][ cur_col ] == LIGHT_ON ? LIGHT_OFF : LIGHT_ON;
      }
    }
  }

  private static void addLightBrightness( long[][] grid, int pRowStart, int pColStart, int pRowEnd, int pColEnd, long pDelta )
  {
    for ( int cur_row = pRowStart; cur_row <= pRowEnd; cur_row++ )
    {
      for ( int cur_col = pColStart; cur_col <= pColEnd; cur_col++ )
      {
        grid[ cur_row ][ cur_col ] += pDelta;

        if ( grid[ cur_row ][ cur_col ] < 0 )
        {
          grid[ cur_row ][ cur_col ] = LIGHT_OFF;
        }
      }
    }
  }

  private static int countLightStatus( long[][] grid, int pRowStart, int pColStart, int pRowEnd, int pColEnd, int pStatus )
  {
    int result_count = 0;

    for ( int cur_row = pRowStart; cur_row <= pRowEnd; cur_row++ )
    {
      for ( int cur_col = pColStart; cur_col <= pColEnd; cur_col++ )
      {
        result_count += grid[ cur_row ][ cur_col ] == pStatus ? 1 : 0;
      }
    }

    return result_count;
  }

  private static long countLightBrightness( long[][] grid, int pRowStart, int pColStart, int pRowEnd, int pColEnd )
  {
    long result_count = 0;

    for ( int cur_row = pRowStart; cur_row <= pRowEnd; cur_row++ )
    {
      for ( int cur_col = pColStart; cur_col <= pColEnd; cur_col++ )
      {
        result_count += grid[ cur_row ][ cur_col ];
      }
    }

    return result_count;
  }

  private static String getDebugLightStatus( long[][] grid, int pRowStart, int pColStart, int pRowEnd, int pColEnd )
  {
    String dbg_string = "";

    for ( int cur_row = pRowStart; cur_row <= pRowEnd; cur_row++ )
    {
      for ( int cur_col = pColStart; cur_col <= pColEnd; cur_col++ )
      {
        dbg_string += grid[ cur_row ][ cur_col ] == LIGHT_OFF ? CHAR_LIGHT_OFF : CHAR_LIGHT_ON;
      }

      dbg_string += "\n";
    }

    return dbg_string;
  }

  private static List< String > getListProd()
  {
    List< String > string_array = null;

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2015__day06_input.txt";

    try
    {
      string_array = Files.readAllLines( Path.of( datei_input ) );
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
