package com.example.appointment.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.appointment.intercomm.UserFeignController;
import com.example.appointment.model.AppointmentModel;
import com.example.appointment.model.AppointmentModelVo;
import com.example.appointment.model.vo.PrescriptionVO;
import com.example.appointment.model.vo.UserPrescriptionDetails;
import com.example.appointment.repository.AppointmentRepository;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class AppointmentServiceImpl implements AppointmentService{
	
	@Autowired
	AppointmentRepository  appointmentRepository; 
	
	@Autowired
	UserFeignController userFeignController;

	@Override
	public AppointmentModel bookAppointment(AppointmentModelVo bookingDetails) {
		// TODO Auto-generated method stub
		AppointmentModel details = new AppointmentModel();
		details.setAppointmentDate(bookingDetails.getAppointmentDate());
		details.setDisease(bookingDetails.getDisease());
		details.setDoctorEmail(bookingDetails.getDoctorEmail());
		details.setFollowUp(false);
		details.setPatientEmail(bookingDetails.getPatientEmail());
		details.setPrescription(null);
		details.setStatus("PENDING");
		appointmentRepository.save(details);
		return details;
	}

	@Override
	public AppointmentModel updateAppointment(int appointmentId, String status, String prescription, Boolean followUp) {
		// TODO Auto-generated method stub
		//AppointmentModel appointmentDetails = appointmentRepository.findByPatientAndDoctorEmail(patientEmail, doctorEmail);
		AppointmentModel appointmentDetails = appointmentRepository.findByAppointmentId(appointmentId);
		if(followUp!=null)
		{
			appointmentDetails.setFollowUp(true);
		}
		if(status!=null)
		{
			appointmentDetails.setStatus(status);
		}
		if(prescription!=null)
		{
			appointmentDetails.setPrescription(prescription);
		}
		
		appointmentRepository.save(appointmentDetails);
		return appointmentDetails;
	}

	@Override
	public List<AppointmentModel> getPendingAppointments(String doctorEmail) {
		// TODO Auto-generated method stub
		List<AppointmentModel> appointments = appointmentRepository.getAllPendingAppointments(doctorEmail);
		return appointments;
	}

	@Override
	public List<AppointmentModel> getTodaysAppointment(String doctorEmail) {
		// TODO Auto-generated method stub
		List<AppointmentModel> appointments = appointmentRepository.getAllTodaysAppointments(doctorEmail);
		return appointments;
	}

	@Override
	public List<AppointmentModel> getPreviousAppointmentsForPatient(String doctorEmail, String patientEmail, int pageNo) {
		// TODO Auto-generated method stub
		
		Pageable page = PageRequest.of(pageNo, 2);
		Page<AppointmentModel> appointments = appointmentRepository.getAllPreviousAppointments(doctorEmail, patientEmail, page);
		List<AppointmentModel> list = appointments!=null?appointments.getContent():new ArrayList<AppointmentModel>();
		return list;
	}

	@Override
	public String getPrescriptionPdf(UserPrescriptionDetails details, HttpServletResponse response) {
		// TODO Auto-generated method stub
		 Document document = new Document();
		 PdfPTable table = new PdfPTable(new float[] {9, 9, 9, 9, 9, 9, 9});
		 table.setHorizontalAlignment(0);
		 table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		 Phrase medicineName = new Phrase("Medicine Name",
					new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
			Phrase beforeBreakfast = new Phrase("Before Breakfast", new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
			Phrase afterBreakfast = new Phrase("After Breakfast", new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
			Phrase beforeLunch = new Phrase("Before Lunch", new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
			Phrase afterLunch = new Phrase("After Lunch", new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
			Phrase beforeDinner = new Phrase("Before Dinner", new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
			Phrase afterDinner = new Phrase("After Dinner", new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
			table.addCell(medicineName);
			table.addCell(beforeBreakfast);
			table.addCell(afterBreakfast);
			table.addCell(beforeLunch);
			table.addCell(afterLunch);
			table.addCell(beforeDinner);
			table.addCell(afterDinner);
			table.setHeaderRows(1);
			PdfPCell[] cells = table.getRow(0).getCells();
			for (int j = 0; j < cells.length; j++) {
				cells[j].setBackgroundColor(BaseColor.LIGHT_GRAY);
			}
		
			details.getPrescriptionList().forEach(prescription->{
				
				table.addCell(prescription.getMedicineName());
				table.addCell(prescription.getBeforeBreakfast()? "1" : "");
				table.addCell(prescription.getAfterBreakfast()? "1" : "");
				table.addCell(prescription.getBeforeLunch()? "1" : "");
				table.addCell(prescription.getAfterLunch()? "1" : "");
				table.addCell(prescription.getBeforeDinner()? "1" : "");
				table.addCell(prescription.getAfterDinner()? "1" : "");
				
			});
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			try {
				PdfWriter.getInstance(document, baos);

				document.open();

				/*
				 * Image image=Image.getInstance("src/main/resources/icon.jpg");
				 * image.setAlignment(1);
				 */
				String pattern = "yyyy.MMM.dd HH.mm";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));

				String date = DateTimeFormatter.ofPattern("dd MMM yyyy  HH:mm", Locale.ENGLISH).format(ldt);

				Paragraph p1 = new Paragraph("Doctor Clinic Name",
						new Font(FontFamily.HELVETICA, 22, Font.UNDERLINE, BaseColor.DARK_GRAY));
				p1.setAlignment(1);

				document.add(p1);
				document.add(new Paragraph(" "));

				Paragraph p = new Paragraph("Medical Prescription",
						new Font(FontFamily.HELVETICA, 22, Font.NORMAL, BaseColor.DARK_GRAY));
				p.setAlignment(1);
				// document.add(image);
				//logger.error("Image  ERROR !!!!!!");
				document.add(p);
				document.add(new Paragraph(" "));
				Paragraph todaydate = new Paragraph("Date : " + date,
						new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.DARK_GRAY));
				todaydate.setAlignment(2);
				document.add(todaydate);
				document.add(new Paragraph(" "));
				document.add(new Paragraph(
						"Patient Name" + " : " + details.getPatientName() + "",
						new Font(FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.DARK_GRAY)));
				
				document.add(new Paragraph(
						"Patient Age"+ " : " + details.getPatientAge()+ "",
						new Font(FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.DARK_GRAY)));
				
				document.add(new Paragraph(
						"Doctor Name"+ " : " + details.getDoctorName()+ "",
						new Font(FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.DARK_GRAY)));
				document.add(new Paragraph(" "));
				document.add(new Paragraph(" "));
				document.add(table);
				document.add(new Paragraph(" "));
				document.add(new Paragraph(" "));
				Paragraph p2 = new Paragraph("",
						new Font(FontFamily.HELVETICA, 6, Font.NORMAL, BaseColor.DARK_GRAY));
				p2.setAlignment(1);
				document.add(p2);
			}catch (DocumentException e) {
				return "document cannot be downloaded";

			} finally {
				document.close();
			}

			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Content-Disposition", "attachment;filename=YourDreamHome.pdf");
			response.setHeader("Pragma", "public");
			response.setContentLength(baos.size());
//			ServletOutputStream os;

			try(ServletOutputStream os= response.getOutputStream()) {
//				os = response.getOutputStream();

				baos.writeTo(os);

				os.flush();
				baos.close();
				os.close();

			} catch (IOException e) {
				//UTCLLogger.error(this.getClass(), e.getMessage());
				return "exception occured";
			}

			return "document is downloaded successfully";
			
	}

}
