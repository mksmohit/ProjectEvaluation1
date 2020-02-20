import java.util.*;
class OthelloBoard {
	private int board[][];
	final static int player1Symbol = 1;
	final static int player2Symbol = 2;

	public OthelloBoard() {
		board = new int[8][8];
		board[3][3] = player1Symbol;
		board[3][4] = player2Symbol;
		board[4][3] = player2Symbol;
		board[4][4] = player1Symbol;
	}

	public void print() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
    
    public boolean movehelper(int x, int y, int[][] moves, int index, 
                             int sym, int opp, int count){
        if(x<0 || x>=8 || y<0 || y>=8){
            return false;
        }
        if(board[x][y]==0){
            return false;
        }
        
        
        if(board[x][y]==sym && count==1){
            return false;
        }
        if(board[x][y]==sym && count>1){
            return true;
        }
        
        boolean ans=movehelper(x+moves[index][0], y+moves[index][1]*1, moves, index
                              , sym, opp, count+1);
        if(ans==true){
            board[x][y]=sym;
            return true;
        }else{
            return false;
        } 
    }

	public boolean move(int symbol, int x, int y){
        int[][] moves={ 
                    {0, 1},
                       {1,1},
                       {1,0},
                       {1,-1},
                       {0,-1},
                       {-1,-1},
                       {-1,0},
                       {-1,1}
        };
        
        // int opp=(symbol==2)?1:2;
        int opp;
        if(symbol==2){
            opp=1;
        }else{
            opp=2;
        }
        
        //move in all directions
        boolean flag=false;
        if(board[x][y]!=0){
            return false;
        }
        board[x][y]=symbol;
        for(int i=0; i<8; i++){
            boolean ans=movehelper(x,y, moves, i, symbol, opp, 0);
            if(ans==true){
                flag=true;
            }
        }
        if(flag==false) {
	        	board[x][y]=0;
	        }
        
        return flag;
	}
}
public class Main {

	static Scanner s = new Scanner(System.in);

	final static int player1Symbol = 1;
	final static int player2Symbol = 2;

	public static void main(String[] args) {
		OthelloBoard b = new OthelloBoard();
		int n = s.nextInt();
		boolean p1Turn = true;
		while(n > 0) {
			int x = s.nextInt();
			int y = s.nextInt();
			boolean ans = false;
			if(p1Turn) {
				ans = b.move(player1Symbol, x, y);
			}
			else {
				ans = b.move(player2Symbol, x, y);
			}
			if(ans) {
				b.print();
				p1Turn = !p1Turn;
				n--;
			}
			else {
				System.out.println(ans);
			}
		}
	}
}
