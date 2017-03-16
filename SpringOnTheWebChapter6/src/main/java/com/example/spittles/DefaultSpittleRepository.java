package com.example.spittles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.helpers.Loader;
import org.springframework.stereotype.Component;

@Component
public class DefaultSpittleRepository implements SpittleRepository{
	
	public static final int START_SPITTLE_NUM = 50;
	
	List<Spittle> spittleRepository = new ArrayList<Spittle>();
	Map<Long,Spittle> spittleMapById = new HashMap<Long,Spittle>();
	
	public DefaultSpittleRepository() {
		
		for(int i = 1; i<START_SPITTLE_NUM+1;i++) {
			Spittle spittle = new Spittle(i+1,"Spittle"+i, new Date());
			spittleRepository.add(spittle);
			spittleMapById.put(new Long(i), spittle);
		}
		
	}
	
	

	@Override
	public List<Spittle> getSpittles() {
		return Collections.unmodifiableList(spittleRepository);
	}

	@Override
	public List<Spittle> getSpittles(int count) {
		return spittleRepository.subList(0, count);
	}



	@Override
	public Spittle getSpittleById(long id) {
		return spittleMapById.get(new Long(id));
	}

}
