package de.ea234.aoc2015.day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * 
 * --- Day 2: I Was Told There Would Be No Math ---
 * https://adventofcode.com/2015/day/2
 * 
 * https://www.reddit.com/r/adventofcode/comments/3v3w2f/day_2_solutions/
 * 
 * 
 * SURFACE l   12   w   19  h   10   a   228   b   190   c   120   smallest side  120   =    1196
 * SURFACE l   15   w   29  h    4   a   435   b   116   c    60   smallest side   60   =    1282
 * SURFACE l   22   w   14  h    7   a   308   b    98   c   154   smallest side   98   =    1218
 * SURFACE l   29   w   18  h    5   a   522   b    90   c   145   smallest side   90   =    1604
 * SURFACE l    1   w    7  h   27   a     7   b   189   c    27   smallest side    7   =     453
 * SURFACE l   15   w   11  h   23   a   165   b   253   c   345   smallest side  165   =    1691
 * SURFACE l   12   w   10  h   30   a   120   b   300   c   360   smallest side  120   =    1680
 * SURFACE l   30   w    2  h    7   a    60   b    14   c   210   smallest side   14   =     582
 * SURFACE l   28   w   10  h    1   a   280   b    10   c    28   smallest side   10   =     646
 * SURFACE l   18   w   10  h   30   a   180   b   300   c   540   smallest side  180   =    2220
 * SURFACE l   20   w   21  h   16   a   420   b   336   c   320   smallest side  320   =    2472
 * SURFACE l   23   w    3  h    6   a    69   b    18   c   138   smallest side   18   =     468
 * SURFACE l   27   w   26  h   11   a   702   b   286   c   297   smallest side  286   =    2856
 * SURFACE l    3   w    2  h   22   a     6   b    44   c    66   smallest side    6   =     238
 * SURFACE l   14   w    3  h    5   a    42   b    15   c    70   smallest side   15   =     269
 * SURFACE l   10   w    9  h    8   a    90   b    72   c    80   smallest side   72   =     556
 * 
 * 
 * RIBBON  l   29   w   13  h   26   a    13   b    26   c    29   bow   9802   =       9880
 * RIBBON  l   11   w   11  h   14   a    11   b    11   c    14   bow   1694   =       1738
 * RIBBON  l   27   w    2  h    5   a     2   b     5   c    27   bow    270   =        284
 * RIBBON  l   18   w   17  h    8   a     8   b    17   c    18   bow   2448   =       2498
 * RIBBON  l   28   w   23  h    9   a     9   b    23   c    28   bow   5796   =       5860
 * RIBBON  l    1   w    1  h   17   a     1   b     1   c    17   bow     17   =         21
 * RIBBON  l    7   w   23  h    9   a     7   b     9   c    23   bow   1449   =       1481
 * RIBBON  l   15   w   24  h   28   a    15   b    24   c    28   bow  10080   =      10158
 * RIBBON  l   30   w   11  h   18   a    11   b    18   c    30   bow   5940   =       5998
 * RIBBON  l   23   w    3  h    6   a     3   b     6   c    23   bow    414   =        432
 * RIBBON  l   27   w   26  h   11   a    11   b    26   c    27   bow   7722   =       7796
 * RIBBON  l    3   w    2  h   22   a     2   b     3   c    22   bow    132   =        142
 * RIBBON  l   14   w    3  h    5   a     3   b     5   c    14   bow    210   =        226
 * RIBBON  l   10   w    9  h    8   a     8   b     9   c    10   bow    720   =        754
 * 
 * Result Part 1 1586300
 * Result Part 2 3737498
 * 
 * </pre> 
 */
public class Day02_IWasToldThereWouldBeNoMath
{
  public static void main( String[] args )
  {
    calcSurfaceArea( 2, 3, 4 );
    
    calcSurfaceArea( 1, 1, 10 );

    calcRibbon( 2, 3, 4 );

    calculate01( getListProd(), false );

    System.exit( 0 );
  }

  private static void calculate01( List< String > pListInput, boolean pKnzDebug )
  {
    long result_part_01 = 0;

    long result_part_02 = 0;

    for ( String input_str : pListInput )
    {
      if ( !input_str.isBlank() )
      {
        String[] parts = input_str.split( "x" );

        if ( parts.length == 3 )
        {
          long length = Integer.parseInt( parts[ 0 ] );
          long width  = Integer.parseInt( parts[ 1 ] );
          long height = Integer.parseInt( parts[ 2 ] );

          result_part_01 += calcSurfaceArea( length, width, height );
        }
      }
    }

    wl( "" );
    wl( "" );

    for ( String input_str : pListInput )
    {
      if ( !input_str.isBlank() )
      {
        String[] parts = input_str.split( "x" );

        if ( parts.length == 3 )
        {
          long length = Integer.parseInt( parts[ 0 ] );
          long width  = Integer.parseInt( parts[ 1 ] );
          long height = Integer.parseInt( parts[ 2 ] );

          result_part_01 += calcRibbon( length, width, height );
        }
      }
    }

    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
    wl( "Result Part 2 " + result_part_02 );
    wl( "" );
    wl( "" );
  }

  private static long calcSurfaceArea( long pLength, long pWidth, long pHeight )
  {
    long surface_a = ( pLength * pWidth );
    long surface_b = ( pWidth * pHeight );
    long surface_c = ( pHeight * pLength );

    long smallest_side = Math.min( Math.min( surface_a, surface_b ), surface_c );

    long surface_area = ( 2 * surface_a ) + ( 2 * surface_b ) + ( 2 * surface_c ) + smallest_side;

    wl( String.format( "SURFACE l %4d   w %4d  h %4d   a %5d   b %5d   c %5d   smallest side %4d   = %7d", pLength, pWidth, pHeight, surface_a, surface_b, surface_c, smallest_side, surface_area ) );

    return surface_area;
  }

  private static long calcRibbon( long pLength, long pWidth, long pHeight )
  {
    long ribbon_bow_cubic = pLength * pWidth * pHeight;

    long[] perimeter_array = { pLength, pWidth, pHeight };

    Arrays.sort( perimeter_array );

    long ribbon_wrap_around = ( perimeter_array[ 0 ] * 2 ) + ( perimeter_array[ 1 ] * 2 );

    long total = ribbon_wrap_around + ribbon_bow_cubic;

    wl( String.format( "RIBBON  l %4d   w %4d  h %4d   a %5d   b %5d   c %5d   bow %6d   = %10d", pLength, pWidth, pHeight, perimeter_array[ 0 ], perimeter_array[ 1 ], perimeter_array[ 2 ], ribbon_bow_cubic, total ) );

    return total;
  }

  private static List< String > getListProd()
  {
    List< String > string_array = null;

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2015__day02_input.txt";

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

