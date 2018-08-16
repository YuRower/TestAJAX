   $(document).ready(function() {
              $('#submit').click(function(event) {
                $.ajax({
                  url : 'ActionServlet',
                  data : {
                    user : $('#user').val()
                  },
                  success : function(responseText) {
                    $('#welcometext').text(responseText);
                  }
                });
              });
            });