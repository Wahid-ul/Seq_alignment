

public class SW {
	String s1="GAATTCAGTTA";
	String s2="GGATCGA";
	int k[][]=new int [s2.length()+1][s1.length()+1];
	void matrix() {
		//filling gap score in the first column
		for(int i=0;i<s2.length()+1;i++) {
			k[i][0]=0*i;
			
		}
		//filling gap score in the first row
		for(int j=0;j<s1.length()+1;j++) {
			k[0][j]=0*j;
			
			
		}
		//Fill rest of the matrix with each cell has a value max result from horizontal,vertical,diagonal
		for( int i=1;i<s2.length()+1;i++) {
			for(int j=1;j<s1.length()+1;j++) {
				int left=k[i][j-1]+0;
				int up=k[i-1][j]+0;
				int diag=k[i-1][j-1]+m(s1,s2,i,j);
				k[i][j]=Math.max(diag, Math.max(up, left));
				
			}
		}
		for(int i=0;i<s2.length()+1;i++) {
			for(int j=0;j<s1.length()+1;j++) {
				System.out.print(k[i][j]+"\t");
			}
			System.out.println();
		}
	}
	void print() {
		System.out.println("Smith-Waterman matrix:");
	}
	public int m(String s1,String s2,int k,int l) {
		if(s2.charAt(k-1)==s1.charAt(l-1))
			return 1;
		else
			return 0;
	}
	//tracebacking-Finds the path and gets the output modified sequences
	void trace() {
		String news1="";
		String news2="";
		int i=s2.length();
		int j=s1.length();
		int score=k[i][j];
		while(i!=0 && j!=0) {
			if(k[i][j]==k[i-1][j-1]+m(s1,s2,i,j)) {
				news2 +=s2.charAt(i-1);
				news1 +=s1.charAt(j-1);
				i--;
				j--;
				continue;
			}else if(k[i][j]==k[i][j-1]+0) {
				news2 +="-";
				news1 +=s1.charAt(j-1);
				j--;
				continue;	
			}else {
				news2 +=s2.charAt(i-1);
				news1 +="-";
				i--;
				continue;
			}
		}
		if (i == 0)
        {
            for (int k = 0; k < j; k++) {
                news2 = "-" + news2;
                news1 = s1.charAt(j-k) + news1;
            }
        }
        else {
            for (int k = 0; k < i; k++) {
                news2 = s2.charAt(i-k) + news2;
                news1 = "-" + news1;
            }
        }
		System.out.println("Alignment result:");
		//we have to reverse the new sequences as in every iteration during 
		//tracebacking we get from last to first.For that we use .toCharArray()-here we have to convert
		//string into array and then with the help of for loop we move from end to starting
		char[] n1=news1.toCharArray();
		for(int l=n1.length-1;l>=0;l--) {
			System.out.print(n1[l]);
		}
		System.out.println();
		char [] n2=news2.toCharArray();
		for(int m=n1.length-1;m>=0;m--) {
			System.out.print(n2[m]);
		}
		System.out.println();
		
		//System.out.println(news1);
		//System.out.println(news2);
		System.out.println("Score of the matrix:");
		System.out.println(score);
	}
				
		
	public static void main(String[] args) {
		
		SW obj=new SW();
		obj.print();
		obj.matrix();
		obj.trace();
		
	}

}
