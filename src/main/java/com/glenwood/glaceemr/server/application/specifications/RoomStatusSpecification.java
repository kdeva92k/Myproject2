package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.AlertMonitor;
import com.glenwood.glaceemr.server.application.models.AlertMonitor_;
import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.Chart_;
import com.glenwood.glaceemr.server.application.models.CurrentMedication;
import com.glenwood.glaceemr.server.application.models.CurrentMedication_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Encounter_;
import com.glenwood.glaceemr.server.application.models.LabEntries;
import com.glenwood.glaceemr.server.application.models.LabEntries_;
import com.glenwood.glaceemr.server.application.models.LeafLibrary;
import com.glenwood.glaceemr.server.application.models.LeafLibrary_;
import com.glenwood.glaceemr.server.application.models.LeafPatient;
import com.glenwood.glaceemr.server.application.models.LeafPatient_;
import com.glenwood.glaceemr.server.application.models.PosTable;
import com.glenwood.glaceemr.server.application.models.PosTable_;
import com.glenwood.glaceemr.server.application.models.Prescription;
import com.glenwood.glaceemr.server.application.models.Prescription_;
import com.glenwood.glaceemr.server.application.models.Room;
import com.glenwood.glaceemr.server.application.models.Room_;

public class RoomStatusSpecification {
    
	/**
	 * to get pos
	 * @return List<posTable>
	 */
	public static Specification<PosTable> getPos() {
		return new Specification<PosTable>() {
			@Override
			public Predicate toPredicate(Root<PosTable> root,CriteriaQuery<?> cq, CriteriaBuilder cb) {
				Predicate posCode=cb.equal(root.get(PosTable_.posTablePosCode),11);
                Predicate posActive=cb.equal(root.get(PosTable_.posTableIsActive),true);
				cq.orderBy(cb.asc(root.get(PosTable_.posTableRelationId)));
                Predicate finalPredicate=cb.and(posCode,posActive);
				return finalPredicate;
			}
		};
	}

	/**
	 * to get providers
	 * @return List<EmployeeProfile>
	 */
	public static Specification<EmployeeProfile> getproviders() {
		return new Specification<EmployeeProfile>() {
			@Override
			public Predicate toPredicate(Root<EmployeeProfile> root,CriteriaQuery<?> cq, CriteriaBuilder cb) {
				Join<EmployeeProfile,AlertMonitor> empJoin=root.join(EmployeeProfile_.alertMonitor,JoinType.INNER);
				empJoin.on(cb.equal(empJoin.get(AlertMonitor_.alertMonitorOptionType),"EMPLOYEE"));
				Predicate posActive=cb.equal(root.get(EmployeeProfile_.empProfileIsActive),true);
				cq.orderBy(cb.asc(root.get(EmployeeProfile_.empProfileEmpid)));
				return posActive;
			}
		};
	}

	/**
	 * to get roomName
	 * @return List<Room>
	 */
	public static Specification<Room> getRoomIsActive() {
		return new Specification<Room>() {
			@Override
			public Predicate toPredicate(Root<Room> root,CriteriaQuery<?> cq, CriteriaBuilder cb) {
				Predicate roomIsActive=cb.equal(root.get(Room_.roomIsActive),true);
		        return roomIsActive;
			}
		};
		
	}

	/**
	 * to update roomNo
	 * @param pos
	 * @param chartId
	 * @param roomtoAdd
	 * @return List<Encounter>
	 */
	public static Specification<Encounter> getUpdateEncounter(final Integer pos,final Integer chartId ,final Short roomtoAdd) {
		return new Specification<Encounter>(){
			@Override
			public Predicate toPredicate(Root<Encounter> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate updateEnc=cb.and(cb.equal(root.get(Encounter_.encounterPos),pos),
										   cb.equal(root.get(Encounter_.encounterStatus),1),
										   cb.equal(root.get(Encounter_.encounterType),1),
										   root.get(Encounter_.encounterChartid).in(chartId));
				return updateEnc;
			}
		};
	}
    
	/**
	 * to get chartId
	 * @param addPatientId
	 * @return chartId
	 */
	public static Specification<Chart> getChartId(final String patientId) {
		return new Specification<Chart>(){
			@Override
			public Predicate toPredicate(Root<Chart> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate chartId=cb.equal(root.get(Chart_.chartPatientid),patientId);
				return chartId;
			}
		};
	}

	/**
	 * to swap patients
	 * @param toSwap
	 * @param pos
	 * @return selChartid
	 */
	public static Specification<Encounter> getchartIds(final Short toswap,final Integer pos) {
		return new Specification<Encounter>(){
			@Override
			public Predicate toPredicate(Root<Encounter> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate selchartId=cb.and(cb.equal(root.get(Encounter_.encounterRoom),toswap),
											cb.equal(root.get(Encounter_.encounterPos),pos),
											cb.notEqual(root.get(Encounter_.encounterRoom),-1),
											cb.equal(root.get(Encounter_.encounterType),1),
											cb.equal(root.get(Encounter_.encounterStatus),1));
				return selchartId;

			}

		};
	}
    
	/**
	 * to transfer patients
	 * @param pos 
	 * @param idList 
	 * @param toswap
	 * @param pos
	 * @param withroom
	 * @return List<Encounter>
	 */
	public static Specification<Encounter> UpdateTransferRoom(final java.util.List<Integer> idList, final Integer pos) {
		return new Specification<Encounter>(){
			@Override
			public Predicate toPredicate(Root<Encounter> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate updateEnc=cb.and(cb.equal(root.get(Encounter_.encounterPos),pos),
						   cb.equal(root.get(Encounter_.encounterStatus),1),
						   cb.equal(root.get(Encounter_.encounterType),1),
						   cb.equal(root.get(Encounter_.encounterRoomIsactive),true));
							root.get(Encounter_.encounterChartid).in(idList);
				return updateEnc;			
			}
		};
	}

	/**
	 * to swap patients
	 * @param withroom
	 * @param pos
	 * @return selChartId
	 */
	public static Specification<Encounter> chartidswithroom(final Short withroom,final Integer pos) {
			return new Specification<Encounter>(){
				public Predicate toPredicate(Root<Encounter> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate selchartId=cb.and(cb.equal(root.get(Encounter_.encounterRoom),withroom),
											cb.equal(root.get(Encounter_.encounterPos),pos),
											cb.notEqual(root.get(Encounter_.encounterRoom),-1),
											cb.equal(root.get(Encounter_.encounterType),1),
											cb.equal(root.get(Encounter_.encounterStatus),1));			
				return selchartId;
			}
		};
	}
    
	/**
	 * to get userName
	 * @param userId
	 * @return
	 */
	public static Specification<EmployeeProfile> getUserName(final Integer userId) {
		return new Specification<EmployeeProfile>() {
			public Predicate toPredicate(Root<EmployeeProfile> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate empId=cb.equal(root.get(EmployeeProfile_.empProfileEmpid), userId);
				return empId;
			}	
		};
	}
	
	/**
	 * to get rxName
	 * @param entityid
	 * @return
	 */
	public static Specification<Prescription> getRxName(final Integer entityid) {
		return new Specification<Prescription>() {
			public Predicate toPredicate(Root<Prescription> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate docPrescId=cb.equal(root.get(Prescription_.docPrescId), entityid);
				return docPrescId;
			}
		};
	}
	
	/**
	 * to get curMedRxName
	 * @param entityid
	 * @return
	 */
	public static Specification<CurrentMedication> getCurMedRxName(final Integer entityid) {
		return new Specification<CurrentMedication>() {
			public Predicate toPredicate(Root<CurrentMedication> root,CriteriaQuery<?> query,CriteriaBuilder cb) {
				Predicate curMedId=cb.equal(root.get(CurrentMedication_.currentMedicationId),entityid);
				return curMedId;
			}
		};
	}
	
	/**
	 * to get labEntriesTestDesc
	 * @param entityid
	 * @return
	 */
	public static Specification<LabEntries> getLabEntriesTestDesc(final Integer entityid) {
		return new Specification<LabEntries>() {
			public Predicate toPredicate(Root<LabEntries> root,CriteriaQuery<?> query,CriteriaBuilder cb) {
				Predicate labEntriesId=cb.equal(root.get(LabEntries_.labEntriesTestdetailId),entityid);
				return labEntriesId;
			}
		};
	}

	/**
	 * to get leafPatientlibraryid
	 * @param entityid
	 * @return
	 */
	public static Specification<LeafPatient> getLeafPatLibId(final Integer entityid) {
		return new Specification<LeafPatient>() {
			public Predicate toPredicate(Root<LeafPatient> root,CriteriaQuery<?> query,CriteriaBuilder cb) {
				Predicate leafPatId=cb.equal(root.get(LeafPatient_.leafPatientId),entityid);
				return leafPatId;
			}
		};
	}

	/**
	 * to get leafPatientLibraryName
	 * @param leafPatId
	 * @return
	 */
	public static Specification<LeafLibrary> getLeafLibName(final Integer leafPatId) {
		return new Specification<LeafLibrary>() {
			public Predicate toPredicate(Root<LeafLibrary> root,CriteriaQuery<?> query,CriteriaBuilder cb) {
				Predicate leafLibraryId=root.get(LeafLibrary_.leafLibraryId).in(leafPatId); 
				return leafLibraryId;
			}
		};
	}
    
	/**
	 * to get encounterChartId
	 * @param chartId
	 * @return
	 */
	public static Specification<Encounter> getEncId(final Integer chartId) {
		return new Specification<Encounter>() {
			public Predicate toPredicate(Root<Encounter> root,CriteriaQuery<?> query,CriteriaBuilder cb) {
				Predicate restrictions=cb.and(root.get(Encounter_.encounterChartid).in(chartId),
											  cb.equal(root.get(Encounter_.encounterType),1),
											  cb.equal(root.get(Encounter_.encounterStatus),1));
				return restrictions;
			}
		};
	}

	/**
	 * to get encounter chartid for switching patients between rooms
	 * @param toswap
	 * @param pos
	 * @return
	 */
	public static Specification<Encounter> getEncChartId(final Short toswap,final Integer pos) {
		return new Specification<Encounter>() {
			public Predicate toPredicate(Root<Encounter> root,CriteriaQuery<?> query,CriteriaBuilder cb) {
				Predicate updateEnc=cb.and(cb.notEqual(root.get(Encounter_.encounterRoom),-1),
						   cb.equal(root.get(Encounter_.encounterRoom),toswap),
						   cb.equal(root.get(Encounter_.encounterPos),pos),
						   cb.equal(root.get(Encounter_.encounterStatus),1),
						   cb.equal(root.get(Encounter_.encounterType),1),
						   cb.equal(root.get(Encounter_.encounterRoomIsactive),true));
				return updateEnc;			
			}
		};
	}
}