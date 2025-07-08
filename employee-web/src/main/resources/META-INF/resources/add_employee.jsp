<%@ include file="init.jsp"%>


<portlet:actionURL name="addEmployee" var="addEmployeeActionURL" />


<div class="conatiner col-md5">

	<div class="card">

		<div class="card-title m-2 p-2 title-heading">Employee Form</div>

		<div class="card-body">

			<h2>Add Employee Form here</h2>

<%-- <c:if test="${employee == null}">
			<aui:form action="${addEmployeeActionURL}" 
				method="post"> </c:if>
				
				
				<c:if test="${employee != null}">
				<aui:form action="${updateEmployeeActionURL}"
				method="post"> </c:if> --%>
				
				<aui:form action="${addEmployeeActionURL}" name="employeeForm"
				method="POST">

				<div class="row">

					<div class="col-md-6">
						<fieldset class="form-group">
							<aui:input name="firstName" class="form-element"
								cssClass="form-element" placeholder="Enter First Name">
								<aui:validator name="required" />
								<aui:validator name="alpha" />
							</aui:input>
						</fieldset>
					</div>




					<div class="col-md-6">
						<fieldset class="form-group">
							<aui:input name="lastName" class="form-element"
								cssClass="form-element" placeholder="Enter Last Name">
								<aui:validator name="required" />
								<aui:validator name="alpha" />
							</aui:input>
						</fieldset>
					</div>

				</div>

				<aui:input name="designation" class="form-element-designation"
					cssClass="form-element-designation" label="Designation"
					placeholder="Enter your designation ">
					<aui:validator name="required" />

				</aui:input>


				<div class="row">

					<div class="col-md-6">


						<aui:input name="emailAddress" class="form-element"
							cssClass="form-element" placeholder="Enter your email"
							label="Email">
							<aui:validator name="required" />
							<aui:validator name="email" />

						</aui:input>
					</div>

					<div class="col-md-6">

						<aui:input name="phoneNumber" class="form-element"
							cssClass="form-element" placeholder="Enter your phone number"
							label="Phone">
							<aui:validator name="required" />
							<aui:validator name="number" />

						</aui:input>
					</div>

				</div>
				
				 <aui:input name="password" type="password" 
				 class="form-element"  cssClass="form-element"  label="Password">
					<aui:validator name="required" />
				</aui:input>


				<div class="row">
					<div class="col-md-6">
						<aui:input name="addressLine1" class="form-element"
							cssClass="form-element"
							placeholder="Enter your house no/Bidg./Appt."
							label="Address Line 1">
							<aui:validator name="required" />
						</aui:input>
					</div>

					<div class="col-md-6">

						<aui:input name="addressLine2" class="form-element"
							cssClass="form-element"
							placeholder="Enter your house street/lane/area"
							label="Address Line 2">
							<aui:validator name="required" />

						</aui:input>
					</div>

				</div>



				<div class="row">
					<div class="col-md-6">
						<aui:input name="city" class="form-element"
							cssClass="form-element" placeholder="Enter your city"
							label="City">
							<aui:validator name="required" />

						</aui:input>
					</div>

					<div class="col-md-6">
						<aui:input name="zipCode" class="form-element"
							cssClass="form-element"
							placeholder="Enter your post code / zip code"
							label="Post Code / Zip Code">
							<aui:validator name="required" />
							<aui:validator name="number" />

						</aui:input>
					</div>
				</div>



				<aui:button class="employee-submit-btn"
					cssClass="employee-submit-btn" type="submit" name="" value="Submit"></aui:button>
			</aui:form>


			<portlet:renderURL var="backToViewURL">
				<portlet:param name="mvcPath" value="/view.jsp"></portlet:param>
			</portlet:renderURL>

			<a class="btn btn-warning mt-3" href="${ backToViewURL }">Click
				here to back to view jsp page</a>


		</div>
	</div>
</div>




