package kr.or.ddit.jackson;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.OperatorType;
import kr.or.ddit.vo.CalculatorVO;

/**
 *	jackson-databind를 이용한 마샬링과 직렬화
 *	 Marshalling : native data를 json/xml과 같은 공통 메세지 포맷으로 변환하는 작업 (일어 -> 영어)
 *	 UnMarshalling : json/xml과 같은 공통 메세지 포맷을 native data로 변환하는 작업 (영어 -> 독어)
 *
 *	 Serialization : 전송이나 저장을 위해 매체가 필요할 때, 전송 혹은 저장할 수 있는 byte stream(byte array) 형태로 데이터를 변환하는 작업
 *	 DeSerialization : 매체에 저장된 혹은 매체로부터 전송된 byte stream을 원래의 데이터 형태로 변환하는 작업
 */
class ObjectMapperTest {

	@Test
	void testMarshalling() throws JsonProcessingException {
		Map<String, Object> nativeData = new HashMap<>();
		nativeData.put("prop1",323);
		nativeData.put("prop2",false);
		nativeData.put("prop3","SAMPLE");
		nativeData.put("prop4",Collections.singletonMap("innerProp", 234));
		nativeData.put("prop5",null);
		nativeData.put("prop6",new int[] {1,2,3});
//		System.out.println(nativeData);
		
//		native -> json
		ObjectMapper objectMapper = new ObjectMapper();
//		marshalling : write* 메서드, unmarshalling : read* 메서드
		String json = objectMapper.writeValueAsString(nativeData);
		System.out.println(json);
	}
	
	@Test
	void testUnMarshalling() throws JsonMappingException, JsonProcessingException {
		String json = "{\"prop2\":false,\"prop1\":323,\"prop6\":[1,2,3],\"prop5\":null,\"prop4\":{\"innerProp\":234},\"prop3\":\"SAMPLE\"}";
//		json -> native
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> nativeData = objectMapper.readValue(json, HashMap.class); // HashMap의 class 정보를 가지고 있음
		System.out.println(nativeData);
	}
	
	/**
	 *  I/O 작업 단계
	 *  1. 매체(media)를 객체화(File, Socket, byte array)
	 *  2. 1번의 매체에 단방향 데이터의 통로(stream)을 연결하여 개방함 - 1차 stream
	 *  	ex) fis = new FileInputStream(file), SocketOutputStream, ByteArrayInputStream
	 *  3. 1차 스트림을 대상으로 2차 스트림(보조스트림)을 연결하여 개방함. - optional(buffered, Filtered..)
	 *  	ex) new BufferedInputStream(fis), DataInputStream, - 1차 스트림을 넣음
	 *  	ObjectInputStream(역직렬화) / ObjectOutputStream(직렬화)
	 *  4. EOF를 만날때까지 반복적인 read/write
	 *  5. close (try with resource 구문)
	 */
	@Test
	void testSerialization() {
		Map<String, Serializable> nativeData = new HashMap<>();
		nativeData.put("prop1","SAMPLE");
		nativeData.put("prop2",1234);
		
		File file = new File("d:/sample.dat");
		try(
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
		) {
			oos.writeObject(nativeData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	void testSerialization2() {
		CalculatorVO nativeData = new CalculatorVO(0,0,OperatorType.PLUS);
		
		File file = new File("d:/sample.dat");
		try(
				FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				) {
			oos.writeObject(nativeData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testDeSerialization() throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File("d:/sample.dat");
		try(
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
		){
			Map<String, Serializable> nativeData = (Map<String, Serializable>) ois.readObject();
			System.out.println(nativeData);
		}
	}
	
	@Test
	void testMarshallingAndSerialization() throws StreamWriteException, DatabindException, IOException {
		Map<String, Serializable> nativeData = new HashMap<>();
		nativeData.put("prop1","SAMPLE");
		nativeData.put("prop2",1234);
		
		File file = new File("d:/sample.json");
		ObjectMapper objectMapper = new ObjectMapper();
		try(
			FileWriter writer = new FileWriter(file);
		){
			objectMapper.writeValue(writer,nativeData);
		}
	}
	
	@Test
	void testDeSerializationAndUnMarsalling() throws StreamReadException, DatabindException, IOException {
		File file = new File("d:/sample.json");
		ObjectMapper objectMapper = new ObjectMapper();
		try(
			FileReader reader = new FileReader(file);
		){
			Map<String, Serializable> nativeData = objectMapper.readValue(reader,HashMap.class);
			System.out.println(nativeData);
		}
	}
}
