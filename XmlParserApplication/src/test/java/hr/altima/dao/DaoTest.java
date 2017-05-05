package hr.altima.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import hr.altima.dao.service.DbEntryService;

@ActiveProfiles("tst")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Transactional
public class DaoTest {

	@Autowired
	DbEntryService dbEntryService;


	@Test
	public void testAutiwiring() {
		Assert.assertNotNull(dbEntryService);
	}

	@Test
	public void testBasicSaveAndRead(){

		final DbEntry input = new DbEntry();
		input.setName("Some guy");

		dbEntryService.deleteAll();

		//Assert.assertNotNull(input.getIdentity());

		dbEntryService.save(input);

		final List<DbEntry> output = Lists.newArrayList(dbEntryService.findAll());

		Assert.assertEquals(1, output.size());

		Assert.assertEquals("Some guy", output.get(0).getName());

	}

	@Test
	public void testForeignKeySaveAndRead(){
		final DbEntry child = new DbEntry();
		child.setName("Peter J. Quill aka. Starlord");

		final DbEntry parent = new DbEntry();
		parent.setName("No spoilers :)");

		child.setParent(parent);

		dbEntryService.deleteAll();

		//child is set first in list, to test persistance
		final List<DbEntry> input = new ArrayList<>();
		input.add(child);
		input.add(parent);
		dbEntryService.save(input);

		final List<DbEntry> output = Lists.newArrayList(dbEntryService.findAll());

		Assert.assertEquals(2, output.size());

		for(final DbEntry dbEntry : output) {
			if(dbEntry.getParent()==null) {
				Assert.assertEquals("No spoilers :)", dbEntry.getName());
			} else {
				Assert.assertEquals("No spoilers :)", dbEntry.getParent().getName());
				Assert.assertEquals("Peter J. Quill aka. Starlord", dbEntry.getName());
			}
		}
	}

	@Test
	public void testSaveAndOverwrite(){

		dbEntryService.deleteAll();

		//Create initial Database state
		final DbEntry currentEntry1 = new DbEntry();
		currentEntry1.setName("Sam Winchester");

		final DbEntry currentEntry2 = new DbEntry();
		currentEntry2.setName("Dean Winchester");

		currentEntry1.setParent(currentEntry2);

		dbEntryService.save(Arrays.asList(currentEntry1,currentEntry2));

		//Create new database state
		final DbEntry newEntry1 = new DbEntry();
		newEntry1.setName("John Winchester");

		currentEntry1.setParent(newEntry1);

		currentEntry2.setParent(newEntry1);


		dbEntryService.save(Arrays.asList(currentEntry2,currentEntry1,newEntry1));

		//Check new database state

		final List<DbEntry> output = Lists.newArrayList(dbEntryService.findAll());

		Assert.assertEquals(3, output.size());

		for(final DbEntry dbEntry : output) {
			if(dbEntry.getParent()==null) {
				Assert.assertEquals("John Winchester", dbEntry.getName());
			} else {
				Assert.assertEquals("John Winchester", dbEntry.getParent().getName());
			}
		}

	}


}
