package com.emrmiddleware.dao;

import java.util.ArrayList;

import com.emrmiddleware.dmo.EncounterDMO;
import com.emrmiddleware.dmo.UserCredentialsDMO;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.emrmiddleware.conf.DBconfig;
import com.emrmiddleware.dmo.PatientDMO;
import com.emrmiddleware.dto.PatientAttributeDTO;
import com.emrmiddleware.dto.PatientAttributeTypeDTO;
import com.emrmiddleware.dto.PatientDTO;
import com.emrmiddleware.exception.DAOException;

public class PatientDAO {

	private final Logger logger = LoggerFactory.getLogger(PatientDAO.class);
	public ArrayList<PatientDTO> getPatients(String lastpulldatatime, String locationuuid) throws DAOException {

		SqlSession session = getSession();
		ArrayList<PatientDTO> patientlist = new ArrayList<PatientDTO>();
		try {

			PatientDMO patientdmo = session.getMapper(PatientDMO.class);
			patientlist = patientdmo.getPatients(lastpulldatatime, locationuuid);
			return patientlist;
		} catch (PersistenceException e) {
			logger.error(e.getMessage(),e);
			throw new DAOException(e.getMessage(), e);
		} finally {
			session.close();
		}
	}

	public ArrayList<PatientAttributeTypeDTO> getPatientAttributeType(String lastpulldatatime, String locationuuid)
			throws DAOException {

		SqlSession session = getSession();
		ArrayList<PatientAttributeTypeDTO> patientAttributeTypeList = new ArrayList<PatientAttributeTypeDTO>();
		try {

			PatientDMO patientdmo = session.getMapper(PatientDMO.class);
			patientAttributeTypeList = patientdmo.getPatientAttributeMaster(lastpulldatatime);
			return patientAttributeTypeList;
		} catch (PersistenceException e) {
			logger.error(e.getMessage(),e);
			throw new DAOException(e.getMessage(), e);
		} finally {
			session.close();
		}
	}

	public ArrayList<PatientAttributeDTO> getPatientAttributes(String lastpulldatatime, String locationuuid)
			throws DAOException {
		SqlSession session = getSession();
		ArrayList<PatientAttributeDTO> patientAttributesList = new ArrayList<PatientAttributeDTO>();
		try {

			PatientDMO patientdmo = session.getMapper(PatientDMO.class);
			patientAttributesList = patientdmo.getPatientAttributes(lastpulldatatime, locationuuid);
			return patientAttributesList;
		} catch (PersistenceException e) {
			logger.error(e.getMessage(),e);
			throw new DAOException(e.getMessage(), e);
		} finally {
			session.close();
		}
	}

	public PatientDTO getPatient(String personuuid) throws DAOException {
		SqlSession session = getSession();
		PatientDTO patientdto = new PatientDTO();
		try {

			PatientDMO patientdmo = session.getMapper(PatientDMO.class);
			patientdto = patientdmo.getPatient(personuuid);
			return patientdto;
		} catch (PersistenceException e) {
			logger.error(e.getMessage(),e);
			throw new DAOException(e.getMessage(), e);
		} finally {
			session.close();
		}
	}

	private SqlSession getSession(){
		SqlSessionFactory sessionfactory = DBconfig.getSessionFactory();
//		sessionfactory.getConfiguration().addMapper(PatientDMO.class);
		return sessionfactory.openSession();
	}

}
