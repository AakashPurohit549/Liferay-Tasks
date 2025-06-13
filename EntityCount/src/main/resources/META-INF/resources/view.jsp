<%@ include file="init.jsp" %>

<p>
	<b><liferay-ui:message key="entitycount.caption"/></b>
</p>


<h2>Portlet Content Counts</h2>

<div class="row g-2">
<c:if test="${showJournalArticles}">
     <div class="col md-3">
     <div class="card bg-light tileView">
    <p class="card-body bg-light ">
    <span class="text-bold">Journal Articles</span>
    <span class="font-weight-bold right-most"> ${journalCount} </span></p>
    </div>
    </div>
</c:if> 

<c:if test="${showDocuments}">
     <div class="col md-3">
      <div class="card bg-light tileView">
    <p class="card-body bg-light ">
     <span class="text-bold">Documents</span>
     <span class="font-weight-bold right-most"> ${docCount}</span></p>
    </div>
    </div>
</c:if>

<c:if test="${showUsers}">
     <div class="col md-3">
      <div class="card bg-light tileView">
    <p class="card-body bg-light ">
     <span class="text-bold">Users</span>
     <span class="font-weight-bold right-most"> ${userCount}</span></p>
    </div>
    </div>
</c:if>
</div>



<h2>Test for System UI Content Counts</h2>

<c:if test="${journalCountInter > -1 }">
    <p>Journal Articles: ${journalCount}</p>
</c:if>

<c:if test="${docCountInter > -1}">
    <p>Documents: ${docCountInter}</p>
</c:if>

<c:if test="${userCountInter > -1}">
    <p>Users: ${userCountInter}</p>
</c:if>


