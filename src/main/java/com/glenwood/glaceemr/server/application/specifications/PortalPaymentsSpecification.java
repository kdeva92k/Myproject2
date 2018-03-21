package com.glenwood.glaceemr.server.application.specifications;

import java.math.BigDecimal;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.EnsBillsDetails;
import com.glenwood.glaceemr.server.application.models.EnsBillsDetails_;
import com.glenwood.glaceemr.server.application.models.GeneratedBillsHistoryDetails;
import com.glenwood.glaceemr.server.application.models.GeneratedBillsHistoryDetails_;
import com.glenwood.glaceemr.server.application.models.IntermediateStmt;
import com.glenwood.glaceemr.server.application.models.IntermediateStmt_;
import com.glenwood.glaceemr.server.application.models.NonServiceDetails;
import com.glenwood.glaceemr.server.application.models.NonServiceDetails_;
import com.glenwood.glaceemr.server.application.models.PatientInsDetail;
import com.glenwood.glaceemr.server.application.models.PatientInsDetail_;
import com.glenwood.glaceemr.server.application.models.PaymentService;
import com.glenwood.glaceemr.server.application.models.PaymentService_;
import com.glenwood.glaceemr.server.application.models.PosTable;
import com.glenwood.glaceemr.server.application.models.PosTable_;
import com.glenwood.glaceemr.server.application.models.ReceiptDetail;
import com.glenwood.glaceemr.server.application.models.ReceiptDetail_;
import com.glenwood.glaceemr.server.application.models.UserinformationCreditcard;
import com.glenwood.glaceemr.server.application.models.UserinformationCreditcard_;

public class PortalPaymentsSpecification {

	
	
	
	/**
	 * @return list of available Language options  
	 */	
	public static Specification<ReceiptDetail> getPatientPaymentHistory(final int patientId, int chartId)
	{
		return new Specification<ReceiptDetail>() {

			@Override
			public Predicate toPredicate(Root<ReceiptDetail> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				

				Predicate paymentHistoryPredicate=cq.where(cb.equal(root.get(ReceiptDetail_.receiptDetailPayerId), patientId),
						cb.equal(root.get(ReceiptDetail_.receiptDetailPayerType), 1)).getRestriction();

				return paymentHistoryPredicate;
			}

		};
	}

	/**
	 * @return list of available Language options  
	 */	
	public static Specification<NonServiceDetails> getNonServiceDetailPatientPaymentHistory(final int patientId, int chartId)
	{
		return new Specification<NonServiceDetails>() {

			@Override
			public Predicate toPredicate(Root<NonServiceDetails> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				if (Long.class != cq.getResultType()) {
				 root.fetch(NonServiceDetails_.receiptDetails,JoinType.LEFT);
				 root.fetch(NonServiceDetails_.namesMapping,JoinType.LEFT);
				}

				Predicate paymentHistoryPredicate=cq.where(cb.equal(root.get(NonServiceDetails_.nonServiceDetailPatientId), patientId)).getRestriction();

				return paymentHistoryPredicate;
			}

		};
	}


	/**
	 * @return list of available Language options  
	 */	
	public static Specification<GeneratedBillsHistoryDetails> getPatientStatementHistory(final int patientId, int chartId)
	{
		return new Specification<GeneratedBillsHistoryDetails>() {

			@Override
			public Predicate toPredicate(Root<GeneratedBillsHistoryDetails> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {

				
				if (Long.class != cq.getResultType()) {
					
					root.fetch(GeneratedBillsHistoryDetails_.bill_status,JoinType.LEFT);
					root.fetch(GeneratedBillsHistoryDetails_.ensBillsDetails, JoinType.LEFT);
				}

				Predicate statementHistoryPredicate=cq.where(cb.equal(root.get(GeneratedBillsHistoryDetails_.generated_bills_history_details_patientid), patientId)).getRestriction();

				return statementHistoryPredicate;
			}

		};
	}

	/**
	 * @return list of available Language options  
	 */	
	public static Specification<GeneratedBillsHistoryDetails> getPatientBalance(final int patientId)
	{
		return new Specification<GeneratedBillsHistoryDetails>() {

			@Override
			public Predicate toPredicate(Root<GeneratedBillsHistoryDetails> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {

				Predicate statementHistoryPredicate=cq.multiselect(cb.function("pt_balance", BigDecimal.class, cb.literal(root.get(GeneratedBillsHistoryDetails_.generated_bills_history_details_patientid)))).where(cb.equal(root.get(GeneratedBillsHistoryDetails_.generated_bills_history_details_patientid), patientId)).getRestriction();

				return statementHistoryPredicate;
			}

		};
	}

	/**
	 * @return UserinformationCreditcard  
	 */	
	public static Specification<UserinformationCreditcard> getUserinformationCreditcard(final int pos)
	{
		return new Specification<UserinformationCreditcard>() {

			@Override
			public Predicate toPredicate(Root<UserinformationCreditcard> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {

				Predicate userinformationCreditcardPredicate=cq.where(cb.equal(root.get(UserinformationCreditcard_.pos),String.valueOf(pos))).getRestriction();

				return userinformationCreditcardPredicate;
			}

		};
	}


	public static Specification<PaymentService> getLastPaymentSummary(final Integer paymentServicePatientid) {

		return new Specification<PaymentService>() {

			@Override
			public Predicate toPredicate(Root<PaymentService> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

				Predicate paymentSummaryPredicate=cq.where(cb.equal(root.get(PaymentService_.paymentServicePatientid), paymentServicePatientid),cb.equal(root.get(PaymentService_.paymentServiceId),cb.max(root.get(PaymentService_.paymentServiceId)))).getRestriction();

				return paymentSummaryPredicate;
			}
		};

	}

	
	
	public static Specification<PatientInsDetail> getPatientInsDetails(final Integer patientId) {

		return new Specification<PatientInsDetail>() {

			@Override
			public Predicate toPredicate(Root<PatientInsDetail> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

				
				root.fetch(PatientInsDetail_.insCompAddr);
				Predicate paymentSummaryPredicate=cq.where(cb.equal(root.get(PatientInsDetail_.patientInsDetailPatientid), patientId)).orderBy(cb.asc(root.get(PatientInsDetail_.patientInsDetailInstype))).getRestriction();

				return paymentSummaryPredicate;
			}
		};

	}
	
	public static Specification<GeneratedBillsHistoryDetails> getPatientStatementFileDetails(final int patientId, final int billId) {

		return new Specification<GeneratedBillsHistoryDetails>() {

			@Override
			public Predicate toPredicate(Root<GeneratedBillsHistoryDetails> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

				
				root.fetch(GeneratedBillsHistoryDetails_.ensBillsDetails, JoinType.LEFT);
				Predicate statementPredicate=cq.where(cb.equal(root.get(GeneratedBillsHistoryDetails_.generated_bills_history_details_patientid), patientId),cb.equal(root.get(GeneratedBillsHistoryDetails_.generated_bills_history_details_billid), billId)).getRestriction();
				
				return statementPredicate;
			}
		};

	}
	
	public static Specification<IntermediateStmt> getPatientIMStatementFileDetails(final int patientId, final int billId) {

		return new Specification<IntermediateStmt>() {

			@Override
			public Predicate toPredicate(Root<IntermediateStmt> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

				
				Predicate statementPredicate=cq.where(cb.equal(root.get(IntermediateStmt_.intermediateStmtId), billId)).getRestriction();
				
				return statementPredicate;
			}
		};

	}
	
	public static Specification<EnsBillsDetails> getStatementDetailsByBatchNo(final int batchNo) {

		return new Specification<EnsBillsDetails>() {

			@Override
			public Predicate toPredicate(Root<EnsBillsDetails> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

				
				Predicate batchNoPredicate=cq.where(cb.equal(root.get(EnsBillsDetails_.batchno), batchNo)).getRestriction();
				
				return batchNoPredicate;
			}
		};

	}
	
	public static Specification<PosTable> getTransFirstConfiguredPos() {

		return new Specification<PosTable>() {

			@Override
			public Predicate toPredicate(Root<PosTable> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				return cq.where(cb.equal(root.get(PosTable_.posTableIstransfirstConfigured), true)).getRestriction();
			}
		};

	}

	
	public static Pageable createPortalPaymentHistoryPageRequestByDescDate(int pageIndex, int offset) {

		return new PageRequest(pageIndex, offset, Sort.Direction.DESC,"receiptDetailCreatedDate");
	}


	public static Pageable createPortalStatementHistoryPageRequestByDescDate(int pageIndex, int offset) {
		
		return new PageRequest(pageIndex, offset, Sort.Direction.DESC, "generatedDate");
		
	}
	
	
}
