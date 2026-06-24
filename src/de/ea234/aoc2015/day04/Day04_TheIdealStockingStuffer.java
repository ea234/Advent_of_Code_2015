package de.ea234.aoc2015.day04;

import java.security.MessageDigest;

/**
 * <pre>
 * 
 * --- Day 4: The Ideal Stocking Stuffer ---
 * https://adventofcode.com/2015/day/4
 * 
 * 
 * ----------------------------------------------------------------------------
 * Similar to:
 * 
 * --- Day 5: How About a Nice Game of Chess? ---
 * https://adventofcode.com/2016/day/5
 * 
 * https://www.reddit.com/r/adventofcode/comments/5gk2yv/2016_day_5_solutions/
 * 
 * 
 * 
 * ------------------------------------------------------------------------------------------
 * 
 * Calculation Time 00:00:00:470
 * 
 * cur_index end  609043
 * cur_md5_string 000001dbbfa3a5c83a2d506429c7b00e
 * 
 * 
 * Calculation Time 00:00:03:177
 * 
 * cur_index end  6742839
 * cur_md5_string 000000072a1e4320d13deee9d934ae29
 * 
 * Result Part 1 609043
 * Result Part 2 6742839
 * 
 * 
 * ------------------------------------------------------------------------------------------
 * 
 * Calculation Time 00:00:00:068
 * 
 * cur_index end  117946
 * cur_md5_string 00000fe1c139a2c710e9a5c03ec1af03
 * 
 * 
 * Calculation Time 00:00:01:852
 * 
 * cur_index end  3938038
 * cur_md5_string 00000028023e3b4729684757f8dc3fbf
 * 
 * Result Part 1 117946
 * Result Part 2 3938038
 * 
 * </pre> 
 */
public class Day04_TheIdealStockingStuffer
{
  public static void main( String[] args )
  {
    /*
     * Reusing MD5-Source
     * https://github.com/ea234/Advent_of_Code_2016/blob/main/src/de/ea234/aoc2016/day14/Day14MD5.java
     */

    calculate01( "abcdef",   255_000_000, true  );

    calculate01( "ckczppom", 255_000_000, false );

    System.exit( 0 );
  }

  private static void calculate01( String pSalt, int pMaxLoop, boolean pKnzDebug )
  {
    wl( "" );
    wl( "------------------------------------------------------------------------------------------" );

    int result_part_01 = calculateMD5( pSalt, "00000",  pMaxLoop, pKnzDebug );
    int result_part_02 = calculateMD5( pSalt, "000000", pMaxLoop, pKnzDebug );

    wl( "Result Part 1 " + result_part_01 );
    wl( "Result Part 2 " + result_part_02 );
    wl( "" );
  }

  private static int calculateMD5( String pSalt, String pStartSequenze, int pMaxLoop, boolean pKnzDebug )
  {
    long start_time  = System.currentTimeMillis();

    int result_index = 0;

    int cur_index    = 0;

    String cur_md5_string = null;

    while ( ( result_index == 0 ) && ( cur_index < pMaxLoop ) )
    {
      cur_md5_string = getMD5( pSalt + cur_index );

      if ( cur_md5_string.startsWith( pStartSequenze ) )
      {
        result_index = cur_index;
      }
      else
      {
        cur_index++;
      }
    }

    long end_time = System.currentTimeMillis();

    wl( "" );
    wl( "Calculation Time " + getDebugLaufzeit( end_time - start_time ) );
    wl( "" );
    wl( "cur_index end    " + cur_index );
    wl( "cur_md5_string   " + cur_md5_string );
    wl( "" );

    return cur_index;
  }

  private static String getMD5( String pInput )
  {
    try
    {
      MessageDigest md = MessageDigest.getInstance( "MD5" );

      byte[] md5_digest = md.digest( pInput.getBytes( "UTF-8" ) );

      StringBuilder hex_string = new StringBuilder();

      for ( byte cur_byte : md5_digest )
      {
        String hex_number = Integer.toHexString( 0xFF & cur_byte );

        if ( hex_number.length() == 1 ) hex_string.append( '0' );

        hex_string.append( hex_number );
      }

      return hex_string.toString();
    }
    catch ( Exception err_inst )
    {
      wl( err_inst.getMessage() );
    }

    return null;
  }

  private static void wl( String pString ) // wl = short for "write log"
  {
    System.out.println( pString );
  }

  private static String getDebugLaufzeit( long pAnzahlMillisekunden )
  {
    long zeit_differenz_betrag = pAnzahlMillisekunden;

    long m_laufzeit_stunden = 0;
    long m_laufzeit_minuten = 0;
    long m_laufzeit_sekunden = 0;
    long m_laufzeit_milli_s = 0;

    if ( zeit_differenz_betrag > 0 )
    {
      m_laufzeit_milli_s = (long) ( zeit_differenz_betrag % 1000 );

      zeit_differenz_betrag /= 1000;

      m_laufzeit_sekunden = (long) ( zeit_differenz_betrag % 60 );

      zeit_differenz_betrag /= 60;

      m_laufzeit_minuten = (long) ( zeit_differenz_betrag % 60 );

      m_laufzeit_stunden = (long) zeit_differenz_betrag / 60;
    }

    return ( m_laufzeit_stunden < 10 ? "0" : "" ) + m_laufzeit_stunden + ":" + ( m_laufzeit_minuten < 10 ? "0" : "" ) + m_laufzeit_minuten + ":" + ( m_laufzeit_sekunden < 10 ? "0" : "" ) + m_laufzeit_sekunden + ":" + ( m_laufzeit_milli_s < 10 ? "00" : ( m_laufzeit_milli_s < 100 ? "0" : "" ) ) + m_laufzeit_milli_s;
  }
}
