import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class CrossandKnaughts {
   static ArrayList <Integer> playerPositions=new ArrayList<Integer>();
   static ArrayList <Integer> cpuPositions=new ArrayList<Integer>();

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Random random=new Random(1);

        char[][]gameboard={{' ','|',' ','|',' '},
                           {'-','+','-','+','-'},
                           {' ','|',' ','|',' '},
                           {'-','+','-','+','-'},
                           {' ','|',' ','|',' '}                };
        PrintGameboard(gameboard);
        while(true) {
            System.out.println("YOUR TURN!");
            int playerPos = scanner.nextInt();

            while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
                System.out.println("POSITION TAKEN! pick a vacant spot!");
                playerPos = scanner.nextInt();
            }
            setPiece(gameboard, playerPos, "player");

            String result = checkwinner();
            System.out.println(result);
            if (result.length() > 0)
                break;
            System.out.println("CPU's TURN!");
            int cpupos=( random.nextInt(9)+1);
            while (playerPositions.contains(cpupos) || cpuPositions.contains(cpupos)) {
               cpupos=random.nextInt(9)+1;
            }
            setPiece(gameboard,cpupos,"pc");

             result = checkwinner();
            System.out.println(result);
            if (result.length() > 0)
                break;
        }
    }

    public static void  PrintGameboard(char [][]gameboard){
        for (char[] row:gameboard)
        {for (char c:row){
            System.out.print(c);
        }
            System.out.println();
        }
    }
    public static void setPiece (char[][] gameboard,int position,String user) {
        int timedelay=0;
        char symbol=' ';

        if (user.equals("player")){
           timedelay=750;
            symbol='X';
            playerPositions.add(position);
        } else if (user.equals("pc")){
            timedelay=1500;
            symbol='O';
            cpuPositions.add(position);

        }
        switch (position)
        {   case 1: gameboard[0][0]=symbol;break;
            case 2: gameboard[0][2]=symbol;break;
            case 3: gameboard[0][4]=symbol;break;
            case 4: gameboard[2][0]=symbol;break;
            case 5: gameboard[2][2]=symbol;break;
            case 6: gameboard[2][4]=symbol;break;
            case 7: gameboard[4][0]=symbol;break;
            case 8: gameboard[4][2]=symbol;break;
            case 9: gameboard[4][4]=symbol;break;
        }
        try {
            TimeUnit.MILLISECONDS.sleep(timedelay);
        }catch (InterruptedException ie)
        {ie.printStackTrace();
        }
        PrintGameboard(gameboard);
    }

    public static String checkwinner(){
        List toprow=Arrays.asList(1,2,3);
        List midrow=Arrays.asList(4,5,6);
        List bottomrow=Arrays.asList(7,8,9);
        List leftcol=Arrays.asList(1,4,7);
        List midcol=Arrays.asList(2,5,8);
        List rightcol=Arrays.asList(3,6,9);
        List cross1=Arrays.asList(1,5,9);
        List cross2=Arrays.asList(3,5,7);


        List<List>winning=new ArrayList<>();
        winning.add(toprow);
        winning.add(midrow);
        winning.add(bottomrow);
        winning.add(leftcol);
        winning.add(midcol);
        winning.add(rightcol);
        winning.add(cross1);
        winning.add(cross2);

        for(List a:winning)
        {  if (playerPositions.containsAll(a)){
            return "congrtulations!you have won!";
        }else if (cpuPositions.containsAll(a)){
            return "SORRY!CPU winss!";}
           else if(cpuPositions.size()+playerPositions.size()==9)
             {
                     return "Match Tie!";
              }
        }

    return "";
    }


}

