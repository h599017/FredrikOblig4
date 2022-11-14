<!DOCTYPE html>
<!-- saved from url=(0032)http://localhost:8080/innlogging -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<link rel="stylesheet" href="main.css">
<link rel="stylesheet" href="simple.css">
<title>Logg inn</title>
</head>
<body>
    <h2>Logg inn</h2>
    <p style="color:red;"></p>
    <form method="post">
        <fieldset>
            <label>Mobil:</label> <input type="text" name="mobil" title="Mobil må være 8 siffer" required pattern="[0-9]{8}" required="required" > <br> <br>
            <label>Passord:</label> <input type="password" name="passord" required ="required" title="Passord feltet kan ikke være tomt">
            <br><br><button type="submit">Logg inn</button>
        </fieldset>
    </form>
     <p class = "feilmld"> ${errorMsg} </p>
     <p class = "feilmld"> ${redirectMessage} </p>


</body></html>