/**
 * 
 */
package logger;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Victor Tran
 *
 */
public class SLAMCLogger {

	private FileWriter fw;
	
	public SLAMCLogger(String fileName){
		init(fileName);
	}
	
	public void log(Object obj){
		try {
			fw.append(obj + "\r\n");
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void init(String debugName){
		try {
			fw = new FileWriter(debugName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close(){
		try {
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
