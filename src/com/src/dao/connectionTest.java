/**
 * 
 */
package com.src.dao;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

/**
 * @author Akash Hande
 *
 */
public class connectionTest {

	@Test
	public void test() {
		Connection connect;
		ConnectionDatabase cDB = new ConnectionDatabase();
		connect = cDB.connectToDB();
		assertEquals(connect != null, true);
	}

}
