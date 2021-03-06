package com.emrmiddleware.dao;

import com.emrmiddleware.dmo.EncounterDMO;
import com.emrmiddleware.dmo.UserCredentialsDMO;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.emrmiddleware.conf.DBconfig;
import com.emrmiddleware.dmo.PersonDMO;
import com.emrmiddleware.dto.PersonDTO;
import com.emrmiddleware.exception.DAOException;

public class PersonDAO {

	private final Logger logger = LoggerFactory.getLogger(PersonDAO.class);
	public PersonDTO getPerson(String personuuid) throws DAOException {

		SqlSession session = getSession();
		PersonDTO persondto = new PersonDTO();
		try {

			PersonDMO persondmo = session.getMapper(PersonDMO.class);
			persondto = persondmo.getPerson(personuuid);
			return persondto;
		} catch (PersistenceException e) {
			logger.error(e.getMessage(),e);
			throw new DAOException(e.getMessage(), e);
		} finally {
			session.close();
		}
	}

	private SqlSession getSession(){
		SqlSessionFactory sessionfactory = DBconfig.getSessionFactory();
//		sessionfactory.getConfiguration().addMapper(PersonDMO.class);
		return sessionfactory.openSession();
	}
	
}
