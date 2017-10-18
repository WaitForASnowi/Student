package org.shen.daily.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @author ZhiqiangShen
 * 用于生成测试数据
 *
 */
public class TestUtil {
	public static void generateExample() throws IOException {
		
		File file=new File("E:\\test.sql");
		FileOutputStream fos=new FileOutputStream(file);
		OutputStreamWriter osw=new OutputStreamWriter(fos, "UTF-8");
		BufferedWriter bw=new BufferedWriter(osw);
		
		for (int i = 1; i < 100; i++) {
			long id=(long)(i+2016220101001L);
			String name="姓名"+i;
			byte sex=(byte) (i%2==0?1:0);
			long tel=(long) (15181517501L+i);
			String email="youxiang"+i+"@email.com";
			
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
