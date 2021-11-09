package com.codingdojo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.models.Dojo;
import com.codingdojo.repositories.DojoRepository;

@Service
public class DojoService {
	
		private final DojoRepository dojoRepository;

		public DojoService(DojoRepository dojoRepository) {
			this.dojoRepository = dojoRepository;
		}

		public List<Dojo> getAllDojos() {
			return (List<Dojo>) dojoRepository.findAll();
		}

		public Dojo createDojo(Dojo dojo) {
			return dojoRepository.save(dojo);
		}

		public Dojo findDojoById(Long id) {
			Optional<Dojo> findDojo = dojoRepository.findById(id);
			if (findDojo.isPresent()) {
				return findDojo.get();
			} else {
				return null;
			}
		}

}
