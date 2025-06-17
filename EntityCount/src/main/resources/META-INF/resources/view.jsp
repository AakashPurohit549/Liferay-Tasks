<%@ include file="init.jsp"%>

<div class="container">

	<div class="row g-2">
		<c:if test="${showJournalArticles}">
			<div class="col md-3">
				<div class="card bg-light tileView">
					<p class="card-body p-1 m-0 bg-light ">
						<span class="customFontSize">Journal Articles</span> <span
							class="font-weight-bold count"> ${journalCount} </span>
					</p>
				</div>
			</div>
		</c:if>

		<c:if test="${showImages}">
			<div class="col md-3">
				<div class="card bg-light tileView">
					<p class="card-body p-1 m-0 bg-light ">
						<img class="customSizeImg" src="<%=request.getContextPath()%>/images/bookmark%201.png">

						<span class="customFontSize">Images</span> <span
							class="font-weight-bold count"> ${imageCount}</span>
					</p>
				</div>
			</div>
		</c:if>

		<c:if test="${showUsers}">
			<div class="col md-3">
				<div class="card bg-light tileView">
					<img class="customSizeImg" src="<%=request.getContextPath()%>/images/Vector%20(2).png">

					<p class="card-body p-1 m-0 bg-light ">
						<span class="customFontSize">Users</span> <span
							class="font-weight-bold count"> ${userCount}</span>
					</p>
				</div>
			</div>
		</c:if>


		<c:if test="${showEmployees}">
			<div class="col md-3">
				<div class="card bg-light tileView">
					<img  class="customSizeImg" src="<%=request.getContextPath()%>/images/Vector.png">

					<p class="card-body p-1 m-0 bg-light ">
						<span class="customFontSize">Employees</span> <span
							class="font-weight-bold count"> ${employees}</span>
					</p>
				</div>
			</div>
		</c:if>

		<c:if test="${showCategories}">
			<div class="col md-3">
				<div class="card bg-light tileView">
					<img class="customSizeImg" src="<%=request.getContextPath()%>/images/bookmark%201.png">

					<p class="card-body p-1 m-0 bg-light ">
						<span class="customFontSize">Technology</span> <span
							class="font-weight-bold count"> ${categories}</span>
					</p>
				</div>
			</div>
		</c:if>

	</div>

</div>




