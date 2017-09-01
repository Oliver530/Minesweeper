# Minesweeper4java
A simple test driven minesweeper clone for java

## Build
1. clone repository to your local computer
2. run "./gradlew build" in project directory

## How to play (console view)

```sh
java -jar minesweeper4java-0.1.jar

Enter dimension (>3): <dimension>
- open <row> <col>
- mark <row> <col>
- help
- exit
```

Example:
```sh
Enter dimension (>3): 12
There are 14 mines. Good luck!
   | 0  1  2  3  4  5  6  7  8  9 10 11 
---|------------------------------------
 0 | ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  · 
 1 | ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  · 
 2 | ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  · 
 3 | ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  · 
 4 | ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  · 
 5 | ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  · 
 6 | ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  · 
 7 | ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  · 
 8 | ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  · 
 9 | ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  · 
10 | ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  · 
11 | ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  · 

Command: open 5 5

   | 0  1  2  3  4  5  6  7  8  9 10 11 
---|------------------------------------
 0 | ·  ·  ·  1     1  ·  ·  ·  ·  ·  · 
 1 | ·  ·  ·  1     1  ·  ·  ·  ·  ·  · 
 2 | ·  ·  3  1     1  1  1  1  ·  ·  · 
 3 | 1  1  1                 1  ·  ·  · 
 4 |                         1  ·  ·  · 
 5 |                         1  ·  ·  · 
 6 |                         1  ·  ·  · 
 7 |    1  2  2  1     1  1  1  ·  ·  · 
 8 |    1  ·  ·  1     1  ·  ·  ·  ·  · 
 9 |    1  2  2  1     1  1  1  ·  ·  · 
10 |                         1  ·  ·  · 
11 |                         1  ·  ·  · 

Command: mark 1 6

   | 0  1  2  3  4  5  6  7  8  9 10 11 
---|------------------------------------
 0 | ·  ·  ·  1     1  ·  ·  ·  ·  ·  · 
 1 | ·  ·  ·  1     1  @  ·  ·  ·  ·  · 
 2 | ·  ·  3  1     1  1  1  1  ·  ·  · 
 3 | 1  1  1                 1  ·  ·  · 
 4 |                         1  ·  ·  · 
 5 |                         1  ·  ·  · 
 6 |                         1  ·  ·  · 
 7 |    1  2  2  1     1  1  1  ·  ·  · 
 8 |    1  ·  ·  1     1  ·  ·  ·  ·  · 
 9 |    1  2  2  1     1  1  1  ·  ·  · 
10 |                         1  ·  ·  · 
11 |                         1  ·  ·  · 

...
```
Enjoy!

**Feedback and pull requests appreciated**

## ToDos
- Use Logging Framework
- Graphical user interface
- UML diagrams
- arc42 documentation