package com.glenwood.glaceemr.server.application.services.chart.print.genericfooter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.models.print.GenericLetterFooter;
import com.glenwood.glaceemr.server.application.repositories.print.GenericFooterRepository;


@Service
public class GenericFooterServiceImpl implements GenericFooterService {
	
	@Autowired
	GenericFooterRepository genericFooterRepository;

	@Override
	public List<GenericLetterFooter> getGenericFooterList() {
		List<GenericLetterFooter> genericFooterList=genericFooterRepository.findAll();
		return genericFooterList;
	}

	@Override
	public GenericLetterFooter saveGenericFooter(GenericLetterFooter genericLetterFooter) {
		GenericLetterFooter savedGenericLetterFooter=genericFooterRepository.save(genericLetterFooter);
		return savedGenericLetterFooter;
	}

	@Override
	public GenericLetterFooter getGenericFooter(Integer genericLetterFooterId) {
		GenericLetterFooter genericLetterFooter=genericFooterRepository.findOne(genericLetterFooterId);
		return genericLetterFooter;
	}
	
	

}
