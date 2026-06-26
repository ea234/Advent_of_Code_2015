package de.ea234.aoc2015.day08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * <pre>
 * 
 * --- Day 8: Matchsticks --- 
 * https://adventofcode.com/2015/day/8
 * 
 * https://www.reddit.com/r/adventofcode/comments/3vw32y/day_8_solutions/
 * 
 * 
 * length_memory    2   length_code    0  =     2 
 * length_memory    5   length_code    3  =     2 
 * length_memory   10   length_code    7  =     3 
 * length_memory    6   length_code    1  =     5
 *  
 * counter_quotation_marks 8   counter_white_space 0
 * length_memory   23   length_code   11  =    12
 *  
 * counter_quotation_marks 600   counter_white_space 300
 * length_memory 6610   length_code 5277  =  1333 
 *  
 *  
 * ea234@MsiZ370:/mnt/hd4tbb/daten/zdownload$ head -n 20 advent_of_code_2015__day08_input.txt 
 * "sjdivfriyaaqa\xd2v\"k\"mpcu\"yyu\"en"
 * "vcqc"
 * "zbcwgmbpijcxu\"yins\"sfxn"
 * "yumngprx"
 * "bbdj"
 * "czbggabkzo\"wsnw\"voklp\"s"
 * "acwt"
 * "aqttwnsohbzian\"evtllfxwkog\"cunzw"
 * "ugvsgfv"
 * "xlnillibxg"
 * "kexh\"pmi"
 * "syvugow"
 * "m\"ktqnw"
 * "yrbajyndte\\rm"
 * "f\"kak\x70sn\xc4kjri"
 * "yxthr"
 * "alvumfsjni\"kohg"
 * "trajs\x5brom\xf1yoijaumkem\"\"tahlzs"
 * "\"oedr\"pwdbnnrc"
 * "qsmzhnx\""
 * 
 * ea234@MsiZ370:/mnt/hd4tbb/daten/zdownload$ xxd -l210 advent_of_code_2015__day08_input.txt 
 * 00000000: 2273 6a64 6976 6672 6979 6161 7161 5c78  "sjdivfriyaaqa\x
 * 00000010: 6432 765c 226b 5c22 6d70 6375 5c22 7979  d2v\"k\"mpcu\"yy
 * 00000020: 755c 2265 6e22 0a22 7663 7163 220a 227a  u\"en"."vcqc"."z
 * 00000030: 6263 7767 6d62 7069 6a63 7875 5c22 7969  bcwgmbpijcxu\"yi
 * 00000040: 6e73 5c22 7366 786e 220a 2279 756d 6e67  ns\"sfxn"."yumng
 * 00000050: 7072 7822 0a22 6262 646a 220a 2263 7a62  prx"."bbdj"."czb
 * 00000060: 6767 6162 6b7a 6f5c 2277 736e 775c 2276  ggabkzo\"wsnw\"v
 * 00000070: 6f6b 6c70 5c22 7322 0a22 6163 7774 220a  oklp\"s"."acwt".
 * 00000080: 2261 7174 7477 6e73 6f68 627a 6961 6e5c  "aqttwnsohbzian\
 * 00000090: 2265 7674 6c6c 6678 776b 6f67 5c22 6375  "evtllfxwkog\"cu
 * 000000a0: 6e7a 7722 0a22 7567 7673 6766 7622 0a22  nzw"."ugvsgfv"."
 * 000000b0: 786c 6e69 6c6c 6962 7867 220a 226b 6578  xlnillibxg"."kex
 * 000000c0: 685c 2270 6d69 220a 2273 7976 7567 6f77  h\"pmi"."syvugow
 * 000000d0: 220a                                     ".
 * 
 * 
 *  
 * </pre>
 */
public class Day08_Matchsticks
{
  public static void main( String[] args )
  {
//    calculatePart01( "\"\"" ); // 0
//    calculatePart01( "\"abc\"" ); // 3
//    calculatePart01( "\"aaa\\\"aaa\"" ); // 7
//    calculatePart01( "\"aaa\\\"aaa\"" ); // 7
//    calculatePart01( "\"\\x27\"" ); // 1
//
    String test_x = "\"\"" + "\n" + "\"abc\"" + "\n" + "\"aaa\\\"aaa\"" + "\n" + "\"\\x27\"";

    calculate01( test_x.getBytes(), true );

    calculate01( getListProd(), true );

    System.exit( 0 );
  }

  private static void calculatePart01( String pString )
  {
    calculate01( pString.getBytes(), false );
  }

  private static void calculate01( byte[] pBytes, boolean pKnzDebug )
  {
    long length_memory = getMemoryLength( pBytes, pKnzDebug );

    long length_code = pBytes.length;

    long result_part_01 = length_code - length_memory;

    //wl( String.format( "Part 1: length_memory %4d   length_code %4d  =  %4d ", length_code, length_memory, result_part_01 ) );

    String new_string = getEscapedString( pBytes );

    long length_memory_c2 = getMemoryLength( new_string.getBytes(), pKnzDebug );

    long length_new_string = new_string.length();

    long result_part_02 = length_new_string - length_code;

    wl( String.format( "Part 2: length_code %4d   new_string_code   %4d   length_memory_c2 %4d  =  %4d ", length_code, length_new_string, length_memory_c2, result_part_02 ) );
  }

  public static int getMemoryLength( byte[] pBytes, boolean pKnzDebug )
  {
    int memory_length = 0;

    int counter_quotation_marks = 0;

    int counter_white_space = 0;

    for ( int cur_index = 0; cur_index < pBytes.length; cur_index++ )
    {
      byte cur_char = pBytes[ cur_index ];

      if ( cur_char == '"' )
      {
        counter_quotation_marks++;
      }
      else if ( cur_char < 32 )
      {
        counter_white_space++;
      }
      else if ( cur_char == '\\' )
      {
        byte next_char = pBytes[ cur_index + 1 ];

        memory_length++;

        if ( next_char == '\\' || next_char == '"' )
        {
          /*
           * Escaped backslash
           * Escaped quotation marks 
           */

          cur_index++;
        }
        else if ( next_char == 'x' )
        {

          /*
           * 3 more characters: x + 2 Hex Numbers
           */
          cur_index += 3;
        }
      }
      else
      {
        memory_length++;
      }
    }

    if ( pKnzDebug )
    {
      wl( "counter_quotation_marks " + counter_quotation_marks + "   counter_white_space " + counter_white_space );
    }

    return memory_length + counter_white_space;
  }

  public static String getEscapedString( byte[] pBytes )
  {
    StringBuilder new_string = new StringBuilder();

    new_string.append( "\"" );

    for ( int cur_index = 0; cur_index < pBytes.length; cur_index++ )
    {
      byte cur_char = pBytes[ cur_index ];

      if ( cur_char == '"' )
      {
        new_string.append( "\\" );

        new_string.append( ( (char) cur_char ) );
      }
      else if ( cur_char == '\n' )
      {
        new_string.append( "\"" );
        new_string.append( ( (char) cur_char ) );
        new_string.append( "\"" );
      }
      else if ( cur_char == '\\' )
      {
        byte next_char = pBytes[ cur_index + 1 ];

        new_string.append( "\\\\" );

        if ( next_char == '\\' )
        {
          new_string.append( "\\\\" );

          cur_index++;
        }
        else if ( next_char == '"' )
        {
          new_string.append( "\\" );

          new_string.append( ( (char) next_char ) );

          cur_index++;
        }
        else if ( next_char == 'x' )
        {
          new_string.append( ( (char) next_char ) );

          new_string.append( ( (char) pBytes[ cur_index + 2 ] ) );

          new_string.append( ( (char) pBytes[ cur_index + 3 ] ) );

          /*
           * 3 more characters: x + 2 Hex Numbers
           */
          cur_index += 3;
        }
      }
      else
      {
        new_string.append( ( (char) cur_char ) );
      }
    }

    new_string.append( "\"" );

    return new_string.toString();
  }


  private static List< String > getListProd()
  {
    List< String > string_array = null;

//    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2015__day07_input.txt";
    String datei_input = "/mnt/hd4tbb/daten/zdownload/input";

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