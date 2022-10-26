<%@page isELIgnored="false"%>
<html lang="en">
  <head>
    <title>Please sign in</title>
  </head>
  <body>
     <div class="container">
     <p> ${errorMsg} </p>
     <p> ${msg} </p>
      <form class="form" method="post" action="/login">
        <h2 class="form heading">Please sign in</h2>
        <p>
          <label for="username" class="username">Username</label>
          <input type="text" id="username" name="username" class="form-control" placeholder="Username" required autofocus>
        </p>
        <p>
          <label for="password" class="password">Password</label>
          <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
        </p>
        <button class="btn" type="submit">Sign in</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
      </form>
</div>
</body>
</html>