<!DOCTYPE html>
<html lang="no">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="main.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="./simple.css">

<title>Pamelding</title>
</head>
<body>
    <h2>Pamelding</h2>
    
    <form id="deltagerFormBean" action="/paamelding" method="post">
        <fieldset>
            <label for="fornavn">Fornavn</label>
            <!-- NB! Spring sin (form:input)-tag støtter ikke required alene,
men greit med required="noe"! -->
            <input type="text" id="fornavn" name="fornavn" title="Fornavn ma starte med en stor bokstav og være mer enn en bokstav" required pattern="\s*\p{Lu}\p{Ll}+\s*"
                placeholder="Fyll inn fornavn" required="required"
                type="text" /> 
                <br>
                <br>
            <label for="etternavn">Etternavn:</label>
            <input type="text" id="etternavn" name="etternavn" title="Etternavn ma starte med stor forbokstav og innholde kun bokstaver, og antall tegn ma vaere mellom 2 og 20"
                required pattern="\s*\p{Lu}\p{Ll}+\s*" placeholder="Fyll inn etternavn"
                required="required" type="text" /> 
                <br>
                <br>
                <label for="mobil">Mobil: </label> 
                <input id="mobil"
                name="mobil" title="Mobilnummer ma være eksakt 8 siffer" required pattern="[0-9]{8}"
                placeholder="Fyll inn telefonnummer" required="required"
                type="text" /> 
                
                <br>
                <br>

            <label>Passord: <input name="passord"
                id="password" type="password" required pattern=".{8,}" 
                title="Passordet ma vaere minst 8 tegn"
                placeholder="Velg et passord" required="required" onkeyup='check();' /> 
                <span id="StrengthDisp" class="badge displayBadge"></span>
            <br>
           
            </label> <br> <label>Repetert passord: <input
                type="password" name="confirm_password"
                id="confirm_password" required pattern=".{8,}" 
                title="Passordet ma samsvare med passordet." placeholder="Gjenta passord" 
                required="required" onkeyup='check();' /> 
                <span
                id='message'></span>
            </label> <br>
                <br>
                
                <label
                for="kjonn">Kjonn:</label> 
                <input id="kjonn1"
                name="kjonn" checked="checked" type="radio" value="mann" />mann
            <input id="kjonn2" name="kjonn" type="radio" value="kvinne" />kvinne
            <br>
            <br>
            <button type="submit">Meld meg pa</button>
        </fieldset>
    </form>
    <p class="feilmld"> ${errorMsg} </p>
    <p class = "feilmld"> ${ugyldig} </p>
    
    <script src="myscript.js" defer></script>
</body>
</html>
