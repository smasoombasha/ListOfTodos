     <%@ include file="common/header.jspf" %>
       <%@ include file="common/navigation.jspf" %>
       <div class="container">
       			<h1>Your Todos</h1>
       			<table class="table">
       				<thead>
       					<tr>
       					<th>id</th>
       						<th>Description</th>
       						<th>Target Date</th>
       						<th>Is Done?</th>
       					</tr>
       				</thead>
       				<tbody>
       					<c:forEach items="${todo}" var="todo">
       						<tr>
       						<th>${todo.id}</th>
       							<td>${todo.description}</td>
       							<td>${todo.DATE_COLUMN}</td>
       							<td>${todo.done}</td>
								<td><a href="delete-todo?id=${todo.id}" Class="btn btn-warning" >Delete</a></td>
								<td><a href="update-todo?id=${todo.id}" Class="btn btn-success" >Update</a></td>
       						</tr>
       					</c:forEach>
       				</tbody>
       			</table>
               			<a href="add-todo" class="btn btn-success">Add Todo</a>
       		</div><%@ include file="common/footer.jspf" %>








