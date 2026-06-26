package de.ea234.aoc2015.day08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * 
 * --- Day 8: Matchsticks --- 
 * https://adventofcode.com/2015/day/8
 * 
 * https://www.reddit.com/r/adventofcode/comments/3vw32y/day_8_solutions/
 * 
 * 
 * Part 1: length_memory    2   length_code    0  =     2   "" 
 * Part 1: length_memory    5   length_code    3  =     2   "abc" 
 * Part 1: length_memory   10   length_code    7  =     3   "aaa\"aaa" 
 * Part 1: length_memory    6   length_code    1  =     5   "\x27" 
 * Part 1: length_memory   23   length_code   11  =    12 
 * 
 * 
 * 
 * Part 2: length old string    2   length new string    6  =     4   "" 
 * Part 2: length old string    5   length new string    9  =     4   "abc" 
 * Part 2: length old string   10   length new string   16  =     6   "aaa\"aaa" 
 * Part 2: length old string    6   length new string   11  =     5   "\x27" 
 * Part 2: length old string   23   length new string   42  =    19  
 * 
 * Result Part 1 12
 * Result Part 2 19
 * 
 * 
 * ------------------------------------------------------------------------------------------------
 * 
 * 
 * Part 1: length_memory   38   length_code   29  =     9   "sjdivfriyaaqa\xd2v\"k\"mpcu\"yyu\"en" 
 * Part 1: length_memory    6   length_code    4  =     2   "vcqc" 
 * Part 1: length_memory   27   length_code   23  =     4   "zbcwgmbpijcxu\"yins\"sfxn" 
 * Part 1: length_memory   10   length_code    8  =     2   "yumngprx"
 * ...
 * Part 1: length_memory   13   length_code   11  =     2   "dpbntplgmwb" 
 * Part 1: length_memory   27   length_code   24  =     3   "utsgfkm\\vbftjknlktpthoeo" 
 * Part 1: length_memory   20   length_code   17  =     3   "ccxjgiocmuhf\"ycnh" 
 * Part 1: length_memory   13   length_code   10  =     3   "lltj\"kbbxi" 
 * Part 1: length_memory 6310   length_code 4977  =  1333 
 * 
 * 
 * 
 * Part 2: length old string   38   length new string   51  =    13   "sjdivfriyaaqa\xd2v\"k\"mpcu\"yyu\"en" 
 * Part 2: length old string    6   length new string   10  =     4   "vcqc" 
 * Part 2: length old string   27   length new string   35  =     8   "zbcwgmbpijcxu\"yins\"sfxn" 
 * Part 2: length old string   10   length new string   14  =     4   "yumngprx"
 * ...
 * Part 2: length old string   13   length new string   17  =     4   "dpbntplgmwb" 
 * Part 2: length old string   27   length new string   33  =     6   "utsgfkm\\vbftjknlktpthoeo" 
 * Part 2: length old string   20   length new string   26  =     6   "ccxjgiocmuhf\"ycnh" 
 * Part 2: length old string   13   length new string   19  =     6   "lltj\"kbbxi" 
 * Part 2: length old string 6310   length new string 8356  =  2046  
 * 
 * Result Part 1 1333
 * Result Part 2 2046
 * 
 * 
 * ------------------------------------------------------------------------------------------------
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
    String test_x = "\"\",\"abc\",\"aaa\\\"aaa\",\"\\x27\"";

    calculatePart01( test_x, true );

    calculate01( getListProd(), true );

    System.exit( 0 );
  }

  private static void calculatePart01( String pString, boolean pKnzDebug )
  {
    List< String > converted_string_list = Arrays.stream( pString.split( "," ) ).map( String::trim ).collect( Collectors.toList() );

    calculate01( converted_string_list, pKnzDebug );
  }

  private static void calculate01( List< String > pListInput, boolean pKnzDebug )
  {
    long length_memory_sum = 0;

    long length_code_sum = 0;

    for ( String input_str : pListInput )
    {
      if ( !input_str.isBlank() )
      {
        long length_memory = getMemoryLength( input_str, false );

        length_memory_sum += length_memory;

        length_code_sum += input_str.length();

        wl( String.format( "Part 1: length_memory %4d   length_code %4d  =  %4d   %s ", input_str.length(), length_memory, ( input_str.length() - length_memory ), input_str ) );
      }
    }

    long result_part_01 = length_code_sum - length_memory_sum;

    wl( String.format( "Part 1: length_memory %4d   length_code %4d  =  %4d ", length_code_sum, length_memory_sum, result_part_01 ) );

    wl( "" );
    wl( "" );
    wl( "" );

    long length_code_sum_new = 0;

    for ( String input_str : pListInput )
    {
      if ( !input_str.isBlank() )
      {
        String new_string = getEscapedString( input_str );

        length_code_sum_new += new_string.length();

        wl( String.format( "Part 2: length old string %4d   length new string %4d  =  %4d   %s ", input_str.length(), new_string.length(), ( new_string.length() - input_str.length() ), input_str ) );
      }
    }

    long result_part_02 = length_code_sum_new - length_code_sum;

    wl( String.format( "Part 2: length old string %4d   length new string %4d  =  %4d  ", length_code_sum, length_code_sum_new, result_part_02 ) );

    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
    wl( "Result Part 2 " + result_part_02 );
    wl( "" );
  }

  private static int getMemoryLength( String pBytes, boolean pKnzDebug )
  {
    int memory_length = 0;

    int counter_quotation_marks = 0;

    int counter_white_space = 0;

    for ( int cur_index = 0; cur_index < pBytes.length(); cur_index++ )
    {
      char cur_char = pBytes.charAt( cur_index );

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
        char next_char = pBytes.charAt( cur_index + 1 );

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
      wl( "counter_quotation_marks " + counter_quotation_marks + "   counter_white_space " + counter_white_space + "   memory_length " + memory_length );
    }

    return memory_length + counter_white_space;
  }

  private static String getEscapedString( String pBytes )
  {
    StringBuilder new_string = new StringBuilder();

    new_string.append( "\"" );

    for ( int cur_index = 0; cur_index < pBytes.length(); cur_index++ )
    {
      char cur_char = pBytes.charAt( cur_index );

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
        char next_char = pBytes.charAt( cur_index + 1 );

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

          new_string.append( pBytes.charAt( cur_index + 2 ) );

          new_string.append( pBytes.charAt( cur_index + 3 ) );

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

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2015__day08_input.txt";

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
