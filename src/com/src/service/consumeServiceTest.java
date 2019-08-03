package com.src.service;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import com.src.bean.InterfaceBean;

public class consumeServiceTest {

	/**
	 * Success for - newly generated access key and London, D01 V6V6, D01 Y300, 08/20/2018 19:20, Walking
	 * Fail for - past access key / blank key
	 */
	@Test
	public void testTravelReminder() {
		consumeService cs = new consumeService();
		boolean result = false;
		try {
			result = cs.setTravelReminder("", "London", "D01 V6V6", "D01 Y300", "08/20/2018 19:20", "Walking");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(result, true);
	}
}
