package CarRace3D;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateRaceSQL {
	
	

	public CreateRaceSQL() {
		Connection con = null;
		String url = "jdbc:mysql://localhost/";
		String db = "";
		String driver = "com.mysql.jdbc.Driver";

		try {  
			Class.forName(driver);
			con = DriverManager.getConnection(url+db,"scott","tiger");
			Statement st = con.createStatement();

			File dbFile = new File("CarRace3DCreateSQL.txt");
			FileInputStream fstream = new FileInputStream(dbFile);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			//Read File Line By Line
			if (!br.readLine().equals("Installed")) { // check if DB was installed already
				System.out.println("Creating Car Race 3D DB");
				String strLine = "", strLine1 = "", installed = "Installed\n";
				while ((strLine = br.readLine()) != null) { 
					installed = installed + strLine + "\n";
					if (strLine != null && !strLine.trim().equals("")) { 
						if ((strLine.trim().indexOf("/*") < 0 ) && (strLine.trim().indexOf("*/") < 0 )) { 
							if (strLine.indexOf(';') >= 0) { strLine1 += strLine;	
							st.execute(strLine1);
							strLine1 = ""; 
							}
							else
								strLine1 += strLine;
						}
					}
				}	
				System.out.println("CarRaceDB created!");
				dbFile.delete();
				FileOutputStream fos = new FileOutputStream(dbFile); // create same txt file with new first line as flag
				fos.write(installed.getBytes());
				fos.flush(); 
			}
			else
				System.out.println("Car Race 3D DB found");
		} catch(Exception e) { 
			System.out.println(e);
		}  
		finally {}
	}

}
