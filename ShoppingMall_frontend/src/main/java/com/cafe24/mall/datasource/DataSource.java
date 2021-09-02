package com.cafe24.mall.datasource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class DataSource {
	
	public static dataSourceDTO data;

	/**
	 * 클래스 외부에서 생성자를 통해 인스턴스 생성하지 못하도록 작업
	 */
	private DataSource() {}
	
	/**
	 * dataSource.yml 로드
	 * @throws FileNotFoundException 
	 */
	public static void onLoad() {
		if (data == null) {
			InputStream inputStream;
			try {
				String path = DataSource.class.getResource("").getPath();

				inputStream = new FileInputStream(new File(path + "/dataSource.yml"));
				Yaml yaml = new Yaml(new Constructor(dataSourceDTO.class));
				data = yaml.load(inputStream);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} 
	}

}
