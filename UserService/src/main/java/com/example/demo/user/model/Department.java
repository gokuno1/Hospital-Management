package com.example.demo.user.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DEPARTMENT_DETAILS")
public class Department {
	
		private int deptDetailsId;
		private int deptId;
		private int doctorId;
		
		public int getDeptDetailsId() {
			return deptDetailsId;
		}
		public void setDeptDetailsId(int deptDetailsId) {
			this.deptDetailsId = deptDetailsId;
		}
		public int getDeptId() {
			return deptId;
		}
		public void setDeptId(int deptId) {
			this.deptId = deptId;
		}
		public int getDoctorId() {
			return doctorId;
		}
		public void setDoctorId(int doctorId) {
			this.doctorId = doctorId;
		}
		
		

}
