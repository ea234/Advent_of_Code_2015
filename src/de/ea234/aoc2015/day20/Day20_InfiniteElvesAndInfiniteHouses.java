package de.ea234.aoc2015.day20;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * 
 * --- Day 20: Infinite Elves and Infinite Houses ---
 * https://adventofcode.com/2015/day/20
 * 
 * https://www.reddit.com/r/adventofcode/comments/3xjpp2/day_20_solutions/
 * 
 * 
 * House Nr      1    Presents         10   Visited by    1   Elves , 1
 * House Nr      2    Presents         30   Visited by    2   Elves , 1, 2
 * House Nr      3    Presents         40   Visited by    2   Elves , 1, 3
 * House Nr      4    Presents         70   Visited by    3   Elves , 1, 2, 4
 * House Nr      5    Presents         60   Visited by    2   Elves , 1, 5
 * House Nr      6    Presents        120   Visited by    4   Elves , 1, 2, 3, 6
 * House Nr      7    Presents         80   Visited by    2   Elves , 1, 7
 * House Nr      8    Presents        150   Visited by    4   Elves , 1, 2, 4, 8
 * House Nr      9    Presents        130   Visited by    3   Elves , 1, 3, 9
 * House Nr     10    Presents        180   Visited by    4   Elves , 1, 2, 5, 10
 * House Nr     11    Presents        120   Visited by    2   Elves , 1, 11
 * House Nr     12    Presents        280   Visited by    6   Elves , 1, 2, 3, 4, 6, 12
 * House Nr     13    Presents        140   Visited by    2   Elves , 1, 13
 * House Nr     14    Presents        240   Visited by    4   Elves , 1, 2, 7, 14
 * House Nr     15    Presents        240   Visited by    4   Elves , 1, 3, 5, 15
 * House Nr     16    Presents        310   Visited by    5   Elves , 1, 2, 4, 8, 16
 * House Nr     17    Presents        180   Visited by    2   Elves , 1, 17
 * House Nr     18    Presents        390   Visited by    6   Elves , 1, 2, 3, 6, 9, 18
 * House Nr     19    Presents        200   Visited by    2   Elves , 1, 19
 * House Nr     20    Presents        420   Visited by    6   Elves , 1, 2, 4, 5, 10, 20
 * House Nr     21    Presents        320   Visited by    4   Elves , 1, 3, 7, 21
 * House Nr     22    Presents        360   Visited by    4   Elves , 1, 2, 11, 22
 * House Nr     23    Presents        240   Visited by    2   Elves , 1, 23
 * House Nr     24    Presents        600   Visited by    8   Elves , 1, 2, 3, 4, 6, 8, 12, 24
 * House Nr     25    Presents        310   Visited by    3   Elves , 1, 5, 25
 * House Nr     26    Presents        420   Visited by    4   Elves , 1, 2, 13, 26
 * House Nr     27    Presents        400   Visited by    4   Elves , 1, 3, 9, 27
 * House Nr     28    Presents        560   Visited by    6   Elves , 1, 2, 4, 7, 14, 28
 * House Nr     29    Presents        300   Visited by    2   Elves , 1, 29
 * House Nr     30    Presents        720   Visited by    8   Elves , 1, 2, 3, 5, 6, 10, 15, 30
 * House Nr     31    Presents        320   Visited by    2   Elves , 1, 31
 * House Nr     32    Presents        630   Visited by    6   Elves , 1, 2, 4, 8, 16, 32
 * House Nr     33    Presents        480   Visited by    4   Elves , 1, 3, 11, 33
 * House Nr     34    Presents        540   Visited by    4   Elves , 1, 2, 17, 34
 * House Nr     35    Presents        480   Visited by    4   Elves , 1, 5, 7, 35
 * House Nr     36    Presents        910   Visited by    9   Elves , 1, 2, 3, 4, 6, 9, 12, 18, 36
 * House Nr     37    Presents        380   Visited by    2   Elves , 1, 37
 * House Nr     38    Presents        600   Visited by    4   Elves , 1, 2, 19, 38
 * House Nr     39    Presents        560   Visited by    4   Elves , 1, 3, 13, 39
 * 
 * House Nr    100    Presents       2170   Visited by    9   Elves , 1, 2, 4, 5, 10, 20, 25, 50, 100
 * House Nr    110    Presents       2160   Visited by    8   Elves , 1, 2, 5, 10, 11, 22, 55, 110
 * House Nr    120    Presents       3600   Visited by   16   Elves , 1, 2, 3, 4, 5, 6, 8, 10, 12, 15, 20, 24, 30, 40, 60, 120
 * House Nr    130    Presents       2520   Visited by    8   Elves , 1, 2, 5, 10, 13, 26, 65, 130
 * House Nr    140    Presents       3360   Visited by   12   Elves , 1, 2, 4, 5, 7, 10, 14, 20, 28, 35, 70, 140
 * House Nr    150    Presents       3720   Visited by   12   Elves , 1, 2, 3, 5, 6, 10, 15, 25, 30, 50, 75, 150
 * House Nr    160    Presents       3780   Visited by   12   Elves , 1, 2, 4, 5, 8, 10, 16, 20, 32, 40, 80, 160
 * House Nr    170    Presents       3240   Visited by    8   Elves , 1, 2, 5, 10, 17, 34, 85, 170
 * House Nr    180    Presents       5460   Visited by   18   Elves , 1, 2, 3, 4, 5, 6, 9, 10, 12, 15, 18, 20, 30, 36, 45, 60, 90, 180
 * House Nr    190    Presents       3600   Visited by    8   Elves , 1, 2, 5, 10, 19, 38, 95, 190
 * House Nr    200    Presents       4650   Visited by   12   Elves , 1, 2, 4, 5, 8, 10, 20, 25, 40, 50, 100, 200
 * House Nr    210    Presents       5760   Visited by   16   Elves , 1, 2, 3, 5, 6, 7, 10, 14, 15, 21, 30, 35, 42, 70, 105, 210
 * House Nr    220    Presents       5040   Visited by   12   Elves , 1, 2, 4, 5, 10, 11, 20, 22, 44, 55, 110, 220
 * House Nr    230    Presents       4320   Visited by    8   Elves , 1, 2, 5, 10, 23, 46, 115, 230
 * House Nr    240    Presents       7440   Visited by   20   Elves , 1, 2, 3, 4, 5, 6, 8, 10, 12, 15, 16, 20, 24, 30, 40, 48, 60, 80, 120, 240
 * House Nr    250    Presents       4680   Visited by    8   Elves , 1, 2, 5, 10, 25, 50, 125, 250
 * House Nr    260    Presents       5880   Visited by   12   Elves , 1, 2, 4, 5, 10, 13, 20, 26, 52, 65, 130, 260
 * House Nr    270    Presents       7200   Visited by   16   Elves , 1, 2, 3, 5, 6, 9, 10, 15, 18, 27, 30, 45, 54, 90, 135, 270
 * House Nr    280    Presents       7200   Visited by   16   Elves , 1, 2, 4, 5, 7, 8, 10, 14, 20, 28, 35, 40, 56, 70, 140, 280
 * House Nr    290    Presents       5400   Visited by    8   Elves , 1, 2, 5, 10, 29, 58, 145, 290
 * House Nr    300    Presents       8680   Visited by   18   Elves , 1, 2, 3, 4, 5, 6, 10, 12, 15, 20, 25, 30, 50, 60, 75, 100, 150, 300
 * House Nr    310    Presents       5760   Visited by    8   Elves , 1, 2, 5, 10, 31, 62, 155, 310
 * House Nr    320    Presents       7620   Visited by   14   Elves , 1, 2, 4, 5, 8, 10, 16, 20, 32, 40, 64, 80, 160, 320
 * House Nr    330    Presents       8640   Visited by   16   Elves , 1, 2, 3, 5, 6, 10, 11, 15, 22, 30, 33, 55, 66, 110, 165, 330
 * House Nr    340    Presents       7560   Visited by   12   Elves , 1, 2, 4, 5, 10, 17, 20, 34, 68, 85, 170, 340
 * House Nr    350    Presents       7440   Visited by   12   Elves , 1, 2, 5, 7, 10, 14, 25, 35, 50, 70, 175, 350
 * House Nr    360    Presents      11700   Visited by   24   Elves , 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, 18, 20, 24, 30, 36, 40, 45, 60, 72, 90, 120, 180, 360
 * House Nr    370    Presents       6840   Visited by    8   Elves , 1, 2, 5, 10, 37, 74, 185, 370
 * House Nr    380    Presents       8400   Visited by   12   Elves , 1, 2, 4, 5, 10, 19, 20, 38, 76, 95, 190, 380
 * House Nr    390    Presents      10080   Visited by   16   Elves , 1, 2, 3, 5, 6, 10, 13, 15, 26, 30, 39, 65, 78, 130, 195, 390
 * 
 * House Nr   1000    Presents      23400   Visited by   16   Elves , 1, 2, 4, 5, 8, 10, 20, 25, 40, 50, 100, 125, 200, 250, 500, 1000
 * House Nr   1500    Presents      43680   Visited by   24   Elves , 1, 2, 3, 4, 5, 6, 10, 12, 15, 20, 25, 30, 50, 60, 75, 100, 125, 150, 250, 300, 375, 500, 750, 1500
 * House Nr   2000    Presents      48360   Visited by   20   Elves , 1, 2, 4, 5, 8, 10, 16, 20, 25, 40, 50, 80, 100, 125, 200, 250, 400, 500, 1000, 2000
 * House Nr   2500    Presents      54670   Visited by   15   Elves , 1, 2, 4, 5, 10, 20, 25, 50, 100, 125, 250, 500, 625, 1250, 2500
 * House Nr   3000    Presents      93600   Visited by   32   Elves , 1, 2, 3, 4, 5, 6, 8, 10, 12, 15, 20, 24, 25, 30, 40, 50, 60, 75, 100, 120, 125, 150, 200, 250, 300, 375, 500, 600, 750, 1000, 1500, 3000
 * House Nr   3500    Presents      87360   Visited by   24   Elves , 1, 2, 4, 5, 7, 10, 14, 20, 25, 28, 35, 50, 70, 100, 125, 140, 175, 250, 350, 500, 700, 875, 1750, 3500
 * House Nr   4000    Presents      98280   Visited by   24   Elves , 1, 2, 4, 5, 8, 10, 16, 20, 25, 32, 40, 50, 80, 100, 125, 160, 200, 250, 400, 500, 800, 1000, 2000, 4000
 * House Nr   4500    Presents     141960   Visited by   36   Elves , 1, 2, 3, 4, 5, 6, 9, 10, 12, 15, 18, 20, 25, 30, 36, 45, 50, 60, 75, 90, 100, 125, 150, 180, 225, 250, 300, 375, 450, 500, 750, 900, 1125, 1500, 2250, 4500
 * House Nr   5000    Presents     117150   Visited by   20   Elves , 1, 2, 4, 5, 8, 10, 20, 25, 40, 50, 100, 125, 200, 250, 500, 625, 1000, 1250, 2500, 5000
 * House Nr   5500    Presents     131040   Visited by   24   Elves , 1, 2, 4, 5, 10, 11, 20, 22, 25, 44, 50, 55, 100, 110, 125, 220, 250, 275, 500, 550, 1100, 1375, 2750, 5500
 * House Nr   6000    Presents     193440   Visited by   40   Elves , 1, 2, 3, 4, 5, 6, 8, 10, 12, 15, 16, 20, 24, 25, 30, 40, 48, 50, 60, 75, 80, 100, 120, 125, 150, 200, 240, 250, 300, 375, 400, 500, 600, 750, 1000, 1200, 1500, 2000, 3000, 6000
 * House Nr   6500    Presents     152880   Visited by   24   Elves , 1, 2, 4, 5, 10, 13, 20, 25, 26, 50, 52, 65, 100, 125, 130, 250, 260, 325, 500, 650, 1300, 1625, 3250, 6500
 * House Nr   7000    Presents     187200   Visited by   32   Elves , 1, 2, 4, 5, 7, 8, 10, 14, 20, 25, 28, 35, 40, 50, 56, 70, 100, 125, 140, 175, 200, 250, 280, 350, 500, 700, 875, 1000, 1400, 1750, 3500, 7000
 * House Nr   7500    Presents     218680   Visited by   30   Elves , 1, 2, 3, 4, 5, 6, 10, 12, 15, 20, 25, 30, 50, 60, 75, 100, 125, 150, 250, 300, 375, 500, 625, 750, 1250, 1500, 1875, 2500, 3750, 7500
 * House Nr   8000    Presents     198120   Visited by   28   Elves , 1, 2, 4, 5, 8, 10, 16, 20, 25, 32, 40, 50, 64, 80, 100, 125, 160, 200, 250, 320, 400, 500, 800, 1000, 1600, 2000, 4000, 8000
 * House Nr   8500    Presents     196560   Visited by   24   Elves , 1, 2, 4, 5, 10, 17, 20, 25, 34, 50, 68, 85, 100, 125, 170, 250, 340, 425, 500, 850, 1700, 2125, 4250, 8500
 * House Nr   9000    Presents     304200   Visited by   48   Elves , 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, 18, 20, 24, 25, 30, 36, 40, 45, 50, 60, 72, 75, 90, 100, 120, 125, 150, 180, 200, 225, 250, 300, 360, 375, 450, 500, 600, 750, 900, 1000, 1125, 1500, 1800, 2250, 3000, 4500, 9000
 * House Nr   9500    Presents     218400   Visited by   24   Elves , 1, 2, 4, 5, 10, 19, 20, 25, 38, 50, 76, 95, 100, 125, 190, 250, 380, 475, 500, 950, 1900, 2375, 4750, 9500
 * 
 * </pre>
 */
public class Day20_InfiniteElvesAndInfiniteHouses
{
  public static void main( String[] args )
  {
    HashMap< Integer, Elf > hash_map = new HashMap< Integer, Elf >();

    for ( long house_nr = 1; house_nr < 40; house_nr += 1 )
    {
      calc( hash_map, house_nr );
    }

    wl( "" );

    for ( long house_nr = 100; house_nr < 400; house_nr += 10 )
    {
      calc( hash_map, house_nr );
    }

    wl( "" );

    for ( long house_nr = 1000; house_nr < 10000; house_nr += 500 )
    {
      calc( hash_map, house_nr );
    }

    wl( "" );

    System.exit( 0 );
  }

  private static void calc( HashMap< Integer, Elf > hash_map, long pHouseNr )
  {
    long presents_to_match = 36_000_000;

    long cur_house_presents = 0;

    long cur_house_nr = pHouseNr;

    long cur_house_count_elfs = 0;

    StringBuilder cur_house_visited_by_elf_nr = new StringBuilder();

    int cur_elf_nr = 1;

    while ( ( cur_elf_nr <= cur_house_nr ) && ( cur_house_presents < presents_to_match ) )
    {
      Elf cur_elf_inst = hash_map.get( Integer.valueOf( cur_elf_nr ) );

      if ( cur_elf_inst == null )
      {
        cur_elf_inst = new Elf( cur_elf_nr );

        hash_map.put( Integer.valueOf( cur_elf_nr ), cur_elf_inst );
      }

      if ( cur_elf_inst.visitHouseNr( cur_house_nr ) )
      {
        cur_house_presents += cur_elf_inst.getPresents();

        cur_house_visited_by_elf_nr.append( ", "       );
        cur_house_visited_by_elf_nr.append( cur_elf_nr );

        cur_house_count_elfs++;
      }

      cur_elf_nr++;
    }

    wl( String.format( "House Nr %6d    Presents %10d   Visited by %4d   Elves %s", cur_house_nr, cur_house_presents, cur_house_count_elfs, cur_house_visited_by_elf_nr.toString() ) );
  }

  private static class Elf
  {
    public long nr;

    public long presents;

    public Elf( int pNr )
    {
      nr = pNr;

      presents = nr * 10;
    }

    public boolean visitHouseNr( long pHouseNr )
    {
      return ( pHouseNr % nr ) == 0;
    }

    public long getPresents()
    {
      return presents;
    }
  }

  private static void wl( String pString ) // wl = short for "write log"
  {
    System.out.println( pString );
  }

}
