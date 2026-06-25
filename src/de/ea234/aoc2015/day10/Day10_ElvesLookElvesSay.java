package de.ea234.aoc2015.day10;

import java.util.Arrays;

/**
 * <pre>
 * 
 * --- Day 10: Elves Look, Elves Say ---
 * https://adventofcode.com/2015/day/10
 * 
 * https://www.reddit.com/r/adventofcode/comments/3w6h3m/day_10_solutions/
 * 
 * 1                      11
 * 11                     21
 * 1211                   111221
 * 111221                 312211
 * 211                    1221
 * 
 * 1                      11
 * 11                     21
 * 1211                   111221
 * 111221                 312211
 * 211                    1221
 * 
 * 
 * 
 * 
 * pInputString    = 3113322113
 * pNumberOfRounds = 50
 * 
 * Nr   0  Length         12   Time 00:00:00:000  ,1,3,2,1,2,3,2,2,2,1,1,3,65,0
 * Nr   1  Length         18   Time 00:00:00:000  ,1,1,1,3,1,2,1,1,1,2,1,3,3,2,2,1,1,3,65,0
 * Nr   2  Length         22   Time 00:00:00:000  ,3,1,1,3,1,1,1,2,3,1,1,2,1,1,2,3,2,2,2,1,1,3,65,0
 * Nr   3  Length         28   Time 00:00:00:000  ,1,3,2,1,1,3,3,1,1,2,1,3,2,1,1,2,2,1,1,2,1,3,3,2,2,1,1,3,65,0
 * Nr   4  Length         38   Time 00:00:00:000  ,1,1,1,3,1,2,2,1,2,3,2,1,1,2,1,1,1,3,1,2,2,1,2,2,2,1,1,2,1,1,2,3,2,2,2,1,1,3,65,0
 * Nr   5  Length         48   Time 00:00:00:000  ,3,1,1,3,1,1,2,2,1,1,1,2,1,3,1,2,2,1,1,2,3,1,1,3,1,1,2,2,1,1,3,2,2,1,1,2,2,1,1,2,1,3,3,2,2,1,1,3,65,0
 * Nr   6  Length         60   Time 00:00:00:000  ,1,3,2,1,1,3,2,1,2,2,3,1,1,2,1,1,1,3,1,1,2,2,2,1,1,2,1,3,2,1,1,3,2,1,2,2,2,1,1,3,2,2,2,1,2,2,2,1,1,2,1,1,2,3,2,2,2,1,1,3,65,0
 * Nr   7  Length         76   Time 00:00:00:000  ,1,1,1,3,1,2,2,1,1,3,1,2,1,1,2,2,1,3,2,1,1,2,3,1,1,3,2,1,3,2,2,1,1,2,1,1,1,3,1,2,2,1,1,3,1,2,1,1,3,2,2,1,1,3,3,2,1,1,3,2,2,1,1,2,2,1,1,2,1,3,3,2,2,1,1,3,65,0
 * Nr   8  Length        100   Time 00:00:00:000  ,3,1,1,3,1,1,2,2,2,1,1,3,1,1,1,2,2,1,2,2,1,1,1,3,1,2,2,1,1,2,1,3,2,1,1,3,1,2,1,1,1,3,2,2,2,1,1,2,3,1,1,3,1,1,2,2,2,1,1,3,1,1,1,2,2,1,1,3,2,2,2,1,2,3,1,2,2,1,1,3
 * Nr   9  Length        118   Time 00:00:00:000  ,1,3,2,1,1,3,2,1,3,2,2,1,1,3,3,1,2,2,1,1,2,2,3,1,1,3,1,1,2,2,2,1,1,2,1,1,1,3,1,2,2,1,1,3,1,1,1,2,3,1,1,3,3,2,2,1,1,2,1,3,2,1,1,3,2,1,3,2,2,1,1,3,3,1,2,2,2,1,1,3
 * Nr  10  Length        146   Time 00:00:00:000  ,1,1,1,3,1,2,2,1,1,3,1,2,1,1,1,3,2,2,2,1,2,3,1,1,2,2,2,1,2,2,1,3,2,1,1,3,2,1,3,2,2,1,1,2,3,1,1,3,1,1,2,2,2,1,1,3,3,1,1,2,1,3,2,1,2,3,2,2,2,1,1,2,1,1,1,3,1,2,2,1
 * Nr  11  Length        192   Time 00:00:00:000  ,3,1,1,3,1,1,2,2,2,1,1,3,1,1,1,2,3,1,1,3,3,2,1,1,1,2,1,3,2,1,3,2,1,1,2,2,1,1,1,3,1,2,2,1,1,3,1,2,1,1,1,3,2,2,2,1,1,2,1,3,2,1,1,3,2,1,3,2,2,1,2,3,2,1,1,2,1,1,1,3
 * Nr  12  Length        246   Time 00:00:00:000  ,1,3,2,1,1,3,2,1,3,2,2,1,1,3,3,1,1,2,1,3,2,1,2,3,1,2,3,1,1,2,1,1,1,3,1,2,1,1,1,3,1,2,2,1,2,2,3,1,1,3,1,1,2,2,2,1,1,3,1,1,1,2,3,1,1,3,3,2,2,1,1,2,1,1,1,3,1,2,2,1
 * Nr  13  Length        328   Time 00:00:00:000  ,1,1,1,3,1,2,2,1,1,3,1,2,1,1,1,3,2,2,2,1,2,3,2,1,1,2,1,1,1,3,1,2,1,1,1,2,1,3,1,1,1,2,1,3,2,1,1,2,3,1,1,3,1,1,1,2,3,1,1,3,1,1,2,2,1,1,2,2,1,3,2,1,1,3,2,1,3,2,2,1
 * Nr  14  Length        428   Time 00:00:00:000  ,3,1,1,3,1,1,2,2,2,1,1,3,1,1,1,2,3,1,1,3,3,2,1,1,1,2,1,3,1,2,2,1,1,2,3,1,1,3,1,1,1,2,3,1,1,2,1,1,1,3,3,1,1,2,1,1,1,3,1,2,2,1,1,2,1,3,2,1,1,3,3,1,1,2,1,3,2,1,1,3
 * Nr  15  Length        562   Time 00:00:00:000  ,1,3,2,1,1,3,2,1,3,2,2,1,1,3,3,1,1,2,1,3,2,1,2,3,1,2,3,1,1,2,1,1,1,3,1,1,2,2,2,1,1,2,1,3,2,1,1,3,3,1,1,2,1,3,2,1,1,2,3,1,2,3,2,1,1,2,3,1,1,3,1,1,2,2,2,1,1,2,1,1
 * Nr  16  Length        732   Time 00:00:00:000  ,1,1,1,3,1,2,2,1,1,3,1,2,1,1,1,3,2,2,2,1,2,3,2,1,1,2,1,1,1,3,1,2,1,1,1,2,1,3,1,1,1,2,1,3,2,1,1,2,3,1,1,3,2,1,3,2,2,1,1,2,1,1,1,3,1,2,2,1,2,3,2,1,1,2,1,1,1,3,1,2
 * Nr  17  Length        970   Time 00:00:00:000  ,3,1,1,3,1,1,2,2,2,1,1,3,1,1,1,2,3,1,1,3,3,2,1,1,1,2,1,3,1,2,2,1,1,2,3,1,1,3,1,1,1,2,3,1,1,2,1,1,1,3,3,1,1,2,1,1,1,3,1,2,2,1,1,2,1,3,2,1,1,3,1,2,1,1,1,3,2,2,2,1
 * Nr  18  Length       1250   Time 00:00:00:000  ,1,3,2,1,1,3,2,1,3,2,2,1,1,3,3,1,1,2,1,3,2,1,2,3,1,2,3,1,1,2,1,1,1,3,1,1,2,2,2,1,1,2,1,3,2,1,1,3,3,1,1,2,1,3,2,1,1,2,3,1,2,3,2,1,1,2,3,1,1,3,1,1,2,2,2,1,1,2,1,1
 * Nr  19  Length       1622   Time 00:00:00:000  ,1,1,1,3,1,2,2,1,1,3,1,2,1,1,1,3,2,2,2,1,2,3,2,1,1,2,1,1,1,3,1,2,1,1,1,2,1,3,1,1,1,2,1,3,2,1,1,2,3,1,1,3,2,1,3,2,2,1,1,2,1,1,1,3,1,2,2,1,2,3,2,1,1,2,1,1,1,3,1,2
 * Nr  20  Length       2128   Time 00:00:00:001  ,3,1,1,3,1,1,2,2,2,1,1,3,1,1,1,2,3,1,1,3,3,2,1,1,1,2,1,3,1,2,2,1,1,2,3,1,1,3,1,1,1,2,3,1,1,2,1,1,1,3,3,1,1,2,1,1,1,3,1,2,2,1,1,2,1,3,2,1,1,3,1,2,1,1,1,3,2,2,2,1
 * Nr  21  Length       2754   Time 00:00:00:000  ,1,3,2,1,1,3,2,1,3,2,2,1,1,3,3,1,1,2,1,3,2,1,2,3,1,2,3,1,1,2,1,1,1,3,1,1,2,2,2,1,1,2,1,3,2,1,1,3,3,1,1,2,1,3,2,1,1,2,3,1,2,3,2,1,1,2,3,1,1,3,1,1,2,2,2,1,1,2,1,1
 * Nr  22  Length       3592   Time 00:00:00:000  ,1,1,1,3,1,2,2,1,1,3,1,2,1,1,1,3,2,2,2,1,2,3,2,1,1,2,1,1,1,3,1,2,1,1,1,2,1,3,1,1,1,2,1,3,2,1,1,2,3,1,1,3,2,1,3,2,2,1,1,2,1,1,1,3,1,2,2,1,2,3,2,1,1,2,1,1,1,3,1,2
 * Nr  23  Length       4728   Time 00:00:00:000  ,3,1,1,3,1,1,2,2,2,1,1,3,1,1,1,2,3,1,1,3,3,2,1,1,1,2,1,3,1,2,2,1,1,2,3,1,1,3,1,1,1,2,3,1,1,2,1,1,1,3,3,1,1,2,1,1,1,3,1,2,2,1,1,2,1,3,2,1,1,3,1,2,1,1,1,3,2,2,2,1
 * Nr  24  Length       6162   Time 00:00:00:000  ,1,3,2,1,1,3,2,1,3,2,2,1,1,3,3,1,1,2,1,3,2,1,2,3,1,2,3,1,1,2,1,1,1,3,1,1,2,2,2,1,1,2,1,3,2,1,1,3,3,1,1,2,1,3,2,1,1,2,3,1,2,3,2,1,1,2,3,1,1,3,1,1,2,2,2,1,1,2,1,1
 * Nr  25  Length       8072   Time 00:00:00:000  ,1,1,1,3,1,2,2,1,1,3,1,2,1,1,1,3,2,2,2,1,2,3,2,1,1,2,1,1,1,3,1,2,1,1,1,2,1,3,1,1,1,2,1,3,2,1,1,2,3,1,1,3,2,1,3,2,2,1,1,2,1,1,1,3,1,2,2,1,2,3,2,1,1,2,1,1,1,3,1,2
 * Nr  26  Length      10504   Time 00:00:00:000  ,3,1,1,3,1,1,2,2,2,1,1,3,1,1,1,2,3,1,1,3,3,2,1,1,1,2,1,3,1,2,2,1,1,2,3,1,1,3,1,1,1,2,3,1,1,2,1,1,1,3,3,1,1,2,1,1,1,3,1,2,2,1,1,2,1,3,2,1,1,3,1,2,1,1,1,3,2,2,2,1
 * Nr  27  Length      13736   Time 00:00:00:000  ,1,3,2,1,1,3,2,1,3,2,2,1,1,3,3,1,1,2,1,3,2,1,2,3,1,2,3,1,1,2,1,1,1,3,1,1,2,2,2,1,1,2,1,3,2,1,1,3,3,1,1,2,1,3,2,1,1,2,3,1,2,3,2,1,1,2,3,1,1,3,1,1,2,2,2,1,1,2,1,1
 * Nr  28  Length      17874   Time 00:00:00:000  ,1,1,1,3,1,2,2,1,1,3,1,2,1,1,1,3,2,2,2,1,2,3,2,1,1,2,1,1,1,3,1,2,1,1,1,2,1,3,1,1,1,2,1,3,2,1,1,2,3,1,1,3,2,1,3,2,2,1,1,2,1,1,1,3,1,2,2,1,2,3,2,1,1,2,1,1,1,3,1,2
 * Nr  29  Length      23248   Time 00:00:00:000  ,3,1,1,3,1,1,2,2,2,1,1,3,1,1,1,2,3,1,1,3,3,2,1,1,1,2,1,3,1,2,2,1,1,2,3,1,1,3,1,1,1,2,3,1,1,2,1,1,1,3,3,1,1,2,1,1,1,3,1,2,2,1,1,2,1,3,2,1,1,3,1,2,1,1,1,3,2,2,2,1
 * Nr  30  Length      30322   Time 00:00:00:001  ,1,3,2,1,1,3,2,1,3,2,2,1,1,3,3,1,1,2,1,3,2,1,2,3,1,2,3,1,1,2,1,1,1,3,1,1,2,2,2,1,1,2,1,3,2,1,1,3,3,1,1,2,1,3,2,1,1,2,3,1,2,3,2,1,1,2,3,1,1,3,1,1,2,2,2,1,1,2,1,1
 * Nr  31  Length      39392   Time 00:00:00:000  ,1,1,1,3,1,2,2,1,1,3,1,2,1,1,1,3,2,2,2,1,2,3,2,1,1,2,1,1,1,3,1,2,1,1,1,2,1,3,1,1,1,2,1,3,2,1,1,2,3,1,1,3,2,1,3,2,2,1,1,2,1,1,1,3,1,2,2,1,2,3,2,1,1,2,1,1,1,3,1,2
 * Nr  32  Length      51438   Time 00:00:00:001  ,3,1,1,3,1,1,2,2,2,1,1,3,1,1,1,2,3,1,1,3,3,2,1,1,1,2,1,3,1,2,2,1,1,2,3,1,1,3,1,1,1,2,3,1,1,2,1,1,1,3,3,1,1,2,1,1,1,3,1,2,2,1,1,2,1,3,2,1,1,3,1,2,1,1,1,3,2,2,2,1
 * Nr  33  Length      67070   Time 00:00:00:000  ,1,3,2,1,1,3,2,1,3,2,2,1,1,3,3,1,1,2,1,3,2,1,2,3,1,2,3,1,1,2,1,1,1,3,1,1,2,2,2,1,1,2,1,3,2,1,1,3,3,1,1,2,1,3,2,1,1,2,3,1,2,3,2,1,1,2,3,1,1,3,1,1,2,2,2,1,1,2,1,1
 * Nr  34  Length      87402   Time 00:00:00:001  ,1,1,1,3,1,2,2,1,1,3,1,2,1,1,1,3,2,2,2,1,2,3,2,1,1,2,1,1,1,3,1,2,1,1,1,2,1,3,1,1,1,2,1,3,2,1,1,2,3,1,1,3,2,1,3,2,2,1,1,2,1,1,1,3,1,2,2,1,2,3,2,1,1,2,1,1,1,3,1,2
 * Nr  35  Length     114056   Time 00:00:00:001  ,3,1,1,3,1,1,2,2,2,1,1,3,1,1,1,2,3,1,1,3,3,2,1,1,1,2,1,3,1,2,2,1,1,2,3,1,1,3,1,1,1,2,3,1,1,2,1,1,1,3,3,1,1,2,1,1,1,3,1,2,2,1,1,2,1,3,2,1,1,3,1,2,1,1,1,3,2,2,2,1
 * Nr  36  Length     148740   Time 00:00:00:001  ,1,3,2,1,1,3,2,1,3,2,2,1,1,3,3,1,1,2,1,3,2,1,2,3,1,2,3,1,1,2,1,1,1,3,1,1,2,2,2,1,1,2,1,3,2,1,1,3,3,1,1,2,1,3,2,1,1,2,3,1,2,3,2,1,1,2,3,1,1,3,1,1,2,2,2,1,1,2,1,1
 * Nr  37  Length     193828   Time 00:00:00:001  ,1,1,1,3,1,2,2,1,1,3,1,2,1,1,1,3,2,2,2,1,2,3,2,1,1,2,1,1,1,3,1,2,1,1,1,2,1,3,1,1,1,2,1,3,2,1,1,2,3,1,1,3,2,1,3,2,2,1,1,2,1,1,1,3,1,2,2,1,2,3,2,1,1,2,1,1,1,3,1,2
 * Nr  38  Length     252594   Time 00:00:00:002  ,3,1,1,3,1,1,2,2,2,1,1,3,1,1,1,2,3,1,1,3,3,2,1,1,1,2,1,3,1,2,2,1,1,2,3,1,1,3,1,1,1,2,3,1,1,2,1,1,1,3,3,1,1,2,1,1,1,3,1,2,2,1,1,2,1,3,2,1,1,3,1,2,1,1,1,3,2,2,2,1
 * 
 * Nr  39  Length     329356   Time 00:00:00:002  ,1,3,2,1,1,3,2,1,3,2,2,1,1,3,3,1,1,2,1,3,2,1,2,3,1,2,3,1,1,2,1,1,1,3,1,1,2,2,2,1,1,2,1,3,2,1,1,3,3,1,1,2,1,3,2,1,1,2,3,1,2,3,2,1,1,2,3,1,1,3,1,1,2,2,2,1,1,2,1,1
 * 
 * Nr  40  Length     429204   Time 00:00:00:003  ,1,1,1,3,1,2,2,1,1,3,1,2,1,1,1,3,2,2,2,1,2,3,2,1,1,2,1,1,1,3,1,2,1,1,1,2,1,3,1,1,1,2,1,3,2,1,1,2,3,1,1,3,2,1,3,2,2,1,1,2,1,1,1,3,1,2,2,1,2,3,2,1,1,2,1,1,1,3,1,2
 * Nr  41  Length     559092   Time 00:00:00:004  ,3,1,1,3,1,1,2,2,2,1,1,3,1,1,1,2,3,1,1,3,3,2,1,1,1,2,1,3,1,2,2,1,1,2,3,1,1,3,1,1,1,2,3,1,1,2,1,1,1,3,3,1,1,2,1,1,1,3,1,2,2,1,1,2,1,3,2,1,1,3,1,2,1,1,1,3,2,2,2,1
 * Nr  42  Length     729412   Time 00:00:00:005  ,1,3,2,1,1,3,2,1,3,2,2,1,1,3,3,1,1,2,1,3,2,1,2,3,1,2,3,1,1,2,1,1,1,3,1,1,2,2,2,1,1,2,1,3,2,1,1,3,3,1,1,2,1,3,2,1,1,2,3,1,2,3,2,1,1,2,3,1,1,3,1,1,2,2,2,1,1,2,1,1
 * Nr  43  Length     950310   Time 00:00:00:006  ,1,1,1,3,1,2,2,1,1,3,1,2,1,1,1,3,2,2,2,1,2,3,2,1,1,2,1,1,1,3,1,2,1,1,1,2,1,3,1,1,1,2,1,3,2,1,1,2,3,1,1,3,2,1,3,2,2,1,1,2,1,1,1,3,1,2,2,1,2,3,2,1,1,2,1,1,1,3,1,2
 * Nr  44  Length    1239364   Time 00:00:00:008  ,3,1,1,3,1,1,2,2,2,1,1,3,1,1,1,2,3,1,1,3,3,2,1,1,1,2,1,3,1,2,2,1,1,2,3,1,1,3,1,1,1,2,3,1,1,2,1,1,1,3,3,1,1,2,1,1,1,3,1,2,2,1,1,2,1,3,2,1,1,3,1,2,1,1,1,3,2,2,2,1
 * Nr  45  Length    1615860   Time 00:00:00:011  ,1,3,2,1,1,3,2,1,3,2,2,1,1,3,3,1,1,2,1,3,2,1,2,3,1,2,3,1,1,2,1,1,1,3,1,1,2,2,2,1,1,2,1,3,2,1,1,3,3,1,1,2,1,3,2,1,1,2,3,1,2,3,2,1,1,2,3,1,1,3,1,1,2,2,2,1,1,2,1,1
 * Nr  46  Length    2106324   Time 00:00:00:006  ,1,1,1,3,1,2,2,1,1,3,1,2,1,1,1,3,2,2,2,1,2,3,2,1,1,2,1,1,1,3,1,2,1,1,1,2,1,3,1,1,1,2,1,3,2,1,1,2,3,1,1,3,2,1,3,2,2,1,1,2,1,1,1,3,1,2,2,1,2,3,2,1,1,2,1,1,1,3,1,2
 * Nr  47  Length    2746068   Time 00:00:00:004  ,3,1,1,3,1,1,2,2,2,1,1,3,1,1,1,2,3,1,1,3,3,2,1,1,1,2,1,3,1,2,2,1,1,2,3,1,1,3,1,1,1,2,3,1,1,2,1,1,1,3,3,1,1,2,1,1,1,3,1,2,2,1,1,2,1,3,2,1,1,3,1,2,1,1,1,3,2,2,2,1
 * Nr  48  Length    3579328   Time 00:00:00:006  ,1,3,2,1,1,3,2,1,3,2,2,1,1,3,3,1,1,2,1,3,2,1,2,3,1,2,3,1,1,2,1,1,1,3,1,1,2,2,2,1,1,2,1,3,2,1,1,3,3,1,1,2,1,3,2,1,1,2,3,1,2,3,2,1,1,2,3,1,1,3,1,1,2,2,2,1,1,2,1,1
 * 
 * Nr  49  Length    4666278   Time 00:00:00:007  ,1,1,1,3,1,2,2,1,1,3,1,2,1,1,1,3,2,2,2,1,2,3,2,1,1,2,1,1,1,3,1,2,1,1,1,2,1,3,1,1,1,2,1,3,2,1,1,2,3,1,1,3,2,1,3,2,2,1,1,2,1,1,1,3,1,2,2,1,2,3,2,1,1,2,1,1,1,3,1,2
 * 
 * Result Part 1 329356
 * Result Part 2 4666278
 * 
 * -----------------------------------------------------------------------------------
 * Old test with String concatenation: 
 * 
 * Nr   0  Length         22   Time 00:00:00:000
 * Nr   1  Length         52   Time 00:00:00:000
 * Nr   2  Length        122   Time 00:00:00:000
 * Nr   3  Length        280   Time 00:00:00:001
 * Nr   4  Length        636   Time 00:00:00:000
 * Nr   5  Length       1446   Time 00:00:00:001
 * Nr   6  Length       3306   Time 00:00:00:003
 * Nr   7  Length       7596   Time 00:00:00:010
 * Nr   8  Length      17498   Time 00:00:00:026
 * Nr   9  Length      40330   Time 00:00:00:097
 * Nr  10  Length      92882   Time 00:00:00:364
 * Nr  11  Length     213570   Time 00:00:01:486
 * Nr  12  Length     490064   Time 00:00:07:187
 * Nr  13  Length    1122108   Time 00:00:35:140
 *  
 * </pre>
 */
public class Day10_ElvesLookElvesSay
{
  private static final short STRING_END_CHARACTER = 65; // = ASCII A

  public static void main( String[] args )
  {
    testGetNewString( "1"      );
    testGetNewString( "11"     );
    testGetNewString( "1211"   );
    testGetNewString( "111221" );
    testGetNewString( "211"    );

    calculate01();

    System.exit( 0 );
  }

  private static void calculate01()
  {
    wl( "" );
    wl( "" );

    String cur_string = "3113322113";

    int result_part_01 = calcLength( cur_string, 40 );

    int result_part_02 = calcLength( cur_string, 50 );

    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
    wl( "Result Part 2 " + result_part_02 );
    wl( "" );
    wl( "" );
  }

  private static int calcLength( String pInputString, int pNumberOfRounds )
  {
    wl( "" );
    wl( "pInputString    = " + pInputString );
    wl( "pNumberOfRounds = " + pNumberOfRounds );

    short[][] matrix_numbers = new short[ 2 ][ 10_000_000 ];

    int write_set_nr    = 1;
    int write_cur_index = 0;

    int read_set_nr    = 0;
    int read_cur_index = 0;

    for ( int i = 0; i < pInputString.length(); i++ )
    {
      matrix_numbers[ write_set_nr ][ i ] = (short) ( pInputString.charAt( i ) - '0' );
    }

    matrix_numbers[ write_set_nr ][ pInputString.length() ] = STRING_END_CHARACTER; // = ASCII A

    wl( "" );

    for ( int round_nr = 0; round_nr < pNumberOfRounds; round_nr++ )
    {
      long time_start = System.currentTimeMillis();

      /*
       * Toggle between the write and read array
       */
      if ( write_set_nr == 1 )
      {
        write_set_nr = 0;

        read_set_nr  = 1;
      }
      else
      {
        write_set_nr = 1;

        read_set_nr  = 0;
      }

      /*
       * The write and read index start at 0
       */
      write_cur_index = 0;

      read_cur_index  = 0;


      /*
       * Get the current number an position 0
       */
      short cur_number_char = matrix_numbers[ read_set_nr ][ read_cur_index ];

      int cur_number_count  = 0;

      /*
       * While-loop until the end value is reached
       */
      while ( matrix_numbers[ read_set_nr ][ read_cur_index ] != STRING_END_CHARACTER )
      {
        if ( matrix_numbers[ read_set_nr ][ read_cur_index ] == cur_number_char )
        {
          /*
           * If the current number is equal to the number char, 
           * the number count is increased.
           */
          cur_number_count++;
        }
        else
        {
          /*
           * If the current number has changed, 
           * - the number of times is written to the array
           * - the current number ist written to the array
           * - the end character is written to the array
           */
          write_cur_index = writeNumberToArray( matrix_numbers, write_set_nr, write_cur_index, cur_number_count );

          write_cur_index++;

          matrix_numbers[ write_set_nr ][ write_cur_index ] = cur_number_char;

          write_cur_index++;

          matrix_numbers[ write_set_nr ][ write_cur_index ] = STRING_END_CHARACTER;

          /*
           * Set the new number to compare
           */
          cur_number_char = matrix_numbers[ read_set_nr ][ read_cur_index ];

          /*
           * Set the number count to 1.
           */
          cur_number_count = 1;
        }

        read_cur_index++;
      }

      /*
       * Write the last number
       */
      write_cur_index = writeNumberToArray( matrix_numbers, write_set_nr, write_cur_index, cur_number_count );

      write_cur_index++;

      matrix_numbers[ write_set_nr ][ write_cur_index ] = cur_number_char;

      write_cur_index++;

      matrix_numbers[ write_set_nr ][ write_cur_index ] = STRING_END_CHARACTER;

      wl( String.format( "Nr %3d  Length %10d   Time %s  " + getDebugString( matrix_numbers, write_set_nr, 0, Math.min( write_cur_index + 2, 80 ) ), round_nr, write_cur_index, getDebugLaufzeit( System.currentTimeMillis() - time_start ) ) );
    }

    return write_cur_index;
  }

  private static int writeNumberToArray( short[][] matrix_numbers, int pSetNr, int pIndexStart, int pNumber )
  {
    int tmp = pNumber;

    int digits  = 1;

    int divisor = 1;

    while ( tmp >= 10 )
    {
      tmp /= 10;

      divisor *= 10;

      digits++;
    }

    int write_index = pIndexStart;

    for ( int i = 0; i < digits; i++ )
    {
      matrix_numbers[ pSetNr ][ write_index ] = (short) ( pNumber / divisor );

      write_index++;

      pNumber %= divisor;

      divisor /= 10;
    }

    matrix_numbers[ pSetNr ][ write_index ] = STRING_END_CHARACTER;

    return write_index - 1;
  }

  private static String getDebugString( short[][] matrix_numbers, int pSetNr, int pIndexStart, int pIndexEnd )
  {
    String debug_string = "";

    for ( int i = pIndexStart; i < pIndexEnd; i++ )
    {
      debug_string += "," + matrix_numbers[ pSetNr ][ i ];
    }

    return debug_string;
  }

  private static String testGetNewString( String pInput )
  {
    String result_string = getNewString( pInput );

    wl( String.format( "%-20s   %s", pInput, result_string ) );

    return result_string;
  }

  private static String getNewString( String pInput )
  {
    String result_string = "";

    int cur_index = 0;

    char cur_number_char = pInput.charAt( 0 );
    long cur_number_count = 0;

    while ( cur_index < pInput.length() )
    {
      char akt_char = pInput.charAt( cur_index );

      if ( akt_char == cur_number_char )
      {
        cur_number_count++;
      }
      else
      {
        result_string += "" + cur_number_count;

        result_string += "" + cur_number_char;

        cur_number_char = akt_char;

        cur_number_count = 1;
      }

      cur_index++;
    }

    result_string += "" + cur_number_count;

    result_string += "" + cur_number_char;

    return result_string;
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
