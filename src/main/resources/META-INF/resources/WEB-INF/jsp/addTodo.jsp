
                <%@ include file="common/header.jspf" %>
                <%@ include file="common/navigation.jspf" %>
               <div class="container">
                    <h1>Add New Todo to List</h1>
                    <form:form method="post" modelAttribute="listOfTodos">
                         <fieldset class="mb-3">
                              <form:label path="description">Description : </form:label>
                              <form:input type="text" path="description" required="required" />
                              <br>
                              <form:errors path="description" cssClass="text-warning" />
                         </fieldset>
                         <fieldset class="mb-3">
                              <form:label path="DATE_COLUMN">Target Date : </form:label>
                              <form:input type="text" path="DATE_COLUMN" required="required" />
                              <br>
                              <form:errors path="DATE_COLUMN" cssClass="text-warning" />
                         </fieldset>
                         <form:input type="hidden" path="id" />
                         <form:input type="hidden" path="done" />
                         <br>
                         <input type="submit" class="btn btn-success" />

                    </form:form>
               </div>
               <%@ include file="common/footer.jspf" %>
