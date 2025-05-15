<%
  String message = (String) session.getAttribute("message");
  if (message != null) {
%>
  <div class="flash-message success-msg" id="flash-message"><%= message %></div>
  
  <script>
  
  		/* setTimeout(function (){
  			var flash = document.getElementById("flash-message");
  			
  			if(flash){
  				flash.style.display = 'none';
  			}
  		}, 3000); */
  		
  		setTimeout(function() {
  		  var flash = document.getElementById('flash-message');
  		  if (flash) {
  		    flash.style.opacity = '0';
  		    setTimeout(function() {
  		      flash.style.display = 'none';
  		    }, 500); // wait for fade out to finish
  		  }
  		}, 3000);
  
  </script>
<%
    session.removeAttribute("message");
  }
%>