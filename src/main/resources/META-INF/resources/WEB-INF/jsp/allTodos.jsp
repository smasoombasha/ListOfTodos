  <%@ include file="common/header.jspf" %>
                  <%@ include file="common/navigation.jspf" %>
       <div class="container">
                          <pre>${msg}</pre>
                              <form action="submit-code" method="POST">
                                  <label>Unique Code : </label>
                                  <input type="text" name="code">
                                  <button class="btn btn-success" type="submit">Submit</button>
                              </form>
       </div>
       <%@ include file="common/footer.jspf" %>