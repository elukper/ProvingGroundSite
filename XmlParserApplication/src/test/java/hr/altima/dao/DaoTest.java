package hr.altima.dao;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import hr.altima.configuration.AppConfig;
import hr.altima.model.DbEntry;
import hr.altima.model.service.EntityService;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class DaoTest {

	@Autowired
	EntityService<DbEntry> entityService;

	@Test
	public void testAutiwiring() {
		Assert.assertNotNull(entityService);
	}

	@Test
	public void testBasicSaveAndRead(){

		final DbEntry input = new DbEntry();
		input.setName("Some guy");

		entityService.saveEntity(input);

		final List<DbEntry> output = entityService.findAllEntities(DbEntry.class);

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

		//child is set first in list, to test persistance
		entityService.saveAllEntities(Arrays.asList(child,parent), DbEntry.class);

		final List<DbEntry> output = entityService.findAllEntities(DbEntry.class);

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

		//Create initial Database state
		final DbEntry currentEntry1 = new DbEntry();
		currentEntry1.setName("Sam Winchester");

		final DbEntry currentEntry2 = new DbEntry();
		currentEntry2.setName("Dean Winchester");

		entityService.saveAllEntities(Arrays.asList(currentEntry1,currentEntry2), DbEntry.class);

		//Create new database state
		final DbEntry newEntry1 = new DbEntry();
		newEntry1.setName("John Winchester");

		currentEntry1.setParent(newEntry1);

		currentEntry2.setParent(newEntry1);

		entityService.saveAllEntities(Arrays.asList(currentEntry2,newEntry1,currentEntry1), DbEntry.class);

		//Check new database state

		final List<DbEntry> output = entityService.findAllEntities(DbEntry.class);

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
