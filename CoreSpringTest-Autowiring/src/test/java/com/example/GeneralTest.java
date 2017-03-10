package com.example;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.soundsystem.CompactDisc;
import com.example.soundsystemconfig.JukeboxConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=JukeboxConfig.class)
public class GeneralTest {
	

	@Autowired
	private CompactDisc cd;
	
	@Test
	public void generalTest(){
		assertNotNull(cd);
		
	}

}
