package org.shen.daily.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @author ZhiqiangShen
 * �������ɲ�������
 *
 */
public class TestUtil {
	public static void generateExample() throws IOException {
		
		File file=new File("E:\\test.sql");
		FileOutputStream fos=new FileOutputStream(file);
		OutputStreamWriter osw=new OutputStreamWriter(fos, "UTF-8");
		BufferedWriter bw=new BufferedWriter(osw);
		
		for (int i = 1; i < 100; i++) {
			long id=(long)(i);
			String name="����"+i;
			byte sex=(byte) (i);
			long tel=(long) (i);
			String email="����"+i;
			
			String sql="INSERT student_ VALUES(id,'name',sex,tel,'email');";
			
			sql=sql.replace("id", String.valueOf(id));
			sql=sql.replace("name", name);
			sql=sql.replace("sex", String.valueOf(sex));
			sql=sql.replace("tel", String.valueOf(tel));
			sql=sql.replace("email", email);
			
			bw.write(sql);
			bw.flush();
			bw.newLine();
			
		}
		bw.close();
		
		
	}
	public static void main(String[] args) {
		try {
			generateExample();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}