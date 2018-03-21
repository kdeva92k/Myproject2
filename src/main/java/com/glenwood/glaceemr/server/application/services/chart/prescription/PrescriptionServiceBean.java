package com.glenwood.glaceemr.server.application.services.chart.prescription;

import java.util.Date;

public class PrescriptionServiceBean {

     String brandName;
     Date orderedDate;
     int status;
     int encounterId;
     String comments;
     String form;
     String route;
     String dose;
     String unit;
     String quantity;
     String rxName;
     public PrescriptionServiceBean(String brandName, Date orderedDate, int status,
             int encounterId,String form,String dose,String comments) {
         super();
         this.brandName = brandName;
         this.orderedDate = orderedDate;
         this.status = status;
         this.encounterId = encounterId;
         this.form = form;
         this.dose = dose;
         this.comments = comments;
     }
     public PrescriptionServiceBean() {
         // TODO Auto-generated constructor stub
     }

     public String getRxName() {
         return rxName;
     }
     public void setRxName(String rxName) {
         this.rxName = rxName;
     }
     public String getComments() {
         return comments;
     }
     public void setComments(String comments) {
         this.comments = comments;
     }
     public String getForm() {
         return form;
     }
     public void setForm(String form) {
         this.form = form;
     }
     public String getRoute() {
         return route;
     }
     public void setRoute(String route) {
         this.route = route;
     }
     public String getDose() {
         return dose;
     }
     public void setDose(String dose) {
         this.dose = dose;
     }
     public String getUnit() {
         return unit;
     }
     public void setUnit(String unit) {
         this.unit = unit;
     }
     public String getQuantity() {
         return quantity;
     }
     public void setQuantity(String quantity) {
         this.quantity = quantity;
     }
     public String getBrandName() {
         return brandName;
     }
     public void setBrandName(String brandName) {
         this.brandName = brandName;
     }
     public Date getOrderedDate() {
         return orderedDate;
     }
     public void setOrderedDate(Date orderedDate) {
         this.orderedDate = orderedDate;
     }
     public int getStatus() {
         return status;
     }
     public void setStatus(int status) {
         this.status = status;
     }
     public int getEncounterId() {
         return encounterId;
     }
     public void setEncounterId(int encounterId) {
         this.encounterId = encounterId;
     }


}





