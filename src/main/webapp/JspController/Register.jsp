<html>
<body>

<h2>REGISTRATION PAGE</h2>

<form >
  <input type="radio" name="care" value="seeker"> Seeker &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="radio" name="care" value="sitter"> Sitter<br>
</form>

<form id="seeker" method="post" action="/prac2" hidden="true">
<b>Enter details</b>
   <br>FIRST NAME<input type="text" name="firstName">
    <br>LAST NAME<input type="text" name="lastName" >
    <br>PHONE NUMBER<input type="text" name="phoneNumber" >
    <br>EMAIL ADDRESS<input type="text" name="email" >
    <br>Seeker<input type="text" name="email" >
    <br>
    <br><input type="submit" value="submit">
</form>
<form id="sitter" method="post" action="/prac3" hidden="true">
<br>FIRST NAME<input type="text" name="firstName">
    <br>LAST NAME<input type="text" name="lastName" >
    <br>PHONE NUMBER<input type="text" name="phoneNumber" >
    <br>EMAIL ADDRESS<input type="text" name="email" >
    <br>Sitter<input type="text" name="email" >
    <br>
    <br><input type="submit" value="submit">
</form>
<script>

</script>
</body>
</html>