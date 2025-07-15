package employee.web.actions;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.IOException;
import java.io.OutputStream;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import employee.service.model.Employee;
import employee.service.service.EmployeeLocalService;
import employee.web.constants.EmployeeWebPortletKeys;

@Component(property = { "javax.portlet.name=" + EmployeeWebPortletKeys.EMPLOYEEWEB,
		"mvc.command.name=/downloadFile" }, service = MVCResourceCommand.class)

public class DownloadResourceCommand implements MVCResourceCommand {

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		
		try {

			String employeeId = resourceRequest.getParameter("employeeId");

			long empId = Long.parseLong(employeeId);

			Employee employee = employeeLocalServcice.getEmployee(empId);
			try {
				resourceResponse.setContentType("application/pdf");
				resourceResponse.addProperty("Content-Disposition", "attachment; filename=\"EmployeeData.pdf\"");

				OutputStream os = resourceResponse.getPortletOutputStream();

				Document document = new Document();
				PdfWriter.getInstance(document, os);

				document.open();
				document.add(new Paragraph("First Name : " + employee.getFirstName()));
				document.add(new Paragraph("Last Name : " + employee.getLastName()));
				document.add(new Paragraph("Designation : " + employee.getDesignation()));
				document.add(new Paragraph("Email Address : " + employee.getEmailAddress()));
				document.add(new Paragraph("Phone Number : " + employee.getPhoneNumber()));
				document.add(new Paragraph("City : " + employee.getCity()));
				document.close();

				os.flush();
				os.close();

			} catch (Exception e) {
				e.printStackTrace();
				throw new PortletException("Error generating PDF", e);
			}

			return false;
		} catch (PortalException e) {
			System.out.print(e);

			return true;
		}
	}

	@Reference
	private EmployeeLocalService employeeLocalServcice;

}
