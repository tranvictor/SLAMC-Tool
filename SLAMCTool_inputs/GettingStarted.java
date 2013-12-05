import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class GettingStarted {
	public int intMember;
	public boolean isStarted = true && false || true, isFinished;
	private List<String> someString;

	public void outputMessage(String s, PrintStream out){
		out.println(s);
	}

        public String testMethod(int a, int b, String c){
          return "something";
        }
	
	public void mainFunction(String file) throws IOException{
		int a = 0, b, c;
		StringBuffer d, e, f;
		StringBuffer strbuf = new StringBuffer();
		BufferedReader in = new BufferedReader(new FileReader(file));
		String str;
                int intTest = 7;
                float castTest;
                int[] sampleArray = {1, 2, 3, 4};
                sampleArray[1] = 2;
                sampleArray[2] = sampleArray[1] + sampleArray[0];
                a = 1;
                b = 0;
                c = a & b;
                c = a | b;
                c = a ^ b;
                c = ~a;
                a &= 1;
                b |= c;
                b ^= c;
                b /= 1;
		
		while ((str = in.readLine()) != null){
			strbuf.append(str + '\n');
		}
                str = "some";
                str = "some" + " strings";
                isStarted = true;
                A.temp = 3;
		in.close();
		str.isEmpty();
                do {
                  b %= 4;
                  (b = 1);
                }
                while (a == 1);
                castTest = (float) intTest;
                castTest = (1 <= 2)?0:1;
                while (a == 1){
                  b = 2;
                };
                this.isStarted = false;
                this.testMethod(1, 2, "abc");
                testMethod(2, 4, "lkjadf");
                for (int i : sampleArray){
                  System.out.println(i);
                }
                for (int i = 0; i<4; i++)
                  System.out.println(i);
                for (int i = 3; ;){
                  System.out.println(i);
                }
                if (strbuf.length() > 0){
			outputMessage(strbuf.toString(), System.out);
			if (a != 0){
				System.out.println("");
				do {
					strbuf.append(" ");
					if (strbuf.length() > 100) {
						System.out.print("break");
						break;
					}
				} while (true);
			} else {
				System.out.println(true);
			}
		}
	}

        public static void main(String[] args){
            String temp = "temp";
            System.out.println(temp.hashCode());
        }
}
